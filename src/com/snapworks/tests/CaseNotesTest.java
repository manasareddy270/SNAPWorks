package com.snapworks.tests;

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

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.snapworks.pages.AddCaseNotePage;
import com.snapworks.pages.AnnouncementsPage;
import com.snapworks.pages.CaseNotesPage;
import com.snapworks.pages.ContactHistoryPage;
import com.snapworks.pages.HomePage;
import com.snapworks.pages.LoginPage;
import com.snapworks.pages.SearchPage;

public class CaseNotesTest {

	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchPage searchPage;
	private ContactHistoryPage contactHistoryPage;
	private AnnouncementsPage announcementsPage;
	private CaseNotesPage caseNotesPage;
	private AddCaseNotePage addCaseNotePage;
	private ExtentReports extentReport;
	private ExtentTest testLog;
	// Declare a variable to store the application URL
	String applicationURL;
	String name;
	String password;

	// Method to Launch the application in Chrome browser
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
	public void caseNotesTest() throws InterruptedException {
		Random random = new Random();
		String id = String.format("%04d", random.nextInt(10000));
		String taskType = "Referral";
		String type = "Client Contact";
		String content = "Client Contact" + id;
		String searchValue = "Client Contact" + id;
		loginPage = new LoginPage(driver);
		// Calling loginUser Method to validate login UserName and Password
		// Navigate from login screen to Home screen after login success
		homePage = loginPage.loginUser(name, password);
		// navigating to Search screen from home screen by clicking on Search Tab
		searchPage = homePage.getSearchPage();
		// get the Search results by Task Type dropdown option
		searchPage.getSearchResults(taskType);
		assertTrue(searchPage.clickTaskType().isDisplayed(), "Search results not found for looking taskType");
		contactHistoryPage = searchPage.getContactHistoryPage();

		caseNotesPage = contactHistoryPage.getCaseNotesPage();
		addCaseNotePage = caseNotesPage.getAddCaseNotePage();
		assertTrue(addCaseNotePage.addcaseNotes(type, content).isDisplayed(),
				"New Case Notes is not added successfully");
		caseNotesPage = addCaseNotePage.getcaseNotesPage();
		assertTrue(caseNotesPage.getFilterdResults(searchValue), "Duplicate records are present");
		ExtentTest testLog = extentReport.startTest("CREATE CASE NOTES AND VERIFED CREATED RECORD");
		testLog.log(LogStatus.INFO, "Case notes record is updated successfully");
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
		driver.quit();
	}

	@AfterClass
	public void LogReport() {
		extentReport.endTest(testLog);
		extentReport.flush();
	}
}
