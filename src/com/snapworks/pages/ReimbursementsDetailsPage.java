package com.snapworks.pages;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReimbursementsDetailsPage extends LoadableComponent<ReimbursementsDetailsPage> {

	private WebDriver driver;

	@FindBy(id = "field_vendorName")
	private WebElement payeeVendorName;

	@FindBy(xpath = "//select[@id = 'field_reasonCd']")
	private WebElement fieldReasonCd;

	@FindBy(id = "field_requestedDt")
	private WebElement fieldrequestedDt;

	@FindBy(xpath = "//ngb-datepicker-navigation-select/select[1]")
	private WebElement fieldMonth;

	@FindBy(xpath = "//ngb-datepicker-navigation-select/select[2]")
	private WebElement fieldYear;

	@FindBy(id = "field_requestedAmount")
	private WebElement fieldRequestedAmount;

	@FindBy(xpath = "//input[@id='rmFile']")
	private WebElement uploadFormsInvoices;

	@FindBy(xpath = "//a[@id='uploadFile0']")
	private WebElement uploadFile;

	@FindBy(xpath = "	//div[@id='progress0']")
	private WebElement uploadProcressBar;

	@FindBy(xpath = "//span[contains(text(),'SAVE')]")
	private WebElement save;

	@FindBy(xpath = "//div[@class='simple-notification-wrapper top right']")
	private WebElement simpleNotification;
	

	@FindBy(xpath = "//select[@id='field_decisionCd']")
	private WebElement reimbursementDDD;
	
	@FindBy(xpath = "//span[contains(text(),' Case Notes')]")
	private WebElement caseNotes;


	public ReimbursementsDetailsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Reimbursements Details')]")).isDisplayed());
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public ReimbursementPage getReimbursementSummarryPage(String vendorName, String reason, String requestedDate,
			String amount) throws InterruptedException {

		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		Date today = cal.getTime();
		int dayNum = today.getDay();
		int day = today.getDate();
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

		String LabelDate = dayText + ", " + sdf.format(today);
		Thread.sleep(1000);
		payeeVendorName.sendKeys(vendorName);
		Thread.sleep(1200);
		
		Select fieldReasonSlct = new Select(fieldReasonCd);
		fieldReasonSlct.selectByVisibleText(reason);

		fieldrequestedDt.click();

		driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='" + LabelDate
				+ "']/div[contains(text(),'" + day + "')]")).click();

		fieldRequestedAmount.sendKeys(amount);
		Thread.sleep(1000);
		uploadFile.click();
		Thread.sleep(4000);
		String progressBar = uploadProcressBar.getText().toString();
		if (progressBar.equals("100%")) {
			save.click();
		} else {
			System.out.println("File upload failed or file is not selected");
		}

		Thread.sleep(1000);
		System.out.println(simpleNotification.getText().toString());
		String message = simpleNotification.getText().toString();
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		return new ReimbursementPage(driver);
	}

	public void fillReimbursementRequest(String filePath) throws InterruptedException {
		Thread.sleep(400);
		uploadFormsInvoices.click();

		uploadFileWithRobot(filePath);

	}

	public void uploadFileWithRobot(String filePath) {

		StringSelection stringSelection = new StringSelection(filePath);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);

		Robot robot = null;

		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}

		robot.delay(250);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(300);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(300);
		  robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.delay(300);
		robot.keyRelease(KeyEvent.VK_V);
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(300);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(150);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public ReimbursementPage reimbursementDecision() {
		Select reimbursememtDec = new Select(reimbursementDDD);
		reimbursememtDec.selectByVisibleText("Approved");
		save.click();
		return new ReimbursementPage(driver);
		
	}
	public CaseNotesPage getCaseNotesPage() {

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(caseNotes));
		caseNotes.click();
		return new CaseNotesPage(driver);
	}

}
