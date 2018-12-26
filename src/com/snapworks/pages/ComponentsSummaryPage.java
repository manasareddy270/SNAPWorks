package com.snapworks.pages;

import static org.testng.Assert.assertTrue;

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

public class ComponentsSummaryPage extends LoadableComponent<ComponentsSummaryPage> {

	private WebDriver driver;

	@FindBy(xpath = "//span[contains(text(), 'ADD NEW')]")
	private WebElement addNew;

	@FindBy(xpath = "//button[@class='btn  btn-outline-primary']")
	private WebElement addNewButton;
	
	@FindBy(xpath = "//a[contains(text(),'Track Component')]")
	private WebElement trackComponent;
	
	@FindBy(xpath = "//span[contains(text(), 'PRINT EMPLOYMENT PLAN')]")
	private WebElement printEmpPlanBtn;

	@FindBy(xpath = "//span[@ref = 'lbRecordCount']")
	private WebElement pageCount;

	@FindBy(xpath = "//div[@col-id='serviceProviderName']//span[@class = 'ag-icon ag-icon-menu']")
	private WebElement filterMenuServiceProvider;

	@FindBy(xpath = "//select[@id = 'filterType']")
	private WebElement filterType;

	@FindBy(xpath = "//input[@id='filterText']")
	private WebElement fliterInputValue;

	@FindBy(xpath = "//div[@col-id='componentCd']//span[@class = 'ag-icon ag-icon-menu']")
	private WebElement filterMenuComponenetName;

	@FindBy(xpath = "//div[@col-id='startDt']//span[@class = 'ag-icon ag-icon-menu']")
	private WebElement filterMenuStartDt;

	@FindBy(xpath = "//div[@col-id='estimatedCompletionDt']//span[@class = 'ag-icon ag-icon-menu']")
	private WebElement filterMenuEndDt;

	@FindBy(xpath = "//a//span[contains(text(),'Employment Plan')]")
	private WebElement empPlanLink;

	public ComponentsSummaryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Component Summary')]")).getText().toString()
				.contains("Component Summary"));
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public boolean clickAddNew() throws InterruptedException {
		Thread.sleep(1000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(addNewButton));

		String button = addNewButton.getAttribute("outerHTML").toString();

		if (button.contains("disabled")) {
			System.out.println("AddNew Button is Disabled");
			printEmpPlanBtn.click();
			return true;
		} else {
			empPlanLink.click();
			return false;
		}
	}

	public void verifyUpdatedComponent(String Provider, String comp) throws InterruptedException {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String starDate = sdf.format(now);

		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date calDt = cal.getTime();
		String endDate = sdf.format(calDt);

		Thread.sleep(100);
		int totalPageCount = Integer.parseInt(pageCount.getText());
		if (totalPageCount > 0) {
			filterMenuServiceProvider.click();
			String filtertype = "equals";
			Select filtertypeCombo = new Select(filterType);
			filtertypeCombo.selectByValue(filtertype);
			fliterInputValue.sendKeys(Provider);
			int RowCount = Integer.parseInt(pageCount.getText());

			if (RowCount > 1) {
				filterMenuComponenetName.click();
				filtertype = "equals";
				filtertypeCombo = new Select(filterType);
				filtertypeCombo.selectByValue(filtertype);
				fliterInputValue.sendKeys(comp);
				Thread.sleep(100);
				RowCount = Integer.parseInt(pageCount.getText());
				if (RowCount > 1) {
					filterMenuStartDt.click();
					filtertype = "equals";
					filtertypeCombo = new Select(filterType);
					filtertypeCombo.selectByValue(filtertype);
					fliterInputValue.sendKeys(starDate);
					Thread.sleep(100);
					RowCount = Integer.parseInt(pageCount.getText());
					if (RowCount > 1) {
						filterMenuEndDt.click();
						filtertype = "equals";
						filtertypeCombo = new Select(filterType);
						filtertypeCombo.selectByValue(filtertype);
						fliterInputValue.sendKeys(endDate);
						Thread.sleep(100);
						RowCount = Integer.parseInt(pageCount.getText());
						if (RowCount > 1) {
							System.out.println("Duplicate Records are present");
						} else {
							System.out.println("Component record is saved successfully");
						}
					}
				} else {
					System.out.println("Component record is saved successfully");
				}

			} else {
				System.out.println("Component record is saved successfully");
			}

		} else {
			System.out.println("No Component record is saved ");
		}

	}

	public EmploymentPlanPage getEmploymentPlanPage() {
		
		return new EmploymentPlanPage(driver);
	}

	public ComponentsDetailsPage getComponentsDetailsPage() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(addNewButton));
		addNew.click();
		return new ComponentsDetailsPage(driver);
	}

	public CorrespondencePage getCorrespondencePage() {
		return new CorrespondencePage(driver);
	}
	
	public TrackComponentPage getTrackComponent() throws InterruptedException {
		Thread.sleep(2000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(trackComponent));
		
		trackComponent.click();
		
		return new TrackComponentPage(driver);
	}
}
