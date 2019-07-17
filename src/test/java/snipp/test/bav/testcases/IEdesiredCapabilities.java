package snipp.test.bav.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.codeborne.selenide.WebDriverRunner;

public class IEdesiredCapabilities {

	// Exent 1
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent = new ExtentReports();
	ExtentTest test;

	public static WebDriver driver;
	public static Properties prop;
	Exception exception = null;
	
	DesiredCapabilities capabilities = null;
	


	String homePage = null; // "http://bavarianinn.snipp.ie/home/"; // Changed
	String url = "";
	HttpURLConnection huc = null;
	int respCode = 200;

	public IEdesiredCapabilities() {
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
		@SuppressWarnings("deprecation")
		@BeforeSuite // 1
		public void launchBrowser() {
			 capabilities = DesiredCapabilities.internetExplorer();
//			capabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
//			capabilities.setCapability(InternetExplorerDriver.
//			  INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
//			
			
			capabilities.setCapability("ignoreProtectedModeSettings", true);
			capabilities.setCapability("initialBrowserUrl", "www.google.com");
			capabilities.setCapability("enablePersistentHover", true);
			capabilities.setCapability("enableElementCacheCleanup", true);
			capabilities.setCapability("requireWindowFocus", true);
			capabilities.setCapability("ie.ensureCleanSession", true);
			capabilities.setCapability("logFile", "drivers/Log.txt");
			
			//
			capabilities.setCapability("extractPath", "drivers");
			capabilities.setCapability("silent", true);
			capabilities.setCapability("ie.setProxyByServer", true);
			
			
			
			// Read from the properties file
			String browserName = prop.getProperty("browser");
			String url = prop.getProperty("url");

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
				//driver = new InternetExplorerDriver(capabilities);			

			} else if (browserName.equals("opera")) {
				System.out.println("Launching opera");
				System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
				driver = new OperaDriver();

			}
			System.out.println("Maximize window, delete cookies");
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();

			System.out.println("Before url");
			driver.get(url);
			
			System.out.println("After url");

			WebDriverRunner.setWebDriver(driver);

			// Manage Window and cookies

		}

	


		@BeforeClass
		public void login() {
			// driver.get(homePage);

			System.out.println("Verify the title of the login page");
			String pageTitle = driver.getTitle();
			System.out.println("Title of the page is " + pageTitle);

			System.out.println("@Before method bit");
			System.out.println("Login to site");
			driver.findElement(By.xpath("//*[@id=\"loginContainer\"]/*/*/input")).sendKeys("Michael.Ledwith@snipp.com");
			driver.findElement(By.xpath("//*[@id=\"loginContainer\"]/form/div[2]/input")).sendKeys(Keys.ENTER);
			
			//$(By.xpath("//*[@id=\"loginContainer\"]/*/*/input")).setValue("Michael.Ledwith@snipp.com");
			//$(By.xpath("//*[@id=\"loginContainer\"]/form/div[2]/input")).setValue("Snipp123!");
			
			$(By.name("loginButton")).click();
			String LoginpageTitle = driver.getTitle();
			AssertJUnit.assertEquals(driver.getTitle().contentEquals(LoginpageTitle), true);
			
			htmlReporter = new ExtentHtmlReporter("UAT_BavarianInnBrokenLinks.html");
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
		}

//		@BeforeClass
//		public void setUp() {// Exent 2
	//
//			htmlReporter = new ExtentHtmlReporter("UAT_BavarianInnBrokenLinks.html");
//			extent = new ExtentReports();
//			extent.attachReporter(htmlReporter);
//		}

		@BeforeTest
		public void beforeTest() {
			System.out.println("This is the before test bit");
		}

		@Test(priority = 1) // 7
		public void titleTest() throws InterruptedException {
			ExtentTest test = extent.createTest("Check links - Title test", "Check links");
			System.out.println("");
			System.out.println("**************** Start of testing ****************");

			String loginTitle = driver.getTitle();
			System.out.println("Title displayed is " + loginTitle);
			Thread.sleep(3000);

			
			// Assert.assertEquals(pageTitle, "MemberSearch");
			System.out.println(" ***  Check links for Page =" + loginTitle);

			
			//test.pass("Check links - Passed");
		}

}
