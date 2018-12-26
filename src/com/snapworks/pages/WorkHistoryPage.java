package com.snapworks.pages;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class WorkHistoryPage extends LoadableComponent<WorkHistoryPage> {
	private WebDriver driver;

	@FindBy(xpath = "//label[@for ='empSecurityYes']")
	private WebElement empSecurityYes;

	@FindBy(xpath = "//label[@for ='empSecurityNo']")
	private WebElement empSecurityNo;

	@FindBy(xpath = "//label[@for ='employmentYes']")
	private WebElement employmentYes;

	@FindBy(xpath = "//label[@for ='employmentNo']")
	private WebElement employmentNo;

	@FindBy(xpath = "//label[@for ='experiencesYes']")
	private WebElement experiencesYes;

	@FindBy(xpath = "//span[contains(text(),' ADD NEW')]")
	private WebElement addNewBtn;

	@FindBy(xpath = "//div[@col-id='jobStatusCd']//select[@class='ag-cell-edit-input']")
	private WebElement jobStatusDD;

	@FindBy(xpath = "//div[@role='gridcell'][@col-id='employerName']")
	private WebElement employer;

	@FindBy(xpath = "//div[@role='gridcell'][@col-id='position']")
	private WebElement position;

	@FindBy(xpath = "//div[@col-id='contactInfo'][@class='ag-cell ag-cell-not-inline-editing ag-cell-with-height ag-cell-no-focus ag-cell-value']")
	private WebElement empContactInfo;
	// div[@role='gridcell'][@col-id='contactInfo']

	@FindBy(xpath = "//div[@role='gridcell'][@col-id='hourlySalary']")
	private WebElement hourSalary;

	@FindBy(xpath = "//div[@role='gridcell'][@col-id='hourlySalary']//input")
	private WebElement hourSalaryInput;

	@FindBy(xpath = "//div[@role='gridcell'][@col-id='startDt']")
	private WebElement startDate;

	@FindBy(xpath = "//input[@mask-input='date']")
	private WebElement startDatePicker;

	// input[@mask-input='date']

	@FindBy(xpath = "//label[@for ='experiencesNo']")
	private WebElement experiencesNo;

	@FindBy(xpath = "//span[contains(text(),'SAVE')]")
	private WebElement save;

	@FindBy(xpath = "//span[contains(text(),'FINALIZE ASSESSMENT')]")
	private WebElement finalAss;

	@FindBy(xpath = "//div[@class='simple-notification-wrapper top right']")
	private WebElement simpleNotification;

	public WorkHistoryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Work History')]")).isDisplayed());
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public List<String> getDate() {
		Date now = new Date();
		int dayNum = now.getDay();
		String day = String.valueOf(now.getDate());
		// Thursday, November 1, 2018
		String dayText = "";
		switch (dayNum) {
		case 0:
			dayText = "Sunday";
			break;
		case 1:
			dayText = "Monday";
			break;
		case 2:
			dayText = "Tuesday";
			break;

		case 3:
			dayText = "Wednesday";
			break;
		case 4:
			dayText = "Thursday";
			break;
		case 5:
			dayText = "Friday";
			break;
		case 6:
			dayText = "Saturday";
			break;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
		String fullDate = dateFormat.format(now);

		List<String> currentOptions = new ArrayList<>();
		currentOptions.add(dayText);
		currentOptions.add(day);
		currentOptions.add(fullDate);
		return currentOptions;
	}

	public String WorkHistoryPageDetails(String empSecurity, String employment, String priorJobExp)
			throws InterruptedException {
		Thread.sleep(1000);

		new WebDriverWait(driver, 1000).until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//label[@for ='empSecurityYes']"))));
		if (empSecurity.toUpperCase().equals("YES")) {
			empSecurityYes.click();
		}

		else {
			empSecurityNo.click();
		}

		if (employment.toUpperCase().equals("YES")) {
			employmentYes.click();
		}

		else {
			employmentNo.click();
		}
		if (priorJobExp.toUpperCase().equals("YES")) {
			experiencesYes.click();
			addNewBtn.click();
			Select Jobstatus = new Select(jobStatusDD);
			Jobstatus.selectByVisibleText("Current");
			employer.sendKeys("TTest");
			position.sendKeys("TTest");
			empContactInfo.sendKeys("TTest");
			hourSalary.click();
			hourSalaryInput.sendKeys("$$10.10");
			startDate.click();
			startDatePicker.click();
			List<String> Options = getDate();
			String LabelDate = Options.get(0) + ", " + Options.get(2);
			String day = Options.get(1);
			driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='" + LabelDate
					+ "']/div[contains(text(),'" + day + "')]")).click();

		}

		else {
			experiencesNo.click();
		}
		Thread.sleep(1000);
		save.click();
		Thread.sleep(1000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		System.out.println(simpleNotification.getText().toString());
		String message = simpleNotification.getText().toString();
		new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		finalAss.click();
		Thread.sleep(1000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		System.out.println(simpleNotification.getText().toString());
		String finalmessage = simpleNotification.getText().toString();
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		Thread.sleep(1000);
		return finalmessage;
	}

	public AssessmentPage getAssessmentPage() {
		return new AssessmentPage(driver);
	}
}
