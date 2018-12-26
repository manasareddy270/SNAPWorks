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
import org.openqa.selenium.support.ui.WebDriverWait;

public class EnrollmentSummaryPage extends LoadableComponent<EnrollmentSummaryPage> {
	private WebDriver driver;
	
	@FindBy(xpath="//span[contains(text(),' SAVE ')]")
	private WebElement saveBtn;
	
	@FindBy(xpath="//label[contains(text(),'Yes')]")
	private WebElement yesRadioBtn;
	
	@FindBy(xpath="//input[@id='field_caseClosureDt']")
	private WebElement caseClosureDt;
	
	
	
	public EnrollmentSummaryPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this); 
		this.driver=driver;
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
		assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Enrollment Summary')]")).getText().toString().contains("Enrollment Summary"));
	}
	public List<String> getDate() {
		//aria-label="Thursday, November 8, 2018"
		Date now = new Date();
		int wkdayNum = now.getDay();//To find week day by index(0-6)
		String dayText = "";
		switch(wkdayNum) {
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
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
		String fullDate= dateFormat.format(now);
		String todayDate = String.valueOf(now.getDate());//to click a date
		 List<String> currentOptions = new ArrayList<>();
		 currentOptions.add(dayText);
		 currentOptions.add(todayDate);
		 currentOptions.add(fullDate);
		return currentOptions;
	}
	public void closeCase() {
		yesRadioBtn.click();
		caseClosureDt.click();
		List<String> Options = getDate();
		String LabelDate = Options.get(0)+", "+Options.get(2) ;
		String day = Options.get(1);
		driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='"+LabelDate+"']/div[contains(text(),'"+day+"')]")).click();
		saveBtn.click();
	}

}
