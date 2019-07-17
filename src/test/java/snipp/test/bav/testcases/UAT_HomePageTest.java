//Demo this one

package snipp.test.bav.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
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

public class UAT_HomePageTest {
	public static Properties prop;

	private static WebDriver driver = null;
	// Exent 1
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent = new ExtentReports();
	ExtentTest test;
	Exception exception = null;

	public UAT_HomePageTest() {
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

		htmlReporter = new ExtentHtmlReporter("UAT_HomePageTest.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	@BeforeMethod // 4
	public void beforeMethod() {
		System.out.println("@Before method bit");

	}

	// test cases, starting with @Test
	@Test(priority = 1)
	public void loginToSite() {
		ExtentTest test = extent.createTest("loginToSite", "Home page test");
		System.out.println("Verify the title of the login page");
		String pageTitle = driver.getTitle();
		System.out.println("Title of the page is " + pageTitle);
		AssertJUnit.assertEquals(driver.getTitle().contentEquals("Home"), true);
		test.pass("Passed");
	}

	 @Test(priority = 2)
	 public void howItWorks() {
	 ExtentTest test = extent.createTest("howItWorks", "Home assert test");
	 HomePage.howItWorks(driver).click();
	 String title = driver.getTitle();
	 AssertJUnit.assertEquals(title, "Home");
	 test.pass("Passed");
	 }

	@Test(priority = 3)
	public void register() {
		ExtentTest test = extent.createTest("register", "register assert test");
		HomePage.selectRegister(driver).click();
		String titleR = driver.getTitle();
		AssertJUnit.assertEquals(titleR, "Register");

		driver.navigate().back();
		String title = driver.getTitle();
		AssertJUnit.assertEquals(title, "Home");

		test.pass("Passed");

	}

	@Test(priority = 4)
	public void faq() {
		ExtentTest test = extent.createTest("FAQ", "Homepage assert test");
		HomePage.faq(driver).click();
		String title = driver.getTitle();
		AssertJUnit.assertEquals(title, "FAQ");
		driver.navigate().back();
		test.pass("Passed");
	}

	@Test(priority = 5)
	public void checkFacebook() {
		ExtentTest test = extent.createTest("checkFacebook", "Facebook assert test");
		System.out.println("Checking Facebook");
		// Facebook
		driver.findElement(By.xpath("//a[contains(text(),'Face')]")).click();

		String title = driver.getTitle();
		AssertJUnit.assertEquals(title, "Frankenmuth Bavarian Inn Lodge - Home | Facebook");
		test.pass("Passed");
		driver.navigate().back();
	}

	@Test(priority = 6)
	public void checkYoutube() {
		ExtentTest test = extent.createTest("checkYoutube", "Youtube assert test");
		System.out.println("Checking Youtube");
		driver.findElement(By.xpath("//a[contains(text(),'Youtube')]")).click();

		String title = driver.getTitle();
		AssertJUnit.assertEquals(title, "Bavarian Inn Lodge - YouTube");
		test.pass("Passed");
		driver.navigate().back();
	}

	@Test(priority = 7)
	public void checkYelp() {
		ExtentTest test = extent.createTest("checkYelp", "Youtube assert test");
		System.out.println("Checking Trip Advisor");
		driver.findElement(By.xpath("//a[contains(text(),'Trip Advisor')]")).click();

		String title = driver.getTitle();
		AssertJUnit.assertEquals(title, "Bavarian Inn Lodge - Frankenmuth - TripAdvisor");
		test.pass("Passed");
		driver.navigate().back();
	}

	@Test(priority = 8)
	public void checkTwitter() throws IOException {
		ExtentTest test = extent.createTest("checkTwitter", "Twitter assert test");
		System.out.println("Checking Twitter");
		// checkTwitter
		driver.findElement(By.xpath("//a[contains(text(),'Twitter')]")).click();

		String title = driver.getTitle();
		AssertJUnit.assertEquals(title, "Bavarian Inn (@Bavarian_Inn) | Twitter");
	
		driver.navigate().back();

		String homeTitle = driver.getTitle();
		AssertJUnit.assertEquals(homeTitle, "Home");
		test.pass("Passed");
	}

	@AfterTest
	public void tearDownTest() {
		//driver.close();
		driver.quit();
		System.out.println("Test completed successfully");
	}


	@AfterSuite // Exent 5
	public void tearDown() {

		//extent.flush();

	}

} // End UATBavarianInnCreateUser
