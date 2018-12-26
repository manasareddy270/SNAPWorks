package com.snapworks.pages;

import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NonComplianceDetailsPage extends LoadableComponent<NonCompliancePage> {

	private WebDriver driver;

	@FindBy(id = "field_ncBeginDt")
	private WebElement discoveryDate;

	@FindBy(id = "field_month")
	private WebElement fieldNonComplainceMonth;

	// ngb-datepicker-navigation-select/select[1]
	@FindBy(xpath = "//ngb-datepicker-navigation-select/select[1]")
	private WebElement fieldMonth;

	@FindBy(xpath = "//ngb-datepicker-navigation-select/select[2]")
	private WebElement fieldYear;

	@FindBy(id = "field_participate")
	private WebElement fieldParticipate;

	@FindBy(id = "field_program")
	private WebElement fieldProgram;
	
	@FindBy(id = "field_non_comp_notes")
	private WebElement notes;
	

	@FindBy(xpath = "//label[@for ='yesGoodCause']")
	private WebElement goodCauseYes;

	@FindBy(xpath = "//label[@for ='noGoodCause']")
	private WebElement goodCauseNo;

	@FindBy(xpath = "//div[@class='simple-notification-wrapper top right']")
	private WebElement simpleNotification;

	@FindBy(xpath = "//span[contains(text(),'SAVE')]")
	private WebElement save;

	@FindBy(xpath = "//select[@id='field_goodcausereasoncd']")
	private WebElement gdCauseDD;
	
	public NonComplianceDetailsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub

		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Non Compliance Details')]")).isDisplayed());
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public List<String> getNonComplainceSummarryPage(String ncDiscoveryDate, String ncMonth, String reason,
			String program, String cause) throws InterruptedException, ParseException {
		Thread.sleep(1000);
		discoveryDate.click();
		String Date = "";
		List<String> Options = UniversalMethods.getDate(false, Date,0);
		String LabelDate = Options.get(0) + ", " + Options.get(2);
		String day = Options.get(1);
		String mnth_yr = Options.get(3);
		List<WebElement> elements = driver.findElements(By.xpath("//ngb-datepicker-month-view//div[@aria-label='" + LabelDate + "']/div[contains(text(),'" + day + "')]"));
		Thread.sleep(1000);
		if(elements.isEmpty()) {
			driver.findElement(By.xpath("//div[@class='ngb-dp-arrow']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='" + LabelDate + "']/div[contains(text(),'" + day + "')]")).click();
		}else {
			
			driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='" + LabelDate + "']/div[contains(text(),'" + day + "')]")).click();
			
		}
		fieldNonComplainceMonth.sendKeys(mnth_yr);
		Select fieldReasonSlct = new Select(fieldParticipate);
		List <WebElement> weblist = fieldReasonSlct.getOptions();
		 //Taking the count of items
		 int iCnt = weblist.size();
		 //Using Random class to generate random values
		 Random num = new Random();
		 int iSelect = num.nextInt(iCnt);
		 //Selecting value from DropDownList
		 if(iSelect == 0){
			 iSelect = num.nextInt(iCnt);
		 }
		 Thread.sleep(500);
		 fieldReasonSlct.selectByIndex(iSelect);
		 //Selected Value
		 System.out.println(fieldParticipate.getAttribute("value"));
		//fieldReasonSlct.selectByVisibleText(reason);
		Select fieldProgramSlct = new Select(fieldProgram);
		fieldProgramSlct.selectByVisibleText(program);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
		String optionDate = Options.get(2).toString();
		Date date = dateFormat.parse(optionDate);
		dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String recoveryDate = dateFormat.format(date);
		new WebDriverWait(driver, 1000)
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//label[@for ='yesGoodCause']"))));
		if (cause.toUpperCase().equals("YES")) {

			goodCauseYes.click();
		}

		else {
			goodCauseNo.click();
		}
		String value = fieldParticipate.getAttribute("value").toString();
		reason = getOptionsText(value);
		if(iSelect == 5){
			Random random = new Random();
			String id = String.format("%04d", random.nextInt(10000));
			String note = "testing notes"+id;
			Thread.sleep(1000);
			notes.sendKeys(note);
		}
		save.click();
		Thread.sleep(1000);
		
		System.out.println(simpleNotification.getText().toString());
		String message = simpleNotification.getText().toString();
		if(message.equals("Non-Compliance\nDuplicate record exists.")){
			Thread.sleep(1000);
			
			discoveryDate.click();
		
			Options = UniversalMethods.generateRandomDate();
			LabelDate = Options.get(0) + ", " + Options.get(2);
			day = Options.get(1);

			elements = driver.findElements(By.xpath("//ngb-datepicker-month-view//div[@aria-label='" + LabelDate + "']/div[contains(text(),'" + day + "')]"));
			Thread.sleep(1000);
			if(elements.isEmpty()) {
				driver.findElement(By.xpath("//div[@class='ngb-dp-arrow']")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='" + LabelDate + "']/div[contains(text(),'" + day + "')]")).click();
			}else {
				
				driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='" + LabelDate + "']/div[contains(text(),'" + day + "')]")).click();
				
			}
			//driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='" + LabelDate + "']/div[contains(text(),'" + day + "')]")).click();
			Thread.sleep(1000);
			iSelect = num.nextInt(iCnt);
			if(iSelect == 0){
				 iSelect = num.nextInt(iCnt);
			 }
			fieldReasonSlct.selectByIndex(iSelect);
			 value = fieldParticipate.getAttribute("value").toString();
			reason = getOptionsText(value);
			
			dateFormat = new SimpleDateFormat("MMMM d, yyyy");
			date = dateFormat.parse(Options.get(2));
			dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			recoveryDate = dateFormat.format(date);
			Thread.sleep(1000);
			save.click();
			Thread.sleep(1000);
			System.out.println(simpleNotification.getText().toString());
			message = simpleNotification.getText().toString();
			
		}
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		List<String> currentOptions = new ArrayList<>();
		currentOptions.add(reason);
		currentOptions.add(recoveryDate);
		
		return currentOptions;
	}
	public Boolean IsDateElementPresent(String LabelDate, String day) {
		
			driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='" + LabelDate + "']/div[contains(text(),'" + day + "')]")).isDisplayed();
			return true;
		
		
	}
	public String getOptionsText(String value) {
		String Text = "";
		switch (value) {
			case "1: Il":
				Text = "Illness";
				break;
			case "2: In":
				Text = "Injury";
				break;
			case "3: LC":
				Text = "Lack of child care";
				break;
	
			case "4: LT":
				Text = "Lack of transportation";
				break;
			case "5: OT":
				Text = "Other";
				break;
			
			}
		return Text;
		
	}
	
	
	
	
	public NonCompliancePage getNonComplaincePage() throws InterruptedException {
		Thread.sleep(1000);
		return new NonCompliancePage(driver);
	}
	public NonCompliancePage gcGranted() throws InterruptedException {
		Thread.sleep(1000);
		Select gcGrantedDD = new Select(gdCauseDD);
		gcGrantedDD.selectByVisibleText("Granted");
		save.click();
		
		System.out.println(simpleNotification.getText().toString());
		String message = simpleNotification.getText().toString();
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		
		return new NonCompliancePage(driver);
	}

}
