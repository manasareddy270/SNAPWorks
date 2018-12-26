package com.snapworks.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UniversalMethods {
	private WebDriver driver;
	
	@FindBy(xpath = "//div[@class='client-info ng-star-inserted']//span[@class='info'][1]")
	private WebElement clientName;
	
	@FindBy(xpath = "//span[contains(text(),'Welcome ')]")
	private WebElement welcomeProfile;

	@FindBy(xpath = "//span[contains(text(),'LOGOUT')]")
	private WebElement logout;
	
	public UniversalMethods(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public static List<String> getDate(Boolean IncrementDate, String Date,int value) {
		Date today;
		int dayNum ;
		int day ;
	
		if (Date != "") 
		{
			if (IncrementDate == true) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date(Date));
				switch(value){
				case -1:
					cal.add(Calendar.DAY_OF_MONTH, -1);
					break;
				case 1:
					cal.add(Calendar.DAY_OF_MONTH, 1);
					break;
				}
				
				today = cal.getTime();
			}else{
				today = new Date(Date);
			}
			
		}else {
			if (IncrementDate == true) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				switch(value){
				case -1:
					cal.add(Calendar.DAY_OF_MONTH, -1);
					break;
				case 1:
					cal.add(Calendar.DAY_OF_MONTH, 1);
					break;
				}
				
				today = cal.getTime();
			}else{
				today = new Date();
			}
		}
		
		String todayDate = String.valueOf(today.getDate());
		dayNum = today.getDay();
		String dayText = "";
		switch (dayNum) {
			case 0:
				dayText = "Sunday";
				break;
			case 1:
				dayText = "Monday";
				break;
			case 2:
				dayText = "Tuesday";
				break;
	
			case 3:
				dayText = "Wednesday";
				break;
			case 4:
				dayText = "Thursday";
				break;
			case 5:
				dayText = "Friday";
				break;
			case 6:
				dayText = "Saturday";
				break;
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
			String fullDate = dateFormat.format(today);
			dateFormat = new SimpleDateFormat("MM/YYYY");
			String mnth_yr=dateFormat.format(today);
			List<String> currentOptions = new ArrayList<>();
			currentOptions.add(dayText);
			currentOptions.add(todayDate);
			currentOptions.add(fullDate);
			currentOptions.add(mnth_yr);
			return currentOptions;		
	}
	
	// generate random dates between given date
		public static List<String> generateRandomDate(){
			Date today;
			int dayNum ;
			int day ;
			Date now = new Date();
			
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			Long value1 = cal.getTimeInMillis();
			
			
			cal.setTime(now);
			cal.add(Calendar.DAY_OF_MONTH, -20);
			Long value2 = cal.getTimeInMillis();
			
			long value3 = (long)(value1 + Math.random()*(value2 - value1));
			cal.setTimeInMillis(value3);
			
			 today = cal.getTime();
			String todayDate = String.valueOf(today.getDate());
			dayNum = today.getDay();
			String dayText = "";
			switch (dayNum) {
				case 0:
					dayText = "Sunday";
					break;
				case 1:
					dayText = "Monday";
					break;
				case 2:
					dayText = "Tuesday";
					break;
		
				case 3:
					dayText = "Wednesday";
					break;
				case 4:
					dayText = "Thursday";
					break;
				case 5:
					dayText = "Friday";
					break;
				case 6:
					dayText = "Saturday";
					break;
				}
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
				String fullDate = dateFormat.format(today);
				
				List<String> currentOptions = new ArrayList<>();
				currentOptions.add(dayText);
				currentOptions.add(todayDate);
				currentOptions.add(fullDate);
				return currentOptions;
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
