package com.End2End.Test_Regression;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;

public class Upal_parameter extends BaseClass {
	
	@Test(testName = "Create simple paramter", priority = 15, enabled = true)
	public void create_simple() throws FilloException {
		logger = rep.startTest("Upal paramter scenarios");
		commFunc.login_User1(driver);//ye hataga
		System.out.println("//For creating simple paramter");
		long start_time = commFunc.start_time();
		bio.click_module(driver, "upal");
		bio.click_module(driver, "upal/parameter-mgt");
		upal.click_dropdown(driver, "SELECT PARAMETER STRUCTURE", "SIMPLE PARAMETER");
		commFunc.select_menu(driver, "Create");
		commFunc.add_data_simple(driver);
		long end_time= commFunc.end_time();
		long timed =commFunc.time_dif(start_time, end_time);
		upal.validate_simple_singular(driver, timed);
	}
	
	@Test(testName = "Update simple paramter", priority = 16, enabled = true)
	public void update_simple() throws InterruptedException, FilloException {
		System.out.println("//For updating simple paramter");
		long start_time = commFunc.start_time();
		commFunc.select_menu(driver, "Update");
		commFunc.select_menu(driver, "Navigate");
		Thread.sleep(5000);
		commFunc.select_menu(driver, "Update");
		String Designation = record.getField("Designation");
		commFunc.put_field_data(driver,"Search", Designation);
		Thread.sleep(3000);
		//commFunc.Click_btn(driver, "Update");
		commFunc.Click(driver, By.xpath("//berd-searchable-table//table//tbody//tr//td[contains(text(),'"+Designation+"')]"));
		commFunc.Click_btn(driver, "Update");
		commFunc.Click_btn(driver,"Increase Maturity");
		long end_time= commFunc.end_time();
		long timed =commFunc.time_dif(start_time, end_time);
		bio.validate_maturity(driver);
		commFunc.Click_btn(driver, "Cancel");
		commFunc.Click_btn(driver, "SUBMIT");
		commFunc.Click_btn(driver, "Select Checkboxes");
		commFunc.Click_btn(driver, "Continue Update");
		upal.validate_update_simple(driver);
		commFunc.update_ticket_module(driver,"UPAL GENERATOR AND MANAGER");
		upal.update_simpleParameter_ticket(driver,timed);
	}

	@Test(testName = "Create multifield paramter", priority = 17, enabled = true)
	public void create_multifield() throws FilloException, InterruptedException {
		System.out.println("//For Create singular multifield parameter");
		long start_time = commFunc.start_time();
		upal.click_dropdown(driver, "SIMPLE PARAMETER", "MULTIFIELD PARAMETER");
		commFunc.select_menu(driver, "Create");
		upal.click_dropdown(driver, "PLURALITY", "SINGULAR");
		upal.click_parameter_dropdown(driver, "Select C2");
		String Designation = record.getField("Designation");
		upal.filter(driver,Designation);
		upal.click_coordinate_paramter_icon(driver, "subject");
		upal.click_coordinate_paramter_icon(driver, "resource");
		upal.click_coordinate(driver, Designation);
		commFunc.Click_btn(driver,"Add Sub-Parameter");
		commFunc.dropdown_data(driver,"Upal-Parameter","urt_item.id");
		long end_time= commFunc.end_time();
		long timed =commFunc.time_dif(start_time, end_time);
		upal.validate_multifield_singular(driver);
	}
	@Test(testName = "Update multifield paramter", priority = 18, enabled = true)
	public void update_multifield() throws FilloException, InterruptedException {
		System.out.println("//For updating multifield");
		long start_time = commFunc.start_time();
		commFunc.select_menu(driver, "Update");
//		upal.click_dropdown(driver, "PLURALITY", "SINGULAR");
//		upal.click_parameter_dropdown(driver, "Select C2");
		String Designation = record.getField("Designation");
		commFunc.put_field_data(driver,"Search", Designation);
		commFunc.Click(driver, By.xpath("//berd-searchable-table//table//tbody//tr//td[contains(text(),'"+Designation+"')]"));
		commFunc.Click_btn(driver, "UPDATE");
		upal.click_parameter_dropdown(driver, "Select C2");
		upal.filter(driver,Designation);
		upal.click_coordinate_paramter_icon(driver, "subject");
		upal.click_coordinate_paramter_icon(driver, "resource");
		upal.click_coordinate(driver, Designation);
		commFunc.Click_btn(driver,"Add Sub-Parameter");
		commFunc.dropdown_data(driver,"Upal-Parameter","urt_item.id");
		commFunc.Click_btn(driver, "SUBMIT");
		commFunc.Click_btn(driver, "Select Checkboxes");
		commFunc.Click_btn(driver, "Continue Update");
		upal.validate_update_multifield(driver);
		commFunc.update_ticket_module(driver,"UPAL GENERATOR AND MANAGER");
		long end_time= commFunc.end_time();
		long timed =commFunc.time_dif(start_time, end_time);
		upal.update_multifieldParameter_ticket(driver, timed);
	}
}
