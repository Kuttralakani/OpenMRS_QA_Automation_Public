package com.openmrs.pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.openmrs.wrappers.GenericWrappers;

public class CreatePatientPage extends GenericWrappers {

	public CreatePatientPage(RemoteWebDriver driver, ExtentTest extTest) {
		this.driver = driver;
		this.extTest = extTest;
	}
	
	public CreatePatientPage validatePageTitle(String title) {
		verifyTitle(title);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage enterGivenName(String givenName) {
		enterByXpath(LocatorsConfig.getProperty("CPatient.Given"), givenName);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage enterFamilyName(String familyName) {
		enterByXpath(LocatorsConfig.getProperty("CPatient.Family"), familyName);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage selectGender(String gender) {
		selectVisibileTextByXpath(LocatorsConfig.getProperty("CPatient.Gender"), gender);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage enterDate(String dateDOB) {
		enterByXpath(LocatorsConfig.getProperty("CPatient.Date"), dateDOB);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage selectMonth(String monthDOB) {
		selectVisibileTextByXpath(LocatorsConfig.getProperty("CPatient.Month"), monthDOB);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage enterYear(String yearDOB) {
		enterByXpath(LocatorsConfig.getProperty("CPatient.Year"), yearDOB);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage enterAddress1(String address1) {
		enterByXpath(LocatorsConfig.getProperty("CPatient.Address1"), address1);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage enterAddress2(String address2) {
		enterByXpath(LocatorsConfig.getProperty("CPatient.Address2"), address2);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage enterCity(String city) {
		enterByXpath(LocatorsConfig.getProperty("CPatient.City"), city);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage enterState(String state) {
		enterByXpath(LocatorsConfig.getProperty("CPatient.State"), state);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage enterCounty(String county) {
		enterByXpath(LocatorsConfig.getProperty("CPatient.County"), county);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage enterPostal(String postal) {
		enterByXpath(LocatorsConfig.getProperty("CPatient.Postal"), postal);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage enterPhone(String phone) {
		enterByXpath(LocatorsConfig.getProperty("CPatient.Phone"), phone);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage selectRelType(String relType) {
		selectVisibileTextByXpath(LocatorsConfig.getProperty("CPatient.RelType"), relType);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage enterRelName(String relName) {
		enterByXpath(LocatorsConfig.getProperty("CPatient.RelName"), relName);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage clickNext() {
		clickByXpath(LocatorsConfig.getProperty("CPatient.Next"));
		return new CreatePatientPage(driver, extTest);
	}
	
	public PatientPage clickSubmit() {
		clickByXpath(LocatorsConfig.getProperty("CPatient.Submit"));
		return new PatientPage(driver, extTest);
	}
	
	public CreatePatientPage validateName(String giveName, String familyName) {
		verifyTextByXpath(LocatorsConfig.getProperty("CPatient.Confirmation.Name"),"Name: " + giveName + ", " + familyName);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage validateGender(String gender) {
		verifyTextByXpath(LocatorsConfig.getProperty("CPatient.Confirmation.Gender"),"Gender: " + gender);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage validateDOB(String day, String month, String year) {
		verifyTextByXpath(LocatorsConfig.getProperty("CPatient.Confirmation.DoB"),"Birthdate: " + day + ", " + month + ", " + year);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage validateAddress(String address1, String address2, String city, String state, String county, String postal) {
		verifyTextByXpath(LocatorsConfig.getProperty("CPatient.Confirmation.Address"),"Address: " + address1 + ", " + address2 + ", " + city + ", " + state + ", " + county + ", " + postal);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage validatePhone(String phone) {
		verifyTextByXpath(LocatorsConfig.getProperty("CPatient.Confirmation.Phone"),"Phone Number: " + phone);
		return new CreatePatientPage(driver, extTest);
	}
	
	public CreatePatientPage validateRelative(String relType, String relName) {
		verifyTextByXpath(LocatorsConfig.getProperty("CPatient.Confirmation.Relative"),"Relatives: " + relName + " - " + relType);
		return new CreatePatientPage(driver, extTest);
	}
}
