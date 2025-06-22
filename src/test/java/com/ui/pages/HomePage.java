package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import com.constants.Env;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.PropertiesUtil;

public final class HomePage extends BrowserUtility{
	
	public HomePage(Browser browserName , boolean isHeadless) {
		super(browserName, isHeadless);
//		goToWebsite(PropertiesUtil.readProperty(Env.QA, "URL"));//read environment via properties
		goToWebsite(JSONUtility.readJSON(Env.QA).getUrl());//read env via JSON using GSON dependency
//		maximizeWindow();
	}
	
	public HomePage(WebDriver driver) {
		super(driver);
		goToWebsite(JSONUtility.readJSON(Env.QA).getUrl());
	}

	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),\"Sign\")]");
	
	public LoginPage goToLoginPage() {
		clickOn(SIGN_IN_LINK_LOCATOR);
		return new LoginPage(getDriver());
	}

}
