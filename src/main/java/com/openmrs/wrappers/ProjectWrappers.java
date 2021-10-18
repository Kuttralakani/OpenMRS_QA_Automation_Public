package com.openmrs.wrappers;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class ProjectWrappers extends GenericWrappers {
	public static String appURL, BrowserName;
	
	@BeforeSuite
	public void beforeSuite() {
		loadProperties();
	}
	
	@BeforeTest
	public void beforeTest() {
		documentTitle = GlobalConfig.getProperty("DocumentTitle");
		reportName= GlobalConfig.getProperty("ReportName");
		startReport(documentTitle, reportName);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		startTest(TCName, TCDesc, TCAuthor, TCCategory, TCDevice);
		invokeApp(appURL, BrowserName);
	}
	
	@AfterMethod
	public void afterMedthod() {
		closeAllBrowsers();
	}
		
	@AfterClass
	public void afterClass() {
		endTest();
	}
	
	@AfterTest
	public void afterTest() {
		unloadProperties();
	}
	
	@AfterSuite
	public void afterSuite() {
		endReport();
	}
}
