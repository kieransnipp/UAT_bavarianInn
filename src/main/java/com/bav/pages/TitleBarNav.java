package com.bav.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.WebDriverRunner;

public class TitleBarNav {
	WebDriver driver;
	private static WebElement element = null;
	
	//Page Factory
	@FindBy(linkText="Upload Receipt")
	WebElement uploadReciept;
	
//	@FindBy(By.xpath("//*[contains(text(), 'Upload Receipt')]"))
//	WebElement uploadReciept;

	public static WebElement uploadReceipt(WebDriver driver) {
		WebDriverRunner.setWebDriver(driver);
		element = driver.findElement(By.xpath("//*[contains(text(), 'Upload Receipt')]"));
		return element;
	}
}
