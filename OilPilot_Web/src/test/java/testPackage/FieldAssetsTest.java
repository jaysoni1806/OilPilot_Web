package testPackage;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ApplicationException;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.commonUtil;

import testBase.TestBase;

public class FieldAssetsTest extends TestBase {
	public String field_name = commonUtil.getRandomString(8);
	public String sonris_id = commonUtil.getRandomNumber(4);

	@Test(priority = 0, description = "Test create Field assets.")
	public void createField() throws ApplicationException, InterruptedException {
		dashboard.clickOnAssets();
		ExtentReportManager.test.log(Status.PASS, "Click on Assets");
		dashboard.clickOnMenuItem("Field");
		ExtentReportManager.test.log(Status.PASS, "Click on Field menu.");
		/*
		 * fieldAstsop.verifyTheScreen(); ExtentReportManager.test.log(Status.PASS,
		 * "Navigate to Filed Screen.");
		 */
		fieldAstsop.clickAddButtonAndVerifyAddFieldHalfCardIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Click on Add button and Verify Add Field half card.");
		fieldAstsop.EnterFiledDetails(field_name, sonris_id);
		ExtentReportManager.test.log(Status.PASS, "Entered Field details");
		fieldAstsop.clickAddButtonForCreateNewCompany();
		ExtentReportManager.test.log(Status.PASS, "Click on Submit button.");
		fieldAstsop.verifyTheFleldIsCreatedOrnot();
		ExtentReportManager.test.log(Status.PASS, "'" + field_name + "'" + " Field successfully created.");
	}

	@Test(priority = 1, description = "Test Search Field.")
	public void searchFleld() throws ApplicationException, InterruptedException {
		fieldAstsop.enterFieldNameinSearchBox(field_name);
		ExtentReportManager.test.log(Status.PASS, "Enter Field name in searchbox");
		fieldAstsop.verifySearchedFieldIsExistsOrNot(field_name);
		ExtentReportManager.test.log(Status.PASS, "Verify the searched Field is exists or not.");
		fieldAstsop.verifyTheFieldListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all Field.");
	}

	@Test(priority = 2, description = "Test edit Field assets.")
	public void editField() throws ApplicationException, InterruptedException {
		fieldAstsop.searchRecentAddedFieldForEdit();
		ExtentReportManager.test.log(Status.PASS, "Search recently created Field for Edit");
		fieldAstsop.clickEdit_actionUnderThePerent_actionandVerifyEditHalfCardIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Verify the Edit action button is present or not.");
		fieldAstsop.enterNewFieldName();
		ExtentReportManager.test.log(Status.PASS, "Enter Updated field name.");
		fieldAstsop.clickOnsubmitAndVerifyThatTheFieldIsUpdateOrNot();
		ExtentReportManager.test.log(Status.PASS, "Verify that the field is update or not.");
		fieldAstsop.verifyTheFieldListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all field.");
	}

	@Test(priority = 3, description = "Test Delete Field assets.")
	public void deleteField() throws ApplicationException, InterruptedException {
		fieldAstsop.searchRecentAddedFieldForDelete();
		ExtentReportManager.test.log(Status.PASS, "Received recently updated field for Delete.");
		fieldAstsop.clickDelete_actionUnderThePerent_actionandVerifyDeleteConfirmationpopupIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS,
				"Verify the Delete Confirmation popup is Present or not when click on delete action button.");
		fieldAstsop.verifyTheFieldIsDeletedorNotAfterConfirm();
		ExtentReportManager.test.log(Status.PASS, "Verify that the field is Delete or not afte confirm.");
		fieldAstsop.verifyTheFieldListAfterClearSearchBox();
		ExtentReportManager.test.log(Status.PASS, "Clear searchbox to get all companies.");
	}

}
