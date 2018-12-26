package com.snapworks.pages;

import static org.testng.Assert.assertTrue;

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

public class EmploymentPlanDetailsPage extends LoadableComponent<EmploymentPlanDetailsPage> {
	private WebDriver driver;
	
	@FindBy(xpath="//input[@name='empPlanStartDt']")
	private WebElement empPlanStartDt;
	
	@FindBy(xpath="//input[@id='field_empPlanStartDt'][@ng-reflect-disabled='true']")
	private WebElement empPlanStartDtDisabled;
	
	@FindBy(id="field_empPlanStartDt")
	private WebElement employmentPlanStartDt;
	
	@FindBy(id="field_estimatedCompletionDt")
	private WebElement estCompletionDt;
	
	@FindBy(id="field_clientAgreementDt")
	private WebElement clientAgreementDate;
	
	@FindBy(id="field_shortTermGoal")
	private WebElement empShortTermGoal;
	
	@FindBy(id="field_longTermGoal")
	private WebElement empLongTermGoal;
	
	@FindBy(id="field_empPlanStatus")
	private WebElement empPlanStatus;
	
	@FindBy(id="field_closureReeason")
	private WebElement CReason;
	
	@FindBy(id="field_content")
	private WebElement notes;
	
	@FindBy(xpath="//span[contains(text(),'Employment Plan')]")
	private WebElement  employmentPlan;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement save;
	
	@FindBy(xpath="//span[contains(text(),' Case Notes')]")
	private WebElement caseNotes;
	
	
	@FindBy(xpath="//div[@class='simple-notification-wrapper top right']")
	private WebElement simpleNotification;
	
	
	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Employment Plan Details')]")).getText().toString().contains("Employment Plan Details"));	
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
	}
	public EmploymentPlanDetailsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this); 
		this.driver=driver;
	}
	public List<String> getDate(Boolean compDate, String Date) {
		Date now;		
		//SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
		
		Calendar cal = Calendar.getInstance();		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
		
		if(Date =="") {
			 now = new Date();
		}else {
			now = new Date(Date);
			cal.setTime(now);
			Long value1 = cal.getTimeInMillis();
			
			now = new Date();			
			cal.setTime(now);			
			Long value2 = cal.getTimeInMillis();
			
			if((value1 == value2) || (value1 > value2)) {
				now = new Date(Date);
				cal.setTime(now);
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
			else if(value1 < value2) {
				 now = new Date();
			}
		}
		
		
		if(compDate == true ) {
			now = new Date(Date);
			cal.setTime(now);
			cal.add(Calendar.DAY_OF_MONTH, 2);
			
		}
		
		Date today = cal.getTime();
		int dayNum = today.getDay();
		//int day=today.getDate();
		
		String todayDate = String.valueOf(today.getDate());
	
		String dayText = "";
		switch(dayNum) {
		case 0:
			dayText = "Sunday";
			break;
		case 1:
			dayText="Monday";
			break;
		case 2:
			dayText = "Tuesday";
			break;
			
		case 3:
			dayText="Wednesday";
			break;
		case 4:
			dayText = "Thursday";
			break;
		case 5:
			dayText="Friday";
			break;
		case 6:
			dayText="Saturday";
			break;
		}
		String fullDate= dateFormat.format(today);
	
		 List<String> currentOptions = new ArrayList<>();
		 currentOptions.add(dayText);
		 currentOptions.add(todayDate);
		 currentOptions.add(fullDate);
		return currentOptions;
	}
	public boolean createemploymentPlan(String empGST, String empLST, String empStatus, String empnotes,String StartDate,String EndDate) throws InterruptedException {
	Thread.sleep(1000);

	
	String empStDate = empPlanStartDt.getAttribute("outerHTML").toString();
	String htmlElement = "ng-reflect-disabled=\"false\"";
	if(empStDate.contains("disabled")) {
		
		Select empsts = new Select(empPlanStatus);
		empsts.selectByVisibleText("Closed");
		Thread.sleep(1000);
		Select closureReason = new Select(CReason);
		closureReason.selectByVisibleText("Non-Cooperation Sanction");
		
		
	}
	else{	
	
	employmentPlanStartDt.click();
	List<String> Options = getDate(false, EndDate);
	String LabelDate = Options.get(0)+", "+Options.get(2) ;
	String day = Options.get(1);
	driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='"+LabelDate+"']/div[contains(text(),'"+day+"')]")).click();
//	
	
	estCompletionDt.click();
	List<String> OptionsDt = getDate(true,EndDate);
	String LabelestCompletionDt = OptionsDt.get(0)+", "+OptionsDt.get(2) ;
	String dayestCompletionDt = OptionsDt.get(1);
	driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='"+LabelestCompletionDt+"']/div[contains(text(),'"+dayestCompletionDt+"')]")).click();
	
	clientAgreementDate.click();
	
	List<String> OptionAD = getDate(false,EndDate);
	String LabelClientAgreementDate = OptionAD.get(0)+", "+OptionAD.get(2) ;
	String dayClientAgreementDate = OptionAD.get(1);
	driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='"+LabelClientAgreementDate+"']/div[contains(text(),'"+dayClientAgreementDate+"')]")).click();
	
	
	Select empSTG1 = new Select(empShortTermGoal);
	empSTG1.selectByVisibleText(empGST);
	
	Select empLTG1 = new Select(empLongTermGoal);
	empLTG1.selectByVisibleText(empLST);
	
	Select empsts = new Select(empPlanStatus);
	empsts.selectByVisibleText(empStatus);
	notes.sendKeys(empnotes);
	
	}
	save.click();
	Thread.sleep(1000);
	new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(simpleNotification.findElement(By.xpath("//*"))));
	System.out.println(simpleNotification.getText().toString());
	String message = simpleNotification.getText().toString();
	String expmsg = "Employment Plan Details\nRecord updated successfully.";
	new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
	if(message.contains(expmsg)) {
		return true;
		
	}else {
		return false;
	}
	
	}
	
	public EmploymentPlanPage getEmploymentPage() throws InterruptedException {
		Thread.sleep(1000);
		//employmentPlan.click();
		return new EmploymentPlanPage(driver) ;
	}
	
	public CaseNotesPage getCaseNotes() {
		caseNotes.click();
		return new CaseNotesPage(driver);
		
	}
}
