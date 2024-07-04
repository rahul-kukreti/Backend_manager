package com.End2End.PagesObjects;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.End2End.Test_Regression.BaseClass;
import com.codoid.products.exception.FilloException;
import com.relevantcodes.extentreports.LogStatus;

public class Backend_manager_ObjectPage extends BaseClass {
	
	public String s = RandomStringUtils.randomAlphanumeric(5);

	By Yes_btn = By.xpath("//button//span[contains(text(),'Yes')]");
	By btn_ok = By.xpath("//button//span[contains(text(),'OK')]");
	By sort_id = By.xpath("//table//thead//tr//th//div[contains(text(),'Id')]");
	By sort_Id = By.xpath("//table//thead//tr//th//div[contains(text(),'id')]");
	By checkbox_click = By.xpath("//mat-checkbox//div//label[contains(text(),'mainAxiom')]");
	By definition = By.xpath("//mat-label[text()='Designation']//ancestor::mat-form-field//following-sibling::input");

	public void click_module(WebDriver driver, String name) {
		commFunc.Explicitywait(driver, By.xpath("//mat-sidenav//mat-nav-list//a[@href='/" + name + "']"));
		commFunc.Click(driver, By.xpath("//mat-sidenav//mat-nav-list//a[@href='/" + name + "']"));
	}

	public void click_Sorticon(WebDriver driver) {
		commFunc.Explicitywait(driver, sort_id);
		commFunc.Click(driver, sort_id);
		commFunc.Click(driver, sort_id);
	}
	
	public void click_Sorticons(WebDriver driver) {
		commFunc.Explicitywait(driver, sort_Id);
		commFunc.Click(driver, sort_Id);
		commFunc.Click(driver, sort_Id);
	}
	

	public void click_checkbox(WebDriver driver) {
		commFunc.Explicitywait(driver, checkbox_click);
		commFunc.Click(driver, checkbox_click);
	}

	public void validate_add_lexical(WebDriver driver, long timed) {
		commFunc.Explicitywait(driver,By.xpath("//berd-confirmation//div//p[contains(text(),'Are you sure want to add')]"));
		if (driver.findElements(By.xpath("//berd-confirmation//div//p[contains(text(),'Are you sure want to add')]")).size() != 0) {
			commFunc.Click(driver, Yes_btn);
		}
		commFunc.Explicitywait(driver,By.xpath("//berd-confirmation//div//p[contains(text(),'Lexcion has been created successfully.')]"));
		if (driver.findElements(By.xpath("//berd-confirmation//div//p[contains(text(),'Lexcion has been created successfully.')]")).size() != 0) {
			commFunc.jclick(driver, btn_ok);
			String s = "User has created lexical successfully";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			logger.log(LogStatus.PASS, s);
			// commFunc.Data_Pass("validate_add_lexical",s,"PASS","",timed,1,1);

		} else {
			String s = "user cannot able to create lexical";
			System.err.println("user cannot able to create lexical");
			logger.log(LogStatus.FAIL, s);
			commFunc.Click(driver, By.xpath("//berd-confirmation-dialog//mat-icon[contains(text(),'close')]"));
			// commFunc.Data_Pass("validate_add_lexical",s,"FAIL",s,timed,6,1);
		}

	}

	public void validate_update_lexicon(WebDriver driver) {
		commFunc.Explicitywait(driver, Yes_btn);
		commFunc.Click(driver, Yes_btn);
		commFunc.Explicitywait(driver, By.xpath("//berd-confirmation//div//p[contains(text(),'BIO_Lexicon has been created and required approval to update record')]"));
		if (driver.findElements(By.xpath("//berd-confirmation//div//p[contains(text(),'BIO_Lexicon has been created and required approval to update record')]")).size() != 0) {
			String s = "Lexicon sent to approval";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			commFunc.Click(driver, btn_ok);
		}
	}
	
	public void maturity_level(WebDriver driver) throws InterruptedException {
		if(driver.findElements(By.xpath("//berd-manage-maturity-button//button//span[contains(text(),'Increase Maturity')]")).size()!=0) {
			
			List<WebElement> li = driver.findElements(By.xpath("//berd-manage-maturity-button//button//span[contains(text(),'Increase Maturity')]"));
			Iterator<WebElement> itr = li.iterator();
			while (itr.hasNext()) {
				Thread.sleep(4000);
				if(driver.findElements(By.xpath("(//button//span[contains(text(),'Increase Maturity')])[2]")).size()!=0) {
				
					commFunc.jclick(driver,By.xpath("(//button//span[contains(text(),'Increase Maturity')])[2]"));
					Thread.sleep(1000);
					commFunc.jclick(driver,By.xpath("(//button//span[contains(text(),'Increase Maturity')])[2]"));
					
					
	}
				break;
			
			}
			Thread.sleep(2000);
			commFunc.Click(driver, By.xpath("//berd-manage-maturity-dialog//button//span[contains(text(),'Cancel')]"));
				
				
}
		}

	public void updated_approval_lexicon(WebDriver driver, String value, long timed) {
		this.click_module(driver, "bio&lexicon");
		commFunc.Click_btn(driver, "Read");
		this.click_Sorticon(driver);
		commFunc.Explicitywait(driver, By.xpath("//tbody//td//div[contains(text(),'" + value + "')]"));
		if (driver.findElements(By.xpath("//tbody//td//div[contains(text(),'" + value + "')]")).size() != 0) {
			String s = "Lexicon updated via ticket";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			logger.log(LogStatus.PASS, s);
			commFunc.Data_Pass("updated_approval_lexicon", s, "PASS", "", timed, 1, 15);
		} else {
			String s = "user cannot able to update lexical";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Click(driver, By.xpath("//berd-confirmation-dialog//mat-icon[contains(text(),'close')]"));
			commFunc.Data_Pass("validate_add_lexical", s, "FAIL", s, timed, 4, 15);
		}
	}
	
	
      public void validate_maturity(WebDriver driver) throws InterruptedException {
		
		if(driver.findElements(By.xpath("//berd-manage-maturity-button//button//span[contains(text(),'Increase Maturity')]")).size()!=0) {
			
		List<WebElement> li = driver.findElements(By.xpath("//berd-manage-maturity-button//button//span[contains(text(),'Increase Maturity')]"));
		
		Iterator<WebElement> itr = li.iterator();
		while (itr.hasNext()) {
			Thread.sleep(4000);
			if(driver.findElements(By.xpath("(//button//span[contains(text(),'Increase Maturity')])[2]")).size()!=0) {
			
				commFunc.jclick(driver,By.xpath("(//button//span[contains(text(),'Increase Maturity')])[2]"));
				String str = "Maturity validated successfully!";
				logger.log(LogStatus.INFO, str);
				logger.log(LogStatus.PASS, str);
			}
		
			break;
			
}
		}
	}
      
      public void validate_delete_button_twice(WebDriver driver,long timed) {
    	  commFunc.Explicitywait(driver, By.xpath("//berd-confirmation-dialog//p[contains(text(),'This entity is already in worked')]"));
    	  if(driver.findElements(By.xpath("//berd-confirmation-dialog//p[contains(text(),'This entity is already in worked')]")).size()!=0) {
    		  String s = "User cannot able to send duplciate delete request";
    		  System.out.println(s);
    		  logger.log(LogStatus.INFO, s);
  			logger.log(LogStatus.PASS, s);
  			//commFunc.Click(driver, btn_ok);
  			commFunc.Click_btn(driver,"OK");
  			commFunc.Data_Pass("validate_delete_button_twice", s, "PASS", "", timed, 1, 15);
    		  
    	  }
    	  else {
    		  String s = "User can able to send duplciate delete request";
  			System.err.println(s);
  			logger.log(LogStatus.FAIL, s);
  			commFunc.Data_Pass("validate_delete_button_twice", s, "FAIL", s, timed, 4, 15);
  			commFunc.Click(driver, By.xpath("//berd-confirmation-dialog//mat-icon[contains(text(),'close')]"));
    	  }
    	  
      }

	// ------------------------------------------For concept------------------------------------------------------------------

	public void validate_concept(WebDriver Driver,long timed) {
		commFunc.Explicitywait(driver, Yes_btn);
		commFunc.Click(driver, Yes_btn);
		commFunc.Explicitywait(driver, By.xpath("//berd-confirmation//child::p[contains(text(),'Concept Created!')]"));
		if (driver.findElements(By.xpath("//berd-confirmation//child::p[contains(text(),'Concept Created!')]")).size() != 0) {
			commFunc.jclick(driver, btn_ok);
			String s = "User has created concept successfully via a lexicon";
			System.out.println(s);
		} else {
			String s = "user cannot able to create concept";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Click(driver, By.xpath("//berd-confirmation-dialog//mat-icon[contains(text(),'close')]"));
		}

	}
	
	public void validate_update_concept(WebDriver driver) {
		commFunc.Explicitywait(driver, Yes_btn);
		commFunc.Click(driver, Yes_btn);
		commFunc.Explicitywait(driver, By.xpath("//berd-confirmation//div//p[contains(text(),'BIO_Concepts has been created and required approval to update record')]"));
		if (driver.findElements(By.xpath("//berd-confirmation//div//p[contains(text(),'BIO_Concepts has been created and required approval to update record')]")).size() != 0) {
			String s = "concept sent to approval";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			commFunc.Click(driver, btn_ok);
		}
	}
	
	public void updated_approval_concept(WebDriver driver, String value, long timed) {
		this.click_module(driver, "bio&lexicon");
		this.click_module(driver,"bio&lexicon/concept");
		commFunc.Click_btn(driver, "Read");
		this.click_Sorticon(driver);
		commFunc.Click(driver, By.xpath("(//tbody[@role='rowgroup']//tr)[1]"));
		commFunc.Explicitywait(driver,By.xpath("//div//h4[contains(text(),'Concept used as a Object')]//following::table//tbody//tr//td[contains(text(),'" + value + "')]"));
		if (driver.findElements(By.xpath("//div//h4[contains(text(),'Concept used as a Object')]//following::table//tbody//tr//td[contains(text(),'" + value + "')]")).size() != 0) {
			String s = "concept updated via ticket";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			logger.log(LogStatus.PASS, s);
			commFunc.Data_Pass("updated_approval_concept", s, "PASS", "", timed, 1, 15);
			commFunc.Click(driver, By.xpath("//berd-bio-concept-detail-dialog//mat-icon[contains(text(),'close')]"));
		} else {
			String s = "user cannot able to update concept";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Click(driver, By.xpath("//berd-bio-concept-detail-dialog//mat-icon[contains(text(),'close')]"));
			commFunc.Data_Pass("updated_approval_concept", s, "FAIL", s, timed, 4, 15);
		}
	}
	
	
	
	//----------------------------------------------Predicate--------------------------------------------------------------------
	
	public void validatae_predicate(WebDriver driver,long timed) {
		commFunc.Explicitywait(driver, Yes_btn);
		commFunc.Click(driver, Yes_btn);
		commFunc.Explicitywait(driver,By.xpath("//berd-confirmation//child::p[contains(text(),'Predicate Created!')]"));
		if (driver.findElements(By.xpath("//berd-confirmation//child::p[contains(text(),'Predicate Created!')]")).size() != 0) {
			String s = "Predicate created successfully!";
			System.out.println(s);
			commFunc.jclick(driver, btn_ok);
}
		else {
			String s = "user cannot able to create predicate";
			System.err.println(s);
			commFunc.Click(driver, By.xpath("//berd-confirmation-dialog//mat-icon[contains(text(),'close')]"));
		}
	}
	
	public void details_updatePredicate(WebDriver driver) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(definition).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		driver.findElement(definition).sendKeys(Keys.chord(Keys.CONTROL, "x"));
		driver.findElement(definition).sendKeys("Algebras");

	}
	
	public void validate_update_predicate(WebDriver driver) {
		commFunc.Explicitywait(driver, Yes_btn);
		commFunc.Click(driver, Yes_btn);
		commFunc.Explicitywait(driver, By.xpath("//berd-confirmation//div//p[contains(text(),'BIO_Predicates has been created and required approval to update record')]"));
		if (driver.findElements(By.xpath("//berd-confirmation//div//p[contains(text(),'BIO_Predicates has been created and required approval to update record')]")).size() != 0) {
			String s = "Predicate sent to approval";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			commFunc.Click(driver, btn_ok);
		}
	}
	
	public void updated_approval_lexicon(WebDriver driver, long timed) throws FilloException {
		this.click_module(driver, "bio&lexicon");
		//this.click_module(driver,"bio&lexicon/predicate");
		commFunc.Click_btn(driver, "Read");
		String Designation = record.getField("Designation");
		commFunc.put_field_data(driver, "Search", Designation);
		if (driver.findElements(By.xpath("//tbody//td[contains(text(),'"+Designation+"')]")).size() != 0) {
			String s = "Lexicon data cannot deleted after approval of ticket!";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Data_Pass("updated_approval_lexicon", s, "FAIL", s, timed, 4, 15);
		} else {
			String s = "Lexicon data deleted after approval of ticket!";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			logger.log(LogStatus.PASS, s);
			commFunc.Data_Pass("updated_approval_lexicon", s, "PASS", "", timed, 1, 15);
		}
	
	}
	
	public void validate_delete_predicate(WebDriver driver) {
		commFunc.Explicitywait(driver, By.xpath("//berd-confirmation//div//p[contains(text(),'BIO_Predicates has been created and required approval to delete record')]"));
		if (driver.findElements(By.xpath("//berd-confirmation//div//p[contains(text(),'BIO_Predicates has been created and required approval to delete record')]")).size() != 0) {
			String s = "Connector sent for delete approval";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			commFunc.Click(driver, btn_ok);
		}
	}
	
	public void delete_approval_predicate(WebDriver driver, long timed) {
		this.click_module(driver, "bio&lexicon");
		this.click_module(driver,"bio&lexicon/predicate");
		commFunc.Click_btn(driver, "Read");
		commFunc.put_field_data(driver,"Search","algebra");
		if (driver.findElements(By.xpath("//tbody//td[contains(text(),'algebra')]")).size() != 0) {
			String s = "User can able to see deleted predicate";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Data_Pass("delete_approval_predicate", s, "FAIL", s, timed, 4, 15);
		} else {
			String s = "User cannot able to see deleted predicate";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			logger.log(LogStatus.PASS, s);
			commFunc.Data_Pass("delete_approval_predicate", s, "PASS","", timed, 1, 15);
		}
  }
	
	//---------------------------------------------Connector----------------------------------------------------------------------------------
	
	public void add_connector_details(WebDriver driver) {

		//String s = RandomStringUtils.randomAlphanumeric(5);
		commFunc.sendKeys(driver, By.xpath("//mat-label[text()='Enter Connector lexicon']//ancestor::mat-form-field//following-sibling::input"),s);

	}
 public void same_connector(WebDriver driver) {
		
		commFunc.put_field_data(driver,"Search", s);
		//commFunc.sendKeys(driver, By.xpath("//mat-label[text()='Enter Connector lexicon']//ancestor::mat-form-field//following-sibling::input"),s);
	}
 
 public void update_connector_details(WebDriver driver) {
		commFunc.put_field_data(driver,"Set designation","updatedC");
	}
	
	
	public void validate_addConnector(WebDriver driver,long timed) throws InterruptedException {
		commFunc.Explicitywait(driver, Yes_btn);
		commFunc.Click(driver, Yes_btn);
		Thread.sleep(2000);
		if(driver.findElements(By.xpath("//berd-confirmation//div//p[contains(text(),'Connector has been created successfully.')]")).size()!=0) {
			String s = "Connector Added successfully!";
			System.out.println(s);
			Thread.sleep(2000);
			commFunc.jclick(driver, By.xpath("//button//span[contains(text(),'OK')]"));
		}
		else {
			String s = "user cannot able to create connector";
			System.err.println(s);
			commFunc.Click(driver, By.xpath("//berd-confirmation-dialog//mat-icon[contains(text(),'close')]"));
		}
	}
	
	public void validate_update_connector(WebDriver driver) {
		commFunc.Explicitywait(driver, Yes_btn);
		commFunc.Click(driver, Yes_btn);
		commFunc.Explicitywait(driver, By.xpath("//berd-confirmation//div//p[contains(text(),'BIO_Connectors has been created and required approval to update record')]"));
		if (driver.findElements(By.xpath("//berd-confirmation//div//p[contains(text(),'BIO_Connectors has been created and required approval to update record')]")).size() != 0) {
			String s = "Connector sent to approval";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			commFunc.Click(driver, btn_ok);
		}
	}
	
	
	public void updated_approval_connector(WebDriver driver, long timed) {
		this.click_module(driver, "bio&lexicon");
		this.click_module(driver,"bio&lexicon/connector");
		commFunc.Click_btn(driver, "Read");
		this.click_Sorticon(driver);
		if (driver.findElements(By.xpath("//tbody//td[contains(text(),'updatedC')]")).size() != 0) {
			String s = "Connector updated via ticket";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			logger.log(LogStatus.PASS, s);
			commFunc.Data_Pass("updated_approval_connector", s, "PASS", "", timed, 1, 15);
		} else {
			String s = "user cannot able to update connector";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Click(driver, By.xpath("//berd-confirmation-dialog//mat-icon[contains(text(),'close')]"));
			commFunc.Data_Pass("updated_approval_connector", s, "FAIL", s, timed, 4, 15);
		}
 }
	
	public void validate_connector(WebDriver driver) {
		commFunc.Explicitywait(driver, Yes_btn);
		commFunc.Click(driver, Yes_btn);
		commFunc.Explicitywait(driver, By.xpath("//berd-confirmation//div//p[contains(text(),'BIO_Connectors has been created and required approval to delete record')]"));
		if (driver.findElements(By.xpath("//berd-confirmation//div//p[contains(text(),'BIO_Connectors has been created and required approval to delete record')]")).size() != 0) {
			String s = "Connector sent for delete approval";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			commFunc.Click(driver, btn_ok);
		}
	}
	
	public void delete_approval_connector(WebDriver driver, long timed) {
		this.click_module(driver, "bio&lexicon");
		this.click_module(driver,"bio&lexicon/connector");
		commFunc.Click_btn(driver, "Read");
		commFunc.put_field_data(driver,"Search","up");
		if (driver.findElements(By.xpath("//tbody//td[contains(text(),'updatedC')]")).size() != 0) {
			String s = "User can able to see deleted connector";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Data_Pass("delete_approval_connector", s, "FAIL", s, timed, 4, 15);
		} else {
			String s = "User cannot able to see deleted connector";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			logger.log(LogStatus.PASS, s);
			commFunc.Data_Pass("delete_approval_connector", s, "PASS","", timed, 1, 15);
		}
  }
	
	public void validate_flexibility_change(WebDriver driver,long timed) {
		commFunc.Click_btn(driver,"Yes");
		commFunc.Click(driver, btn_ok);
		commFunc.Explicitywait(driver, By.xpath("//mat-dialog-container//berd-flexibility-alert-dialog//div//h3[contains(text(),'You are being logged out because the Flexibility Level has been updated.')]"));
		if(driver.findElements(By.xpath("//mat-dialog-container//berd-flexibility-alert-dialog//div//h3[contains(text(),'You are being logged out because the Flexibility Level has been updated.')]")).size()!=0) {
			String s = "User can able to change the flexibility level successfully.";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			logger.log(LogStatus.PASS, s);
			commFunc.Click(driver, By.xpath("//button//span[contains(text(),'Ok')]"));
			commFunc.Data_Pass("delete_approval_connector", s, "PASS","", timed, 1, 15);
		}
	}
	
	public void validate_delete_lexicon_same_user(WebDriver driver,long timed) {
		commFunc.Explicitywait(driver, By.xpath("//berd-confirmation-dialog//div//p[contains(text(),'Tickter Creator cannot approve the ticket')]"));
		if(driver.findElements(By.xpath("//berd-confirmation-dialog//div//p[contains(text(),'Tickter Creator cannot approve the ticket')]")).size()!=0) {
			String s = "Ticket creator cannot approve the ticket";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			logger.log(LogStatus.PASS, s);
			commFunc.Click(driver, btn_ok);
			commFunc.Data_Pass("validate_delete_lexicon_same_user", s, "PASS","", timed, 1, 15);
			
		}
		else {
			String s = "Ticket creator can approve the ticket";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Click(driver, btn_ok);
			commFunc.Data_Pass("validate_delete_lexicon_same_user", s, "FAIL", s, timed, 4, 15);
		}
	}
	
	public void validate_added_flexibility(WebDriver driver,long timed) {
		commFunc.Explicitywait(driver, By.xpath("//berd-confirmation-dialog//div//p[contains(text(),'Data has been added Successfully')]"));
		if(driver.findElements(By.xpath("//berd-confirmation-dialog//div//p[contains(text(),'Data has been added Successfully')]")).size()!=0) {
			String s = "User can able to create flexibility successfully";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			logger.log(LogStatus.PASS, s);
			commFunc.Click(driver, btn_ok);
			commFunc.Data_Pass("validate_added_flexibility", s, "PASS","", timed, 1, 15);
		}
		else {
			String s = "User cannot able to create flexibility successfully";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Click(driver, btn_ok);
			commFunc.Data_Pass("validate_added_flexibility", s, "FAIL", s, timed, 4, 15);
		}
	}
	
	public void validate_update_flexibility(WebDriver driver,long timed) {
		commFunc.Explicitywait(driver, By.xpath("//berd-confirmation-dialog//div//p[contains(text(),'updated Successfully')]"));
		if(driver.findElements(By.xpath("//berd-confirmation-dialog//div//p[contains(text(),'updated Successfully')]")).size()!=0) {
			String s = "User can able to update flexibility successfully";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			logger.log(LogStatus.PASS, s);
			commFunc.Click(driver, btn_ok);
			commFunc.Data_Pass("validate_update_flexibility", s, "PASS","", timed, 1, 15);
		}
		else {
			String s = "User cannot able to update flexibility successfully";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Click(driver, btn_ok);
			commFunc.Data_Pass("validate_update_flexibility", s, "FAIL", s, timed, 4, 15);
		}
	}
	
	public void validate_delete_flexibility(WebDriver driver,long timed) {
		commFunc.Click_btn(driver,"Yes");
		commFunc.Explicitywait(driver, By.xpath("//berd-confirmation-dialog//div//p[contains(text(),'Data is Deleted Successfully')]"));
		if(driver.findElements(By.xpath("//berd-confirmation-dialog//div//p[contains(text(),'Data is Deleted Successfully')]")).size()!=0) {
			String s = "User can able to delete flexibility successfully";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			logger.log(LogStatus.PASS, s);
			commFunc.Click(driver, btn_ok);
			commFunc.Data_Pass("validate_delete_flexibility", s, "PASS","", timed, 1, 15);
		}
		else {
			String s = "User cannot able to delete flexibility successfully";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Click(driver, btn_ok);
			commFunc.Data_Pass("validate_delete_flexibility", s, "FAIL", s, timed, 4, 15);
		}
	}
	
	public void validate_details_flexibility(WebDriver driver,long timed) throws InterruptedException {
		Thread.sleep(3000);
		if(driver.findElements(By.xpath("//berd-flexibility-level-dialog//div//button[@disabled='true']//span[contains(text(),'Save')]")).size()!=0) {
			String s = "User can able to view the flexibility details successfully";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			logger.log(LogStatus.PASS, s);
			commFunc.Click(driver, By.xpath("//berd-flexibility-level-dialog//div//mat-icon[contains(text(),'close')]"));
			commFunc.Data_Pass("validate_details_flexibility", s, "PASS","", timed, 1, 15);
		}
		else {
			String s = "User cannot able to view the flexibility details successfully";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			commFunc.Click(driver, By.xpath("//berd-flexibility-level-dialog//div//mat-icon[contains(text(),'close')]"));
			commFunc.Data_Pass("validate_details_flexibility", s, "FAIL", s, timed, 4, 15);
		}
	}
}
