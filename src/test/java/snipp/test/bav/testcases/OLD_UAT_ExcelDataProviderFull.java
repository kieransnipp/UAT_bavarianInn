//Old, no reports

//This is a demo of the Registration data driven testing
//This file is reading an excel file from the Utility ExcelUtils.java 
// Excel file specified below is "excel\\bavarianInnRego.xlsx";

package snipp.test.bav.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.util.NumberToTextConverter;

import org.iq80.snappy.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import com.codeborne.selenide.WebDriverRunner;

import ch.qos.logback.core.net.SyslogOutputStream;

public class OLD_UAT_ExcelDataProviderFull {

	
	private static XSSFCell Cell;
	private static WebDriver driver = null;
	public static Properties prop;

	public OLD_UAT_ExcelDataProviderFull() {
		//super();
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/bav/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Pre conditions @Before
	@BeforeSuite // 1
	public void launchBrowser() {

		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			System.out.println("Launching firefox");
			// browserName = ((Object) driver).Firefox("drivers/geckodriver.exe");
			System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browserName.equals("ie")) {
			System.out.println("Launching opera");
			System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");

			driver = new InternetExplorerDriver();

		} else if (browserName.equals("opera")) {

			System.out.println("Launching opera");
			System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
			driver = new OperaDriver();

		}
		System.out.println("Maximize window, delete cookies");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.get(prop.getProperty("url"));

		WebDriverRunner.setWebDriver(driver);

		// Manage Window and cookies

	}



	@BeforeClass // 3
	public void enterURL() {
		System.out.println("Open URL");
		// UAT
		driver.get("http://bavarianinn.snipp.ie/home/");

		// Live
		// driver.get("https://www.bavarianperks.com/home/");
		System.out.println("@Before Class bit");
	}

	// test cases, starting with @Test
	@Test(priority = 1)
	public void loginToSite() {
		System.out.println("Verify the title of the login page");
		String pageTitle = driver.getTitle();
		System.out.println("Title of the page is " + pageTitle);
		AssertJUnit.assertEquals(driver.getTitle().contentEquals("Home"), true);
	}

	@Test(priority = 2)
	public void enterDetails() {
		System.out.println("Login to site");
		driver.findElement(By.xpath("//*[contains(text(), 'Register')]")).click();
		String titleRego = driver.getTitle();
		AssertJUnit.assertEquals(titleRego, "Register");
	}

	@Test(priority = 3, dataProvider = "test1data")
 public void test1(String firstName, String lastName, String emailAddress,String passWord1, String passWord2, String dateOfBirth, String mobilePhone, String homePhone, String country, String provence, String
 addressLine1,String addressLine2, String city, String zipCode, String reflocation
 ) throws InterruptedException {

		System.out.println("Mobile phone is " + mobilePhone);

		// FirstName
		driver.findElement(By.xpath("//*[@name='FirstName']")).sendKeys(firstName);
		// LastName
		driver.findElement(By.xpath("//*[@placeholder='Last name *']")).sendKeys(lastName);
		// // Email
		driver.findElement(By.xpath("//*[@placeholder='Email *']")).sendKeys(emailAddress);
		// // Password1
		driver.findElement(By.xpath("//*[@placeholder='Password *']")).sendKeys(passWord1);
		// Password2
		driver.findElement(By.xpath("//*[@placeholder='Confirm password *']")).sendKeys(passWord2);

		// // Date of Birth
		 driver.findElement(By.xpath("//*[@name='DateOfBirth']")).sendKeys(dateOfBirth);
		//
		// // Mobile number
		driver.findElement(By.xpath("//*[@placeholder='Mobile phone *']")).sendKeys(mobilePhone);
		//
		// // Ligne automatisée, tél. : 418 641‑6500
		// // Phone number
		 driver.findElement(By.xpath("//*[@name='Phone']")).sendKeys(homePhone);

		Select select = new Select(driver.findElement(By.name("Country")));
		 
		select.selectByVisibleText("United States");
		Thread.sleep(1000);
		select.selectByVisibleText(country);
		//
		// // Select State
		Select countryState = new Select(driver.findElement(By.xpath("//*[@id='States']")));
		// state.selectByIndex(1);
		countryState.selectByVisibleText("Yukon");

		// // Address 1
		 driver.findElement(By.xpath("//*[@name='AddressLine1']")).sendKeys(addressLine1);
		//
		// // Address 2
		 driver.findElement(By.xpath("//*[@name='AddressLine2']")).sendKeys(addressLine2);

		// // City
		 driver.findElement(By.xpath("//*[@name='City']")).sendKeys(city);
		//
		// // Québec (Québec) G1E 4T9
		// // Zip
		 driver.findElement(By.xpath("//*[@name='Zip']")).sendKeys(zipCode);

		 //driver.findElement(By.xpath("//*[@placeholder=\"Referral location/name \"']")).sendKeys(reflocation);

		// // Need club card - Collect at the Inn
		Select cardOption = new Select(driver.findElement(By.xpath("//*[@name='RequestPerksCard']")));
		cardOption.selectByIndex(2);

		Thread.sleep(2000);
		// Referral location
		driver.findElement(By.xpath("//*[@name='ReferralInfo']")).click();
		 driver.findElement(By.xpath("//*[@name='ReferralInfo']")).sendKeys(reflocation);
		 //driver.findElement(By.xpath("//*[@name='ReferralInfo']")).sendKeys(reflocation);

		// // Agree = yes
		 driver.findElement(By.xpath("//*[@class='control__indicator']")).click();
		//
		// // Select Register
		 driver.findElement(By.xpath("//*[@type='submit']")).click();
		 
		 
		 //To do
		//Rego part needs to be fixed before the test completed assertion can be completed

	} // End method

	@DataProvider(name = "test1data")
	public static Object[][] getData() throws Exception {
		String path = "excel\\bavarianInnRego.xlsx";
		String sheetName = "Sheet1";
		Object data[][] = testData(path, sheetName);

		return data;
	}


	@Test
	public static Object[][] testData(String excelPath, String sheetName) throws Exception {

		ExcelUtils ex = new ExcelUtils(excelPath, sheetName);

		int colCount = ex.getColCount();
		int rowCount = ex.getRowCount();

		Object data[][] = new Object[rowCount - 1][colCount];
		DataFormatter formatter = new DataFormatter();

		for (int i = 1; i < rowCount; i++) {

			for (int j = 0; j < colCount; j++) {
				System.out.println(j);
				String cellData = ex.getCellDataString(i, j);

				System.out.println(cellData + " | x");

				data[i - 1][j] = cellData;
			}
			System.out.println("");

		}
		return data;
	}// End testData

} //End class

