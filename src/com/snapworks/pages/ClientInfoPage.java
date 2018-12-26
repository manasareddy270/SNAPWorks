package com.snapworks.pages;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClientInfoPage extends LoadableComponent<ClientInfoPage>{
	private WebDriver driver;
	
	@FindBy(xpath="//a[contains(text(),'Enrollment Summary')]")
	private WebElement EnrollSummary;
	
	
	@FindBy(xpath="//a[contains(text(),'Household Summary')]")
	private WebElement houseHold;
	
	
	@FindBy(xpath="//a[contains(text(),'Employment Summary')]")
	private WebElement empSummary;
	
	
	@FindBy(xpath="//a[contains(text(),'Eligibility Summary')]")
	private WebElement EligibiltySummary;
	
	@FindBy(xpath="//span[contains(text(),'Contact History')]")
	private WebElement contactHistory;

	@FindBy(xpath = "//div[@class='simple-notification-wrapper top right']")
	private WebElement simpleNotification;
	
	public ClientInfoPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this); 
		this.driver=driver;
	}

	//a[contains(text(),'Enrollment Summary')]
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(EnrollSummary));
		assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Enrollment Summary')]")).getText().toString().contains("Enrollment Summary"));
	}
	
	
	public EnrollmentSummaryPage getEnrolSummary() throws InterruptedException {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Enrollment Summary')]")));
		EnrollSummary.click();
		Thread.sleep(1000);
		return new EnrollmentSummaryPage(driver);
	}
	
	public void validateClientInfoTabs() throws InterruptedException {
		Thread.sleep(5000);
		houseHold.click();
		/*new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		//new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(simpleNotification));
		System.out.println(simpleNotification.getText().toString());
		String message = simpleNotification.getText().toString();*/
		
		//new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000);
		empSummary.click();
		/*new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		//new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(simpleNotification));
		System.out.println(simpleNotification.getText().toString());
		 message = simpleNotification.getText().toString();
		new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));*/
		Thread.sleep(5000);
		EligibiltySummary.click();
		/*new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		
		System.out.println(simpleNotification.getText().toString());
		message = simpleNotification.getText().toString();
		new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));*/
		Thread.sleep(7000);
		EnrollSummary.click();
	}
	
	public ContactHistoryPage getContactHistoryPage() throws InterruptedException {
		Thread.sleep(800);
		contactHistory.click();
		return new ContactHistoryPage(driver);
		
		
	}

}
