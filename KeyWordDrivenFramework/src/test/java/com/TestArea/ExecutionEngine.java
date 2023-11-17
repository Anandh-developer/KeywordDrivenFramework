package com.TestArea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Actions.BasicActions;
import com.Actions.WebElemetActions;
import com.configurations.BaseConfigurations;
import com.helper.DropdownHelper;
import com.helper.FrameHelper;
import com.helper.ScreenshotHelper;
import com.helper.WaitHelper;
import com.helper.WebElementCreator;
import com.reportUtilities.ExtentReport;

public class ExecutionEngine {

	public Workbook workbook = null;
	public Sheet sheet = null;
	public String fileName;
	public String sheetName;
	BasicActions baseActions = new BasicActions();
	BaseConfigurations baseConfig = new BaseConfigurations();
	WebDriver driver;
	WebElemetActions webElementAction;
	WaitHelper waitHelper;
	FrameHelper frameHelper;
	DropdownHelper dropdownHelper;
	WebElementCreator webElementCreator;
	ScreenshotHelper screenshotHelper;
	ExtentReport extentReport;
	public ExecutionEngine(String fileName, String sheetName) {
		this.fileName = fileName;
		this.sheetName = sheetName;
	}

	public void runTest(int startRow, int endingRow) throws InterruptedException, IOException {
		webElementAction = new WebElemetActions(driver);
		waitHelper = new WaitHelper(driver);
		frameHelper = new FrameHelper(driver);
		String dropdownBasis = "";
		String frameLocateBasis = "";
		WebElement webElement = null;
		int k = 0;
		String locator = "";
		String locatorKey = "";
		String locatorValue = "";
		String action = "";
		String testData = "";
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			workbook = WorkbookFactory.create(inputStream);
		} catch (EncryptedDocumentException | IOException e) {

			e.printStackTrace();
		}
		sheet = workbook.getSheet(sheetName);
		for (int i = startRow; i < endingRow; i++) {

			action = sheet.getRow(i).getCell(k + 1).getStringCellValue();

			testData = sheet.getRow(i).getCell(k + 4).getStringCellValue();

			locatorKey = sheet.getRow(i).getCell(k + 2).getStringCellValue();

			locatorValue = sheet.getRow(i).getCell(k + 3).getStringCellValue();

			dropdownBasis = sheet.getRow(i).getCell(k + 5).getStringCellValue();

			frameLocateBasis = sheet.getRow(i).getCell(k + 7).getStringCellValue();

			switch (action) {

			case "Open_browser":
				
				
				
				if (testData.isBlank() || testData.equalsIgnoreCase("NA")) {
					driver = baseActions.launchBrowser(baseConfig.getBrowserName());
				} else {
					driver = baseActions.launchBrowser(testData);

				}
				extentReport= new ExtentReport(driver);
				extentReport.startTest("Test Reports");
				break;
			case "launch_URL":
				driver.get(testData);
				webElementAction = new WebElemetActions(driver);
				waitHelper = new WaitHelper(driver);
				frameHelper = new FrameHelper(driver);
				webElementCreator = new WebElementCreator(driver);
				dropdownHelper = new DropdownHelper(driver);
				
				screenshotHelper= new ScreenshotHelper(driver);
				screenshotHelper.setupScreenshotFolderName();
				extentReport.setReportLog("info", "URL got launched");
				extentReport.attachScreenshotToTheReport();
				break;
			case "sendkeys":
				webElement = webElementCreator.getWebElement(locatorKey, locatorValue);
				webElement.sendKeys(testData);
				
				screenshotHelper.takeScreenshot();
				
				extentReport.setReportLog("info", "Test Data got entered");
				extentReport.attachScreenshotToTheReport();
				break;

			case "click":
				webElement = webElementCreator.getWebElement(locatorKey, locatorValue);
				webElement.click();
				screenshotHelper.takeScreenshot();
				break;
			case "select_from_dropdown":
				
				webElement = webElementCreator.getWebElement(locatorKey, locatorValue);
				if (dropdownBasis.equalsIgnoreCase("index")) {
					
					dropdownHelper.selectByUsingIndex(webElement, Integer.parseInt(testData));
					screenshotHelper.takeScreenshot();
				}
				if (dropdownBasis.equalsIgnoreCase("value")) {
					
					dropdownHelper.selectByUsingValue(webElement, testData);
					screenshotHelper.takeScreenshot();
				}
				if (dropdownBasis.equalsIgnoreCase("text")) {
				
					dropdownHelper.selectByUsingVisibleText(webElement, testData);
					screenshotHelper.takeScreenshot();
				}
				break;
			case "swicth_to_frame":

				if (frameLocateBasis.equalsIgnoreCase("webElement")) {
					webElement = webElementCreator.getWebElement(locatorKey, locatorValue);
					frameHelper.swithcToFrameWithWebElement(webElement);
					screenshotHelper.takeScreenshot();
				} else if (frameLocateBasis.equalsIgnoreCase("id")) {
					
					frameHelper.switchToFrameWithID(locatorValue);
					screenshotHelper.takeScreenshot();

				} else if (frameLocateBasis.equalsIgnoreCase("index")) {
					
					frameHelper.switchToFrameWithIndex(Integer.parseInt(locatorValue));
					screenshotHelper.takeScreenshot();
				}
				break;
			case "close_browser":
				screenshotHelper.takeScreenshot();
				driver.quit();
				extentReport.flushReport();
				break;
			default:
				
				break;
			}

		}

	}

}
