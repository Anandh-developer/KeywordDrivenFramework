package com.Actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.configurations.BaseConfigurations;

public class BasicActions {
	BaseConfigurations baseConfigurations;
	WebDriver driver;

	public WebDriver launchBrowser(String browserName) {

		baseConfigurations = new BaseConfigurations();
		if (browserName.equalsIgnoreCase("chrome")) {
			if (baseConfigurations.isTestRunINHeadlessMode().equalsIgnoreCase("Yes")) {
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--headless");
				driver = new ChromeDriver(option);
				driver.manage().window().maximize();
			} else {
				driver = new ChromeDriver();
				driver.manage().window().maximize();
			}

		}
		return driver;
	}

	public void closeBrowser() {
		driver.close();
	}

}
