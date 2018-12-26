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

public class AnnouncementsDetailsPage extends LoadableComponent<AnnouncementsDetailsPage> {

	private WebDriver driver;

	@FindBy(id = "field_title")
	private WebElement fieldTitle;

	@FindBy(id = "field_validToDt")
	private WebElement fieldEffectiveTo;

	@FindBy(xpath = "//ngb-datepicker-navigation-select/select[1]")
	private WebElement fieldMonth;

	@FindBy(xpath = "//ngb-datepicker-navigation-select/select[2]")
	private WebElement fieldYear;

	@FindBy(id = "field_validFromDt")
	private WebElement fieldEffectiveFrom;

	@FindBy(id = "field_priority")
	private WebElement fieldPriorty;

	@FindBy(id = "field_county")
	private WebElement fieldCounty;

	@FindBy(id = "field_content")
	private WebElement fieldContent;

	@FindBy(xpath = "//div[@class='simple-notification-wrapper top right']")
	private WebElement simpleNotification;

	@FindBy(xpath = "//button[contains(text(),'SAVE')]")
	private WebElement save;

	public AnnouncementsDetailsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Announcement Details')]")).isDisplayed());
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public List<String> getDate(Boolean compDate) {
		// SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		if (compDate == true) {
			cal.setTime(now);
			cal.add(Calendar.DAY_OF_MONTH, 4);

		} else {
			cal.setTime(now);
		}

		Date today = cal.getTime();
		int dayNum = today.getDay();
		// int day=today.getDate();

		String todayDate = String.valueOf(today.getDate());

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
		String fullDate = dateFormat.format(today);
		/*
		 * if(day < 10) { fullDate = dateFormat.format(today); }else { fullDate =
		 * sdf.format(today); }
		 */

		List<String> currentOptions = new ArrayList<>();
		currentOptions.add(dayText);
		currentOptions.add(todayDate);
		currentOptions.add(fullDate);
		return currentOptions;
	}

	public AnnouncementsPage getAnnouncementsSummarryPage(String title, String effectiveFrom, String effectiveTo,
			String priorty, String county, String content) throws InterruptedException {
		fieldTitle.sendKeys(title);

		fieldEffectiveFrom.click();

		List<String> Options = getDate(false);
		String LabelDate = Options.get(0) + ", " + Options.get(2);
		String day = Options.get(1);
		driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='" + LabelDate
				+ "']/div[contains(text(),'" + day + "')]")).click();
		
		fieldEffectiveTo.click();
		List<String> OptionsDt = getDate(true);
		String LabelestCompletionDt = OptionsDt.get(0) + ", " + OptionsDt.get(2);
		String dayestCompletionDt = OptionsDt.get(1);
		driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='" + LabelestCompletionDt
				+ "']/div[contains(text(),'" + dayestCompletionDt + "')]")).click();

		Select fieldPriortySlct = new Select(fieldPriorty);
		fieldPriortySlct.selectByVisibleText(priorty);

		Select fieldCountySlct = new Select(fieldCounty);
		fieldCountySlct.selectByVisibleText(county);

		fieldContent.sendKeys(content);

		save.click();

		Thread.sleep(1000);

		System.out.println(simpleNotification.getText().toString());
		String message = simpleNotification.getText().toString();
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));

		return new AnnouncementsPage(driver);
	}

}
