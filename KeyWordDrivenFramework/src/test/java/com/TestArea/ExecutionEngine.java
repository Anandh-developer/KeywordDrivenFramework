package com.TestArea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Actions.BasicActions;
import com.Actions.WebElemetActions;
import com.configurations.BaseConfigurations;
import com.helper.DropdownHelper;
import com.helper.FrameHelper;
import com.helper.WaitHelper;
import com.helper.WebElementCreator;

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

	public ExecutionEngine(String fileName, String sheetName) {
		this.fileName = fileName;
		this.sheetName = sheetName;
	}

	public void runTest(int startRow, int endingRow) throws InterruptedException {
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
				System.out.println("Test Data " + testData);
				if (testData.isBlank() || testData.equalsIgnoreCase("NA")) {
					driver = baseActions.launchBrowser(baseConfig.getBrowserName());
				} else {
					driver = baseActions.launchBrowser(testData);

				}

				break;
			case "launch_URL":
				driver.get(testData);
				webElementAction = new WebElemetActions(driver);
				waitHelper = new WaitHelper(driver);
				frameHelper = new FrameHelper(driver);
				webElementCreator = new WebElementCreator(driver);
				dropdownHelper = new DropdownHelper(driver);
				break;
			case "sendkeys":
				webElement = webElementCreator.getWebElement(locatorKey, locatorValue);
				webElement.sendKeys(testData);
				System.out.println("Test Data " + testData);
				Thread.sleep(3000);
				break;

			case "click":
				webElement = webElementCreator.getWebElement(locatorKey, locatorValue);
				webElement.click();
				break;
			case "select_from_dropdown":
				System.out.println("Dropdown case");
				webElement = webElementCreator.getWebElement(locatorKey, locatorValue);
				if (dropdownBasis.equalsIgnoreCase("index")) {
					System.out.println("Dropdown basis is index");
					dropdownHelper.selectByUsingIndex(webElement, Integer.parseInt(testData));
				}
				if (dropdownBasis.equalsIgnoreCase("value")) {
					System.out.println("Dropdown basis is value");
					dropdownHelper.selectByUsingValue(webElement, testData);
				}
				if (dropdownBasis.equalsIgnoreCase("text")) {
					System.out.println("Dropdown basis is text");
					dropdownHelper.selectByUsingVisibleText(webElement, testData);
				}
				break;
			case "swicth_to_frame":

				if (frameLocateBasis.equalsIgnoreCase("webElement")) {
					webElement = webElementCreator.getWebElement(locatorKey, locatorValue);
					frameHelper.swithcToFrameWithWebElement(webElement);
				} else if (frameLocateBasis.equalsIgnoreCase("id")) {
					System.out.println("Frame Locate Basis " + frameLocateBasis);
					System.out.println("Frame Locate ID " + locatorValue);
					frameHelper.switchToFrameWithID(locatorValue);
					
				}
				else if (frameLocateBasis.equalsIgnoreCase("index")) {
					System.out.println("Frame Locate Basis " + frameLocateBasis);
					System.out.println("Frame Locate ID " + locatorValue);
					frameHelper.switchToFrameWithIndex(Integer.parseInt(locatorValue));
					Thread.sleep(3000);
				}
				break;
			case "close_browser":
				driver.quit();
				break;
			default:
				System.out.println("Main Default");
				break;
			}

		}

	}

}
