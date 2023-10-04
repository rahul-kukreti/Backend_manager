package com.End2End.Test_Regression;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;

public class Bio_lexicon extends BaseClass {

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
		commFunc.Click_btn(driver,"Read");
		bio.click_Sorticon(driver);
		commFunc.Click(driver, By.xpath("(//tbody[@role='rowgroup']//tr)[1]"));
		commFunc.Click_btn(driver,"UPDATE LEXICON");
		commFunc.Click_btn(driver,"Select Checkboxes");
		commFunc.Click_btn(driver,"Continue Update");
		Thread.sleep(5000);
		String new_lexican = record.getField("Designation");
		commFunc.put_field_data(driver, "Set designation", new_lexican);
		Thread.sleep(5000);
		commFunc.Click_btn(driver,"Save");
		bio.validate_update_lexicon(driver);
		commFunc.update_ticket_module(driver,"LEXICON");
		long end_time= commFunc.end_time();
		long timed =commFunc.time_dif(start_time, end_time);
		bio.updated_approval_lexicon(driver, new_lexican, timed);
	}
	
	@Test(testName = "Create concept", priority = 3, enabled = true)
	public void create_concept() throws FilloException {
		System.out.println("//To create concept");
		long start_time = commFunc.start_time();
		bio.click_module(driver, "bio&lexicon/concept");
		commFunc.Click_btn(driver,"Create");
		commFunc.Click_btn(driver,"create concept using lexicon");
		String connector = record.getField("Connector");
		commFunc.dropdown_data(driver,"Connector",connector);
		String lexical_value = record.getField("Designation");
		commFunc.dropdown_data(driver,"Lexicon",lexical_value);
		commFunc.jclick(driver, By.xpath("(//mat-label[contains(text(),'Connector')])[2]"));
		String value1 = record.getField("Connector1");
		commFunc.jclick(driver,By.xpath("//div[@role='listbox']//child::mat-option//span[contains(text(),'"+value1+"')]"));
		String predicate = record.getField("Predicate");
		commFunc.dropdown_data(driver,"Predicate",predicate);
		String concept = record.getField("Concept");
		commFunc.dropdown_data(driver,"Concept",concept);
		bio.click_checkbox(driver);
		commFunc.Click_btn(driver,"Create Concept");
		long end_time= commFunc.end_time();
		long timed =commFunc.time_dif(start_time, end_time);
		bio.validate_concept(driver);
		//bio.click_module(driver, "bio&lexicon/concept");
		//commFunc.Click_btn(driver,"Read");
	}
	
	@Test(testName = "Update concept", priority = 4, enabled = true)
	public void update_concept() throws FilloException {
		System.out.println("//For updating concept");
		long start_time = commFunc.start_time();
		bio.click_module(driver, "bio&lexicon/concept");
	    commFunc.Click_btn(driver,"Read");
	    bio.click_Sorticon(driver);
		commFunc.Click(driver, By.xpath("(//tbody[@role='rowgroup']//tr)[1]"));
		commFunc.Click_btn(driver,"UPDATE CONCEPT");
		commFunc.Click_btn(driver,"Add New Axiom as Object");
		String subject = record.getField("Subject");
		commFunc.dropdown_data(driver,"Subject",subject);
		String predicate = record.getField("Predicate");
		commFunc.dropdown_data(driver,"Predicate",predicate);
		commFunc.Click_btn(driver,"Save");
		commFunc.Click_btn(driver,"SAVE CHANGES");
		bio.validate_update_concept(driver);
		commFunc.update_ticket_module(driver,"LEXICON");
		long end_time= commFunc.end_time();
		long timed =commFunc.time_dif(start_time, end_time);
		String new_lexican = record.getField("Designation");
		bio.updated_approval_concept(driver, new_lexican,timed);
	}
	
	@Test(testName = "Create predicate", priority = 5, enabled = true)
	public void create_predicate() throws FilloException {
		System.out.println("//For creating predicate");
		long start_time = commFunc.start_time();
		bio.click_module(driver,"bio&lexicon/predicate");
		commFunc.Click_btn(driver,"Create");
		String Designation = record.getField("predicate_designation");
		commFunc.put_field_data(driver, "Designation", Designation);
		
		String Definitions = record.getField("Definition");
		commFunc.put_textarea(driver, "Definition", Definitions);
		
		String Note = record.getField("Note");
		commFunc.put_textarea(driver, "Note", Note);
		commFunc.Click_btn(driver,"Create Predicate");
		long end_time= commFunc.end_time();
		long timed =commFunc.time_dif(start_time, end_time);
		bio.validatae_predicate(driver, timed);
	}
	
	@Test(testName = "Update predicate", priority = 6, enabled = true)
	public void update_predicate() {
		System.out.println("//For updating predicate");
		long start_time = commFunc.start_time();
		commFunc.Click_btn(driver,"Read");
		bio.click_Sorticon(driver);
		commFunc.Click(driver, By.xpath("(//tbody[@role='rowgroup']//tr)[1]"));
		commFunc.Click_btn(driver,"Update");
		bio.details_updatePredicate(driver);
		commFunc.Click_btn(driver,"SUBMIT CHANGES");
		commFunc.Click_btn(driver,"Continue Update");
		bio.validate_update_predicate(driver);
		commFunc.update_ticket_module(driver,"LEXICON");
		long end_time= commFunc.end_time();
		long timed =commFunc.time_dif(start_time, end_time);
		bio.updated_approval_predicate(driver,timed);
	}
	
	@Test(testName = "Create connector", priority = 7, enabled = true)
	public void create_connector() throws InterruptedException {
		System.out.println("//For creating connector");
		long start_time = commFunc.start_time();
		bio.click_module(driver,"bio&lexicon/connector");
		commFunc.Click_btn(driver,"Create");
		Thread.sleep(3000);
		bio.add_connector_details(driver);
		commFunc.Click_btn(driver,"ADD");
		long end_time= commFunc.end_time();
		long timed =commFunc.time_dif(start_time, end_time);
		bio.validate_addConnector(driver, timed);
	}
	
	@Test(testName = "Update connector", priority = 8, enabled = true)
	public void update_connector() {
		System.out.println("//For updating connector");
		long start_time = commFunc.start_time();
		bio.click_module(driver,"bio&lexicon/connector");
		commFunc.Click_btn(driver,"Read");
		bio.same_connector(driver);
		commFunc.Click(driver, By.xpath("(//tbody[@role='rowgroup']//tr)[1]"));
		commFunc.Click_btn(driver,"UPDATE CONNECTOR");
		bio.update_connector_details(driver);
		commFunc.Click_btn(driver,"Save");
		commFunc.Click_btn(driver, "Select Checkboxes");
		commFunc.Click_btn(driver, "Continue Update");
		bio.validate_update_connector(driver);
		commFunc.update_ticket_module(driver,"LEXICON");
		long end_time= commFunc.end_time();
		long timed =commFunc.time_dif(start_time, end_time);
		bio.updated_approval_connector(driver, timed);
	}
	
	@Test(testName = "Delete connector", priority = 9, enabled = true)
	public void delete_connector() {
		System.out.println("//For deleting connector");
		long start_time = commFunc.start_time();
		commFunc.Click(driver, By.xpath("(//tbody[@role='rowgroup']//tr)[1]"));
		commFunc.Click_btn(driver,"DELETE CONNECTOR");
		commFunc.Click_btn(driver, "Select Checkboxes");
		commFunc.Click_btn(driver, "Continue Delete");
		bio.validate_connector(driver);
		commFunc.delete_ticket_module(driver,"LEXICON");
		long end_time= commFunc.end_time();
		long timed =commFunc.time_dif(start_time, end_time);
		bio.delete_approval_connector(driver, timed);
	}
	
	@Test(testName = "Delete predicate", priority = 10, enabled = true)
	public void delete_predicate() {
		System.out.println("//For deleting predicate");
		long start_time = commFunc.start_time();
		bio.click_module(driver,"bio&lexicon/predicate");
		commFunc.Click_btn(driver,"Read");
		bio.click_Sorticon(driver);
		commFunc.Click(driver, By.xpath("(//tbody[@role='rowgroup']//tr)[1]"));
		commFunc.Click_btn(driver,"DELETE");
		commFunc.Click_btn(driver, "Select Checkboxes");
		commFunc.Click_btn(driver, "Continue Delete");
		bio.validate_delete_predicate(driver);
		commFunc.delete_ticket_module(driver,"LEXICON");
		long end_time= commFunc.end_time();
		long timed =commFunc.time_dif(start_time, end_time);
		bio.delete_approval_predicate(driver, timed);
	}

}
