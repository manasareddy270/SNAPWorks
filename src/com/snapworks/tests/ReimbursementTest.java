package com.snapworks.tests;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
import com.snapworks.pages.AnnouncementsPage;
import com.snapworks.pages.AppointmentHistoryPage;
import com.snapworks.pages.AppointmentPage;
import com.snapworks.pages.AssessmentPage;
import com.snapworks.pages.BarrierPage;
import com.snapworks.pages.ContactHistoryPage;
import com.snapworks.pages.EducationCertificationsPage;
import com.snapworks.pages.HomePage;
import com.snapworks.pages.LoginPage;
import com.snapworks.pages.ReimbursementPage;
import com.snapworks.pages.ReimbursementsDetailsPage;
import com.snapworks.pages.SearchPage;
import com.snapworks.pages.SkillsandStrength;
import com.snapworks.pages.TestsandScoresPage;
import com.snapworks.pages.UniversalMethods;
import com.snapworks.pages.WorkHistoryPage;

public class ReimbursementTest {

	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchPage searchPage;
	private AppointmentPage appointmentPage;
	private AppointmentHistoryPage appointmentHistoryPage;
	private AssessmentPage assessmentPage;
	private EducationCertificationsPage educationCertificationsPage;
	private SkillsandStrength skillsandStrengthPage;
	private TestsandScoresPage testsandScoresPage;
	private BarrierPage barrierPage;
	private WorkHistoryPage workHistoryPage;
	private AnnouncementsPage announcementsPage;
	private ReimbursementPage reimbursementPage;
	private ReimbursementsDetailsPage reimbursementsDetailsPage;
	private UniversalMethods universalMethods;
	private ExtentReports extentReport;
	private ContactHistoryPage contactHistoryPage;
	private ExtentTest testLog;

	// Declare a variable to store the application URL
	String applicationURL;

	String name;
	String password;

	@BeforeClass
	public void StartReport() {
		extentReport = ExtentReport.startReportInstance();
	}

	@BeforeMethod
	@Parameters({ "Browser", "name", "password", "url" })

	public void launchBrowser(String BrowserName, String name, String password, String url) {
		this.name = name;
		this.password = password;
		this.applicationURL = url;
		String userdirectory = System.getProperty("user.dir");
		if (BrowserName.equalsIgnoreCase("IE")) {

			System.setProperty("webdriver.ie.driver", "\\IEDriverServer.exe");
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			ieCapabilities.setCapability("ensureCleanSession", true);
			driver = new InternetExplorerDriver();
			driver.get(applicationURL);
			driver.manage().window().maximize();
		} else if (BrowserName.equalsIgnoreCase("Chrome")) {
			String chromedriverpath = userdirectory + "\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromedriverpath);
			driver = new ChromeDriver();
			driver.get(applicationURL);
			driver.manage().window().maximize();
		}

		else if (BrowserName.equalsIgnoreCase("firefox")) {
			String firefoxDriver = userdirectory + "\\geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", firefoxDriver);
			driver = new FirefoxDriver();
			driver.get(applicationURL);
			driver.manage().window().maximize();
		}
	}

	@Test
	public void reimbursementTest() throws InterruptedException {
		Random random = new Random();
		String id = String.format("%04d", random.nextInt(10000));
		String filePath = System.getProperty("user.dir") + "\\Xpath.PNG";
		//String filePath = "C:\\Users\\mpothula\\Pictures\\Xpath.PNG";
		String vendorName = "ABC" + id;
		String reason = "Books or Educational Supplies";
		String requestedDate = "09/23/2018";
		String amount = "1000";
		String taskType = "Referral";
		String user = "Provider";
		loginPage = new LoginPage(driver);
		// Calling loginUser Method to validate login UserName and Password
		// Navigate from login screen to Home screen after login success
		homePage = loginPage.loginUser(name, password);
		// checking login successfully
		assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");
		// navigating to Search screen from home screen by clicking on Search Tab
		searchPage = homePage.getSearchPage();
		// get the Search results by Task Type dropdown option
		searchPage.getSearchResults(taskType);
		// Checking for search results in a grid
		// and trying to navigate to contact history screen after clicking on first
		// taskType anchor link
		assertTrue(searchPage.clickTaskType().isDisplayed(), "Search results not found for looking taskType");
		contactHistoryPage = searchPage.getContactHistoryPage();
		reimbursementPage = contactHistoryPage.getReimbursementPage();
		
		reimbursementsDetailsPage = reimbursementPage.getReimbursementsDetails(user);
		reimbursementsDetailsPage.fillReimbursementRequest(filePath);
		Thread.sleep(1000);
		reimbursementPage = reimbursementsDetailsPage.getReimbursementSummarryPage(vendorName, reason, requestedDate,
				amount);
		assertTrue(reimbursementPage.verifyReimbursementRecord(vendorName, user), "Reimbursement record is not updated");
		ExtentTest testLog = extentReport.startTest("CREATE REIMBURSEMENT RECORD AND VERFY THE ADDED RECORD");
		testLog.log(LogStatus.INFO, "Reimbursemnt record is created & verified");
		
		//String clientName = reimbursementPage.getClientName();
		universalMethods = new UniversalMethods(driver);
		String clientName =universalMethods.getClientName();
		loginPage = reimbursementPage.logOut();
		
		//Worker
		driver.navigate().to("https://dhs9931dsh10/SNAPWorks/workerportal");
		if(ExpectedConditions.alertIsPresent()==null){
		    System.out.println("alert was not present");
		Alert alert = driver.switchTo().alert();
		alert.accept();
		}
		else
		    System.out.println("alert was present");
		
		name="mvelas01";
		password="1qazxsw2";
		
		homePage = loginPage.loginUser(name, password);
		assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");
		searchPage = homePage.getSearchPage();
		String  newtaskType="Reimbursement Request";
		searchPage.selectFirstTask(newtaskType, clientName);
		reimbursementPage = searchPage.getReimbursement();
		user ="Admin";
		reimbursementPage.verifyReimbursementRecord(vendorName, user);
		reimbursementsDetailsPage =reimbursementPage.getReimbursementsDetails(user);
		reimbursementPage = reimbursementsDetailsPage.reimbursementDecision();
		
		universalMethods = new UniversalMethods(driver);
		loginPage = universalMethods.logOut();
		
		
		//Provider
					driver.navigate().to("https://dhs9931dsh10/SNAPWorks/providerportal");
					if(ExpectedConditions.alertIsPresent()==null){
					    System.out.println("alert was not present");
					Alert alert = driver.switchTo().alert();
					alert.accept();
					}
					else
					    System.out.println("alert was present");
					name="manasa.pothula@arkansas.gov";
					password="Password1!";
				
		
		homePage = loginPage.loginUser(name, password);
		assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");
		searchPage = homePage.getSearchPage();
		newtaskType="Reimbursement Decision";
		searchPage.selectFirstTask(newtaskType, clientName);
		reimbursementPage = searchPage.getReimbursement();
		user="Provider1";
		reimbursementPage.verifyReimbursementRecord(vendorName, user);
		reimbursementsDetailsPage =reimbursementPage.getReimbursementsDetails(user);
	}

	@AfterMethod
	public void getScrnCapture(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			ExtentTest testLog = extentReport.startTest(result.getName());
			testLog.log(LogStatus.FAIL, result.getThrowable());
			String screenshot_path = ExtentReport.captureScreenshot(driver, result.getName());
			testLog.log(LogStatus.FAIL, testLog.addScreenCapture(screenshot_path));
		}
		driver.manage().deleteAllCookies();
		//driver.quit();
	}

	@AfterClass
	public void LogReport() {
		extentReport.endTest(testLog);
		extentReport.flush();
	}
}
