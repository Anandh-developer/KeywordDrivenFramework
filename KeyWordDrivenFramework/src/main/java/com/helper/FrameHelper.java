package com.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FrameHelper {
	WebDriver driver;

	public FrameHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void swithcToFrameWithWebElement(WebElement element) {
		driver.switchTo().frame(element);
		System.out.println("Switched to frame successfully");
	}

	public void switchToFrameWithIndex(int index) {
		driver.switchTo().frame(index);
	}

	public void switchToFrameWithID(String id) {
		driver.switchTo().frame(id);
	}

	public void switchToParetFrame() {
		driver.switchTo().parentFrame();
	}

}
