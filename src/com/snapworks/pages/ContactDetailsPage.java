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

public class ContactDetailsPage extends LoadableComponent<ContactDetailsPage> {
	private WebDriver driver;

	@FindBy(id = "field_contactMethod")
	private WebElement contactMethod;

	@FindBy(id = "field_contactReason")
	private WebElement contactReason;

	@FindBy(id = "field_contactDate")
	private WebElement contactDate;

	@FindBy(xpath = "//ngb-datepicker-navigation-select/select[1]")
	private WebElement fieldMonth;

	@FindBy(xpath = "//ngb-datepicker-navigation-select/select[2]")
	private WebElement fieldYear;

	@FindBy(id = "field_contactOutcome")
	private WebElement contactOutcome;

	@FindBy(id = "field_notes")
	private WebElement notes;

	@FindBy(xpath = "//span[contains(text(),'SAVE')]")
	private WebElement save;

	@FindBy(xpath = "//div[@class='simple-notification-wrapper top right']")
	private WebElement simpleNotification;
	
	@FindBy(xpath = "//span[contains(text(),'Reimbursements')]")
	private WebElement reimbursements;


	public ContactDetailsPage(WebDriver driver) {
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
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(save));
		assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Contact Details')]")).isDisplayed());
	}

	public List<String> getDate() {

		// SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
		Date now = new Date();
		Calendar cal = Calendar.getInstance();

		cal.setTime(now);

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

	public boolean saveContactDetails(String method, String reason, String outcome, String ctNotes)
			throws InterruptedException {
		Thread.sleep(100);
		Select contactMethodselect = new Select(contactMethod);
		contactMethodselect.selectByVisibleText(method);

		Select contactReasonselect = new Select(contactReason);
		contactReasonselect.selectByVisibleText(reason);

		contactDate.click();
	
		List<String> Options = getDate();
		String LabelDate = Options.get(0) + ", " + Options.get(2);
		String day = Options.get(1);
		driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='" + LabelDate
				+ "']/div[contains(text(),'" + day + "')]")).click();

		Select contactOutcomeselect = new Select(contactOutcome);
		contactOutcomeselect.selectByVisibleText(outcome);

		notes.sendKeys(ctNotes);
		save.click();

		Thread.sleep(1000);

		System.out.println(simpleNotification.getText().toString());
		String message = simpleNotification.getText().toString();
		String expmsg = "Record updated successfully.";
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		if (message.contains(expmsg)) {
			return true;

		} else {
			return false;
		}
	}

	public ContactHistoryPage getContactHistoryPage() {
		return new ContactHistoryPage(driver);
	}
	
}
