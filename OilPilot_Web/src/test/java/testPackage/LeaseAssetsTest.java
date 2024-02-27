package testPackage;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonUtil.ApplicationException;
import com.commonUtil.ExtentReportManager;
import com.commonUtil.commonUtil;

import testBase.TestBase;

public class LeaseAssetsTest extends TestBase {
	public String Lease_name = commonUtil.getRandomString(4);

	@Test(priority = 0, description = "Test create Lease assets.")
	public void createLease() throws ApplicationException, InterruptedException {

		dashboard.clickOnAssets();
		ExtentReportManager.test.log(Status.PASS, "Click on Assets");
		dashboard.clickOnMenuItem("Lease");
		ExtentReportManager.test.log(Status.PASS, "Click on Lease menu.");
		leaseAstop.clickAddButtonAndVerifyCreateLeaseHalfCardIsPresentOrNot();
		ExtentReportManager.test.log(Status.PASS, "Click on Add button and Verify Create Lease half card.");
		leaseAstop.enterLeaseName(Lease_name);
		ExtentReportManager.test.log(Status.PASS, "Enter Lease name.");
		leaseAstop.selectPumper();
		ExtentReportManager.test.log(Status.PASS, "Select pumper");
		leaseAstop.selectCompany();
		ExtentReportManager.test.log(Status.PASS, "Select company");
		leaseAstop.clickAddButtonForCreateNewLease();
		ExtentReportManager.test.log(Status.PASS, "Click on Submit button.");
		leaseAstop.verifyTheLeaseIsCreatedOrnot();
		ExtentReportManager.test.log(Status.PASS, "'" + Lease_name + "'" + " Lease successfully created.");

	}

}
