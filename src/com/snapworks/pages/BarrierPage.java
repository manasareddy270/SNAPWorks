package com.snapworks.pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BarrierPage extends LoadableComponent<BarrierPage> {
	private WebDriver driver;

	@FindBy(xpath = "//label[@for ='familyYes']")
	private WebElement familyYes;

	@FindBy(xpath = "//span[contains(text(),' ADD NEW')]")
	private WebElement addNew;

	@FindBy(xpath = "//div[@col-id='barrierTypeCd']//select[@class='ag-cell-edit-input']")
	private WebElement barriertype;

	@FindBy(xpath = "//div[@role='gridcell'][@col-id='barrierCd']")
	private WebElement barrierdd;

	@FindBy(xpath = "//div[@role='gridcell'][@col-id='barrierCd']//select[@class='ag-cell-edit-input']")
	private WebElement barrierDDValue;

	@FindBy(xpath = "//label[@for ='familyNo']")
	private WebElement familyNo;

	@FindBy(xpath = "//label[@for ='personalYes']")
	private WebElement personalYes;

	@FindBy(xpath = "//label[@for ='personalNo']")
	private WebElement personalNo;

	@FindBy(xpath = "//label[@for ='transportYes']")
	private WebElement transportYes;

	@FindBy(xpath = "//label[@for ='transportNo']")
	private WebElement transportNo;

	@FindBy(xpath = "//span[contains(text(),'SAVE')]")
	private WebElement save;

	@FindBy(partialLinkText = "Work History")
	private WebElement WorkHistory;

	@FindBy(xpath = "//div[@class='simple-notification-wrapper top right']")
	private WebElement simpleNotification;

	public BarrierPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Barriers')]")).isDisplayed());
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public String SaveBarriers(String family, String personal, String transportation) throws InterruptedException {
		Thread.sleep(1000);
		new WebDriverWait(driver, 1000)
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//label[@for ='familyYes']"))));
		if (family.toUpperCase().equals("YES")) {
			familyYes.click();
			addNew.click();
			Select barriertypedropdown = new Select(barriertype);
			barriertypedropdown.selectByVisibleText("Family");
			barrierdd.click();
			Select barrierdropdown = new Select(barrierDDValue);
			barrierdropdown.selectByVisibleText("Child with special needs");
		}

		else {
			familyNo.click();
		}

		if (personal.toUpperCase().equals("YES")) {
			personalYes.click();
		}

		else {
			personalNo.click();
		}
		if (transportation.toUpperCase().equals("YES")) {
			transportYes.click();
		}

		else {
			transportNo.click();
		}

		save.click();
		//Thread.sleep(1000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		System.out.println(simpleNotification.getText().toString());
		String message = simpleNotification.getText().toString();
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		return message;

		
	}

	public WorkHistoryPage getWorkHistoryPage() throws InterruptedException {
		Thread.sleep(1000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(WorkHistory));
		WorkHistory.click();
		return new WorkHistoryPage(driver);
	}

}
