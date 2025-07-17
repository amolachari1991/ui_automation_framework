package com.ui.tests;

import static com.constants.Browser.*;

import static org.testng.Assert.*;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;

@Listeners(com.ui.listners.TestListner.class)
public class LoginTest extends TestBase{

	@Test(	priority = 1,
			description = "validate login functionality",
			groups = { "e2e","sanity" },
			dataProviderClass = com.ui.dataProviders.LoginDataProvider.class,
			dataProvider = "loginJSON_DataProvider")	
	
	public void loginJSONTest(User user) {
		String username = homePage.goToLoginPage().doLogin(user.getEmailAddress(), user.getPassword()).getUserName();
		assertEquals(username, "Amolachari");
	}
	
	@Test(	priority = 2,
			description = "validate login functionality",
			groups = { "e2e","sanity" },
			dataProviderClass = com.ui.dataProviders.LoginDataProvider.class,
			dataProvider = "loginCSV_DataProvider")
	
	public void loginCSV_Test(User user) throws InterruptedException {
		String username = homePage.goToLoginPage().doLogin(user.getEmailAddress(), user.getPassword()).getUserName();
		assertEquals(username, "Amol achari");
		Thread.sleep(2000);
	}
	
	@Test(	priority = 3,
			description = "validate login functionality",
			groups = { "e2e","sanity" },
			dataProviderClass = com.ui.dataProviders.LoginDataProvider.class,
			dataProvider = "loginExelDataProvider",
			retryAnalyzer =  com.ui.listners.FailedTestRetryAnalyzer.class)
	
	public void loginExcelTest(User user) throws InterruptedException {
		String username = homePage.goToLoginPage().doLogin(user.getEmailAddress(), user.getPassword()).getUserName();
		assertEquals(username, "Amol achari");
	}

}
