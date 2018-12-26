package com.snapworks.pages;

import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
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

public class ContactHistoryPage extends LoadableComponent<ContactHistoryPage> {

	private WebDriver driver;

	@FindBy(xpath = "//span[contains(text(),'ADD NEW')]")
	private WebElement addNew;

	@FindBy(xpath = "//span[contains(text(),'Appointments')]")
	private WebElement appointmentTab;
	// span[contains(text(),'Appointments')]

	@FindBy(xpath = "//span[contains(text(),' Assessment')]")
	private WebElement assesmentTab;

	@FindBy(xpath = "//select[@id = 'filterType']")
	private WebElement filterType;

	@FindBy(xpath = "//input[@id = 'filterText']")
	private WebElement filterInputmethod;

	@FindBy(xpath = "//input[@id = 'filterText']")
	private WebElement filterInputReason;

	@FindBy(xpath = "//div[@class = 'ag-filter-date-from']/input[@class='ag-filter-filter']")
	private WebElement filterInputContactDate;

	@FindBy(xpath = "//input[@id = 'filterText']")
	private WebElement filterInputOutcome;

	@FindBy(xpath = "//input[@id = 'filterText']")
	private WebElement filterInputNotes;

	@FindBy(xpath = "//span[@ref = 'lbRecordCount']")
	private WebElement pageCount;

	@FindBy(xpath = "//div[@col-id='contactDt']//span[@class = 'ag-header-icon ag-header-cell-menu-button']")
	private WebElement filterMenucol1;

	@FindBy(xpath = "//div[@col-id='contactMethodCd']//span[@class = 'ag-header-icon ag-header-cell-menu-button']")
	private WebElement filterMenucol2;

	@FindBy(xpath = "//div[@col-id='contactReasonCd']//span[@class = 'ag-header-icon ag-header-cell-menu-button']")
	private WebElement filterMenucol3;

	@FindBy(xpath = "//div[@col-id='contactOutcomeCd']//span[@class = 'ag-header-icon ag-header-cell-menu-button']")
	private WebElement filterMenucol4;

	@FindBy(xpath = "//div[@col-id='notes']//span[@class = 'ag-header-icon ag-header-cell-menu-button']")
	private WebElement filterMenucol5;

	@FindBy(xpath = "//button[contains(text(),'Next')]")
	private WebElement nextButton;

	@FindBy(xpath = "//span[contains(text(),'Contact History')]")
	private WebElement contactHistory;

	@FindBy(xpath = "//span[contains(text(),' Case Notes')]")
	private WebElement caseNotes;

	@FindBy(xpath = "//span[contains(text(),'Create Task')]")
	private WebElement createTask;

	@FindBy(xpath = "//span[contains(text(),' Non-Compliance')]")
	private WebElement nonComplaince;

	@FindBy(xpath = "//span[contains(text(),'Appointments')]")
	private WebElement appoinmentsPage;
	
	@FindBy(xpath = "//span[contains(text(),'Reimbursements')]")
	private WebElement reimbursements;


	public ContactHistoryPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Contact History')]")).isDisplayed());
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public ContactDetailsPage getContactDetailsPage() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(addNew));
		addNew.click();
		return new ContactDetailsPage(driver);
	}

	public AppointmentPage getContactHistoryPage() {
		// TODO Auto-generated method stub
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(contactHistory));
		contactHistory.click();
		return new AppointmentPage(driver);
	}

	// Method to validate whether Created Announcement is update in the Announcement
	// Summary table
	// and also validating for duplicates
	public String verifyContactHistoryUpdated(String method, String reason, String outcome, String ctNotes)
			throws InterruptedException {
		String message = "";
		Thread.sleep(100);
		int totalPageCount = Integer.parseInt(pageCount.getText());
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
		String today = sdf.format(now);
		filterMenucol1.click();

		Thread.sleep(100);
		String filtertype = "equals";
		Select filtertypeCombo = new Select(filterType);
		filtertypeCombo.selectByValue(filtertype);
		Thread.sleep(100);
		filterInputContactDate.sendKeys(today);

		Thread.sleep(1000);
		int contactnumber = Integer.parseInt(pageCount.getText());

		if (contactnumber > 1) {
			filterMenucol5.click();
			Thread.sleep(100);

			Select filtertypeCombo1 = new Select(filterType);
			filtertypeCombo1.selectByValue(filtertype);

			filterInputmethod.sendKeys(ctNotes);

			Thread.sleep(1000);
			int methodnumber = Integer.parseInt(pageCount.getText());
			if (methodnumber > 1) {

				System.out.println("Contact History Updated but there are " + methodnumber + " records");

			} else {

				System.out.println("Contact History is updated");
				message = "Contact History is updated";
			}

		}

		else if (contactnumber == 0) {

			System.out.println("Contact History is not created or not Updated ");
			// assertTrue(number==1, "Announcement not created");
		} else {

			System.out.println("Contact History is updated");
			message = "Contact History is updated";
		}

		return message;

	}

	public AppointmentPage getAppointmentPage() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(appointmentTab));
		appointmentTab.click();
		return new AppointmentPage(driver);
	}

	public CaseNotesPage getCaseNotesPage() {

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(caseNotes));
		caseNotes.click();
		return new CaseNotesPage(driver);
	}

	public CreateTaskPage getCreateTaskPage() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(createTask));
		createTask.click();
		return new CreateTaskPage(driver);
	}

	public NonCompliancePage getNonComplaincePage() {

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(nonComplaince));
		nonComplaince.click();
		return new NonCompliancePage(driver);
	}

	public AssessmentPage getAssesmentPage() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(assesmentTab));
		assesmentTab.click();
		return new AssessmentPage(driver);
	}
	
	public ReimbursementPage getReimbursementPage() {

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(reimbursements));
		reimbursements.click();
		return new ReimbursementPage(driver);
	}

}
