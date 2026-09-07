package com.avsQual.pages;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.avsQual.base.BaseClass;
import com.avsQual.utility.TestUtilities;

public class SelectAVSPage extends BaseClass{
	
	//Select AVS Page Elements
	WebElement SlctAVSTitle = null;
	WebElement discoverAVS = null;
	WebElement conFigWifi = null;
	WebElement ipTxtBox = null;
	WebElement addBtn = null;
	WebElement removeBtn = null;
	WebElement conctBtn = null;
	
	TestUtilities tu = new TestUtilities();
	public void initialization()
	{
		SlctAVSTitle = driver.findElementByAccessibilityId("tbSeSelectIO");
		discoverAVS = driver.findElementByAccessibilityId("DiscoverIOBoxButton");
		conFigWifi = driver.findElementByAccessibilityId("ConfigureWifi");
		ipTxtBox = driver.findElementByAccessibilityId("txtIOBox");
		addBtn = driver.findElementByAccessibilityId("btnIOIPconnect");
		removeBtn = driver.findElementByAccessibilityId("btnRemoveIP");
		conctBtn = driver.findElementByAccessibilityId("btnConnect");
	}
	

	public SelectAVSPage() throws IOException {
		super();
		initialization();
	}
	
	//Release Memory
	public void resetWebElements()
	{
		SlctAVSTitle = null;
		discoverAVS = null;
		conFigWifi = null;
		ipTxtBox = null;
		addBtn = null;
		removeBtn = null;
		conctBtn = null;
	}
	
	//enter text into IP Text Box
	public void enterTxt_IntoIpTxtBox(String ipadd)
	{
//		clickOn(ipTxtBox);
//		ClearText(ipTxtBox);
		enterText(ipTxtBox, ipadd);
	} 
	public void clickt_IntoIpTxtBox()
	{
		clickOn(ipTxtBox);
	} 
	//click on Add Button
	public void click_AddBtn()
	{
		clickOn(addBtn);
	}
	
/*	public StartCaliberationPage click_CnnctBtn() throws InterruptedException, IOException
	{
		Thread.sleep(3000);
		clickOn(conctBtn);
		try
		{Thread.sleep(2000);
			WebElement Yes_Btn = driver.findElementByAccessibilityId("Button1");
			clickOn(Yes_Btn);
		}
		catch (Exception e) {
			try {
		        Thread.sleep(3000);

		        WebElement yesBtn = driver.findElementByAccessibilityId("Button1");

		        click_On(yesBtn);
		    }
		    catch (Exception ex) {
		        System.out.println("Yes button not found even after retry");
		    }
			//e.getMessage();
		}
		Thread.sleep(15000);
		int explicitWaitTimeout = 100; 
        driver.manage().timeouts().implicitlyWait(explicitWaitTimeout, TimeUnit.SECONDS);

		return new StartCaliberationPage();
	}
	*/
	
	public StartCaliberationPage click_CnnctBtn() throws InterruptedException, IOException
	{
	    Thread.sleep(4000);
	    clickOn(conctBtn);
TimeUnit.MINUTES.sleep(1); // Wait for 4 seconds before attempting to find the "Yes" button
	    try
	    {

	        WebElement yesBtn = driver.findElement(By.name("Yes"));

	        Thread.sleep(2000);

	        click_On(yesBtn);

	        System.out.println("Yes button clicked successfully");
	    }
	    catch (Exception e)
	    {
	        System.out.println("First attempt failed: " + e.getMessage());

	        try
	        {
	            Thread.sleep(3000);

	            WebElement yesBtn = driver.findElement(By.name("Yes"));

	            click_On(yesBtn);

	            System.out.println("Yes button clicked successfully on retry");
	        }
	        catch (Exception ex)
	        {
	            System.out.println("Yes button not found after retry");
	            ex.printStackTrace();
	        }
	    }

	    Thread.sleep(15000);

	    int explicitWaitTimeout = 600;
	    driver.manage().timeouts().implicitlyWait(
	        explicitWaitTimeout, TimeUnit.SECONDS);

	    return new StartCaliberationPage();
	}
	
	/*
	//Click on connect Button  
	public QualificationStudyPage click_ConnectBtn() throws InterruptedException, IOException
	{
		Thread.sleep(4000);
		clickOn(conctBtn);
		
		try
		{
			Thread.sleep(4000);
			WebElement yesBtn = driver.findElementByAccessibilityId("Button1");
		//	waitForElementLoad(yesBtn, 10000);
			Thread.sleep(8000);
			click_On(yesBtn);
		}
		catch (Exception e) {
			try {
		        Thread.sleep(3000);

		        WebElement yesBtn = driver.findElementByAccessibilityId("Button1");

		        click_On(yesBtn);
		    }
		    catch (Exception ex) {
		        System.out.println("Yes button not found even after retry");
		    }
		}
		
		Thread.sleep(15000);
		int explicitWaitTimeout = 600; 
        driver.manage().timeouts().implicitlyWait(explicitWaitTimeout, TimeUnit.SECONDS);

		return new QualificationStudyPage();
	}*/
	
	
	public QualificationStudyPage click_ConnectBtn() throws InterruptedException, IOException
	{
	    Thread.sleep(4000);
	    clickOn(conctBtn);
TimeUnit.MINUTES.sleep(1); // Wait for 4 seconds before attempting to find the "Yes" button
	    try
	    {
	        WebElement yesBtn = driver.findElement(By.name("Yes"));

	        Thread.sleep(2000);

	        click_On(yesBtn);

	        System.out.println("Yes button clicked successfully");
	    }
	    catch (Exception e)
	    {
	        System.out.println("First attempt failed: " + e.getMessage());

	        try
	        {
	            Thread.sleep(3000);

	            WebElement yesBtn = driver.findElement(By.name("Yes"));

	            click_On(yesBtn);

	            System.out.println("Yes button clicked successfully on retry");
	        }
	        catch (Exception ex)
	        {
	            System.out.println("Yes button not found after retry");
	            ex.printStackTrace();
	        }
	    }

	    Thread.sleep(15000);

	    int explicitWaitTimeout = 600;
	    driver.manage().timeouts().implicitlyWait(
	        explicitWaitTimeout, TimeUnit.SECONDS);

	    return new QualificationStudyPage();
	}
	
	
	
	public StartVerificationPage click_CnnctBtnVer() throws InterruptedException, IOException
	{
		Thread.sleep(3000);
		clickOn(conctBtn);
		TimeUnit.MINUTES.sleep(1);
		try
		{
			WebElement Yes_Btn = driver.findElementByAccessibilityId("Button1");
			clickOn(Yes_Btn);
		}
		catch (Exception e) {
			//e.getMessage();
			try {
		        Thread.sleep(3000);

		        WebElement yesBtn = driver.findElementByAccessibilityId("Button1");

		        click_On(yesBtn);
		    }
		    catch (Exception ex) {
		        System.out.println("Yes button not found even after retry");
		    }
		}
		Thread.sleep(15000);
		int explicitWaitTimeout = 100;
        driver.manage().timeouts().implicitlyWait(explicitWaitTimeout, TimeUnit.SECONDS);
 
		return new StartVerificationPage();
	}
 
	public String fetchTxt_FromAVSUnit(String ip)
	{
		List<WebElement> ipAdd = driver.findElementsByAccessibilityId("textBlockIpAddress");
		
		String value="";
		for(WebElement AVSIP:ipAdd)
		{
			value = FetchText(AVSIP);
			if(value.equals(ip))
			{
				waitForElementLoad(AVSIP, 1000);
				break;
			}
		}
		return value;
	}
	//Select Required AVS
	public void select_AVS(String ip) throws InterruptedException
	{
		List<WebElement> ipAdd = driver.findElementsByAccessibilityId("textBlockIpAddress");
		
		for(WebElement AVSIP:ipAdd)
		{
			if(ip.equals(FetchText(AVSIP)))
			{
				waitForElementLoad(AVSIP, 1000);
				clickOn(AVSIP);
				break;
			}
			
		}
	}
	
	public void click_USBDocking()
	{
		WebElement usbDocking = driver.findElementByAccessibilityId("textUsbIp");
		clickOn(usbDocking);
	}
	
}
