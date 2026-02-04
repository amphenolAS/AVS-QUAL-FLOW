package com.avsQual.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.avsQual.utility.TestUtilities;
import com.avsQual.base.BaseClass;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;

public class CerHostPage extends BaseClass {

	
	private WindowsDriver<WindowsElement> driver;
	TestUtilities tu = new TestUtilities();
	
	//elements
	WebElement AppTitle = null;
	WebElement File=null;
	WebElement Connect=null;
	WebElement 	IPbox=null;
	WebElement ok=null;
	
	
	public CerHostPage(WindowsDriver<WindowsElement> driver) throws IOException {
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
		File=driver.findElementByName("File");
		Connect=driver.findElementByAccessibilityId("112");
		IPbox=driver.findElementByClassName("SysIPAddress32");
		ok=driver.findElementByAccessibilityId("1");
	}
	
	
	// Release memory
		public void resetWebElements() {
			AppTitle = null;
			File=null;
			Connect=null;
			IPbox=null;
			ok=null;
		}

		
		// Verify theTitle presence...")
		public boolean AppTitlePresence() {
			WebElement AppTitle = driver.findElementByAccessibilityId("TitleBar");
			return IsElementVisibleStatus(AppTitle);
		}

		public String getTitle() {
			String windowTitle = driver.getTitle();
			return windowTitle;
		}
		
		public void clickon_file() {
			File=driver.findElementByName("File");
			click_On(File);
		}

		
		public void clickon_connect() {
			Connect=driver.findElementByAccessibilityId("112");
			click_On(Connect);
		}
		
		
		
		
		//SysIPAddress32
		
		public void EnterIP(String IP) {
			IPbox=driver.findElementByClassName("SysIPAddress32");
			ClearText(IPbox);
			enterText(IPbox, IP);
		}
		
		public IPPage clickOK() throws IOException, InterruptedException {
			ok=driver.findElementByAccessibilityId("1");
			click_On(ok);
			Thread.sleep(2000);
			return new IPPage();

		}
}
