package testBase;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.Listeners.ExtentReportListener;
import com.commonUtil.ExtentReportManager;

import pageClass.CompanyAssetsPage;
import pageClass.DashBoardPage;
import pageClass.FiledAssetsPage;
import pageClass.LeaseAssetsPage;
import pageClass.LoginPage;
import pageClass.commonLocatorsRepo;

public class TestBase {

	public static WebDriver driver;
	public LoginPage login;
	public DashBoardPage dashboard;
	public CompanyAssetsPage cmpAstsOp;
	public FiledAssetsPage fieldAstsop;
	public commonLocatorsRepo commLocators;
	public LeaseAssetsPage leaseAstop;
	public static Logger log = Logger.getLogger(TestBase.class);

	@BeforeSuite
	public void prerequisite() {
		driver = new ChromeDriver();

		PropertyConfigurator.configure(System.getProperty("user.dir") + "/Resource/log4j.properties");
		log.info("************************************************************************");
		log.info("Chrome browser launched.");

		driver.get("https://oilman-website.apps.openxcell.dev/login/");
		log.info("Hit Oilman URL. ");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@BeforeClass
	public void assignObjectForClass() {
		login = new LoginPage(driver);
		dashboard = new DashBoardPage(driver);
		cmpAstsOp = new CompanyAssetsPage(driver);
		fieldAstsop = new FiledAssetsPage(driver);
		commLocators = new commonLocatorsRepo(driver);
		leaseAstop = new LeaseAssetsPage(driver);
	}

	@BeforeTest
	public void initializeExtentReport() {

		ExtentReportManager.initializeExtentReport();
	}

	@BeforeMethod
	public void seperateCase() {
		log.info("------------------------------------------------------------------------");
	}

	@AfterTest
	public void flush() {
		ExtentReportListener.flush();
	}

	// @AfterSuite
	public void closeBrowser() {
		driver.close();
	}

}
