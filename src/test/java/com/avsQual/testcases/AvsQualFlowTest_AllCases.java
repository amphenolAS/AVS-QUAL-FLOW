package com.avsQual.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.avsQual.base.BaseClass;
import com.avsQual.pages.LoginPage;
import com.avsQual.pages.MainHubPage;
import com.avsQual.pages.QualificationPage;
import com.avsQual.pages.QualificationStudyPage;
import com.avsQual.pages.RW_FileSelctionPage;
import com.avsQual.pages.SelectAVSPage;
import com.avsQual.pages.Setup_CalVerParametersPage;
import com.avsQual.pages.Setup_CalculationsPage;
import com.avsQual.pages.Setup_GroupSensorsPage;
import com.avsQual.pages.Setup_QualParamPage;
import com.avsQual.pages.Setup_ReviewPage;
import com.avsQual.pages.Setup_SensorConfigPage;
import com.avsQual.pages.Setup_defineSetupPage;
import com.avsQual.pages.UserManagementPage;
import com.avsQual.pages.assetCreationPage;
import com.avsQual.pages.assetDetailsPage;
import com.avsQual.pages.assetDetailsPage2;
import com.avsQual.pages.assetHubPage;
import com.avsQual.pages.FileManagementPage;
import com.avsQual.pages.HardwarePage;
import com.avsQual.pages.FM_SyncInPage;
import com.avsQual.pages.SyncInAssetListPage;
import com.avsQual.utility.QualificationUtility;
import com.avsQual.utility.TestUtilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AvsQualFlowTest_AllCases extends BaseClass{
	
	public AvsQualFlowTest_AllCases() throws IOException
	{
		super();
	}
	
	public ExtentReports extent;
	public ExtentTest extentTest;
	
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
		
		@BeforeClass
		public void PreSetup() throws InterruptedException, IOException, ParseException, AWTException {

			extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ER_" + "Qualification_STARTTest" + ".html", true);
			extent.addSystemInfo("TestSuiteName", "QualificationStartTest");
			extent.addSystemInfo("AVS Version", prop.getProperty("AVS_Version"));
			extent.addSystemInfo("FirmWare Version", prop.getProperty("FM_Version"));
			extent.addSystemInfo("User Name", prop.getProperty("User_Name"));
			System.out.println("Qualification START Test is in Progress..");
			
			LaunchApp("Kaye.NextGenValidator_tdxctrh6k91jc!App");
			Thread.sleep(500);
			LoginPage = new LoginPage();
			extent.addSystemInfo("AVS Version", LoginPage.get_SWVersion_About_Text());
			Thread.sleep(2000);
			AppClose();
			Thread.sleep(2000);
			
			LaunchApp("Kaye.NextGenValidator_tdxctrh6k91jc!App");
			Thread.sleep(500);
			LoginPage = new LoginPage();
			MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
			
			assetHubPage = MainHubPage.Click_AssetTile2();
			
			if(!assetHubPage.is_AssetDisplayed("Amphenol"))
			{
			MainHubPage = assetHubPage.click_BackBtn();	
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			FM_SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPage(getUID("adminFull"), getPW("adminFull"));
			FM_SyncInPage.enter_Filepath("Setup");
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
//			LaunchApp("Kaye.NextGenValidator_tdxctrh6k91jc!App");
//			LoginPage = new LoginPage();
//			Thread.sleep(3000);
//			
//			AppClose();
//			Thread.sleep(2000);  	
			
		}
		// @AfterClass-All the tests are conducted
		@AfterClass
		public void endReport_releaseMomory() {
			extent.flush();
			extent.close();
		}

		// Before Method(Test) method
		@BeforeMethod(alwaysRun = true)
		public void Setup() throws InterruptedException, IOException {
			Thread.sleep(3000);
			LaunchApp("Kaye.NextGenValidator_tdxctrh6k91jc!App");
			Thread.sleep(500);
			LoginPage = new LoginPage();
			MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
			assetHubPage = MainHubPage.Click_AssetTile2();
		}

		// @AfterMethod TearDown of the App
		@AfterMethod(alwaysRun = true)
		public void Teardown(ITestResult result) throws IOException {
			if (result.getStatus() == ITestResult.FAILURE) {
				// to add name in extent report
				extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getName() + " #");
				// to add error/exception in extent report
				extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getThrowable() + " #");
				// to add screenshot in extent report
				String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());
				extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1));
				// to add screencast/video in extent report
				// extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath));

			} else if (result.getStatus() == ITestResult.SKIP) {
				extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				extentTest.log(LogStatus.PASS, "Test Case PASSED IS # " + result.getName() + " #");

			}
			extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report
			driver.quit();
			//driver = null;
		} 

		
		@Test(dataProvider = "SetupAVSQF", dataProviderClass = QualificationUtility.class)
		
		public void  AVS_QualFlowWithAllCondition(String AssetName, String SetupName, String AVS_IP, String SelectAVS, String BathTemp,
				 String StartQual_Hours, String StartQual_Min, String StartQual_Sec) throws InterruptedException, IOException, AWTException
		{
			extentTest = extent.startTest(SetupName);
			SoftAssert sa = new SoftAssert();

			assetDetailsPage = assetHubPage.click_assetTile2(AssetName);
			
						//Qualification Count before study
			int beforeStudyCnt = assetDetailsPage.QualificationCnt();
			
			assetDetailsPage.select_Setup(SetupName);
			assetDetailsPage.click_InitiateQualBtn();
			SelectAVSPage = assetDetailsPage.StartQual_OKPopUp1(StartQual_Hours, StartQual_Min, StartQual_Sec);
			
			SelectAVSPage.enterTxt_IntoIpTxtBox(AVS_IP);
			SelectAVSPage.click_AddBtn();
				
			SelectAVSPage.select_AVS(SelectAVS);
			QualificationStudyPage = SelectAVSPage.click_ConnectBtn();
			String sn = QualificationStudyPage.get_SetupName();
			String sn1 = sn.split(":")[1];
			String sn2 = sn1.trim();

			Thread.sleep(1000);
			
	//1st Scenario
				if(sn2.equalsIgnoreCase("Case1") || sn2.equalsIgnoreCase("Case2"))
			{
				QualificationStudyPage.click_StartQualBtn();
				QualificationStudyPage.click_YesBtn();
				UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
				QualificationStudyPage.click_OkPopup1();
				QualificationStudyPage.click_DeleteBtn();
				
				QualificationStudyPage.waitForSaveBtnEabled();
			try
			{
				QualificationStudyPage.click_DeleteBtn();
			}
			catch (Exception e) {
				e.getMessage();
			}
				QualificationStudyPage.click_SaveBtn();
				UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
				QualificationStudyPage.click_OkPopup2();
				QualificationStudyPage.click_DeleteBtn();
			}
	//2nd Scenario
				else if(sn2.equalsIgnoreCase("case15") || sn2.equalsIgnoreCase("Case16"))
				{
					QualificationStudyPage.waitForSaveBtnEabled();
					try
					{
						QualificationStudyPage.click_DeleteBtn();
					}
					catch (Exception e) {
						e.getMessage();
					}
						QualificationStudyPage.click_SaveBtn();
						UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
						QualificationStudyPage.click_OkPopup2();
						QualificationStudyPage.click_DeleteBtn();
				}
				
	//3rd Scenario
				else if(sn2.equalsIgnoreCase("Case6") || sn2.equalsIgnoreCase("Case7") 
						|| sn2.equalsIgnoreCase("Case8") || sn2.equalsIgnoreCase("Case9") || sn2.equalsIgnoreCase("Case10"))
			{
				HardwarePage = QualificationStudyPage.click_HardWareBtn();
				HardwarePage.enterBathTemp(BathTemp);
				HardwarePage.click_SetBathTempBtn();
				HardwarePage.click_DltBtn();
				QualificationStudyPage = HardwarePage.click_BackBtn();
					
				QualificationStudyPage.waitForSaveBtnEabled();
			try
			{
				QualificationStudyPage.click_DeleteBtn();
			}
			catch (Exception e) {
				e.getMessage();
			}
			Thread.sleep(2000);
				QualificationStudyPage.click_SaveBtn();
				UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
				QualificationStudyPage.click_OkPopup2();
				Thread.sleep(3000);
				try
				{
				QualificationStudyPage.click_DeleteBtn();
				}
				catch (Exception e) {
					e.getMessage();
				}
			}
	//4th Scenario	
				else if(sn2.equalsIgnoreCase("Case3"))
				{
					QualificationStudyPage.click_StartQualBtn();
					QualificationStudyPage.click_YesBtn();
					UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
					QualificationStudyPage.click_OkPopup1();
					QualificationStudyPage.click_DeleteBtn();
					
					TimeUnit.MINUTES.sleep(3);
					
					QualificationStudyPage.click_StartExpo();
					QualificationStudyPage.click_YesBtn();	
					QualificationStudyPage.click_DeleteBtn();
					
					QualificationStudyPage.waitForStopQualBtnVisible();

					TimeUnit.MINUTES.sleep(2);
			
					QualificationStudyPage.click_StopQualStudy();
					QualificationStudyPage.click_YesBtn();	
					UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
					QualificationStudyPage.click_DeleteBtn();
					QualificationStudyPage.click_SaveBtn();
					UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
					QualificationStudyPage.click_OkPopup2();
					QualificationStudyPage.click_DeleteBtn();
					
				}
				
	//5th Scenario
				else if(sn2.equalsIgnoreCase("Case4"))
				{
				QualificationStudyPage.click_StartQualBtn();
				QualificationStudyPage.click_YesBtn();
				UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
				QualificationStudyPage.click_OkPopup1();
				QualificationStudyPage.click_DeleteBtn();
				
				QualificationStudyPage.waitForStopQualBtnVisible();
				
				TimeUnit.MINUTES.sleep(1);
				
				QualificationStudyPage.click_StopQualStudy();
				QualificationStudyPage.click_YesBtn();	
				UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
				QualificationStudyPage.click_DeleteBtn();
				QualificationStudyPage.click_SaveBtn();
				UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
				QualificationStudyPage.click_OkPopup2();
				QualificationStudyPage.click_DeleteBtn();
				
			}
				//6th Scenario	
				else if(sn2.equalsIgnoreCase("Case5"))
				{
				QualificationStudyPage.click_StartQualBtn();
				QualificationStudyPage.click_YesBtn();
				UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
				QualificationStudyPage.click_OkPopup1();
				QualificationStudyPage.click_DeleteBtn();
				
				TimeUnit.MINUTES.sleep(4);
				
				QualificationStudyPage.click_StopQualStudy();
				QualificationStudyPage.click_YesBtn();	
				UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
				QualificationStudyPage.click_DeleteBtn();
				QualificationStudyPage.click_SaveBtn();
				UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
				QualificationStudyPage.click_OkPopup2();
				QualificationStudyPage.click_DeleteBtn();
				
			}
	//7th Scenario
				else if(sn2.equalsIgnoreCase("Case11") || sn2.equalsIgnoreCase("Case12") || sn2.equalsIgnoreCase("Case13")
						|| sn2.equalsIgnoreCase("Case14"))
				{
					HardwarePage = QualificationStudyPage.click_HardWareBtn();
					HardwarePage.enterBathTemp(BathTemp);
					HardwarePage.click_SetBathTempBtn();
					HardwarePage.click_DltBtn();
					QualificationStudyPage = HardwarePage.click_BackBtn();
						
					QualificationStudyPage.waitForStartExpoBtnEabled();
						TimeUnit.MINUTES.sleep(2);
						QualificationStudyPage.click_StartExpo();
						QualificationStudyPage.click_YesBtn();	
						QualificationStudyPage.click_DeleteBtn();
								
						TimeUnit.MINUTES.sleep(4);
						QualificationStudyPage.click_StopExpo();
						QualificationStudyPage.click_YesBtn();	
						QualificationStudyPage.click_DeleteBtn();
										
					TimeUnit.MINUTES.sleep(6);
					
					QualificationStudyPage.click_StopQualStudy();
					QualificationStudyPage.click_YesBtn();	
					UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
					QualificationStudyPage.click_DeleteBtn();
					QualificationStudyPage.click_SaveBtn();
					UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
					QualificationStudyPage.click_OkPopup2();
					QualificationStudyPage.click_DeleteBtn();
				}
			
			MainHubPage = QualificationStudyPage.click_BackBtn();
			MainHubPage.click_connectBtn();
			Thread.sleep(1000);
			assetHubPage = MainHubPage.Click_AssetTile2();
			assetDetailsPage = assetHubPage.click_assetTile2(AssetName);
			assetDetailsPage.click_QualTile();
			Thread.sleep(2000);
//			
			//Qualification Count after study
			int afterStudyCnt = assetDetailsPage.QualificationCnt();
			
			//Here 1 represents qualification reports increments by 1 with every iteration
			sa.assertEquals(beforeStudyCnt , afterStudyCnt -1, "Fail: Qualification Reports are not generated");		
			
			sa.assertAll();   
		} 


}
