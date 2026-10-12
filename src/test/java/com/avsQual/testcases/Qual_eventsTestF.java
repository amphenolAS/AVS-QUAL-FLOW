
//this is used for multiple iterations
package com.avsQual.testcases;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.avsQual.base.BaseClass;
import com.avsQual.pages.AuditPage;
import com.avsQual.pages.FM_SyncInPage;
import com.avsQual.pages.FileManagementPage;
import com.avsQual.pages.HardwarePage;
import com.avsQual.pages.LoginPage;
import com.avsQual.pages.MainHubPage;
import com.avsQual.pages.QualificationPage;
import com.avsQual.pages.QualificationStudyPage;
import com.avsQual.pages.RW_FileSelctionPage;
import com.avsQual.pages.SelectAVSPage;
import com.avsQual.pages.SensCaliberationPage;
import com.avsQual.pages.SensVerificationPage;
import com.avsQual.pages.Setup_CalVerParametersPage;
import com.avsQual.pages.Setup_CalculationsPage;
import com.avsQual.pages.Setup_GroupSensorsPage;
import com.avsQual.pages.Setup_QualParamPage;
import com.avsQual.pages.Setup_ReviewPage;
import com.avsQual.pages.Setup_SensorConfigPage;
import com.avsQual.pages.Setup_defineSetupPage;
import com.avsQual.pages.StartCaliberationPage;
import com.avsQual.pages.StartVerificationPage;
import com.avsQual.pages.SyncInAssetListPage;
import com.avsQual.pages.UserManagementPage;
import com.avsQual.pages.assetCreationPage;
import com.avsQual.pages.assetDetailsPage;
import com.avsQual.pages.assetDetailsPage2;
import com.avsQual.pages.assetHubPage;
import com.avsQual.pages.AuditPage;
import com.avsQual.utility.CaliberationUtility;
import com.avsQual.utility.QualificationUtility;
import com.avsQual.utility.TestUtilities;
import com.avsQual.utility.VerificationUtility;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Qual_eventsTestF extends BaseClass {
	
	public Qual_eventsTestF() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}


	public String AssetName;
	public String SetupName;
	public String AVS_IP;
	public String SelectAVS;
	public String BathTemp;

	// Excel iteration data is supplied by the programmatic TestNG runner.
	// There is intentionally NO @Factory here.
	// One XmlTest = one Excel row/iteration, and all 6 @Test methods run inside it.
	
	//public ExtentReports extent; for single iteration
	public static ExtentReports extent; //for multiple iteration
	public ExtentTest extentTest;
	//need to change console id for each console
	String ConsoleId="6A95A143387A0E44F1DE";
	
	// Initialization of the Pages
		LoginPage LoginPage;
		MainHubPage MainHubPage;
		UserManagementPage UserManagementPage;
		assetHubPage assetHubPage;
		assetCreationPage assetCreationPage;
		assetDetailsPage2 assetDetailsPage2;
		Setup_defineSetupPage Setup_defineSetupPage;
		Setup_SensorConfigPage Setup_SensorConfigPage;
		Setup_GroupSensorsPage Setup_GroupSensorsPage;
		Setup_CalculationsPage Setup_CalculationsPage;
		Setup_CalVerParametersPage Setup_CalVerParametersPage;
		Setup_QualParamPage Setup_QualParamPage;
		Setup_ReviewPage Setup_ReviewPage;
		assetDetailsPage assetDetailsPage;
		SelectAVSPage SelectAVSPage;
		QualificationStudyPage QualificationStudyPage;
		QualificationPage QualificationPage;
		RW_FileSelctionPage RWFileSelctionPage;
		TestUtilities tu = new TestUtilities();
		FileManagementPage FileManagementPage;
		FM_SyncInPage FM_SyncInPage;
		SyncInAssetListPage SyncInAssetListPage;
		HardwarePage HardwarePage; 
		StartCaliberationPage StartCaliberationPage;
		SensCaliberationPage SensCaliberationPage;
		StartVerificationPage StartVerificationPage;
		SensVerificationPage SensVerificationPage;
		AuditPage AuditPage;
		
		
		
		
	//	@BeforeClass
		// Runs ONCE for the complete Excel run.
		@BeforeSuite
		public void PreSetup() throws InterruptedException, IOException, ParseException, AWTException {

			extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ER_" + "WUXI_AuditEvents_Test" + ".html", true);
			extent.addSystemInfo("TestSuiteName", "WUXI_AuditEvents_Test");
			extent.addSystemInfo("FirmWare Version", prop.getProperty("AVS_FW_Version"));
			extent.addSystemInfo("DAQ_Version"  , prop.getProperty("DAQ_Version"));
			extent.addSystemInfo("User Name", prop.getProperty("User_Name"));
			System.out.println("EventsTest is in Progress..");
			try {
				//System.out.println("step1-before applaunch");
				LaunchApp("Kaye.NextGenValidator_tdxctrh6k91jc!App");
				Thread.sleep(500);
				//System.out.println("step2-after app launch");
				LoginPage = new LoginPage();
				//System.out.println("step3-login created");
				//System.out.println("checking SW version in about page");
				String version = LoginPage.get_SWVersion_About_Text();
		        System.out.println("Version = " + version);
		        //System.out.println("Step 4- version is checked and added in extent report");
				extent.addSystemInfo("AVS Version", version);
				Thread.sleep(2000);
				System.out.println("App is launched and login page is displayed");
				AppClose();
				//System.out.println("step5-app closed");
				Thread.sleep(2000);
				if(driver != null)
				{
				    driver.quit();
				    driver = null;
				}
			} catch (Exception e) {
				//System.out.println("App is already launched");
				System.out.println("PRESETUP FAILED");

		        e.printStackTrace();

		        throw e;
			}
			
			LaunchApp("Kaye.NextGenValidator_tdxctrh6k91jc!App");
			Thread.sleep(500);
			LoginPage = new LoginPage();
			MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
			//System.out.println("Login is successful and main hub page is displayed");
			assetHubPage = MainHubPage.Click_AssetTile2();
			
			if(!assetHubPage.is_AssetDisplayed("Amphenol"))
			{
			MainHubPage = assetHubPage.click_BackBtn();	
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			FM_SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPage(getUID("adminFull"), getPW("adminFull"));
			FM_SyncInPage.enter_Filepath("AVS_Cal");
			FM_SyncInPage.unSelect_FilterBtn();
							
			SyncInAssetListPage = FM_SyncInPage.click_SyncInOK_btn();
			SyncInAssetListPage.click_SelectAllBtn();
			SyncInAssetListPage.click_OkOnSyncInSelections();
			Thread.sleep(1000);
			SyncInAssetListPage.click_YesBtn_popup();
			Thread.sleep(15000);
			SyncInAssetListPage.click_OK_popup();
			
			}
			
			else
			{
				Thread.sleep(3000);
				AppClose();
				Thread.sleep(3000); 
			}   
			
		}
		// @AfterClass-All the tests are conducted
		//@AfterClass
		// Runs ONCE after all Excel iterations are completed.
		@AfterSuite
		public void endReport_releaseMomory() {
			extent.flush();
			extent.close();
		}

		// Before Method(Test) method
		@BeforeMethod(alwaysRun = true)
		@Parameters({"AssetName", "SetupName", "AVS_IP", "SelectAVS", "BathTemp"})
		public void Setup(
				String assetName,
				String setupName,
				String avsIp,
				String selectAvs,
				String bathTemp) throws InterruptedException, IOException {

			// Load the Excel row for the current iteration.
			this.AssetName = assetName;
			this.SetupName = setupName;
			this.AVS_IP = avsIp;
			this.SelectAVS = selectAvs;
			this.BathTemp = bathTemp;

			System.out.println("==============================================");
			System.out.println("Iteration Data Loaded");
			System.out.println("AssetName  = " + this.AssetName);
			System.out.println("SetupName  = " + this.SetupName);
			System.out.println("AVS_IP     = " + this.AVS_IP);
			System.out.println("SelectAVS  = " + this.SelectAVS);
			System.out.println("BathTemp   = " + this.BathTemp);
			System.out.println("==============================================");

			Thread.sleep(3000);
			try{
			//System.out.println("Before method of Cal_Qual_VerTest before app launch");
			LaunchApp("Kaye.NextGenValidator_tdxctrh6k91jc!App");
			Thread.sleep(500);
			LoginPage = new LoginPage();
			//System.out.println("App is launched and login page is displayed in before method of Cal_Qual_VerTest");
			MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
			assetHubPage = MainHubPage.Click_AssetTile2();
			}
		catch(Exception e)
		{
		    System.out.println("BEFORE METHOD FAILED");
		    e.printStackTrace();
		    throw e;
		}
		}

		// @AfterMethod TearDown of the App
		@AfterMethod(alwaysRun = true)
		public void Teardown(ITestResult result) throws IOException {
			if (result.getStatus() == ITestResult.FAILURE) {
				
				extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getThrowable() + " #");

			    try {

			        String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver,result.getName());

			        extentTest.log(LogStatus.FAIL,extentTest.addScreenCapture(screenshotPath1));

			    } catch(Exception e) {

			        System.out.println( "Unable to capture screenshot for failed test");
			    }

			} else if (result.getStatus() == ITestResult.SKIP) {
				try {

			        if(driver != null) {

			            String screenshotPath =TestUtilities.getFailedTCScreenshot(driver,result.getName() + "_SKIPPED");

			            extentTest.log(LogStatus.SKIP,extentTest.addScreenCapture(screenshotPath));
			        }

			    } catch (Exception e) {
			        System.out.println("Unable to capture screenshot for skipped test");
			    }
          extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				extentTest.log(LogStatus.PASS, "Test Case PASSED IS # " + result.getName() + " #");

			}
			extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report
			extent.flush();
			if(driver != null)
			{
			    try
			    {
			        driver.quit();
			    }
			    catch(Exception e)
			    {
			    	System.out.println("BEFORE METHOD FAILED");
			    	System.out.println("Driver = " + driver);
			    	e.printStackTrace();
			        System.out.println("Driver already closed");
			    }
			}
			driver = null;
		} 
		
		
		@Test(priority=0,description="Verify Qual Audit Events after saving Test and during study")
		
		public void  AVS_Qual()
				throws InterruptedException, IOException, AWTException
		{
			extentTest = extent.startTest("Qual Audit Events after saving Test(Qual_eventsTest)");
			SoftAssert sa = new SoftAssert();
            System.out.println(" Qual_eventsTest is started");
			assetDetailsPage = assetHubPage.click_assetTile2(AssetName);
			
			//Qualification Count before study
			
			int beforeQualStudyCnt = assetDetailsPage.QualificationCnt();   
			
			assetDetailsPage.select_Setup(SetupName);
			assetDetailsPage.click_InitiateQualBtn();
			SelectAVSPage = assetDetailsPage.StartQual_OKButton();
			Thread.sleep(4000);
			/*
			SelectAVSPage.enterTxt_IntoIpTxtBox(AVS_IP);
			//System.out.println("AVS_IP = " + AVS_IP);
			SelectAVSPage.click_AddBtn();
			SelectAVSPage.select_AVS(SelectAVS);
			//System.out.println("SelectAVS = " + SelectAVS);*/
			SelectAVSPage.click_USBDocking();
			QualificationStudyPage = SelectAVSPage.click_ConnectBtn();
			TimeUnit.SECONDS.sleep(3);
			    QualificationStudyPage.click_StartQualBtn();
				QualificationStudyPage.click_YesBtn();
				UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
				QualificationStudyPage.click_OkPopup1();
				QualificationStudyPage.click_DeleteBtn();
				Thread.sleep(2000);
				
				//checking for events Start qual
				String liveQualStartedTime =QualificationStudyPage.getLiveEventTimestamp("Manual Qual Started");
			   // System.out.println("Manual Qual Started Live Time = "+ liveQualStartedTime ); 
			
				sa.assertTrue( liveQualStartedTime != null,"Manual Qual Started event is not displayed");
			  
			    MainHubPage = QualificationStudyPage.click_BackBtn();
			    AuditPage= MainHubPage.ClickAuditTitle();
			    
			    String ExcptdMsg1 ="Setup : \"" + SetupName + "\" is loaded to AVS \"" + AVS_IP + "\" by User Name : \"User1\", User ID : \"1\"";
				String ActualMsg1 = AuditPage.get_auditEvent_text();

				sa.assertEquals(ActualMsg1, ExcptdMsg1, "FAIL: Event is not similar as expected ");
				
				Thread.sleep(1000);
				MainHubPage=AuditPage.Click_BackBtn();
				QualificationStudyPage=	MainHubPage.click_Discover_Qual();
				Thread.sleep(5000);
				
				// Auto Exposure Started
				if (!QualificationStudyPage.waitForLiveEvent("Auto Exposure Started", 180)) {

				    throw new SkipException("Auto Exposure Started event not received");
				}
                    Thread.sleep(1000);
				String liveExposureStartedTime =QualificationStudyPage.getLiveEventTimestamp("Auto Exposure Started");
				sa.assertTrue( liveExposureStartedTime != null,"Exposure Started event is not displayed");
                   Thread.sleep(2000);
				 MainHubPage = QualificationStudyPage.click_BackBtn();
				    AuditPage= MainHubPage.ClickAuditTitle();
				    Thread.sleep(2000);			    
				    String ExcptdMsg2 ="Setup : \"" + SetupName + "\" is loaded to AVS \"" + AVS_IP + "\" by User Name : \"User1\", User ID : \"1\"";
					String ActualMsg2 = AuditPage.get_auditEvent_text();

					sa.assertEquals(ActualMsg2, ExcptdMsg2, "FAIL: Event is not similar as expected ");
					
					Thread.sleep(2000);
					MainHubPage=AuditPage.Click_BackBtn();
					QualificationStudyPage=	MainHubPage.click_Discover_Qual();
				
				
				
				// Auto Exposure Stopped
				if (!QualificationStudyPage.waitForLiveEvent( "Auto Exposure Stopped", 180)) {

				    throw new SkipException("Auto Exposure Stopped event not received");
				}
				 Thread.sleep(1000);
				String liveExposureStoppedTime =QualificationStudyPage.getLiveEventTimestamp("Auto Exposure Stopped");
				sa.assertTrue( liveExposureStoppedTime != null,"Exposure stopped event is not displayed");
				   Thread.sleep(2000);	
				 MainHubPage = QualificationStudyPage.click_BackBtn();
				    AuditPage= MainHubPage.ClickAuditTitle();
				   
				    String ExcptdMsg3 ="Setup : \"" + SetupName + "\" is loaded to AVS \"" + AVS_IP + "\" by User Name : \"User1\", User ID : \"1\"";
				    String ActualMsg3 = AuditPage.get_auditEvent_text();

					sa.assertEquals(ActualMsg3, ExcptdMsg3, "FAIL: Event is not similar as expected ");
					
					Thread.sleep(2000);
					MainHubPage=AuditPage.Click_BackBtn();
					QualificationStudyPage=	MainHubPage.click_Discover_Qual();
				
				
				
				// Auto Qual Stopped
				if (!QualificationStudyPage.waitForLiveEvent( "Auto Qual Stopped", 180)) {

				    throw new SkipException("Auto Qual Stopped event not received");
				}

				String liveQualStoppedTime =QualificationStudyPage.getLiveEventTimestamp("Auto Qual Stopped");
				sa.assertTrue( liveQualStoppedTime != null,"Manual Qual Started event is not displayed");


				// AVS File Saved
				if (!QualificationStudyPage.waitForLiveEvent( "AVS File Saved", 180)) {
                    
					throw new SkipException("AVS File Saved event not received");
				}

				String liveFileSavedTime =QualificationStudyPage.getLiveEventTimestamp("AVS File Saved");
				sa.assertTrue( liveFileSavedTime != null,"Manual Qual Started event is not displayed");
				
				Thread.sleep(2000);
		/*	if(QualificationStudyPage.waitForSaveMsg()==true)
			{
				Thread.sleep(4000);
				QualificationStudyPage.click_DeleteBtn();
				Thread.sleep(2000);
			}*/
				QualificationStudyPage.click_DeleteBtn();
			Thread.sleep(2000);
			
				QualificationStudyPage.click_SaveBtn1();
				UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
				QualificationStudyPage.click_OkPopup2();
				QualificationStudyPage.click_DeleteBtn();	
			    MainHubPage = QualificationStudyPage.click_BackBtn();
			
                 AuditPage= MainHubPage.ClickAuditTitle();
				       
                     //study saved
                        String ActualMsg = AuditPage.get_auditEvent_text();
                        sa.assertTrue( ActualMsg.contains("Qualification file saved for \"" + SetupName + "\""), "FAIL: Qualification file saved event not found");
                		sa.assertTrue(ActualMsg.contains("User Name: \"User1\""),"FAIL: User Name is incorrect");
                		sa.assertTrue(ActualMsg.contains("User ID : \"1\""), "FAIL: User ID is incorrect");
                	//	sa.assertTrue(ActualMsg.contains("for " + SelectAVS), "FAIL: AVS name is incorrect");
                		
                		//Study stopped checking
                		String ActualMsg_1 = AuditPage.get_auditEvent_text(10);
                		String ExpectedMsg_1 = "Qualification Study stopped for \"" + SetupName + "\" by AVS_\"23030012\" as Automatic Event at \"" + liveQualStoppedTime + "\"";
                		sa.assertEquals(ActualMsg_1, ExpectedMsg_1, "FAIL: Event is not similar as expected ");
                		
                		//Study Exposure stopped checking
                		String ActualMsg_2 = AuditPage.get_auditEvent_text(17);
                		String ExpectedMsg_2 =  "Qualification Study Exposure stopped for \"" + SetupName +"\" by AVS_\"23030012\" as Automatic Event at \"" + liveExposureStoppedTime + "\"";
                		sa.assertEquals(ActualMsg_2, ExpectedMsg_2, "FAIL: Event is not similar as expected ");
                		
                		
                		//Study Exposure started checking
                		String ActualMsg_3 = AuditPage.get_auditEvent_text(24);
                        String ExpectedMsg_3 ="Qualification Study Exposure started for \"" + SetupName +  "\" by AVS_\"23030012\" as Automatic Event at \"" + liveExposureStartedTime + "\"";
                		 sa.assertEquals(ActualMsg_3, ExpectedMsg_3, "FAIL: Event is not similar as expected ");
                		
                		//Study  started checking
                		String ActualMsg_4 = AuditPage.get_auditEvent_text(31);
                	    String ExpectedMsg_4 = "Qualification Study started for Setup:\"" + SetupName + "\" at \""+ liveQualStartedTime+ "\" in AVS_23030012 from console: \""+ConsoleId+"\" by  User ID : \"1\", User Name : \"User1\"";
                	    sa.assertEquals(ActualMsg_4, ExpectedMsg_4, "FAIL: Event is not similar as expected ");
			
                          sa.assertAll();
                        }
		

@Test(priority=1,description="Verify Qual Audit Events after Cancling Test and during study")
		
		public void  AVS_QuitQual()
				throws InterruptedException, IOException, AWTException
		{
			extentTest = extent.startTest("Verify Qual Audit Events after Cancling Test and during study)");
			SoftAssert sa = new SoftAssert();
            System.out.println(" Qual_CancleeventsTest is started");
			assetDetailsPage = assetHubPage.click_assetTile2(AssetName);
			
			//Qualification Count before study
			
			int beforeQualStudyCnt = assetDetailsPage.QualificationCnt();   
			
			assetDetailsPage.select_Setup(SetupName);
			assetDetailsPage.click_InitiateQualBtn();
			SelectAVSPage = assetDetailsPage.StartQual_OKButton();
			Thread.sleep(4000);
			/*SelectAVSPage.enterTxt_IntoIpTxtBox(AVS_IP);
			//System.out.println("AVS_IP = " + AVS_IP);
			SelectAVSPage.click_AddBtn();
			SelectAVSPage.select_AVS(SelectAVS);
			//System.out.println("SelectAVS = " + SelectAVS);*/
			SelectAVSPage.click_USBDocking();
			QualificationStudyPage = SelectAVSPage.click_ConnectBtn();
			TimeUnit.SECONDS.sleep(3);
			    QualificationStudyPage.click_StartQualBtn();
				QualificationStudyPage.click_YesBtn();
				UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
				QualificationStudyPage.click_OkPopup1();
				QualificationStudyPage.click_DeleteBtn();
				Thread.sleep(2000);
				
				//checking for events Start qual
				String liveQualStartedTime =QualificationStudyPage.getLiveEventTimestamp("Manual Qual Started");
			   // System.out.println("Manual Qual Started Live Time = "+ liveQualStartedTime ); 
			
				sa.assertTrue( liveQualStartedTime != null,"Manual Qual Started event is not displayed");
			  Thread.sleep(5000);
			    MainHubPage = QualificationStudyPage.click_BackBtn();
			    AuditPage= MainHubPage.ClickAuditTitle();
			    
			    String ExcptdMsg1 ="Setup : \"" + SetupName + "\" is loaded to AVS \"" + AVS_IP + "\" by User Name : \"User1\", User ID : \"1\"";
				String ActualMsg1 = AuditPage.get_auditEvent_text();

				sa.assertEquals(ActualMsg1, ExcptdMsg1, "FAIL: Event is not similar as expected ");
				
				Thread.sleep(1000);
				MainHubPage=AuditPage.Click_BackBtn();
				QualificationStudyPage=	MainHubPage.click_Discover_Qual();
			
				Thread.sleep(5000);
							
				//Cancle the study
					QualificationStudyPage.click_Quitbtn();
					QualificationStudyPage.click_YesBtn();
					UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
					Thread.sleep(3000);
					QualificationStudyPage.click_DeleteBtn();
					//checking for live msg
					String QuitQualTime =QualificationStudyPage.getLiveEventTimestamp("Quit Qual Initiated");
				   // System.out.println("Manual Qual Started Live Time = "+ liveQualStartedTime ); 
				
					sa.assertTrue( QuitQualTime != null,"Quit Qual Initiated Started event is not displayed");
				  	
					//System.out.println("Step 1: Click on Save button");
				QualificationStudyPage.click_SaveBtn1();
				//System.out.println("Step 2: clicked on save");
				UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
				QualificationStudyPage.click_OkPopup2();
				QualificationStudyPage.click_DeleteBtn();
				
				String liveFileSavedTime =QualificationStudyPage.getLiveEventTimestamp("AVS File Saved");
				sa.assertTrue( liveFileSavedTime != null,"Manual Qual Started event is not displayed");
				
			     MainHubPage = QualificationStudyPage.click_BackBtn();
			
                 AuditPage= MainHubPage.ClickAuditTitle();
               //study saved
                 String ActualMsg = AuditPage.get_auditEvent_text();
                 sa.assertTrue( ActualMsg.contains("Qualification file saved for \"" + SetupName + "\""), "FAIL: Qualification file saved event not found");
         		sa.assertTrue(ActualMsg.contains("User Name: \"User1\""),"FAIL: User Name is incorrect");
         		sa.assertTrue(ActualMsg.contains("User ID : \"1\""), "FAIL: User ID is incorrect");
         	//	sa.assertTrue(ActualMsg.contains("for " + SelectAVS), "FAIL: AVS name is incorrect");
				
				// quit qual checking
        		String ActualMsg_1 = AuditPage.get_auditEvent_text(10);
        		String ExpectedMsg_1=	"Quit Qualification operation performed for Setup:\""+SetupName+"\" at \""+QuitQualTime+"\" in AVS_23030012 from console: \""+ConsoleId+"\" by  User ID : \"1\", User Name : \"User1\"";
        		
        		sa.assertEquals(ActualMsg_1, ExpectedMsg_1, "FAIL: Event is not similar as expected ");
        					
sa.assertAll();
}



@Test(priority=2,description="Verify Calibration Audit Events after Cancling Test and during study")

public void  AVS_Cal_Cancle()
		throws InterruptedException, IOException, AWTException
{
	extentTest = extent.startTest("Cancle Caliberation Study and verify Audit Events");
	SoftAssert sa = new SoftAssert();
    System.out.println("Test case AVS_Calibearation is started");
	assetDetailsPage = assetHubPage.click_assetTile2(AssetName);

	assetDetailsPage.select_Setup(SetupName);
	SelectAVSPage = assetDetailsPage.click_InitiateCalibBtn();
	/*
	SelectAVSPage.clickt_IntoIpTxtBox();
	SelectAVSPage.enterTxt_IntoIpTxtBox(AVS_IP);
	SelectAVSPage.click_AddBtn();
	Thread.sleep(2000);
	SelectAVSPage.select_AVS(SelectAVS);*/
	SelectAVSPage.click_USBDocking();
	
	StartCaliberationPage = SelectAVSPage.click_CnnctBtn();
	Thread.sleep(4000);
	StartCaliberationPage.click_SelectAllBtn();
	SensCaliberationPage = StartCaliberationPage.click_initCappppBtn();
	Thread.sleep(2000);
	
	SensCaliberationPage.click_StartBtn();
	Thread.sleep(5000);
	//checking for events Start qual
	String liveCalStartedTime =SensCaliberationPage.getLiveEventTimestamp("Calibration Started");
   // System.out.println("Manual Qual Started Live Time = "+ liveQualStartedTime ); 

	sa.assertTrue( liveCalStartedTime != null,"Cal Started event is not displayed");
  Thread.sleep(5000);
    MainHubPage = SensCaliberationPage.clickBackButton1();
    AuditPage= MainHubPage.ClickAuditTitle();
    //checking for loaded
    String ExcptdMsg1 ="Setup : \"" + SetupName + "\" is loaded to AVS \"" + AVS_IP + "\" by User Name : \"User1\", User ID : \"1\"";
	String ActualMsg1 = AuditPage.get_auditEvent_text();

	sa.assertEquals(ActualMsg1, ExcptdMsg1, "FAIL: Event is not similar as expected ");
	
	Thread.sleep(3000);
	MainHubPage=AuditPage.Click_BackBtn();
	SensCaliberationPage=	MainHubPage.click_Discover_Cal();
	
	Thread.sleep(5000);
	
	SensCaliberationPage.click_CancelBtn1();
	
	Thread.sleep(5000);
	String liveCalStoppedTime =SensCaliberationPage.getLiveEventTimestamp("Calibration Stopped");
	Thread.sleep(2000);
	String liveCaldoneTime =SensCaliberationPage.getLiveEventTimestamp("Calibration Done Successfully");
	Thread.sleep(2000);
	MainHubPage = SensCaliberationPage.clickBackButton();
	AuditPage=MainHubPage.ClickAuditTitle();
	
	//calibration stopped checking
	String ActualMsg = AuditPage.get_auditEvent_text();
	String ExpectedMsg = "Calibration Study stopped Setup:\""+SetupName +"\" at "+liveCalStoppedTime +" in AVS_23030012 from console: \""+ConsoleId+"\" by  User ID : \"1\", User Name : \"User1\"";
     sa.assertEquals(ActualMsg, ExpectedMsg, "FAIL: Event is not similar as expected ");
     
    //calibration start checking
 	String ActualMsg2 = AuditPage.get_auditEvent_text(10);
     String ExpectedMsg2 = "Calibration Study started Setup:\"" + SetupName + "\" at " + liveCalStartedTime + " in AVS_23030012 from console: \"" + ConsoleId + "\" by  User ID : \"1\", User Name : \"User1\"";
      sa.assertEquals(ActualMsg2, ExpectedMsg2, "FAIL: Event is not similar as expected ");
      
	
	sa.assertAll();

}


@Test(priority=3,description="Verify Calibration Audit Events after saving Test and during study")

public void  AVS_Cal()
		throws InterruptedException, IOException, AWTException
{
	extentTest = extent.startTest(" Caliberation Study and verify Audit Events");
	SoftAssert sa = new SoftAssert();
    System.out.println("Test case AVS_Calibearation is started");
	assetDetailsPage = assetHubPage.click_assetTile2(AssetName);

	assetDetailsPage.select_Setup(SetupName);
	SelectAVSPage = assetDetailsPage.click_InitiateCalibBtn();
	/*
	SelectAVSPage.clickt_IntoIpTxtBox();
	SelectAVSPage.enterTxt_IntoIpTxtBox(AVS_IP);
	SelectAVSPage.click_AddBtn();
	Thread.sleep(2000);
	SelectAVSPage.select_AVS(SelectAVS);*/
	SelectAVSPage.click_USBDocking();
	
	StartCaliberationPage = SelectAVSPage.click_CnnctBtn();
	Thread.sleep(4000);
	StartCaliberationPage.click_SelectAllBtn();
	SensCaliberationPage = StartCaliberationPage.click_initCappppBtn();
	Thread.sleep(2000);
	
	SensCaliberationPage.click_StartBtn();
	Thread.sleep(5000);
	//checking for events Start qual
	String liveCalStartedTime =SensCaliberationPage.getLiveEventTimestamp("Calibration Started");
   // System.out.println("Manual Qual Started Live Time = "+ liveQualStartedTime ); 

	sa.assertTrue( liveCalStartedTime != null,"Cal Started event is not displayed");
     Thread.sleep(5000);
    MainHubPage = SensCaliberationPage.clickBackButton1();
    AuditPage= MainHubPage.ClickAuditTitle();
    //checking for loaded
    String ExcptdMsg1 ="Setup : \"" + SetupName + "\" is loaded to AVS \"" + AVS_IP + "\" by User Name : \"User1\", User ID : \"1\"";
	String ActualMsg1 = AuditPage.get_auditEvent_text();

	sa.assertEquals(ActualMsg1, ExcptdMsg1, "FAIL: Event is not similar as expected ");
	
	Thread.sleep(3000);
	MainHubPage=AuditPage.Click_BackBtn();
	SensCaliberationPage=	MainHubPage.click_Discover_Cal();
	
	Thread.sleep(5000);
	
	if(SensCaliberationPage.waitForimgCheckSetpoint()==true)
	{
		Thread.sleep(1000);
		SensCaliberationPage.click_DeleteButton();
		Thread.sleep(2000);
		}
	
	String liveCalSaveTime =SensCaliberationPage.getLiveEventTimestamp("AVS File Saved");
	Thread.sleep(2000);

	
	String liveCaldoneTime =SensCaliberationPage.getLiveEventTimestamp("Calibration Done Successfully");
	Thread.sleep(2000);
	
	//Validate Save Button Should be disable
	sa.assertTrue(SensCaliberationPage.isSaveButtonEnableStatus(), "Fail: Save Button is not enabled");
	
	SensCaliberationPage.click_SaveStudyBtn();  
	Thread.sleep(5000);
	MainHubPage = SensCaliberationPage.clickBackButton();
	
	//MainHubPage = SensCaliberationPage.clickBackButton();
	AuditPage=MainHubPage.ClickAuditTitle();
	
	//saved checking
	
	String ActualMsg = AuditPage.get_auditEvent_text();
   /* sa.assertTrue( ActualMsg.contains("Calibration file saved for \"" + SetupName + "\""), "FAIL: Calibration file saved event not found");
	sa.assertTrue(ActualMsg.contains("User Name: \"User1\""),"FAIL: User Name is incorrect");
	sa.assertTrue(ActualMsg.contains("User ID : \"1\""), "FAIL: User ID is incorrect");
    sa.assertTrue(ActualMsg.contains("User : \"User1\""),"FAIL: User Name is incorrect");
  	sa.assertTrue(ActualMsg.contains("User : \"1\""), "FAIL: User ID is incorrect");*/
	sa.assertTrue(ActualMsg.contains("Calibration file saved for " + SetupName),"FAIL: Calibration file saved event not found");
	sa.assertTrue(ActualMsg.contains("User : User1"),"FAIL: User Name is incorrect");
	sa.assertTrue( ActualMsg.contains("ID: 1"),"FAIL: User ID is incorrect");
	   
	
	//study completed checking
	String ActualMsg2 = AuditPage.get_auditEvent_text(10);
    String ExpectedMsg2 = "Calibration Study Completed Successfully for Setup:\"" + SetupName + "\" at " + liveCaldoneTime + " in AVS_23030012";
     sa.assertEquals(ActualMsg2, ExpectedMsg2, "FAIL: Event is not similar as expected ");

    //calibration start checking
 	String ActualMsg3 = AuditPage.get_auditEvent_text(17);
     String ExpectedMsg3 = "Calibration Study started Setup:\"" + SetupName + "\" at " + liveCalStartedTime + " in AVS_23030012 from console: \"" + ConsoleId + "\" by  User ID : \"1\", User Name : \"User1\"";
      sa.assertEquals(ActualMsg3, ExpectedMsg3, "FAIL: Event is not similar as expected ");
      
	
	sa.assertAll();

}



@Test(priority=4,description="Verify Verification Audit Events after Cancling Test and during study")

public void  AVS_Ver_Cancle()
		throws InterruptedException, IOException, AWTException
{
	extentTest = extent.startTest("Cancle Verification Study and verify Audit Events");
	SoftAssert sa = new SoftAssert();
    System.out.println("Test case AVS_Verification is started");
	assetDetailsPage = assetHubPage.click_assetTile2(AssetName);

	assetDetailsPage.select_Setup(SetupName);
	SelectAVSPage = assetDetailsPage.click_InitiateVerBtn();
	/*
	SelectAVSPage.clickt_IntoIpTxtBox();
	SelectAVSPage.enterTxt_IntoIpTxtBox(AVS_IP);
	SelectAVSPage.click_AddBtn();
	Thread.sleep(2000);
	SelectAVSPage.select_AVS(SelectAVS);*/
	SelectAVSPage.click_USBDocking();
	TimeUnit.MINUTES.sleep(1);

	StartVerificationPage = SelectAVSPage.click_CnnctBtnVer();
	TimeUnit.MINUTES.sleep(1);
	StartVerificationPage.click_SelectAllBtn();
	SensVerificationPage = StartVerificationPage.click_initVer();
	TimeUnit.MINUTES.sleep(1);
	
    SensVerificationPage.click_StartBtn();
	TimeUnit.MINUTES.sleep(1);
	//checking for events Start qual
	String liveVerStartedTime =SensVerificationPage.getLiveEventTimestamp("Verification Started");
   // System.out.println("Manual Qual Started Live Time = "+ liveQualStartedTime ); 

	sa.assertTrue( liveVerStartedTime != null,"ver Started event is not displayed");
  Thread.sleep(10000);
    MainHubPage = SensVerificationPage.clickBackButton1();
    AuditPage= MainHubPage.ClickAuditTitle();
    //checking for loaded
    String ExcptdMsg1 ="Setup : \"" + SetupName + "\" is loaded to AVS \"" + AVS_IP + "\" by User Name : \"User1\", User ID : \"1\"";
	String ActualMsg1 = AuditPage.get_auditEvent_text();

	sa.assertEquals(ActualMsg1, ExcptdMsg1, "FAIL: Event is not similar as expected ");
	
	Thread.sleep(1000);
	MainHubPage=AuditPage.Click_BackBtn();
	SensVerificationPage=	MainHubPage.click_Discover_Ver();
	
	Thread.sleep(5000);
	
	SensVerificationPage.click_CancelBtn1();
	
	Thread.sleep(5000);
	String liveVerStoppedTime =SensVerificationPage.getLiveEventTimestamp("Verification Stopped");
	Thread.sleep(2000);
	String liveVerdoneTime =SensVerificationPage.getLiveEventTimestamp("Verification Done Successfully");
	Thread.sleep(5000);
	MainHubPage = SensVerificationPage.clickBackButton();
	AuditPage=MainHubPage.ClickAuditTitle();
	
	//Verification stopped checking
	String ActualMsg = AuditPage.get_auditEvent_text();
	String ExpectedMsg = "Stop Verification Study for "+SetupName + " at "+liveVerStoppedTime +" in AVS_23030012 from console: \""+ConsoleId+"\" by  User ID : \"1\", User Name : \"User1\"";
     sa.assertEquals(ActualMsg, ExpectedMsg, "FAIL: Event is not similar as expected ");
     
    //Verification start checking
 	String ActualMsg2 = AuditPage.get_auditEvent_text(10);
     String ExpectedMsg2 = "Start Verification Study for "+ SetupName +" at " + liveVerStartedTime + " in AVS_23030012 from console: \"" + ConsoleId + "\" by  User ID : \"1\", User Name : \"User1\"";
      sa.assertEquals(ActualMsg2, ExpectedMsg2, "FAIL: Event is not similar as expected ");
      
	
	sa.assertAll();

}


@Test(priority=5,description="Verify Verification Audit Events after saving study and during study")

public void  AVS_Ver()
		throws InterruptedException, IOException, AWTException
{
	extentTest = extent.startTest(" Verification Study and verify Audit Events");
	SoftAssert sa = new SoftAssert();
    System.out.println("Test case AVS_Verification is started");
	assetDetailsPage = assetHubPage.click_assetTile2(AssetName);

	assetDetailsPage.select_Setup(SetupName);
	SelectAVSPage = assetDetailsPage.click_InitiateVerBtn();
	/*
	SelectAVSPage.clickt_IntoIpTxtBox();
	SelectAVSPage.enterTxt_IntoIpTxtBox(AVS_IP);
	SelectAVSPage.click_AddBtn();
	Thread.sleep(2000);
	SelectAVSPage.select_AVS(SelectAVS);*/
	SelectAVSPage.click_USBDocking();
	TimeUnit.MINUTES.sleep(1);
	StartVerificationPage = SelectAVSPage.click_CnnctBtnVer();
	TimeUnit.MINUTES.sleep(1);
	StartVerificationPage.click_SelectAllBtn();
	SensVerificationPage = StartVerificationPage.click_initVer();
	TimeUnit.MINUTES.sleep(1);
	
    SensVerificationPage.click_StartBtn();
	TimeUnit.MINUTES.sleep(1);
	//checking for events Start qual
	String liveVerStartedTime =SensVerificationPage.getLiveEventTimestamp("Verification Started");
   // System.out.println("Manual Qual Started Live Time = "+ liveQualStartedTime ); 

	sa.assertTrue( liveVerStartedTime != null,"ver Started event is not displayed");
  Thread.sleep(10000);
    MainHubPage = SensVerificationPage.clickBackButton1();
    AuditPage= MainHubPage.ClickAuditTitle();
    //checking for loaded
    String ExcptdMsg1 ="Setup : \"" + SetupName + "\" is loaded to AVS \"" + AVS_IP + "\" by User Name : \"User1\", User ID : \"1\"";
	String ActualMsg1 = AuditPage.get_auditEvent_text();

	sa.assertEquals(ActualMsg1, ExcptdMsg1, "FAIL: Event is not similar as expected ");
	
	Thread.sleep(3000);
	MainHubPage=AuditPage.Click_BackBtn();
	SensVerificationPage=	MainHubPage.click_Discover_Ver();
	
	Thread.sleep(5000);
	

if(SensVerificationPage.waitForimgCheckSetpoint()==true)
			{
				Thread.sleep(1000);          
				SensVerificationPage.click_DeleteButton();
				Thread.sleep(2000);
			
			//Validate Save Button Should be disable
			sa.assertTrue(SensVerificationPage.isSaveButtonEnableStatus(), "Fail: Save Button is not enabled");
			
			
	Thread.sleep(5000);
	String liveVerSavedTime =SensVerificationPage.getLiveEventTimestamp("AVS File Saved");
	Thread.sleep(2000);
	String liveVerdoneTime =SensVerificationPage.getLiveEventTimestamp("Verification Done Successfully");
	Thread.sleep(5000);
        
SensVerificationPage.click_SaveStudyBtn();
	
Thread.sleep(5000);
	MainHubPage = SensVerificationPage.clickBackButton();
	AuditPage=MainHubPage.ClickAuditTitle();


String ActualMsg = AuditPage.get_auditEvent_text();
	sa.assertTrue(ActualMsg.contains("Verification file saved for \"" + SetupName+"\""),"FAIL: Verification file saved event not found");
	sa.assertTrue(ActualMsg.contains("User : \"User1\""),"FAIL: User Name is incorrect");
	sa.assertTrue(ActualMsg.contains("ID : \"1 "),"FAIL: User ID is incorrect");
		
	   
	
	//study completed checking
	String ActualMsg2 = AuditPage.get_auditEvent_text(10);
    String ExpectedMsg2 = "Verification Study Completed Successfully for Setup:\"" + SetupName + "\" at " + liveVerdoneTime + " in AVS_23030012";
     sa.assertEquals(ActualMsg2, ExpectedMsg2, "FAIL: Event is not similar as expected ");

     
    //Verification start checking
 	String ActualMsg3 = AuditPage.get_auditEvent_text(17);
     String ExpectedMsg3 = "Start Verification Study for "+ SetupName +" at " + liveVerStartedTime + " in AVS_23030012 from console: \"" + ConsoleId + "\" by  User ID : \"1\", User Name : \"User1\"";
      sa.assertEquals(ActualMsg2, ExpectedMsg2, "FAIL: Event is not similar as expected ");
      
	
	sa.assertAll();

}


}









}






















































