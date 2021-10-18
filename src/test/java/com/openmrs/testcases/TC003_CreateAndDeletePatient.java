package com.openmrs.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.openmrs.pages.LoginPage;
import com.openmrs.utilities.TestDataProvider;
import com.openmrs.wrappers.ProjectWrappers;

public class TC003_CreateAndDeletePatient extends ProjectWrappers {
	
	@BeforeClass
	public void beforeClass() {
		appURL = GlobalConfig.getProperty("AppURL"); 
		BrowserName = GlobalConfig.getProperty("Browser");
		TCName= "Create a patient and delete the same patient.";
		TCDesc= "Create a new patient and check whether the same patient is added. Delete the same patient.";
		TCAuthor= GlobalConfig.getProperty("Author");
		TCCategory= GlobalConfig.getProperty("Category");
		TCDevice= GlobalConfig.getProperty("Platform");
	}
	
	@Test(dataProvider = "fetchData")
	public void createPatientAndDelete(String username, String password, String location, String loginTitle, String homeTitle,
			String giveName, String familyName, String gender, 
			String day, String month, String year, String address1, String address2, 
			String City, String State, String Country, String postCode, String phone, 
			String relType, String relName, String searchPatientTitle, 
			String createPatientTitle, String patientTitle, String deleteReason) {
		com.openmrs.utilities.MyScreenRecorder.startRecording("createPatientAndDelete");
		new LoginPage(driver, extTest)
		.validatePageTitle(loginTitle)
		.enterUserName(username)
		.enterPassword(password)
		.clickLocation(location)
		.clickLogin()
		.validatePageTitle(homeTitle)
		.clickRegisterPatient()
		.validatePageTitle(createPatientTitle)
		.enterGivenName(giveName)
		.enterFamilyName(familyName)
		.clickNext()
		.selectGender(gender)
		.clickNext()
		.enterDate(day)
		.selectMonth(month)
		.enterYear(year)
		.clickNext()
		.enterAddress1(address1)
		.enterAddress2(address2)
		.enterCity(City)
		.enterState(State)
		.enterCounty(Country)
		.enterPostal(postCode)
		.clickNext()
		.enterPhone(phone)
		.clickNext()
		.selectRelType(relType)
		.enterRelName(relName)
		.clickNext()
		.validateName(giveName, familyName)
		.validateGender(gender)
		.validateDOB(day, month, year)
		.validateAddress(address1, address2, City, State, Country, postCode)
		.validatePhone(phone)
		.validateRelative(relType, relName)
		.clickSubmit()
		.validatePageTitle(patientTitle)
		.validateGivenName(giveName)
		.validateFamilyName(familyName)
		.clickHome()
		.clickSearchPatient()
		.validatePageTitle(searchPatientTitle)
		.enterPatientName(giveName)
		.isPatientAvailable(giveName,"Data")
		.clickFirstPatient()
		.clickDeletePatient()
		.enterDeleteReason(deleteReason)
		.clickDeleteConfirmation()
		.enterPatientName(giveName)
		.isPatientAvailable(giveName,"No Data")
		.clickHome()
		.clickLogout();
		com.openmrs.utilities.MyScreenRecorder.stopRecording();
		
	}
	
	@DataProvider(name="fetchData")
	public Object[][] getData() {
		return TestDataProvider.getData("Register Patient - Test Data");
	}

}
