package com.snapworks.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClientSearchPage extends LoadableComponent<ClientSearchPage>{
	private WebDriver driver;
	
	@FindBy(xpath = "//label[@for='individual']")
	private WebElement ClientRadioBtn;
	
	@FindBy(xpath = "//input[@id='currentSearch']")
	private WebElement search;
	
	@FindBy(xpath = "//a[@class='list-group-item list-group-item-action'][1]")
	private WebElement firstSearchResult;
	
	@FindBy(xpath = "//div[@col-id='personId']/a")
	private WebElement clientIdLink;
	
	@FindBy(xpath = "//div[@role='alert']")
	private WebElement alert;
	
	//div[@role='alert']
	public ClientSearchPage(WebDriver driver) {
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
		
	}
	public boolean clientSearch(String ClientName) throws InterruptedException {
		Thread.sleep(300);
		ClientRadioBtn.click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(search));
		search.sendKeys(ClientName);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		if(driver.findElements( By.xpath("//a[@class='list-group-item list-group-item-action'][1]") ).size() != 0) {
			firstSearchResult.click();
			
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			
			if(driver.findElements( By.xpath("//div[@col-id='personId']/a") ).size() != 0) {
				clientIdLink.click();

			}
			else {
				System.out.println(alert.getText().toString());
				return false;
			}
		}else {
			System.out.println(alert.getText().toString());
			return false;
		}
		
		return true;
		
	}
	public ClientInfoPage getClientInfoPage() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return new ClientInfoPage(driver);
	}
	 
}
