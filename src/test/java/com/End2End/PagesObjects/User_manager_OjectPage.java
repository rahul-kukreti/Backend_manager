package com.End2End.PagesObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.End2End.Test_Regression.BaseClass;
import com.relevantcodes.extentreports.LogStatus;

public class User_manager_OjectPage extends BaseClass {

	By Active_checkbox = By.xpath("//section//mat-checkbox[@formcontrolname='Active']");
	By InActive_checkbox = By.xpath("//section//mat-checkbox[@formcontrolname='Pending']");
	By Add_user = By.xpath("//berd-add-user-dialog//div//form//div//button[contains(text(),'Add')]");
	
	By designation = By.xpath("//input[@formcontrolname='designation']");
	By email = By.xpath("//input[@formcontrolname='email']");
	By password = By.xpath("//input[@formcontrolname='password']");
	
	
	public void active_checkbox(WebDriver driver) {
		commFunc.Explicitywait(driver, Active_checkbox);
		commFunc.Click(driver, Active_checkbox);	
	}
	
	public void Inactive_checkbox(WebDriver driver) {
		commFunc.Explicitywait(driver, InActive_checkbox);
		commFunc.Click(driver, InActive_checkbox);	
	}
	
	public void validate_active_check(WebDriver driver,long timed) {
		commFunc.Explicitywait(driver, By.xpath("//table//tbody//td[contains(text(),'Active')]"));
		List<WebElement> list = driver.findElements(By.xpath("//table//tbody//td[contains(text(),'Active')]"));
		int list_size = list.size();
		//System.out.println(list_size);
		for(int i= 0;i<=list_size;i++) {
			try {
			String expected_value = list.get(i).getText();
			String actual_value ="Active";
			if(actual_value.equalsIgnoreCase(expected_value)) {
				String s = " Active Filter work properly";
				System.out.println(s);
				logger.log(LogStatus.INFO, s);
				logger.log(LogStatus.PASS, s);
				commFunc.Data_Pass("validate_active_check",s,"PASS","",timed,1,15);
			}
			else {
				String s = "Active Filter is not woring";
				System.err.println(s);
				logger.log(LogStatus.FAIL, s);
				commFunc.Data_Pass("validate_active_check",s,"FAIL",s,timed,4,15);
			}
			break;
			}
      catch(IndexOutOfBoundsException e) {
			
		}
	}
	}
	
	public void validate_Inactive_check(WebDriver driver,long timed) {
		commFunc.Explicitywait(driver, By.xpath("//table//tbody//td[contains(text(),'inActive')]"));
		List<WebElement> list = driver.findElements(By.xpath("//table//tbody//td[contains(text(),'inActive')]"));
		int list_size = list.size();
		//System.out.println(list_size);
		for(int i= 0;i<=list_size;i++) {
			try {
			String expected_value = list.get(i).getText();
			String actual_value ="inActive";
			if(actual_value.equalsIgnoreCase(expected_value)) {
				String s = "In-Active Filter work properly";
				System.out.println(s);
				logger.log(LogStatus.INFO, s);
				logger.log(LogStatus.PASS, s);
				commFunc.Data_Pass("validate_Inactive_check",s,"PASS","",timed,1,15);
			}
			else {
				String s = "In-Active Filter is not working";
				System.err.println(s);
				logger.log(LogStatus.FAIL, s);
				commFunc.Data_Pass("validate_Inactive_check",s,"FAIL",s,timed,4,15);
			}
			break;
			}
      catch(IndexOutOfBoundsException e) {
			
		}
	}
	}
	
	public void validate_blank_user(WebDriver driver,long timed) {
		//commFunc.Explicitywait(driver, Add_user);
		commFunc.actionsClick(driver, Add_user);
		commFunc.Explicitywait(driver, By.xpath("//mat-form-field//div//input[@formcontrolname='designation']//following::div[contains(text(),'*Designation is required')]"));
		if(driver.findElements(By.xpath("//mat-form-field//div//input[@formcontrolname='designation']//following::div[contains(text(),'*Designation is required')]")).size()!=0) {
			String s = "User cannot able to add blank user successfully";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			logger.log(LogStatus.PASS, s);
			commFunc.Click(driver, By.xpath("//berd-add-user-dialog//div//button//span[contains(text(),'Close')]"));
			commFunc.Data_Pass("validate_blank_user",s,"PASS","",timed,1,15);
		}
		else {
			String s = "User can able to add blank user successfully";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Click(driver, By.xpath("//berd-add-user-dialog//div//button//span[contains(text(),'Close')]"));
			commFunc.Data_Pass("validate_blank_user",s,"FAIL",s,timed,4,15);
		}
		
		
	}
	
	public void user_details(WebDriver driver) throws InterruptedException {

		commFunc.sendKeys(driver, designation, (conf.get_designation()));
		commFunc.sendKeys(driver, email, (conf.get_email()));
		commFunc.sendKeys(driver, password, (conf.getpassword()));
		commFunc.click_dropdown(driver, "Select Institution", "Bridge Intelligence");
		Thread.sleep(3000);
		commFunc.click_dropdown(driver, "Select Role", "general");
		commFunc.Click(driver, Add_user);
		commFunc.Click_btn(driver, "Yes");
	}
	
	public void validate_search_user(WebDriver driver,long timed) {
		commFunc.Explicitywait(driver, By.xpath("//berd-user-management//table//tbody//td[contains(text(),'"+conf.get_email()+"')]"));
		if(driver.findElements(By.xpath("//berd-user-management//table//tbody//td[contains(text(),'"+conf.get_email()+"')]")).size()!=0) {
			String s = "User can able to search added user successfully";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			logger.log(LogStatus.PASS, s);
			commFunc.Data_Pass("validate_search_user",s,"PASS","",timed,1,15);
		}
		else {
			String s = "User cannot able to search added user successfully";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Data_Pass("validate_search_user",s,"FAIL",s,timed,4,15);
		}
		
	}
	
	public void validate_inactive_login(WebDriver driver,long timed) {
		commFunc.Explicitywait(driver, By.xpath("//mat-card//mat-card-actions//following::div[contains(text(),'Your account has been deactivated')]"));
		if(driver.findElements(By.xpath("//mat-card//mat-card-actions//following::div[contains(text(),'Your account has been deactivated')]")).size()!=0) {
			String s = "Inactive User cannot able to login successfully";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			logger.log(LogStatus.PASS, s);
			commFunc.Data_Pass("validate_inactive_login",s,"PASS","",timed,1,15);
			driver.navigate().refresh();
			commFunc.login_User1(driver);
		}
		else {
			String s = "Inactive user loggedIn sucessfully";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Data_Pass("validate_inactive_login",s,"FAIL",s,timed,4,15);
			
		}
	}
	
	
}
