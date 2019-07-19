//This test uses the page object model and creates random Canadian Localised data for the 

//UAT Bavarian Registration form

package testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaDriverService;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.codeborne.selenide.WebDriverRunner;

import ch.qos.logback.core.net.SyslogOutputStream;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.company.Company;
import io.codearte.jfairy.producer.person.Address;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;

import com.bav.pages.HomePage;
import com.bav.pages.RegisterPage;

public class UAT_RegisterPageTestCanada {
	public static Properties prop;
	private static WebDriver driver = null;

	// Exent 1
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent = new ExtentReports();
	ExtentTest test;
	Exception exception = null;

	// first create Fairy object. By default - Locale is English
	Fairy fairy = Fairy.create();
	Fairy canadaFairy = Fairy.create(Locale.CANADA);

	// Create person object
	Person person = canadaFairy.person();

	// Generate random data
	String firstName = person.getFirstName();
	String lastName = person.getLastName();
	String emailAddress = person.getEmail();

	Address address = person.getAddress();
	String addressLine1 = address.getAddressLine1();
	String addressLine2 = address.getAddressLine2();
	Person adultMale = canadaFairy.person(PersonProperties.male(), PersonProperties.minAge(21));

	String referralLocation = address.getCity();
	String postCode = address.getPostalCode();
	DateTime dateOfBirth = adultMale.getDateOfBirth();

	public UAT_RegisterPageTestCanada() {

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
	public void launchBrowser() throws IOException {

		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			System.out.println("Launching firefox");

			System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browserName.equals("ie")) {
			System.out.println("Launching Internet Explorer");
			System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");

			driver = new InternetExplorerDriver();

		} else if (browserName.equals("opera")) {

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
		htmlReporter = new ExtentHtmlReporter("UAT_RegisterPageTestCanada.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

//	@BeforeMethod // 4
//	public void beforeMethod() {
//		System.out.println("@Before method bit");
//
//	}

	// test cases, starting with @Test
	@Test(priority = 1)
	public void loginToSite() {
		ExtentTest test = extent.createTest("loginToSite", "Registraton of Canadian user");
		System.out.println("Verify the title of the login page");
		String title = driver.getTitle();
		System.out.println("Title of the page is " + title);

		Assert.assertEquals(title, "Details");

		test.pass("Passed");
	}

	@Test(priority = 2)
	public void enterDetails() {
		ExtentTest test = extent.createTest("enterDetails", "Registraton of Canadian user");
		System.out.println("Login to site");
		HomePage.selectRegister(driver).click();

		// driver.findElement(By.xpath("//*[contains(text(), 'Register')]")).click();
		String titleRego = driver.getTitle();
		Assert.assertEquals(titleRego, "Register");
		test.pass("Passed");
	}

	@Test(dependsOnMethods = "enterDetails")
	public void enterForm() {
		ExtentTest test = extent.createTest("enterForm", "Registraton of Canadian user");

		String title = driver.getTitle();
		Assert.assertEquals(title, "Register");

		// ------------------

		RegisterPage.selectCountry(driver);
		// Select select = new
		// Select(driver.findElement(By.xpath("//*[@name='Country']")));
		// select.selectByVisibleText("Canada");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		// RegisterPage.dateOfBirth(driver).sendKeys(dateOfBirth);
		int dateOfMonth = dateOfBirth.getDayOfMonth();
		int month = dateOfBirth.getMonthOfYear();
		int year = dateOfBirth.getYear();

		String dateOfMonthS = String.valueOf(dateOfMonth);
		String monthS = String.valueOf(month);
		String yearS = String.valueOf(year);

		String randomDate = dateOfMonthS + "/" + monthS + "/" + yearS;
		RegisterPage.dateOfBirth(driver).sendKeys(randomDate);

		// Mobile number
		driver.findElement(By.xpath("//*[@placeholder='Mobile phone *']")).sendKeys("01214545");

		// Ligne automatisée, tél. : 418 641‑6500
		// Phone number
		driver.findElement(By.xpath("//*[@name='Phone']")).sendKeys("4186416500");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			RegisterPage.selectCountry2(driver);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Select country
		RegisterPage.selectState(driver);

		// Address 1
		RegisterPage.address1(driver).sendKeys(addressLine1);
		// driver.findElement(By.xpath("//*[@name='AddressLine1']")).sendKeys(addressLine1);

		// Address 2
		RegisterPage.address2(driver).sendKeys(addressLine2);
		// driver.findElement(By.xpath("//*[@name='AddressLine2']")).sendKeys(addressLine2);

		// City
		RegisterPage.city(driver).sendKeys(referralLocation);
		// driver.findElement(By.xpath("//*[@name='City']")).sendKeys("Vancouver");

		// Québec (Québec) G1E 4T9
		// Zip
		// RegisterPage.zipCode(driver).sendKeys(postCode);
		RegisterPage.zipCode(driver).sendKeys("ZZ123");

		System.out.println("Post code is " + postCode);
		// driver.findElement(By.xpath("//*[@name='Zip']")).sendKeys("ZZ123");

		// Need club card - Collect at the Inn
		RegisterPage.sendPerksCard(driver);
		// Select cardOption = new
		// Select(driver.findElement(By.xpath("//*[@name='RequestPerksCard']")));
		// cardOption.selectByIndex(2);

		RegisterPage.referralLocation(driver).sendKeys(referralLocation);
		// driver.findElement(By.xpath("//*[@name='ReferralInfo']")).sendKeys(referralLocation);

		// Agree = yes
		RegisterPage.iAgree(driver).click();
		// driver.findElement(By.xpath("//*[@class='control__indicator']")).click();

		// Select Register
		RegisterPage.registerYes(driver).click();
		// driver.findElement(By.xpath("//*[@type='submit']")).click();
		test.log(Status.INFO, "Rego completed");

		// To do
		test.fail("This part needs to be fixed before the test completed assertion can be completed");
	}

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

} // End UATBavarianInnCreateUser
