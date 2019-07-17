package snipp.test.bav.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserTest {
	
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");	
		
		System.out.println("Before url");
		
		driver.get("http://google.com");
	}

}
