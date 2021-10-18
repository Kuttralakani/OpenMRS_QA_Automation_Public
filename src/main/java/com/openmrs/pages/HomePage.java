package com.openmrs.pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import com.openmrs.wrappers.GenericWrappers;
import com.aventstack.extentreports.ExtentTest;

public class HomePage extends GenericWrappers {
	
	public HomePage(RemoteWebDriver driver, ExtentTest extTest) {
		this.driver = driver;
		this.extTest = extTest;
	}
	
	public HomePage validatePageTitle(String title) {
		verifyTitle(title);
		return new HomePage(driver, extTest);
	}
	
	public HomePage validateUsername(String username) {
		verifyTextByXpath(LocatorsConfig.getProperty("HomePage.Username"),username);
		return new HomePage(driver, extTest);
	}
	
	public HomePage validateLocation(String location) {
		verifyTextByXpath(LocatorsConfig.getProperty("HomePage.Location"),location);
		return new HomePage(driver, extTest);
	}
	
	public CreatePatientPage clickRegisterPatient() {
		clickByXpath(LocatorsConfig.getProperty("HomePage.RegisterPatient"));
		return new CreatePatientPage(driver, extTest);
	}
	
	public SearchPatientPage clickSearchPatient() {
		clickByXpath(LocatorsConfig.getProperty("HomePage.SearchPatient"));
		return new SearchPatientPage(driver, extTest);
	}
	
	public CreatePatientPage clickCreatePatient() {
		clickByXpath(LocatorsConfig.getProperty("HomePage.RegisterPatient"));
		return new CreatePatientPage(driver, extTest);
	}
	
	public LoginPage clickLogout() {
		clickByXpath(LocatorsConfig.getProperty("HomePage.Logout"));
		return new LoginPage(driver, extTest);
	}
}
