package com.TestArea;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(com.listener.Listeners.class)
public class TestEngine {

	public WebDriver driver;

	String sheetName = "FrameScenario";
	public String excelSheet = System.getProperty("user.dir") + "\\ExcelScenarios\\KeyWordScenarios.xlsx";
	ExecutionEngine exeEngine=new ExecutionEngine(excelSheet,sheetName);
	@Test(testName="Test One")
	public void testEngnineOne() throws InterruptedException {
		try {
			exeEngine.runTest(11,16);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test(testName="Test Two")
	public void testEngnineTwo() throws InterruptedException {
		try {
			exeEngine.runTest(11,16);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
