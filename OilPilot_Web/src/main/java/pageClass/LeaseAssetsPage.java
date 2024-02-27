package pageClass;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.commonUtil.ApplicationException;
import com.commonUtil.Utility;

public class LeaseAssetsPage {
	public static Logger log = Logger.getLogger(LeaseAssetsPage.class);

	WebDriver driver;
	Utility utility;
	DashBoardPage dashboard;
	commonLocatorsRepo commLocators;
	private List<WebElement> searchRecords;
	public String LeaseName;

	public LeaseAssetsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		utility = new Utility(driver);
		dashboard = new DashBoardPage(driver);
		commLocators = new commonLocatorsRepo(driver);
	}

	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//div[text()='Select company']")
	WebElement companyDropDown;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//input[@placeholder='Enter name']")
	private WebElement inputLeaseName;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//button[@type='button']")
	private WebElement cancelBtn;
	@FindBy(xpath = "//div[contains(@class,'paperAnchorRight')][not(contains(@style,'visibility: hidden'))]//form//button[@type='submit']")
	private WebElement submitBtn;

	public void clickAddButtonAndVerifyCreateLeaseHalfCardIsPresentOrNot() throws ApplicationException {
		try {
			List<WebElement> searchRecords = driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row')]"));
			utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			List<WebElement> searchRecords = driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row')]"));
			utility.WaitUntilListOfElementIsVisible(searchRecords, 5);
		}
		log.info("Wait until loading lease list.");
		commLocators.clickAddButton();
	}

	public void enterLeaseName(String lease_name) throws ApplicationException {
		// TODO Auto-generated method stub
		LeaseName = lease_name;

		if (inputLeaseName.isDisplayed()) {
			utility.ClearTextBox(inputLeaseName);
			log.info("Clear the Lease text box.");

			utility.SendValues(inputLeaseName, LeaseName);
			log.info("Enter Lease name.");
		} else {
			throw new ApplicationException("Exception Occured", "Add lease input field is not present.");
		}
	}

	public void selectCompany() throws ApplicationException {
		commLocators.selectValue("Select company", "Company", cancelBtn);
	}

	public void selectPumper() throws ApplicationException {
		commLocators.selectValue("Select pumper name", "Pumper", cancelBtn);
	}

	public void clickAddButtonForCreateNewLease() throws ApplicationException {

		if (submitBtn.isDisplayed()) {
			utility.Submit(submitBtn);
			log.info("Click on Submit button.");
		} else {
			throw new ApplicationException("Exception Occured", "Submit button is not present.");
		}

	}

	public void verifyTheLeaseIsCreatedOrnot() throws ApplicationException, InterruptedException {

		utility.WaitUntilElementVisibiltyGone(dashboard.toastMessage, 5);
		enterCompanyNameinSearchBox(LeaseName);
		verifySearchedCompanyIsExistsOrNot(LeaseName);
		// utility.clearSearchBox();
	}

	public void enterCompanyNameinSearchBox(String leaseName) throws ApplicationException, InterruptedException {
		commLocators.Search(LeaseName);
	}

	public void verifySearchedCompanyIsExistsOrNot(String LeaseName) throws ApplicationException {
		searchRecords = driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row')]"));

		if (searchRecords.size() != 0) {
			for (WebElement searchedRecord : searchRecords) {
				WebElement leaseRow = searchedRecord.findElement(By.xpath("//h6[contains(@class,'subtitle2')]"));
				if (LeaseName.equals(leaseRow.getText())) {
					log.info("Record searched successfully.");
				}
			}
		} else {
			throw new ApplicationException("Exception Occured", "Searched record is not present.");
		}
	}

}
