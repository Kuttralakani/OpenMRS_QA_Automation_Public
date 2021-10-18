package com.openmrs.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.openmrs.pages.LoginPage;
import com.openmrs.utilities.TestDataProvider;
import com.openmrs.wrappers.ProjectWrappers;

public class TC002_UnsuccessfulLoginTest extends ProjectWrappers {

	@BeforeClass
	public void beforeClass() {
		appURL = GlobalConfig.getProperty("AppURL"); 
		BrowserName = GlobalConfig.getProperty("Browser");
		TCName= "Unsuccessful Login to OpenMRS";
		TCDesc= "Login to OpenMRS application with invalid username and password. Ensure the login is not successful.";
		TCAuthor= GlobalConfig.getProperty("Author");
		TCCategory= GlobalConfig.getProperty("Category");
		TCDevice= GlobalConfig.getProperty("Platform");
	}
	
	@Test(dataProvider = "fetchData")
	public void unSuccessfulLoginTest(String username, String password, String location, String error, String beforeLoginTitle) {
		com.openmrs.utilities.MyScreenRecorder.startRecording("unSuccessfulLoginTest");
		new LoginPage(driver, extTest)
		.validatePageTitle(beforeLoginTitle)
		.enterUserName(username)
		.enterPassword(password)
		.clickLocation(location)
		.checkError(error);	
		com.openmrs.utilities.MyScreenRecorder.stopRecording();
	}
	
	@DataProvider(name="fetchData")
	public Object[][] getData() {
		return TestDataProvider.getData("Invalid Login - Test Data");
	}
}
