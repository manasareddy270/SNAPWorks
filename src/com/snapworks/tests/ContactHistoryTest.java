package com.snapworks.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
import com.snapworks.pages.ContactDetailsPage;
import com.snapworks.pages.ContactHistoryPage;
import com.snapworks.pages.HomePage;
import com.snapworks.pages.LoginPage;
import com.snapworks.pages.SearchPage;

public class ContactHistoryTest {
	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchPage searchPage;
	private AnnouncementsPage announcementsPage;
	private ContactHistoryPage contactHistoryPage;
	private ContactDetailsPage contactDetailsPage;
	private ExtentReports extentReport;
	private ExtentTest testLog;

	String applicationURL;

	String name;
	String password;

	@BeforeClass
	public void StartReport() {
		extentReport = ExtentReport.startReportInstance();
	}

	// Method to Launch the application in Chrome browser
	@BeforeMethod
	@Parameters({ "Browser", "name", "password", "url" })

	public void launchBrowser(String BrowserName, String name, String password, String url) {
		this.name = name;
		this.password = password;
		this.applicationURL = url;
		String userdirectory = System.getProperty("user.dir");

		if (BrowserName.equalsIgnoreCase("Chrome")) {
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

	// Method to validate contact history and contact details
	@Test
	public void contactHistoryTest() throws InterruptedException {
		// variable declaration
		Random random = new Random();
		String taskType = "Referral";
		String id = String.format("%04d", random.nextInt(10000));
		String method = "Email";
		String reason = "Appointment Reminder";
		String outcome = "Accepted Participation";
		String ctNotes = "Test" + id;

		loginPage = new LoginPage(driver);
		// Calling loginUser Method to validate login UserName and Password
		// Navigate from login screen to Home screen after login success
		homePage = loginPage.loginUser(name, password);
		assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");
		searchPage = homePage.getSearchPage();
		searchPage.getSearchResults(taskType);
		searchPage.clickTaskType();
		contactHistoryPage = searchPage.getContactHistoryPage();
		contactDetailsPage = contactHistoryPage.getContactDetailsPage();
		// Calling saveContactDetails to validate New contact details is saving
		// successfully
		// returning from contact details screen to contact history screen to check new
		// contact updated in history table
		boolean actual = contactDetailsPage.saveContactDetails(method, reason, outcome, ctNotes);
		assertTrue(actual, "Contact Details are Saved Successfully");
		ExtentTest testLog = extentReport.startTest("ADD CONTACT DETAILS");
		testLog.log(LogStatus.INFO, "Contact details updared or created sucssessfully");
		contactHistoryPage = contactDetailsPage.getContactHistoryPage();

		// Calling xxxxx method to know whether created contact details are updated in
		// the Contact history table
		String expected = "Contact History is updated";
		assertEquals(contactHistoryPage.verifyContactHistoryUpdated(method, reason, outcome, ctNotes), expected);
		ExtentTest testLog1 = extentReport.startTest("VERIFY CONTACT DETAILS UPDATED OR CREATED");// For Name
		testLog1.log(LogStatus.INFO, "Verified newly added contact details in contact history page");

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
