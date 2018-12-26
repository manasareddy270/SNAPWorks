package com.snapworks.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.snapworks.pages.AnnouncementsPage;
import com.snapworks.pages.AssessmentPage;
import com.snapworks.pages.BarrierPage;
import com.snapworks.pages.ContactDetailsPage;
import com.snapworks.pages.ContactHistoryPage;
import com.snapworks.pages.EducationCertificationsPage;
import com.snapworks.pages.EmploymentPlanDetailsPage;
import com.snapworks.pages.EmploymentPlanPage;
import com.snapworks.pages.HomePage;
import com.snapworks.pages.LoginPage;
import com.snapworks.pages.SearchPage;
import com.snapworks.pages.SkillsandStrength;
import com.snapworks.pages.TestsandScoresPage;
import com.snapworks.pages.WorkHistoryPage;

public class AssessmentTest {

	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchPage searchPage;
	private AssessmentPage assessmentPage;
	private EducationCertificationsPage educationCertificationsPage;
	private SkillsandStrength skillsandStrengthPage;
	private TestsandScoresPage testsandScoresPage;
	private BarrierPage barrierPage;
	private WorkHistoryPage workHistoryPage; 
	private EmploymentPlanPage employmentPlanPage;
	private EmploymentPlanDetailsPage employmentPlanDetailsPage;
	private ExtentReports extentReport;
	private ExtentTest testLog;
	
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
	//Method to validate contact history and contact details
	@Test
	public void contactHistoryTest() throws InterruptedException {
		
		String columnName = "Status";
		String taskType = "Perform Assessment";
		String ClntNm="";
		String highschoolattended = "Test";
		String yrOfSchlGrad = "2016";
		String highestschl = "Masters";
		String driversLicense = "Yes";
		String skillsStrengths = "Yes";
		String testScores = "Yes";
		String family = "Yes";
		String Personal = "No";
		String transportaion = "No";
		String empSecurity = "No";
		String employment = "Yes";
		String priorJobExp = "Yes";
		loginPage = new LoginPage(driver);
		homePage = loginPage.loginUser(name, password);
		assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");			
		searchPage= homePage.getSearchPage();
	
		searchPage.selectFirstTask(taskType, ClntNm);
		assessmentPage=searchPage.getAssessmentSummaryPage();
		
		assertTrue(assessmentPage.getAssessmentSummary(columnName), "Assessment Failed");
		ExtentTest testLog=extentReport.startTest("CHECK ASSESSMENT");
		testLog.log(LogStatus.INFO,"Navigates to Education & Certification Page");
		
		educationCertificationsPage=assessmentPage.getEducationCertificationsPage();
		String expected= "Assessment-Educations & Certifications\nRecord added successfully.";
		SoftAssert sa = new SoftAssert();
		/////////////
		sa.assertEquals(educationCertificationsPage.SaveEducationDetails(highschoolattended, yrOfSchlGrad, highestschl), expected);
		ExtentTest testLog1=extentReport.startTest("ADD EDUCATION DETAILS");
		testLog1.log(LogStatus.INFO,"Education & Certification Details added Successfully");
		skillsandStrengthPage = educationCertificationsPage.getSkillsandStrengthPage();
		
		expected="Assessment-Skills & Strengths\nRecord added successfully.";
		sa.assertEquals(skillsandStrengthPage.SaveSkillsandStrengths(driversLicense, skillsStrengths), expected);
		ExtentTest testLog2=extentReport.startTest("ADD SKILLS AND STRENGHTS");
		testLog2.log(LogStatus.INFO,"Assessment-Skills & Strengths Record added successfully.");
		testsandScoresPage = skillsandStrengthPage.getTestsandScoresPage();
		
		expected="Assessment-Tests & Score\nRecord added successfully.";
		sa.assertEquals(testsandScoresPage.SaveTestScores(testScores), expected);
		ExtentTest testLog3=extentReport.startTest("ADD TEST AND SCORE");
		testLog3.log(LogStatus.INFO,"Assessment-Tests & Score Record added successfully.");
		barrierPage = testsandScoresPage.getBarriersPage();
		
		expected="Assessment-Barriers\nRecord added successfully.";
		sa.assertEquals(barrierPage.SaveBarriers(family ,Personal, transportaion), expected);
		ExtentTest testLog4=extentReport.startTest("ADD BARRIERS");
		testLog4.log(LogStatus.INFO,"Assessment-Barriers Record added successfully.");
		workHistoryPage = barrierPage.getWorkHistoryPage();
		
		
		expected="Finalize Assessment\nRecord added successfully.";
		sa.assertEquals(workHistoryPage.WorkHistoryPageDetails(empSecurity,employment,priorJobExp), expected);
		ExtentTest testLog5=extentReport.startTest("ADD ASSESSMENT WORK HISTORY AND FINALIZE ASSESSMENT");
		testLog5.log(LogStatus.INFO,"Finalize Assessment Record added successfully.");
		assessmentPage = workHistoryPage.getAssessmentPage();
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
				driver.quit();
		}
		
		@AfterClass
		public void LogReport(){
			extentReport.endTest(testLog);
			extentReport.flush();				
			}
}
