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

public class ComponentsDetailsPage extends LoadableComponent<ComponentsSummaryPage> {

	private WebDriver driver;

	@FindBy(id = "field_component")
	private WebElement component;

	@FindBy(id = "field_serviceProvider")
	private WebElement serviceProvider;

	@FindBy(id = "field_startDt")
	private WebElement cmpStDate;

	@FindBy(id = "field_estimatedCompletionDt")
	private WebElement compEstDate;
	
	@FindBy(xpath = "//a[@class='list-group-item list-group-item-action ng-star-inserted']")
	private WebElement elasticResult;
	

	@FindBy(id = "field_authorizedHours")
	private WebElement hours;

	@FindBy(id = "field_content")
	private WebElement notes;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement save;

	@FindBy(xpath = "//div[@class='simple-notification-wrapper top right']")
	private WebElement simpleNotification;
	
	
	

	public ComponentsDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Component Details')]")).getText().toString()
				.contains("Component Details"));
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public List<String> getDate(Boolean compDate, String Date) {
		Date today;
		int dayNum ;
		int day ;
	
		if (Date != "") {
			
			if (compDate == true) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date(Date));
				cal.add(Calendar.DAY_OF_MONTH, -1);
				today = cal.getTime();
				
			}else {
				today = new Date(Date);
			}
			dayNum = today.getDay();
			
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
			String todayDate = String.valueOf(today.getDate());
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
			String fullDate = dateFormat.format(today);
			
			List<String> currentOptions = new ArrayList<>();
			currentOptions.add(dayText);
			currentOptions.add(todayDate);
			currentOptions.add(fullDate);
			return currentOptions;
		}
		return null;
	}

	public void addComponent(String comp, String Provider, String hrs, String compnotes, String StartDate,String EndDate) throws InterruptedException {
		Thread.sleep(1000);
		String message = "";

		Select empSTG1 = new Select(component);
		empSTG1.selectByVisibleText(comp);
		serviceProvider.sendKeys(Provider);
		Thread.sleep(1000);
		elasticResult.click();
		Thread.sleep(500);
		cmpStDate.click();
		Thread.sleep(500);
		List<String> Options = getDate(false, StartDate);
		String LabelDate = Options.get(0) + ", " + Options.get(2);
		String day = Options.get(1);
		driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='" + LabelDate + "']/div[contains(text(),'" + day + "')]")).click();
		Thread.sleep(500);
		compEstDate.click();
		Thread.sleep(500);
		List<String> OptionsDt = getDate(false, EndDate);
		String LabelestCompletionDt = OptionsDt.get(0) + ", " + OptionsDt.get(2);
		String dayestCompletionDt = OptionsDt.get(1);
		driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='" + LabelestCompletionDt	+ "']/div[contains(text(),'" + dayestCompletionDt + "')]")).click();

		hours.sendKeys(hrs);
		notes.sendKeys(compnotes);

		save.click();
		Thread.sleep(100);
		new WebDriverWait(driver, 10)
				.until(ExpectedConditions.elementToBeClickable(simpleNotification.findElement(By.xpath("//*"))));
		System.out.println(simpleNotification.getText().toString());
		message = simpleNotification.getText().toString();
		new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));

	}

	public ComponentsSummaryPage getCompSummaryPage() {
		return new ComponentsSummaryPage(driver);
	}

}
