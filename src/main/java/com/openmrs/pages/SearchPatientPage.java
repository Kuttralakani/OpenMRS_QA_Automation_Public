package com.openmrs.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.openmrs.wrappers.GenericWrappers;
import com.aventstack.extentreports.ExtentTest;


public class SearchPatientPage extends GenericWrappers {
	
	public SearchPatientPage(RemoteWebDriver driver, ExtentTest extTest) {
		this.driver = driver;
		this.extTest = extTest;
	}
	
	public SearchPatientPage validatePageTitle(String title) {
		verifyTitle(title);
		return new SearchPatientPage(driver, extTest);
	}
	
	public SearchPatientPage enterPatientName(String patientName) {
		enterByXpath(LocatorsConfig.getProperty("SearchPatient.Search"), patientName);
		waitTillElementDisappearByXpath(LocatorsConfig.getProperty("SearchPatient.Spinner"),3);
		return new SearchPatientPage(driver, extTest);
	}
	
	public SearchPatientPage isPatientAvailable(String patientName, String avail) {
		List<WebElement> patientDetails = getDataFromTableByXpath(LocatorsConfig.getProperty("SearchPatient.Result"));
		isSearchPatientAvail(patientDetails, patientName, avail);
		return new SearchPatientPage(driver, extTest);
	}
	
	public PatientPage clickFirstPatient() {
		clickRowInTableByXpath(LocatorsConfig.getProperty("SearchPatient.Result"),0);
		return new PatientPage(driver, extTest);
	} 
	
	public HomePage clickHome() {
		clickByXpath(LocatorsConfig.getProperty("Breadcrumb.Home"));
		return new HomePage(driver, extTest);
	} 
}
