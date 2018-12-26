package com.snapworks.pages;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
public class ApplicationConfigurationPage extends LoadableComponent<ApplicationConfigurationPage>{
	private WebDriver driver;

	@FindBy(id = "currentSearch")
	private WebElement search;
	
	@FindBy(xpath = "//a[@tabindex='0']")
	private WebElement searchResults;
	
	@FindBy(xpath = "//span[contains(text(),'ADD NEW')]")
	private WebElement addNew;
	
	@FindBy(xpath = "//span[@ref='lbRecordCount']")
	private WebElement recordCount;
	
	@FindBy(xpath = "//button[contains(text(),'Next')]")
	private WebElement nextbtn;
	
	@FindBy(xpath = "//input[@id='field_description']")
	private WebElement editDescription;
	
	@FindBy(xpath = "//span[contains(text(),' SUBMIT')]")
	private WebElement editSubmitBtn;
	
	@FindBy(xpath = "//div[@row-index='0']//div[@col-id='name']")
	private WebElement firstDescriptionValue;
	
	@FindBy(xpath = "//span[contains(text(),'Provider Management')]")
	private WebElement ProviderManagement;
	
	
	
	public ApplicationConfigurationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Application Configuration')]")).isDisplayed());
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
	}
	public void searchConfig() throws InterruptedException{
		Thread.sleep(1000);
		search.sendKeys("Hour");
		Thread.sleep(1800);
		//searchResults.click();
		Actions actions = new Actions(driver);
		actions.moveToElement(searchResults).click().build().perform();
		
		
		//actions.click().build().perform();
		
	}
	
	public int getColumnNum(String columnName) {
		int colNum = driver
				.findElements(By.xpath("//div[@class='ag-header-row']//span[contains(text(),'" + columnName
						+ "')]/ancestor::div[@class='ag-header-cell ag-header-cell-sortable']/preceding-sibling::div"))
				.size() + 1;
		return colNum;
	}
	
	public String getAdminApplicationCingurationDetails(String columnName, String title, boolean firstTime) throws InterruptedException {
		Thread.sleep(1000);
		String value1="";
		String descriptionText = "";
		int rcCount= Integer.parseInt(recordCount.getText().toString());
		if(firstTime ==true){
			 descriptionText = firstDescriptionValue.getText().toString();
		}
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//button[@ref='btNext']")));
		if (columnName.equals("Code")) {
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
								new WebDriverWait(driver, 10).until(ExpectedConditions
										.elementToBeClickable(status1.findElement(By.xpath("./..//a/i[@class='fa fa-pencil']"))));
								((JavascriptExecutor) driver).executeScript("arguments[0].click();",
										status1.findElement(By.xpath("./..//a/i[@class='fa fa-pencil']")));
								
								System.out.println("Annoncement record updated sucessfully in Announcement Summary table");
								return descriptionText;
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
							new WebDriverWait(driver, 10).until(ExpectedConditions
									.elementToBeClickable(status.findElement(By.xpath("./..//a/i[@class='fa fa-pencil']"))));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();",
									status.findElement(By.xpath("./..//a/i[@class='fa fa-pencil']")));
							System.out.println("Annoncement record updated sucessfully in Announcement Summary table");
							return descriptionText;
						}else{
							System.out.println("Announcement record is not updated in Announcement Summary table");
						}
					}
				}
			}
			
		}
		return descriptionText;
	}
	public void editConfiguration(boolean newValue, String descriptionText) throws InterruptedException{
		driver.switchTo().activeElement();
		
		
		Thread.sleep(800);
		editDescription.clear();
		Random random = new Random(5);
		String id = String.format("%01d", random.nextInt(5));
		if(newValue == true){
			editDescription.sendKeys(String.valueOf(id));
		}else{
			editDescription.sendKeys(descriptionText);
		}
		
		editSubmitBtn.click();;
	}
	public ProviderManagementPage getProviderManagementPage() throws InterruptedException
	{
	new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(ProviderManagement));
	Thread.sleep(1000);
	ProviderManagement.click();
	return new ProviderManagementPage(driver);
	} 
}
