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
	//Click on connect Button
	public QualificationStudyPage click_ConnectBtn() throws InterruptedException, IOException
	{
		Thread.sleep(2000);
		clickOn(conctBtn);
		
			//WebElement yesBtn = driver.findElementByAccessibilityId("Popup Window").findElement(By.name("Yes"));
			WebElement yesBtn = driver.findElementByAccessibilityId("Button1");
			Thread.sleep(1000);
			clickOn(yesBtn);
			//tu.click_YesBtn_popup();
		
		try
		{
			WebElement ethernet = driver.findElementByAccessibilityId("ConnectEthernetOption");
			Thread.sleep(1000);
			clickOn(ethernet);
		}
		catch (Exception e) {
			e.getMessage();
		}
		
		Thread.sleep(15000);
		
		return new QualificationStudyPage();
	}
	public String fetchTxt_FromAVSUnit(String ip) throws InterruptedException
	{
		List<WebElement> ipAdd = driver.findElementsByAccessibilityId("textBlockIpAddress");
		
		String value="";
		for(WebElement AVSIP:ipAdd)
		{
			value = FetchText(AVSIP);
			if(value.equals(ip))
			{
				Thread.sleep(1000);
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
				Thread.sleep(1000);
				clickOn(AVSIP);
				break;
			}
			
		}
	}
	
}
