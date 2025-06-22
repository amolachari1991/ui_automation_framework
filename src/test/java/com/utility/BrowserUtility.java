package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;

public abstract class BrowserUtility {
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);
	}

	public BrowserUtility(String browserName) {
		super();
		if (browserName.equalsIgnoreCase("chrome")) {
			logger.info("Launching browser : " + browserName);
			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("edge")) {
			logger.info("Launching browser : " + browserName);
			driver.set(new EdgeDriver());
		} else if (browserName.equalsIgnoreCase("firefox")) {
			logger.info("Launching browser : " + browserName);
			driver.set(new FirefoxDriver());
		} else {
			logger.info("Invalid browser...please select chrome or edge or firefox");
			System.out.println("Invalid browser...please select chrome or edge or firefox");
		}
	}

	public BrowserUtility(Browser browserName) {
		super();
		logger.info("Launching browser : " + browserName);
		if (browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());
		} else if (browserName == Browser.EDGE) {
			driver.set(new EdgeDriver());
		} else if (browserName == Browser.FIREFOX) {
			driver.set(new FirefoxDriver());
		}
	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
		super();
		logger.info("Launching browser : " + browserName);
		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(chromeOptions));
			} else {
				driver.set(new ChromeDriver());
			}
		}
		if (browserName == Browser.EDGE) {
			if (isHeadless) {
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.addArguments("--headless");
				edgeOptions.addArguments("--window-size=1920,1080");
				driver.set(new EdgeDriver(edgeOptions));
			} else {
				driver.set(new EdgeDriver());
			}
		}
		if (browserName == Browser.FIREFOX) {
			if (isHeadless) {
				FirefoxOptions fireFoxOptions = new FirefoxOptions();
				fireFoxOptions.addArguments("--headless");
				fireFoxOptions.addArguments("--window-size=1920,1080");
				driver.set(new FirefoxDriver(fireFoxOptions));
			} else {
				driver.set(new FirefoxDriver());
			}
		}
	}

	public void goToWebsite(String url) {
		logger.info("Navigating to website =>" + url);
		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maximizing the window");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		logger.info("clicking on intended locator : " + locator);
		WebElement element = driver.get().findElement(locator);
		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		logger.info("entering text[ " + textToEnter + " ] into : " + locator);
		WebElement element = driver.get().findElement(locator);
		element.sendKeys(textToEnter);
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public String getText(By locator) {
		logger.info("get text from locator : " + locator);
		return driver.get().findElement(locator).getText();
	}

	public void exitBrowser() {
		logger.info("exiting the browser");
		driver.get().quit();
	}

	public String takeScreenShot(String name) {
		if (driver.get() == null) {
			logger.error("WebDriver is null. Cannot take screenshot.");
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		Date date = new Date();
		String fileNameDate = formatter.format(date);
		String path = ".//screenShoots//" + name + "_" + fileNameDate + ".png";
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
//	public String takeScreenShot(String name) {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
//        Date date = new Date();
//        String fileNameDate = formatter.format(date);
//		String path = System.getProperty("user.dir")+"/screenShoots/"+name+"_"+fileNameDate+".png";
//		
//		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
//		File src = screenshot.getScreenshotAs(OutputType.FILE);
//		File destination = new File(path);
//		try {
//			FileUtils.copyFile(src, destination);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return path;
//	}

}
