package com.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementCreator {
	WebDriver driver;

	public WebElementCreator(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getWebElement(String elementType, String locator) {
		WebElement element = null;
		switch (elementType) {
		case "xpath":
			element = driver.findElement(By.xpath(locator));
			break;
		case "id":
			element = driver.findElement(By.id(locator));
			break;
		case "name":
			element = driver.findElement(By.name(locator));
			break;
		case "className":
			element = driver.findElement(By.className(locator));
			break;
		case "linkText":
			element = driver.findElement(By.linkText(locator));
			break;
		case "cssSelector":
			element = driver.findElement(By.cssSelector(locator));
			break;
		default:
			System.out.println("Locator is not getting matched");
			break;
		}

		return element;

	}

}
