package com.snapworks.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
import com.snapworks.pages.AppointmentHistoryPage;
import com.snapworks.pages.AppointmentPage;
import com.snapworks.pages.AssessmentPage;
import com.snapworks.pages.ContactHistoryPage;
import com.snapworks.pages.HomePage;
import com.snapworks.pages.LoginPage;
import com.snapworks.pages.SearchPage;

public class AppointmentTest {
	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchPage searchPage;
	private ContactHistoryPage contactHistoryPage;
	private AppointmentPage appointmentPage;
	private AppointmentHistoryPage appointmentHistoryPage;
	private AssessmentPage assessmentPage;
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

	@Test
	public void search() throws InterruptedException {

		// Declaring Variables

		String taskType = "Referral";
		String timeHr = "09";
		String timeMin = "30";
		String appMdn = "AM";
		String appType = "Assessment";
		String appStatus = "Re-schedule";
		String appMtd = "In Office";
		String apploc = "Austin";
		String newtaskType = "Appointment";
		String clientName = "";
		loginPage = new LoginPage(driver);
		// Calling loginUser Method to validate login UserName and Password
		// Navigate from login screen to Home screen after login success
		homePage = loginPage.loginUser(name, password);
		assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");
		searchPage = homePage.getSearchPage();
		searchPage.getSearchResults(taskType);
		searchPage.clickTaskType();
		contactHistoryPage = searchPage.getContactHistoryPage();
		appointmentPage = contactHistoryPage.getAppointmentPage();
		// appointmentPage = searchPage.getAppoinmentsPage();
		String expected = "Appointment\nAppointment scheduled";
		assertEquals(appointmentPage.scheduleAppointment(timeHr, timeMin, appMdn, appType, appStatus, appMtd, apploc),
				expected);
		ExtentTest testLog = extentReport.startTest("APPOINTMENT IS SCHEDULED");
		testLog.log(LogStatus.INFO, "Appointment is scheduled sucessfully");

		appointmentHistoryPage = appointmentPage.getAppointmentHistoryPage();
		assertTrue(appointmentHistoryPage.verifyappCreated(timeHr, timeMin, appMdn),
				"Appointment scheduled not successfully");
		ExtentTest testLog1 = extentReport.startTest("VERIFY SCHEDULED APPOINTMENT ");
		testLog1.log(LogStatus.INFO, "Appointment History table is updated with the new record");

		clientName = appointmentHistoryPage.getClientName();
		searchPage = appointmentHistoryPage.getSearchPage();

		searchPage.selectFirstTask(newtaskType, clientName);
		appointmentPage = searchPage.getAppoinmentsPage();
		expected = "Appointment\nRecord updated successfully.";
		assertEquals(appointmentPage.scheduleAppointment(timeHr, timeMin, appMdn, appType, appStatus, appMtd, apploc),
				expected);
		ExtentTest testLog2 = extentReport.startTest("CHANGE SCHEDULED APPOINTMENT STATUS TO SHOW");
		testLog2.log(LogStatus.INFO, "Appointment Status=SHOW updated sucessfully");
		assessmentPage = appointmentPage.getAssessmentPage();

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
