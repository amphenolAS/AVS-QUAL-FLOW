package com.avsQual.pages;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avsQual.pages.assetCreationPage;
import com.avsQual.base.BaseClass;
import com.avsQual.utility.TestUtilities;

public class assetHubPage<AssetPageTitle> extends BaseClass {

	// Page element variable declaration definition
	WebElement AssetPageTitle = null;
	List<WebElement> AssetList = null;
	WebElement AddAssetBtn = null;
	WebElement TypeBtn = null;
	WebElement ManufacturerBtn = null;
	WebElement LocationBtn = null;
	WebElement BackBtn = null;
	WebElement SearchBtn = null;
	WebElement AssetHub_BackButton = null;
	
	// Page element Initialize method
	private void initElements() {
		//AssetPageTitle = driver.findElementByName("Assets");
		AssetPageTitle = driver.findElementByAccessibilityId("AssetsHeaderTextBlock");
		AssetList = driver.findElementByAccessibilityId("ItemGridView").findElements(By.className("GridViewItem"));
		AddAssetBtn = driver.findElementByAccessibilityId("AddedAssetsButton");
		TypeBtn = driver.findElementByAccessibilityId("TypeButton");
		ManufacturerBtn = driver.findElementByAccessibilityId("ManufacturerButton");
		LocationBtn = driver.findElementByAccessibilityId("LocationButton");
		BackBtn = driver.findElementByAccessibilityId("BackButton");
		SearchBtn = driver.findElementByAccessibilityId("SearchAssetsButton");
		AssetHub_BackButton = driver.findElementByAccessibilityId("BackButton");
		
	}

	// Constructor for initializing the page elements
	assetHubPage() throws IOException {
		super();
		initElements();
	}

	// Release memory
	public void resetWebElements() {
		AssetPageTitle = null;
		AssetList = null;
		AddAssetBtn = null;
		TypeBtn = null;
		ManufacturerBtn = null;
		LocationBtn = null;
		BackBtn = null;
		SearchBtn = null;
	}

	// Wait for page to load
	public void waitforAssetHubPageLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Assets")));
	}

	// Get the Asset Page title
	public String assetPageTitle() {
		return FetchText(AssetPageTitle);
	}

	// Verify the presence of Type Filter Button
	public boolean typeFilterBtnstate() {
		return IsElementVisibleStatus(TypeBtn);
	}

	// Verify the presence of Manufacturer Filter Button
	public boolean manufacturerFilterBtnstate() {
		return IsElementVisibleStatus(ManufacturerBtn);
	}

	// Click Manufacturer Filter Button
	public void click_manufacturerFilterBtn() throws InterruptedException {
		clickOn(ManufacturerBtn);
		Thread.sleep(500);
	}

	// Verify the presence of Location Filter Button
	public boolean locationFilterBtnstate() {
		return IsElementVisibleStatus(LocationBtn);
	}

	// Click Manufacturer Filter Button
	public void click_locationFilterBtn() throws InterruptedException {
		clickOn(LocationBtn);
		Thread.sleep(500);
	}

	// Verify the presence of Search Asset Button
	public boolean serachAstBtn_state() throws InterruptedException {
		return IsElementVisibleStatus(SearchBtn);
	}
	
	
	// Verify the presence of Search Add Asset Button
		public boolean addAst() throws InterruptedException {
			return IsElementVisibleStatus(AddAssetBtn);
		}

	// Click the Search Asset Button
	public void click_serachAstBtn() throws InterruptedException {
		clickOn(SearchBtn);
		Thread.sleep(500);
	}

	public assetHubPage serachAstBtnclose() throws InterruptedException, IOException {
	WebElement	serachAstBtnclose=	driver.findElementByName("Delete");
		clickOn(serachAstBtnclose);
		Thread.sleep(500);
		//return IsElementVisibleStatus(Asset);
		return new  assetHubPage();
	}
	
	
	// Click on back btn

	public MainHubPage click_BackBtn() throws InterruptedException, IOException {
		clickOn(BackBtn);
		Thread.sleep(1000);
		return new MainHubPage();
	}

	// Verify the presence of Search Asset Text field
	public boolean searchAstTxtfiled_state() {
		WebElement searchAst_textbx = driver.findElementByAccessibilityId("searchTextBox");
		return IsElementVisibleStatus(searchAst_textbx);
	}

	// Click the Search Asset Button
	public void enter_serachAsttxt(String AstName) throws InterruptedException {
		WebElement searchAst_textbx = driver.findElementByAccessibilityId("searchTextBox");
		clickOn(searchAst_textbx);
		ClearText(searchAst_textbx);
		enterText(searchAst_textbx, AstName);
		Thread.sleep(1000);
	}

	// Verify the presence of No record found
	public boolean IsNoRecordFoundVisible() {
		WebElement NoRecordFoundMSG = driver.findElementByName("No record found");
		return IsElementVisibleStatus(NoRecordFoundMSG);
	}

	// Verify the presence of Add Asset Button
	public boolean addAst_Visible() {
		return IsElementVisibleStatus(AddAssetBtn);
	}

	// Click the Add Asset Button
	public assetCreationPage ClickAddAssetBtn() throws InterruptedException, IOException {
		clickOn(AddAssetBtn);
		Thread.sleep(1000);
		return new assetCreationPage();
	}

	// Click the Back Button
	public MainHubPage ClickBackBtn() throws InterruptedException, IOException {
		clickOn(BackBtn);
		Thread.sleep(1000);
		return new MainHubPage();
	}

	// Get the total Assets present in the Asset Hub page
	public int asset_Count() {
		List<WebElement> AssetList = driver.findElementByAccessibilityId("ItemGridView")
				.findElements(By.className("GridViewItem"));
		return AssetList.size();
		// System.out.println("Total Assets created: " + AssetList.size());
	}

	//checking Assets are Collpsed or expanded
	public int assets_CollapsedorExpanded()
	{
		WebElement AssetListItems = driver.findElementByClassName("GridViewItem");
		int assetsize =	AssetListItems.getSize().getHeight();
		return assetsize;
	}
	// Get the target Asset tile info in the Asset hub page
	public String[] assetTile(String AssetName) {
		List<WebElement> AssetList = driver.findElementByAccessibilityId("ItemGridView")
				.findElements(By.className("GridViewItem"));
		// System.out.println("Total Assets created: " + AssetList.size());

		// Declaring an String array
		String[] a = null;

		// Loop for the different Asset tiles created
		for (int i = 0; i < AssetList.size(); i++) {
			// System.out.println("Asset type : " + AssetList.get(i).getText());

			List<WebElement> AssetTileInfoList = AssetList.get(i).findElements(By.className("TextBlock"));
			// System.out.println(" Asset tile info count: " + AssetTileInfoList.size());

			// Fetch all the contents of the Asset tile
			for (int j = 0; j < AssetTileInfoList.size(); j++) {
				// System.out.println("AssetTileInfo: "+AssetTileInfoList.get(j).getText());

				if (AssetTileInfoList.get(j).getText().equals(AssetName)) {
					a = new String[AssetTileInfoList.size()];

					// Another inner loop to fetch the tile info content once it matched the Asset
					// Name.
					for (int j2 = 0; j2 < a.length; j2++) {
						a[j2] = AssetTileInfoList.get(j2).getText();
					}
					// Move out of the Asset list after collecting the tile info of the targeted
					// asset
					// System.out.println("2nd Inr loop: "+Arrays.toString(a));
					break;
				}
			}
		}
		// Returning the String object
		return a;
	}

	// Click/Select the target Asset tile in the Asset hub page
	public assetDetailsPage click_assetTile(String AssetName) throws IOException, InterruptedException {
		List<WebElement> AssetList = driver.findElementByAccessibilityId("ItemGridView")
				.findElements(By.className("GridViewItem"));
		// System.out.println("Total Assets created: " + AssetList.size());

		// Loop for the different Asset tiles created
		for (int i = 0; i < AssetList.size(); i++) {
			// System.out.println("Asset type : " + AssetList.get(i).getText());

			List<WebElement> AssetTileInfoList = AssetList.get(i).findElements(By.className("TextBlock"));
			// System.out.println(" Asset tile info count: " + AssetTileInfoList.size());

			// Fetch all the contents of the Asset tile
			for (int j = 0; j < AssetTileInfoList.size(); j++) {
				// System.out.println("AssetTileInfo: "+AssetTileInfoList.get(j).getText());

				if (AssetTileInfoList.get(j).getText().equals(AssetName)) {
					clickOn(AssetTileInfoList.get(j));
					Thread.sleep(1000);
					break;
				}
			}
		}
		return new assetDetailsPage();
	}
	
	// Click/Select the target Asset tile in the Asset hub page
	public assetDetailsPage click_assetTile2(String AssetName) throws IOException, InterruptedException {
		List<WebElement> AssetList = driver.findElementByAccessibilityId("ItemGridView")
				.findElements(By.className("GridViewItem"));
		// System.out.println("Total Assets created: " + AssetList.size());

		// Loop for the different Asset tiles created
		for (int i = 0; i < AssetList.size(); i++) {
			// System.out.println("Asset type : " + AssetList.get(i).getText());

			List<WebElement> AssetTileInfoList = AssetList.get(i).findElements(By.className("TextBlock"));
			// System.out.println(" Asset tile info count: " + AssetTileInfoList.size());

			// Fetch all the contents of the Asset tile
			for (int j = 0; j < AssetTileInfoList.size(); j++) {
				// System.out.println("AssetTileInfo: "+AssetTileInfoList.get(j).getText());

				if (AssetTileInfoList.get(j).getText().equals(AssetName)) {
					clickOn(AssetTileInfoList.get(j));
					Thread.sleep(1000);
					break;
				}
			}
		}
		return new assetDetailsPage();
	}

	// Get the target Asset tile Height Width info in the Asset hub page
	public String[] assetTile_Dimension(String AssetName) {
		List<WebElement> AssetList = driver.findElementByAccessibilityId("ItemGridView")
				.findElements(By.className("GridViewItem"));
		// System.out.println("Total Assets created: " + AssetList.size());

		// Declaring Asset Tile dimensions
		int Ht = 0;
		int wdth = 0;

		// Loop for the different Asset tiles created
		for (int i = 0; i < AssetList.size(); i++) {
			// System.out.println("Asset type : " + AssetList.get(i).getText());

			List<WebElement> AssetTileInfoList = AssetList.get(i).findElements(By.className("TextBlock"));
			// System.out.println(" Asset tile info count: " + AssetTileInfoList.size());

			// Fetch all the contents of the Asset tile
			for (int j = 0; j < AssetTileInfoList.size(); j++) {
				// System.out.println("AssetTileInfo: "+AssetTileInfoList.get(j).getText());

				if (AssetTileInfoList.get(j).getText().equals(AssetName)) {

					Ht = AssetTileInfoList.get(j).getSize().height;
					wdth = AssetTileInfoList.get(j).getSize().width;

				}
			}
		}
		// Returning the String object
		String Height = Integer.toString(Ht);
		String Width = Integer.toString(wdth);

		String[] AsstDimension = { Height, Width };
		return AsstDimension;

	}

	// Get the Asset count info in the Asset hub page
	public String assetcount() {
		List<WebElement> AssetList = driver.findElementByAccessibilityId("ItemGridView")
				.findElements(By.className("GridViewItem"));
		// System.out.println("Total Assets created: "+AssetList.size());

		// Returning the asset count
		return String.valueOf(AssetList.size());
	}

	// Right Click anywhere in the Asset Hub Page to invoke the Bottom app status
	// bar
	public void rightclickonAssetPageTitle() throws InterruptedException {
		Actions ac = new Actions(driver);
		ac.contextClick(AssetPageTitle).build().perform();
		Thread.sleep(2000);
	}

	// Click the App Help button in the Bottom app status bar
	public void clickHelpIcon() throws InterruptedException {
		WebElement appHelp = driver.findElementByName("Help");
		clickOn(appHelp);
		Thread.sleep(1000);
	}

	// AssetHub Help window display
	public boolean is_assetHubHelpWindow_Displayed() throws InterruptedException {
		WebElement assetHubHelpElement = driver.findElementByName("Asset Hub");
		return IsElementVisibleStatus(assetHubHelpElement);
	}

	// Get the Assets list based on Type filter in the Asset hub page
	public boolean assetList_TypeFilter() {
		boolean response = false;

		clickOn(TypeBtn);

		// Asset groups list classified according to Type
		List<WebElement> AssetType_List = driver.findElementByAccessibilityId("ItemGridView")
				.findElements(By.className("GroupItem"));
		// System.out.println("Total Asset Types created: " + AssetType_List.size());

		// Loop for the different Asset type groups created
		for (int i = 0; i < AssetType_List.size(); i++) {
			String Asset_Group_Type_Name = AssetType_List.get(i).findElement(By.className("TextBlock")).getText();
			// System.out.println("Asset type data : " +
			// AssetType_List.get(i).findElement(By.className("TextBlock")).getText());

			List<WebElement> Asset_List_against_Type = AssetType_List.get(i).findElements(By.className("GridViewItem"));
			// System.out.println(" Asset tile info count: " +
			// Asset_List_against_Type.size());

			// Fetch all the contents of the Asset tile against the corresponding Asset Type
			// group
			for (int j = 0; j < Asset_List_against_Type.size(); j++) {
				String Individual_Asset_Type = Asset_List_against_Type.get(j).getText();
				// System.out.println("AssetTileInfo: " +
				// Asset_List_against_Type.get(j).getText());

				if (Asset_Group_Type_Name.equals(Individual_Asset_Type)) {
					response = true;
					continue;
				} else {
					response = false;
				}
			}
		}
		return response;
	}

	// Get the Assets list based on Manufacturer filter in the Asset hub page
	public boolean assetList_ManufacturerFilter() throws InterruptedException {
		boolean response = false;

		click_manufacturerFilterBtn();

		// Asset groups list classified according to Type
		List<WebElement> AssetType_List = driver.findElementByAccessibilityId("ItemGridView")
				.findElements(By.className("GroupItem"));
		// System.out.println("Total Asset Types_Mfg created: " +
		// AssetType_List.size());

		// Loop for the different Asset type(Manufacturer) groups created
		for (int i = 0; i < AssetType_List.size(); i++) {

			// Get the text of Each Manufacturer header
			String Asset_Group_Type_Name = AssetType_List.get(i).findElement(By.className("TextBlock")).getText();
			/*
			 * System.out .println("Asset Manufacturer data : " +
			 * AssetType_List.get(i).findElement(By.className("TextBlock")).getText());
			 */

			// Create the list of Asset present under each Mfg filter type group
			List<WebElement> Asset_List_against_Manufacturer = AssetType_List.get(i)
					.findElements(By.className("GridViewItem"));
			// System.out.println(" Asset tile info count: " +
			// Asset_List_against_Manufacturer.size());

			for (int j = 0; j < Asset_List_against_Manufacturer.size(); j++) {
				// CLick the each Asset under the respective Filter Group
				// to move to corresponding Asset detail page
				Asset_List_against_Manufacturer.get(j).click();
				Thread.sleep(1000);

				// define the Mfg text block element & get the text of it in the asset details
				// page
				WebElement AssetDetail_Mfginfo = driver.findElementByAccessibilityId("ManufacturerTextBlock");
				String Individual_Asset_Mfgdata = AssetDetail_Mfginfo.getText();
				// System.out.println("AssetDetail_MfgInfo: " + Individual_Asset_Mfgdata);

				// Validate if the Mfg filter group header text is same as the
				// Mfg data against the corresponding asset in the asset details page
				// If true, return the response as true else false
				if (Asset_Group_Type_Name.equals(Individual_Asset_Mfgdata)) {
					response = true;

					// define the back button element & click it in the asset details page
					// in order to move back to the Asset Hub page
					WebElement AsstDetail_BackBtn = driver.findElementByAccessibilityId("ArrowGlyph");
					AsstDetail_BackBtn.click();
					Thread.sleep(1000);

					// Since the Page (DOM) changes (from Asset details page to Asset hub page),
					// need to reinitialize the AssetHub page elements in this block itself
					// again fetch back the Asset Mfg filter group List as well as the Asset list
					// under each Mfg Group
					initElements();
					click_manufacturerFilterBtn();

					AssetType_List = driver.findElementByAccessibilityId("ItemGridView")
							.findElements(By.className("GroupItem"));
					Asset_List_against_Manufacturer = AssetType_List.get(i).findElements(By.className("GridViewItem"));

					continue;
				} else {
					response = false;
				}

			}

		}
		return response;
	}

	// Get the Assets list based on Location filter in the Asset hub page
	public boolean assetList_LocationFilter() throws InterruptedException {
		boolean response = false;
		
		//clickOn(LocationBtn);

		click_locationFilterBtn();

		// Asset groups list classified according to Type
		List<WebElement> AssetType_List = driver.findElementByAccessibilityId("ItemGridView")
				.findElements(By.className("GroupItem"));
		// System.out.println("Total Asset Types_Mfg created: "+AssetType_List.size());

		// Loop for the different Asset type(Location) groups created
		for (int i = 0; i < AssetType_List.size(); i++) {

			// Get the text of Each Location header
			String Asset_Group_Type_Name = AssetType_List.get(i).findElement(By.className("TextBlock")).getText();
Thread.sleep(3000);
			// System.out .println("Asset Location data : " +
			// AssetType_List.get(i).findElement(By.className("TextBlock")).getText());

			// Create the list of Asset present under each Location filter type group
			List<WebElement> Asset_List_against_Location = AssetType_List.get(i)
					.findElements(By.className("GridViewItem"));
			// System.out.println(" Asset tile info count: "
			// +Asset_List_against_Location.size());
		

			
			for (int j = 0; j < Asset_List_against_Location.size(); j++) {
				Thread.sleep(2000);
				// CLick the each Asset under the respective Filter Group
				// to move to corresponding Asset detail page
				Asset_List_against_Location.get(j).click();
				Thread.sleep(2000);

				// define the Asset edit element & click it in the asset details page
				// in order to move to the Asset edit screen to fetch the location data
				WebElement AssetDetail_editBtn = driver.findElementByAccessibilityId("EditAssetsButton");
				clickOn(AssetDetail_editBtn);

				// define the Asset location field element & fetch the data from it
				List<WebElement> AssetEditBox = driver.findElementsByAccessibilityId("EditableTextBox");
				//String Individual_Asset_Locationdata = AssetEditBox.get(1).getText();
				String Individual_Asset_Locationdata=AssetEditBox.get(i).getText();
				// System.out.println("AssetDetail_LocationInfo: " +
				// Individual_Asset_Locationdata);

				// Validate if the Location filter group header text is same as the
				// Location data against the corresponding asset in the asset edit page
				// If true, return the response as true else false
				if (Asset_Group_Type_Name.equals(Individual_Asset_Locationdata)) {
					response = true;

					// define the back button element & click it in the asset edit page
					// in order to move back to the Asset details screen page
					WebElement AssetEditBackBtn = driver.findElementByAccessibilityId("BackButton");
					AssetEditBackBtn.click();
					Thread.sleep(1000);

					WebElement AsstDetail_BackBtn = driver.findElementByAccessibilityId("ArrowGlyph");
					AsstDetail_BackBtn.click();
					Thread.sleep(1000);

					// Since the Page (DOM) changes (from Asset details page to Asset hub page),
					// need to reinitialize the AssetHub page elements in this block itself
					// again fetch back the Asset Location filter group List as well as the Asset
					// list
					// under each Location Group
					initElements();
					click_locationFilterBtn();

					AssetType_List = driver.findElementByAccessibilityId("ItemGridView")
							.findElements(By.className("GroupItem"));
					Asset_List_against_Location = AssetType_List.get(i).findElements(By.className("GridViewItem"));

					continue;
				} else {
					response = false;
				}

			}

		}
		return response;
	}

	// Click on ADD(+) icon
	public assetCreationPage Click_AddAssetButton() throws IOException {
		clickOn(AddAssetBtn);
		return new assetCreationPage();
	}

	// Click on ADD(+) icon for non default priviligae of ADMIN
	public void Click_AddButton() {
		clickOn(AddAssetBtn);
	}

	// click Back button to move to MainHub Page from Asset Hub page in case new
	// Asset is created
	public MainHubPage clickBackBtn() throws InterruptedException, IOException {
		clickOn(AssetHub_BackButton);
		Thread.sleep(1000);
		return new MainHubPage();
	}

	//Display User Message TestBlock
	public boolean is_MessageTextBlockDisplay()
	{
		WebElement DisplayMessagetxtBlck = driver.findElementByAccessibilityId("dispalyMessageTextBlock");
		return IsElementVisibleStatus(DisplayMessagetxtBlck);
	}
	public String NoRecordFoundMsg() throws InterruptedException {
		WebElement Msg = driver.findElementByName("No record found");
		Thread.sleep(1000);
		return FetchText(Msg);
	}

	public String getAssetIDvalue() {
		WebElement AssetIDvalue = driver.findElementByAccessibilityId("AssetIdTextBlock");
		return FetchText(AssetIDvalue);
	}
	
	
	// Verify the asset Hub Page title name
		public boolean Is_assetHubPageTitle_Visible() {
			return IsElementVisibleStatus(AssetPageTitle);
		}
		

		// Verify the assets displayed
			public boolean Is_assets_Visible() {
				return IsElementVisibleStatus(AssetList);
			}

			public boolean IsElementVisibleStatus(List<WebElement> assetList2) {
				return IsElementVisibleStatus(AssetList);
			}
			
			public boolean is_AssetDisplayed(String AName) {
//				try
//				{
//				return IsElementVisibleStatus(AssetList);
//				}
//				catch (Exception e) {
//					return false;
//				}
				
				List<WebElement> assetList = driver.findElementsByAccessibilityId("AssetSearchNameTextBlock");
				
				boolean status = false;
				for(WebElement aList:assetList)
				{
					try
					{
						if(FetchText(aList).equalsIgnoreCase(AName))
							status = true;
					}
					catch (Exception e) {
						status = false;
					}
				}
				return status;
			}
			
			//list of assets
			
			
			
			

		

}

