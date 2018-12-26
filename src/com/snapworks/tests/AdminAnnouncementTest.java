package com.snapworks.tests;

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
import com.snapworks.pages.AdminAnnouncementDetails;
import com.snapworks.pages.AdminAnnouncementSummary;
import com.snapworks.pages.HomePage;
import com.snapworks.pages.LoginPage;

public class AdminAnnouncementTest {
	private WebDriver driver;
	private ExtentReports extentReport;
	private ExtentTest testLog;
	private LoginTest loginTest;
	private LoginPage loginPage;
	private HomePage homePage;
	private AdminAnnouncementSummary adminAnnouncementSummary;
	private AdminAnnouncementDetails adminAnnouncementDetails;
	
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
	
	public void ValidateAnnouncements() throws InterruptedException {
		Random random = new Random();
		String id = String.format("%04d", random.nextInt(10000));
		String title="Admin Announcement Test" +id;
		String type= "High";
		String discription="test"+id;
		String columnname="Title";
		
		loginPage = new LoginPage(driver);
		homePage = loginPage.loginUser(name, password);
		assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");
		adminAnnouncementSummary=homePage.getAdminAnnouncementsPage();	
		adminAnnouncementDetails = adminAnnouncementSummary.getAnnouncemntDetailspage();
		adminAnnouncementSummary=adminAnnouncementDetails.getAnnouncementDetailsPage(title, type, discription);
		adminAnnouncementSummary.getAdminAnnouncementsSummary(columnname,title);
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
