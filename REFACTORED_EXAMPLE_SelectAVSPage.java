package com.avsQual.pages;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avsQual.base.BaseClass;
import com.avsQual.utility.RetryHelper;
import com.avsQual.utility.TestUtilities;
import com.avsQual.utility.WaitTimes;
import com.avsQual.utility.PageLocators;

/**
 * SelectAVSPage - Page Object Model for AVS Selection UI
 * 
 * REFACTORED FOR BEST PRACTICES:
 * ✓ Removed hardcoded Thread.sleep() calls
 * ✓ Centralized element locators
 * ✓ Proper exception handling with meaningful error messages
 * ✓ Explicit waits instead of implicit/mixed strategies
 * ✓ Reusable retry logic
 * ✓ Clear, descriptive method names
 * ✓ Comprehensive logging
 * 
 * @author Refactored - Best Practices Version
 * @version 2.0
 */
public class SelectAVSPage extends BaseClass {
	
	private static final Logger logger = Logger.getLogger(SelectAVSPage.class.getName());
	
	// ============================================================================
	// CENTRALIZED ELEMENT LOCATORS - All in one place for maintainability
	// ============================================================================
	private final By locatorSelectAVSTitle = By.accessibilityId("tbSeSelectIO");
	private final By locatorDiscoverAVS = By.accessibilityId("DiscoverIOBoxButton");
	private final By locatorConfigWifi = By.accessibilityId("ConfigureWifi");
	private final By locatorIpTxtBox = By.accessibilityId("txtIOBox");
	private final By locatorAddBtn = By.accessibilityId("btnIOIPconnect");
	private final By locatorRemoveBtn = By.accessibilityId("btnRemoveIP");
	private final By locatorConnectBtn = By.accessibilityId("btnConnect");
	private final By locatorYesButton = By.name("Yes");
	private final By locatorIPAddress = By.accessibilityId("textBlockIpAddress");
	private final By locatorUSBDocking = By.accessibilityId("textUsbIp");
	
	// Web Element references
	private WebElement selectAVSTitle = null;
	private WebElement discoverAVS = null;
	private WebElement configWifi = null;
	private WebElement ipTxtBox = null;
	private WebElement addBtn = null;
	private WebElement removeBtn = null;
	private WebElement connectBtn = null;
	
	private TestUtilities testUtilities = new TestUtilities();
	
	// ============================================================================
	// CONSTRUCTOR & INITIALIZATION
	// ============================================================================
	
	/**
	 * Constructor - Initializes all page elements on instantiation
	 * 
	 * @throws IOException if properties file cannot be read
	 */
	public SelectAVSPage() throws IOException {
		super();
		initializePageElements();
		logger.info("SelectAVSPage initialized successfully");
	}
	
	/**
	 * Initialize all WebElements for this page
	 * Uses explicit waits to ensure elements are loaded before proceeding
	 * 
	 * This ensures all elements exist before methods try to interact with them
	 */
	private void initializePageElements() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WaitTimes.PAGE_LOAD_TIMEOUT);
			
			this.selectAVSTitle = wait.until(ExpectedConditions.presenceOfElementLocated(locatorSelectAVSTitle));
			this.discoverAVS = wait.until(ExpectedConditions.presenceOfElementLocated(locatorDiscoverAVS));
			this.configWifi = wait.until(ExpectedConditions.presenceOfElementLocated(locatorConfigWifi));
			this.ipTxtBox = wait.until(ExpectedConditions.presenceOfElementLocated(locatorIpTxtBox));
			this.addBtn = wait.until(ExpectedConditions.presenceOfElementLocated(locatorAddBtn));
			this.removeBtn = wait.until(ExpectedConditions.presenceOfElementLocated(locatorRemoveBtn));
			this.connectBtn = wait.until(ExpectedConditions.presenceOfElementLocated(locatorConnectBtn));
			
		} catch (TimeoutException e) {
			String errorMsg = "Page elements failed to load within " + WaitTimes.PAGE_LOAD_TIMEOUT + " seconds";
			logger.severe(errorMsg);
			throw new RuntimeException(errorMsg, e);
		}
	}
	
	/**
	 * Release memory - Called after test completion
	 * Nullifies all element references to allow garbage collection
	 */
	public void resetWebElements() {
		this.selectAVSTitle = null;
		this.discoverAVS = null;
		this.configWifi = null;
		this.ipTxtBox = null;
		this.addBtn = null;
		this.removeBtn = null;
		this.connectBtn = null;
		logger.info("Page elements memory released");
	}
	
	// ============================================================================
	// PAGE INTERACTION METHODS - With proper wait handling and exception management
	// ============================================================================
	
	/**
	 * Enter IP address into the IP Text Box
	 * 
	 * @param ipAddress The IP address to enter (format: xxx.xxx.xxx.xxx)
	 * @throws IllegalArgumentException if IP address is null or empty
	 * @throws TimeoutException if element is not interactive within timeout
	 */
	public void enterIPAddress(String ipAddress) {
		if (ipAddress == null || ipAddress.isEmpty()) {
			throw new IllegalArgumentException("IP address cannot be null or empty");
		}
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, WaitTimes.ELEMENT_INTERACTION_TIMEOUT);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locatorIpTxtBox));
			element.clear();
			element.sendKeys(ipAddress);
			logger.info("Entered IP address: " + ipAddress);
			
		} catch (TimeoutException e) {
			String errorMsg = "IP text box was not interactive after " + WaitTimes.ELEMENT_INTERACTION_TIMEOUT + " seconds";
			logger.severe(errorMsg);
			throw new RuntimeException(errorMsg, e);
		}
	}
	
	/**
	 * Click on the IP Text Box to focus it
	 * 
	 * @throws TimeoutException if element cannot be clicked within timeout
	 */
	public void clickIPTextBox() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WaitTimes.ELEMENT_INTERACTION_TIMEOUT);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locatorIpTxtBox));
			element.click();
			logger.info("Clicked IP text box");
			
		} catch (TimeoutException e) {
			String errorMsg = "Failed to click IP text box within " + WaitTimes.ELEMENT_INTERACTION_TIMEOUT + " seconds";
			logger.severe(errorMsg);
			throw new RuntimeException(errorMsg, e);
		}
	}
	
	/**
	 * Click the Add Button to add an AVS
	 * 
	 * @throws TimeoutException if button cannot be clicked within timeout
	 */
	public void clickAddButton() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WaitTimes.ELEMENT_INTERACTION_TIMEOUT);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locatorAddBtn));
			element.click();
			logger.info("Clicked Add button");
			
		} catch (TimeoutException e) {
			String errorMsg = "Add button was not clickable after " + WaitTimes.ELEMENT_INTERACTION_TIMEOUT + " seconds";
			logger.severe(errorMsg);
			throw new RuntimeException(errorMsg, e);
		}
	}
	
	/**
	 * Click USB Docking option to select USB connection mode
	 * 
	 * @throws TimeoutException if element cannot be clicked within timeout
	 */
	public void clickUSBDocking() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WaitTimes.ELEMENT_INTERACTION_TIMEOUT);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locatorUSBDocking));
			element.click();
			logger.info("Clicked USB Docking option");
			
		} catch (TimeoutException e) {
			String errorMsg = "USB Docking option was not clickable after " + WaitTimes.ELEMENT_INTERACTION_TIMEOUT + " seconds";
			logger.severe(errorMsg);
			throw new RuntimeException(errorMsg, e);
		}
	}
	
	// ============================================================================
	// CRITICAL METHOD: Click Connect Button with Confirmation Dialog
	// ============================================================================
	
	/**
	 * Click Connect Button for Calibration
	 * 
	 * Handles the confirmation dialog that appears after clicking Connect.
	 * Uses retry logic for robust handling of timing-dependent UI elements.
	 * 
	 * FLOW:
	 * 1. Click Connect button
	 * 2. Wait for "Yes" confirmation button to appear
	 * 3. Click "Yes" to confirm connection
	 * 4. Return to StartCaliberationPage once connection is established
	 * 
	 * @return StartCaliberationPage object for chaining
	 * @throws RuntimeException if connection cannot be established after retries
	 * @throws IOException if page initialization fails
	 */
	public StartCaliberationPage clickConnectButtonForCalibration() throws IOException {
		logger.info("Initiating Connect flow for Calibration");
		
		try {
			// STEP 1: Click the Connect button
			clickConnectButton();
			logger.info("Connect button clicked");
			
			// STEP 2: Wait for and click the "Yes" confirmation button
			// Using retry helper for robust handling of timing-dependent elements
			clickConfirmationDialog("Calibration connection confirmation");
			
			// STEP 3: Wait for the connection to be established
			// Allows backend to process connection request (not just UI rendering)
			waitForConnectionEstablished();
			
			logger.info("Calibration connection established successfully");
			return new StartCaliberationPage();
			
		} catch (Exception e) {
			String errorMsg = "Failed to establish calibration connection: " + e.getMessage();
			logger.severe(errorMsg);
			throw new RuntimeException(errorMsg, e);
		}
	}
	
	/**
	 * Click Connect Button for Qualification
	 * 
	 * Similar to calibration but returns QualificationStudyPage
	 * 
	 * @return QualificationStudyPage object for chaining
	 * @throws RuntimeException if connection cannot be established
	 * @throws IOException if page initialization fails
	 */
	public QualificationStudyPage clickConnectButtonForQualification() throws IOException {
		logger.info("Initiating Connect flow for Qualification");
		
		try {
			clickConnectButton();
			logger.info("Connect button clicked");
			
			clickConfirmationDialog("Qualification connection confirmation");
			
			waitForConnectionEstablished();
			
			logger.info("Qualification connection established successfully");
			return new QualificationStudyPage();
			
		} catch (Exception e) {
			String errorMsg = "Failed to establish qualification connection: " + e.getMessage();
			logger.severe(errorMsg);
			throw new RuntimeException(errorMsg, e);
		}
	}
	
	/**
	 * Click Connect Button for Verification
	 * 
	 * Similar to calibration but returns StartVerificationPage
	 * 
	 * @return StartVerificationPage object for chaining
	 * @throws RuntimeException if connection cannot be established
	 * @throws IOException if page initialization fails
	 */
	public StartVerificationPage clickConnectButtonForVerification() throws IOException {
		logger.info("Initiating Connect flow for Verification");
		
		try {
			clickConnectButton();
			logger.info("Connect button clicked");
			
			clickConfirmationDialog("Verification connection confirmation");
			
			waitForConnectionEstablished();
			
			logger.info("Verification connection established successfully");
			return new StartVerificationPage();
			
		} catch (Exception e) {
			String errorMsg = "Failed to establish verification connection: " + e.getMessage();
			logger.severe(errorMsg);
			throw new RuntimeException(errorMsg, e);
		}
	}
	
	// ============================================================================
	// HELPER METHODS - Reusable utility methods
	// ============================================================================
	
	/**
	 * Click the Connect button
	 * Uses explicit wait to ensure button is clickable before interaction
	 * 
	 * RATIONALE FOR WAIT TIME:
	 * - UI typically renders within 1-2 seconds
	 * - Connection dialog initialization takes additional time
	 * - 10 second timeout provides safety margin for slow systems
	 * 
	 * @throws TimeoutException if button cannot be clicked
	 */
	private void clickConnectButton() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WaitTimes.DIALOG_APPEARANCE_TIMEOUT);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locatorConnectBtn));
			element.click();
			logger.info("Connect button clicked successfully");
			
		} catch (TimeoutException e) {
			String errorMsg = "Connect button was not clickable after " + WaitTimes.DIALOG_APPEARANCE_TIMEOUT + " seconds";
			logger.severe(errorMsg);
			throw new RuntimeException(errorMsg, e);
		}
	}
	
	/**
	 * Wait for and click the confirmation "Yes" button
	 * 
	 * Implements retry logic because:
	 * - Dialog may take time to appear after Connect is clicked
	 * - Element may become stale between retries
	 * - Multiple dialog types in application (some have "Yes", some have other buttons)
	 * 
	 * RETRY STRATEGY:
	 * - Max 3 attempts (after 3 failures, element likely doesn't exist)
	 * - 2 second delay between retries (allows UI to fully render)
	 * - Each attempt uses fresh element reference (avoids stale element)
	 * 
	 * @param dialogDescription Human-readable description of dialog for logging
	 * @throws RuntimeException if dialog confirmation fails after all retries
	 */
	private void clickConfirmationDialog(String dialogDescription) {
		logger.info("Waiting for confirmation dialog: " + dialogDescription);
		
		RetryHelper.retryUntilSuccess(
			() -> {
				try {
					WebDriverWait wait = new WebDriverWait(driver, WaitTimes.DIALOG_APPEARANCE_TIMEOUT);
					WebElement yesButton = wait.until(
						ExpectedConditions.elementToBeClickable(locatorYesButton)
					);
					yesButton.click();
					logger.info("Clicked 'Yes' button for: " + dialogDescription);
					
				} catch (TimeoutException e) {
					throw new RuntimeException("Yes button not found for: " + dialogDescription, e);
				}
			},
			/* maxRetries */ 3,
			/* delayBetweenRetries */ 2000
		);
	}
	
	/**
	 * Wait for the AVS connection to be fully established
	 * 
	 * RATIONALE FOR WAIT TIME:
	 * - Network connection establishment: 2-3 seconds
	 * - Backend device initialization: additional 3-5 seconds
	 * - Page loading/UI updates: 1-2 seconds
	 * - Safety margin for slow systems: 2-3 seconds
	 * Total: 10-15 seconds is reasonable
	 * 
	 * Using explicit wait ensures page is fully loaded, not just rendered
	 */
	private void waitForConnectionEstablished() {
		logger.info("Waiting for connection to be established...");
		
		try {
			// Wait for a known element on the next page to become visible
			// This confirms navigation has completed
			WebDriverWait wait = new WebDriverWait(driver, WaitTimes.CONNECTION_ESTABLISHMENT_TIMEOUT);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.accessibilityId("btnStCal")));
			
			logger.info("Connection established - Next page loaded");
			
		} catch (TimeoutException e) {
			String errorMsg = "Connection establishment failed or page did not load within " + 
				WaitTimes.CONNECTION_ESTABLISHMENT_TIMEOUT + " seconds";
			logger.severe(errorMsg);
			throw new RuntimeException(errorMsg, e);
		}
	}
	
	/**
	 * Fetch text from an AVS Unit by searching through list of IP addresses
	 * 
	 * @param ipAddress The IP address to search for
	 * @return The text/IP address if found, empty string if not found
	 */
	public String fetchTextFromAVSUnit(String ipAddress) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WaitTimes.ELEMENT_INTERACTION_TIMEOUT);
			List<WebElement> ipElements = wait.until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(locatorIPAddress)
			);
			
			for (WebElement element : ipElements) {
				String elementText = element.getText();
				if (elementText.equals(ipAddress)) {
					logger.info("Found AVS unit with IP: " + ipAddress);
					return elementText;
				}
			}
			
			logger.warning("AVS unit with IP " + ipAddress + " not found");
			return "";
			
		} catch (TimeoutException e) {
			String errorMsg = "IP address list did not load within " + WaitTimes.ELEMENT_INTERACTION_TIMEOUT + " seconds";
			logger.severe(errorMsg);
			throw new RuntimeException(errorMsg, e);
		}
	}
	
	/**
	 * Select an AVS unit by its IP address from the displayed list
	 * 
	 * @param ipAddress The IP address to select
	 * @throws RuntimeException if AVS with given IP not found
	 */
	public void selectAVSByIPAddress(String ipAddress) {
		if (ipAddress == null || ipAddress.isEmpty()) {
			throw new IllegalArgumentException("IP address cannot be null or empty");
		}
		
		try {
			logger.info("Attempting to select AVS with IP: " + ipAddress);
			
			WebDriverWait wait = new WebDriverWait(driver, WaitTimes.ELEMENT_INTERACTION_TIMEOUT);
			List<WebElement> ipElements = wait.until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(locatorIPAddress)
			);
			
			for (WebElement element : ipElements) {
				if (element.getText().equals(ipAddress)) {
					WebElement clickable = wait.until(ExpectedConditions.elementToBeClickable(element));
					clickable.click();
					logger.info("Successfully selected AVS with IP: " + ipAddress);
					return;
				}
			}
			
			throw new NoSuchElementException("No AVS found with IP: " + ipAddress);
			
		} catch (TimeoutException e) {
			String errorMsg = "AVS list did not load or element not clickable within " + 
				WaitTimes.ELEMENT_INTERACTION_TIMEOUT + " seconds";
			logger.severe(errorMsg);
			throw new RuntimeException(errorMsg, e);
		} catch (NoSuchElementException e) {
			logger.severe(e.getMessage());
			throw e;
		}
	}
	
}
