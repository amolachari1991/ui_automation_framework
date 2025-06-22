package com.ui.tests;

import static com.constants.Browser.FIREFOX;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LamdaTestUtility;
import com.utility.LoggerUtility;

public class TestBase {
	protected HomePage homePage;
	Logger loger = LoggerUtility.getLogger(this.getClass());
	private boolean isLamdaTest;
	@Parameters({"browser", "isLamdaTest", "isHeadless"})
	@BeforeMethod
	public void setUp(
			@Optional("CHROME") String browser, 
			@Optional("false") boolean isLamdaTest, 
			@Optional("false") boolean isHeadless, 
			ITestResult result) {
		WebDriver lambdaDriver = null;
		this.isLamdaTest=isLamdaTest;
		if (isLamdaTest) {
			lambdaDriver = LamdaTestUtility.initializeLamdaTest(Browser.valueOf(browser.toUpperCase()), result.getMethod().getMethodName());
			homePage = new HomePage(lambdaDriver);
		} else {
			loger.info("Loading the HomePage");
			homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);
		}
	}

	public BrowserUtility getInstance() {
		return homePage;
	}

	@AfterMethod
	public void tearDown() {
		if (isLamdaTest) {
			LamdaTestUtility.quitSession();
		} else {
			homePage.exitBrowser();
		}
	}
}
