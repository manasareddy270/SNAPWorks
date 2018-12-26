package com.snapworks.pages;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AnnouncementsPage extends LoadableComponent<AnnouncementsPage> {

	private WebDriver driver;

	@FindBy(xpath = "//span[contains(text(),'Reimbursements')]")
	private WebElement reimbursements;

	@FindBy(xpath = "//span[contains(text(),' Case Notes')]")
	private WebElement caseNotes;

	@FindBy(xpath = "//span[contains(text(),' Non-Compliance')]")
	private WebElement nonComplaince;

	@FindBy(xpath = "//span[contains(text(),'Contact History')]")
	private WebElement contactHistory;

	@FindBy(id = "field_uname")
	private WebElement userName;

	@FindBy(id = "field_pword")
	private WebElement password;

	@FindBy(xpath = "//select[@id = 'filterType']")
	private WebElement filterType;

	@FindBy(xpath = "//input[@id = 'filterText']")
	private WebElement filterInput;

	@FindBy(xpath = "//span[@ref = 'lbRecordCount']")
	private WebElement pageCount;

	@FindBy(xpath = "//span[@class = 'ag-icon ag-icon-menu']")
	private WebElement filerMenu;

	@FindBy(xpath = "//button[contains(text(),'Next')]")
	private WebElement nextButton;

	@FindBy(xpath = "//span[contains(text(),'ADD NEW')]")
	private WebElement addNew;

	public AnnouncementsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		// assertTrue(driver.getTitle().equals("Worker Portal"));
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Announcement Summary')]")).isDisplayed());

	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public AnnouncementsDetailsPage getAnnouncementsDetailsPage() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(addNew));
		addNew.click();
		return new AnnouncementsDetailsPage(driver);

	}

	public ReimbursementPage getReimbursementPage() {

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(reimbursements));
		reimbursements.click();
		return new ReimbursementPage(driver);
	}

	public NonCompliancePage getNonComplaincePage() {

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(nonComplaince));
		nonComplaince.click();
		return new NonCompliancePage(driver);
	}

	public int getColumnNum(String columnName) {
		// new WebDriverWait(driver,
		// 6).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='ag-header-row']"))));

		int colNum = driver
				.findElements(By.xpath("//div[@class='ag-header-row']//span[contains(text(),'" + columnName
						+ "')]/ancestor::div[@class='ag-header-cell ag-header-cell-sortable']/preceding-sibling::div"))
				.size() + 1;
		return colNum;

	}

	public <Any> Any getAnnouncementSummaryTable(String columnName) throws InterruptedException {

		int columnNum = getColumnNum(columnName);
		Thread.sleep(100);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(100))
				.pollingEvery(Duration.ofMillis(1000)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//div[@role='row']//div[@role='gridcell']"))));

		// ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
		// driver.findElement(By.xpath("//div[@role='row']//div[@role='gridcell']")));
		// new WebDriverWait(driver,
		// 6).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@role='row']//div[@role='gridcell']"))));
		// driver.findElement(By.xp4ath("//div[@role='gridcell']//a/../following-sibling::div["+columnNum+"]/a")).click();
		List<WebElement> columnValues = driver
				.findElements(By.xpath("//div[@role='row']//div[@role='gridcell'][" + columnNum + "]"));

		return (Any) columnValues;

	}

	// Method to validate whether Created Announcement is update in the Announcement
	// Summary table
	// and also validating for duplicates
	public boolean verifyAnnouncementUpdated(String titleValue) throws InterruptedException {

		Thread.sleep(700);
		int totalPageCount = Integer.parseInt(pageCount.getText());
		filerMenu.click();
		Thread.sleep(100);
		String filtertype = "equals";
		Select yearFromCombo = new Select(filterType);
		yearFromCombo.selectByValue(filtertype);

		filterInput.sendKeys(titleValue);

		Thread.sleep(700);
		int number = Integer.parseInt(pageCount.getText());

		if (number > 1) {
			System.out
					.println("Announcement Updated but there are " + number + " records for title " + titleValue + "");
			return false;
		} else if (number == 1) {
			System.out.println("Announcement History is updated with " + titleValue + "");
			return true;
		} else {
			System.out.println(
					"ERROR:---> Newly created announcement record is not updated in the Announcement History page with "
							+ titleValue + "");
			// assertTrue(number==1, "Announcement not created");
			return false;
		}

	}

	public ContactHistoryPage getContactHistoryPage() {
		// TODO Auto-generated method stub
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(contactHistory));
		contactHistory.click();
		return new ContactHistoryPage(driver);
	}

}
