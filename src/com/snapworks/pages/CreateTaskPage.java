package com.snapworks.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateTaskPage extends LoadableComponent<CreateTaskPage> {

	private WebDriver driver;

	@FindBy(id = "field_taskType")
	private WebElement taskType;
	
	@FindBy(id = "field_taskStatus")
	private WebElement taskStatus;
	

	@FindBy(xpath = "//select[@id='field_assignedToC']")
	private WebElement assignedTop;

	@FindBy(id = "field_notes")
	private WebElement taskNotes;

	@FindBy(xpath = "//label[@for ='field_county']")
	private WebElement county;

	@FindBy(xpath = "//input[@id = 'field_provider']")
	private WebElement provider;

	@FindBy(xpath = "//button[contains(text(),'SAVE')]")
	private WebElement save;

	@FindBy(xpath = "//div[@class='simple-notification-wrapper top right']")
	private WebElement simpleNotification;
	
	@FindBy(xpath = "//span[contains(text(),' Reimbursements')]")
	private WebElement  Reimbursements;
	
	@FindBy(xpath = "//span[contains(text(),'Employment Plan')]")
	private WebElement employmentPage;

	public CreateTaskPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Create Task')]")).isDisplayed());
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public String saveNewTask(String type, String assignedTo, String assigned, String notes)
			throws InterruptedException {

		Thread.sleep(1000);
		String taskAssignedTo = assignedTo.toUpperCase();
		Select typeFromCombo = new Select(taskType);
		// typeFromCombo.selectByValue(type);
		typeFromCombo.selectByVisibleText(type);

		new WebDriverWait(driver, 1000)
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//label[@for ='field_county']"))));
		if (taskAssignedTo.equals("COUNTY")) {

			county.click();
			Thread.sleep(1000);
			Select assignedTopFromCombo = new Select(assignedTop);
			assignedTopFromCombo.selectByVisibleText(assigned);

		}

		else {
			provider.click();
			Thread.sleep(100);

			Select assignedTopFromCombo = new Select(assignedTop);
			assignedTopFromCombo.selectByVisibleText(assigned);
		}

		if (type.toUpperCase().equals("ADDRESS CHANGE")) {

			taskNotes.sendKeys(notes);
		}

		save.click();
		Thread.sleep(1000);
		System.out.println(simpleNotification.getText().toString());
		String message = simpleNotification.getText().toString();
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		return message;
	}
	
	public void closeTask() throws InterruptedException {
		Thread.sleep(1000);
		Select tasksts = new Select(taskStatus);
		tasksts.selectByVisibleText("Closed");
		save.click();
		Thread.sleep(1000);
		System.out.println(simpleNotification.getText().toString());
		String message = simpleNotification.getText().toString();
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
	}
	
	public ReimbursementPage getReimbursement() throws InterruptedException {
	
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(Reimbursements));
		Thread.sleep(4000);
		Reimbursements.click();
		return new ReimbursementPage(driver);
	}
	
	public EmploymentPlanPage getEmploymentPlanPage() throws InterruptedException {
		Thread.sleep(1000);
		employmentPage.click();
		return new EmploymentPlanPage(driver);
	}

}
