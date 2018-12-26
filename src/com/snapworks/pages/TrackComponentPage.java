package com.snapworks.pages;

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

public class TrackComponentPage extends LoadableComponent<TrackComponentPage>{
	private WebDriver driver;
	
	
	@FindBy(xpath = "//span[@ref='lbLastRowOnPage']")
	private WebElement rowCount;
	
	@FindBy(xpath = "//span[contains(text(), 'ADD NEW')]")
	private WebElement addNew;
	
	@FindBy(xpath = "//div[@role='gridcell'][@col-id='component.authorizedHours']")
	private WebElement Authorizedhours;
	
	@FindBy(xpath = "//div[@role='gridcell'][@col-id='completedHours']")
	private WebElement CompletedHours;
	
	@FindBy(xpath = "//div[@role='gridcell'][@col-id='metPlanCd']")
	private WebElement MetPlan;
	
	@FindBy(xpath = "//div[@role='gridcell'][@col-id='metPlanCd']//select")
	private WebElement MetPlanDD;
	
	@FindBy(xpath = "//span[contains(text(), 'SAVE')]")
	private WebElement save;
	
	@FindBy(xpath="//div[@class='simple-notification-wrapper top right']")
	private WebElement simpleNotification;
	
	@FindBy(xpath = "//span[contains(text(),' Non-Compliance')]")
	private WebElement nonComplaince;
	
	public TrackComponentPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		/*WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(addNew));*/
	}
	
	public boolean completeMetPlan(String columnName) throws InterruptedException {
		Thread.sleep(1000);
		addNew.click();
		Thread.sleep(500);
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(simpleNotification.findElement(By.xpath("//*"))));
		System.out.println(simpleNotification.getText().toString());
		String message = simpleNotification.getText().toString();
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		int recordCnt = Integer.parseInt(rowCount.getText().toString());
		if(recordCnt > 0) {
			System.out.println(message);
			return completeTrackComp(columnName);
		}
		else {
			System.out.println("There is no new component to track");
		}
		return false;
		
		
	}

	public int getColumnNum(String columnName) {
		
		int colNum = driver
				.findElements(By.xpath("//div[@class='ag-header-row']//span[contains(text(),'" + columnName
						+ "')]/ancestor::div[@class='ag-header-cell ag-header-cell-sortable']/preceding-sibling::div"))
				.size() + 1;
		return colNum;

	}
	
	public boolean completeTrackComp(String columnName) throws InterruptedException {
		Thread.sleep(1000);
		if (columnName.equals("Met Plan?")) {
			int columnNum = getColumnNum(columnName);
			Thread.sleep(100);
			List<WebElement> statuses = driver
					.findElements(By.xpath("//div[@role='row']//div[@role='gridcell'][" + columnNum + "]"));

			for (WebElement status : statuses) {
				String value = status.getText().toString();
				if ((value.equals("In Progress"))) {
					Thread.sleep(100);
					new WebDriverWait(driver, 10).until(ExpectedConditions
							.elementToBeClickable(status.findElement(By.xpath("//div[@role='gridcell'][@col-id='component.authorizedHours']"))));
					String Hrs = Authorizedhours.getText().toString();
					Thread.sleep(100);
					CompletedHours.sendKeys("0"+ Hrs);
					MetPlan.click();
					WebDriverWait wait = new WebDriverWait(driver, 20);
					wait.until(ExpectedConditions.elementToBeClickable(MetPlanDD));
					Select metpaln = new Select(MetPlanDD);
					metpaln.selectByVisibleText("Yes");
				}
			}
			save.click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(simpleNotification.findElement(By.xpath("//*"))));
			System.out.println(simpleNotification.getText().toString());
			String message = simpleNotification.getText().toString();
			new WebDriverWait(driver, 20).until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
			if(message.contains("Track Component\nRecord added successfully.")) {
				return true;
			}else {
				return false;
			}
			
		}
		return false;
	}
	
	public NonCompliancePage getNonComplaincePage() throws InterruptedException {
		Thread.sleep(5000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(nonComplaince));
		nonComplaince.click();
		Thread.sleep(500);
		return new NonCompliancePage(driver);
	}
	
}
