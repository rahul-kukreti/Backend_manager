package com.End2End.PagesObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.End2End.Test_Regression.BaseClass;
import com.codoid.products.exception.FilloException;
import com.relevantcodes.extentreports.LogStatus;

public class CoordinateManager_ObjectPage extends BaseClass {
	
	By btn_ok = By.xpath("//button//span[contains(text(),'Ok')]");
	By domain = By.xpath("//berd-c1-edit-domain//mat-expansion-panel-header//mat-panel-title[contains(text(),'UPAL_C1 Possible Ranges-UPAL_GenericRanges')]");
	By add_new_item = By.xpath("//ancestor::mat-expansion-panel//mat-expansion-panel-header//mat-panel-title[contains(text(),'UPAL_C1 Possible Ranges-UPAL_GenericRanges')]//following::button//span[contains(text(),'Add New Item')]");
	By Upal_c1_btn =  By.xpath("//berd-c1-edit-domain//mat-expansion-panel-header//mat-panel-title[contains(text(),'UPAL_C1 Ranges')]");
	
	public void validate_addc1(WebDriver driver,long timed) {
		commFunc.Click_btn(driver, "Yes");
		commFunc.Click_btn(driver, "Yes");
		commFunc.Explicitywait(driver,By.xpath("//berd-c1-generic-question-dialog//h3[contains(text(),'C1 created!')]"));
		if (driver.findElements(By.xpath("//berd-c1-generic-question-dialog//h3[contains(text(),'C1 created!')]")).size() != 0) {

			String str = "C1 varaible created successfully!";
			System.out.println(str);
			logger.log(LogStatus.INFO, str);
			logger.log(LogStatus.PASS, str);
			commFunc.Click(driver, btn_ok);
		} else {
			String s ="Error in creating c1 varaible";
			System.err.println("Error in creating c1 varaible");
			logger.log(LogStatus.FAIL, s);
			commFunc.Click(driver, btn_ok);
			
		}
	}
	
	public void domain_update_details(WebDriver driver) {

		commFunc.Click(driver, domain);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		commFunc.Click(driver, add_new_item);
		WebElement ele = driver.findElement(By.xpath("//mat-label[text()='Value']//ancestor::mat-form-field//following-sibling::input"));

		ele.sendKeys("200");
		commFunc.Click_btn(driver, "Add Row");
		commFunc.Click_btn(driver, "Submit");
		commFunc.Click_btn(driver, "Select Checkboxes");
		commFunc.Click_btn(driver, "Continue Update");
		commFunc.Click_btn(driver, "Yes");
		commFunc.Explicitywait(driver, By.xpath("//berd-confirmation//div//p[contains(text(),' UPAL_C1 has been created and required approval to update record')]"));
		if (driver.findElements(By.xpath("//berd-confirmation//div//p[contains(text(),' UPAL_C1 has been created and required approval to update record')]")).size() != 0) {
			String s = "Upal_C1 sent to approval";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			commFunc.Click(driver, By.xpath("//button//span[contains(text(),'OK')]"));
		}

		else {
			String s ="Error in updating upal_c1";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Click(driver, By.xpath("//berd-confirmation-dialog//mat-icon[contains(text(),'close')]"));
		}
	}
	
	public void validate_updateC1_ticket(WebDriver driver,long timed) throws FilloException, InterruptedException {
		bio.click_module(driver, "coordinate");
		bio.click_module(driver, "coordinate/c1");
		commFunc.Click_btn(driver,"Read");
		String filter = record.getField("Designation");
		commFunc.put_field_data(driver, "Filter", filter);
		commFunc.click_coordinate_icon(driver, "what is the");
		commFunc.click_coordinate_icon(driver, "4 -  numeric");
		commFunc.click_coordinate_icon(driver, "numeric  simple");
		Thread.sleep(2000);
		commFunc.click_coordinate(driver, filter);
		commFunc.scrollIntoElement(driver, By.xpath("//button//span[contains(text(),'Update')]"));
		commFunc.Click_btn(driver, "Update");
		commFunc.Click_btn(driver, "Update Domain");
		commFunc.Click(driver, Upal_c1_btn);
		commFunc.Explicitywait(driver, By.xpath("//berd-c1-edit-domain//mat-expansion-panel-header//mat-panel-title[contains(text(),'UPAL_C1 Ranges')]//following::berd-searchable-table//table//tbody//tr//td[contains(text(),'200')]"));
		if(driver.findElements(By.xpath("//berd-c1-edit-domain//mat-expansion-panel-header//mat-panel-title[contains(text(),'UPAL_C1 Ranges')]//following::berd-searchable-table//table//tbody//tr//td[contains(text(),'200')]")).size()!=0) {
			String s = "Upal_C1 updated via ticket";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			logger.log(LogStatus.PASS, s);
			commFunc.Data_Pass("validate_updateC1_ticket", s, "PASS", "", timed, 1, 15);
			commFunc.Click(driver, By.xpath("//berd-c1-edit-domain//mat-icon[contains(text(),'close')]"));
		} else {
			String s = "user cannot able to update Upal_c1";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Click(driver, By.xpath("//berd-c1-edit-domain//mat-icon[contains(text(),'close')]"));
			commFunc.Data_Pass("validate_updateC1_ticket", s, "FAIL", s, timed, 4, 15);
		}
		}
	
	//--------------------------------------------------------C2 varibale--------------------------------------------------------------
	
	public void validateC2_simple(WebDriver driver,long timed) {
		commFunc.Click_btn(driver, "Yes");
		commFunc.Click_btn(driver, "Yes");
		commFunc.Explicitywait(driver,By.xpath("//berd-c1-generic-question-dialog//h3[contains(text(),'C2 Simple created!')]"));
		if (driver.findElements(By.xpath("//berd-c1-generic-question-dialog//h3[contains(text(),'C2 Simple created!')]")).size() != 0) {
			String str = "C2 simple subject created successfully!";
			System.out.println(str);
			logger.log(LogStatus.INFO, str);
			logger.log(LogStatus.PASS, str);
			commFunc.Click(driver, btn_ok);
			
		} else {
			String s ="Error in creating C2 simple subject";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Click(driver, btn_ok);
		}
	}
	
	//------------------------------------------------------------------C3 varaible------------------------------------------------------------
	public void validate_c3(WebDriver driver,long timed) {
	commFunc.Click_btn(driver, "Yes");
	commFunc.Click_btn(driver, "Yes");
	commFunc.Explicitywait(driver,By.xpath("//berd-c1-generic-question-dialog//h3[contains(text(),'Created and send to approval')]"));
	if (driver.findElements(By.xpath("//berd-c1-generic-question-dialog//h3[contains(text(),'Created and send to approval')]")).size() != 0) {

		String str = "C3 varaible created successfully!";
		System.out.println(str);
		logger.log(LogStatus.INFO, str);
		logger.log(LogStatus.PASS, str);
		commFunc.Click(driver, btn_ok);
		
	} else {
		String s ="Error in creating c3 varaible or sucess message is not displayed!";
		System.err.println(s);
		logger.log(LogStatus.FAIL, s);
		commFunc.Click(driver, btn_ok);
		
	}
}
		
	}
