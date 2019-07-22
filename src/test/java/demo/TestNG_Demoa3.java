//Accesing the data VIA a static way
package demo;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.codeborne.selenide.WebDriverRunner;

public class TestNG_Demoa3 {

	WebDriver driver = null;
	static private Logger logger = LogManager.getLogger(TestNG_Demoa3.class);
	public static String browserName = null;

	@BeforeTest
	public void setUpTest() {
		System.out.println("Forth test running");
		String projectPath = System.getProperty("user.dir");
		PropertiesFile.getProperties();  //Sets the browserName for this class
		
		if (PropertiesFile.browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			if (PropertiesFile.browser.equalsIgnoreCase("firefox")) {
				System.out.println("Launching firefox");
				// browserName = ((Object) driver).Firefox("drivers/geckodriver.exe");
				System.setProperty("webdriver.gecko.driver", projectPath + "/drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			}
			WebDriverRunner.setWebDriver(driver);
		}

	} // End setUpTest

	
	@Test 
	public void googleSearch() {
		System.out.println("Get google");
		driver.get("https://www.google.com/");
		System.out.println("After google");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String title = driver.getTitle();
		
		AssertJUnit.assertEquals("Google", title, "Google");
		System.out.println("TestNG_Demo3 Test Passed");
//		driver.findElement(By.name("q")).sendKeys("Automation steps");
//		driver.findElement(By.name("btnK")).click();
	}
	
//	@Test
//	public void firstTest() {
//		System.out.println("Do you see this?");
//	}
	
//	@AfterMethod
//	public void afterMethod() {
//		System.out.println("Before method");
//	}
	
	@AfterSuite
	public void tearDown() {
		driver.close();
		driver.quit();
		
	}
}
