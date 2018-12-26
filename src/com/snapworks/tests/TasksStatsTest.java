package com.snapworks.tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.snapworks.pages.HomePage;
import com.snapworks.pages.LoginPage;
import com.snapworks.pages.SearchPage;

public class TasksStatsTest {

	private static final String priority = null;
	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchPage searchPage;

	String applicationURL;

	String name;
	String password;

	@BeforeMethod
	@Parameters({ "Browser", "name", "password", "url" })

	public void launchBrowser(String BrowserName, String name, String password, String url) {
		this.name = name;
		this.password = password;
		this.applicationURL = url;
		String userdirectory = System.getProperty("user.dir");
		String chromedriverpath = userdirectory + "\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromedriverpath);
		driver = new ChromeDriver();
		driver.get(applicationURL);
		driver.manage().window().maximize(); // will maximize the browser
	}

	@Test(priority = 1)
	public void verifyDueToday() throws InterruptedException {
		loginPage = new LoginPage(driver);
		// Calling loginUser Method to validate login UserName and Password
		// Navigate from login screen to Home screen after login success
		homePage = loginPage.loginUser(name, password);
		assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");
		String expected = "DueToday-Pass:::From Date or To Date or Task Status are selected input as expected";
		Assert.assertEquals(homePage.validateDueTodyLink(), expected);

	}

	@Test(priority = 2)
	public void verifyUpcoming() throws InterruptedException {
		loginPage = new LoginPage(driver);
		// Calling loginUser Method to validate login UserName and Password
		// Navigate from login screen to Home screen after login success
		homePage = loginPage.loginUser(name, password);
		assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");

		String expected = "UpComing-Pass:::From Date or To Date or Task Status are selected input as expected";
		Assert.assertEquals(homePage.validateUpcomingLink(), expected);
	}

	@Test(priority = 3)
	public void verifyOverdue() throws InterruptedException {
		loginPage = new LoginPage(driver);
		// Calling loginUser Method to validate login UserName and Password
		// Navigate from login screen to Home screen after login success
		homePage = loginPage.loginUser(name, password);
		assertTrue(homePage.getWelcomeProfile().isDisplayed(), "Login Failed");

		String expected = "Overdue-Pass:::From Date or To Date or Task Status are selected input as expected";
		Assert.assertEquals(homePage.validateOverdueLink(), expected);
	}

	@AfterMethod
	public void quitBrowser() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

}
