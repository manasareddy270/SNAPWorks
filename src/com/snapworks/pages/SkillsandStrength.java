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

public class SkillsandStrength extends LoadableComponent<SkillsandStrength> {
	private WebDriver driver;

	@FindBy(xpath = "//label[@for ='licenseNo']")
	private WebElement licenseNo;

	@FindBy(xpath = "//label[@for ='skillsNo']")
	private WebElement skillsNo;

	@FindBy(xpath = "//label[@for ='licenseYes']")
	private WebElement licenseYes;

	@FindBy(xpath = "//label[@for ='skillsYes']")
	private WebElement skillsYes;

	@FindBy(xpath = "//span[contains(text(),' ADD SKILL')]")
	private WebElement addSkillBtn;

	@FindBy(xpath = "//select[@class='ag-cell-edit-input']")
	private WebElement skillDropdown;

	@FindBy(xpath = "//span[contains(text(),'ADD STRENGTH')]")
	private WebElement addStrengthBtn;

	@FindBy(xpath = "//div[@col-id='strengthCd']//select[@class='ag-cell-edit-input']")
	private WebElement strengthDD;

	@FindBy(xpath = "//span[contains(text(),'SAVE')]")
	private WebElement save;

	@FindBy(partialLinkText = "Tests & Scores")
	private WebElement TestsandScores;

	@FindBy(xpath = "//div[@class='simple-notification-wrapper top right']")
	private WebElement simpleNotification;

	public SkillsandStrength(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Skills & Strengths')]")).isDisplayed());
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public String SaveSkillsandStrengths(String driversLicense, String skillsStrengths) throws InterruptedException {
		Thread.sleep(1000);
		new WebDriverWait(driver, 100)
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//label[@for ='licenseYes']"))));
		// licenseYes.click();
		// skillsYes.click();
		if (driversLicense.toUpperCase().equals("YES")) {
			licenseYes.click();
		} else {
			licenseNo.click();
			// licenseYes.click();
		}
		if (skillsStrengths.toUpperCase().equals("YES")) {
			skillsYes.click();
			addSkillBtn.click();
			Select skillddvalue = new Select(skillDropdown);
			skillddvalue.selectByVisibleText("Bi-lingual");
			addStrengthBtn.click();
			Select Strengthvalue = new Select(strengthDD);
			Strengthvalue.selectByVisibleText("Accurate");
		} else {
			skillsNo.click();
		}

		/*
		 * java.util.List<WebElement> license =
		 * driver.findElements(By.name("licenseRadio")); String Value; int Size =
		 * license.size(); for(int i=0;i<license.size();i++){ Value =
		 * license.get(i).getAttribute("value"); if(Value.equals("Y"))
		 * license.get(i).click(); }
		 */
		save.click();
		//Thread.sleep(1000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		System.out.println(simpleNotification.getText().toString());
		String message = simpleNotification.getText().toString();
		new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		return message;

	}

	public TestsandScoresPage getTestsandScoresPage() throws InterruptedException {
		Thread.sleep(1000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(TestsandScores));
		TestsandScores.click();
		return new TestsandScoresPage(driver);
	}
}
