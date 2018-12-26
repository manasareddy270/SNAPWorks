package com.snapworks.tests;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReport {

	public static ExtentReports startReportInstance() {
		ExtentReports extent;
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String Path = "C://Report//LearnAutomation" + timeStamp + ".html";
		extent = new ExtentReports(Path, false);
		extent.addSystemInfo("Environment", "QA");
		// extent.addSystemInfo("reportName","DELOITTE AUTOMATION REPORT");
		// extent.addSystemInfo("documentTitle","SNAP WORKS");
		return extent;
	}

	public static String captureScreenshot(WebDriver driver1, String screenshotName) {

		try {
			TakesScreenshot ts = (TakesScreenshot) driver1;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String dest = "C:\\Report\\" + screenshotName + ".png";
			File destination = new File(dest);
			FileUtils.copyFile(source, destination);
			System.out.println("Screenshot taken");
			return dest;
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot " + e.getMessage());
			return e.getMessage();
		}
	}

}
