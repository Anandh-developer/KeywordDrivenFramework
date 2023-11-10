package com.TestArea;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestEngine {

	public WebDriver driver;

	String sheetName = "FrameScenario";
	public String excelSheet = System.getProperty("user.dir") + "\\ExcelScenarios\\KeyWordScenarios.xlsx";
	ExecutionEngine exeEngine=new ExecutionEngine(excelSheet,sheetName);
	@Test
	public void testEngnine() throws InterruptedException {
		exeEngine.runTest(11,16);
	}

}
