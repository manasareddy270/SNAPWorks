package com.snapworks.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends LoadableComponent<SearchPage> {
	private WebDriver driver;

	@FindBy(xpath = "//span[text()='SEARCH']")
	private WebElement searchBtn;

	@FindBy(id = "field_taskType")
	private WebElement taskType;

	@FindBy(id = "field_taskStartDt")
	private WebElement TaskFromDt;

	@FindBy(id = "field_taskDueDt")
	private WebElement TaskToDt;

	@FindBy(xpath = "/span[@class='dropdown-btn']")
	private WebElement TaskStatus;

	@FindBy(xpath = "//span[contains(text(),'Contact History')]")
	private WebElement contactHistory;

	@FindBy(xpath = "//div[@row-index='0']//div[@col-id='0']/a")
	private WebElement taskTypeLink;

	@FindBy(xpath = "//span[contains(text(),'BACK TO SEARCH')]")
	private WebElement backToSearch;

	@FindBy(xpath = "//span[contains(text(),'Employment Plan')]")
	private WebElement employmentPlan;

	@FindBy(xpath = "//span[contains(text(),'Appointments')]")
	private WebElement appoinmentsPage;

	@FindBy(xpath = "//div[@col-id='wpPerson.firstName&wpPerson.lastName']//span[@class='ag-icon ag-icon-menu']")
	private WebElement clinetNameHeader;

	@FindBy(xpath = "//select[@id='filterType']")
	private WebElement filterType;

	@FindBy(xpath = "//input[@id='filterText']")
	private WebElement filterText;

	@FindBy(xpath = "//span[@ref='lbRecordCount']")
	private WebElement recordCount;

	@FindBy(xpath = "//div[@row-index='0']//div[@col-id='0']/a")
	private WebElement firstAppoinmenttasktypevalue;
	
	@FindBy(xpath = "//span[contains(text(),' Non-Compliance')]")
	private WebElement nonComplaince;

	public SearchPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
		assertTrue(
				driver.findElement(By.xpath("//a[contains(text(),'Search')]")).getText().toString().contains("Search"));

	}

	public WebElement getSearchBtn() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchBtn));
		return searchBtn;
	}

	public void getSearchResults() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchBtn));
		searchBtn.click();
		new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.xpath("//div[@role='gridcell']/a")).isDisplayed();
			}
		});

	}

	public void getSearchResults(String taskTyp) throws InterruptedException {
		Thread.sleep(1000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(taskType));

		Select task = new Select(taskType);
		task.selectByVisibleText(taskTyp);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchBtn));
		searchBtn.click();
		Thread.sleep(4000);
		/*new WebDriverWait(driver, 30).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.xpath("//div[@role='gridcell']//a")).isDisplayed();
			}
		});*/

	}

	public WebElement clickTaskType() {

		taskTypeLink.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(backToSearch));
		return backToSearch;
	}

	public void selectFirstTask(String tasktypeoption, String ClntNm) throws InterruptedException {
		Thread.sleep(1000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(taskType));
		getSearchResults(tasktypeoption);
		Thread.sleep(1000);
		// new WebDriverWait(driver,
		// 6).until(ExpectedConditions.visibilityOf(firstReferaltasktypevalue));
		if (ClntNm == "") {
			taskTypeLink.click();
		} else {
			
			clinetNameHeader.click();
			String filterName = "Equals";
			Select filter = new Select(filterType);
			filter.selectByVisibleText(filterName);
			filterText.sendKeys(ClntNm);
			Thread.sleep(1000);
			int recordcnt = Integer.parseInt(recordCount.getText());
			if (recordcnt > 0) {
				firstAppoinmenttasktypevalue.click();
			} else {
				System.out.println("Failed: No appointment records found for " + ClntNm + "");
			}

		}

	}

	public ContactHistoryPage getContactHistoryPage() throws InterruptedException {
		Thread.sleep(100);
		return new ContactHistoryPage(driver);
	}

	public AppointmentPage getAppoinmentsPage() {

		return new AppointmentPage(driver);
	}

	public EmploymentPlanPage getEmploymentPage() throws InterruptedException {
		Thread.sleep(100);
		return new EmploymentPlanPage(driver);

	}

	public AssessmentPage getAssessmentSummaryPage() throws InterruptedException {
	
		return new AssessmentPage(driver);
	}

	public ComponentsSummaryPage getComponentPage() {
		return new ComponentsSummaryPage(driver);
	}
	
	public NonCompliancePage getNonComplaincePage() {

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(nonComplaince));
		
		return new NonCompliancePage(driver);
	}
	
	public CreateTaskPage getCreateTask() throws InterruptedException {
		Thread.sleep(100);
		return new CreateTaskPage(driver);
	}
	
	public ReimbursementPage getReimbursement() {

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(nonComplaince));
		
		return new ReimbursementPage(driver);
	}

	

}
