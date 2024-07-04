package com.End2End.Test_Regression;

import org.testng.annotations.Test;

public class User_manager extends BaseClass {

	@Test(testName = "Validate active user", priority = 11, enabled = true)
	public void validate_active_user() {
		logger = rep.startTest("User manager scenarios");
//		commFunc.login_User1(driver);
//		bio.click_module(driver, "bm");
		System.out.println("//For Validate active user");
		long start_time = commFunc.start_time();
		bio.click_module(driver, "bm/user-management");
		user.active_checkbox(driver);
		long end_time = commFunc.end_time();
		long timed = commFunc.time_dif(start_time, end_time);
		user.validate_active_check(driver, timed);

	}

	@Test(testName = "Validate Inactive user", priority = 12, enabled = true)
	public void validate_Inactive_user() {
		System.out.println("//For validating inactive user");
		long start_time = commFunc.start_time();
		user.active_checkbox(driver);
		user.Inactive_checkbox(driver);
		long end_time = commFunc.end_time();
		long timed = commFunc.time_dif(start_time, end_time);
		user.validate_Inactive_check(driver, timed);
	}

	@Test(testName = "Add blank user", priority = 13, enabled = true)
	public void add_user() throws InterruptedException {
		System.out.println("//For validating blank user");
		long start_time = commFunc.start_time();
		user.Inactive_checkbox(driver);
		commFunc.Click_btn(driver,"Add User");
		Thread.sleep(2000);
		long end_time = commFunc.end_time();
		long timed = commFunc.time_dif(start_time, end_time);
		user.validate_blank_user(driver,timed);
	}
	
	@Test(testName = "Search user", priority = 14, enabled = true)
	public void Add_user() throws InterruptedException {
		System.out.println("//For searching user");
		long start_time = commFunc.start_time();
		commFunc.put_field_search(driver,(conf.get_email()));
		long end_time = commFunc.end_time();
		long timed = commFunc.time_dif(start_time, end_time);
		user.validate_search_user(driver,timed);
	}
	
	@Test(testName = "Validate login for inactive user", priority = 15, enabled = true)
	public void validate_inactive_user() {
		System.out.println("//For validating login for inactive user");
		long start_time = commFunc.start_time();
		commFunc.logout(driver);
		commFunc.login_User_inctive(driver);
		long end_time = commFunc.end_time();
		long timed = commFunc.time_dif(start_time, end_time);
		user.validate_inactive_login(driver,timed);
		
	}
	
	
	
}
