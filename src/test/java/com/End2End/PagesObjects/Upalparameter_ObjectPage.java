package com.End2End.PagesObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.End2End.Test_Regression.BaseClass;
import com.codoid.products.exception.FilloException;
import com.relevantcodes.extentreports.LogStatus;

public class Upalparameter_ObjectPage extends BaseClass {
	
	By btn_ok = By.xpath("//button//span[contains(text(),'OK')]");

	
	public void click_dropdown(WebDriver driver, String name, String value) {
		commFunc.Explicitywait(driver, By.xpath("//mat-select//span[contains(text(),'"+name+"')]"));
		commFunc.Click(driver, By.xpath("//mat-select//span[contains(text(),'"+name+"')]"));
		commFunc.Click(driver, By.xpath("//div[@role='listbox']//child::mat-option//span[contains(text(),'"+value+"')]"));
	}
	
	public void filter(WebDriver driver,String name) {
		commFunc.Explicitywait(driver, By.xpath("//input[@placeholder='Search here']"));
		WebElement ele = driver.findElement(By.xpath("//input[@placeholder='Search here']"));
		ele.sendKeys(name);
	}
	
	public void click_parameter_dropdown(WebDriver driver,String name) {
		commFunc.Explicitywait(driver, By.xpath("//mat-select//span[contains(text(),'"+name+"')]"));
		commFunc.Click(driver, By.xpath("//mat-select//span[contains(text(),'"+name+"')]"));
	}
	
	public void click_coordinate_paramter_icon(WebDriver driver,String icon_name) {
		commFunc.Explicitywait(driver,By.xpath("//mat-tree-node//button//following::mat-option//span[contains(text(),'"+icon_name+"')]//ancestor::mat-tree-node//following::mat-icon[contains(text(),'chevron_right')]"));
		commFunc.Click(driver,By.xpath("//mat-tree-node//button//following::mat-option//span[contains(text(),'"+icon_name+"')]//ancestor::mat-tree-node//following::mat-icon[contains(text(),'chevron_right')]"));
	}

	public void click_coordinate(WebDriver driver,String value) {
		commFunc.Explicitywait(driver,By.xpath("//mat-tree-node//mat-option//following::span[contains(text(),'"+value+"')]"));
		commFunc.Click(driver,By.xpath("//mat-tree-node//mat-option//following::span[contains(text(),'"+value+"')]"));
	}
	
	public void ranges_paramter(WebDriver driver, String value) {
		commFunc.Explicitywait(driver,By.xpath("//mat-form-field//following::mat-option//span[contains(text(),'"+value+"')]"));
		commFunc.Click(driver,By.xpath("//mat-form-field//following::mat-option//span[contains(text(),'"+value+"')]"));
	}
	
	
	//------------------------------------------------------------Upal simple varaible----------------------------------------------------------
	
	public void validate_simple_singular(WebDriver driver,long timed) {
		commFunc.Click_btn(driver, "Yes");
		commFunc.Explicitywait(driver, By.xpath("//berd-confirmation//div/p[contains(text(),'Simple Parameter Created!')]"));
		if(driver.findElements(By.xpath("//berd-confirmation//div/p[contains(text(),'Simple Parameter Created!')]")).size()!=0) {
			String str = "Simple paramter singular created successfully!";
			System.out.println(str);
			logger.log(LogStatus.INFO, str);
			logger.log(LogStatus.PASS, str);
			commFunc.Click(driver, btn_ok);
			
		}
		else {
			String s = "Error in creating simple parameter singular!";
			System.err.println("Error in creating simple parameter singular!");
			logger.log(LogStatus.FAIL, s);
			commFunc.Click(driver, By.xpath("//berd-confirmation-dialog//mat-icon[contains(text(),'close')]"));
			
		}

}
	
	public void validate_update_simple(WebDriver driver) {
		commFunc.Click_btn(driver, "Yes");
		commFunc.Explicitywait(driver, By.xpath("//berd-confirmation//div//p[contains(text(),'UPAL_SimpleParameters has been created and required approval to update record')]"));
		if(driver.findElements(By.xpath("//berd-confirmation//div//p[contains(text(),'UPAL_SimpleParameters has been created and required approval to update record')]")).size()!=0) {
			String str = "Simple paramter sent to approval";
			System.out.println(str);
			logger.log(LogStatus.INFO, str);
			logger.log(LogStatus.PASS, str);
			commFunc.Click(driver, btn_ok);
			
		}
		else {
			String s = "Error in updating simple parameter singular!";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Click(driver, By.xpath("//berd-confirmation-dialog//mat-icon[contains(text(),'close')]"));
			
		}
	}
	
	public void update_simpleParameter_ticket(WebDriver driver, long timed) throws FilloException, InterruptedException {
		bio.click_module(driver, "upal");
		bio.click_module(driver, "upal/parameter-mgt");
		upal.click_dropdown(driver, "SELECT PARAMETER STRUCTURE", "SIMPLE PARAMETER");
		Thread.sleep(5000);
		commFunc.select_menu(driver, "Update");
		String Designation = record.getField("Designation");
		commFunc.put_field_data(driver,"Search", Designation);
		Thread.sleep(3000);
		//commFunc.Click_btn(driver, "Update");
		commFunc.Click(driver, By.xpath("//berd-searchable-table//table//tbody//tr//td[contains(text(),'"+Designation+"')]"));
		if(driver.findElements(By.xpath("//div[contains(text(),'Work is in Progress!')]")).size()!=0) {
			String s = "Simple paramter is not updated successfully via a ticket";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Data_Pass("update_uaplC1_ticket", s, "FAIL", s, timed, 4, 15);
		}
		else {
			String s = "Simple paramter is updated successfully via a ticket";
			System.out.println(s);
			logger.log(LogStatus.PASS, s);
			logger.log(LogStatus.INFO, s);
			commFunc.Data_Pass("update_uaplC1_ticket", s, "PASS", "", timed, 1, 15);
		}
	}
	
	//------------------------------------------------------Multifield paramter---------------------------------------------
	
	public void validate_multifield_singular(WebDriver driver) throws InterruptedException {
		//commFunc.Click(driver, optional);
		commFunc.Click_btn(driver, "SUBMIT");
		commFunc.Click_btn(driver, "Yes");
		commFunc.Explicitywait(driver, By.xpath("//berd-confirmation//div/p[contains(text(),'Multifield Parameter Created!')]"));
		if(driver.findElements(By.xpath("//berd-confirmation//div/p[contains(text(),'Multifield Parameter Created!')]")).size()!=0) {
			String str = "Multifield C2 paramter created successfully!";
			System.out.println(str);
			logger.log(LogStatus.INFO, str);
			logger.log(LogStatus.PASS, str);
			commFunc.Click(driver, btn_ok);
			
		}
		else {
			String s = "Error in creating  multifielf c2 parameter!";
			System.err.println("Error in creating  multifielf c2 parameter!");
			logger.log(LogStatus.FAIL, s);
			commFunc.Click(driver, By.xpath("//berd-confirmation-dialog//mat-icon[contains(text(),'close')]"));
			
		}
}
	
	public void validate_update_multifield(WebDriver driver) {
		commFunc.Click_btn(driver, "Yes");
		commFunc.Explicitywait(driver, By.xpath("//berd-confirmation//div//p[contains(text(),'UPAL_MultifieldParameters has been created and required approval to update record')]"));
		if(driver.findElements(By.xpath("//berd-confirmation//div//p[contains(text(),'UPAL_MultifieldParameters has been created and required approval to update record')]")).size()!=0) {
			String str = "Multifield paramter sent to approval";
			System.out.println(str);
			logger.log(LogStatus.INFO, str);
			logger.log(LogStatus.PASS, str);
			commFunc.Click(driver, btn_ok);
			
		}
		else {
			String s = "Error in updating Multifield parameter singular!";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Click(driver, By.xpath("//berd-confirmation-dialog//mat-icon[contains(text(),'close')]"));
			
		}
	}
	
	
	public void update_multifieldParameter_ticket(WebDriver driver, long timed) throws FilloException, InterruptedException {
		bio.click_module(driver, "upal");
		bio.click_module(driver, "upal/parameter-mgt");
		upal.click_dropdown(driver, "SELECT PARAMETER STRUCTURE", "MULTIFIELD PARAMETER");
		Thread.sleep(5000);
		commFunc.select_menu(driver, "Update");
		String Designation = record.getField("Designation");
		commFunc.put_field_data(driver,"Search", Designation);
		Thread.sleep(3000);
		//commFunc.Click_btn(driver, "Update");
		commFunc.Click(driver, By.xpath("//berd-searchable-table//table//tbody//tr//td[contains(text(),'"+Designation+"')]"));
		if(driver.findElements(By.xpath("//div[contains(text(),'Work is in Progress!')]")).size()!=0) {
			String s = "Multifield paramter is not updated successfully via a ticket";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Data_Pass("update_multifieldParameter_ticket", s, "FAIL", s, timed, 4, 15);
		}
		else {
			String s = "Multifield paramter is updated successfully via a ticket";
			System.out.println(s);
			logger.log(LogStatus.PASS, s);
			logger.log(LogStatus.INFO, s);
			commFunc.Data_Pass("update_multifieldParameter_ticket", s, "PASS", "", timed, 1, 15);
		}
	}
}
