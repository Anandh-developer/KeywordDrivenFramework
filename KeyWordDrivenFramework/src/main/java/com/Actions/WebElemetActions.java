package com.Actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.helper.WaitHelper;

public class WebElemetActions {
	WebDriver driver;
	public WebElemetActions(WebDriver driver)
	{
		this.driver=driver;
	}
	WaitHelper waitHelper= new WaitHelper(driver);

	public void clearInput(WebElement element) {
		element.clear();
	}

	public void sendInputData(WebElement element, String inputData) throws InterruptedException {
		System.out.println("Inside Send Input function");
		element.click();
		System.out.println("Input data "+inputData);
		element.sendKeys(inputData);
		
	}

	public void clickElement(WebElement element) {
	
		element.click();
	}
	

}
