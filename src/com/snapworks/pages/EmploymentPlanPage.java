package com.snapworks.pages;

import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmploymentPlanPage extends LoadableComponent<EmploymentPlanPage>{
	private WebDriver driver;
	
	@FindBy(xpath="//span[contains(text(),'Employment Plan Summary')]")
	private WebElement EmploymentPlanSummary;
	
	@FindBy(xpath="//span[contains(text(),'ADD NEW')]")
	private WebElement addNew;
	
	@FindBy(xpath="//button[@class='btn  btn-outline-primary']")
	private WebElement addNewButton;
	
	@FindBy(xpath="//div[@ref='eBodyContainer']/div[@row-index='0']//div[@col-id='estimatedCompletionDt']")
	private WebElement latestEmpEndDt;
	
	@FindBy(xpath="//div[@ref='eBodyContainer']/div[@row-index='0']//div[@col-id='empPlanStartDt']")
	private WebElement latestEmpStartDt;
	
	@FindBy(xpath="//span[@ref='lbRecordCount']")
	private WebElement recordCount;
	
	@FindBy(xpath = "//button[contains(text(),'Next')]")
	private WebElement nextbtn;
	
	@FindBy(xpath="//span[contains(text(),'Create Task')]")
	private WebElement createTask;
	
	@FindBy(xpath="//span[contains(text(),'Components')]")
	private WebElement componetsPage;
	
	@FindBy(xpath="//span[contains(text(),' Case Notes')]")
	private WebElement  caseNotes;
	
	@FindBy(xpath="//div[@class='sidebar-header']/a")
	private WebElement backToSearch;
	
	@FindBy(xpath="//div[@class='client-info ng-star-inserted']//span[@class='info'][1]")
	private WebElement clientName;
	
	@FindBy(xpath="//span[contains(text(),'Client Information')]")
	private WebElement ClientInfo;
	

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(addNew));
		assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Employment Plan Summary')]")).getText().toString().contains("Employment Plan Summary"));
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}
	public EmploymentPlanPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this); 
		this.driver=driver;
	}
	
	public List<Object> getTopRecordDates() throws InterruptedException{
		String stDate = "";
		String endDate= "";
		Thread.sleep(2000);
		int recordCt = Integer.parseInt(recordCount.getText());
		if(recordCt>0) {
			stDate= latestEmpStartDt.getText();
			endDate = latestEmpEndDt.getText();
		}
		return Arrays.asList(stDate, endDate) ;
	}
	public boolean getLatestRcdData(String empPlanSummarycolumn) throws InterruptedException {
		Thread.sleep(2000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(addNewButton));
		String button = addNewButton.getAttribute("outerHTML").toString();
		 if(button.contains("disabled" )){
		
			System.out.println("Opened Employment Plan is Present");
			return getEmploymentPlanSummary(empPlanSummarycolumn);
		}
		else{
			Thread.sleep(1000);
			addNew.click();
			Thread.sleep(1000);
			return false;
			}	
	}
	
	public int getColumnNum(String columnName){
//		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='ag-header-row']"))));
		int colNum = driver.findElements(By.xpath("//div[@class='ag-header-row']//span[contains(text(),'"+columnName+"')]/ancestor::div[@class='ag-header-cell ag-header-cell-sortable']/preceding-sibling::div")).size()+1;
		return colNum;
	}
	
	public boolean getEmploymentPlanSummary(String columnName) throws InterruptedException{
		Thread.sleep(2000);
		int count = 0;
		int rcCount= Integer.parseInt(recordCount.getText().toString());
		if (columnName.equals("Status")) {
			int columnNum = getColumnNum(columnName);
			for(int i = 0; i<rcCount; i = i+10) {
				Thread.sleep(1000);
				if(i>=10) {
					Thread.sleep(1000);
					nextbtn.click();
					Thread.sleep(1000);
					List<WebElement> statuses = driver.findElements(By.xpath("//div[@role='row']//div[@role='gridcell'][" + columnNum + "]"));
					for (WebElement status : statuses) {
						String value = status.getText().toString();
						if ((value.equals("Open"))) {
							new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(status.findElement(By.xpath("./..//a/i[@class='fa fa-pencil']"))));
							((JavascriptExecutor)driver).executeScript("arguments[0].click();", status.findElement(By.xpath("./..//a/i[@class='fa fa-pencil']")));
							count++;
							return true;
						}	
					}
				}
				else {
					List<WebElement> statuses = driver.findElements(By.xpath("//div[@role='row']//div[@role='gridcell'][" + columnNum + "]"));
					for (WebElement status : statuses) {
						String value = status.getText().toString();
						if ((value.equals("Open"))) {
							new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(status.findElement(By.xpath("./..//a/i[@class='fa fa-pencil']"))));
							((JavascriptExecutor)driver).executeScript("arguments[0].click();", status.findElement(By.xpath("./..//a/i[@class='fa fa-pencil']")));
							count++;
							return true;
						}	
					}
				}
			}
		}
		return false;
	}
	
	public boolean getEmploymentPlanSummaryCheck(String columnName) throws InterruptedException{
		Thread.sleep(1000);
		int count = 0;
		if (columnName.equals("Status")) {
			int columnNum = getColumnNum(columnName);
			Thread.sleep(100);
			List<WebElement> statuses = driver.findElements(By.xpath("//div[@role='row']//div[@role='gridcell'][" + columnNum + "]"));
			for (WebElement status : statuses) {
				String value = status.getText().toString();
				if ((value.equals("Closed"))) {
					return  true;
				}
			}		
		}
		return false;	
	}
	
	public EmploymentPlanDetailsPage getEmploymentPlanDetailsPage() throws InterruptedException {
		Thread.sleep(1000);
		return new EmploymentPlanDetailsPage(driver);
	}

	public CreateTaskPage getCreateTaskPage() throws InterruptedException {	
		Thread.sleep(1000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(createTask));
		createTask.click();
		return new CreateTaskPage(driver);
	}
	

	public CaseNotesPage getcaseNotesPage() throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(1000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(caseNotes));
		caseNotes.click();
		return new CaseNotesPage(driver);
	}
	
	
	
	public ComponentsSummaryPage getComponentsPage() throws InterruptedException {	
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(componetsPage));
		componetsPage.click();
		Thread.sleep(1000);
		return new ComponentsSummaryPage(driver);
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
    
    public ClientInfoPage getClientInfo() throws InterruptedException {
    	Thread.sleep(1000);
    	ClientInfo.click();
    	return new ClientInfoPage(driver);
    }

}
