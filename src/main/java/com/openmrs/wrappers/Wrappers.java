package com.openmrs.wrappers;

import java.util.List;

import org.openqa.selenium.WebElement;

/**
 * @author Kani
 *
 */
public interface Wrappers {

	/**
	 * invokeApp: This method will invoke the specified URL in the mentioned Browser
	 * @param browser: Chrome / Firefox / Safari / Edge / Internet Explorer
	 * @param url: Application URL
	 */
	public void invokeApp(String browser, String url); 
	
	
	/**
	 * @param idValue
	 * @param data
	 */
	public void enterById(String idValue, String data); 
	public void enterByXpath(String xpathValue, String data); 
	public void enterByCssSelector(String cssValue, String data);
	
	public void verifyTitle(String title); 
	
	public void verifyTextById(String idValue, String text); 
	public void verifyTextByXpath(String xpathValue, String text); 
	public void verifyTextByCssSelector(String cssValue, String text);
	public void verifyTextContainsByXpath(String xpathValue, String text); 
	
	public void clickById(String idValue);
	public void clickByXpath(String xpathValue);
	public void clickByCssSelector(String cssValue); 
	
	public void clickByLink(String nameValue); 
	public void clickByLinkNoSnap(String nameValue); 

	public String getTextById(String idValue); 
	public String getTextByXpath(String xpathValue); 
	public String getTextByCssSelector(String cssValue);
	
	public void selectVisibileTextByXpath(String xpathValue, String data); 
	public void selectIndexByXpath(String xpathValue, int data); 
	public void selectValueByXpath(String xpathValue, String data); 
	
	public void switchToParentWindow(); 
	public void switchToLastWindow(); 
	
	public void acceptAlert(); 
	public void dismissAlert(); 
	public String getAlertText(); 
	
	public void closeBrowser(); 
	public void closeAllBrowsers(); 
	
	public List<WebElement> getDataFromTableByXpath(String xpathValue);
	public void isSearchPatientAvail(List<WebElement> patientDetails, String pName, String avail);
	public void waitTillElementDisappearByXpath(String xpathValue, int timeOutInSeconds);
}
