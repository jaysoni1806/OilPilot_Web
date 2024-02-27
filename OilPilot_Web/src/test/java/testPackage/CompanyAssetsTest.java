package testPackage;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ApplicationException;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.commonUtil;

import testBase.TestBase;

public class CompanyAssetsTest extends TestBase {
	public String Company_name = commonUtil.getRandomString(4);

	@Test(priority = 0, description = "Test create Company assets.")
	public void createCompany() throws ApplicationException, InterruptedException {

		dashboard.clickOnAssets();
		ExtentReportManager.test.log(Status.PASS, "Click on Assets");
		dashboard.clickOnMenuItem("Company");
		ExtentReportManager.test.log(Status.PASS, "Click on Company menu.");
		cmpAstsOp.clickAddButtonAndVerifyCreateCompnayHalfCardIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Click on Add button and Verify Create Company half card.");
		cmpAstsOp.enterCompanyName(Company_name);
		ExtentReportManager.test.log(Status.PASS, "Enter Company name.");
		cmpAstsOp.clickAddButtonForCreateNewCompany();
		ExtentReportManager.test.log(Status.PASS, "Click on Submit button.");
		cmpAstsOp.verifyTheCompanyIsCreatedOrnot();
		ExtentReportManager.test.log(Status.PASS, "'" + Company_name + "'" + " Company successfully created.");

	}

	@Test(priority = 1, description = "Test Search company.")
	public void searchCompany() throws ApplicationException, InterruptedException {
		cmpAstsOp.enterCompanyNameinSearchBox(Company_name);
		ExtentReportManager.test.log(Status.PASS, "Enter Company name in searchbox");
		cmpAstsOp.verifySearchedCompanyIsExistsOrNot(Company_name);
		ExtentReportManager.test.log(Status.PASS, "Verify the searched company is exists or not.");
		cmpAstsOp.verifyTheCompanyListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all companies.");
	}

	@Test(priority = 2, description = "Test Edit Company assets.")
	public void editCompany() throws ApplicationException, InterruptedException {
		cmpAstsOp.searchRecentAddedCompanyForEdit();
		ExtentReportManager.test.log(Status.PASS, "Search recently created company for Edit");
		cmpAstsOp.clickEdit_actionUnderThePerent_actionandVerifyEditHalfCardIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Verify the Edit action button is present or not.");
		cmpAstsOp.enterNewCompanyName();
		ExtentReportManager.test.log(Status.PASS, "Enter Updated company name.");
		cmpAstsOp.clickOnsubmitAndVerifyThatTheCompanyIsUpdateOrNot();
		ExtentReportManager.test.log(Status.PASS, "Verify that the company is update or not.");
		cmpAstsOp.verifyTheCompanyListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all companies.");
	}

	@Test(priority = 3, description = "Test Delete Company assets.")
	public void deleteCompany() throws ApplicationException, InterruptedException {
		cmpAstsOp.searchRecentUpdatedCompanyForDelete();
		ExtentReportManager.test.log(Status.PASS, "Received recently updated company for Delete.");
		cmpAstsOp.clickDelete_actionUnderThePerent_actionandVerifyDeleteConfirmationpopupIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS,
				"Verify the Delete Confirmation popup is Present or not when click on delete action button.");
		cmpAstsOp.verifyTheCompanyIsDeletedorNotAfterConfirm();
		ExtentReportManager.test.log(Status.PASS, "Verify that the compnay is Delete or not afte confirm.");
		cmpAstsOp.verifyTheCompanyListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all companies.");
	}

}
