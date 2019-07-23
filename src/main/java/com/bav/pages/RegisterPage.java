package com.bav.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class RegisterPage extends TestBase{

	WebDriver driver;
	private static WebElement element = null;

	public static WebElement enterFirstName(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@name='FirstName']"));
		return element;
	}

	public static WebElement enterLastName(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@placeholder='Last name *']"));
		return element;
	}

	public static WebElement enterEmail(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@placeholder='Email *']"));
		return element;
	}

	public static WebElement enterPassword1(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@placeholder='Password *']"));
		//driver.findElement(By.xpath("//*[@placeholder='Password *']")).sendKeys("Password1@");
		return element;
	}

	public static WebElement enterPassword2(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@placeholder='Confirm password *']"));
		return element;
	}

	public static WebElement dateOfBirth(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@name='DateOfBirth']"));
		return element;
	}

	public static WebElement mobilePhone(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@placeholder='Mobile phone *']"));
		return element;
	}

	public static WebElement homePhone(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@name='Phone']"));
		return element;
	}

	public static WebElement referralLocation(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@name='ReferralInfo']"));
		return element;
	}

	

	// Address 1
	public static WebElement address1(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@name='AddressLine1']"));
		return element;
	}

	// Address 2
	public static WebElement address2(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@name='AddressLine2']"));
		return element;
	}

	// City
	public static WebElement city(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@name='City']"));
		return element;
	}

	// Québec (Québec) G1E 4T9
	// Zip
	public static WebElement iAgree(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@class='control__indicator']"));
		return element;
	}

	public static WebElement zipCode(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@name='Zip']"));
		return element;
	}

	public static WebElement registerYes(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@type='submit']"));
		return element;
	}

	public static WebElement sendPerksCard(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@name='RequestPerksCard']"));
		Select cardOption = new Select(driver.findElement(By.xpath("//*[@name='RequestPerksCard']")));
		cardOption.selectByIndex(2);
		return element;

	}

	public static WebElement selectCountry(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@name='Country']"));
		Select select = new Select(driver.findElement(By.xpath("//*[@name='Country']")));
		select.selectByVisibleText("Canada");

		return element;
	}

	public static WebElement selectCountry2(WebDriver driver) throws InterruptedException {
		element = driver.findElement(By.xpath("//*[@name='Country']"));
		Select select = new Select(driver.findElement(By.xpath("//*[@name='Country']")));
		select.selectByVisibleText("United States");
		Thread.sleep(1000);
		select.selectByVisibleText("Canada");
		
		return element;
	}
	
	public static WebElement selectState(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='States']"));
		Select state = new Select(driver.findElement(By.xpath("//*[@id='States']")));
		state.selectByVisibleText("Yukon");

		return element;
	}
	
	
	//state

}
