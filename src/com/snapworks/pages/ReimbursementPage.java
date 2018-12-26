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

public class ReimbursementPage extends LoadableComponent<ReimbursementPage> {

	private WebDriver driver;

	// span[contains(text(),'ADD NEW')]

	@FindBy(xpath = "//span[contains(text(),'ADD NEW')]")
	private WebElement addNew;

	@FindBy(xpath = "//span[@ref='lbRecordCount']")
	private WebElement pageCount;

	@FindBy(xpath = "//div[@col-id='vendorName']//span[@class='ag-icon ag-icon-menu']")
	private WebElement VendorheaderMenu;

	@FindBy(xpath = "//select[@class='ag-filter-select']")
	private WebElement filterType;

	@FindBy(xpath = "//input[@class='ag-filter-filter']")
	private WebElement filterValue;
	
	@FindBy(xpath = "//div[@class='client-info ng-star-inserted']//span[@class='info'][1]")
	private WebElement clientName;
	
	@FindBy(xpath = "//span[contains(text(),'Welcome ')]")
	private WebElement welcomeProfile;

	@FindBy(xpath = "//span[contains(text(),'LOGOUT')]")
	private WebElement logout;
	
	@FindBy(xpath = "//./..//a/i[@class='fa fa-pencil']")
	private WebElement editBtn;
	
	@FindBy(xpath = "//./..//a/i[@class='fa fa-eye']")
	private WebElement viewIcon;

	public ReimbursementPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Reimbursements Summary')]")).isDisplayed());
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
	}

	public ReimbursementsDetailsPage getReimbursementsDetails(String user) throws InterruptedException {
		// C:\Users\mpothula\eclipse-workspace\Selenium\SNAP Works\Uploads\Capture3.PNG
		if(user.equals("Provider"))	{
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(addNew));
			Thread.sleep(1000);
			addNew.click();
		}
		
		return new ReimbursementsDetailsPage(driver);
	}

	public boolean verifyReimbursementRecord(String vendorName, String user) throws InterruptedException {
		int pageCnt = Integer.parseInt(pageCount.getText());
		if (pageCnt > 1) {
			String selectedOption = "Equals";
			VendorheaderMenu.click();
			Select option1 = new Select(filterType);
			option1.selectByVisibleText(selectedOption);
			filterValue.sendKeys(vendorName);
			Thread.sleep(1000);
			pageCnt = Integer.parseInt(pageCount.getText());
			if (pageCnt > 1) {
				System.out.println("Duplicate records are present for" + vendorName);
				return false;
			} else {
				System.out.println("Record successfully updated with" + vendorName);
				switch(user) {
				case "Admin":
					Thread.sleep(1400);
					editBtn.click();
					break;
				case "Provider1":
					Thread.sleep(1000);
					viewIcon.click();
					break;
				}
				
				
				return true;
			}

		} else {
			System.out.println("Record updated successfully with" + vendorName);
			switch(user) {
			case "Admin":
				editBtn.click();
				break;
			case "Provider1":
				viewIcon.click();
				break;
			}
			return true;
		}
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
	

	public LoginPage logOut() throws InterruptedException {
		Thread.sleep(7000);
		new WebDriverWait(driver, 100).until(ExpectedConditions.visibilityOf(welcomeProfile));
		welcomeProfile.click();
		new WebDriverWait(driver, 100).until(ExpectedConditions.visibilityOf(logout));
		Thread.sleep(2000);
		logout.click();
		return new LoginPage(driver);
	}
}
