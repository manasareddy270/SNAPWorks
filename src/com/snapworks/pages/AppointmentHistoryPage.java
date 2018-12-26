package com.snapworks.pages;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppointmentHistoryPage extends LoadableComponent<AppointmentPage> {
	//
	private WebDriver driver;

	@FindBy(id = "field_appointmentDate")
	private WebElement appointmentDate;

	@FindBy(xpath = "//div[@col-id='appointmentTime']//span[@class='ag-icon ag-icon-menu']")
	private WebElement headerMenu;

	@FindBy(xpath = "//select[@class='ag-filter-select']")
	private WebElement filterType;

	@FindBy(xpath = "//input[@class='ag-filter-filter']")
	private WebElement filterValue;

	@FindBy(xpath = "//span[@ref='lbRecordCount']")
	private WebElement noRecords;

	@FindBy(xpath = "//div[@class='sidebar-header']/a")
	private WebElement backToSearch;

	@FindBy(xpath = "//div[@class='client-info ng-star-inserted']//span[@class='info'][1]")
	private WebElement clientName;

	public AppointmentHistoryPage(WebDriver driver) {
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
		assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Appointment History')]")).getText().toString()
				.contains("Appointment History"));
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

	public boolean verifyappCreated(String timehr, String timemin, String mer) throws InterruptedException {
		Thread.sleep(1000);
		int pageCount = Integer.parseInt(noRecords.getText());
		boolean rtnvalue = false;
		if (pageCount > 0) {
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(noRecords));
			String selectedOption = "Equals";
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
			String date = sdf.format(now);
			String apDt = date + " " + timehr + ":" + timemin + " " + mer;
			headerMenu.click();
			Select option1 = new Select(filterType);
			option1.selectByVisibleText(selectedOption);
			filterValue.sendKeys(date);
			Thread.sleep(1000);
			pageCount = Integer.parseInt(noRecords.getText());
			if (pageCount > 1) {
				System.out.println("Appointment scheduled with duplicates ");
				rtnvalue = true;
			} else {
				System.out.println("Appointment scheduled successfully");
				rtnvalue = true;
			}
		} else {
			System.out.println("Appointment scheduled not successfully");
		}
		return rtnvalue;
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
