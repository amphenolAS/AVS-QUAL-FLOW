package com.avsQual.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.avsQual.pages.HardwarePage;
import com.avsQual.pages.LoginPage;
import com.avsQual.pages.MainHubPage;
import com.avsQual.pages.QualificationPage;
import com.avsQual.pages.RW_FileSelctionPage;
import com.avsQual.pages.UserManagementPage;
import com.avsQual.pages.assetCreationPage;
import com.avsQual.pages.assetDetailsPage2;
import com.avsQual.pages.assetHubPage;
import com.avsQual.pages.Setup_defineSetupPage;
import com.avsQual.pages.Setup_SensorConfigPage;
import com.avsQual.pages.Setup_GroupSensorsPage;
import com.avsQual.pages.Setup_CalculationsPage;
import com.avsQual.pages.Setup_CalVerParametersPage;
import com.avsQual.pages.Setup_QualParamPage;
import com.avsQual.pages.Setup_ReviewPage;
import com.avsQual.pages.assetDetailsPage;
import com.avsQual.pages.SelectAVSPage;
import com.avsQual.pages.QualificationStudyPage;
import com.avsQual.utility.QualificationUtility;
import com.avsQual.utility.TestUtilities;
import com.avsQual.base.BaseClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AvsQualFlow_FromScratch extends BaseClass {
	
	public AvsQualFlow_FromScratch() throws IOException
	{
		super();
	}
	
	public ExtentReports extent; 
	public ExtentTest extentTest; 

	String a_Name = prop.getProperty("Asset_Name");
	String a_ID = prop.getProperty("Asset_ID");
	String a_type = prop.getProperty("Asset_type");
	String manufacturer = prop.getProperty("Manufacturer");
	String location = prop.getProperty("Location");
	String s_Name = prop.getProperty("setup_Name");
	String l_Condition = prop.getProperty("Lithelity_Condition");
	String condition_Value = prop.getProperty("Lithelity_Condition_Value");
	int c_Val = Integer.parseInt(condition_Value);
	String startQual = prop.getProperty("Start_Qual");
	String StartQual_Hours = prop.getProperty("Time_oftheday_Hours");
	String StartQual_Min = prop.getProperty("Time_oftheday_Minutes");
	String StartQual_Sec = prop.getProperty("Time_oftheday_Seconds");
	String Compare_ComboBox = prop.getProperty("Start_Qual_CompareBox");
	String Start_QualTempValue = prop.getProperty("Start_Qual_Temp_Value");
	String Start_Expo = prop.getProperty("Start_Expo_Parameter");
	String Start_Expo_Hr = prop.getProperty("Start_Expo_Hours");
	String Start_Expo_Min = prop.getProperty("Start_Expo_Minutes");
	String Start_Expo_Sec = prop.getProperty("Start_Expo_Seconds");
	String Start_Expo_CmprBx = prop.getProperty("Start_Expo_CompareBox");
	String Start_ExpoTempValue = prop.getProperty("Start_Expo_Temp_Value");
	String Stp_Expo = prop.getProperty("Stop_Expo_Parameter");
	String Stp_Expo_Hr = prop.getProperty("Stop_Expo_Hours");
	String Stp_Expo_Min = prop.getProperty("Stop_Expo_Minutes");
	String Stp_Expo_Sec = prop.getProperty("Stop_Expo_Seconds");
	String Stp_Expo_CmprBx = prop.getProperty("Stop_Expo_CompareBox");
	String Stp_ExpoTempValue = prop.getProperty("Stop_Expo_Temp_Value");
	String Stp_Qual = prop.getProperty("Stop_Qual_Parameter");
	String Stp_Qual_Hr = prop.getProperty("Stop_Qual_Hours");
	String Stp_Qual_Min = prop.getProperty("Stop_Qual_Minutes");
	String Stp_Qual_Sec = prop.getProperty("Stop_Qual_Seconds");
	String Stp_Qual_CmprBx = prop.getProperty("Stop_Qual_CompareBox");
	String Stp_QualTempValue = prop.getProperty("Stop_Qual_Temp_Value");
	String Sampling_Rate = prop.getProperty("Sampling_Rate");
	String wait_Time = prop.getProperty("ManualStudyStop_Time");
	String StartExpo_Time = prop.getProperty("Start_Expo_Time");
	String StopExpo_Time = prop.getProperty("Stop_Expo_Time");
	String Time_ofday_Month = prop.getProperty("Time_oftheday_Month");
	String Time_ofday_Date = prop.getProperty("Time_oftheday_Date");
	String Time_oftheday_year = prop.getProperty("Time_oftheday_year");
		
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
	HardwarePage HardwarePage;
	TestUtilities tu = new TestUtilities();

	// Before Class -All the tests are conducted
		@BeforeClass
	public void PreSetup() throws InterruptedException, IOException, ParseException, AWTException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ER_" + "Qualification_STARTTest" + ".html", true);
		extent.addSystemInfo("TestSuiteName", "QualificationStartTest");
		extent.addSystemInfo("AVS Version", prop.getProperty("AVS_Version"));
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
		
		if(!assetHubPage.is_AssetDisplayed(a_Name))
		{
			assetCreationPage = assetHubPage.ClickAddAssetBtn();
			assetCreationPage.assetCreation_WithoutSave(a_Name, a_ID, a_type, manufacturer, location);
			assetCreationPage.clickSaveBtn();
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			Thread.sleep(3000);
			assetHubPage = assetCreationPage.clickBackBtn();
			
			assetDetailsPage = assetHubPage.click_assetTile2(a_Name);
			
			Setup_defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
			Setup_SensorConfigPage = Setup_defineSetupPage.enter_MandatoryFieldsindefineSetupconfigPage(s_Name, "48");
			Setup_SensorConfigPage.Click_SelectSen_Expanderbtn();
			Setup_SensorConfigPage.select_FromSim(1);
			Setup_SensorConfigPage.SelectAny_Option_FomSelectSensor("Sensor 1");
			Setup_SensorConfigPage.select_ToSim(4);
			Setup_SensorConfigPage.SelectAny_Option_ToSelectSensor("Sensor 12");
			Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
			Setup_SensorConfigPage.select_Sensortype();
			Setup_SensorConfigPage.Enter_SensorLabel("Temp");
			Setup_SensorConfigPage.click_AutoNumber();
			Setup_SensorConfigPage.Click_assignBtn();
			Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn(); 
			Setup_GroupSensorsPage.click_DfltGrp_Btn();
			Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab(); 
			Setup_CalculationsPage.click_calculationItem(1);
			Setup_CalculationsPage.clcik_CalLithComboBox();
			Setup_CalculationsPage.click_LithelityCondition(l_Condition, c_Val);
			Setup_CalVerParametersPage = Setup_CalculationsPage.Click_NxtBtn();
			Setup_QualParamPage = Setup_CalVerParametersPage.click_NextPage();
			Thread.sleep(1000);
			Setup_QualParamPage.select_DropDownOptionFromStrtQual(startQual, Time_ofday_Month, Time_ofday_Date, Time_oftheday_year, StartQual_Hours, StartQual_Min, StartQual_Sec, Compare_ComboBox, Start_QualTempValue);
			Setup_QualParamPage.select_DropDownOptionFromStrtExpo(Start_Expo, Start_Expo_Hr, Start_Expo_Min, Start_Expo_Sec, Start_Expo_CmprBx, Start_ExpoTempValue);
			Setup_QualParamPage.select_DropDownOptionFromStopExpo(Stp_Expo, Stp_Expo_Hr, Stp_Expo_Min, Stp_Expo_Sec, Stp_Expo_CmprBx, Stp_ExpoTempValue);
			Setup_QualParamPage.select_DropDownOptionFromStopQual(Stp_Qual, Stp_Qual_Hr, Stp_Qual_Min, Stp_Qual_Sec, Stp_Qual_CmprBx, Stp_QualTempValue);
			Setup_QualParamPage.select_SR(Sampling_Rate);
			Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();
			Setup_ReviewPage.clickSaveBtn();
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			try
			{
				Thread.sleep(1000);
			tu.click_Yes_popup();
			}
			catch (Exception e) {
				e.getMessage();
			}
			Thread.sleep(3000);
			assetDetailsPage = Setup_ReviewPage.click_backBtn(); 
			Thread.sleep(2000);
			AppClose();  
		}
				
		else 
		{
			assetDetailsPage = assetHubPage.click_assetTile2(a_Name);
			if(!assetDetailsPage.is_SetupDisplayed(s_Name))
			{
				Setup_defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
				Setup_SensorConfigPage = Setup_defineSetupPage.enter_MandatoryFieldsindefineSetupconfigPage(s_Name, "48");
				Setup_SensorConfigPage.Click_SelectSen_Expanderbtn();
				Setup_SensorConfigPage.select_FromSim(1);
				Setup_SensorConfigPage.SelectAny_Option_FomSelectSensor("Sensor 1");
				Setup_SensorConfigPage.select_ToSim(4);
				Setup_SensorConfigPage.SelectAny_Option_ToSelectSensor("Sensor 12");
				Setup_SensorConfigPage.Click_Configurationsensors_Expanderbtn();
				Setup_SensorConfigPage.select_Sensortype();
				Setup_SensorConfigPage.Enter_SensorLabel("Temp");
				Setup_SensorConfigPage.click_AutoNumber();
				Setup_SensorConfigPage.Click_assignBtn();
				Setup_GroupSensorsPage = Setup_SensorConfigPage.Click_nextbtn(); 
				Setup_GroupSensorsPage.click_DfltGrp_Btn();
				Setup_CalculationsPage = Setup_GroupSensorsPage.Click_CalculationsTab(); 
				Setup_CalculationsPage.click_calculationItem(1);
				Setup_CalculationsPage.clcik_CalLithComboBox();
				Setup_CalculationsPage.click_LithelityCondition(l_Condition, c_Val);
				Setup_CalVerParametersPage = Setup_CalculationsPage.Click_NxtBtn();
				Setup_QualParamPage = Setup_CalVerParametersPage.click_NextPage();
				Setup_QualParamPage.select_DropDownOptionFromStrtQual(startQual, Time_ofday_Month, Time_ofday_Date, Time_oftheday_year, StartQual_Hours, StartQual_Min, StartQual_Sec, Compare_ComboBox, Start_QualTempValue);
				Setup_QualParamPage.select_DropDownOptionFromStrtExpo(Start_Expo, Start_Expo_Hr, Start_Expo_Min, Start_Expo_Sec, Start_Expo_CmprBx, Start_ExpoTempValue);
				Setup_QualParamPage.select_DropDownOptionFromStopExpo(Stp_Expo, Stp_Expo_Hr, Stp_Expo_Min, Stp_Expo_Sec, Stp_Expo_CmprBx, Stp_ExpoTempValue);
				Setup_QualParamPage.select_DropDownOptionFromStopQual(Stp_Qual, Stp_Qual_Hr, Stp_Qual_Min, Stp_Qual_Sec, Stp_Qual_CmprBx, Stp_QualTempValue);
				Setup_QualParamPage.select_SR(Sampling_Rate);
				Setup_ReviewPage = Setup_QualParamPage.Click_NxtBtn();
				Setup_ReviewPage.clickSaveBtn();
				UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
				try
				{
					Thread.sleep(1000);
				tu.click_Yes_popup();
				}
				catch (Exception e) {
					e.getMessage();
				}
				Thread.sleep(3000);
				assetDetailsPage = Setup_ReviewPage.click_backBtn(); 
				Thread.sleep(2000);
				AppClose();  
			}
			
			else
			{
				Thread.sleep(2000);
				AppClose();
			} 
		}  
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

	@Test(dataProvider = "AVSQF", dataProviderClass = QualificationUtility.class)
	
	public void AVSQF(String AssetName, String SetupName, String AVS_IP, String SelectAVS, String BathTemp) throws InterruptedException, IOException
	{
		extentTest = extent.startTest(SetupName);
		SoftAssert sa = new SoftAssert();

		assetDetailsPage = assetHubPage.click_assetTile2(AssetName);
		
		//Qualification Count before study
		int beforeStudyCnt = assetDetailsPage.QualificationCnt();
		
		assetDetailsPage.select_Setup(SetupName);
		assetDetailsPage.click_InitiateQualBtn();
		SelectAVSPage = assetDetailsPage.StartQual_OKPopUp(Time_ofday_Month, Time_ofday_Date, Time_oftheday_year, StartQual_Hours, StartQual_Min, StartQual_Sec);
		
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
			
		//	QualificationStudyPage.waitForStopBtnDisabled();
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
			QualificationStudyPage.click_SaveBtn();
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			QualificationStudyPage.click_OkPopup2();
			QualificationStudyPage.click_DeleteBtn();
		}
//4th Scenario	
			else if(sn2.equalsIgnoreCase("Case3"))
			{
				QualificationStudyPage.click_StartQualBtn();
				QualificationStudyPage.click_YesBtn();
				UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
				QualificationStudyPage.click_OkPopup1();
				QualificationStudyPage.click_DeleteBtn();
				
				int startExpo = Integer.parseInt(StartExpo_Time);
				TimeUnit.MINUTES.sleep(startExpo);
				
				QualificationStudyPage.click_StartExpo();
				QualificationStudyPage.click_YesBtn();	
				QualificationStudyPage.click_DeleteBtn();
				
				QualificationStudyPage.waitForStopQualBtnVisible();
				
				int time = Integer.parseInt(wait_Time);

				TimeUnit.MINUTES.sleep(time);
		
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
			int time = Integer.parseInt(wait_Time);
			
			TimeUnit.MINUTES.sleep(time);
			
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
			
			int time = Integer.parseInt(wait_Time);
			
			TimeUnit.MINUTES.sleep(time);
			
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
					
		//		QualificationStudyPage.waitForStopBtnEnabled();
				QualificationStudyPage.waitForStartExpoBtnEabled();
				int startExpo = Integer.parseInt(StartExpo_Time);
					TimeUnit.MINUTES.sleep(startExpo);
					QualificationStudyPage.click_StartExpo();
					QualificationStudyPage.click_YesBtn();	
					QualificationStudyPage.click_DeleteBtn();
			
				int stopExpo = Integer.parseInt(StopExpo_Time);
			
					TimeUnit.MINUTES.sleep(stopExpo);
					QualificationStudyPage.click_StopExpo();
					QualificationStudyPage.click_YesBtn();	
					QualificationStudyPage.click_DeleteBtn();
				
				int time = Integer.parseInt(wait_Time);
				
				TimeUnit.MINUTES.sleep(time);
				
				QualificationStudyPage.stop_QualStudy(200);
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
//		assetDetailsPage.select_Setup(SetupName);
//		assetDetailsPage.click_QualTile();
//		Thread.sleep(2000);
//		
		//Qualification Count after study
		int afterStudyCnt = assetDetailsPage.QualificationCnt();
		
		//Here 1 represents qualification reports increments by 1 with every iteration
		sa.assertEquals(beforeStudyCnt , afterStudyCnt -1, "Fail: Qualification Reports are not generated");		
		
		sa.assertAll();   
	} 


}
