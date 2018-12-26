package com.snapworks.pages;

import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
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

public class NonCompliancePage extends LoadableComponent<NonCompliancePage> {

	private WebDriver driver;

	@FindBy(xpath = "//span[contains(text(),'ADD NEW')]")
	private WebElement addNew;

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

	@FindBy(xpath = "//span[@ref = 'lbRecordCount']")
	private WebElement pageCount;

	@FindBy(xpath = "//div[@col-id='nonCoopDiscoveryDt']//span[@class = 'ag-icon ag-icon-menu']")
	private WebElement filterMenunonCoopDiscoveryDt;

	@FindBy(xpath = "//div[@col-id='nonCoopMonthDt']//span[@class = 'ag-icon ag-icon-menu']")
	private WebElement filterMenunonCoopMonthDt;

	@FindBy(xpath = "//div[@col-id='nonComplianceReasonCd']//span[@class = 'ag-icon ag-icon-menu']")
	private WebElement filterMenunonComplianceReasonCd;

	@FindBy(xpath = "//div[@col-id='programCd']//span[]@class = 'ag-icon ag-icon-menu'")
	private WebElement filterMenuprogramCd;

	@FindBy(xpath = "//button[contains(text(),'Next')]")
	private WebElement nextButton;
	
	@FindBy(xpath = "//div[@class='client-info ng-star-inserted']//span[@class='info'][1]")
	private WebElement clientName;
	
	@FindBy(xpath = "//span[contains(text(),'Welcome ')]")
	private WebElement welcomeProfile;

	@FindBy(xpath = "//span[contains(text(),'LOGOUT')]")
	private WebElement logout;
	
	@FindBy(xpath = "//./..//a/i[@class='fa fa-pencil']")
	private WebElement editBtn;
	
	@FindBy(xpath = "//span[contains(text(),'Reimbursements')]")
	private WebElement reimbursements;
	
	
	public NonCompliancePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Non-Compliance Summary')]")).isDisplayed());
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}
	public int getColumnNum(String columnName){
//		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='ag-header-row']"))));
		int colNum = driver.findElements(By.xpath("//div[@class='ag-header-row']//span[contains(text(),'"+columnName+"')]/ancestor::div[@class='ag-header-cell ag-header-cell-sortable']/preceding-sibling::div")).size()+1;
		return colNum;
	}
	
	public boolean getEmploymentPlanSummaryCheck(String columnName) throws InterruptedException{
		Thread.sleep(1000);
		int count = 0;
		if (columnName.equals("Status")) {
			int columnNum = getColumnNum(columnName);
			Thread.sleep(100);
			List<WebElement> dates = driver.findElements(By.xpath("//div[@role='row']//div[@role='gridcell'][" + columnNum + "]"));
			for (WebElement date : dates) {
				String value = date.getText().toString();
				if ((value.equals("Closed"))) {
					return  true;
						
					//Discovery Date
				}
			}		
		}
		return false;	
	}

	public boolean AddNonComplianceDetailsPage() throws InterruptedException{
			Thread.sleep(500);
			int totalPageCount = Integer.parseInt(pageCount.getText());
			if(totalPageCount == 0){
				
				return true;
			}
		
		return false;
		
	}
	public NonComplianceDetailsPage getAddNonComplianceDetailsPage(String user) throws InterruptedException {
		Thread.sleep(700);
		if(user.equals("Provider")) {
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(addNew));
			
				addNew.click();
				Thread.sleep(500);
			
			
		}
		
		return new NonComplianceDetailsPage(driver);
	}

	// Method to validate whether Created Announcement is update in the Announcement
	// Summary table
	// and also validating for duplicates
	public void verifyNonComplianceSummaryCreated(String ncDiscoveryDate, String ncMonth, String reason, String user)
			throws InterruptedException {
		Thread.sleep(2000);
		int totalPageCount = Integer.parseInt(pageCount.getText());
		filterMenunonCoopDiscoveryDt.click();
		Thread.sleep(100);
		String filtertype = "equals";
		Select filtertypeCombo = new Select(filterType);
		filtertypeCombo.selectByValue(filtertype);
		Thread.sleep(100);
		filterInputContactDate.sendKeys(ncDiscoveryDate);

		Thread.sleep(1000);
		int dcDateRowCount = Integer.parseInt(pageCount.getText());

		if (dcDateRowCount > 1) 
		{
			/*filterMenunonCoopMonthDt.click();
			Thread.sleep(100);

			Select filtertypeCombo1 = new Select(filterType);
			filtertypeCombo.selectByValue(filtertype);
			SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
			filterInputmethod.sendKeys(sdf.format(ncMonth));*/

			Thread.sleep(1000);
			int ncMonthRowCount = Integer.parseInt(pageCount.getText());
			if (ncMonthRowCount > 1) {
				filterMenunonComplianceReasonCd.click();
				Thread.sleep(100);

				Select filtertypeCombo2 = new Select(filterType);
				filtertypeCombo2.selectByValue(filtertype);

				filterInputReason.sendKeys(reason);

				Thread.sleep(1000);
				int reasonRowCount = Integer.parseInt(pageCount.getText());
				if (reasonRowCount > 1) {

					System.out.println("Non Compliance Updated but there are " + reasonRowCount + " records");

				} else {

					System.out.println("Non Compliance is updated");
					if(user.equals("County")) {
						editBtn.click();
					}
				}
			} else {
				System.out.println("Non Compliance  is updated");
				if(user.equals("County")) {
					editBtn.click();
				}
			}

		}

		else if (dcDateRowCount == 0) {

			System.out.println("Non Compliance  is not created or not Updated ");
			// assertTrue(number==1, "Announcement not created");
		} else {

			System.out.println("Non Compliance  is updated");
			if(user.equals("County")) {
				editBtn.click();
			}
		}

	}
	
	public String getClientName() {
		String ClientName = clientName.getText();
		String[] temp = ClientName.split(", ");
		String result = "";

		// Iterate over the temp array and store
		// the string in reverse order.
		for (int i = 0; i < temp.length; i++) {
			if (i == temp.length - 1)
				result = temp[i] + result;
			else
				result = " " + temp[i] + result;
		}
		return result;
	}
	
	public LoginPage logOut() throws InterruptedException {
		Thread.sleep(3000);
		new WebDriverWait(driver, 100).until(ExpectedConditions.visibilityOf(welcomeProfile));
		welcomeProfile.click();
		new WebDriverWait(driver, 100).until(ExpectedConditions.visibilityOf(logout));
		Thread.sleep(1000);
		logout.click();
		return new LoginPage(driver);
	}
	public ReimbursementPage getReimbursementPage() {

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(reimbursements));
		reimbursements.click();
		return new ReimbursementPage(driver);
	}

}
