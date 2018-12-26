package com.snapworks.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.snapworks.pages.AddCaseNotePage;
import com.snapworks.pages.AppointmentHistoryPage;
import com.snapworks.pages.AppointmentPage;
import com.snapworks.pages.AssessmentPage;
import com.snapworks.pages.BarrierPage;
import com.snapworks.pages.CaseNotesPage;
import com.snapworks.pages.ComponentsDetailsPage;
import com.snapworks.pages.ComponentsSummaryPage;
import com.snapworks.pages.ContactDetailsPage;
import com.snapworks.pages.ContactHistoryPage;
import com.snapworks.pages.CorrespondencePage;
import com.snapworks.pages.CreateTaskPage;
import com.snapworks.pages.EducationCertificationsPage;
import com.snapworks.pages.EmploymentPlanDetailsPage;
import com.snapworks.pages.EmploymentPlanPage;
import com.snapworks.pages.HomePage;
import com.snapworks.pages.LoginPage;
import com.snapworks.pages.NonComplianceDetailsPage;
import com.snapworks.pages.NonCompliancePage;
import com.snapworks.pages.ReimbursementPage;
import com.snapworks.pages.ReimbursementsDetailsPage;
import com.snapworks.pages.SearchPage;
import com.snapworks.pages.SkillsandStrength;
import com.snapworks.pages.TestsandScoresPage;
import com.snapworks.pages.TrackComponentPage;
import com.snapworks.pages.UniversalMethods;
import com.snapworks.pages.WorkHistoryPage;

public class SanpWorksFullTest {
	
	private WebDriver driver;
	private LoginTest loginTest;
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchPage searchPage;
	private ContactHistoryPage contactHistoryPage;
	private ContactDetailsPage contactDetailsPage;
	private AppointmentPage appointmentPage;
	private AppointmentHistoryPage appointmentHistoryPage;
	private AssessmentPage assessmentPage; 
	private EducationCertificationsPage educationCertificationsPage;
	private SkillsandStrength skillsandStrengthPage;
	private TestsandScoresPage testsandScoresPage;
	private BarrierPage barrierPage;
	private WorkHistoryPage workHistoryPage; 
	private EmploymentPlanPage employmentPlanPage;
	private EmploymentPlanDetailsPage employmentPlanDetailsPage;
	private CreateTaskPage createTaskPage;
	private ComponentsSummaryPage componentsSummaryPage;
	private ComponentsDetailsPage componentsDetailsPage;
	private CaseNotesPage caseNotesPage;
	private AddCaseNotePage addCaseNotePage;
	private CorrespondencePage correspondencePage;
	private ExtentReports extentReport;
	private ExtentTest testLog;
	private TrackComponentPage trackComponentPage;
	private NonCompliancePage nonCompliancePage;
	private NonComplianceDetailsPage nonComplianceDetailsPage;
	private ReimbursementPage reimbursementPage;
	private ReimbursementsDetailsPage reimbursementsDetailsPage;
	private UniversalMethods universalMethods;
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
	public void SnapWorksTest() throws InterruptedException, ParseException{
		Random random = new Random();
		String id = String.format("%04d", random.nextInt(10000));
		String taskType = "Referral";
		String method = "Email";
		String reason= "Appointment Reminder";
		String outcome ="Accepted Participation";
		String ctNotes = "Test"+id;
		String timeHr = "09";
		String timeMin = "30";
		String appMdn = "AM";
		String appType = "Assessment";
		String appStatus = "Re-schedule";
		String appMtd = "In Office";
		String apploc = "Austin";
		String newtaskType = "Appointment";
		String clientName ="";
		String columnName = "Status";
		String highschoolattended = "Test";
		String yrOfSchlGrad = "2016";
		String highestschl = "Masters";
		String driversLicense = "Yes";
		String skillsStrengths = "Yes";
		String testScores = "Yes";
		String family = "Yes";
		String Personal = "No";
		String transportaion = "No";
		String empSecurity = "No";
		String employment = "No";
		String priorJobExp = "Yes";
		String expected = "";
		
		String empGST = "Full Time Employment";
		String empLST = "Part Time Employment";
		String empStatus = "Open";
		String empPlanSummarycolumn ="Status";
		String empnotes ="Test"+id;
		
		String comp = "English as a 2nd Language";
		String Provider = "Shorter College in Arkansas";
		String hrs ="2";
		String compnotes = "Test";
		
		String vendorName = "ABC" + id;
		String requestedDate = "09/23/2018";
		String amount = "1000";
		String filePath = System.getProperty("user.dir") + "\\Xpath.PNG";
		loginPage = new LoginPage(driver);
		homePage = loginPage.loginUser(name, password);
		assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");			
		searchPage= homePage.getSearchPage();
		searchPage.getSearchResults(taskType);
		searchPage.clickTaskType();
		contactHistoryPage =searchPage.getContactHistoryPage();
		contactDetailsPage = contactHistoryPage.getContactDetailsPage();
		
		SoftAssert sa = new SoftAssert();
		
		 boolean actual = contactDetailsPage.saveContactDetails(method, reason, outcome, ctNotes);
		 assertTrue(actual, "Contact Details are Saved Successfully");
		 ExtentTest testLog=extentReport.startTest("ADD CONTACT DETAILS");
		 testLog.log(LogStatus.INFO,"Contact details updared or created sucssessfully");
		 contactHistoryPage = contactDetailsPage.getContactHistoryPage();
		 
		expected="Contact History is updated";
		sa.assertEquals(contactHistoryPage.verifyContactHistoryUpdated( method, reason,  outcome,ctNotes), expected);
		ExtentTest testLog1=extentReport.startTest("VERIFY CONTACT DETAILS UPDATED OR CREATED");//For Name
		testLog1.log(LogStatus.INFO,"Verified newly added contact details in contact history page");
		
		
		//Appointment
		appointmentPage = contactHistoryPage.getAppointmentPage();
		expected = "Appointment\nAppointment scheduled";
		
		
		equals(appointmentPage.scheduleAppointment(timeHr, timeMin, appMdn, appType, appStatus, appMtd, apploc).contains(expected));
		ExtentTest testLog2=extentReport.startTest("APPOINTMENT IS SCHEDULED");
		testLog2.log(LogStatus.INFO,"Appointment is scheduled sucessfully");
		
		appointmentHistoryPage = appointmentPage.getAppointmentHistoryPage();
		sa.assertTrue(appointmentHistoryPage.verifyappCreated(timeHr, timeMin, appMdn), "Appointment scheduled not successfully");
		ExtentTest testLog3=extentReport.startTest("VERIFY SCHEDULED APPOINTMENT ");
		testLog3.log(LogStatus.INFO,"Appointment History table is updated with the new record");
		
		clientName = appointmentHistoryPage.getClientName();
		searchPage = appointmentHistoryPage.getSearchPage();
		
		searchPage.selectFirstTask(newtaskType, clientName);
		appointmentPage= searchPage.getAppoinmentsPage();
		expected = "Appointment\nRecord updated successfully.";
		sa.assertEquals(appointmentPage.scheduleAppointment(timeHr, timeMin, appMdn, appType, appStatus, appMtd, apploc), expected);
		ExtentTest testLog4=extentReport.startTest("CHANGE SCHEDULED APPOINTMENT STATUS TO SHOW");
		testLog4.log(LogStatus.INFO,"Appointment Status=SHOW updated sucessfully");
		
		// Assessment
		assessmentPage=appointmentPage.getAssessmentPage();
		clientName = assessmentPage.getClientName();
		searchPage = assessmentPage.getSearchPage();
		newtaskType= "Perform Assessment";
		searchPage.selectFirstTask(newtaskType, clientName);
		
		assertTrue(assessmentPage.getAssessmentSummary(columnName), "Assessment Failed");
		ExtentTest testLog5=extentReport.startTest("CHECK ASSESSMENT");
		testLog5.log(LogStatus.INFO,"Navigates to Education & Certification Page");
		// Assessment EducationCertification
		educationCertificationsPage=assessmentPage.getEducationCertificationsPage();
		expected= "Assessment-Educations & Certifications\nRecord added successfully.";
		sa.assertEquals(educationCertificationsPage.SaveEducationDetails(highschoolattended, yrOfSchlGrad, highestschl), expected);
		ExtentTest testLog6=extentReport.startTest("ADD EDUCATION DETAILS");
		testLog6.log(LogStatus.INFO,"Education & Certification Details added Successfully");
		// Assessment Skills and Strength
		skillsandStrengthPage = educationCertificationsPage.getSkillsandStrengthPage();		
		expected="Assessment-Skills & Strengths\nRecord added successfully.";
		sa.assertEquals(skillsandStrengthPage.SaveSkillsandStrengths(driversLicense, skillsStrengths), expected);
		ExtentTest testLog7=extentReport.startTest("ADD SKILLS AND STRENGHTS");
		testLog7.log(LogStatus.INFO,"Assessment-Skills & Strengths Record added successfully.");
		// Assessment Test and Scores
		testsandScoresPage = skillsandStrengthPage.getTestsandScoresPage();		
		expected ="Assessment-Tests & Score\nRecord added successfully.";
		assertEquals(testsandScoresPage.SaveTestScores(testScores), expected);
		ExtentTest testLog8=extentReport.startTest("ADD TEST AND SCORE");
		testLog8.log(LogStatus.INFO,"Assessment-Tests & Score Record added successfully.");
		//Assessment Barrier Page
		barrierPage = testsandScoresPage.getBarriersPage();		
		expected="Assessment-Barriers\nRecord added successfully.";
		assertEquals(barrierPage.SaveBarriers(family ,Personal, transportaion), expected);
		ExtentTest testLog9=extentReport.startTest("ADD BARRIERS");
		testLog9.log(LogStatus.INFO,"Assessment-Barriers Record added successfully.");
		// Assessment WorkHistory
		workHistoryPage = barrierPage.getWorkHistoryPage();
		expected="Finalize Assessment\nRecord added successfully.";
		assertEquals(workHistoryPage.WorkHistoryPageDetails(empSecurity,employment,priorJobExp), expected);
		ExtentTest testLog10=extentReport.startTest("ADD ASSESSMENT WORK HISTORY AND FINALIZE ASSESSMENT");
		testLog10.log(LogStatus.INFO,"Finalize Assessment Record added successfully.");
		// Return Back to Assessment
		assessmentPage = workHistoryPage.getAssessmentPage();	
		//Get the client name to get Employment page from search Page
		clientName = assessmentPage.getClientName();
		searchPage = assessmentPage.getSearchPage();
		newtaskType= "Employment Plan";
		searchPage.selectFirstTask(newtaskType, clientName);		
		employmentPlanPage = searchPage.getEmploymentPage();
		boolean rtnValue = false;
		// Get Dates from EmploymentSummary page in the form of List
		List<Object> values = (List<Object>) employmentPlanPage.getTopRecordDates();
		String StartDate = (String) values.get(0);
		String EndDate = (String) values.get(1);
		// Get Latest Employment Record data
		
		boolean value = employmentPlanPage.getLatestRcdData(empPlanSummarycolumn);
		// if value is true click addNew and pass to Employment details page and check whether new record is updated or not in Employment Summary page
		// if value is false i.e employment summary page table has Open or InProgress, click edit icon and close employment plan
		if(value == true) {
			// new employment plan
			employmentPlanDetailsPage=employmentPlanPage.getEmploymentPlanDetailsPage();
			rtnValue = employmentPlanDetailsPage.createemploymentPlan(empGST, empLST, empStatus, empnotes,StartDate,EndDate);
			employmentPlanPage=employmentPlanDetailsPage.getEmploymentPage();
			// Get client name to create Component for the same user			
			clientName = employmentPlanPage.getClientName();
			searchPage = employmentPlanPage.getSearchPage();
			newtaskType= "Component";
			searchPage.selectFirstTask(newtaskType, clientName);			
			componentsSummaryPage=searchPage.getComponentPage();
			value = componentsSummaryPage.clickAddNew();
			// If value is true goto correspondencePage
			// If value is false create new component
				if(value==true) {
					correspondencePage = componentsSummaryPage.getCorrespondencePage();
				}
				else {
					Date now = new Date();
					SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
					String ncDiscoveryDate = dateformat.format(now);
				
					String ncMonth = dateformat.format(now);
					String reason1 = "Lack of transportation";
					String program = "SNAP";
					String cause = "NO";
					String user = "Provider";
					employmentPlanPage = componentsSummaryPage.getEmploymentPlanPage();
					
				    values = (List<Object>) employmentPlanPage.getTopRecordDates();
					
					StartDate = (String) values.get(0);
					EndDate = (String) values.get(1);
					columnName = "Met Plan?";
					componentsSummaryPage = employmentPlanPage.getComponentsPage();
					componentsDetailsPage=componentsSummaryPage.getComponentsDetailsPage();
					componentsDetailsPage.addComponent(comp, Provider, hrs, compnotes,StartDate,EndDate);
					componentsSummaryPage=componentsDetailsPage.getCompSummaryPage();
					// Track Component
					trackComponentPage=componentsSummaryPage.getTrackComponent();
					trackComponentPage.completeMetPlan(columnName);	
					// Non Compliance
					nonCompliancePage = trackComponentPage.getNonComplaincePage();
					nonComplianceDetailsPage = nonCompliancePage.getAddNonComplianceDetailsPage(user);
					List<String> Options = nonComplianceDetailsPage.getNonComplainceSummarryPage(ncDiscoveryDate, ncMonth, reason,program, cause);
					reason  = Options.get(0);
					ncDiscoveryDate = Options.get(1);
					nonCompliancePage = nonComplianceDetailsPage.getNonComplaincePage();
					nonCompliancePage.verifyNonComplianceSummaryCreated(ncDiscoveryDate, ncMonth, reason, user);
					// Get client name to to complete non compliance after admin login
					clientName = nonCompliancePage.getClientName();
					loginPage= nonCompliancePage.logOut();
					name="pulaski01";
					password="1qazxsw2";
					homePage = loginPage.loginUser(name, password);
					assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");
					searchPage = homePage.getSearchPage();
					 newtaskType="Non-Compliance";
					
					searchPage.selectFirstTask(newtaskType, clientName);
					nonCompliancePage = searchPage.getNonComplaincePage();
					user = "County";
					nonCompliancePage.verifyNonComplianceSummaryCreated(ncDiscoveryDate, ncMonth, reason1, user);
					nonComplianceDetailsPage= nonCompliancePage.getAddNonComplianceDetailsPage(user);
					nonCompliancePage = nonComplianceDetailsPage.gcGranted();
					user="";
					nonCompliancePage.verifyNonComplianceSummaryCreated(ncDiscoveryDate, ncMonth, reason1, user);
					loginPage= nonCompliancePage.logOut();
					name="varathan.cs@gmail.com";
					password="1qazxsw2";
					homePage = loginPage.loginUser(name, password);
					assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");
					searchPage = homePage.getSearchPage();
					newtaskType="Non-Compliance Decision";
					searchPage.selectFirstTask(newtaskType, clientName);
					//nonCompliancePage = searchPage.getNonComplaincePage();
					createTaskPage = searchPage.getCreateTask();
					createTaskPage.closeTask();
					reimbursementPage = createTaskPage.getReimbursement();
					user= "Provider";
					reimbursementsDetailsPage = reimbursementPage.getReimbursementsDetails(user);
					reimbursementsDetailsPage.fillReimbursementRequest(filePath);
					Thread.sleep(1000);
					String reason2 = "Books or Educational Supplies";
					 
					 amount = "1000";
					reimbursementPage = reimbursementsDetailsPage.getReimbursementSummarryPage(vendorName, reason2, requestedDate, amount);
					assertTrue(reimbursementPage.verifyReimbursementRecord(vendorName, user), "Reimbursement record is not updated");
					ExtentTest testLog12 = extentReport.startTest("CREATE REIMBURSEMENT RECORD AND VERFY THE ADDED RECORD");
					testLog12.log(LogStatus.INFO, "Reimbursemnt record is created & verified");
					
					//String clientName = reimbursementPage.getClientName();
					universalMethods = new UniversalMethods(driver);
				    clientName =universalMethods.getClientName();
					loginPage = reimbursementPage.logOut();
					
					name="admin";
					password="1qazxsw2";
					homePage = loginPage.loginUser(name, password);
					assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");
					searchPage = homePage.getSearchPage();
				    newtaskType="Reimbursement Request";
					searchPage.selectFirstTask(newtaskType, clientName);
					reimbursementPage = searchPage.getReimbursement();
					user ="Admin";
					reimbursementPage.verifyReimbursementRecord(vendorName, user);
					reimbursementsDetailsPage =reimbursementPage.getReimbursementsDetails(user);
					reimbursementPage = reimbursementsDetailsPage.reimbursementDecision();
					
					universalMethods = new UniversalMethods(driver);
					loginPage = universalMethods.logOut();
					name="varathan.cs@gmail.com";
					password="1qazxsw2";
					homePage = loginPage.loginUser(name, password);
					assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");
					searchPage = homePage.getSearchPage();
					newtaskType="Reimbursement Decision";
					searchPage.selectFirstTask(newtaskType, clientName);
					reimbursementPage = searchPage.getReimbursement();
					user="Provider1";
					reimbursementPage.verifyReimbursementRecord(vendorName, user);
					reimbursementsDetailsPage =reimbursementPage.getReimbursementsDetails(user);
				}
						
		}
		else{
			
			employmentPlanDetailsPage = employmentPlanPage.getEmploymentPlanDetailsPage();
			rtnValue = employmentPlanDetailsPage.createemploymentPlan(empGST, empLST, empStatus, empnotes,StartDate,EndDate);
			employmentPlanPage=employmentPlanDetailsPage.getEmploymentPage();

			boolean statusClosed = employmentPlanPage.getEmploymentPlanSummaryCheck(empPlanSummarycolumn);
			if(statusClosed == true) {
				
				//Client Information page
				// TO-DO List
			}

		
			
		}	
		
		assertTrue(rtnValue, "NO Opened Employment or Add New Button is not working");
		ExtentTest testLog11=extentReport.startTest("VERIFY EMPLOYMENT PLAN UPDATED OR CREATED");//For Name
		testLog11.log(LogStatus.INFO,"Verified newly added contact details in Employement page");
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
