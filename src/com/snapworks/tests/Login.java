package com.snapworks.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.snapworks.pages.AppointmentHistoryPage;
import com.snapworks.pages.AppointmentPage;
import com.snapworks.pages.AssessmentPage;
import com.snapworks.pages.BarrierPage;
import com.snapworks.pages.ContactHistoryPage;
import com.snapworks.pages.EducationCertificationsPage;
import com.snapworks.pages.HomePage;
import com.snapworks.pages.LoginPage;
import com.snapworks.pages.SearchPage;
import com.snapworks.pages.SkillsandStrength;
import com.snapworks.pages.TestsandScoresPage;
import com.snapworks.pages.WorkHistoryPage;;

public class Login {

	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchPage searchPage;
	private ContactHistoryPage contactHistoryPage;
	private AppointmentPage appointmentPage;
	private AppointmentHistoryPage appointmentHistoryPage;
	private AssessmentPage assessmentPage;
	private EducationCertificationsPage educationCertificationsPage;
	private SkillsandStrength skillsandStrengthPage;
	private TestsandScoresPage testsandScoresPage;
	private BarrierPage barrierPage;
	private WorkHistoryPage workHistoryPage;

	// Declare a variable to store the application URL
	String applicationURL;

	String name;
	String password;

	// Method to Launch the application in Chrome browser
	@BeforeMethod
	@Parameters({ "Browser", "name", "password", "url" })

	public void launchBrowser(String BrowserName, String name, String password, String url) {
		this.name = name;
		this.password = password;
		this.applicationURL = url;

		String userdirectory = System.getProperty("user.dir");
		String chromedriverpath = userdirectory + "\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromedriverpath);
		driver = new ChromeDriver();
		// driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.get(applicationURL);
		// driver.manage().deleteAllCookies();
		driver.manage().window().maximize(); // will maximize the browser
	}

	// Method to execute search scenario
	@Test
	public void search() throws InterruptedException {

		// Declaring Variables

		String taskId = "100101";
		String columnName = "Task Type";
		String taskType = "Appointment";
		String pattern = "MM/dd/yyyy";
		// SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		// Date appDate = simpleDateFormat.parse("09/17/2018");
		// String appDate = simpleDateFormat.format(new Date());
		// String appstatus = "No Show";
		String appDate = "09/30/2018";

		String timeHr = "09";
		String timeMin = "30";
		String appMdn = "AM";
		String appType = "Assessment";
		String appStatus = "Re-schedule";
		String appMtd = "In Office";
		String apploc = "Austin";
		// Creating LoginPage class instance to pass ChromeDriver
		loginPage = new LoginPage(driver);
		// Calling loginUser Method to validate login UserName and Password
		// Navigate from login screen to Home screen after login success
		homePage = loginPage.loginUser(name, password);

		Thread.sleep(100);
		// calling getSearchPage Method to navigate from Home Screen to Search Screen by
		// clicking on Search Tab
		searchPage = homePage.getSearchPage();
		// Calling getSearchResults Method to get the search results based on user
		// choice task type
		searchPage.getSearchResults(taskType);
		// Calling getTable Method to navigate from search screen to Appointment screen
		// based on user choice taskId or task type in the result table
		searchPage.clickTaskType();
		contactHistoryPage = searchPage.getContactHistoryPage();
		appointmentPage = contactHistoryPage.getAppointmentPage();
		// Calling scheduleAppointment method to Save the schedule
		// to validate appointment was saved successfully or any error occurred by
		// displaying in a toast notification
		appointmentPage.scheduleAppointment(timeHr, timeMin, appMdn, appType, appStatus, appMtd, apploc);
		appointmentHistoryPage = appointmentPage.getAppointmentHistoryPage();
		// String appDateTime =
		// appointmentHistoryPage.getAppointmentHistoryTable("Appointment Date & Time");
		// System.out.println(appDateTime);
		// AssertJUnit.assertTrue("Appointment Not Successful",
		// (appDateTime.contains(appDate+" "+timeHr+":"+timeMin+" "+appMdn)));

		// appointmentPage.getSaveAppStatus(appstatus);

	}

	// @Test
	/*
	 * public void fillAssessment() throws InterruptedException{ String taskId =
	 * "100101"; String columnName = "Task Type"; String taskType = "Referral";
	 * String highschoolattended = "Test"; String yrOfSchlGrad = "2016"; String
	 * highestschl = "Masters"; String driversLicense = "Yes"; String
	 * skillsStrengths = "No"; String testScores = "No"; String family = "No";
	 * String Personal = "No"; String transportaion = "No"; String empSecurity =
	 * "No"; String employment = "No"; String priorJobExp = "No"; //Creating
	 * LoginPage class instance to pass ChromeDriver loginPage = new
	 * LoginPage(driver); // Calling loginUser Method to validate login UserName and
	 * Password // Navigate from login screen to Home screen after login success
	 * homePage = loginPage.loginUser(name, password); // calling getSearchPage
	 * Method to navigate from Home Screen to Search Screen by clicking on Search
	 * Tab searchPage = homePage.getSearchPage(); // Calling getSearchResults Method
	 * to get the search results based on user choice task type
	 * searchPage.getSearchResults(taskType); // Calling getTable Method to navigate
	 * from search screen to Appointment screen //based on user choice taskId or
	 * task type in the result table appointmentPage = searchPage.getTable(taskId,
	 * columnName); assessmentPage = appointmentPage.getAssessmentPage(); String
	 * column ="Status";
	 * 
	 * educationCertificationsPage = assessmentPage.getAssessmentSummary(column);
	 * 
	 * educationCertificationsPage.SaveEducationDetails(highschoolattended,
	 * yrOfSchlGrad, highestschl); skillsandStrengthPage =
	 * educationCertificationsPage.getSkillsandStrengthPage();
	 * 
	 * skillsandStrengthPage.SaveSkillsandStrengths(driversLicense,
	 * skillsStrengths); testsandScoresPage =
	 * skillsandStrengthPage.getTestsandScoresPage();
	 * 
	 * testsandScoresPage.SaveTestScores(testScores); barrierPage =
	 * testsandScoresPage.getBarriersPage();
	 * 
	 * 
	 * barrierPage.SaveBarriers(family ,Personal, transportaion); workHistoryPage =
	 * barrierPage.getWorkHistoryPage();
	 * workHistoryPage.WorkHistoryPage(empSecurity,employment,priorJobExp); }
	 */

	// @After
	public void quitBrowser() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

}