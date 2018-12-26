package com.snapworks.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.snapworks.pages.CaseNotesPage;
import com.snapworks.pages.ClientInfoPage;
import com.snapworks.pages.ComponentsSummaryPage;
import com.snapworks.pages.EmploymentPlanDetailsPage;
import com.snapworks.pages.EmploymentPlanPage;
import com.snapworks.pages.EnrollmentSummaryPage;
import com.snapworks.pages.HomePage;
import com.snapworks.pages.LoginPage;
import com.snapworks.pages.SearchPage;


public class EmploymentPlanTest {

	private WebDriver driver;
	private LoginTest loginTest;
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchPage searchPage;
	private EmploymentPlanPage employmentPlanPage;
	private EmploymentPlanDetailsPage employmentPlanDetailsPage;
	private ExtentReports extentReport;
	private ExtentTest testLog;
	private CaseNotesPage caseNotesPage;
	private ComponentsSummaryPage componentsSummaryPage;
	private EnrollmentSummaryPage enrollmentSummaryPage;
	private ClientInfoPage clientInfoPage;
	
	
	String applicationURL;
	String name;
	String password;
	@BeforeClass
	public void StartReport()
	{	
		extentReport=ExtentReport.startReportInstance();		
	}
	// Method to Launch the application in Chrome browser

	@BeforeMethod
	@Parameters({ "Browser", "name", "password", "url" })

	public void launchBrowser(String BrowserName, String name, String password, String url) {
		this.name = name;
		this.password = password;
		this.applicationURL = url;
		String userdirectory = System.getProperty("user.dir");
		
		if(BrowserName.equalsIgnoreCase("Chrome"))
		{
		String chromedriverpath = userdirectory + "\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromedriverpath);
		driver = new ChromeDriver();
		driver.get(applicationURL);
		driver.manage().window().maximize(); 
		}
		
		else if(BrowserName.equalsIgnoreCase("firefox"))
		{
			String firefoxDriver = userdirectory + "\\geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", firefoxDriver);
			driver = new FirefoxDriver();
			driver.get(applicationURL);
			driver.manage().window().maximize(); 
		}
	}
	@Test
	public void CreateEmploymentPlan() throws InterruptedException{
		Random random = new Random();
		String id = String.format("%04d", random.nextInt(10000));
		String taskType = "Employment Plan";
		String empGST = "Full Time Employment";
		String empLST = "Part Time Employment";
		String empStatus = "Open";
		String empPlanSummarycolumn ="Status";
		String empnotes ="Test"+id;
		loginPage = new LoginPage(driver);
		// Calling loginUser Method to validate login UserName and Password 
		// Navigate from login screen to Home screen after login success
		homePage = loginPage.loginUser(name, password);
		// checking login successfully
		assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");		
		// navigating to Search screen from home screen by clicking on Search Tab
		searchPage= homePage.getSearchPage();
		// get the Search results by Task Type dropdown option 
		searchPage.getSearchResults(taskType);	
		assertTrue(searchPage.clickTaskType().isDisplayed(), "Search results not found for looking taskType");
		employmentPlanPage = searchPage.getEmploymentPage();
		boolean rtnValue = false;
		// Get the latest dates recorded in the employment Plan page
		// store dates in a list
		List<Object> values = (List<Object>) employmentPlanPage.getTopRecordDates();
		boolean value = employmentPlanPage.getLatestRcdData(empPlanSummarycolumn);
		String StartDate = (String) values.get(0);
		String EndDate = (String) values.get(1);
		if(value == true) {
			// new employment plan
			employmentPlanDetailsPage=employmentPlanPage.getEmploymentPlanDetailsPage();
			rtnValue = employmentPlanDetailsPage.createemploymentPlan(empGST, empLST, empStatus, empnotes, StartDate, EndDate);
			employmentPlanPage=employmentPlanDetailsPage.getEmploymentPage();
			boolean statusOpen = employmentPlanPage.getEmploymentPlanSummaryCheck(empPlanSummarycolumn);
			if(statusOpen == true) {
				componentsSummaryPage = employmentPlanPage.getComponentsPage();
			}
			
		}
		else{
			
			employmentPlanDetailsPage = employmentPlanPage.getEmploymentPlanDetailsPage();
			rtnValue = employmentPlanDetailsPage.createemploymentPlan(empGST, empLST, empStatus, empnotes,StartDate, EndDate);
			employmentPlanPage=employmentPlanDetailsPage.getEmploymentPage();

			boolean statusOpen = employmentPlanPage.getEmploymentPlanSummaryCheck(empPlanSummarycolumn);
			if(statusOpen == false) {
				//Client Information page
				// TO-DO List
				clientInfoPage = employmentPlanPage.getClientInfo();
				enrollmentSummaryPage = clientInfoPage.getEnrolSummary();
				enrollmentSummaryPage.closeCase();
			}
			
			
		}	
		
		
		assertTrue(rtnValue, "NO Opened Employment or Add New Button is not working");
		ExtentTest testLog1=extentReport.startTest("VERIFY EMPLOYMENT PLAN UPDATED OR CREATED");//For Name
		testLog1.log(LogStatus.INFO,"Verified newly added contact details in Employement page");
		
	}
	@AfterMethod
	public void getScrnCapture(ITestResult result)
		{				
			if(result.getStatus()==ITestResult.FAILURE)
			{	
				ExtentTest testLog=extentReport.startTest(result.getName());
				testLog.log(LogStatus.FAIL, result.getThrowable());
				String screenshot_path=ExtentReport.captureScreenshot(driver,result.getName());
				testLog.log(LogStatus.FAIL,testLog.addScreenCapture(screenshot_path));								
			}
			driver.manage().deleteAllCookies();	
			//driver.quit();
	}
	
	@AfterClass
	public void LogReport(){
		extentReport.endTest(testLog);
		extentReport.flush();				
		}
}
