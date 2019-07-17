package sandpit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static com.codeborne.selenide.Selenide.$;

import static com.codeborne.selenide.Selenide.open;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.codeborne.selenide.WebDriverRunner;

import demo.PropertiesFile;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.company.Company;
import io.codearte.jfairy.producer.person.Address;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;
import com.bav.config.*;

public class XX_OLD_UATBavarianInnCreateUser {

	// first create Fairy object. By default - Locale is English
	Fairy fairy = Fairy.create();
	Fairy plFairy = Fairy.create(Locale.forLanguageTag("pl"));

	// Create person object
	Person person = fairy.person();

	// Extend
	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("OLD_UATBavarianInnCreateUser.html");
	ExtentReports extent = new ExtentReports();
	ExtentTest test = extent.createTest("Bavarian Inn Rego", "Registraton of Canadian user");

	public static Properties prop;

	private static WebDriver driver = null;

	public XX_OLD_UATBavarianInnCreateUser() {
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
		test.pass("Constructor completed");
	}

	// Pre conditions @Before
	@BeforeSuite // 1
	public void launchBrowser() {

		String url = PropertiesFile.url;
		System.out.println("The properties URL is "+url);
		
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

		System.out.println("Before url");
		driver.get(prop.getProperty("url"));
	
		System.out.println("After url");

		WebDriverRunner.setWebDriver(driver);
		test.pass("Browser launched completed");

		// Manage Window and cookies

	}

	@BeforeMethod // 4
	public void beforeMethod() throws IOException {
		System.out.println("Before bit");

	}

	// test cases, starting with @Test
	@Test(priority = 1)
	public void loginToSite() throws IOException {

		extent.attachReporter(htmlReporter);
		// log(status, details)
		test.log(Status.INFO, "This step shows the usage of log(status, details)");

		// info()details
		test.info("This step shows the usage of info()details");

		System.out.println("Verify the title of the login page");
		String pageTitle = driver.getTitle();
		System.out.println("Title of the page is " + pageTitle);
		AssertJUnit.assertEquals(driver.getTitle().contentEquals("Home"), true);
		test.addScreenCaptureFromPath("screenshot.png", "Title");
		test.pass("loginToSite passed");

		

	}

	@Test(priority = 2)
	public void enterDetails() {
		System.out.println("Login to site");

		driver.findElement(By.xpath("//*[contains(text(), 'Register')]")).click();
		String titleRego = driver.getTitle();
		AssertJUnit.assertEquals(titleRego, "Register");
		test.pass("Registration page is displayed");
	}

	@Test(dependsOnMethods = "enterDetails")
	public void enterForm() throws InterruptedException {

		String title = driver.getTitle();
		AssertJUnit.assertEquals(title, "Register");

		// ------------------
		// Generate random data
		Fairy fairy = Fairy.create();
		Person person = fairy.person();
		String firstName = person.getFirstName();
		String lastName = person.getLastName();
		String emailAddress = person.getEmail();

		Address address = person.getAddress();
		String addressLine1 = address.getAddressLine1();
		String addressLine2 = address.getAddressLine2();
		Person adultMale = fairy.person(PersonProperties.male(), PersonProperties.minAge(21));
		String referralLocation = address.getCity();

		// ------ End random data
		// -------------------------------------------

		// Select country
		Select select = new Select(driver.findElement(By.xpath("//*[@name='Country']")));
		select.selectByVisibleText("Canada");
		Thread.sleep(2000);
		test.pass("Canada selected passed");

		// FirstName
		driver.findElement(By.xpath("//*[@name='FirstName']")).sendKeys(firstName);
		// LastName
		driver.findElement(By.xpath("//*[@placeholder='Last name *']")).sendKeys(lastName);
		// Email
		driver.findElement(By.xpath("//*[@placeholder='Email *']")).sendKeys(emailAddress);

		// Password1
		driver.findElement(By.xpath("//*[@placeholder='Password *']")).sendKeys("Password1@");
		// Password2
		driver.findElement(By.xpath("//*[@placeholder='Confirm password *']")).sendKeys("Password1@");

		// Date of Birth
		driver.findElement(By.xpath("//*[@name='DateOfBirth']")).sendKeys("01/01/2000");

		test.pass("DOB enetered passed");
		// Mobile number
		driver.findElement(By.xpath("//*[@placeholder='Mobile phone *']")).sendKeys("01214545");

		// Ligne automatisée, tél. : 418 641‑6500
		// Phone number
		driver.findElement(By.xpath("//*[@name='Phone']")).sendKeys("4186416500");

		// Select State
		Select state = new Select(driver.findElement(By.xpath("//*[@id='States']")));
		select.selectByVisibleText("United States");
		Thread.sleep(1000);
		select.selectByVisibleText("Canada");

		// state.selectByIndex(1);
		state.selectByVisibleText("Yukon");

		// Address 1
		driver.findElement(By.xpath("//*[@name='AddressLine1']")).sendKeys(addressLine1);

		// Address 2
		driver.findElement(By.xpath("//*[@name='AddressLine2']")).sendKeys(addressLine2);

		// City
		driver.findElement(By.xpath("//*[@name='City']")).sendKeys("Vancouver");

		// Québec (Québec) G1E 4T9
		// Zip
		driver.findElement(By.xpath("//*[@name='Zip']")).sendKeys("ZZ123");

		// Need club card - Collect at the Inn
		Select cardOption = new Select(driver.findElement(By.xpath("//*[@name='RequestPerksCard']")));
		cardOption.selectByIndex(2);

		driver.findElement(By.xpath("//*[@name='ReferralInfo']")).sendKeys(referralLocation);

		// Agree = yes
		driver.findElement(By.xpath("//*[@class='control__indicator']")).click();

		// Select Register
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		test.pass("All the users details entered");

	}
	
	@AfterClass
	public void createReport() {
		test.info("Create the report");
		extent.flush(); // Creates the HTML
	}

} // End UATBavarianInnCreateUser
