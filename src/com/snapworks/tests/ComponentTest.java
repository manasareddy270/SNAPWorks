package com.snapworks.tests;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.snapworks.pages.ComponentsDetailsPage;
import com.snapworks.pages.ComponentsSummaryPage;
import com.snapworks.pages.CorrespondencePage;
import com.snapworks.pages.EmploymentPlanPage;
import com.snapworks.pages.HomePage;
import com.snapworks.pages.LoginPage;
import com.snapworks.pages.SearchPage;
import com.snapworks.pages.TrackComponentPage;


public class ComponentTest {
	
	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchPage searchPage;
	private ExtentReports extentReport;
	private ExtentTest testLog;
	private ComponentsSummaryPage componentsSummaryPage;
	private ComponentsDetailsPage componentsDetailsPage;
	private CorrespondencePage correspondencePage;
	private EmploymentPlanPage employmentPlanPage;
	private TrackComponentPage trackComponentPage;
	
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
	public void addComponents() throws InterruptedException {
		String taskType = "Component";
		String comp = "English as a 2nd Language";
		String Provider = "Shorter College in Arkansas";
		String hrs ="2";
		String compnotes = "Test";
		String StartDate="";
		String EndDate="";
		String columnName="Met Plan?";
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
		componentsSummaryPage= searchPage.getComponentPage();
		boolean value = componentsSummaryPage.clickAddNew();
		if(value==true) {
			correspondencePage = componentsSummaryPage.getCorrespondencePage();
		}
		else {
			employmentPlanPage = componentsSummaryPage.getEmploymentPlanPage();
			List<Object> values = (List<Object>) employmentPlanPage.getTopRecordDates();
			
			StartDate = (String) values.get(0);
			EndDate = (String) values.get(1);
			componentsSummaryPage = employmentPlanPage.getComponentsPage();
			componentsDetailsPage=componentsSummaryPage.getComponentsDetailsPage();
			componentsDetailsPage.addComponent(comp, Provider, hrs, compnotes,StartDate, EndDate);
			componentsSummaryPage=componentsDetailsPage.getCompSummaryPage();
			trackComponentPage=componentsSummaryPage.getTrackComponent();
			trackComponentPage.completeMetPlan(columnName);
		}
		
	
	}
}
