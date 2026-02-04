package com.avsQual.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.avsQual.base.BaseClass;
import com.avsQual.utility.TestUtilities;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;

public class IPPage extends BaseClass {
	private WindowsDriver<WindowsElement> driver;
	TestUtilities tu = new TestUtilities();
	
	//elements
	WebElement AppTitle = null;
	
	
	
	public IPPage() throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * public CerHostPage() throws IOException { super(); // TODO Auto-generated
	 * constructor stub }
	 */

	
	private void initElements() {
		// Page Element definition
		AppTitle = driver.findElementByAccessibilityId("TitleBar");
		
	}
	
	
	// Release memory
		public void resetWebElements() {
			AppTitle = null;
			
		}

		
		// Verify theTitle presence...")
		public boolean AppTitlePresence() {
			WebElement AppTitle = driver.findElementByAccessibilityId("TitleBar");
			return IsElementVisibleStatus(AppTitle);
		}


}
