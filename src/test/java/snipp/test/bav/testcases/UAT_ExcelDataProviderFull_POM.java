//Demo this one

//This is a demo of the Registration data driven testing
//This file is reading an excel file from the Utility ExcelUtils.java 
// Excel file specified below is "excel\\bavarianInnRego.xlsx";

package snipp.test.bav.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
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
//import org.testng.AssertJUnit;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.codeborne.selenide.WebDriverRunner;
import ch.qos.logback.core.net.SyslogOutputStream;

import com.bav.pages.HomePage;
import com.bav.pages.RegisterPage;

public class UAT_ExcelDataProviderFull_POM {

	// Exent 1
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent = new ExtentReports();
	ExtentTest test;
	Exception exception = null;

	private static XSSFCell Cell;
	public static Properties prop;
	private static WebDriver driver = null;

	public UAT_ExcelDataProviderFull_POM() {
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
			System.out.println("Launching Internet Explorer");
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

	@BeforeClass
	public void setUp() {// Exent 2
		htmlReporter = new ExtentHtmlReporter("UAT_ExcelDataProviderFull_POM.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	// test cases, starting with @Test
	@Test(priority = 1)
	public void loginToSite() {
		// Exent 3
		ExtentTest test = extent.createTest("loginToSite ", "Bavarian inn");
		System.out.println("Verify the title of the login page");
		String title = driver.getTitle();
		System.out.println("Title of the page is " + title);

		Assert.assertEquals(title, "Home");
		test.pass("Passed");
	}

	@Test(priority = 2)
	public void enterDetails() {
		ExtentTest test = extent.createTest("enterDetails ", "Bavarian inn");
		System.out.println("Login to site");
		driver.findElement(By.xpath("//*[contains(text(), 'Register')]")).click();
		String titleRego = driver.getTitle();
		Assert.assertEquals(titleRego, "Register");
		test.pass("Passed");
	}

	@Test(priority = 3, dataProvider = "test1data")
	public void test1(String firstName, String lastName, String emailAddress, String passWord1, String passWord2,
			String dateOfBirth, String mobilePhone, String homePhone, String country, String provence,
			String addressLine1, String addressLine2, String city, String zipCode, String reflocation)
			throws InterruptedException {
		ExtentTest test = extent.createTest("Reading from Excel", "Register user");

		String title = driver.getTitle();
		Assert.assertEquals(title, "Register");

		// ------------------

		RegisterPage.selectCountry(driver);
		// Select select = new
		// Select(driver.findElement(By.xpath("//*[@name='Country']")));
		// select.selectByVisibleText("Canada");
		Thread.sleep(2000);

		// FirstName
		RegisterPage.enterFirstName(driver).sendKeys(firstName);
		// driver.findElement(By.xpath("//*[@name='FirstName']")).sendKeys(firstName);

		RegisterPage.enterLastName(driver).sendKeys(lastName);
		// driver.findElement(By.xpath("//*[@placeholder='Last name
		// *']")).sendKeys(lastName);
		// LastName
		// RegisterPage.enterFirstName(driver).click();
		// RegisterPage.enterFirstName(driver).sendKeys(lastName);

		// Email
		// driver.findElement(By.xpath("//*[@placeholder='Email
		// *']")).sendKeys(emailAddress);
		RegisterPage.enterEmail(driver).sendKeys(emailAddress);

		// Password1
		// driver.findElement(By.xpath("//*[@placeholder='Password
		// *']")).sendKeys("Password1@");
		RegisterPage.enterPassword1(driver).sendKeys("Password1@");

		// Password2
		// driver.findElement(By.xpath("//*[@placeholder='Confirm password
		// *']")).sendKeys("Password1@");
		RegisterPage.enterPassword2(driver).sendKeys("Password1@");

		// Date of Birth
		// driver.findElement(By.xpath("//*[@name='DateOfBirth']")).sendKeys("01/01/2000");
		RegisterPage.dateOfBirth(driver).sendKeys(dateOfBirth);

		// Mobile number
		driver.findElement(By.xpath("//*[@placeholder='Mobile phone *']")).sendKeys(mobilePhone);

		// Ligne automatisée, tél. : 418 641‑6500
		// Phone number
		driver.findElement(By.xpath("//*[@name='Phone']")).sendKeys(homePhone);
		Thread.sleep(2000);

		RegisterPage.selectCountry2(driver);

		// Select country
		RegisterPage.selectState(driver);

		// Address 1
		RegisterPage.address1(driver).sendKeys(addressLine1);
		// driver.findElement(By.xpath("//*[@name='AddressLine1']")).sendKeys(addressLine1);

		// Address 2
		RegisterPage.address2(driver).sendKeys(addressLine2);
		// driver.findElement(By.xpath("//*[@name='AddressLine2']")).sendKeys(addressLine2);

		// City
		RegisterPage.city(driver).sendKeys(reflocation);
		// driver.findElement(By.xpath("//*[@name='City']")).sendKeys("Vancouver");

		// Québec (Québec) G1E 4T9
		// Zip
		// RegisterPage.zipCode(driver).sendKeys(postCode);
		RegisterPage.zipCode(driver).sendKeys("ZZ123"); // Canadian real postcode

		System.out.println("Post code is " + zipCode);
		// driver.findElement(By.xpath("//*[@name='Zip']")).sendKeys("ZZ123");

		// Need club card - Collect at the Inn
		RegisterPage.sendPerksCard(driver);
		// Select cardOption = new
		// Select(driver.findElement(By.xpath("//*[@name='RequestPerksCard']")));
		// cardOption.selectByIndex(2);

		RegisterPage.referralLocation(driver).sendKeys(reflocation);
		// driver.findElement(By.xpath("//*[@name='ReferralInfo']")).sendKeys(referralLocation);

		// Agree = yes
		RegisterPage.iAgree(driver).click();
		// driver.findElement(By.xpath("//*[@class='control__indicator']")).click();

		// Select Register
		RegisterPage.registerYes(driver).click();
		// driver.findElement(By.xpath("//*[@type='submit']")).click();

		// To do
		test.fail("This part needs to be fixed before the test completed assertion can be completed");

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

	@AfterTest // 12
	public void afterTest() {
		System.out.println("After Test bit");
		System.out.println("Close browser");
		driver.close();
		driver.quit();

	}

	@AfterSuite // Exent 5
	public void tearDown() {
		extent.flush();

	}

} // End class
