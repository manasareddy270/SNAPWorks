package com.snapworks.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class CorrespondencePage extends LoadableComponent<CorrespondencePage> {
	private WebDriver driver;

	public CorrespondencePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Correspondence')]")).getText().toString()
				.contains("Correspondence"));

	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

}
