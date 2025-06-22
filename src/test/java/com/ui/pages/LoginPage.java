package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public final class LoginPage extends BrowserUtility {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	private static final By EMAIL_LOCATOR = By.xpath("//*[@id=\"email\"]");
	private static final By PASSWARD_LOCATOR = By.xpath("//*[@id=\"passwd\"]");
	private static final By SIGN_BTN = By.xpath("//span[normalize-space()='Sign in']");

	public MyAccountPage doLogin(String email , String passward) {
		enterText(EMAIL_LOCATOR, email);
		enterText(PASSWARD_LOCATOR, passward);
		clickOn(SIGN_BTN);
		return new MyAccountPage(getDriver());
	}

}
