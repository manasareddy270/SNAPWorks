package com.snapworks.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.snapworks.pages.HomePage;
import com.snapworks.pages.LoginPage;
import com.snapworks.pages.ReportPage;

public class ReportsTest {

	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private ReportPage reportPage;

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
		driver.manage().window().maximize();
	}

	// @Test
	public void login() {

		loginPage = new LoginPage(driver);
		// homePage = loginPage.loginUser(name, password);
		// homePage.getReportsPage();

	}

	@Test
	public void report() {
		String name;
		String password;

		loginPage = new LoginPage(driver);
		// homePage = loginPage.loginUser(name, password);
		// homePage.getReportsPage();

	}

	// @After
	public void quitBrowser() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

}
