//Demo this one

package snipp.test.bav.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

import java.io.IOException;

//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;

//import org.apache.poi.ss.usermodel.DataFormatter;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.Table.Cell;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.ss.usermodel.CellType;

public class ExcelUtils {

	static String projectPath;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public ExcelUtils(String excelPath, String sheetName) throws Exception {
		try {
			projectPath = System.getProperty("user.dir");
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(sheetName);

		} catch (IOException e) {
			System.out
					.println("IOException error from ExcelUtils(String excelPath, String sheetName) throws Exception ");
			e.printStackTrace();
		}

	}

	public static int getColCount() throws IOException {

		int colCount = 0;
		try {
			colCount = sheet.getRow(0).getPhysicalNumberOfCells();

			System.out.println("Number of colums " + colCount);
		} catch (Exception e) {
			e.getMessage();
			e.getCause();
			e.printStackTrace();
		}

		// Call row count function
		return colCount;

	} // End column count

	public static int getRowCount() throws IOException {
		int countRows = 0;
		try {

			countRows = sheet.getPhysicalNumberOfRows();

			System.out.println("Number of rows " + countRows);
		} catch (Exception e) {
			e.getMessage();
			e.getCause();
			e.printStackTrace();
		}

		// Call row count function
		return countRows;

	} // End getRowCount

	public static String getCellDataString(int rowNum, int colNum) {

		Iterator rows = sheet.rowIterator();
		String cellDataS = "";
		int cellDataD = 0;
		String toreturn = "";
		XSSFRow row = (XSSFRow) sheet.getRow(rowNum);

		XSSFCell cell = (XSSFCell) row.getCell(colNum);

		CellType type = cell.getCellTypeEnum();

		if (type == CellType.STRING) {
			System.out.println("Is a string");
			toreturn = cellDataS = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
			System.out.println("Here it is" + cell);

		}
		System.out.println(type);
		if (type == CellType.NUMERIC) {
			System.out.println("Is numeric");
			// if it is numeric then call getNumericCellValue
			System.out.println(sheet.getRow(rowNum).getCell(colNum).getNumericCellValue());

			//////////////////////////////////
			cell.setCellType(CellType.NUMERIC);
			// convert double to int , then int to string
			toreturn = Double.toString(cell.getNumericCellValue()); // Double to String (working)

			String left = "";
			String right = "";
			int p = toreturn.indexOf('.');
			if (p >= 0) {
				left = toreturn.substring(0, p);
				right = toreturn.substring(p + 1);
			} else {
				// s does not contain '.'
			}

			System.out.println("left number 'toreturn' is " + left);
			System.out.println("right number 'toreturn' is " + right);

			System.out.println("Full number 'toreturn' is " + toreturn);

			toreturn = left;
		}

		return toreturn;

	} // End getCellData

	public static void getCellDataNumber(int rowNum, int colNum) {
		// Step 3 - call function to get data from workbook
		try {

			String cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();

		} catch (Exception e) {
			e.getMessage();
			e.getCause();
			e.printStackTrace();
		}

	} // End getCellData

} // End ExcelUtils
