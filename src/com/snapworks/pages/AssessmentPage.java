package com.snapworks.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AssessmentPage extends LoadableComponent<AssessmentPage> {

	private WebDriver driver;

	@FindBy(xpath = "//span[contains(text(),'Employment Plan')]")
	private WebElement employmentPlan;

	@FindBy(xpath = "//span[contains(text(),'ADD NEW')]")
	private WebElement addNew;

	@FindBy(xpath = "//div[@class='sidebar-header']/a")
	private WebElement backToSearch;

	@FindBy(xpath = "//div[@class='client-info ng-star-inserted']//span[@class='info'][1]")
	private WebElement clientName;
	
	@FindBy(xpath = "//span[@ref='lbRecordCount']")
	private WebElement recordCount;
	
	@FindBy(xpath = "//button[contains(text(),'Next')]")
	private WebElement nextbtn;
	
	
	public AssessmentPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(addNew));
		assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Assessment Summary')]")).getText().toString()
				.contains("Assessment Summary"));

	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public int getColumnNum(String columnName) {
		
		int colNum = driver
				.findElements(By.xpath("//div[@class='ag-header-row']//span[contains(text(),'" + columnName
						+ "')]/ancestor::div[@class='ag-header-cell ag-header-cell-sortable']/preceding-sibling::div"))
				.size() + 1;
		return colNum;

	}

	public boolean getAssessmentSummary(String columnName) throws InterruptedException {
		Thread.sleep(1000);
		int count = 0;
		int rcCount= Integer.parseInt(recordCount.getText().toString());
		/*JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollby(0,250)", "");*/
		
		//((JavascriptExecutor)driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//button[@ref='btNext']")));
		if (columnName.equals("Status")) {
			Thread.sleep(1000);
			int columnNum = getColumnNum(columnName);
			List<WebElement> statuses1 = driver
					.findElements(By.xpath("//div[@role='row']//div[@role='gridcell'][" + columnNum + "]"));
			for (WebElement status1 : statuses1) {
				String value1 = status1.getText().toString();
				if ((value1.equals("Open")) || (value1.equals("In Progress"))||(value1.equals("Completed"))) {
					 columnNum = getColumnNum(columnName);
				}else {
					columnNum = getColumnNum(columnName)+1;
				}
			}
			Thread.sleep(1000);
			for(int i = 0; i<rcCount; i = i+10) {
				Thread.sleep(1000);
				if(i>=10) 
				{
						Thread.sleep(1000);
						nextbtn.click();
						Thread.sleep(1000);
						columnNum = getColumnNum(columnName);
						 statuses1 = driver.findElements(By.xpath("//div[@role='row']//div[@role='gridcell'][" + columnNum + "]"));
						for (WebElement status1 : statuses1) {
							String value1 = status1.getText().toString();
							if ((value1.equals("Open")) || (value1.equals("In Progress"))||(value1.equals("Completed"))) {
								 columnNum = getColumnNum(columnName);
							}else {
								columnNum = getColumnNum(columnName)+1;
							}
						}
						statuses1 = driver.findElements(By.xpath("//div[@role='row']//div[@role='gridcell'][" + columnNum + "]"));
						for (WebElement status1 : statuses1) {
							String value1 = status1.getText().toString();
							if ((value1.equals("Open")) || (value1.equals("In Progress"))) {
								Thread.sleep(100);
								new WebDriverWait(driver, 10).until(ExpectedConditions
										.elementToBeClickable(status1.findElement(By.xpath("./..//a/i[@class='fa fa-pencil']"))));
								((JavascriptExecutor) driver).executeScript("arguments[0].click();",
										status1.findElement(By.xpath("./..//a/i[@class='fa fa-pencil']")));
								count++;
								return true;
							}
						}	
				}
				else {
					List<WebElement> statuses = driver
							.findElements(By.xpath("//div[@role='row']//div[@role='gridcell'][" + columnNum + "]"));
					for (WebElement status : statuses) {
						String value = status.getText().toString();
						if ((value.equals("Open")) || (value.equals("In Progress"))) {
							Thread.sleep(100);
							new WebDriverWait(driver, 10).until(ExpectedConditions
									.elementToBeClickable(status.findElement(By.xpath("./..//a/i[@class='fa fa-pencil']"))));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();",
									status.findElement(By.xpath("./..//a/i[@class='fa fa-pencil']")));
							count++;
							return true;
						}		
					}
				}
			}
			if (count == 0) {
				Thread.sleep(400);
				addNew.click();
				Thread.sleep(100);
				return true;
			}
		}
		return false;
	}

	public EducationCertificationsPage getEducationCertificationsPage() {
		return new EducationCertificationsPage(driver);
	}

	public EmploymentPlanPage getEmploymentPage() throws InterruptedException {
		Thread.sleep(100);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(employmentPlan));
		employmentPlan.click();

		return new EmploymentPlanPage(driver);

	}

	public String getClientName() {
		String ClientName = clientName.getText();
		String[] temp = ClientName.split(", ");
		String result = "";

		// Iterate over the temp array and store
		// the string in reverse order.
		for (int i = 0; i < temp.length; i++) {
			if (i == temp.length - 1)
				result = temp[i] + result;
			else
				result = " " + temp[i] + result;
		}
		return result;
	}

	public SearchPage getSearchPage() throws InterruptedException {
		Thread.sleep(1000);
		backToSearch.click();
		return new SearchPage(driver);
	}

}
