package com.configurations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseConfigurations {

	Properties poperties = null;
	String propertyFilePath = System.getProperty("user.dir") + "\\configFolder\\configFile.properties";
	FileInputStream fileInputStream=null;

	public String getBrowserName() {
		try {
			fileInputStream = new FileInputStream(propertyFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		poperties = new Properties();
		try {
			poperties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String browserName = poperties.getProperty("chrome");
		return browserName;
	}

	public String isTestRunINHeadlessMode() {
		try {
			fileInputStream = new FileInputStream(propertyFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		poperties = new Properties();
		try {
			poperties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String isHeadlessMode = poperties.getProperty("headless");
		return isHeadlessMode;
	}
	public String getURL()
	{
		try {
			fileInputStream = new FileInputStream(propertyFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		poperties = new Properties();
		try {
			poperties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String url = poperties.getProperty("url");
		return url;
	}

}
