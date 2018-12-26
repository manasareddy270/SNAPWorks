package com.snapworks.tests;

import static org.testng.Assert.assertTrue;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.snapworks.pages.AdminAnnouncementDetails;
import com.snapworks.pages.AdminAnnouncementSummary;
import com.snapworks.pages.ApplicationConfigurationPage;
import com.snapworks.pages.HomePage;
import com.snapworks.pages.LoginPage;
import com.snapworks.pages.ProviderDetailsPage;
import com.snapworks.pages.ProviderManagementPage;

public class SNAPAdminFullTest {
	private WebDriver driver;
	private ExtentReports extentReport;
	private ExtentTest testLog;
	private LoginTest loginTest;
	private LoginPage loginPage;
	private HomePage homePage;
	private AdminAnnouncementSummary adminAnnouncementSummary;
	private AdminAnnouncementDetails adminAnnouncementDetails;
	private ApplicationConfigurationPage applicationConfigurationPage;
	private ProviderManagementPage providerManagementPage;
	private ProviderDetailsPage providerDetailsPage;
	
	String applicationURL;
	String name;
	String password;
	
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
		//@Parameters({"name", "password", "url" ,"adminlogin", "adminpassword", "adminurl" })
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

			//adminAnnouncementSummary.getAdminAnnouncementsSummary(columnname,title);
			String columnname1="Code";
			String title1="EL";
			applicationConfigurationPage = adminAnnouncementSummary.getAppConfig();
			applicationConfigurationPage.searchConfig();
			String  descriptionText= applicationConfigurationPage.getAdminApplicationCingurationDetails(columnname1, title1,true);
			applicationConfigurationPage.editConfiguration(true,"");
			applicationConfigurationPage.getAdminApplicationCingurationDetails(columnname1, title1,false);
			applicationConfigurationPage.editConfiguration(false,descriptionText);
			
			providerManagementPage=applicationConfigurationPage.getProviderManagementPage();
			String ProviderName= "Test"+id;
			String Fname = "Test"+id;
			String LName = "Test"+id;
			String Address1 = "Address1"+id;
			String Address2 = "Address2"+id;
			String City = "Little Rock";
			String State = "Arkansas";
			String zip = "72201";
			String phone = "501111"+id;
			String Fax = "501111"+id;
			String email = "test"+id+"@test.com";
			
			String officeid = String.format("%03d", random.nextInt(1000));
			String OfficeProviderName ="OfficeTest" +officeid;
			String OfficeFname = "OfficeTest" +officeid;
			String OfficeLName = "OfficeTest" +officeid;
			String OfficeAddressLine1 = "officeAdd" +officeid;
			String officeAddressLine2 = "Office Add 2" +officeid;
			String officeCity = "Little Rock";
			String officeState = "Arkansas";
			String OfficeZip = "72201";
			String OfficePhone = "5012222"+officeid;
			String OfficeFax = "5012222"+officeid;
			String officeEmail = "test"+id+"@test.com";
			providerDetailsPage = providerManagementPage.getProviderDetails();
			providerDetailsPage.enterproviderDetails(ProviderName,Fname, LName, Address1, Address2 , City ,  State,  zip,  phone,  Fax, email);
			providerDetailsPage.AssignproviderCounty();
			providerDetailsPage.AssignProviderOffice();
			providerDetailsPage.enterproviderOfficeDetails(OfficeProviderName, OfficeFname, OfficeLName, OfficeAddressLine1, 
					officeAddressLine2,officeCity, officeState, OfficeZip, OfficePhone, OfficeFax, officeEmail);
			providerDetailsPage.getStaffManagemet(Fname, LName, phone, ProviderName, email);
			assertTrue(providerDetailsPage.searchStaff(email), "Duplicate records are present");
			
		}
		@AfterMethod
		public void getScrnCapture(ITestResult result)
			{				
				if(result.getStatus()==ITestResult.FAILURE)
				{	
					ExtentTest testLog=extentReport.startTest(result.getName());
					testLog.log(LogStatus.FAIL, result.getThrowable());
					String screenshot_path=ExtentReport.captureScreenshot(driver,result.getName());
					testLog.log(LogStatus.FAIL,testLog.addScreenCapture(screenshot_path));								
				}
				driver.manage().deleteAllCookies();	
				//driver.quit();
		}
		
		@AfterClass
		public void LogReport(){
			extentReport.endTest(testLog);
			extentReport.flush();				
			}	
}
