package com.avsQual.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
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
			Thread.sleep(1000);
			WebElement yes = driver.findElementByName("Yes");
			clickOn(yes);
			Thread.sleep(1000);
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
		
		public MainHubPage clickBackButton() throws IOException, InterruptedException
		{
			WebElement back = driver.findElementByAccessibilityId("ArrowGlyph");
			clickOn(back);
			Thread.sleep(2000);
			WebElement noBtn = driver.findElementByAccessibilityId("Button1");
			clickOn(noBtn);
			return new MainHubPage();
		}
		
		
		public MainHubPage clickBackButton1() throws IOException {
		WebElement backBtn = driver.findElementByAccessibilityId("ArrowGlyph");

	    WebDriverWait wait = new WebDriverWait(driver, 30);

	    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(backBtn));
	    element.click();
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
				int explicitWaitTimeout = 20000; 
		        driver.manage().timeouts().implicitlyWait(explicitWaitTimeout, TimeUnit.SECONDS);
				WebElement imgCheckSetpoint = driver.findElementByAccessibilityId("imgCheckSetpointDone");//
				status = IsElementVisibleStatus(imgCheckSetpoint);
			}
			catch (Exception e) {
				e.getMessage();
			}
			
			return status;
			    	
//			    	try
//			    	{
//			    		WebElement imgCheckSetpoint = driver.findElementByAccessibilityId("imgCheckSetpointDone");
//			    		waitForElementVisible(imgCheckSetpoint);
//			    	}
//			    	catch (Exception e) {
//						e.printStackTrace();
//					}
		}
		
		
		
		public boolean waitForimgLowSetpoint() throws InterruptedException
		{
			boolean status = false;
			try
			{
				int explicitWaitTimeout = 20000; 
		        driver.manage().timeouts().implicitlyWait(explicitWaitTimeout, TimeUnit.SECONDS);
				WebElement imgCheckSetpoint = driver.findElementByAccessibilityId("imgLowSetpointDone");//imgLowSetpointDone
				status = IsElementVisibleStatus(imgCheckSetpoint);
			}
			catch (Exception e) {
				e.getMessage();
			}
			
			return status;
			    	
//			    	try
//			    	{
//			    		WebElement imgCheckSetpoint = driver.findElementByAccessibilityId("imgCheckSetpointDone");
//			    		waitForElementVisible(imgCheckSetpoint);
//			    	}
//			    	catch (Exception e) {
//						e.printStackTrace();
//					}
		}
		
		
		
		public void click_CancelBtn1() throws InterruptedException
		{
			clickOn(cancel);
			Thread.sleep(1000);
			WebElement yes = driver.findElementByName("Yes");
			clickOn(yes);
			Thread.sleep(3000);
			tu.click_YesBtn_popup();
			Thread.sleep(1000);
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			//tu.click_YesBtn_popup();
		}
		
		
		
		
		
		public void click_DeleteButton()
		{
			WebElement deleteButton = driver.findElementByAccessibilityId("btnDelete");
			clickOn(deleteButton);
		}
		
		
		// Click the Yes button of the popup message
		public void click_YesBtn() throws InterruptedException {
			int explicitWaitTimeout = 2000; 
	        driver.manage().timeouts().implicitlyWait(explicitWaitTimeout, TimeUnit.SECONDS);
	        WebElement yes = driver.findElementByName("Yes");
			clickOn(yes);
			Thread.sleep(1000);
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			tu.click_YesBtn_popup();
			
			
		}
		
		
		// Click the Yes button of the popup message 
		public void click_YesBtn_popup() throws InterruptedException {
			WebElement Yes_Btn = driver.findElementByAccessibilityId("Button1");
			Thread.sleep(2000);
			clickOn(Yes_Btn);
			Thread.sleep(2000);
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			Thread.sleep(1000);
		}




}
