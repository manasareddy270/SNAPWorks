package com.snapworks.pages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import javax.swing.Action;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProviderDetailsPage extends LoadableComponent<ProviderDetailsPage> {
	private WebDriver driver;
	
	@FindBy(xpath = "//input[@id='provider_name']")
	private WebElement providerName;
	
	@FindBy(xpath = "//input[@id='contact_firstName']")
	private WebElement contactFName;
	
	@FindBy(xpath = "//input[@id='contact_lastName']")
	private WebElement contactLName;
	
	@FindBy(xpath = "//input[@id='address1']")
	private WebElement address1;
	
	@FindBy(xpath = "//input[@id='address2']")
	private WebElement address2;
	
	@FindBy(xpath = "//input[@id='field_city']")
	private WebElement city;
	
	@FindBy(xpath = "//input[@id='field_state']")
	private WebElement state;
	
	@FindBy(xpath = "//input[@id='field_zipCode']")
	private WebElement zip;
	
	@FindBy(xpath = "//input[@id='field_phnNum']")
	private WebElement phone;
	
	@FindBy(xpath = "//input[@id='field_faxNum']")
	private WebElement fax;
	
	@FindBy(xpath = "//input[@id='field_email']")
	private WebElement email;
	
	@FindBy(xpath = "//input[@id='field_beginDt']")
	private WebElement begindate;
	
	@FindBy(xpath = "//input[@id='field_endDt']")
	private WebElement endDt;
	
	@FindBy(xpath = "//span[contains(text(),'SAVE')]")
	private WebElement save;
	
	@FindBy(xpath = "//a[contains(text(),'Assign Provider County')]")
	private WebElement assignProviderCounty;
	
	@FindBy(xpath = "//span[contains(text(),'ADD NEW')]")
	private WebElement addNewBtn;
	
	@FindBy(xpath = "//select[@class='ag-cell-edit-input']")
	private WebElement countyDD;
	
	@FindBy(xpath = "//div[@role='gridcell'][@col-id='startDate']")
	private WebElement servicebeginDt;
	
	@FindBy(xpath = "//div[@role='gridcell'][@col-id='endDate']")
	private WebElement serviceEndDt;
	
	@FindBy(xpath = "//input[@mask-input='date']")
	private WebElement Date;
	
	@FindBy(xpath = "//div[@class='simple-notification-wrapper top right']")
	private WebElement simpleNotification;
	
	@FindBy(xpath = "//a[contains(text(),'Assign Provider Office')]")
	private WebElement assignProviderOffice;
	
	@FindBy(xpath = "//select[@class='form-control ng-untouched ng-pristine ng-valid']")
	private WebElement selectcounty;
	
	@FindBy(xpath = "//input[@id='office_name']")
	private WebElement OfficeName;
	
	@FindBy(xpath = "//input[@id='contact_firstName']")
	private WebElement OfficeContactFName;
	
	@FindBy(xpath = "//input[@id='contact_lastName']")
	private WebElement OfficeContactLName;
	
	@FindBy(xpath = "//input[@id='address1']")
	private WebElement OfficeAddress1;
	
	@FindBy(xpath = "//input[@id='address2']")
	private WebElement OfficeAddress2;
	
	@FindBy(xpath = "//input[@id='field_city']")
	private WebElement OfficeCity;
	
	@FindBy(xpath = "//input[@id='field_state']")
	private WebElement OfficeState;
	
	@FindBy(xpath = "//input[@id='field_zipCode']")
	private WebElement officeZip;
	
	@FindBy(xpath = "//input[@id='field_phnNum']")
	private WebElement officePhone;
	
	@FindBy(xpath = "//input[@id='field_faxNum']")
	private WebElement officefax;
	
	@FindBy(xpath = "//input[@id='field_email']")
	private WebElement OfficeEmail;
	
	@FindBy(xpath = "//input[@id='field_beginDt']")
	private WebElement officebegindate;
	
	@FindBy(xpath = "//input[@id='field_endDt']")
	private WebElement OfficeEndDt;
	
	@FindBy(xpath = "//span[contains(text(),'Staff Management')]")
	private WebElement StaffManagement;
	
	@FindBy(xpath = "//input[@id='currentSearch']")
	private WebElement StaffSearch;
	
	@FindBy(xpath = "//span[contains(text(),'ADD NEW')]")
	private WebElement addNew;
	
	@FindBy(xpath = "//input[@id='field_firstName']")
	private WebElement FirstName;
	
	@FindBy(xpath = "//input[@id='field_lastName']")
	private WebElement LastName;
	
	@FindBy(xpath = "//input[@id='field_phNum']")
	private WebElement Phone;
	
	@FindBy(xpath = "//input[@id='field_supervisorName']")
	private WebElement SupervisorName;
	
	@FindBy(xpath = "//input[@id='field_email']")
	private WebElement Email;
	
	@FindBy(xpath = "//label[@class='custom-control-label'][@for='field_provider']")
	private WebElement ProviderRadio;
	
	@FindBy(xpath = "//select[@id='field_assignedToP']")
	private WebElement ProviderDD;
	
	@FindBy(xpath = "//span[@class='dropdown-btn']")
	private WebElement MultiCounty;
	
	@FindBy(xpath = "//select[@id='field_office']")
	private WebElement selectOffice;
	
	@FindBy(xpath = "//input[@id='field_beginDate']")
	private WebElement BeginDt;
	
	@FindBy(xpath = "//input[@id='field_endDate']")
	private WebElement EndDt;

	@FindBy(xpath = "//div[@class='dropdown-list'][1]//div[contains(text(),'Arkansas A')]")
	private WebElement arkConty;
	
	@FindBy(xpath = "//button[contains(text(),'SAVE')]")
	private WebElement BtnSave;
	
	@FindBy(xpath = "//span[@ref = 'lbRecordCount']")
	private WebElement pageCount;
	
	public ProviderDetailsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Provider Details')]")).isDisplayed());
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}
	public void enterproviderDetails(String ProviderName, String Fname,String LName, String Address1,String Address2 ,
			String City , String State, String Zip, String Phone, String Fax, String Email) throws InterruptedException{
		providerName.sendKeys(ProviderName);
		contactFName.sendKeys(Fname);
		contactLName.sendKeys(LName);
		address1.sendKeys(Address1);
		address2.sendKeys(Address2);
		city.sendKeys(City);
		state.sendKeys(State);
		zip.sendKeys(Zip);
		phone.sendKeys(Phone);
		fax.sendKeys(Fax);
		email.sendKeys(Email);
		
		begindate.click();
		List<String> OptionAD = UniversalMethods.getDate(false, "",0);
		String ValidfromDate = OptionAD.get(0)+", "+OptionAD.get(2) ;
		String ValidfromDtDay = OptionAD.get(1);
		driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='"+ValidfromDate+"']/div[contains(text(),'"+ValidfromDtDay+"')]")).click();
		
		endDt.click();
		OptionAD = UniversalMethods.getDate(true, "",1);
		String ValidtoDtDate = OptionAD.get(0)+", "+OptionAD.get(2) ;
		String ValidtoDtDay = OptionAD.get(1);
		driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='"+ValidtoDtDate+"']/div[contains(text(),'"+ValidtoDtDay+"')]")).click();
		Thread.sleep(1000);
		save.click();
		System.out.println(simpleNotification.getText().toString());
		String message = simpleNotification.getText().toString();
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		
	}
	
		public void AssignproviderCounty() throws InterruptedException{
			Thread.sleep(1000);
			assignProviderCounty.click();
			addNewBtn.click();
		
			Select county = new Select(countyDD);
			county.selectByIndex(2);
			
			servicebeginDt.click();
			Date.click();
			List<String> OptionAD = UniversalMethods.getDate(false, "",0);
			String ValidfromDate = OptionAD.get(0)+", "+OptionAD.get(2) ;
			String ValidfromDtDay = OptionAD.get(1);
			driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='"+ValidfromDate+"']/div[contains(text(),'"+ValidfromDtDay+"')]")).click();
			
			serviceEndDt.click();
			Date.click();
			OptionAD = UniversalMethods.getDate(true, "",1);
			String ValidtoDtDate = OptionAD.get(0)+", "+OptionAD.get(2) ;
			String ValidtoDtDay = OptionAD.get(1);
			driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='"+ValidtoDtDate+"']/div[contains(text(),'"+ValidtoDtDay+"')]")).click();
			Thread.sleep(2000);
			save.click();
			Thread.sleep(200);
			System.out.println(simpleNotification.getText().toString());
			String message = simpleNotification.getText().toString();
			new WebDriverWait(driver, 10).until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		}
		
		public void AssignProviderOffice() throws InterruptedException{
			assignProviderOffice.click();
			Thread.sleep(1000);
			
			Select county = new Select(selectcounty);
			county.selectByIndex(1);
			Thread.sleep(2000);
			addNewBtn.click();
			Thread.sleep(200);
			System.out.println(simpleNotification.getText().toString());
			String message = simpleNotification.getText().toString();
			new WebDriverWait(driver, 10).until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
		}
		
		public void enterproviderOfficeDetails(String OfficeProviderName, String OfficeFname,String OfficeLName, String OfficeAddressLine1,
				String officeAddressLine2 ,String officeCity , String officeState, String OfficeZip, 
				String OfficePhone, String OfficeFax, String officeEmail) throws InterruptedException{
			OfficeName.sendKeys(OfficeProviderName);
			OfficeContactFName.sendKeys(OfficeFname);
			OfficeContactLName.sendKeys(OfficeLName);
			OfficeAddress1.sendKeys(OfficeAddressLine1);
			OfficeAddress2.sendKeys(officeAddressLine2);
			OfficeCity.sendKeys(officeCity);
			OfficeState.sendKeys(officeState);
			officeZip.sendKeys(OfficeZip);
			officePhone.sendKeys(OfficePhone);
			officefax.sendKeys(OfficeFax);
			OfficeEmail.sendKeys(officeEmail);
			
			officebegindate.click();
			List<String> OptionAD = UniversalMethods.getDate(false, "",0);
			String ValidfromDate = OptionAD.get(0)+", "+OptionAD.get(2) ;
			String ValidfromDtDay = OptionAD.get(1);
			driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='"+ValidfromDate+"']/div[contains(text(),'"+ValidfromDtDay+"')]")).click();
			
			OfficeEndDt.click();
			OptionAD = UniversalMethods.getDate(true, "",1);
			String ValidtoDtDate = OptionAD.get(0)+", "+OptionAD.get(2) ;
			String ValidtoDtDay = OptionAD.get(1);
			driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='"+ValidtoDtDate+"']/div[contains(text(),'"+ValidtoDtDay+"')]")).click();
			Thread.sleep(2000);
			save.click();
			Thread.sleep(200);
			System.out.println(simpleNotification.getText().toString());
			String message = simpleNotification.getText().toString();
			new WebDriverWait(driver, 10).until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
			
		}
		public void getStaffManagemet(String firstName, String lastName, String cPhone, String SName, 
				String Semail) throws InterruptedException{
			Thread.sleep(800);
			StaffManagement.click();
			new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'ADD NEW')]")));
			Thread.sleep(6000);
			addNew.click();
			FirstName.sendKeys(firstName);
			LastName.sendKeys(lastName);
			Phone.sendKeys(cPhone);
			SupervisorName.sendKeys(SName);
			Email.sendKeys(Semail);
			Thread.sleep(800);
			ProviderRadio.click();
			Thread.sleep(1000);
			ProviderRadio.click();
			
			Select provider = new Select(ProviderDD);
			provider.selectByIndex(6);
			
			MultiCounty.click();
			
			Actions actions = new Actions(driver);
			actions.moveToElement(arkConty).clickAndHold();
			actions.click().build().perform();
		
			Select office = new Select(selectOffice);
			office.selectByIndex(1);
			
			BeginDt.click();
			List<String> OptionAD = UniversalMethods.getDate(false, "",0);
			String ValidfromDate = OptionAD.get(0)+", "+OptionAD.get(2) ;
			String ValidfromDtDay = OptionAD.get(1);
			driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='"+ValidfromDate+"']/div[contains(text(),'"+ValidfromDtDay+"')]")).click();
			
			EndDt.click();
			OptionAD = UniversalMethods.getDate(true, "",1);
			String ValidtoDtDate = OptionAD.get(0)+", "+OptionAD.get(2) ;
			String ValidtoDtDay = OptionAD.get(1);
			driver.findElement(By.xpath("//ngb-datepicker-month-view//div[@aria-label='"+ValidtoDtDate+"']/div[contains(text(),'"+ValidtoDtDay+"')]")).click();
			Thread.sleep(1000);
			BtnSave.click();
			Thread.sleep(200);
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
			//new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(simpleNotification));
			System.out.println(simpleNotification.getText().toString());
			String message = simpleNotification.getText().toString();
			new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='simple-notification-wrapper top right']//*")));
			
		}
		public boolean searchStaff(String Semail) throws InterruptedException{
			Thread.sleep(1000);
			StaffSearch.sendKeys(Semail);
			Thread.sleep(1000);
			int totalPageCount = Integer.parseInt(pageCount.getText());
			if (totalPageCount == 1) {
				System.out.println("Case Notes added successfully");
				return true;
			} else {
				System.out.println("Duplicate records are present");
				return false;
			}
		}

}
