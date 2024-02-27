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

//import utils.UtilityMethods;

public class DashBoardPage {

	WebDriver driver;
	Utility utility;
	commonLocatorsRepo commLocators;
	public static Logger log = Logger.getLogger(DashBoardPage.class);

	@FindBy(xpath = "//div[contains(@class,'MuiAvatar-circular')]")
	public WebElement dashboard;
	@FindBy(xpath = "//p[text()='Assets']")
	public WebElement asset;
	@FindBy(xpath = "//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-entered')]")
	public WebElement assets_CollapseEntered;
	@FindBy(xpath = "//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-hidden')]")
	public WebElement assets_CollapseHidden;
	@FindBy(xpath = "//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-entered')]//li//a[@href='/assets/company/']")
	public WebElement companyMenu;
	@FindBy(xpath = "//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-entered')]//li//a[@href='/assets/field/']")
	public WebElement fieldMenu;
	@FindBy(xpath = "//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-entered')]//li//a[@href='/assets/lease/']")
	public WebElement leaseMenu;
	@FindBy(xpath = "//div[contains(@class,'react-hot-toast')]//div[@role='status']")
	public WebElement toastMessage;

	public DashBoardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		utility = new Utility(driver);
		commLocators = new commonLocatorsRepo(driver);
	}

	public void clickOnAssets() throws ApplicationException {
		List<WebElement> assets_CollapseHidden = driver.findElements(By.xpath(
				"//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-hidden')]"));

		if (assets_CollapseHidden.size() > 0) {
			if (asset.isDisplayed()) {
				utility.WaitUntilElementIsNotClickable(asset, 5);
				log.info("Waiting until Assets manu is clickable.");
				utility.Submit(asset);
				log.info("Click on Assets menu.");
			} else {
				throw new ApplicationException("Exception Occured", "Assets is not display.");
			}
		} else {
			log.info("Assets is already open.");
		}
	}

	public void clickOnMenuItem(String menuNAme) throws ApplicationException, InterruptedException {
		List<WebElement> assets_CollapseHidden = driver.findElements(By.xpath(
				"//p[text()='Assets']/parent::div/parent::div/following-sibling::ul[contains(@class,'MuiCollapse-hidden')]"));

		try {
			if (assets_CollapseHidden.isEmpty()) {
				if (menuNAme.equals("Company")) {
					selectMenu(companyMenu, menuNAme);
				} else if (menuNAme.equals("Field")) {
					selectMenu(fieldMenu, menuNAme);
				} else if (menuNAme.equals("Lease")) {
					selectMenu(leaseMenu, menuNAme);
				}
			} else {
				utility.Submit(asset);

			}
		} catch (Exception e) {
			log.info("Somthing not working for Assets-> " + e);
		}

	}

	public void selectMenu(WebElement MenuElement, String menuNAme) throws ApplicationException, InterruptedException {
		if (MenuElement.isDisplayed()) {
			utility.WaitUntilElementIsNotClickable(MenuElement, 5);
			log.info("Wait until " + menuNAme + " manu is clickable.");
			utility.Submit(MenuElement);
			log.info("Click on " + menuNAme + " manu item.");

		} else if (assets_CollapseHidden.isDisplayed()) {
			log.info("Assets menu has been collepse");
			clickOnAssets();
			log.info("Call Click Assets function again");
		}

		else {
			throw new ApplicationException("Exception Occured",
					commLocators.screenHeader.getText() + "Assets is not display.");
		}
	}

	public void clickOnSubmitButtonAndVerifyThatTheFiledIsCreateOrNot() {

	}

}
