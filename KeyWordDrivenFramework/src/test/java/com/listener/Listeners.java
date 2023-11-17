package com.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.reportUtilities.ExtentReport;

public class Listeners implements ITestListener {

	public void onStart(ITestContext context) {
		System.out.println(context.getName());
		System.out.println("Test start");
	
	}
	public void onTestStart(ITestResult result) {
		System.out.println("Test Started");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test passed");
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Test failed");
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Test skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Test failed with pass percentage");
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		System.out.println("Test failed with pass percentage");
	}



	public void onFinish(ITestContext context) {
		System.out.println("Test finish");
	}
}
