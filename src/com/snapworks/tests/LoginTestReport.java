package com.snapworks.tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import library.Utility;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//Added below one part of report functionaliy
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.snapworks.pages.LoginPage;

public class LoginTestReport {
	private WebDriver driver;
	private LoginPage loginPage;
	// private HomePage homePage;
	private ExtentReports report;
	private ExtentTest testLog;
	// private ITestResult Result1;

	String applicationURL;
	String name;
	String password;

	// Method to Launch the application in Chrome browser
	@BeforeClass
	public void StartReport() {
		report = ExtentReport.startReportInstance();
	}

	@BeforeMethod
	@Parameters({ "Browser", "name", "password", "url" })

	public void launchBrowser(String BrowserName, String name, String password, String url) {
		this.name = name;
		this.password = password;
		this.applicationURL = url;
		String userdirectory = System.getProperty("user.dir");
		String chromedriverpath = userdirectory + "\\chromedriver.exe";
		driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", chromedriverpath);
		driver.get(applicationURL);
		driver.manage().window().maximize(); // will maximize the browser
		// Report
		// ExtentTest testLog=report.startTest("Launching SNAP WORKS");
		// testLog.log(LogStatus.INFO,"SNAP WORKS Launched");
	}

	@Test
	public void verifyLoginFunctionality() throws InterruptedException {
		loginPage = new LoginPage(driver);
		// Calling loginUser Method to validate login UserName and Password
		// Navigate from login screen to Home screen after login success
		// ExtentTest testLog=report.startTest("SNAP WORK LOGIN");
		loginPage.loginUser(name, password);
		String LoginMsg = driver
				.findElement(By.xpath(
						"/html/body/app-root/div/div[2]/div/div[3]/div/app-login/div/div[2]/div[3]/div[4]/div/div"))
				.getText();
		// assertFalse(logtxt.isDisplayed(),"Login Failed");
		// assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");
		// Report
		Assert.assertEquals(LoginMsg, "Username or password is");
		testLog.log(LogStatus.FAIL, "SNAP WORKS APPLICATION LOGIN IS NOT SUCCESSFUL");
	}

	@Test
	public void verifyRememberMeCheckedFunctionality() throws InterruptedException {
		loginPage = new LoginPage(driver);
		// Calling loginUser Method to validate login UserName and Password
		// Navigate from login screen to Home screen after login success
		loginPage.loginUserWithRememberMe(name, password);
		// homePage.getLoginPage();
		boolean remember = loginPage.verifyRememberMeFunctionality(name);
		assertTrue(remember, "User Name not displayed");
		// Report
		ExtentTest testLog = report.startTest("SNAP WORK USER NAME NOT DISPLAY");
		testLog.log(LogStatus.INFO, "User Name not displayed");
	}

	@Test
	public void verifyRememberMeUnCheckedFunctionality() throws InterruptedException {
		loginPage = new LoginPage(driver);
		// Calling loginUser Method to validate login UserName and Password
		// Navigate from login screen to Home screen after login success
		// homePage = loginPage.loginUser(name, password);
		// homePage.getLoginPage();
		boolean remember = loginPage.verifyRememberMeFunctionality(name);
		assertFalse(remember, "User Name displayed");
		// Report
		ExtentTest testLog = report.startTest("SNAP WORK USER NAME DISPLAY");
		testLog.log(LogStatus.INFO, "User Name displayed");
	}

	@AfterMethod
	public void getScrnCapture(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			ExtentTest testLog = report.startTest(result.getName());
			testLog.log(LogStatus.FAIL, result.getThrowable());
			String screenshot_path = ExtentReport.captureScreenshot(driver, result.getName());
			testLog.log(LogStatus.FAIL, testLog.addScreenCapture(screenshot_path));
		}
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	@AfterClass
	public void LogReport() {
		report.endTest(testLog);
		report.flush();
	}
}
