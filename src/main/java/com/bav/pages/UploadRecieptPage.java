package com.bav.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class UploadRecieptPage extends TestBase {

	@FindBy(xpath = "//*[contains(text(), 'Make sure the Store name is clearly visible.')]")
	WebElement buttonBestChoice;

	@FindBy(xpath = "//*[contains(text(), 'CONTACT US')]")
	WebElement contactUs;
	@FindBy(xpath = "//*[contains(text(), 'Member Offers')]")
	WebElement memberOffers;

	//Initialize the page objects
	public UploadRecieptPage() {
		PageFactory.initElements(driver, this);
	}

	public static String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public ContactUsPage clickOnContactUs() {
		contactUs.click();
		System.out.println("Return Contact up page");
		return new ContactUsPage();
		
	}
	
	public HomePage homePage() {
		contactUs.click();
		return new HomePage();
	}

}
