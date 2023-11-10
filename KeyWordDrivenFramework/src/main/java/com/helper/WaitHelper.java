package com.helper;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {
	
	WebDriver driver;
	WebDriverWait wait;
	public WaitHelper(WebDriver driver)
	{
		this.driver=driver;
	}
	public void implecitWait()
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(120));
	}
	public void waitForElementToPresent(WebElement element,int second)
	{
	wait= new WebDriverWait(driver, Duration.ofSeconds(second));
	wait.until(ExpectedConditions.elementToBeClickable(element));
	}

}
