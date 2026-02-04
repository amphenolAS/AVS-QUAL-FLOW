package com.avsQual.testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import com.avsQual.utility.TestUtilities;
import com.avsQual.base.BaseClass;
import com.avsQual.pages.CerHostPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CerhostTest extends BaseClass{
	public CerhostTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExtentReports extent;
	public ExtentTest extentTest;
	TestUtilities tu = new TestUtilities();
	CerHostPage CerHostPage;

	// Before All the tests are conducted
	@BeforeClass
	// @BeforeTest
	private void PreSetUp() throws IOException, InterruptedException, AWTException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ER" + "CerHostTest" + ".html", true);
		extent.addSystemInfo("TestSuiteName", "LoginTest");
		extent.addSystemInfo("User Name", prop.getProperty("User_Name1"));
		System.out.println("CerHostTest Test in Progress..");

		//String appPath = "C:\\Users\\Kaveri.Bedar\\git\\CRT-Test-Automation\\src\\test\\resources\\TestData\\CRT Licensting Tool\\CRTActivationTool.exe"; // .exe
		String appPath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CERHOST.exe"; // .exe
		
		CerHostLaunchApp(appPath);
		CerHostPage = new CerHostPage(driver);
		closeApplication();
		Thread.sleep(1000);
	}

	// After All the tests are conducted
	// @AfterTest
	@AfterClass
	public void endReport() throws InterruptedException {
		extent.flush();
		extent.close();
		System.out.println("Cerhost completed");
		Thread.sleep(500);
	}

	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		//String appPath = "C:\\Users\\Kaveri.Bedar\\git\\CRT-Test-Automation\\src\\test\\resources\\TestData\\CRT Licensting Tool\\CRTActivationTool.exe"; // Update with your .exe file path																																																																				
		String appPath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CERHOST.exe"; // .exe
		
		CerHostLaunchApp(appPath);
		//String current = System.getProperty("user.dir");
		//System.out.println("Current working directory in Java : " + current);
		CerHostPage = new CerHostPage(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void Teardown(ITestResult result) throws IOException, Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FailED IS # " + result.getName() + " #"); // to add name in extent
																								// report
			// TearDown of the App
			extentTest.log(LogStatus.FAIL, "TEST CASE FailED IS # " + result.getThrowable() + " #"); // to add
																										// error/exception
																										// in extent
																										// report

			String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1)); // to add screenshot in extent
																							// report
			// extentTest.log(LogStatus.Fail, extentTest.addScreencast(screenshotPath));
			// //to add screencast/video in extent report
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS # " + result.getName() + " #");
			// String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver,
			// result.getName());
			// extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2));
			// //to add screenshot in extent report
		}
		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report
		// MainLoginPage.resetWebElements();
		Thread.sleep(5000);
		driver.quit();
	}

	/********
	 * Test Cases
	 * 
	 * @throws AWTException
	 * @throws IOException
	 *********/

	// sss

	@Test(groups = { "Sanity", "Regression" }, description = " open file ")
	public void ACT_LIC_01() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(" ");

		SoftAssert sa = new SoftAssert();
		String Actual = CerHostPage.getTitle();
		//String Expected = "Remote Display Control for Windows CE";
		System.out.println(Actual);
		CerHostPage.clickon_file();
		CerHostPage.clickon_connect();
		CerHostPage.EnterIP("10.17.18.56");
		CerHostPage.clickOK();
		//sa.assertEquals(Actual, Expected, "FAIL:Appliation is not opened and tool name is not displayed");
		//sa.assertAll();
	}

	

}
