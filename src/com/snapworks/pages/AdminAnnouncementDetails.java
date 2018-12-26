package com.snapworks.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.snapworks.tests.ExtentReport;

public class AdminAnnouncementDetails extends LoadableComponent<AdminAnnouncementDetails>{
	private WebDriver driver;
	private ExtentReports extentReport;
	private ExtentTest testLog;
	
	@FindBy(xpath = "//input[@id='field_title']")
	private WebElement title;
	
	@FindBy(xpath = "//input[@id='field_validFromDt']")
	private WebElement validFromDt;
	
	@FindBy(xpath = "//input[@id='field_validToDt']")
	private WebElement validToDt;
	
	@FindBy(xpath = "//select[@id='field_priority']")
	private WebElement type;
	
	@FindBy(xpath = "//label[@for='field_provider1']")
	private WebElement providerRadio;
	
	@FindBy(xpath = "//label[@for='field_provider'][1]")
	private WebElement StateRadio;
	
	@FindBy(xpath = "//label[@for='field_both']")
	private WebElement bothRadio;
	
	@FindBy(xpath = "//label[@for='field_all_users']")
	private WebElement allRadio;
	
	@FindBy(xpath = "//span[@class='dropdown-btn'][1]")
	private WebElement countiesDD;
	
	@FindBy(xpath = "//ng-multiselect-dropdown[@id='field_provider']")
	private WebElement providerDD;
	
	@FindBy(xpath = "//textarea[@id='field_content']")
	private WebElement description;
	
	@FindBy(xpath = "//button[contains(text(),'SAVE')]")
	private WebElement save;
	
	@FindBy(xpath = "//button[contains(text(),'RESET')]")
	private WebElement reset;
	
	@FindBy(xpath = "//div[@class='simple-notification-wrapper top right']")
	private WebElement simpleNotification;
	
	@FindBy(xpath = "//*[@id='field_county']/div[@class='multiselect-dropdown']//div[@class='select-all-text']")
	private WebElement allCounties;
	
	@FindBy(xpath = "//*[@id='field_provider']/div[@class='multiselect-dropdown']//div[@class='select-all-text']")
	private WebElement allproviders;
	
	@FindBy(xpath = "//span[contains(text(),'Application Configuration')]")
	private WebElement ApplicationConfiguration;
	

	public AdminAnnouncementDetails(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void load() {
		// TODO Auto-generated method stub
	}
	
	public AdminAnnouncementSummary getAnnouncementDetailsPage(String Title, String Type,String Discription) throws InterruptedException 
	{
		Thread.sleep(800);
		title.sendKeys(Title);
		validFromDt.click();
		List<String> OptionAD = UniversalMethods.getDate(false, "",0);
		String ValidfromDate = OptionAD.get(0)+", "+OptionAD.get(2) ;
		String ValidfromDtDay = OptionAD.get(1);
		driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='"+ValidfromDate+"']/div[contains(text(),'"+ValidfromDtDay+"')]")).click();
		validToDt.click();
		OptionAD = UniversalMethods.getDate(true, "",1);
		String ValidtoDtDate = OptionAD.get(0)+", "+OptionAD.get(2) ;
		String ValidtoDtDay = OptionAD.get(1);
		driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='"+ValidtoDtDate+"']/div[contains(text(),'"+ValidtoDtDay+"')]")).click();
		type.sendKeys(Type);
		Random num = new Random();
		 int iSelect = num.nextInt(3);
		 switch(iSelect){
		 case 0:
			 providerRadio.click();
			 providerDD.click();
			 allproviders.click();
			 break;
		 case 1:
			 StateRadio.click();
			 countiesDD.click();
			 allCounties.click();
			 break;
		 case 2:
			 bothRadio.click();
			 providerDD.click();
			 allproviders.click();
			 countiesDD.click();
			 allCounties.click();
			 break;
		 case 3:
			 allRadio.click();
			 break;
		 }
		 description.click();
		 description.sendKeys(Discription);
		 Thread.sleep(2500);
		 save.click();
		 Thread.sleep(300);
		 System.out.println(simpleNotification.getText().toString());
			String message = simpleNotification.getText().toString();
			new WebDriverWait(driver, 10).until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
			return new AdminAnnouncementSummary(driver);
	}
	
	
	
}

