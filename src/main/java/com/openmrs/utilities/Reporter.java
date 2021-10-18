package com.openmrs.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.openmrs.wrappers.GenericWrappers;


public abstract class Reporter {
	public static final Logger LOG = LogManager.getLogger(GenericWrappers.class.getName());
	
	public String documentTitle, reportName;
	public String TCName, TCDesc, TCAuthor, TCCategory, TCDevice;
	public static String reportOutputName;
	
	public static ExtentReports extReport;
	public ExtentSparkReporter extSparkReport;
	public ExtentTest extTest;

	public abstract String takeSnap();

	public void startReport(String documentTitle, String reportName) {
		String reportTS = new SimpleDateFormat("YYYYMMddhhmmss").format(new Date());
		reportOutputName = System.getProperty("user.dir") + "\\Reports\\ReportOutput_" + reportTS + ".html";
		
		extSparkReport = new ExtentSparkReporter(reportOutputName);
		extSparkReport.config().setDocumentTitle(documentTitle);
		extSparkReport.config().setReportName(reportName);
		extSparkReport.config().setTheme(Theme.STANDARD);

		extReport = new ExtentReports();
		extReport.attachReporter(extSparkReport);
		
		extReport.setSystemInfo("Company Name", "Omega Solutions Inc");
		extReport.setSystemInfo("Project Name", "Open MRS Application");
		extReport.setSystemInfo("Author Name", "Kuttralakani Moscomani");
		
		LOG.info("The extent report is initialized successfully.");
		
	}

	public void startTest(String TCName, String TCDesc, String TCAuthor, String TCCategory, String TCDevice) {
		extTest=extReport.createTest(TCName, TCDesc);
		extTest.assignAuthor(TCAuthor);
		extTest.assignCategory(TCCategory);
		extTest.assignCategory(TCCategory);
		extTest.assignDevice(TCDevice);
		LOG.info("Created a test successfully.");
	}
	
	public void recordTest(String stepDesc, String stepStatus) {
		String snanShotPath = takeSnap();
		if (stepStatus.equalsIgnoreCase("PASS")) {
			try {
					extTest.pass(stepDesc,MediaEntityBuilder.createScreenCaptureFromPath(snanShotPath).build());
					LOG.info(stepDesc + " is passed successfully.");
				} catch (IOException e) {
					System.out.println(e.getMessage().toString());
					LOG.error(e.getMessage().toString());
				}  catch (Exception e) {
					System.out.println(e.getMessage().toString());
					LOG.fatal(e.getMessage().toString());
				}
		} else if (stepStatus.equalsIgnoreCase("FAIL")) {
			try {
					extTest.fail(stepDesc,MediaEntityBuilder.createScreenCaptureFromPath(snanShotPath).build());
					LOG.info(stepDesc + " is failed.");
				} catch (IOException e) {
					System.out.println(e.getMessage().toString());
					LOG.error(e.getMessage().toString());
				}  catch (Exception e) {
					System.out.println(e.getMessage().toString());
					LOG.fatal(e.getMessage().toString());
				}
		}
	}
	
	public void endTest() {
		extTest=null;
		//extReport.removeTest(extTest);
		LOG.info("Closed the test successfully.");
	}
	
	public void endReport() {
		try {
			extReport.flush();
			Desktop.getDesktop().browse(new File(reportOutputName).toURI());
			LOG.info("Created the extent report successfully.");
		} catch (IOException e) {
			System.out.println(e.getMessage().toString());
			LOG.error(e.getMessage().toString());
		}  catch (Exception e) {
			System.out.println(e.getMessage().toString());
			LOG.fatal(e.getMessage().toString());
		}
	}
}
