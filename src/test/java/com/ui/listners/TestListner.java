package com.ui.listners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReportUtility;
import com.utility.LoggerUtility;

public class TestListner implements ITestListener {

	Logger logger = LoggerUtility.getLogger(this.getClass());

	ExtentSparkReporter extentSparkReporter;
	ExtentReports extentReports;
	ExtentTest extentTest;

	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info(Arrays.toString(result.getMethod().getGroups()));
		ExtentReportUtility.createExtentTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + "	" + "PASSED");
		ExtentReportUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + "	" + "PASSED");
	}

	public void onTestFailure(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + "	" + "FAILED");
		logger.info(result.getThrowable().getMessage());
		ExtentReportUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + "	" + "FAILED");
		ExtentReportUtility.getTest().log(Status.FAIL, result.getThrowable().getMessage());
		
		Object testClassInstance= result.getInstance();
		BrowserUtility browserUtility = ((TestBase)testClassInstance).getInstance();
		String screenShotPath = browserUtility.takeScreenShot(result.getMethod().getMethodName().toString());
		logger.info("Capturing the screenShot for failed test : "+result.getMethod().getMethodName());
		ExtentReportUtility.getTest().addScreenCaptureFromPath(screenShotPath);
	}

	public void onTestSkipped(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + "	" + "SKIPPED");
		ExtentReportUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + "	" + "SKIPPED");
	}

	public void onStart(ITestContext context) {
		logger.info("Test suite started");
		ExtentReportUtility.setupSparkReporter("report");
	}

	public void onFinish(ITestContext context) {
		logger.info("Test suite finished");
		ExtentReportUtility.flushReport();
	}

}
