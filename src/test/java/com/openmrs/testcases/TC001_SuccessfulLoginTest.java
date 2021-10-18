package com.openmrs.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.openmrs.wrappers.ProjectWrappers;
import com.openmrs.pages.LoginPage;
import com.openmrs.utilities.*;

public class TC001_SuccessfulLoginTest extends ProjectWrappers {
	
	@BeforeClass
	public void beforeClass() {
		appURL = GlobalConfig.getProperty("AppURL"); 
		BrowserName = GlobalConfig.getProperty("Browser");
		TCName= "Successful Login to OpenMRS";
		TCDesc= "Login to OpenMRS application with valid username and password. Ensure the login is successful.";
		TCAuthor= GlobalConfig.getProperty("Author");
		TCCategory= GlobalConfig.getProperty("Category");
		TCDevice= GlobalConfig.getProperty("Platform");
	}
	
	@Test(dataProvider = "fetchData")
	public void successfulLoginTest(String username, String password, String location, String beforeLoginTitle, String afterLoginTitle) {
		com.openmrs.utilities.MyScreenRecorder.startRecording("successfulLoginTest");
		new LoginPage(driver, extTest)
		.validatePageTitle(beforeLoginTitle)
		.enterUserName(username)
		.enterPassword(password)
		.clickLocation(location)
		.clickLogin()
		.validatePageTitle(afterLoginTitle)
		.validateUsername(username)
		.validateLocation(location)
		.clickLogout();
		com.openmrs.utilities.MyScreenRecorder.stopRecording();
	}
	
	@DataProvider(name="fetchData")
	public Object[][] getData() {
		return TestDataProvider.getData("Login - Test Data");
	}
}
