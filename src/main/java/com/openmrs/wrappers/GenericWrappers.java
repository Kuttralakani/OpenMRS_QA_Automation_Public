package com.openmrs.wrappers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.openmrs.utilities.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GenericWrappers extends Reporter implements Wrappers {

	public RemoteWebDriver driver;
	public static Properties GlobalConfig;
	public static Properties LocatorsConfig;

	public void loadProperties() {
		try {
			String globalConfigPath = System.getProperty("user.dir")
					+ "\\src\\test\\resources\\com\\openmrs\\configs\\GlobalConfig.properties";
			String locatorsConfigPath = System.getProperty("user.dir")
					+ "\\src\\test\\resources\\com\\\\openmrs\\configs\\LocatorsConfig.properties";

			GlobalConfig = new Properties();
			LocatorsConfig = new Properties();

			GlobalConfig.load(new FileInputStream(new File(globalConfigPath)));
			LocatorsConfig.load(new FileInputStream(new File(locatorsConfigPath)));
			LOG.info("The properties files are loaded successfully.");
		} catch (FileNotFoundException e) {
			recordTest("The Configuration File is not found.", "FAIL");
			LOG.error("The Configuration File is not found.");
		} catch (IOException e) {
			recordTest("The Configuration File is not found.", "FAIL");
			LOG.error("The Configuration File is not found.");
		} catch (Exception e) {
			recordTest("The Configuration File is not found.", "FAIL");
			LOG.fatal("The Configuration File is not found.");
		}
	}

	public void unloadProperties() {
		GlobalConfig = null;
		LocatorsConfig = null;
		LOG.info("The properties files are unloaded successfully.");
	}

	public void invokeApp(String url, String browser) {
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				ChromeOptions chOptions = new ChromeOptions();
				chOptions.addArguments("--disable-notifications");
				LOG.info("Chrome options are initialized.");
				
				DesiredCapabilities desCap = new DesiredCapabilities();
				desCap.setCapability(ChromeOptions.CAPABILITY, chOptions);
				LOG.info("Desired Capabilities are initialized.");
				
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(chOptions);
				LOG.info("The Chrome Web Driver is instatiated.");
				
			} else if (browser.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();			
			}
			driver.manage().window().maximize();
			LOG.info("The Chrome Web Driver agent is maximized.");
			driver.manage().deleteAllCookies();
			LOG.info("All the cookies are deleted.");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
			LOG.info("The Chrome Web Driver agent's timeouts are initiliazed.");
			driver.get(url);
			recordTest("The given URL '" + url + "' is launched in '" + browser + "' browser successfully.", "PASS");
			LOG.info("The given URL '\" + url + \"' is launched in '\" + browser + \"' browser successfully.");
		} catch (TimeoutException e) {
			recordTest("The specified time got expired. Unable to launch the URL with all webelements.", "FAIL");
			LOG.error("The specified time got expired. Unable to launch the URL with all webelements.");
		} catch (WebDriverException e) {
			recordTest("The given URL '" + url + "' is not launched in '" + browser + "' browser successfully.",
					"FAIL");
			LOG.fatal("The given URL '\" + url + \"' is not launched in '\" + browser + \"' browser successfully.");
		}
	}

	public void enterById(String idValue, String data) {
		try {
			driver.findElementById(idValue).sendKeys(data);
			recordTest("The webelement with an ID: '" + idValue + "' is identified and entered with the data '"	+ data + "' successfully.", "PASS");
			LOG.info("The webelement with an ID: '" + idValue + "' is identified and entered with the data '"	+ data + "' successfully.");
		} catch (NoSuchElementException e) {
			recordTest("The webelement with an ID: '" + idValue + "' is not found.", "FAIL");
			LOG.error("The webelement with an ID: '" + idValue + "' is not found.");
		} catch (ElementNotVisibleException e) {
			recordTest("The webelement with an ID: '" + idValue + "' is not visible.", "FAIL");
			LOG.error("The webelement with an ID: '" + idValue + "' is not visible.");
		} catch (ElementNotInteractableException e) {
			recordTest("The webelement with an ID: '" + idValue + "' is not enabled.", "FAIL");
			LOG.error("The webelement with an ID: '" + idValue + "' is not enabled.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public void enterByXpath(String xpathValue, String data) {
		try {
			driver.findElementByXPath(xpathValue).sendKeys(data);
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is identified and entered with the data '" + data + "' successfully.", "PASS");
			LOG.info("The webelement with the XPATH: '" + xpathValue + "' is identified and entered with the data '" + data + "' successfully.");
		} catch (NoSuchElementException e) {
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is not found.", "FAIL");
			LOG.error("The webelement with the XPATH: '" + xpathValue + "' is not found.");
		} catch (ElementNotVisibleException e) {
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is not visible.", "FAIL");
			LOG.error("The webelement with the XPATH: '" + xpathValue + "' is not visible.");
		} catch (ElementNotInteractableException e) {
			recordTest("The webelement with the XPATH '" + xpathValue + "' is not enabled.", "FAIL");
			LOG.error("The webelement with the XPATH '" + xpathValue + "' is not enabled.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public void enterByCssSelector(String cssValue, String data) {
		try {
			driver.findElementByCssSelector(cssValue).sendKeys(data);
			recordTest("The webelement with the CSS: '" + cssValue + "' is identified and entered with the data '" + data + "' successfully.", "PASS");
			LOG.info("The webelement with the CSS: '" + cssValue + "' is identified and entered with the data '" + data + "' successfully.");
		} catch (NoSuchElementException e) {
			recordTest("The webelement with the CSS: '" + cssValue + "' is not found.", "FAIL");
			LOG.error("The webelement with the CSS: '" + cssValue + "' is not found.");
		} catch (ElementNotVisibleException e) {
			recordTest("The webelement with the CSS: '" + cssValue + "' is not visible.", "FAIL");
			LOG.error("The webelement with the CSS: '" + cssValue + "' is not visible.");
		} catch (ElementNotInteractableException e) {
			recordTest("The webelement with the CSS '" + cssValue + "' is not enabled.", "FAIL");
			LOG.error("The webelement with the CSS '" + cssValue + "' is not enabled.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public void verifyTitle(String title) {
		String strTitleAct = null;
		try {
			strTitleAct = driver.getTitle();
			if (strTitleAct.equals(title)) {
				recordTest("The Actual Title of the page '" + strTitleAct + "' matches with the Expected Title '" + title + "'.", "PASS");
				LOG.info("The Actual Title of the page '" + strTitleAct + "' matches with the Expected Title '" + title + "'.");
			} else {
				recordTest("The Actual Title of the page '" + strTitleAct + "' doesn't matches with the Expected Title '" + title + "'.", "FAIL");
				LOG.error("The Actual Title of the page '" + strTitleAct + "' doesn't matches with the Expected Title '" + title + "'.");
			}
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public void verifyTextById(String idValue, String text) {
		String strTextAct = null;
		try {
			strTextAct = driver.findElementById(idValue).getText();
			if (strTextAct.equals(text)) {
				recordTest("The Actual Text '" + strTextAct + "' from the ID: '" + idValue + "' matches with the Expected Text '" + text + "'.", "PASS");
				LOG.info("The Actual Text '" + strTextAct + "' from the ID: '" + idValue + "' matches with the Expected Text '");
			} else {
				recordTest("The Actual Text '" + strTextAct + "' from the ID: '" + idValue + "' doesn't matches with the Expected Text '" + text + "'.", "FAIL");
				LOG.error("The Actual Text '" + strTextAct + "' from the ID: '" + idValue + "' doesn't matches with the Expected Text '" + text + "'.");
			}
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.error("The application got crashed for unknown error.");
		}
	}

	public void verifyTextByXpath(String xpathValue, String text) {
		String strTextAct = null;
		try {
			strTextAct = driver.findElementByXPath(xpathValue).getText().trim();
			if (strTextAct.equals(text)) {
				recordTest("The Actual Text '" + strTextAct + "' from the XPATH: '" + xpathValue + "' exactly matches with the Expected Text '" + text + "'.", "PASS");
				LOG.info("The Actual Text '" + strTextAct + "' from the XPATH: '" + xpathValue + "' exactly matches with the Expected Text '" + text + "'.");
			} else {
				recordTest("The Actual Text '" + strTextAct + "' from the XPATH: '" + xpathValue + "' doesn't exactly matches with the Expected Text '" + text + "'.", "FAIL");
				LOG.error("The Actual Text '" + strTextAct + "' from the XPATH: '" + xpathValue + "' doesn't exactly matches with the Expected Text '" + text + "'.");
			}
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public void verifyTextByCssSelector(String cssValue, String text) {
		String strTextAct = null;
		try {
			strTextAct = driver.findElementByCssSelector(cssValue).getText();
			if (strTextAct.equals(text)) {
				recordTest("The Actual Text '" + strTextAct + "' from the CSS: '" + cssValue + "' exactly matches with the Expected Text '" + text + "'.", "PASS");
				LOG.info("The Actual Text '" + strTextAct + "' from the CSS: '" + cssValue + "' exactly matches with the Expected Text '" + text + "'.");
			} else {
				recordTest("The Actual Text '" + strTextAct + "' from the CSS: '" + cssValue + "' doesn't exactly matches with the Expected Text '" + text + "'.", "FAIL");
				LOG.error("The Actual Text '" + strTextAct + "' from the CSS: '" + cssValue + "' doesn't exactly matches with the Expected Text '" + text + "'.");
			}
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public void verifyTextContainsByXpath(String xpathValue, String text) {
		String strTextAct = null;
		try {
			strTextAct = driver.findElementByXPath(xpathValue).getText();
			if (strTextAct.contains(text)) {
				recordTest("The Actual Text '" + strTextAct + "' from the XPATH: '" + xpathValue + "' partially matches with the Expected Text '" + text + "'.", "PASS");
				LOG.info("The Actual Text '" + strTextAct + "' from the XPATH: '" + xpathValue + "' partially matches with the Expected Text '" + text + "'.");
			} else {
				recordTest("The Actual Text '" + strTextAct + "' from the XPATH: '" + xpathValue + "' doesn't partially matches with the Expected Text '" + text + "'.", "FAIL");
				LOG.error("The Actual Text '" + strTextAct + "' from the XPATH: '" + xpathValue + "' doesn't partially matches with the Expected Text '" + text + "'.");
			}
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public void clickById(String idValue) {
		try {
			driver.findElementById(idValue).click();
			recordTest("The webelement with an ID: '" + idValue + "' is identified and clicked successfully.", "PASS");
			LOG.info("The webelement with an ID: '" + idValue + "' is identified and clicked successfully.");
		} catch (NoSuchElementException e) {
			recordTest("The webelement with an ID: '" + idValue + "' is not found.", "FAIL");
			LOG.error("The webelement with an ID: '" + idValue + "' is not found.");
		} catch (ElementNotVisibleException e) {
			recordTest("The webelement with an ID: '" + idValue + "' is not visible.", "FAIL");
			LOG.error("The webelement with an ID: '" + idValue + "' is not visible.");
		} catch (ElementNotInteractableException e) {
			recordTest("The webelement with an ID: '" + idValue + "' is not enabled.", "FAIL");
			LOG.error("The webelement with an ID: '" + idValue + "' is not enabled.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public void clickByXpath(String xpathValue) {
		try {
			driver.findElementByXPath(xpathValue).click();
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is identified and clicked successfully.","PASS");
			LOG.info("The webelement with the XPATH: '" + xpathValue + "' is identified and clicked successfully.");
		} catch (NoSuchElementException e) {
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is not found.", "FAIL");
			LOG.error("The webelement with the XPATH: '" + xpathValue + "' is not found.");
		} catch (ElementNotVisibleException e) {
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is not visible.", "FAIL");
			LOG.error("The webelement with the XPATH: '" + xpathValue + "' is not visible.");
		} catch (ElementNotInteractableException e) {
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is not enabled.", "FAIL");
			LOG.error("The webelement with the XPATH: '" + xpathValue + "' is not enabled.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public void clickByCssSelector(String cssValue) {
		try {
			driver.findElementByClassName(cssValue).click();
			recordTest("The webelement with the CSS: '" + cssValue + "' is identified and clicked successfully.","PASS");
			LOG.info("The webelement with the CSS: '" + cssValue + "' is identified and clicked successfully.");
		} catch (NoSuchElementException e) {
			recordTest("The webelement with the CSS: '" + cssValue + "' is not found.", "FAIL");
			LOG.error("The webelement with the CSS: '" + cssValue + "' is not found.");
		} catch (ElementNotVisibleException e) {
			recordTest("The webelement with the CSS: '" + cssValue + "' is not visible.", "FAIL");
			LOG.error("The webelement with the CSS: '" + cssValue + "' is not visible.");
		} catch (ElementNotInteractableException e) {
			recordTest("The webelement with the CSS: '" + cssValue + "' is not enabled.", "FAIL");
			LOG.error("The webelement with the CSS: '" + cssValue + "' is not enabled.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public void clickByLink(String nameValue) {
		try {
			driver.findElementByLinkText(nameValue).click();
			recordTest("The webelement with the LINK: '" + nameValue + "' is identified and clicked successfully.","PASS");
			LOG.info("The webelement with the LINK: '" + nameValue + "' is identified and clicked successfully.");
		} catch (NoSuchElementException e) {
			recordTest("The webelement with the LINK: '" + nameValue + "' is not found.", "FAIL");
			LOG.error("The webelement with the LINK: '" + nameValue + "' is not found.");
		} catch (ElementNotVisibleException e) {
			recordTest("The webelement with the LINK: '" + nameValue + "' is not visible.", "FAIL");
			LOG.error("The webelement with the LINK: '" + nameValue + "' is not visible.");
		} catch (ElementNotInteractableException e) {
			recordTest("The webelement with the LINK: '" + nameValue + "' is not enabled.", "FAIL");
			LOG.error("The webelement with the LINK: '" + nameValue + "' is not enabled.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public void clickByLinkNoSnap(String nameValue) {
		try {
			driver.findElementByLinkText(nameValue).click();
			recordTest("The webelement with the LINK: '" + nameValue + "' is identified and clicked successfully.","PASS");
			LOG.info("The webelement with the LINK: '" + nameValue + "' is identified and clicked successfully.");
		} catch (NoSuchElementException e) {
			recordTest("The webelement with the LINK: '" + nameValue + "' is not found.", "FAIL");
			LOG.error("The webelement with the LINK: '" + nameValue + "' is not found.");
		} catch (ElementNotVisibleException e) {
			recordTest("The webelement with the LINK: '" + nameValue + "' is not visible.", "FAIL");
			LOG.error("The webelement with the LINK: '" + nameValue + "' is not visible.");
		} catch (ElementNotInteractableException e) {
			recordTest("The webelement with the LINK: '" + nameValue + "' is not enabled.", "FAIL");
			LOG.error("The webelement with the LINK: '" + nameValue + "' is not enabled.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public String getTextById(String idValue) {
		String strText = null;
		try {
			strText = driver.findElementById(idValue).getText();
			recordTest("The webelement with an ID: '" + idValue + "' is identified and the available text is '"	+ strText + "'.", "PASS");
			LOG.info("The webelement with an ID: '" + idValue + "' is identified and the available text is '"	+ strText + "'.");
		} catch (NoSuchElementException e) {
			recordTest("The webelement with an ID: '" + idValue + "' is not found.", "FAIL");
			LOG.error("The webelement with an ID: '" + idValue + "' is not found.");
		} catch (ElementNotVisibleException e) {
			recordTest("The webelement with an ID: '" + idValue + "' is not visible.", "FAIL");
			LOG.error("The webelement with an ID: '" + idValue + "' is not visible.");
		} catch (ElementNotInteractableException e) {
			recordTest("The webelement with the XPATH: '" + idValue + "' is enabled.", "FAIL");
			LOG.error("The webelement with the XPATH: '" + idValue + "' is enabled.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
		return strText;
	}

	public String getTextByXpath(String xpathValue) {
		String strText = null;
		try {
			strText = driver.findElementByXPath(xpathValue).getText();
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is identified and the available text is '" + strText + "'.", "PASS");
			LOG.info("The webelement with the XPATH: '" + xpathValue + "' is identified and the available text is '" + strText + "'.");
		} catch (NoSuchElementException e) {
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is not found.", "FAIL");
			LOG.error("The webelement with the XPATH: '" + xpathValue + "' is not found.");
		} catch (ElementNotVisibleException e) {
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is not visible.", "FAIL");
			LOG.error("The webelement with the XPATH: '" + xpathValue + "' is not visible.");
		} catch (ElementNotInteractableException e) {
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is enabled.", "FAIL");
			LOG.error("The webelement with the XPATH: '" + xpathValue + "' is enabled.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
		return strText;
	}

	public String getTextByCssSelector(String cssValue) {
		String strText = null;
		try {
			strText = driver.findElementByXPath(cssValue).getText();
			recordTest("The webelement with the CSS: '" + cssValue + "' is identified and the available text is '" + strText + "'.", "PASS");
			LOG.info("The webelement with the CSS: '" + cssValue + "' is identified and the available text is '" + strText + "'.");
		} catch (NoSuchElementException e) {
			recordTest("The webelement with the CSS: '" + cssValue + "' is not found.", "FAIL");
			LOG.error("The webelement with the CSS: '" + cssValue + "' is not found.");
		} catch (ElementNotVisibleException e) {
			recordTest("The webelement with the CSS: '" + cssValue + "' is not visible.", "FAIL");
			LOG.error("The webelement with the CSS: '" + cssValue + "' is not visible.");
		} catch (ElementNotInteractableException e) {
			recordTest("The webelement with the CSS: '" + cssValue + "' is enabled.", "FAIL");
			LOG.error("The webelement with the CSS: '" + cssValue + "' is enabled.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
		return strText;
	}

	public void selectVisibileTextByXpath(String xpathValue, String data) {
		try {
			Select sel = new Select(driver.findElementByXPath(xpathValue));
			sel.selectByVisibleText(data);
			recordTest("The webelement with an XPATH: '" + xpathValue + "' is identified and selected the text '" + data + "' successfully.", "PASS");
			LOG.info("The webelement with an XPATH: '" + xpathValue + "' is identified and selected the text '" + data + "' successfully.");
		} catch (NoSuchElementException e) {
			recordTest("The webelement with an XPATH: '" + xpathValue + "' is not found.", "FAIL");
			LOG.error("The webelement with an XPATH: '" + xpathValue + "' is not found.");
		} catch (ElementNotVisibleException e) {
			recordTest("The webelement with an XPATH: '" + xpathValue + "' is not visible.", "FAIL");
			LOG.error("The webelement with an XPATH: '" + xpathValue + "' is not visible.");
		} catch (ElementNotSelectableException e) {
			recordTest("The webelement with an XPATH: '" + xpathValue + "' is not enabled.", "FAIL");
			LOG.error("The webelement with an XPATH: '" + xpathValue + "' is not enabled.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public void selectIndexByXpath(String xpathValue, int data) {
		try {
			Select sel = new Select(driver.findElementByXPath(xpathValue));
			sel.selectByIndex(data);
			recordTest("The webelement with an XPATH: '" + xpathValue + "' is identifed and selected the index '" + data + "' successfully.", "PASS");
			LOG.info("The webelement with an XPATH: '" + xpathValue + "' is identifed and selected the index '" + data + "' successfully.");
		} catch (NoSuchElementException e) {
			recordTest("The webelement with an XPATH: '" + xpathValue + "' is not found.", "FAIL");
			LOG.error("The webelement with an XPATH: '" + xpathValue + "' is not found.");
		} catch (ElementNotVisibleException e) {
			recordTest("The webelement with an XPATH: '" + xpathValue + "' is not visible", "FAIL");
			LOG.error("The webelement with an XPATH: '" + xpathValue + "' is not visible");
		} catch (ElementNotSelectableException e) {
			recordTest("The webelement with an XPATH: '" + xpathValue + "' is not enabled.", "FAIL");
			LOG.error("The webelement with an XPATH: '" + xpathValue + "' is not enabled.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public void selectValueByXpath(String xpathValue, String data) {
		try {
			Select sel = new Select(driver.findElementByXPath(xpathValue));
			sel.selectByValue(data);
			recordTest("The webelement with an XPATH: '" + xpathValue + "' is identifed and selected the index '" + data + "' successfully.", "PASS");
			LOG.info("The webelement with an XPATH: '" + xpathValue + "' is identifed and selected the index '" + data + "' successfully.");
		} catch (NoSuchElementException e) {
			recordTest("The webelement with an XPATH: '" + xpathValue + "' is not found.", "FAIL");
			LOG.error("The webelement with an XPATH: '" + xpathValue + "' is not found.");
		} catch (ElementNotVisibleException e) {
			recordTest("The webelement with an XPATH: '" + xpathValue + "' is not visible", "FAIL");
			LOG.error("The webelement with an XPATH: '" + xpathValue + "' is not visible");
		} catch (ElementNotSelectableException e) {
			recordTest("The webelement with an XPATH: '" + xpathValue + "' is not enabled.", "FAIL");
			LOG.error("The webelement with an XPATH: '" + xpathValue + "' is not enabled.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public void switchToParentWindow() {
		String strWinsName = null;
		boolean blnFound = false;
		try {
			Set<String> allWins = driver.getWindowHandles();
			Iterator<String> it = allWins.iterator();
			it.next();
			for (String eachWins : allWins) {
				driver.switchTo().window(eachWins);
				blnFound = true;
				break;
			}
			if (blnFound) {
				recordTest("The latest window (" + strWinsName + ") is focused successfully.", "PASS");
				LOG.info("The latest window (" + strWinsName + ") is focused successfully.");
			} else {
				recordTest("The parent window is not found.", "FAIL");
				LOG.error("The parent window is not found.");
			}
		} catch (NoSuchWindowException e) {
			recordTest("The target window is unavailable so the window switch is unsuccessful.", "FAIL");
			LOG.error("The target window is unavailable so the window switch is unsuccessful.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public void switchToLastWindow() {
		String strWinsName = null;
		boolean blnFound = false;
		try {
			Set<String> allWins = driver.getWindowHandles();
			for (String eachWins : allWins) {
				driver.switchTo().window(eachWins);
				strWinsName = eachWins;
				blnFound = true;
			}
			if (blnFound) {
				recordTest("The latest window (" + strWinsName + ") is focused successfully.", "PASS");
				LOG.info("The latest window (" + strWinsName + ") is focused successfully.");
			} else {
				recordTest("The parent window is not found.", "FAIL");
				LOG.error("The parent window is not found.");
			}
		} catch (NoSuchWindowException e) {
			recordTest("The target window is unavailable so the window switch is unsuccessful.", "FAIL");
			LOG.error("The target window is unavailable so the window switch is unsuccessful.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public void acceptAlert() {
		try {
			driver.switchTo().alert().accept();
			recordTest("The alert has been accepted successfully.", "PASS");
			LOG.info("The alert has been accepted successfully.");
		} catch (NoAlertPresentException e) {
			recordTest("There is not alert displayed to handle.", "FAIL");
			LOG.error("There is not alert displayed to handle.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public void dismissAlert() {
		try {
			driver.switchTo().alert().dismiss();
			recordTest("The alert has been dismissed successfully.", "PASS");
			LOG.info("The alert has been dismissed successfully.");
		} catch (NoAlertPresentException e) {
			recordTest("There is not alert displayed to handle.", "FAIL");
			LOG.error("There is not alert displayed to handle.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public String getAlertText() {
		String strAlertText = null;
		try {
			strAlertText = driver.switchTo().alert().getText();
			recordTest("The text in the displayed alert is '" + strAlertText + "'.", "PASS");
			LOG.info("The text in the displayed alert is '" + strAlertText + "'.");
		} catch (NoAlertPresentException e) {
			recordTest("There is not alert displayed to handle.", "FAIL");
			LOG.error("There is not alert displayed to handle.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
		return strAlertText;
	}

	public String takeSnap() {
		String snapShot = System.getProperty("user.dir") + "\\Reports\\Snapshots\\"
				+ new SimpleDateFormat("YYYYMMddhhmmss").format(new Date()) + ".PNG";
		try {
			File flSource = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File flDest = new File(snapShot);
			FileUtils.copyFile(flSource, flDest);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage().toString());
			LOG.error(e.getMessage().toString());
		} catch (IOException e) {
			System.out.println(e.getMessage().toString());
			LOG.error(e.getMessage().toString());
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			LOG.error(e.getMessage().toString());
		}
		return snapShot;
	}

	public void closeBrowser() {
		try {
			driver.close();
			LOG.info("The current driver agent is closed successfully.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public void closeAllBrowsers() {
		try {
			if (driver != null) {
				driver.quit();
				LOG.info("All the driver agents are closed successfully.");
			}
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public List<WebElement> getDataFromTableByXpath(String xpathValue) {
		List<WebElement> rowDetails = null;
		try {
			rowDetails = driver.findElementsByXPath(xpathValue);
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is identified and the available row count is '" + rowDetails.size() + "'.", "PASS");
			LOG.info("The webelement with the XPATH: '" + xpathValue + "' is identified and the available row count is '" + rowDetails.size() + "'.");
		} catch (NoSuchElementException e) {
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is not found.", "FAIL");
			LOG.error("The webelement with the XPATH: '" + xpathValue + "' is not found.");
		} catch (ElementNotVisibleException e) {
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is not visible.", "FAIL");
			LOG.error("The webelement with the XPATH: '" + xpathValue + "' is not visible.");
		} catch (ElementNotInteractableException e) {
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is enabled.", "FAIL");
			LOG.error("The webelement with the XPATH: '" + xpathValue + "' is enabled.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
		return rowDetails;
	}

	public void isSearchPatientAvail(List<WebElement> patientDetails, String pName, String avail) {
		try {
			if (patientDetails.size() > 1) {
				recordTest("More patient details are identifed. Please refine the search text.", "FAIL");
				LOG.error("More patient details are identifed. Please refine the search text.");
			} else {
				String patDetail = patientDetails.get(0).getText();
				if (patDetail.equals("No matching records found") && avail.equalsIgnoreCase("No Data")) {
					recordTest("No matching records found for the given patient. " + pName, "PASS");
					LOG.info("No matching records found for the given patient. " + pName);
				} else if (patDetail.equals("No matching records found") && avail.equalsIgnoreCase("Data")) {
					recordTest("No matching records found for the given patient. " + pName, "FAIL");
					LOG.error("No matching records found for the given patient. " + pName);
				} else if (patDetail.contains(pName)) {
					recordTest("The patient details is available in the system. " + pName, "PASS");
					LOG.info("The patient details is available in the system. " + pName);
				}
			}
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public void clickRowInTableByXpath(String xpathValue, int rowNum) {
		try {
			List<WebElement> rowDetails = driver.findElementsByXPath(xpathValue);
			rowDetails.get(rowNum).click();
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is identified and clicked successfully.","PASS");
			LOG.info("The webelement with the XPATH: '" + xpathValue + "' is identified and clicked successfully.");
		} catch (NoSuchElementException e) {
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is not found.", "FAIL");
			LOG.error("The webelement with the XPATH: '" + xpathValue + "' is not found.");
		} catch (ElementNotVisibleException e) {
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is not visible.", "FAIL");
			LOG.error("The webelement with the XPATH: '" + xpathValue + "' is not visible.");
		} catch (ElementNotInteractableException e) {
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is enabled.", "FAIL");
			LOG.error("The webelement with the XPATH: '" + xpathValue + "' is enabled.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}

	public void waitTillElementDisappearByXpath(String xpathValue, int timeOutInSeconds) {
		try {
			WebDriverWait waitDriver = new WebDriverWait(driver, timeOutInSeconds);
			waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathValue)));
			waitDriver.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathValue)));
		} catch (NoSuchElementException e) {
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is not found.", "FAIL");
			LOG.error("The webelement with the XPATH: '" + xpathValue + "' is not found.");
		} catch (ElementNotVisibleException e) {
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is not visible.", "FAIL");
			LOG.error("The webelement with the XPATH: '" + xpathValue + "' is not visible.");
		} catch (ElementNotInteractableException e) {
			recordTest("The webelement with the XPATH: '" + xpathValue + "' is enabled.", "FAIL");
			LOG.error("The webelement with the XPATH: '" + xpathValue + "' is enabled.");
		} catch (TimeoutException e) {
			recordTest("Timeout Exception. Please try again.", "FAIL");
			LOG.error("Timeout Exception. Please try again.");
		} catch (WebDriverException e) {
			recordTest("The application got crashed for unknown error.", "FAIL");
			LOG.fatal("The application got crashed for unknown error.");
		}
	}
}
