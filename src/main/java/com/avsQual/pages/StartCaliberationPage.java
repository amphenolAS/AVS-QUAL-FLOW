package com.avsQual.pages;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.avsQual.base.BaseClass;
import com.avsQual.utility.TestUtilities;

public class StartCaliberationPage extends BaseClass{
	
	//Select AVS Page Elements
	WebElement selectAll = null;
	WebElement initQual = null;
	WebElement backBtn = null;
	
	TestUtilities tu = new TestUtilities();
	public void initialization()
	{
		selectAll = driver.findElementByAccessibilityId("btnVerCal");
		initQual = driver.findElementByAccessibilityId("btnStCal");
		backBtn = driver.findElementByAccessibilityId("ArrowGlyph");
		
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
			backBtn = null;
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
		
		
		public MainHubPage click_BackBtn() throws IOException, InterruptedException
	    {
	    	Thread.sleep(2000);
	    	clickOn(backBtn);
	    	return new MainHubPage();
	    }
		


		}


		
		
