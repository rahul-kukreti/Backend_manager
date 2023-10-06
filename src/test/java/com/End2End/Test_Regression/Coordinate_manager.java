package com.End2End.Test_Regression;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;

public class Coordinate_manager extends BaseClass {
	
	@Test(testName = "Create C1 variable", priority = 11, enabled = true)
	public void create_C1() throws FilloException {
		logger = rep.startTest("Coordinate manager scenarios");
		System.out.println("//For creating c1 varibale");
		commFunc.login_User1(driver);//ye hataga
		long start_time = commFunc.start_time();
		bio.click_module(driver, "coordinate");
		bio.click_module(driver, "coordinate/c1");
		commFunc.Click_btn(driver, "Create");
		String Designation = record.getField("Designation");
		commFunc.dropdown_data(driver, "Bio-Concept", Designation);
		commFunc.Click(driver, By.xpath("//div[@role='tabpanel']//button//span[text()=' Continue ']"));
		String filter = record.getField("Filter");
		commFunc.put_field_data(driver, "Filter", filter);
		commFunc.click_coordinate_icon(driver, "what is the");
		commFunc.click_coordinate_icon(driver, "4 -  numeric");
		commFunc.click_coordinate(driver, "numeric  simple");
		String default_unit = record.getField("Default_unit");
		commFunc.dropdown_data(driver, "Default Unit", default_unit);
		commFunc.scrollIntoElement(driver, By.xpath("(//div[@role='tabpanel']//button//span[text()=' Continue '])[2]"));
		commFunc.Click(driver, By.xpath("(//div[@role='tabpanel']//button//span[text()=' Continue '])[2]"));
		long end_time = commFunc.end_time();
		long timed = commFunc.time_dif(start_time, end_time);
		coordinate.validate_addc1(driver,timed);
	}
	
	@Test(testName = "Update C1 variable", priority = 12, enabled = true)
	public void update_C1() throws FilloException, InterruptedException {
//		logger = rep.startTest("Coordinate manager scenarios");
//		commFunc.login_User1(driver);
//		bio.click_module(driver, "coordinate");
//		bio.click_module(driver, "coordinate/c1");
//		commFunc.Click_btn(driver,"Read");//ye hataga
		System.out.println("//For updating C1 variable");
		String filter = record.getField("Designation");
		commFunc.put_field_data(driver, "Filter", filter);
		long start_time = commFunc.start_time();
		commFunc.click_coordinate_icon(driver, "what is the");
		commFunc.click_coordinate_icon(driver, "4 -  numeric");
		commFunc.click_coordinate_icon(driver, "numeric  simple");
		Thread.sleep(2000);
		commFunc.click_coordinate(driver, filter);
		commFunc.scrollIntoElement(driver, By.xpath("//button//span[contains(text(),'Update')]"));
		commFunc.Click_btn(driver, "Update");
		commFunc.Click_btn(driver, "Update Domain");
		coordinate.domain_update_details(driver);
		commFunc.update_ticket_module(driver,"COORDINATES");
		long end_time= commFunc.end_time();
		long timed =commFunc.time_dif(start_time, end_time);
		coordinate.validate_updateC1_ticket(driver, timed);
	} 
	
	@Test(testName = "Create C2 variable", priority = 13, enabled = true)
	public void create_c2() throws FilloException, InterruptedException {
		System.out.println("//For creating c2 varibale");
		long start_time = commFunc.start_time();
		bio.click_module(driver, "coordinate/c2");
		commFunc.Click_btn(driver, "Create");
		commFunc.Click_btn(driver, "Simple");
		String Designation = record.getField("Designation");
		commFunc.dropdown_data(driver, "Bio-Concept", Designation);
		commFunc.Click(driver, By.xpath("//div[@role='tabpanel']//button//span[text()=' Continue ']"));
		commFunc.click_coordinate_icon(driver, "subject characterized");
		Thread.sleep(2000);
		commFunc.click_coordinate(driver, "resource");
		commFunc.Click(driver, By.xpath("(//div[@role='tabpanel']//button//span[text()=' Continue '])[2]"));
		long end_time= commFunc.end_time();
		long timed =commFunc.time_dif(start_time, end_time);
		coordinate.validateC2_simple(driver, timed);
	}
	
	@Test(testName = "Create C3 variable", priority = 14, enabled = true)
	public void create_c3() throws InterruptedException, FilloException {
		System.out.println("//For creating c3 variable");
		long start_time = commFunc.start_time();
		bio.click_module(driver, "coordinate/c3");
		commFunc.Click_btn(driver, "Create");
		commFunc.Click_btn(driver, "Simple");
		Thread.sleep(5000);
		String Designation = record.getField("Designation");
		commFunc.dropdown_data(driver, "Bio-Concept", Designation);
		commFunc.Click(driver, By.xpath("//div[@role='tabpanel']//button//span[text()=' Continue ']"));
		commFunc.click_coordinate_icon(driver, "internal");
		commFunc.click_coordinate(driver, "free");
		commFunc.Click(driver, By.xpath("(//div[@role='tabpanel']//button//span[text()=' Continue '])[2]"));
		long end_time= commFunc.end_time();
		long timed =commFunc.time_dif(start_time, end_time);
		coordinate.validate_c3(driver, timed);
	}

}
