package com.openmrs.pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import com.openmrs.wrappers.GenericWrappers;
import com.aventstack.extentreports.ExtentTest;


public class LoginPage extends GenericWrappers {
	
	public LoginPage(RemoteWebDriver driver, ExtentTest extTest) {
		this.driver = driver;
		this.extTest = extTest;
	}
	
	public LoginPage validatePageTitle(String title) {
		verifyTitle(title);
		return new LoginPage(driver, extTest);
	}
	
	public LoginPage enterUserName(String username) {
		enterById(LocatorsConfig.getProperty("LoginPage.Username"), username);
		return new LoginPage(driver, extTest);
	}
	
	public LoginPage enterPassword(String password) {
		enterById(LocatorsConfig.getProperty("LoginPage.Password"), password);
		return new LoginPage(driver, extTest);
	}
	
	public LoginPage clickLocation(String location) {
		clickById(location);
		return new LoginPage(driver, extTest);
	}
	
	public HomePage checkError(String error) {
		clickById(LocatorsConfig.getProperty("LoginPage.Login"));
		verifyTextByXpath(LocatorsConfig.getProperty("LoginPage.Error"), error);
		return new HomePage(driver, extTest);
	}
	
	public HomePage clickLogin() {
		clickById(LocatorsConfig.getProperty("LoginPage.Login"));
		return new HomePage(driver, extTest);
	}
}
