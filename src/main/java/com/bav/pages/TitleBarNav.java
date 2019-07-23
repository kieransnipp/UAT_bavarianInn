package com.bav.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.codeborne.selenide.WebDriverRunner;

public class TitleBarNav {
	WebDriver driver;
	private static WebElement element = null;
	
	

	public static WebElement uploadReceipt(WebDriver driver) {
		WebDriverRunner.setWebDriver(driver);
		element = driver.findElement(By.xpath("//*[contains(text(), 'Upload Receipt')]"));
		return element;
	}
}
