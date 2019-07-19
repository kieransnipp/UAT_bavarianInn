//Demo this one

package testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;

//import org.testng.AssertJUnit;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//import org.junit.Test;  
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;

public class UAT_BavarianInnLoginCheckScreens {

	// Exent 1
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent = new ExtentReports();
	ExtentTest test;
	Exception exception = null;

	public static Properties prop;

	private static WebDriver driver = null;

	public UAT_BavarianInnLoginCheckScreens() {
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
		// Manage Window and cookies
		System.out.println("Maximize window, delete cookies");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		System.out.println("Before url");
		driver.get(prop.getProperty("url"));
		System.out.println("After url");

		WebDriverRunner.setWebDriver(driver);

	}

	@BeforeTest
	public void setUp() {// Exent 2

		htmlReporter = new ExtentHtmlReporter("UAT_BavarianInnLoginCheckScreens.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	@BeforeMethod // 4
	public void beforeMethod() {
		System.out.println("@Before method bit");

	}

	// test cases, starting with @Test
	@Test(priority = 1) // 5
	public void loginToSite() {
		ExtentTest test = extent.createTest("Screen Checks - loginToSite", "Screen links Checks");

		test.log(Status.INFO, "This step shows the usage of log(status, details)");
		System.out.println("Verify the title of the login page");
		String pageTitle = driver.getTitle();
		System.out.println("Title of the page is " + pageTitle);

		String title = driver.getTitle();

		Assert.assertEquals(title, "Home");
		test.pass("Passed - Home page displayed");
	}

	@Test(priority = 2) // 6
	public void enterDetails() throws InterruptedException {
		ExtentTest test = extent.createTest("enterDetails", "Login in");
		System.out.println("Login to site");
		$(By.xpath("//*[@id=\"loginContainer\"]/*/*/input")).setValue("Michael.Ledwith@snipp.com");
		$(By.xpath("//*[@id=\"loginContainer\"]/form/div[2]/input")).setValue("Snipp123!");
		$(By.name("loginButton")).click();
		String title = driver.getTitle();
		System.out.println("Title is " + title);
		Thread.sleep(2000);

		test.log(Status.INFO, "Passed before");

		Assert.assertEquals(title, "Home");
		test.pass("Passed login after assert");
	}

	@Test(priority = 3) // 7
	public void loginTitleTest() throws InterruptedException {
		ExtentTest test = extent.createTest("loginTitleTest", "Title displayed");
		System.out.println("");
		System.out.println("**************** Start of testing ****************");

		String title = driver.getTitle();
		System.out.println("Title displayed is " + title);
		Thread.sleep(3000);

		Assert.assertEquals(title, "Details");

		System.out.println("Title is correct and this line is printed");
		test.pass("Login title displayed");
	}

	@Test(priority = 4)
	public void WelcomeMessage() {
		ExtentTest test = extent.createTest("WelcomeMessage", "Welcome displayed");
		System.out.println("Check welcome message");
		String Hi = driver.findElement(By.xpath("//*[contains(text(), 'Michael')]")).toString();
		System.out.println(Hi + " is displayed");

		Assert.assertTrue(Hi.contains("Michael"));
		System.out.println("Welcome message is displayed correctly");
		test.pass("Welcome displayed");
	}

	@Test(priority = 8)
	public void membersOffers() {
		ExtentTest test = extent.createTest("membersOffers", "Check Offer links");

		driver.findElement(By.xpath("//*[contains(text(), 'Member Offers')]")).click();
		String title = driver.getTitle();
		test.pass("Offer lnks correct");
		Assert.assertEquals(title, "Offers");

	}

	@Test(priority = 9)
	public void membersLocations() {
		ExtentTest test = extent.createTest("membersLocations", "Members locations");
		// driver.findElement(By.xpath("//*[contains(text(), 'Locations' and
		// @class-'main-navigation-list']")).click();
		driver.findElement(By.xpath("//*[contains(text(), 'Locations')]")).click();
		String title = driver.getTitle();
		test.pass("Members locations displayed");
		Assert.assertEquals(title, "Stores");

	}

	@Test(priority = 10)
	public void membersSocial() {
		ExtentTest test = extent.createTest("membersSocial", "Members social");
		driver.findElement(By.xpath("//*[contains(text(), 'Social')]")).click();
		String title = driver.getTitle();
		test.pass("Logout is correct");
		Assert.assertEquals(title, "Earn Points");
	}

	@Test(priority = 11)
	public void logOutOfSite() throws InterruptedException {
		ExtentTest test = extent.createTest("logOutOfSite", "Log out of site");

		// Force logout Log out
		open("http://bavarianinn.snipp.ie/umbraco/Surface/LoginSurface/Logout");
		test.pass("Logout is correct");
	}

	// Post conditions starting with @After
	@AfterMethod // 10
	public void afterMethod() {
		 extent.flush();
		System.out.println("After Method bit");
	}

	@AfterTest // 12
	public void afterTest() {
		System.out.println("After Test bit");
		System.out.println("Close browser");
		driver.close();
		driver.quit();

	}

	@AfterSuite // 13
	public void generateTestNG() {
		System.out.println("Generate report");
	}

	// -------------------------------------

} // End LoginTestAssertions
