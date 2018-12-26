package com.snapworks.tests;

import static org.testng.Assert.assertFalse;
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
import com.snapworks.pages.HomePage;
import com.snapworks.pages.LoginPage;

public class LoginTest {
	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
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
		} else if (BrowserName.equalsIgnoreCase("firefox")) {
			String firefoxDriver = userdirectory + "\\geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", firefoxDriver);
			driver = new FirefoxDriver();
			driver.get(applicationURL);
			driver.manage().window().maximize();
		}

		// String chromedriverpath = userdirectory + "\\chromedriver.exe";
		// System.setProperty("webdriver.chrome.driver", chromedriverpath);
		// driver = new ChromeDriver();
		// driver.get(applicationURL);
		// driver.manage().window().maximize();

	}

	@Test
	public void verifyLoginFunctionality() throws InterruptedException {
		loginPage = new LoginPage(driver);
		// Calling loginUser Method to validate login UserName and Password
		// Navigate from login screen to Home screen after login success
		homePage = loginPage.loginUser(name, password);
		assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");
		ExtentTest testLog = extentReport.startTest("SNAP WORK LOGIN");// For Name
		testLog.log(LogStatus.INFO, "SNAP WORKS APPLICATION LOGIN IS SUCCESSFUL");

	}

	@Test
	public void verifyRememberMeCheckedFunctionality() throws InterruptedException {
		loginPage = new LoginPage(driver);
		// Calling loginUser Method to validate login UserName and Password
		// Navigate from login screen to Home screen after login success
		homePage = loginPage.loginUserWithRememberMe(name, password);
		homePage.getLoginPage();
		boolean remember = loginPage.verifyRememberMeFunctionality(name);
		assertTrue(remember, "Rememberme not checked and User Name not displayed");
		// Report
		ExtentTest testLog = extentReport.startTest("REMEBERME CHECKED");
		testLog.log(LogStatus.INFO, "Rememberme checked and User Name displayed");

	}

	@Test
	public void verifyRememberMeUnCheckedFunctionality() throws InterruptedException {
		loginPage = new LoginPage(driver);
		boolean remember = false;
		// Calling loginUser Method to validate login UserName and Password
		// Navigate from login screen to Home screen after login success
		homePage = loginPage.loginUser(name, password);
		homePage.getLoginPage();
		remember = loginPage.verifyRememberMeFunctionality(name);
		if (remember == true) {

			homePage = loginPage.loginUserWithRememberMe(name, password);
			homePage.getLoginPage();
			remember = loginPage.verifyRememberMeFunctionality(name);

		}
		assertFalse(remember, "User Name displayed");

		ExtentTest testLog = extentReport.startTest("REMEMBERME UNCHECKED");
		testLog.log(LogStatus.INFO, "Remeberme unchecked and User Name not displayed");

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
