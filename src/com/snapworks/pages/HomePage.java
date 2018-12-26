package com.snapworks.pages;

import static org.testng.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends LoadableComponent<HomePage> {

	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"innerNav\"]/ul/li[2]/a")
	private WebElement searchTab;

	@FindBy(xpath = "//a[contains(text(),'Reports')]")
	private WebElement reportTab;
	
	@FindBy(xpath = "//a[@href='/admin']")
	private WebElement admin;

	@FindBy(xpath = "//h1[@class='card-header section-header']/span[contains(text(),'Announcements')]")
	private WebElement announcements;

	@FindBy(xpath = "//span[contains(text(),'Welcome ')]")
	private WebElement welcomeProfile;

	@FindBy(xpath = "//span[contains(text(),'LOGOUT')]")
	private WebElement logout;

	@FindBy(xpath = "//span[contains(text(),'Due Today')]")
	private WebElement DueToday;

	@FindBy(xpath = "//span[contains(text(),'Upcoming')]")
	private WebElement Upcoming;

	@FindBy(xpath = "//span[contains(text(),'Overdue')]")
	private WebElement Overdue;

	@FindBy(xpath = "//input[@id='field_taskStartDt']")
	private WebElement taskStartDt;

	@FindBy(xpath = "//input[@id='field_taskDueDt']")
	private WebElement taskToDt;

	@FindBy(xpath = "//div[@tabindex='0']")
	private WebElement date;

	@FindBy(xpath = "//div[@ng-reflect-selected='true']")
	private WebElement day;

	@FindBy(xpath = "//span[@class='dropdown-btn']")
	private WebElement taskStatus;
	// span[@class='dropdown-btn']

	//// div[@class='btn-light bg-primary text-white']

	public HomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public WebDriver getDriver() {

		return driver;
	}

	public WebElement getWelcomeProfile() throws InterruptedException {
		Thread.sleep(7000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(welcomeProfile));
		
		return welcomeProfile;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		// assertTrue(driver.getTitle().equals("Worker Portal"));
		assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText().toString().contains("Home"));

	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public SearchPage getSearchPage() throws InterruptedException {

		Thread.sleep(5000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchTab));
		Thread.sleep(2000);
		searchTab.click();

		return new SearchPage(driver);
	}
	
	public ClientSearchPage getClientSearchPage() throws InterruptedException {

		Thread.sleep(800);//updated time
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchTab));
		Thread.sleep(800);
		searchTab.click();

		return new ClientSearchPage(driver);
	}

	public LoginPage getLoginPage() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(welcomeProfile));
		welcomeProfile.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(logout));
		logout.click();
		return new LoginPage(driver);
	}

	public AnnouncementsPage getAnnouncementsPage() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(announcements));
		announcements.click();
		return new AnnouncementsPage(driver);
	}
	
	public AdminAnnouncementSummary getAdminAnnouncementsPage() throws InterruptedException {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(admin));
		Thread.sleep(8000);
		admin.click();
		return new AdminAnnouncementSummary(driver);
	}

	// public ReportPage getReportsPage() {
	//
	// new WebDriverWait(driver,
	// 6).until(ExpectedConditions.visibilityOf(reportTab));
	// reportTab.click();
	// return new ReportPage(driver);

	// Methods for Task Stats in home screen

	// Validate Due Today link
	public String validateDueTodyLink() throws InterruptedException {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(DueToday));
		DueToday.click();
		Thread.sleep(3000);

		taskStartDt.click();

		Date now = new Date();
		int today = now.getDate();
		int FromDate = Integer.parseInt(day.getText());
		day.click();

		taskToDt.click();
		int ToDate = Integer.parseInt(day.getText());
		day.click();

		String list = taskStatus.getText();
		boolean actualListValue = list.contains("Open");
		actualListValue = list.contains("In progress");
		String Result = "";
		if (FromDate == today && ToDate == today && actualListValue == true) {
			// System.out.println("DueToday-Pass:::From Date or To Date or Task Status are
			// selected input as expected");
			Result = "DueToday-Pass:::From Date or To Date or Task Status are selected input as expected";
		} else {
			// System.out.println("DueToday-Failed:::::From Date or To Date or Task Status
			// are selected wrong input or not selected" );
			Result = "Failed:::::From Date or To Date or Task Status are selected wrong input or not selected";
		}
		return Result;

		// Boolean date = actualName.contains(startDate);
		// return new SearchPage(driver);
	}

	public String validateUpcomingLink() throws InterruptedException {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(DueToday));
		Upcoming.click();
		Thread.sleep(3000);

		taskStartDt.click();

		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);

		// Number of Days to add
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date tomorrow = cal.getTime();
		// Date after adding the days to the given datez	
		int fromDate = tomorrow.getDate();

		cal.add(Calendar.DAY_OF_MONTH, 4);
		Date fifthDay = cal.getTime();
		int toDate = fifthDay.getDate();

		int FromDate = Integer.parseInt(day.getText());

		day.click();

		taskToDt.click();
		int ToDate = Integer.parseInt(day.getText());
		day.click();

		String list = taskStatus.getText();
		boolean actualListValue = list.contains("Open");
		actualListValue = list.contains("In progress");
		String Result = "";
		if (FromDate == fromDate && ToDate == toDate && actualListValue == true) {
			// System.out.println("UpComing-Pass:::From Date or To Date or Task Status are
			// selected input as expected");
			Result = "UpComing-Pass:::From Date or To Date or Task Status are selected input as expected";
		} else {
			// System.out.println("Upcoming-Failed:::::From Date or To Date or Task Status
			// are selected wrong input or not selected" );
			Result = "Upcoming-Failed:::::From Date or To Date or Task Status are selected wrong input or not selected";
		}
		return Result;

	}

	public String validateOverdueLink() throws InterruptedException {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(Overdue));
		Overdue.click();
		Thread.sleep(3000);

		taskStartDt.click();

		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);

		// Number of Days to add
		cal.add(Calendar.DAY_OF_MONTH, -5);
		Date fourthback = cal.getTime();
		// Date after adding the days to the given date
		int fromDate = fourthback.getDate();

		cal.add(Calendar.DAY_OF_MONTH, 4);
		Date fifthDay = cal.getTime();
		int dateValue = fifthDay.getDate();

		int FromDate = Integer.parseInt(day.getText());

		day.click();

		taskToDt.click();
		int ToDate = Integer.parseInt(day.getText());

		day.click();

		String list = taskStatus.getText();
		boolean actualListValue = list.contains("Open");
		actualListValue = list.contains("In progress");
		String Result = "";
		if (FromDate == fromDate && ToDate == dateValue && actualListValue == true) {
			// System.out.println("Overdue-Pass:::From Date or To Date or Task Status are
			// selected input as expected");
			Result = "Overdue-Pass:::From Date or To Date or Task Status are selected input as expected";

		} else {
			// System.out.println("Overdue-Failed:::::From Date or To Date or Task Status
			// are selected wrong input or not selected" );
			Result = "Overdue-Failed:::::From Date or To Date or Task Status are selected wrong input or not selected";
		}
		return Result;
	}
	
}
