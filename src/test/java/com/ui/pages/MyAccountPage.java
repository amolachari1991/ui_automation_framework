package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public final class MyAccountPage extends BrowserUtility {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	private static final By USER_NAME = By.xpath("//*[contains(text(),'Amol achari')]");
	
	public String getUserName() {
		return getText(USER_NAME);
	}

}
