//Demo this one

package snipp.test.bav.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertTrue;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.codeborne.selenide.WebDriverRunner;

public class UAT_BavarianInnBrokenLinks {

	// Exent 1
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent = new ExtentReports();
	ExtentTest test;

	public static WebDriver driver;
	public static Properties prop;
	Exception exception = null;

	// homePage = prop.getProperty("url");

	String homePage = null;  //"http://bavarianinn.snipp.ie/home/"; // Changed

	String url = "";
	HttpURLConnection huc = null;
	int respCode = 200;

	public UAT_BavarianInnBrokenLinks() {
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
		$(By.xpath("//*[@id=\"loginContainer\"]/*/*/input")).setValue("Michael.Ledwith@snipp.com");
		$(By.xpath("//*[@id=\"loginContainer\"]/form/div[2]/input")).setValue("Snipp123!");
		$(By.name("loginButton")).click();
		String LoginpageTitle = driver.getTitle();
		AssertJUnit.assertEquals(driver.getTitle().contentEquals(LoginpageTitle), true);
		
		htmlReporter = new ExtentHtmlReporter("UAT_BavarianInnBrokenLinks.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

//	@BeforeClass
//	public void setUp() {// Exent 2
//
//		htmlReporter = new ExtentHtmlReporter("UAT_BavarianInnBrokenLinks.html");
//		extent = new ExtentReports();
//		extent.attachReporter(htmlReporter);
//	}

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

		AssertJUnit.assertEquals(driver.getTitle().contentEquals("Details"), true);
		// Assert.assertEquals(pageTitle, "MemberSearch");
		System.out.println(" ***  Check links for Page =" + loginTitle);

		Boolean checkedOk = checkLinks();
		assertTrue(checkedOk);
		test.pass("Check links - Passed");
	}

	// test cases, starting with @Test
	@Test(priority = 2) // 5
	public void loginToSite() {
		ExtentTest test = extent.createTest("loginToSite", "Check links");
		System.out.println("Verify the title of the login page");
		String pageTitle = driver.getTitle();
		System.out.println("Title of the page is " + pageTitle);
		System.out.println(" ***  Check links for Page =" + pageTitle);

		Boolean checkedOk = checkLinks();
		assertTrue(checkedOk);
		test.pass("Check links - Passed");
	}

	@Test(priority = 4)
	public void WelcomeMessage() {
		ExtentTest test = extent.createTest("Welcome Message", "Check links");
		String pageTitle = driver.getTitle();
		System.out.println("Check welcome message");
		String Hi = driver.findElement(By.xpath("//*[contains(text(), 'Hi Michael')]")).toString();
		System.out.println(Hi + " is displayed");

		AssertJUnit.assertTrue(Hi.contains("Hi Michael"));
		System.out.println("Welcome message is displayed correctly");
		System.out.println(" ***  Check links for Page =" + pageTitle);

		Boolean checkedOk = checkLinks();
		assertTrue(checkedOk);
		test.pass("Check links - Passed");
	}

	// @Test(priority = 5)
	// public void uploadReciept() {
	// System.out.println("Receipt Up load page");
	// driver.findElement(By.xpath("//*[contains(text(), 'Upload
	// Receipt')]")).click();
	// String title = driver.getTitle();
	// Assert.assertEquals(title, "Upload Receipt");
	// }
	//
	// @Test(priority = 6)
	// public void perksClubCardSorryCheck() {
	// System.out.println("Perks Club Card Sorry Check");
	//
	// String sorry = driver.findElement(By.xpath("//*[contains(text(),
	// 'Sorry!')]")).toString();
	// assertTrue(sorry.contains("Sorry!"));
	// // Assert.assertEquals(sorry, "Sorry!");
	// }
	//
	// @Test(priority = 7)
	// public void profileDisplayed() {
	// driver.findElement(By.xpath("//*[contains(text(), 'My Profile') and @class
	// ='alert-link']")).click();
	// System.out.println("Profile displayed");
	// String title = driver.getTitle();
	// Assert.assertEquals(title, "Profile");
	//
	// }

	@Test(priority = 8)
	public void membersOffers() {
		ExtentTest test = extent.createTest("membersOffers", "Check links");
		driver.findElement(By.xpath("//*[contains(text(), 'Member Offers')]")).click();
		String title = driver.getTitle();
		AssertJUnit.assertEquals(title, "Offers");
		System.out.println(" ***  Check links for Page =" + title);

		Boolean checkedOk = checkLinks();
		assertTrue(checkedOk);
		test.pass("Check links - Passed");
	}

	@Test(priority = 9)
	public void membersLocations() {
		ExtentTest test = extent.createTest("membersLocations", "Check links");
		driver.findElement(By.xpath("//*[contains(text(), 'Locations')]")).click();
		String title = driver.getTitle();
		AssertJUnit.assertEquals(title, "Stores");
		System.out.println(" ***  Check links for Page =" + title);

		Boolean checkedOk = checkLinks();
		assertTrue(checkedOk);
		test.pass("Check links - Passed");
	}

	@Test(priority = 10)
	public void membersSocial() {
		ExtentTest test = extent.createTest("membersSocial", "Check links");
		driver.findElement(By.xpath("//*[contains(text(), 'Social')]")).click();
		String title = driver.getTitle();
		AssertJUnit.assertEquals(title, "Earn Points");
		System.out.println(" ***  Check links for Page =" + title);
		Boolean checkedOk = checkLinks();
		assertTrue(checkedOk);
		test.pass("Check links - Passed");
	}

	@Test(priority = 11)
	public void logOutOfSite() throws InterruptedException {

		// Force logout Log out
		open("http://bavarianinn.snipp.ie/umbraco/Surface/LoginSurface/Logout");

		// String title = driver.getTitle();
		// Assert.assertEquals(title, "Home");
		// System.out.println("Assert works ");
		Thread.sleep(3000);

		// driver.findElement(By.cssSelector("dropdown-toggle")).click();
		// driver.findElement(By.cssSelector("dropdown-menu > li:nth-child(4)
		// >a")).click();

	}

	public boolean checkLinks() {
		// driver = new ChromeDriver();

		driver.manage().window().maximize();
		// driver.get(homePage);
		homePage = prop.getProperty("url");  //Updated
		List<WebElement> links = driver.findElements(By.tagName("a"));
		Iterator<WebElement> it = links.iterator();
		while (it.hasNext()) {

			url = it.next().getAttribute("href");

			System.out.println(url);

			if (url == null || url.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}

			if (!url.startsWith(homePage)) {
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}

			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());

				huc.setRequestMethod("HEAD");

				huc.connect();

				respCode = huc.getResponseCode();

				if (respCode >= 400) {
					System.out.println(url + " is a broken link");
					return false;
				} else {
					System.out.println(url + " is a valid link");

				}

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // End while

		System.out.println(" ***********  End of link checks for this page ***************** ");

		return true;
	}

	@AfterMethod
	public void afterTest() {
		System.out.println("This is the after method bit");
	}

//	@AfterTest
//	public void tearDownTest() {
//		
//	}

	@AfterSuite // Exent 5
	public void tearDown() {
		driver.close();
		driver.quit();
		System.out.println("Test completed successfully");
		//extent.flush();

	}
}
