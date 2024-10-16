package com.avsQual.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;

import com.avsQual.base.BaseClass;
import com.avsQual.utility.TestUtilities;

public class SensCaliberationPage extends BaseClass{
	

	//Select AVS Page Elements
	WebElement start = null;
	WebElement cancel = null;
	WebElement saveStudy = null;
	
	TestUtilities tu = new TestUtilities();
	public void initialization()
	{
		start = driver.findElementByAccessibilityId("btnStartCalib");
		cancel = driver.findElementByAccessibilityId("btnStopCalib");
		saveStudy = driver.findElementByAccessibilityId("btnSaveStudy");
		
	}
	

	public SensCaliberationPage() throws IOException {
		super();
		initialization();
	}
	
	//Release Memory
		public void resetWebElements()
		{
			start = null;
			cancel = null;
			saveStudy = null;
		}
		
		public void click_StartBtn() throws InterruptedException
		{
			clickOn(start);
			WebElement yes = driver.findElementByName("Yes");
			clickOn(yes);
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			tu.click_YesBtn_popup();
		}
		
		public void click_CancelBtn()
		{
			clickOn(cancel);
		}
		public void click_SaveStudyBtn() throws InterruptedException
		{
			Thread.sleep(4000);
			clickOn(saveStudy);
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			
			Thread.sleep(1000);
			WebElement okBtn = driver.findElementByAccessibilityId("SaveStudyOKButton");
			clickOn(okBtn);
		}
		
		public MainHubPage clickBackButton() throws IOException
		{
			WebElement back = driver.findElementByAccessibilityId("ArrowGlyph");
			clickOn(back);
			WebElement noBtn = driver.findElementByAccessibilityId("Button1");
			clickOn(noBtn);
			return new MainHubPage();
		}
		public boolean isSaveButtonEnableStatus() {
			return IsElementEnabledStatus(saveStudy);
		}
		
		public boolean waitForimgCheckSetpoint() throws InterruptedException
		{
			boolean status = false;
			try
			{
				int explicitWaitTimeout = 50000; 
		        driver.manage().timeouts().implicitlyWait(explicitWaitTimeout, TimeUnit.SECONDS);
				WebElement imgCheckSetpoint = driver.findElementByAccessibilityId("imgCheckSetpointDone");
				status = IsElementVisibleStatus(imgCheckSetpoint);
			}
			catch (Exception e) {
				e.getMessage();
			}
			
			return status;
		}



}
