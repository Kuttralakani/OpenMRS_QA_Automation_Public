package com.openmrs.pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.openmrs.wrappers.GenericWrappers;
import com.aventstack.extentreports.ExtentTest;


public class PatientPage extends GenericWrappers {
	
	public PatientPage(RemoteWebDriver driver, ExtentTest extTest) {
		this.driver = driver;
		this.extTest = extTest;
	}
	
	public PatientPage validatePageTitle(String title) {
		verifyTitle(title);
		return new PatientPage(driver, extTest);
	}
	
	public PatientPage validateGivenName(String givenName) {
		verifyTextByCssSelector(LocatorsConfig.getProperty("PatientPage.Given"), givenName);
		return new PatientPage(driver, extTest);
	}
	
	public PatientPage validateFamilyName(String familyName) {
		verifyTextByCssSelector(LocatorsConfig.getProperty("PatientPage.Family"), familyName);
		return new PatientPage(driver, extTest);
	}
	
	public PatientPage clickDeletePatient() {
		clickByXpath(LocatorsConfig.getProperty("PatientPage.Delete"));
		return new PatientPage(driver, extTest);
	}
	
	public PatientPage enterDeleteReason(String deleteReason) {
		enterByXpath(LocatorsConfig.getProperty("PatientPage.DeleteReason"), deleteReason);
		return new PatientPage(driver, extTest);
	}
	
	public SearchPatientPage clickDeleteConfirmation() {
		clickByXpath(LocatorsConfig.getProperty("PatientPage.DeleteConfirm"));
		return new SearchPatientPage(driver, extTest);
	}
	
	public HomePage clickHome() {
		clickByXpath(LocatorsConfig.getProperty("Breadcrumb.Home"));
		return new HomePage(driver, extTest);
	}
}
