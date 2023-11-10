package com.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownHelper {
	WebDriver driver;
	Select select;

	public DropdownHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void selectByUsingVisibleText(WebElement element, String dropdownText) {
		select = new Select(element);
		select.selectByVisibleText(dropdownText);
	}

	public void selectByUsingIndex(WebElement element, int index) {
		select = new Select(element);
		select.selectByIndex(index);

	}

	public void selectByUsingValue(WebElement element, String value) {
		select = new Select(element);
		select.selectByValue(value);

	}
}
