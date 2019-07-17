package com.bav.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	WebDriver driver;
	private static WebElement element = null;
	
	
	//Top of page
	public static WebElement howItWorks(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[contains(text(), 'How it')]"));
		return element;
	}
	
	
	public static WebElement selectRegister(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[contains(text(),'Register')]"));
		//WebElement register = driver.findElement(By.xpath("//*[contains(text(), 'Register')]"));
		return element;
	}
	
	public static WebElement faq(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[contains(text(), 'FAQ')]"));
		//WebElement faq = driver.findElement(By.xpath("//*[contains(text(), 'FAQ')]"));
		return element;
	}
	
	//End top of the page
	
	//Enter details
	public static WebElement enterEmail(WebDriver driver) {
		element= driver.findElement(By.xpath("//input[@name='Email']"));
		return element;
	}
	
	public static WebElement enterPassW(WebDriver driver) {
		element= driver.findElement(By.xpath("//input[@name='Password']"));
		return element;
	}
	 
	public static WebElement forgotPassword(WebDriver driver) {
		element= driver.findElement(By.linkText("Forgot password?"));
		return element;
	}

	public static WebElement loginButton(WebDriver driver) {
		element= driver.findElement(By.name("loginButton"));
		return element;
	}
	
	


} // End of page
