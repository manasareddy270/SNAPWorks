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

public class EducationCertificationsPage extends LoadableComponent<EducationCertificationsPage> {

	private WebDriver driver;
	@FindBy(id = "field_content")
	private WebElement highschoolattended;

	@FindBy(id = "field_title")
	private WebElement yrOfSchlGrad;

	@FindBy(id = "field_highestEducation")
	private WebElement highestschl;

	@FindBy(xpath = "//span[contains(text(),' ADD EDUCATION')]")
	private WebElement addEduBtn;

	@FindBy(xpath = "//div[@row-index='0']//div[@col-id='educationCd']//select[@class='ag-cell-edit-input']") // Certification
	private WebElement edoptnDD;

	@FindBy(xpath = "//span[contains(text(),'SAVE')]")
	private WebElement save;

	@FindBy(xpath = "//div[@class='ag-row ag-row-even ag-row-level-0 ag-row-focus ag-row-not-inline-editing']//div[@col-id='schoolName']")
	private WebElement school;

	@FindBy(xpath = "//div[@class='ag-row ag-row-even ag-row-level-0 ag-row-focus ag-row-inline-editing']//div[@col-id='fieldOfStudy']")
	private WebElement study;

	@FindBy(xpath = "//div[@class='ag-row ag-row-even ag-row-level-0 ag-row-focus ag-row-inline-editing']//div[@col-id='degreeObtained']")
	private WebElement degreeObtained;

	@FindBy(xpath = "//div[@class='ag-row ag-row-even ag-row-level-0 ag-row-focus ag-row-inline-editing']//div[@col-id='yearObtained']")
	private WebElement yearObtained;

	@FindBy(xpath = "//span[contains(text(),'ADD CERTIFICATION')]")
	private WebElement addCertification;

	@FindBy(xpath = "//select[@class='ag-cell-edit-input']")
	private WebElement CertificationDD;

	@FindBy(xpath = "//div[@class='ag-row ag-row-even ag-row-level-0 ag-row-focus ag-row-not-inline-editing']//div[@col-id='typeCd']")
	private WebElement CertificationType;

	@FindBy(xpath = "//select[@class='ag-cell-edit-input']")
	private WebElement CertificationTypeDD;

	@FindBy(xpath = "//div[@class='ag-cell ag-cell-not-inline-editing ag-cell-with-height ag-cell-no-focus ag-cell-value'][@col-id='yearObtained']")
	private WebElement CertificationYearObtained;

	// @FindBy(xpath="//div[@class='ag-row ag-row-even ag-row-level-0 ag-row-focus
	// ag-row-selected ag-row-inline-editing']//div[@col-id='yearObtained']")
	// private WebElement CertificationYearObtainedInput;

	@FindBy(partialLinkText = "Skills & Strengths")
	private WebElement SkillsandStrength;

	@FindBy(xpath = "//div[@class='simple-notification-wrapper top right']")
	private WebElement simpleNotification;

	public EducationCertificationsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub

		assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Education & Certifications')]")).isDisplayed());

	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public String SaveEducationDetails(String HighSchoolAttended, String GraduationYear, String EducationLevel)
			throws InterruptedException {
		Thread.sleep(1000);
		highschoolattended.sendKeys(HighSchoolAttended);

		Select Grdyr = new Select(yrOfSchlGrad);
		Grdyr.selectByVisibleText(GraduationYear);

		Select Edlvl = new Select(highestschl);
		Edlvl.selectByVisibleText(EducationLevel);

		addEduBtn.click();
		Select dd = new Select(edoptnDD);
		dd.selectByVisibleText("Certification");
		school.sendKeys("TTest");
		study.sendKeys("TTest");
		degreeObtained.sendKeys("TTest");
		yearObtained.sendKeys("22017");

		addCertification.click();
		Select lcDD = new Select(CertificationDD);
		lcDD.selectByVisibleText("Professional Certification");

		CertificationType.click();
		Select ctDD = new Select(CertificationTypeDD);
		ctDD.selectByVisibleText("Accounting and Finance");

		CertificationYearObtained.sendKeys("22018");
		
		save.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		//new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(simpleNotification));
		System.out.println(simpleNotification.getText().toString());
		String message = simpleNotification.getText().toString();
		new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		return message;

	}

	public SkillsandStrength getSkillsandStrengthPage() throws InterruptedException {
		Thread.sleep(1000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(SkillsandStrength));
		SkillsandStrength.click();
		
		return new SkillsandStrength(driver);

	}

}
