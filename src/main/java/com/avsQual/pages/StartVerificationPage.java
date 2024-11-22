package com.avsQual.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.avsQual.base.BaseClass;
import com.avsQual.utility.TestUtilities;

public class StartVerificationPage extends BaseClass{
	
	//Select AVS Page Elements
	WebElement selectAll = null;
	WebElement initVer = null;
	
	TestUtilities tu = new TestUtilities();
	public void initialization()
	{
		selectAll = driver.findElementByAccessibilityId("btnVerCal");
		initVer = driver.findElementByAccessibilityId("btnStVerify");
		
	}
	

	public StartVerificationPage() throws IOException {
		super();
		initialization();
	}
	
	//Release Memory
		public void resetWebElements()
		{
			selectAll = null;
			initVer = null;
		}
		
		public void click_SelectAllBtn() throws InterruptedException
		{
			Thread.sleep(2000);
			clickOn(selectAll);
		}
		
//		public SensCaliberationPage click_initCappppBtn() throws IOException
//		{
//			clickOn(initQual);
//			return new SensCaliberationPage();
//		}
		
		public SensVerificationPage click_initVer() throws IOException
		{
			clickOn(initVer);
			return new SensVerificationPage();
		}


		
}
