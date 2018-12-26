package com.snapworks.pages;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppointmentPage extends LoadableComponent<AppointmentPage> {

	private WebDriver driver;

	@FindBy(xpath = "//input[@id='field_appointmentDate']")
	private WebElement appointmentDate;

	@FindBy(id = "field_appointmentTimeHour")
	private WebElement appointmentTimeHour;

	@FindBy(id = "field_appointmentTimeMin")
	private WebElement appointmentTimeMin;

	@FindBy(id = "field_appointmentMeridian")
	private WebElement appointmentMeridian;

	@FindBy(id = "field_appointmentType")
	private WebElement appointmentType;

	@FindBy(xpath = "//select[@id='field_appointmentStatus']")
	private WebElement appointmentStatus;
	
	@FindBy(id = "field_appointmentMethod")
	private WebElement appointmentMethod;

	@FindBy(id = "field_appointmentLocation")
	private WebElement appointmentLocation;

	@FindBy(xpath = "//span[contains(text(),'SAVE')]")
	private WebElement saveBtn;

	@FindBy(xpath = "//span[contains(text(),'RESET')]")
	private WebElement ResetBtn;

	@FindBy(xpath = "//div[@class='simple-notification-wrapper top right']")
	private WebElement simpleNotification;

	@FindBy(partialLinkText = "Appointment History")
	private WebElement appointmentHistory;

	@FindBy(xpath = "//span[contains(text(),'Assessment')]")
	private WebElement assessment;

	@FindBy(xpath = "//span[contains(text(),' Assessment')]")
	private WebElement assesmentTab;

	@FindBy(xpath = "//span[contains(text(),'Assessment Summary')]")
	private WebElement assessmentSummary;

	@FindBy(xpath = "//span[contains(text(),'Reimbursements')]")
	private WebElement reimbursements;

	public AppointmentPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Schedule Appointment')]")).getText().toString()
				.contains("Schedule Appointment"));
		// assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Search')]")).getText().toString().contains("Search"));

	}

	public void getSaveAppStatus(String appstatus) {

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(appointmentStatus));
		Select status = new Select(appointmentStatus);
		status.selectByVisibleText(appstatus);
		saveBtn.click();

	}

	public List<String> scheduleAppointment(String timeHr, String timeMin, String appMdn, String appType, String appStatus,
			String appMtd, String apploc) throws InterruptedException {
		String message = "";
		String value = "";
		Thread.sleep(1000);
		String status = appointmentStatus.getAttribute("outerHTML").toString();

		if (status.contains("disabled")) 
		{
			// ((JavascriptExecutor)driver).executeScript("arguments[0].value='"+appDate+"';",
			// appointmentDate);
			String Date = "";
			appointmentDate.click();
			List<String> Options = UniversalMethods.getDate(false, Date,0);
			String LabelDate = Options.get(0) + ", " + Options.get(2);
			String day = Options.get(1);
			driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='" + LabelDate
					+ "']/div[contains(text(),'" + day + "')]")).click();
			Select appHourSlct = new Select(appointmentTimeHour);
			appHourSlct.selectByVisibleText(timeHr);

			Select appMinSlct = new Select(appointmentTimeMin);
			appMinSlct.selectByVisibleText(timeMin);

			Select appMdnSlct = new Select(appointmentMeridian);
			appMdnSlct.selectByVisibleText(appMdn);

			Select appTypeSlct = new Select(appointmentType);
			appTypeSlct.selectByVisibleText(appType);

			Select appStatusSlct = new Select(appointmentStatus);
			appStatusSlct.selectByVisibleText(appStatus);

			Select appMethodSlct = new Select(appointmentMethod);
			appMethodSlct.selectByVisibleText(appMtd);

			Select appLocationSlct = new Select(appointmentLocation);
			appLocationSlct.selectByIndex(1);
			// appLocationSlct.selectByVisibleText(apploc);

		} 
		else {
			Thread.sleep(600);
			appStatus = "Show";
			Select appstatusSlct = new Select(appointmentStatus);
			appstatusSlct.selectByVisibleText(appStatus);
			value = "true";

		}
		Thread.sleep(600);
		saveBtn.click();
		
		System.out.println(simpleNotification.getText().toString());
		message = simpleNotification.getText().toString();
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		List<String> currentOptions = new ArrayList<>();
		currentOptions.add(message);
		currentOptions.add(value);
		return currentOptions;
	}

	public AppointmentHistoryPage getAppointmentHistoryPage() throws InterruptedException {
		Thread.sleep(1000);
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(appointmentHistory));
		appointmentHistory.click();
		return new AppointmentHistoryPage(driver);
	}

	public AssessmentPage getAssessmentPage() throws InterruptedException {
		Thread.sleep(1000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(assessment));
		assessment.click();

		return new AssessmentPage(driver);
	}

	public AssessmentPage getAssessmentSummaryPage() throws InterruptedException {
		// Thread.sleep(100);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(assesmentTab));
		assesmentTab.click();

		return new AssessmentPage(driver);
	}

	public ReimbursementPage getReimbursementPage() {

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(reimbursements));
		reimbursements.click();
		return new ReimbursementPage(driver);
	}

}
