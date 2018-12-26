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

public class TestsandScoresPage extends LoadableComponent<EducationCertificationsPage> {

	private WebDriver driver;

	@FindBy(xpath = "//label[@for ='testsNo']")
	private WebElement testsNo;

	@FindBy(xpath = "//label[@for ='testsYes']")
	private WebElement testsYes;

	@FindBy(xpath = "//span[contains(text(),' ADD NEW')]")
	private WebElement addNew;

	@FindBy(xpath = "//div[@col-id='testCd']//select[@class='ag-cell-edit-input']")
	private WebElement testDD;

	@FindBy(xpath = "//div[@role='gridcell'][@col-id='score']")
	private WebElement score;

	@FindBy(xpath = "//span[contains(text(),'SAVE')]")
	private WebElement save;

	@FindBy(partialLinkText = "Barriers")
	private WebElement Barriers;

	@FindBy(xpath = "//div[@class='simple-notification-wrapper top right']")
	private WebElement simpleNotification;

	public TestsandScoresPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Tests & Scores')]")).isDisplayed());
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public String SaveTestScores(String testScores) throws InterruptedException {
		Thread.sleep(1000);
		new WebDriverWait(driver, 1000)
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//label[@for ='testsYes']"))));
		if (testScores.toUpperCase().equals("YES")) {
			testsYes.click();
			addNew.click();
			Select selectvalue = new Select(testDD);
			selectvalue.selectByVisibleText("LD Screening");
			score.sendKeys("990");
		} else {
			testsNo.click();
		}

		save.click();
		//Thread.sleep(2000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		System.out.println(simpleNotification.getText().toString());
		String message = simpleNotification.getText().toString();
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		return message;

		// new WebDriverWait(driver,
		// 6).until(ExpectedConditions.visibilityOf(Barriers));
		// Barriers.click();
		// return (Any) new BarrierPage(driver);
	}

	public BarrierPage getBarriersPage() throws InterruptedException {
		Thread.sleep(1000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(Barriers));
		Barriers.click();
		return new BarrierPage(driver);
	}

}
