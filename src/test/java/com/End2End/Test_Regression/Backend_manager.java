package com.End2End.Test_Regression;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;

public class Backend_manager extends BaseClass {

	@Test(testName = "Create lexicon", priority = 1, enabled = true)
	public void create_lexicon() throws FilloException {
		logger = rep.startTest("Lexicon and concept scenarios");
		System.out.println("//To create lexicon");
		commFunc.login_User1(driver);
		bio.click_module(driver, "bio&lexicon");
		long start_time = commFunc.start_time();
		commFunc.Click_btn(driver, "Create");
		String Lexican = record.getField("Lexican");
		commFunc.put_field_data(driver, "Enter Single lexicon", Lexican);
		commFunc.Click_btn(driver, "CHECK SPELLING");
		commFunc.Click(driver, By.xpath("//berd-check-spelling-dialog//button//span[contains(text(),'Dictionary')]"));
		commFunc.Click_btn(driver, "ADD");
		long end_time = commFunc.end_time();
		long timed = commFunc.time_dif(start_time, end_time);
		bio.validate_add_lexical(driver, timed);

	}

	@Test(testName = "Update lexicon", priority = 2, enabled = true)
	public void update_lexicon() throws FilloException, InterruptedException {
		System.out.println("//To update lexicon");
		long start_time = commFunc.start_time();
		commFunc.approver_module(driver);
		bio.click_module(driver, "bio&lexicon");
		commFunc.Click_btn(driver, "Read");
		String lexicon = record.getField("Lexican");
		commFunc.put_field_data(driver, "Search", lexicon);
		Thread.sleep(3000);
		commFunc.Click(driver, By.xpath("(//tbody[@role='rowgroup']//tr)[1]"));
		commFunc.Click_btn(driver, "UPDATE LEXICON");
		// commFunc.Click_btn(driver,"Select Checkboxes");
		// commFunc.Click_btn(driver,"Continue Update");
		commFunc.Click_btn(driver, "Increase Maturity");
		bio.maturity_level(driver);
		Thread.sleep(5000);
		String new_lexican = record.getField("Designation");
		commFunc.put_field_data(driver, "Set designation", new_lexican);
		commFunc.Click_btn(driver, "CHECK SPELLING");
		commFunc.Click(driver, By.xpath("//berd-check-spelling-dialog//button//span[contains(text(),'Dictionary')]"));
		Thread.sleep(5000);
		commFunc.Click_btn(driver, "Save");
		// commFunc.Click_btn(driver,"Select Checkboxes");
		commFunc.Click_btn(driver, "Continue Update");
		bio.validate_update_lexicon(driver);
		commFunc.logout(driver);
		commFunc.login_User2(driver);
		commFunc.update_ticket_module(driver, "LEXICON");
		long end_time = commFunc.end_time();
		long timed = commFunc.time_dif(start_time, end_time);
		bio.updated_approval_lexicon(driver, new_lexican, timed);
	}

	@Test(testName = "Delete lexicon request ", priority = 3, enabled = true)
	public void delete_btn_request() throws InterruptedException {
		System.out.println("//For validate delete request twice");
		long start_time = commFunc.start_time();
		commFunc.delete_lexicon_path(driver);
		Thread.sleep(3000);
		commFunc.delete_lexicon_paths(driver);
		long end_time = commFunc.end_time();
		long timed = commFunc.time_dif(start_time, end_time);
		bio.validate_delete_button_twice(driver, timed);
	}
	
	@Test(testName = "Validate delete lexicon for same user", priority = 4, enabled = true)
	public void validate_delete_lexicon() {
		System.out.println("//For validating delete lexicon with same user");
		long start_time = commFunc.start_time();
		commFunc.delete_ticket_same_user(driver, "LEXICON");
		long end_time = commFunc.end_time();
		long timed = commFunc.time_dif(start_time, end_time);
		bio.validate_delete_lexicon_same_user(driver,timed);
	}

	@Test(testName = "Delete lexicon", priority = 5, enabled = true)
	public void delete_lexicon() throws FilloException {
		System.out.println("//For validate delete request");
		long start_time = commFunc.start_time();
		driver.navigate().refresh();
		commFunc.logout(driver);
		commFunc.login_User1(driver);
		commFunc.delete_ticket_module(driver, "LEXICON");
		long end_time = commFunc.end_time();
		long timed = commFunc.time_dif(start_time, end_time);
		bio.updated_approval_lexicon(driver, timed);

		// For removing all flexibility
		bio.click_module(driver, "bm");
		bio.click_module(driver, "bm/flexibility-mgr");
		commFunc.click_dropdown(driver, "Flexibility Level", "Flexibility Board");
		commFunc.set_previous_state_update(driver);
		commFunc.set_previous_state_delete(driver);

	}
	
	@Test(testName = "Change flexibility level", priority = 6, enabled = false)
	public void change_flexibility() throws InterruptedException {
		System.out.println("//For changing flexibility");
		long start_time = commFunc.start_time();
		commFunc.click_dropdown(driver, "Flexibility Board", "Flexibility Level");
		commFunc.Click_btn(driver,"Define Working Flexibility");
		commFunc.dropdown_data(driver,"New Working Flexibility","2");
		commFunc.Click_btn(driver,"Save");
		long end_time = commFunc.end_time();
		long timed = commFunc.time_dif(start_time, end_time);
		bio.validate_flexibility_change(driver,timed);
		
		//changing back to flexibility
		commFunc.login_User1(driver);
		Thread.sleep(5000);
		bio.click_module(driver, "bm");
		bio.click_module(driver, "bm/flexibility-mgr");
	    commFunc.flexibility_reverse(driver);
	}
	
	@Test(testName = "Create flexibility record", priority = 7, enabled = true)
	public void create_flexibility() throws FilloException, InterruptedException {
//		logger = rep.startTest("Lexicon and concept scenarios");
//		commFunc.login_User1(driver);
		System.out.println("//For creating flexibility record");
		long start_time = commFunc.start_time();
		bio.click_module(driver, "bm");
		bio.click_module(driver, "bm/flexibility-mgr");
		commFunc.Click_btn(driver,"Create Record");
		Thread.sleep(8000);
		String Designation = record.getField("Designation");
		commFunc.put_field_data(driver, "Designation", Designation);
		Thread.sleep(8000);
		commFunc.Click_btn(driver,"Save");
		commFunc.Click(driver, By.xpath("//berd-confirmation-dialog//div//button//span[contains(text(),'Yes')]"));
		long end_time = commFunc.end_time();
		long timed = commFunc.time_dif(start_time, end_time);
		bio.validate_added_flexibility(driver,timed);
	}
	
	
	@Test(testName = "Update flexibility record", priority = 8, enabled = false)//true when it gets resolved
	public void Update_flexibility() throws FilloException {
		System.out.println("//For validating update flexibility");
		long start_time = commFunc.start_time();
		bio.click_Sorticons(driver);
		String Designation = record.getField("Designation");
		commFunc.Click(driver, By.xpath("(//mat-card-content//tbody//tr//td[contains(text(),'"+Designation+"')]//following-sibling::td//mat-select)"));
		commFunc.Click(driver,By.xpath("//div[@role='listbox']//child::mat-option//span[contains(text(),'Update')]"));
		commFunc.put_field_data(driver,"Designation","1");
		commFunc.Click_btn(driver,"Save");
		commFunc.Click(driver, By.xpath("//berd-confirmation-dialog//div//button//span[contains(text(),'Yes')]"));
		long end_time = commFunc.end_time();
		long timed = commFunc.time_dif(start_time, end_time);
		bio.validate_update_flexibility(driver, timed);
	}
	
	@Test(testName = "view added flexibility record", priority = 9, enabled = true)
	public void view_flexibility_details() throws FilloException, InterruptedException {
		System.out.println("//For viewing added flexibility");
		long start_time = commFunc.start_time();
		bio.click_Sorticons(driver);
		String Designation = record.getField("Designation");
		commFunc.Click(driver, By.xpath("(//mat-card-content//tbody//tr//td[contains(text(),'"+Designation+"')]//following-sibling::td//mat-select)"));
		commFunc.Click(driver,By.xpath("//div[@role='listbox']//child::mat-option//span[contains(text(),'Details')]"));
		long end_time = commFunc.end_time();
		long timed = commFunc.time_dif(start_time, end_time);
		bio.validate_details_flexibility(driver, timed);
	}
	
	@Test(testName = "Delete added flexibility record", priority = 10, enabled = true)
	public void validate_delete_flexibility() throws FilloException {
		System.out.println("//For validating delete flexibility");
		long start_time = commFunc.start_time();
		String Designation = record.getField("Designation");
		commFunc.Click(driver, By.xpath("(//mat-card-content//tbody//tr//td[contains(text(),'"+Designation+"')]//following-sibling::td//mat-select)"));
		commFunc.Click(driver,By.xpath("//div[@role='listbox']//child::mat-option//span[contains(text(),'Delete')]"));
		long end_time = commFunc.end_time();
		long timed = commFunc.time_dif(start_time, end_time);
		bio.validate_delete_flexibility(driver,timed);

	}

}
