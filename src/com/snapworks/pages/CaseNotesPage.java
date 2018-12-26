package com.snapworks.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CaseNotesPage extends LoadableComponent<CaseNotesPage> {

	private WebDriver driver;

	@FindBy(xpath = "//span[contains(text(),'ADD NEW')]")
	private WebElement addNew;

	@FindBy(xpath = "//input[@id='currentSearch']")
	private WebElement currentSearch;

	@FindBy(xpath = "//span[@ref = 'lbRecordCount']")
	private WebElement pageCount;
	
	@FindBy(xpath = "//span[contains(text(),'Create Task')]")
	private WebElement createTask;

	public CaseNotesPage(WebDriver driver) {
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
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Case Notes')]")).isDisplayed());
	}

	public AddCaseNotePage getAddCaseNotePage() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(addNew));
		addNew.click();
		return new AddCaseNotePage(driver);
	}

	public boolean getFilterdResults(String searchValue) throws InterruptedException {
		Thread.sleep(1000);
		currentSearch.sendKeys(searchValue);
		Thread.sleep(700);
		int totalPageCount = Integer.parseInt(pageCount.getText());
		if (totalPageCount == 1) {
			System.out.println("Case Notes added successfully");
			return true;
		} else {
			System.out.println("Duplicate records are present");
			return false;
		}
	}
	
	public CreateTaskPage getCreateTaskPage() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(createTask));
		createTask.click();
		return new CreateTaskPage(driver);
	}

}
