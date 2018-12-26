package com.snapworks.tests;

import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.snapworks.pages.AnnouncementsPage;
import com.snapworks.pages.ClientInfoPage;
import com.snapworks.pages.ClientSearchPage;
import com.snapworks.pages.ContactHistoryPage;
import com.snapworks.pages.CreateTaskPage;
import com.snapworks.pages.HomePage;
import com.snapworks.pages.LoginPage;
import com.snapworks.pages.NonComplianceDetailsPage;
import com.snapworks.pages.NonCompliancePage;
import com.snapworks.pages.SearchPage;

public class NonComplianceTest {
	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchPage searchPage;
	private ContactHistoryPage contactHistoryPage;
	private AnnouncementsPage announcementsPage;
	private NonCompliancePage nonCompliancePage;
	private NonComplianceDetailsPage nonComplianceDetailsPage;
	private CreateTaskPage createTaskPage;
	private ClientInfoPage clientInfoPage;
	private ClientSearchPage clientsearchPage;
	// Declare a variable to store the application URL
	String applicationURL;
	String name;
	String password;
	String userdirectory = System.getProperty("user.dir");
	// Method to Launch the application in Chrome browser
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
	public void nonComplainceTest() throws InterruptedException, ParseException {

		String taskType = "Referral";
		Date now = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
		String ncDiscoveryDate = dateformat.format(now);
		String ClientName ="507";
		String ncMonth = dateformat.format(now);
		String reason = "Lack of transportation";
		String program = "SNAP";
		String cause = "NO";
		String user = "Provider";
		loginPage = new LoginPage(driver);
		// Calling loginUser Method to validate login UserName and Password
		// Navigate from login screen to Home screen after login success
		homePage = loginPage.loginUser(name, password);
		// checking login successfully
		assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");
		// navigating to Search screen from home screen by clicking on Search Tab
		//searchPage = homePage.getSearchPage();
		clientsearchPage= homePage.getClientSearchPage();
		clientsearchPage.clientSearch(ClientName);
		clientInfoPage = clientsearchPage.getClientInfoPage();
		clientInfoPage.validateClientInfoTabs();
		contactHistoryPage = clientInfoPage.getContactHistoryPage();
		nonCompliancePage = contactHistoryPage.getNonComplaincePage();
		nonComplianceDetailsPage = nonCompliancePage.getAddNonComplianceDetailsPage(user);
		List<String> Options = nonComplianceDetailsPage.getNonComplainceSummarryPage(ncDiscoveryDate, ncMonth, reason,program, cause);
		reason  = Options.get(0);
		ncDiscoveryDate = Options.get(1);
		nonCompliancePage = nonComplianceDetailsPage.getNonComplaincePage();
		
		nonCompliancePage.verifyNonComplianceSummaryCreated(ncDiscoveryDate, ncMonth, reason, user);
		String clientName = nonCompliancePage.getClientName();
		loginPage= nonCompliancePage.logOut();
		driver.manage().deleteAllCookies();
		Thread.sleep(3000);
		//Worker Portal
		
		
		Thread.sleep(1000);
		driver.navigate().to("https://dhs9931dsh10/SNAPWorks/workerportal");
		Thread.sleep(1000);
		if(ExpectedConditions.alertIsPresent() != null){
	   
	    System.out.println("alert was present");
	    Alert alert = driver.switchTo().alert();
		alert.accept(); 
		}
		else{
			 System.out.println("alert was not present");  
		}
		name="mpothula";
		password="Dec12345";
		
		homePage = loginPage.loginUser(name, password);
		assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");
		Thread.sleep(3000);
		searchPage = homePage.getSearchPage();
		String newtaskType="Non-Compliance";
		
		searchPage.selectFirstTask(newtaskType, clientName);
		nonCompliancePage = searchPage.getNonComplaincePage();
		user = "County";
		nonCompliancePage.verifyNonComplianceSummaryCreated(ncDiscoveryDate, ncMonth, reason, user);
		nonComplianceDetailsPage= nonCompliancePage.getAddNonComplianceDetailsPage(user);
		nonCompliancePage = nonComplianceDetailsPage.gcGranted();
		user="";
		nonCompliancePage.verifyNonComplianceSummaryCreated(ncDiscoveryDate, ncMonth, reason, user);
		loginPage= nonCompliancePage.logOut();
		driver.manage().deleteAllCookies();
		
		Thread.sleep(3000);
		Thread.sleep(1000);
		//provider
		driver.navigate().to("https://dhs9931dsh10/SNAPWorks/providerportal");
		Thread.sleep(1000);
		/*if(ExpectedConditions.alertIsPresent()!=null){
		    System.out.println("alert was present");
		    Alert alert = driver.switchTo().alert();
			alert.accept();+
		}
		else{
		    System.out.println("alert was not present");
		      
		}*/
		name="Manasa.Pothula@arkansas.gov";
		password="ABCabc2!";
		
		homePage = loginPage.loginUser(name, password);
		assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");
		searchPage = homePage.getSearchPage();
		 newtaskType="Non-Compliance Decision";
		searchPage.selectFirstTask(newtaskType, clientName);
		//nonCompliancePage = searchPage.getNonComplaincePage();
		createTaskPage = searchPage.getCreateTask();
		createTaskPage.closeTask();
	}

	// @After
	public void quitBrowser() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}
}
