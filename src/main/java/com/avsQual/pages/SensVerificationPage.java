package com.avsQual.pages;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avsQual.base.BaseClass;
import com.avsQual.utility.TestUtilities;

public class SensVerificationPage extends BaseClass{
	

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
	

	public SensVerificationPage() throws IOException {
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
		{Thread.sleep(2000);
			clickOn(start);
			Thread.sleep(1000);
			WebElement yes = driver.findElementByName("Yes");
			clickOn(yes);
			Thread.sleep(1000);
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			//tu.click_YesBtn_popup();
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
				int explicitWaitTimeout = 20000; 
		        driver.manage().timeouts().implicitlyWait(explicitWaitTimeout, TimeUnit.SECONDS);
				WebElement imgCheckSetpoint = driver.findElementByAccessibilityId("imgCheckSetpointDone");
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
		
		public void click_DeleteButton()
		{
			WebElement deleteButton = driver.findElementByAccessibilityId("btnDelete");
			clickOn(deleteButton);
		}


		/*public boolean isAVSBusyMsgDisplayed() {
			try{
				Thread.sleep(4000);
				return driver.findElementByName("Unable to Start Study. System is busy. Try restarting AVS or sending setup again.").isDisplayed();
				
			} catch (Exception e) {
				return false;	
			}
		}*/
		
		public boolean isAVSBusyMsgDisplayed() throws InterruptedException
		{
			boolean status = false;
			try
			{
				int explicitWaitTimeout = 5; 
		        driver.manage().timeouts().implicitlyWait(explicitWaitTimeout, TimeUnit.SECONDS);
		       // WebElement saveBtn = driver.findElementByAccessibilityId("btnSaveStudyFile");
		        WebElement Unablemsg = driver.findElementByName("Unable to Start Study. System is busy. Try restarting AVS or sending setup again.");
		        //"Unable to Start Study. System is busy. Try restarting AVS or sending setup again."
				status = IsElementVisibleStatus(Unablemsg);
			}
			catch (Exception e) {
				e.getMessage();
			}
			
			return status;
			
		}
		
	
		
		public HardwarePage click_HardwareBtn() throws IOException {
			WebElement Hardwarebtn =driver.findElementByAccessibilityId("btCSHardwareConnection");
			clickOn(Hardwarebtn);
			return new HardwarePage();
			
		}
		
		
		public String get_AlertMsg_text() {
			WebElement Msg = driver.findElementByAccessibilityId("dispalyMessageTextBlock");//dispalyMessageTextBlock
				return FetchText(checkingVisibilityOfElement(Msg, 5));
		}
		
		
		
		
		
		

		 // ==========================================================
		 // LIVE MESSAGE EVENTS
		 // ==========================================================

		 /**
		  * Returns all TextBlock elements available in the Live Message box.
		  */
		 public List<WebElement> getLiveMessageEvents()
		 {
		     List<WebElement> messageBox =
		             driver.findElementsByAccessibilityId( "PART_ScrollViewer"  );
		           

		     if (messageBox.isEmpty())
		     {
		         throw new RuntimeException( "Live Message box not found");
		         
		     }

		     List<WebElement> textBlocks =
		             messageBox.get(0).findElements( By.className("TextBlock")  );
		           

		     return textBlocks;
		 }


		 // ==========================================================
		 // GET TEXT FROM EVENT
		 // ==========================================================

		 /**
		  * Reads the Name property directly.
		  *
		  * Do NOT use FetchText() here because some TextBlocks
		  * are not visible and FetchText() waits for visibility.
		  */
		 public String getEventText(WebElement element)
		 {
		     try
		     {
		         String text = element.getAttribute("Name");

		         if (text != null &&
		             !text.trim().isEmpty())
		         {
		             return text.trim();
		         }
		     }
		     catch (Exception e)
		     {
		         System.out.println(
		                 "Unable to read event text: "
		                 + e.getMessage()
		         );
		     }

		     return null;
		 }


		 
		 // ==========================================================
		 // EXTRACT TIMESTAMP
		 // ==========================================================

		 public String extractTimestamp(String event)
		 {
		     Pattern pattern =
		             Pattern.compile(
		                     "\\d{2}-[A-Za-z]{3}-\\d{4} "
		                   + "\\d{2}:\\d{2}:\\d{2}"
		             );

		     Matcher matcher =
		             pattern.matcher(event);

		     if (matcher.find())
		     {
		         return matcher.group();
		     }

		     throw new RuntimeException(
		             "Timestamp not found in event: "
		             + event
		     );
		 }
		 public String getLatestLiveEvent(String eventName)
		 {
		     List<WebElement> events = getLiveMessageEvents();

		     String latestEvent = null;

		     for (WebElement event : events)
		     {
		         String eventText = getEventText(event);

		         if (eventText == null)
		         {
		             continue;
		         }

		         if (eventText.startsWith(eventName))
		         {
		             latestEvent = eventText;
		         }
		     }

		     if (latestEvent == null)
		     {
		         throw new RuntimeException(
		                 "Live event not found: " + eventName
		         );
		     }

		     System.out.println("Latest Live Event : " + latestEvent);

		     return latestEvent;
		 }

		 // ==========================================================
		 // GET LIVE EVENT TIMESTAMP
		 // ==========================================================

		 public String getLiveEventTimestamp(String eventName)
		 {
		     String event = getLatestLiveEvent(eventName);

		     String timestamp =extractTimestamp(event);

		     System.out.println("Event : " + eventName);

		     System.out.println("Timestamp : " + timestamp);

		     return timestamp;
		 }


		 
		 // ==========================================================
		 // QUALIFICATION STOPPED EVENT
		 // ==========================================================


		 public String getLatestVerStoppedEvent()
		 {
		     List<WebElement> events =
		             getLiveMessageEvents();

		     String latestEvent = null;

		     for (WebElement event : events)
		     {
		         String eventText =
		                 getEventText(event);

		         if (eventText == null)
		         {
		             continue;
		         }

		         System.out.println(
		                 "Live Message : " + eventText
		         );

		         if (
		                 eventText.startsWith(
		                         "Verification Started"
		                 )
		                 ||
		                 eventText.startsWith(
		                         "Verification Stopped"
		                 )
		         )
		         {
		             latestEvent =
		                     eventText;
		         }
		     }

		     if (latestEvent == null)
		     {
		         throw new RuntimeException(
		                 "Verification Stopped event "
		                 + "not found"
		         );
		     }

		     System.out.println(
		             "Latest Ver Stopped Event : "
		             + latestEvent
		     );

		     return extractTimestamp(
		             latestEvent
		     );
		 }
		    
		 
		 public void click_SaveBtn_1() throws InterruptedException {

			    WebElement saveBtn =
			            driver.findElementByAccessibilityId("btnSaveStudyFile");

			    System.out.println("Save button displayed: " + saveBtn.isDisplayed());
			    System.out.println("Save button enabled: " + saveBtn.isEnabled());

			    if (!saveBtn.isDisplayed()) {
			        throw new IllegalStateException("Save Study button is not displayed");
			    }

			    if (!saveBtn.isEnabled()) {
			        throw new IllegalStateException("Save Study button is disabled");
			    }

			    clickOn(saveBtn);

			    System.out.println("Save Study button clicked successfully");
			}
		 
		 
		 
		 
		 
		 public boolean waitForLiveEvent(String eventName, int timeoutInSeconds)
			        throws InterruptedException {

			    for (int i = 0; i < timeoutInSeconds; i++) {

			        try {
			            String eventTime = getLiveEventTimestamp(eventName);

			            if (eventTime != null && !eventTime.trim().isEmpty()) {

			                System.out.println(
			                    "Event received: " + eventName +
			                    " | Time: " + eventTime
			                );

			                return true;
			            }

			        } catch (Exception e) {
			            // Event not available yet
			        }

			        Thread.sleep(1000);
			    }

			    System.out.println(
			        "Event not received within timeout: " + eventName
			    );

			    return false;
			}
		    
		 
		 
		 public MainHubPage clickBackButton1() throws IOException {
				WebElement backBtn = driver.findElementByAccessibilityId("ArrowGlyph");

			    WebDriverWait wait = new WebDriverWait(driver, 30);

			    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(backBtn));
			    element.click();
				return new MainHubPage();
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
				Thread.sleep(3000);
			}



}
