package com.snapworks.pages;

import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
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

public class AdminAnnouncementSummary extends LoadableComponent<AdminAnnouncementSummary>{
	private WebDriver driver;
	
	@FindBy(xpath = "//span[@ref='lbRecordCount']")
	private WebElement recordCount;
	
	@FindBy(xpath = "//button[contains(text(),'Next')]")
	private WebElement nextbtn;
	
	@FindBy(xpath = "//span[contains(text(),'ADD NEW')]")
	private WebElement addNew;
	
	@FindBy(xpath = "//span[contains(text(),'Application Configuration')]")
	private WebElement ApplicationConfiguration;
	
	@FindBy(xpath = "//span[contains(text(),'Access Control')]")
	private WebElement Accesscontol;
	
	@FindBy(xpath = "//span[contains(text(),'Provider Management')]")
	private WebElement ProviderManagement;
	
	
	public AdminAnnouncementSummary(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Announcement Summary')]")).isDisplayed());
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
	}
	
	public AdminAnnouncementDetails getAnnouncemntDetailspage() throws InterruptedException
	{
	new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(addNew));
	Thread.sleep(1000);
	addNew.click();
	return new AdminAnnouncementDetails(driver);
	}
	
	public int getColumnNum(String columnName) {
		// new WebDriverWait(driver,
		// 6).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='ag-header-row']"))));
		int colNum = driver
				.findElements(By.xpath("//div[@class='ag-header-row']//span[contains(text(),'" + columnName
						+ "')]/ancestor::div[@class='ag-header-cell ag-header-cell-sortable']/preceding-sibling::div"))
				.size() + 1;
		return colNum;

	}
	public boolean getAdminAnnouncementsSummary(String columnName, String title) throws InterruptedException {
		Thread.sleep(1000);
		String value1="";
		int rcCount= Integer.parseInt(recordCount.getText().toString());
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//button[@ref='btNext']")));
		if (columnName.equals("Title")) {
			Thread.sleep(1000);
			int columnNum = getColumnNum(columnName);
			List<WebElement> statuses1 = driver
					.findElements(By.xpath("//div[@role='row']//div[@role='gridcell'][" + columnNum + "]"));
			
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
							value1 = status1.getText().toString();
							if ((value1.equals(title))) {
								Thread.sleep(100);
								/*new WebDriverWait(driver, 10).until(ExpectedConditions
										.elementToBeClickable(status1.findElement(By.xpath("./..//a/i[@class='fa fa-pencil']"))));
								((JavascriptExecutor) driver).executeScript("arguments[0].click();",
										status1.findElement(By.xpath("./..//a/i[@class='fa fa-pencil']")));
								*/
								System.out.println("Annoncement record updated sucessfully in Announcement Summary table");
								return true;
							}else{
								System.out.println("Announcement record is not updated in Announcement Summary table");
							}
						}	
				}
				else {
					List<WebElement> statuses = driver
							.findElements(By.xpath("//div[@role='row']//div[@role='gridcell'][" + columnNum + "]"));
					for (WebElement status : statuses) {
						value1 = status.getText().toString();
						if ((value1.equals(title))) {
							Thread.sleep(100);
							/*new WebDriverWait(driver, 10).until(ExpectedConditions
									.elementToBeClickable(status.findElement(By.xpath("./..//a/i[@class='fa fa-pencil']"))));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();",
									status.findElement(By.xpath("./..//a/i[@class='fa fa-pencil']")));*/
							System.out.println("Annoncement record updated sucessfully in Announcement Summary table");
						return true;
						}else{
							System.out.println("Announcement record is not updated in Announcement Summary table");
						}
					}
				}
			}
			
		}
		return false;
	}
	public ApplicationConfigurationPage getAppConfig() throws InterruptedException
	{
	new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(ApplicationConfiguration));
	Thread.sleep(1000);
	ApplicationConfiguration.click();
	return new ApplicationConfigurationPage(driver);
	} 
	
	public AcessContolPage getAccessContol() throws InterruptedException
	{
	new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(Accesscontol));
	Thread.sleep(1000);
	Accesscontol.click();
	return new AcessContolPage(driver);
	} 
	
	public ProviderManagementPage getProviderManagementPage() throws InterruptedException
	{
	new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(ProviderManagement));
	Thread.sleep(1000);
	ProviderManagement.click();
	return new ProviderManagementPage(driver);
	} 
	
	
}
