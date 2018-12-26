package com.snapworks.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class LoginPage extends LoadableComponent<LoginPage> {
	private WebDriver driver;

	@FindBy(id = "field_uname")
	private WebElement userName;

	@FindBy(id = "field_pword")
	private WebElement password;

	@FindBy(xpath = "//span[contains(text(),'LOG IN')]")
	private WebElement login;

	@FindBy(xpath = "//label[@for='field_rememberMe']")
	private WebElement rememberMe;

	@FindBy(linkText = "Forgot password?")
	private WebElement forgotPassword;

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.getTitle().equals("Worker Portal"));
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
	}

	public LoginPage(WebDriver driver) {

		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public HomePage loginUser(String name, String pwd) throws InterruptedException {
		Thread.sleep(1000);
		userName.sendKeys(name);
		password.sendKeys(pwd);
		login.click();
		Thread.sleep(100);
		return new HomePage(driver);

	}

	public HomePage loginUserWithRememberMe(String name, String pwd) throws InterruptedException {

		// new WebDriverWait(driver,
		// 6).until(ExpectedConditions.visibilityOf(userName));
		// ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
		// userName);
		Thread.sleep(1000);
		userName.sendKeys(name);
		password.sendKeys(pwd);
		rememberMe.click();
		login.click();
		return new HomePage(driver);

	}

	public boolean verifyRememberMeFunctionality(String givenName) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", userName);
		// new WebDriverWait(driver,
		// 6).until(ExpectedConditions.visibilityOf(userName));
		String actualName = userName.getAttribute("outerHTML");
		return actualName.contains(givenName);

	}
	
	
	

}
