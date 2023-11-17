package com.helper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotHelper {
	WebDriver driver;
	static int fileIndex = 0;
	static String destinationFilePath = "";

	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
	LocalDateTime localDateTime = LocalDateTime.now();
	String localDate = localDateTime.format(dateTimeFormatter);

	public ScreenshotHelper(WebDriver driver) {
		this.driver = driver;
	}

	public File takeScreenshot() {

		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destinationFile = new File(destinationFilePath + "\\screenshots\\screenshot" + fileIndex + ".png");
		try {
			FileUtils.copyFile(screenshotFile, destinationFile);
			fileIndex = fileIndex + 1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationFile;

	}

	

	public String setupScreenshotFolderName() {
		destinationFilePath = System.getProperty("user.dir") + "\\ScreenshotFolder" + localDate;
		return destinationFilePath;
	}

}
