package com.reportUtilities;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.helper.ScreenshotHelper;

public class ExtentReport {

	ExtentReports extentReport = new ExtentReports();

	ExtentSparkReporter extentSparkReporter;
	ExtentTest extentTest;
	WebDriver driver;
	ScreenshotHelper screenshotHelper;

	public ExtentReport(WebDriver driver) {
		this.driver = driver;
	}

	public void startTest(String testName) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
		LocalDateTime localDateTime = LocalDateTime.now();
		String localDate = localDateTime.format(dateTimeFormatter);
		String  reportPath=System.getProperty("user.dir")+"\\TestReport\\executionReport"+localDate+".html";
		extentSparkReporter = new ExtentSparkReporter(reportPath);
		extentTest = extentReport.createTest(testName);
		screenshotHelper = new ScreenshotHelper(driver);
		extentSparkReporter.config().setTheme(Theme.DARK);
		extentSparkReporter.config().setReportName("Spark Report");
		extentReport.attachReporter(extentSparkReporter);
	}

	public void attachScreenshotToTheReport() {
		File takeScreenhotToAttachIntoFile = screenshotHelper.takeScreenshot();
		extentTest.addScreenCaptureFromPath(takeScreenhotToAttachIntoFile.getAbsolutePath());
		
	}

	public void setReportLog(String status, String message) {
		switch (status) {
		case "warning":
			extentTest.log(Status.WARNING, message);
			attachScreenshotToTheReport();
			break;
		case "info":
			extentTest.log(Status.INFO, message);
			attachScreenshotToTheReport();
			break;
		case "pass":
			extentTest.log(Status.PASS, message);
			attachScreenshotToTheReport();
			break;
		case "fail":
			extentTest.log(Status.FAIL, message);
			attachScreenshotToTheReport();
			break;
		case "skip":
			extentTest.log(Status.SKIP, message);
			attachScreenshotToTheReport();
			break;

		default:
			System.out.println("There is no logges got matched");
			break;
		}
	}

	public void flushReport() {
		
		extentReport.flush();
	}

}
