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

public class AddCaseNotePage extends LoadableComponent<AddCaseNotePage> {

	private WebDriver driver;

	@FindBy(id = "field_title")
	private WebElement fieldType;

	@FindBy(id = "field_content")
	private WebElement fieldContent;

	@FindBy(xpath = "//button[contains(text(),'SAVE')]")
	private WebElement save;

	@FindBy(xpath = "//div[@class='simple-notification-wrapper top right']")
	private WebElement simpleNotification;

	@FindBy(xpath = "//span[contains(text(),'Case Notes')]")
	private WebElement caseNotes;

	public AddCaseNotePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Add Note')]")).isDisplayed());
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public WebElement addcaseNotes(String type, String content) throws InterruptedException {
		Thread.sleep(1000);
		Select fieldTypeSlct = new Select(fieldType);
		fieldTypeSlct.selectByVisibleText(type);
		fieldContent.sendKeys(content);
		save.click();
		Thread.sleep(1000);
		System.out.println(simpleNotification.getText().toString());
		String message = simpleNotification.getText().toString();
		new WebDriverWait(driver, 100).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(caseNotes));
		return caseNotes;
	}

	public CaseNotesPage getcaseNotesPage() {
		return new CaseNotesPage(driver);
	}

}
