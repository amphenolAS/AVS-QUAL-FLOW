package com.avsQual.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.avsQual.base.BaseClass;
import com.avsQual.utility.TestUtilities;

public class StartCaliberationPage extends BaseClass{
	
	//Select AVS Page Elements
	WebElement selectAll = null;
	WebElement initQual = null;
	
	TestUtilities tu = new TestUtilities();
	public void initialization()
	{
		selectAll = driver.findElementByAccessibilityId("btnVerCal");
		initQual = driver.findElementByAccessibilityId("btnStCal");
		
	}
	

	public StartCaliberationPage() throws IOException {
		super();
		initialization();
	}
	
	//Release Memory
		public void resetWebElements()
		{
			selectAll = null;
			initQual = null;
		}
		
		public void click_SelectAllBtn()
		{
			clickOn(selectAll);
		}
		
		public SensCaliberationPage click_initCappppBtn() throws IOException
		{
			clickOn(initQual);
			return new SensCaliberationPage();
		}
}
