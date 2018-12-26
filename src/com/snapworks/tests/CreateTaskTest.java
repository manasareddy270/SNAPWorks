package com.snapworks.tests;

import static org.testng.Assert.assertTrue;

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
import com.snapworks.pages.ContactHistoryPage;
import com.snapworks.pages.CreateTaskPage;
import com.snapworks.pages.HomePage;
import com.snapworks.pages.LoginPage;
import com.snapworks.pages.SearchPage;

public class CreateTaskTest {

	private WebDriver driver;
	private LoginTest loginTest;
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchPage searchPage;
	private ContactHistoryPage contactHistoryPage;
	private ExtentReports extentReport;
	private ExtentTest testLog;

	private CreateTaskPage createTaskPage;

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
	public void ValidateCreateTasks() throws InterruptedException {
		String taskType = "Appointment";
		String type = "Address Change";
		String assignedTo = "county";
		String assigned = "Arkansas A";
		String notes = "test";

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
		createTaskPage = contactHistoryPage.getCreateTaskPage();
		assertTrue(createTaskPage.saveNewTask(type, assignedTo, assigned, notes).contains("Task saved successfully"));
		ExtentTest testLog = extentReport.startTest("CREATE TASK");
		testLog.log(LogStatus.INFO, "Task is created successfully");
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
