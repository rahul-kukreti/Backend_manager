package com.End2End.PagesObjects;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.End2End.Test_Regression.BaseClass;
import com.relevantcodes.extentreports.LogStatus;

public class BioLexicon_ObjectPage extends BaseClass {
	
	public String s = RandomStringUtils.randomAlphanumeric(5);

	By Yes_btn = By.xpath("//button//span[contains(text(),'Yes')]");
	By btn_ok = By.xpath("//button//span[contains(text(),'OK')]");
	By sort_id = By.xpath("//table//thead//tr//th//div[contains(text(),'Id')]");
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

	public void updated_approval_lexicon(WebDriver driver, String value, long timed) {
		this.click_module(driver, "bio&lexicon");
		commFunc.Click_btn(driver, "Read");
		this.click_Sorticon(driver);
		if (driver.findElements(By.xpath("//tbody//td[contains(text(),'" + value + "')]")).size() != 0) {
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
	
	public void updated_approval_predicate(WebDriver driver, long timed) {
		this.click_module(driver, "bio&lexicon");
		this.click_module(driver,"bio&lexicon/predicate");
		commFunc.Click_btn(driver, "Read");
		this.click_Sorticon(driver);
		if (driver.findElements(By.xpath("//tbody//td[contains(text(),'Algebras')]")).size() != 0) {
			String s = "Predicate updated via ticket";
			System.out.println(s);
			logger.log(LogStatus.INFO, s);
			logger.log(LogStatus.PASS, s);
			commFunc.Data_Pass("updated_approval_predicate", s, "PASS", "", timed, 1, 15);
		} else {
			String s = "user cannot able to update predicate";
			System.err.println(s);
			logger.log(LogStatus.FAIL, s);
			//commFunc.Click(driver, By.xpath("//berd-confirmation-dialog//mat-icon[contains(text(),'close')]"));
			commFunc.Data_Pass("updated_approval_predicate", s, "FAIL", s, timed, 4, 15);
		}
	
	}
	
	public void validate_delete_predicate(WebDriver driver) {
		commFunc.Explicitywait(driver, Yes_btn);
		commFunc.Click(driver, Yes_btn);
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
}
