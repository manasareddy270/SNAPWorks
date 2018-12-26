package com.snapworks.pages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AcessContolPage extends LoadableComponent<AcessContolPage> {
	private WebDriver driver;
	@FindBy(xpath = "//span[@ref='lbRecordCount']")
	private WebElement recordCount;
	
	@FindBy(xpath = "//label[@for ='42_Yes']")
	private WebElement AddServiceProviderYes;

	@FindBy(xpath = "//label[@for ='42_No']")
	private WebElement AddServiceProviderNo;

	@FindBy(xpath = "//label[@for ='42_Read']")
	private WebElement AddServiceProviderRead;

	@FindBy(xpath = "//label[@for ='42_write']")
	private WebElement AddServiceProviderWrite;
	
	@FindBy(xpath = "//label[@for ='51_Yes']")
	private WebElement AdminAnnouncementDetailsYes;

	@FindBy(xpath = "//label[@for ='51_No']")
	private WebElement AdminAnnouncementDetailsNo;

	@FindBy(xpath = "//label[@for ='51_Read']")
	private WebElement AdminAnnouncementDetailsRead;

	@FindBy(xpath = "//label[@for ='51_write']")
	private WebElement AdminAnnouncementDetailsWrite;
	
	@FindBy(xpath = "//label[@for ='50_Yes']")
	private WebElement AdminAnnouncementSummaryYes;

	@FindBy(xpath = "//label[@for ='50_No']")
	private WebElement AdminAnnouncementSummaryNo;

	@FindBy(xpath = "//label[@for ='50_Read']")
	private WebElement AdminAnnouncementSummaryRead;

	@FindBy(xpath = "//label[@for ='50_write']")
	private WebElement AdminAnnouncementSummaryWrite;

	@FindBy(xpath = "//label[@for ='48_Yes']")
	private WebElement AnnouncementDetailsYes;

	@FindBy(xpath = "//label[@for ='48_No']")
	private WebElement AnnouncementDetailsNo;

	@FindBy(xpath = "//label[@for ='48_Read']")
	private WebElement AnnouncementDetailsRead;

	@FindBy(xpath = "//label[@for ='48_write']")
	private WebElement AnnouncementDetailsWrite;
	
	@FindBy(xpath = "//label[@for ='47_Yes']")
	private WebElement AnnouncementSummaryYes;

	@FindBy(xpath = "//label[@for ='47_No']")
	private WebElement AnnouncementSummaryNo;

	@FindBy(xpath = "//label[@for ='47_Read']")
	private WebElement AnnouncementSummaryRead;

	@FindBy(xpath = "//label[@for ='47_write']")
	private WebElement AnnouncementSummaryWrite;

	@FindBy(xpath = "//label[@for ='30_Yes']")
	private WebElement AppointmentYes;

	@FindBy(xpath = "//label[@for ='30_No']")
	private WebElement AppointmentNo;

	@FindBy(xpath = "//label[@for ='30_Read']")
	private WebElement AppointmentRead;

	@FindBy(xpath = "//label[@for ='30_write']")
	private WebElement AppointmentWrite;

	@FindBy(xpath = "//label[@for ='31_Yes']")
	private WebElement AppointmentHistoryYes;

	@FindBy(xpath = "//label[@for ='31_No']")
	private WebElement AppointmentHistoryNo;

	@FindBy(xpath = "//label[@for ='31_Read']")
	private WebElement AppointmentHistoryRead;

	@FindBy(xpath = "//label[@for ='31_write']")
	private WebElement AppointmentHistoryWrite;
	
	@FindBy(xpath = "//label[@for ='32_Yes']")
	private WebElement AssessmentSummaryYes;

	@FindBy(xpath = "//label[@for ='32_No']")
	private WebElement AssessmentSummaryNo;

	@FindBy(xpath = "//label[@for ='32_Read']")
	private WebElement AssessmentSummaryRead;

	@FindBy(xpath = "//label[@for ='32_write']")
	private WebElement AssessmentSummaryWrite;
	
	@FindBy(xpath = "//label[@for ='17_Yes']")
	private WebElement AssignProviderCountyYes;

	@FindBy(xpath = "//label[@for ='17_No']")
	private WebElement AssignProviderCountyNo;

	@FindBy(xpath = "//label[@for ='17_Read']")
	private WebElement AssignProviderCountyRead;

	@FindBy(xpath = "//label[@for ='17_write']")
	private WebElement AssignProviderCountyWrite;

	@FindBy(xpath = "//label[@for ='18_Yes']")
	private WebElement AssignProviderOfficeYes;

	@FindBy(xpath = "//label[@for ='18_No']")
	private WebElement AssignProviderOfficeNo;

	@FindBy(xpath = "//label[@for ='18_Read']")
	private WebElement AssignProviderOfficeRead;

	@FindBy(xpath = "//label[@for ='18_write']")
	private WebElement AssignProviderOfficeWrite;
	
	@FindBy(xpath = "//label[@for ='36_Yes']")
	private WebElement BarriersYes;

	@FindBy(xpath = "//label[@for ='36_No']")
	private WebElement BarriersNo;

	@FindBy(xpath = "//label[@for ='36_Read']")
	private WebElement BarriersRead;

	@FindBy(xpath = "//label[@for ='36_write']")
	private WebElement BarriersWrite;
	
	@FindBy(xpath = "//label[@for ='44_Yes']")
	private WebElement CaseNotesDetailsYes;

	@FindBy(xpath = "//label[@for ='44_No']")
	private WebElement CaseNotesDetailsNo;

	@FindBy(xpath = "//label[@for ='44_Read']")
	private WebElement CaseNotesDetailsRead;

	@FindBy(xpath = "//label[@for ='44_write']")
	private WebElement CaseNotesDetailsWrite;

	@FindBy(xpath = "//label[@for ='43_Yes']")
	private WebElement CaseNotesSummaryYes;

	@FindBy(xpath = "//label[@for ='43_No']")
	private WebElement CaseNotesSummaryNo;

	@FindBy(xpath = "//label[@for ='43_Read']")
	private WebElement CaseNotesSummaryRead;

	@FindBy(xpath = "//label[@for ='43_write']")
	private WebElement CaseNotesSummaryWrite;
	
	@FindBy(xpath = "//label[@for ='24_Yes']")
	private WebElement ClientSummaryYes;

	@FindBy(xpath = "//label[@for ='24_No']")
	private WebElement ClientSummaryNo;

	@FindBy(xpath = "//label[@for ='24_Read']")
	private WebElement ClientSummaryRead;

	@FindBy(xpath = "//label[@for ='24_write']")
	private WebElement ClientSummaryWrite;
	
	@FindBy(xpath = "//label[@for ='41_Yes']")
	private WebElement ComponentDetailsYes;

	@FindBy(xpath = "//label[@for ='41_No']")
	private WebElement ComponentDetailsNo;

	@FindBy(xpath = "//label[@for ='41_Read']")
	private WebElement ComponentDetailsRead;

	@FindBy(xpath = "//label[@for ='41_write']")
	private WebElement ComponentDetailsWrite;
	
	@FindBy(xpath = "//label[@for ='38_Yes']")
	private WebElement ComponentSummarysYes;

	@FindBy(xpath = "//label[@for ='38_No']")
	private WebElement ComponentSummarysNo;

	@FindBy(xpath = "//label[@for ='38_Read']")
	private WebElement ComponentSummarysRead;

	@FindBy(xpath = "//label[@for ='38_write']")
	private WebElement ComponentSummarysWrite;

	@FindBy(xpath = "//label[@for ='11_Yes']")
	private WebElement ContactDetailsYes;

	@FindBy(xpath = "//label[@for ='11_No']")
	private WebElement ContactDetailsNo;

	@FindBy(xpath = "//label[@for ='11_Read']")
	private WebElement ContactDetailsRead;

	@FindBy(xpath = "//label[@for ='11_write']")
	private WebElement ContactDetailsWrite;

	@FindBy(xpath = "//label[@for ='29_Yes']")
	private WebElement ContactSummaryYes;

	@FindBy(xpath = "//label[@for ='29_No']")
	private WebElement ContactSummaryNo;

	@FindBy(xpath = "//label[@for ='29_Read']")
	private WebElement ContactSummaryRead;

	@FindBy(xpath = "//label[@for ='29_write']")
	private WebElement ContactSummaryWrite;
	
	@FindBy(xpath = "//label[@for ='45_Yes']")
	private WebElement CreateTaskYes;

	@FindBy(xpath = "//label[@for ='45_No']")
	private WebElement CreateTaskNo;

	@FindBy(xpath = "//label[@for ='45_Read']")
	private WebElement CreateTaskRead;

	@FindBy(xpath = "//label[@for ='45_write']")
	private WebElement CreateTaskWrite;

	@FindBy(xpath = "//label[@for ='33_Yes']")
	private WebElement EducationCertificationsYes;

	@FindBy(xpath = "//label[@for ='33_No']")
	private WebElement EducationCertificationsNo;

	@FindBy(xpath = "//label[@for ='33_Read']")
	private WebElement EducationCertificationsRead;

	@FindBy(xpath = "//label[@for ='33_write']")
	private WebElement EducationCertificationsWrite;
	
	@FindBy(xpath = "//label[@for ='27_Yes']")
	private WebElement EligibilitySummaryYes;

	@FindBy(xpath = "//label[@for ='27_No']")
	private WebElement EligibilitySummaryNo;

	@FindBy(xpath = "//label[@for ='27_Read']")
	private WebElement EligibilitySummaryRead;

	@FindBy(xpath = "//label[@for ='27_write']")
	private WebElement EligibilitySummaryWrite;
	
	@FindBy(xpath = "//label[@for ='20_Yes']")
	private WebElement EmploymentDetailsYes;

	@FindBy(xpath = "//label[@for ='20_No']")
	private WebElement EmploymentDetailsNo;

	@FindBy(xpath = "//label[@for ='20_Read']")
	private WebElement EmploymentDetailsRead;

	@FindBy(xpath = "//label[@for ='20_write']")
	private WebElement EmploymentDetailsWrite;

	@FindBy(xpath = "//label[@for ='19_Yes']")
	private WebElement EmploymentPlanYes;

	@FindBy(xpath = "//label[@for ='19_No']")
	private WebElement EmploymentPlanNo;

	@FindBy(xpath = "//label[@for ='19_Read']")
	private WebElement EmploymentPlanRead;

	@FindBy(xpath = "//label[@for ='19_write']")
	private WebElement EmploymentPlanWrite;

	@FindBy(xpath = "//label[@for ='26_Yes']")
	private WebElement EmploymentSummaryYes;

	@FindBy(xpath = "//label[@for ='26_No']")
	private WebElement EmploymentSummaryNo;

	@FindBy(xpath = "//label[@for ='26_Read']")
	private WebElement EmploymentSummaryRead;

	@FindBy(xpath = "//label[@for ='26_write']")
	private WebElement EmploymentSummaryWrite;
	
	@FindBy(xpath = "//label[@for ='28_Yes']")
	private WebElement EnrollmentSummaryYes;

	@FindBy(xpath = "//label[@for ='28_No']")
	private WebElement EnrollmentSummaryNo;

	@FindBy(xpath = "//label[@for ='28_Read']")
	private WebElement EnrollmentSummaryRead;

	@FindBy(xpath = "//label[@for ='28_write']")
	private WebElement EnrollmentSummaryWrite;

	@FindBy(xpath = "//label[@for ='56_Yes']")
	private WebElement ExecutiveDashboardYes;

	@FindBy(xpath = "//label[@for ='56_No']")
	private WebElement ExecutiveDashboardNo;

	@FindBy(xpath = "//label[@for ='56_Read']")
	private WebElement ExecutiveDashboardRead;

	@FindBy(xpath = "//label[@for ='56_write']")
	private WebElement ExecutiveDashboardWrite;
	
	@FindBy(xpath = "//label[@for ='25_Yes']")
	private WebElement HouseholdSummaryYes;

	@FindBy(xpath = "//label[@for ='25_No']")
	private WebElement HouseholdSummaryNo;

	@FindBy(xpath = "//label[@for ='25_Read']")
	private WebElement HouseholdSummaryRead;

	@FindBy(xpath = "//label[@for ='25_write']")
	private WebElement HouseholdSummaryWrite;
	
	@FindBy(xpath = "//label[@for ='13_Yes']")
	private WebElement NonComplianceDetailsYes;

	@FindBy(xpath = "//label[@for ='13_No']")
	private WebElement NonComplianceDetailsNo;

	@FindBy(xpath = "//label[@for ='13_Read']")
	private WebElement NonComplianceDetailsRead;

	@FindBy(xpath = "//label[@for ='13_write']")
	private WebElement NonComplianceDetailsWrite;

	@FindBy(xpath = "//label[@for ='12_Yes']")
	private WebElement NonComplianceSummaryYes;

	@FindBy(xpath = "//label[@for ='12_No']")
	private WebElement NonComplianceSummaryNo;

	@FindBy(xpath = "//label[@for ='12_Read']")
	private WebElement NonComplianceSummaryRead;

	@FindBy(xpath = "//label[@for ='12_write']")
	private WebElement NonComplianceSummaryWrite;
	
	@FindBy(xpath = "//label[@for ='57_Yes']")
	private WebElement OperationsDashboardYes;

	@FindBy(xpath = "//label[@for ='57_No']")
	private WebElement OperationsDashboardNo;

	@FindBy(xpath = "//label[@for ='57_Read']")
	private WebElement OperationsDashboardRead;

	@FindBy(xpath = "//label[@for ='57_write']")
	private WebElement OperationsDashboardWrite;
	
	@FindBy(xpath = "//label[@for ='40_Yes']")
	private WebElement OverallProgressYes;

	@FindBy(xpath = "//label[@for ='40_No']")
	private WebElement OverallProgressNo;

	@FindBy(xpath = "//label[@for ='40_Read']")
	private WebElement OverallProgressRead;

	@FindBy(xpath = "//label[@for ='40_write']")
	private WebElement OverallProgressWrite;
	
	@FindBy(xpath = "//label[@for ='16_Yes']")
	private WebElement ProviderDetailsYes;

	@FindBy(xpath = "//label[@for ='16_No']")
	private WebElement ProviderDetailsNo;

	@FindBy(xpath = "//label[@for ='16_Read']")
	private WebElement ProviderDetailsRead;

	@FindBy(xpath = "//label[@for ='16_write']")
	private WebElement ProviderDetailsWrite;
	
	@FindBy(xpath = "//label[@for ='15_Yes']")
	private WebElement ProviderSummaryYes;

	@FindBy(xpath = "//label[@for ='15_No']")
	private WebElement ProviderSummaryNo;

	@FindBy(xpath = "//label[@for ='15_Read']")
	private WebElement ProviderSummaryRead;

	@FindBy(xpath = "//label[@for ='15_write']")
	private WebElement ProviderSummaryWrite;
	
	@FindBy(xpath = "//label[@for ='22_Yes']")
	private WebElement ReimbursementsDetailsYes;

	@FindBy(xpath = "//label[@for ='22_No']")
	private WebElement ReimbursementsDetailsNo;

	@FindBy(xpath = "//label[@for ='22_Read']")
	private WebElement ReimbursementsDetailsRead;

	@FindBy(xpath = "//label[@for ='22_write']")
	private WebElement ReimbursementsDetailsWrite;
	
	@FindBy(xpath = "//label[@for ='21_Yes']")
	private WebElement ReimbursementsSummaryYes;

	@FindBy(xpath = "//label[@for ='21_No']")
	private WebElement ReimbursementsSummaryNo;

	@FindBy(xpath = "//label[@for ='21_Read']")
	private WebElement ReimbursementsSummaryRead;

	@FindBy(xpath = "//label[@for ='21_write']")
	private WebElement ReimbursementsSummaryWrite;

	@FindBy(xpath = "//label[@for ='34_Yes']")
	private WebElement SkillsStrengthsYes;

	@FindBy(xpath = "//label[@for ='34_No']")
	private WebElement SkillsStrengthsNo;

	@FindBy(xpath = "//label[@for ='34_Read']")
	private WebElement SkillsStrengthsRead;

	@FindBy(xpath = "//label[@for ='34_write']")
	private WebElement SkillsStrengthsWrite;

	@FindBy(xpath = "//label[@for ='54_Yes']")
	private WebElement StaffManagementDetailsYes;

	@FindBy(xpath = "//label[@for ='54_No']")
	private WebElement StaffManagementDetailsNo;

	@FindBy(xpath = "//label[@for ='54_Read']")
	private WebElement StaffManagementDetailsRead;

	@FindBy(xpath = "//label[@for ='54_write']")
	private WebElement StaffManagementDetailsWrite;

	@FindBy(xpath = "//label[@for ='53_Yes']")
	private WebElement StaffManagementSummaryYes;

	@FindBy(xpath = "//label[@for ='53_No']")
	private WebElement StaffManagementSummaryNo;

	@FindBy(xpath = "//label[@for ='53_Read']")
	private WebElement StaffManagementSummaryRead;

	@FindBy(xpath = "//label[@for ='53_write']")
	private WebElement StaffManagementSummaryWrite;
	
	@FindBy(xpath = "//label[@for ='35_Yes']")
	private WebElement TestsScoresYes;

	@FindBy(xpath = "//label[@for ='35_No']")
	private WebElement TestsScoresNo;

	@FindBy(xpath = "//label[@for ='35_Read']")
	private WebElement TestsScoresRead;

	@FindBy(xpath = "//label[@for ='35_write']")
	private WebElement TestsScoresWrite;
	
	@FindBy(xpath = "//label[@for ='39_Yes']")
	private WebElement TrackComponentYes;

	@FindBy(xpath = "//label[@for ='39_No']")
	private WebElement TrackComponentNo;

	@FindBy(xpath = "//label[@for ='39_Read']")
	private WebElement TrackComponentRead;

	@FindBy(xpath = "//label[@for ='39_write']")
	private WebElement TrackComponentWrite;

	@FindBy(xpath = "//label[@for ='58_Yes']")
	private WebElement ViewReportsYes;

	@FindBy(xpath = "//label[@for ='58_No']")
	private WebElement ViewReportsNo;

	@FindBy(xpath = "//label[@for ='58_Read']")
	private WebElement ViewReportsRead;

	@FindBy(xpath = "//label[@for ='58_write']")
	private WebElement ViewReportsWrite;
	
	@FindBy(xpath = "//label[@for ='37_Yes']")
	private WebElement WorkHistoryYes;

	@FindBy(xpath = "//label[@for ='37_No']")
	private WebElement WorkHistoryNo;

	@FindBy(xpath = "//label[@for ='37_Read']")
	private WebElement WorkHistoryRead;

	@FindBy(xpath = "//label[@for ='37_write']")
	private WebElement WorkHistoryWrite;
	
	@FindBy(xpath = "//span[contains(text(),'SAVE')]")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//span[contains(text(),'RESET')]")
	private WebElement resetBtn;
	

	public AcessContolPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertTrue(driver.findElement(By.xpath("//ha[contains(text(),'Access Control Summary')]")).isDisplayed());
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
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
	public boolean getAccessControlDetails(String columnName, String title) throws InterruptedException {
		Thread.sleep(1000);
		String value1="";
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//button[@ref='btNext']")));
		if (columnName.equals("Role")) {
			Thread.sleep(1000);
			int columnNum = getColumnNum(columnName);
			Thread.sleep(1000);
					List<WebElement> statuses = driver
							.findElements(By.xpath("//div[@role='row']//div[@role='gridcell'][" + columnNum + "]"));
					for (WebElement status : statuses) {
						value1 = status.getText().toString();
						if ((value1.equals(title))) {
							Thread.sleep(100);
							new WebDriverWait(driver, 10).until(ExpectedConditions
									.elementToBeClickable(status.findElement(By.xpath("./..//a/i[@class='fa fa-pencil']"))));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();",
									status.findElement(By.xpath("./..//a/i[@class='fa fa-pencil']")));
							
							return true;
						}else{
							System.out.println("County Worker edit button is not working");
						}
					}
				}
		return false;
		}
	public void configureCountyWorker(){
		/*List<WebElement> listyes = driver.findElements(By.xpath("//input[@class='custom-control-input'][@value='Y']"));
		List<WebElement> listNo = driver.findElements(By.xpath("//input[@class='custom-control-input'][@value='N']"));
		for ( WebElement yes : listyes ) {
			String ltYes= listyes.get(0).getAttribute("checked");
			for(WebElement no : listNo){
				String ltNo= listNo.get(0).getAttribute("checked");
	    		if ( ltYes.contains("true") ){
	    			//System.out.println(no);
	    			listNo.get(1).click();
		    		}
	    		else{
					yes.click();
			}*/
	    			List<WebElement> listyes = driver.findElements(By.xpath("//input[@class='custom-control-input'][@value='Y']"));
	    			List<WebElement> listNo = driver.findElements(By.xpath("//input[@class='custom-control-input'][@value='N']"));
	    					for ( WebElement yes : listyes ) {
	    							String ltYes= listyes.get(0).getAttribute("checked");
	    							if ( !ltYes.contains("true") )
	    							{					
	    								yes.click();
	    							}else
	    							{
	    								yes.click();
	    							}
	    					   }
	    						for (WebElement no : listNo){
	    						    String ltNo= listNo.get(0).getAttribute("checked");
	    				    		if ( !ltNo.contains("true") ){
	    				    			System.out.println(no);
	    				    			no.click();
	    					    		}
	    				    		else{
	    								no.click();
	    						        } 
	    						} 
				/*if ( !yes.isSelected() ) {
			        yes.click();
			    }else{
			    	List<WebElement> listNo = driver.findElements(By.xpath("//input[@class='custom-control-input'][@value='N']"));
			    	for(WebElement no : listNo){
			    		if ( !no.isSelected() ){
			    			no.click();
			    		}
			    		String attribute = yes.getAttribute("outerHTML").toString();
			if(attribute.contains("true")){
				List<WebElement> listNo = driver.findElements(By.xpath("//input[@class='custom-control-input'][@value='N']"));
		    	for(WebElement no : listNo){
		    		if ( !no.isSelected() ){
		    			no.click();
			    }*/
			}
}
