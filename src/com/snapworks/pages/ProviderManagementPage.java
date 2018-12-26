package com.snapworks.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProviderManagementPage extends LoadableComponent<ProviderManagementPage>{
	private WebDriver driver;
	
	@FindBy(xpath = "//span[contains(text(),'ADD NEW')]")
	private WebElement addNewBtn;
	

	public ProviderManagementPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Provider Profile Management')]")).isDisplayed());
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
	}
	
	public ProviderDetailsPage getProviderDetails() throws InterruptedException{
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(addNewBtn));
		Thread.sleep(1000);
		addNewBtn.click();
		return new ProviderDetailsPage(driver);
	}
}
