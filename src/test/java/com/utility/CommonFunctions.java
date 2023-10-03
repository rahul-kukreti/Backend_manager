package com.utility;

import org.json.simple.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.End2End.Test_Regression.BaseClass;
import com.codoid.products.exception.FilloException;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import static io.restassured.RestAssured.*;

public class CommonFunctions extends BaseClass {
	// testing
	WebDriver driver;

	JavascriptExecutor js;
	SoftAssert softAssert = new SoftAssert();

	static SoftAssert objSoftAssert = new SoftAssert();
	
	//Login details one
	By username = By.xpath("//mat-label[contains(text(),'Enter your email')]//ancestor::mat-form-field//following-sibling::input[@formcontrolname='identifier']");
	By password = By.xpath("//mat-label[contains(text(),'Password')]//ancestor::mat-form-field//following-sibling::input[@formcontrolname='password']");
	By login_btn = By.xpath("//button//span[contains(text(),'Login')]");
	By approver_placeholder = By.xpath("//mat-label[text()='nbrOfApprovesToApplyAction']//ancestor::mat-form-field//following-sibling::input");

	public void assertElementsExistence(WebDriver driver, By xpath) {
		List<WebElement> elements = driver.findElements(xpath);

		if (elements.size() == 0) {
			softAssert.assertTrue(true);

		} else {
			softAssert.assertTrue(false, xpath + " is/are NOT hidden");
		}

		softAssert.assertAll();
	}

	public void put_field_data(WebDriver driver, String field_data, String value) {
		try {
			this.Explicitywait(driver,By.xpath("//mat-label[text()='" + field_data + "']//ancestor::mat-form-field//following-sibling::input"));
			if (driver.findElements(By.xpath("//mat-label[text()='" + field_data + "']//ancestor::mat-form-field//following-sibling::input")).size() != 0) {
				WebElement ele = driver.findElement(By.xpath("//mat-label[text()='" + field_data + "']//ancestor::mat-form-field//following-sibling::input"));

				ele.sendKeys(value);

			}
		} catch (TimeoutException e) {

			throw new TimeoutException("element not found", e);
		}
	}

	public void dropdown_data(WebDriver driver, String name, String value) {
		this.Explicitywait(driver, By.xpath("//mat-label[contains(text(),'" + name + "')]"));
		
		try {

			if (driver.findElements(By.xpath("//mat-label[contains(text(),'" + name + "')]")).size() != 0) {
				this.scrollIntoElement(driver,By.xpath("//mat-label[contains(text(),'" + name + "')]"));
				this.jclick(driver, By.xpath("//mat-label[contains(text(),'" + name + "')]"));
				this.jclick(driver,
						By.xpath("//div[@role='listbox']//child::mat-option//span[contains(text(),'" + value + "')]"));

			}
		} catch (TimeoutException e) {

			throw new TimeoutException("element not found", e);
		}
	}

	public void put_textarea(WebDriver driver, String name, String value) {
		try {
		this.Explicitywait(driver, By.xpath("//mat-label[text()='" + name + "']//ancestor::mat-form-field//following-sibling::textarea"));
		if (driver.findElements(By.xpath("//mat-label[text()='" + name + "']//ancestor::mat-form-field//following-sibling::textarea")).size() != 0) {
			WebElement ele = driver.findElement(By.xpath("//mat-label[text()='" + name + "']//ancestor::mat-form-field//following-sibling::textarea"));

			ele.sendKeys(value);

		}
		}
		catch(TimeoutException e) {
			throw new TimeoutException("element not found", e);
		}
	}
	
	public void click_dropdown(WebDriver driver, String name, String value) {
		this.Explicitywait(driver, By.xpath("//mat-select//span[contains(text(),'"+name+"')]"));
		this.Click(driver, By.xpath("//mat-select//span[contains(text(),'"+name+"')]"));
		this.Click(driver, By.xpath("//div[@role='listbox']//child::mat-option//span[contains(text(),'"+value+"')]"));
	}

	public void select_menu(WebDriver driver, String menu) {
		this.Explicitywait(driver,By.xpath("//berd-nav-menu-widget//div[@class='berd-tool-action-container']//div[contains(text(),'"+ menu + "')]"));
		try {
			if (driver.findElements(By.xpath("//berd-nav-menu-widget//div[@class='berd-tool-action-container']//div[contains(text(),'"+ menu + "')]")).size() != 0) {
				this.Click(driver, By.xpath("//berd-nav-menu-widget//div[@class='berd-tool-action-container']//div[contains(text(),'"+ menu + "')]"));
			}
		} catch (TimeoutException e) {
			throw new TimeoutException("element not found", e);
		}
	}

	public void Click_btn(WebDriver driver, String name) {
		this.Explicitywait(driver, By.xpath("//button//span[contains(text(),'" + name + "')]"));
		this.Click(driver, By.xpath("//button//span[contains(text(),'" + name + "')]"));

	}

	public void click_coordinate(WebDriver driver, String name) {

		this.Explicitywait(driver, By.xpath("//mat-tree-node//button//following::h3[contains(text(),'" + name + "')]"));
		this.Click(driver, By.xpath("//mat-tree-node//button//following::h3[contains(text(),'" + name + "')]"));
	}

	public void click_coordinate_icon(WebDriver driver, String name) {

		this.Explicitywait(driver, By.xpath("//mat-tree-node//button//following::h3[contains(text(),'" + name+ "')]//ancestor::mat-tree-node//following::mat-icon[contains(text(),'chevron_right')]"));
		this.jclick(driver, By.xpath("//mat-tree-node//button//following::h3[contains(text(),'" + name+ "')]//ancestor::mat-tree-node//following::mat-icon[contains(text(),'chevron_right')]"));
	}

	/**
	 * Switches to the next tab
	 *
	 */
	// ---------------------------switchTabs----------------
	public void switchTabs() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String currentHandle = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				// Switch to the opened tab
				driver.switchTo().window(actual);
			}
		}
	}

	public void scrollUntilAllElementIsVisible(WebDriver driver, By elementsXpath, By vScrollXpath) {
		int elementCount = driver.findElements(elementsXpath).size();

		while (true) {
			driver.findElement(vScrollXpath).sendKeys(Keys.PAGE_DOWN);

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int newElementCount = driver.findElements(elementsXpath).size();
			if (newElementCount == elementCount) {
				break;
			}
			elementCount = driver.findElements(elementsXpath).size();
		}

	}

	// <------------------------------Select DropDown Values----------------------->

	public void selectDropdown(WebDriver driver, By Locator, String value) {
		clicabilityWait(driver, Locator);
		List<WebElement> list = driver.findElements(Locator);

		for (WebElement ele : list) {

			System.out.println("Values " + ele.getAttribute("innerHTML"));

			if (ele.getAttribute("innerHTML").contains(value)) {

				ele.click();
				break;
			}
		}
	}

	public void SelectValueFromDropdown(WebDriver driver, By Xpath, String VisabuleText) {
		Select Locater = new Select(driver.findElement(Xpath));
		Locater.selectByVisibleText(VisabuleText);

	}

	// <-------------------------Move To Element location------------------------>

	public void MoveToElement(WebDriver Driver, By locater) {
		Actions actions = new Actions(driver);
		WebElement Element = driver.findElement(locater);
		actions.moveToElement(Element);
		actions.clickAndHold();
		// actions.moveToElement(Element);
		actions.release().perform();
	}
	// <-------------------------Move To Element location------------------------>

	public void actionsClick(WebDriver driver, By locater) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locater)).click().perform();
	}

	// <--------------Normal Click Method-------------------->

	public void Click(WebDriver driver, By Locator) {
		try {
			this.clicabilityWait(driver, Locator);
			driver.findElement(Locator).click();

		} catch (Exception e) {
			try {
				jclick(driver, Locator);
			} catch (Exception f) {
				Actions action = new Actions(driver);

				action.moveToElement(driver.findElement(Locator)).click().perform();

			}
		}
	}

	// <---------------------Click Element By using JavaScript
	// Executer-------------------->
	public void jclick(WebDriver driver, By Locator) {
		this.Explicitywait(driver, Locator);
		WebElement element = driver.findElement(Locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);

	}

	// <---------------------------Send Data into Locater With Enter
	// Key-------------------------------->

	public void sendKeyswithEnter(WebDriver driver, By Locator, String value) {

		WebElement element = driver.findElement(Locator);
		element.sendKeys(value);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		element.sendKeys(Keys.ENTER);

	}

	// <---------------------------Send Data into
	// Locater-------------------------------->
	public void sendKeys(WebDriver driver, By Locator, String value) {
		this.Explicitywait(driver, Locator);
		WebElement element = driver.findElement(Locator);
		element.sendKeys(value);
	}

	// <----------------------Send Data into Locater by using JavaScript
	// Executer---------------->

	public void jsendkeyes(WebDriver driver, By Locator, String EnterValue) {
		WebElement element = driver.findElement(Locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String val = "Arguments[0].value='" + EnterValue + "';";
		js.executeScript(val, element);
	}

	// <---------------Get Attribute Value Method------------------->

	public String ElementAttributeGetText(WebDriver driver, By Locator, String AttributeName) {
		String Val = driver.findElement(Locator).getAttribute(AttributeName);
		return Val;

	}

	public void CloseBrowserTab() {
		driver.close();
	}

	// <----------------Explicity Wait-------------------------->
	public void Explicitywait(WebDriver driver, By WebElement) {
		try {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(WebElement));
		}
		catch(TimeoutException e) {
		  String s = e.toString();
			System.out.println(e);
		}
	}

	public void explicitywaitInvisibility(WebDriver driver, By WebElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(WebElement));
	}

	public void listWaitGreaterThan(WebDriver driver, By WebElement, int count) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(WebElement, count));
	}

	public void listWaitEqual(WebDriver driver, By WebElement, int count) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.numberOfElementsToBe(WebElement, count));
	}

	// <----------------clicability Wait-------------------------->
	public void clicabilityWait(WebDriver driver, By WebElement) {
		this.Explicitywait(driver, WebElement);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(WebElement));
	}

	// <---------------TextVisibilitycheck-------------------------->
	public void textvisibilityCheck(WebDriver driver, By WebElement, String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(WebElement, text));
	}

	public String GetCurrentDate(String ReqDateFormate) {

		DateFormat dateFormat = new SimpleDateFormat(ReqDateFormate);
		Date date = new Date();
		String date1 = dateFormat.format(date);

		return date1;

	}

	public boolean isElementInteractable(WebDriver driver, By element) {
		try {
			WebDriverWait waitDriver = new WebDriverWait(driver, Duration.ofSeconds(5));

			waitDriver.until(ExpectedConditions.elementToBeClickable(driver.findElement(element)));
//			if(driver.findElement(element).isDisplayed())
//			{
			System.out.println("Multiple tabs are present,....");
//			}
		} catch (Exception e) {
			System.out.println("Continue.....");
			return false;

		}
		return true;
	}

	public void ScrollDown(int scrollpixel) {
		try {
			JavascriptExecutor myExecutor = ((JavascriptExecutor) driver);
			myExecutor.executeScript("window.scrollBy(0," + scrollpixel + ")", "");
			Thread.sleep(2000);
			return;
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}
	}

	public void scrollIntoElement(WebDriver driver, By element) {
		try {
			Thread.sleep(500);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(element));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// <--------------------File Uploading------------------->
	public void fileUpload(WebDriver driver, String fileDir) {
		By inputFileDirField = By.xpath("//input[contains(@type,'file')]");
		this.Explicitywait(driver, inputFileDirField);
		this.sendKeys(driver, inputFileDirField, fileDir);
	}

	// <---------------Convert Date Formate--------------------------->

	public String DateFormateConvert(String PrasentDateFormate, String ExpectedDateFormate, String Valu)
			throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(PrasentDateFormate);
		Date myDate = dateFormat.parse(Valu);

		SimpleDateFormat dateFormat2 = new SimpleDateFormat(ExpectedDateFormate);
		String Result = dateFormat2.format(myDate);
		System.out.println("*********Date Formate Changed Successfully*******" + "  " + Result);
		return Result;

	}

	// ------------Get System Time and Date-----------
	public String getSystemDateTime() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// get current date time with Date()
		Date date = new Date();

		// Now format the date
		String date1 = dateFormat.format(date.getTime());

		// Print the Date
		
//		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//		   LocalDateTime now = LocalDateTime.now();
		return date1;

	}

	public String DateTimeWeekly() {
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String date = dateFormat2.format(cal.getTime());
		return date;

	}

	public String DateTimemonthly_Start() {
		DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		String date = dateFormat3.format(cal.getTime());
		return date;

	}

	public String DateTimemonthly_End() {
		DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		String date = dateFormat3.format(cal.getTime());
		return date;

	}

	public String last_14_days() {
		DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -14);
		String date = dateFormat3.format(cal.getTime());
		return date;
	}

//	public void add_data_simple(WebDriver driver) {
//		upal.click_dropdown(driver, "PLURALITY", "SINGULAR");
//
//		// For selecting adopted variable value
//		upal.click_parameter_dropdown(driver, "ADOPTED VARIABLE");
//		upal.filter(driver);
//		upal.click_coordinate_paramter_icon(driver, "what is");
//		upal.click_coordinate_paramter_icon(driver, "numeric");
//		upal.click_coordinate_paramter_icon(driver, "numeric  simple");
//		upal.click_coordinate(driver, "a Road");
//
//		// For selecting adopted subject value
//		upal.click_parameter_dropdown(driver, "ADOPTED SUBJECT");
//		upal.filter(driver);
//		upal.click_coordinate_paramter_icon(driver, "subject");
//		upal.click_coordinate_paramter_icon(driver, "resource");
//		upal.click_coordinate(driver, "a Road");
//
//		// For selecting adopted function value
//		upal.click_parameter_dropdown(driver, "ADOPTED EV_F TYPE");
//		upal.filter(driver);
//		upal.click_coordinate_paramter_icon(driver, "internal");
//		upal.click_coordinate_paramter_icon(driver, "free value");
//		upal.click_coordinate(driver, "a Road");
//
//		// For selecting ranges
//		upal.click_parameter_dropdown(driver, "C1 RANGES");
//		upal.ranges_paramter(driver, "integer");
//
//		commFunc.Click_btn(driver, "SUBMIT");
//	}

//	public void select_data(WebDriver driver) {
//		// Search by variable value
//		upal.click_parameter_dropdown(driver, "SEARCH BY VARIABLE");
//		upal.filter(driver);
//		upal.click_coordinate_paramter_icon(driver, "what is");
//		upal.click_coordinate_paramter_icon(driver, "numeric");
//		upal.click_coordinate_paramter_icon(driver, "numeric  simple");
//		upal.click_coordinate(driver, "a Road");
//
//		// Search by subject value
//		upal.click_parameter_dropdown(driver, "SEARCH BY SUBJECT");
//		upal.filter(driver);
//		upal.click_coordinate_paramter_icon(driver, "subject");
//		upal.click_coordinate_paramter_icon(driver, "resource");
//		upal.click_coordinate(driver, "a Road");
//
//		// Search by function value
//		upal.click_parameter_dropdown(driver, "SEARCH BY EV_F TYPE");
//		upal.filter(driver);
//		upal.click_coordinate_paramter_icon(driver, "internal");
//		upal.click_coordinate_paramter_icon(driver, "free value");
//		upal.click_coordinate(driver, "a Road");
//
//		// Search by Ranges
//		upal.click_dropdown(driver, "ADOPTED UNIT", "metre");
//
//		commFunc.Click_btn(driver, "Apply Filter");
//	}
	
//	public void select_function_parameter(WebDriver driver) throws FilloException {
//		
//		String Designation = record.getField("Function_Designation");
//		commFunc.dropdown_data(driver, " Function-Draw", Designation);
//		
//		String fun_type = record.getField("Function_type");
//		upal.click_dropdown(driver, "Function Type", fun_type);
//		
//		String fun_nature = record.getField("Function_Nature");
//		upal.click_dropdown(driver, "Function Nature", fun_nature);
//		
//		String upal_parametr = record.getField("Search");
//		commFunc.dropdown_data(driver, "Upal Activation Parameter", upal_parametr);
//		
//		String activation_fun = record.getField("cost_selection");
//		commFunc.dropdown_data(driver,"Activation Function", activation_fun);
//	}
//	
	
	//Added line for Free Variable----------------
//public void select_FreeVariable_parameter(WebDriver driver) throws FilloException {
//		
//		
//		//String fun_type = record.getField("Function_type");
//		upal.click_dropdown(driver, "Function Type","engine");
//		
//		//String fun_nature = record.getField("Function_Nature");
//		upal.click_dropdown(driver, "Function Nature","approach");
//		
//		String math_type = record.getField("Math_Type");
//		commFunc.dropdown_data(driver, "MATH TYPE", math_type);
//		
//		
//		
//		
//		
//		String knowledgeDomain = record.getField("Knowledge_Domain");
//		//upal.click_dropdown(driver, "Knowledge Domain", knowledgeDomain);
//		//Remove these below two lines when dropdown is enabled 
//		By knowledgeDomainxpath= By.xpath("//mat-label[text()=' Knowledge Domain']");
//		commFunc.Click(driver, knowledgeDomainxpath);
//		By knowledgeXpath= By.xpath("//input[@placeholder='SELECT KNOWLEDGE DOMAIN']");
//		commFunc.sendKeys(driver, knowledgeXpath, knowledgeDomain);
//		
//
//		String function_content = record.getField("Function_content");
//		commFunc.put_textarea(driver, "FUNCTION CONTENT", function_content);
//		
//		
//	}


//public void select_FreeVariable_withoutKnowledge(WebDriver driver) throws FilloException {
//	
//	
//	//String fun_type = record.getField("Function_type");
//	upal.click_dropdown(driver, "Function Type","engine");
//	
//	//String fun_nature = record.getField("Function_Nature");
//	upal.click_dropdown(driver, "Function Nature","approach");
//	
//	String math_type = record.getField("Math_Type");
//	commFunc.dropdown_data(driver, "MATH TYPE", math_type);
//	
//	
//	String function_content = record.getField("Function_content");
//	commFunc.put_textarea(driver, "FUNCTION CONTENT", function_content);
//	
//	
//}

	
	
	
	
	
	
	
	//--------------------------------------Aggregate resource details----------------------------------------------
	
	public void Aggr_details(WebDriver driver) throws FilloException {
		String price_struc = record.getField("Price_structure");
		commFunc.dropdown_data(driver, "Price Structure", price_struc);
		
		String unit = record.getField("Default_unit");
		commFunc.dropdown_data(driver, "homogPriceU_UnitID", unit);
		
		String cost_selection = record.getField("cost_selection");
		commFunc.dropdown_data(driver, "costSelection_E_EnginesID", cost_selection);
		
		String cost_Approach = record.getField("cost_selection");
		commFunc.dropdown_data(driver, "costApproach_E_EnginesID", cost_Approach);
		
		String contract_option = record.getField("Items");
		commFunc.dropdown_data(driver, "contractOptionsUPAL_ItemsID", contract_option);
		
		String exclusion = record.getField("Items");
		commFunc.dropdown_data(driver, "exclusionsUPAL_ItemsID", exclusion);
		
		String min_transact = record.getField("Items");
		commFunc.dropdown_data(driver, "minTransactionUPAL_ItemsID", min_transact);
		
		String sintax = record.getField("Items");
		commFunc.dropdown_data(driver, "minTransactionSintaxUPAL_ItemsID", sintax);
		
		String additional = record.getField("Items");
		commFunc.dropdown_data(driver, "additionalRequestsUPAL_ItemsID", additional);
	}
	
//	public void axiom_details(WebDriver driver) throws InterruptedException {
//		//For C1 varaible
//				upal.click_dropdown(driver, "Exp1 Var1", "C1");
//				upal.click_dropdown(driver, "Exp1 Operator", "Son_Of");
//			    upal.click_parameter_dropdown(driver, "UPAL_C1");
//				Thread.sleep(3000);
//				upal.filter(driver);
//		    	upal.click_coordinate_paramter_icon(driver, "what is");
//				upal.click_coordinate_paramter_icon(driver, "numeric");
//				 upal.click_parameter_dropdown(driver, "UPAL_C1");
//				upal.click_coordinate_paramter_icon(driver, "numeric  simple");
//				upal.click_coordinate(driver, "a Road");
//				
//				//For c2 variable
//				upal.click_dropdown(driver, "Exp2 Var1", "C2");
//				upal.click_dropdown(driver, "Exp2 Operator", "Son_Of");
//			    upal.click_parameter_dropdown(driver, "UPAL_C2");
//				Thread.sleep(3000);
//				upal.filter(driver);
//		    	upal.click_coordinate_paramter_icon(driver, "subject");
//				upal.click_coordinate_paramter_icon(driver, "resource");
//				upal.click_parameter_dropdown(driver, "UPAL_C2");
//				upal.click_coordinate(driver, "a Road");
//				
//				//For c3 varaible
//				upal.click_dropdown(driver, "Exp3 Var1", "C3");
//				upal.click_dropdown(driver, "Exp3 Operator", "Son_Of");
//			    upal.click_parameter_dropdown(driver, "UPAL_C3");
//				Thread.sleep(3000);
//				upal.filter(driver);
//		    	upal.click_coordinate_paramter_icon(driver, "internal");
//				upal.click_coordinate_paramter_icon(driver, "free");
//				//upal.click_parameter_dropdown(driver, "UPAL_C3");
//				upal.click_coordinate(driver, "a Road");
//	}
	
	public void specific_source(WebDriver driver) throws FilloException {
		this.Click_btn(driver, "Create");
		this.dropdown_data(driver, "SO_TypifiedAggregatedID*", "a Road");
		this.Click_btn(driver, "Define Designation");
		String search = record.getField("Search");
		this.dropdown_data(driver, "BIO CONCEPT", search);
		this.Click_btn(driver, "Add");
		this.Click_btn(driver, "Save");
		commFunc.Click_btn(driver, "Yes");
		commFunc.Click(driver,By.xpath("//button//span[contains(text(),'OK')]"));
		this.Click_btn(driver, "Create");
		
	}
	
	
	     public void login_User1(WebDriver driver) {
			commFunc.sendKeys(driver, username, (conf.getlogin()));
			commFunc.sendKeys(driver, password, (conf.getpassword()));
			commFunc.Explicitywait(driver, login_btn);
			commFunc.Click(driver, login_btn);
	}
	     
	     public void login_User2(WebDriver driver) {
				commFunc.sendKeys(driver, username, (conf.getlogin1()));
				commFunc.sendKeys(driver, password, (conf.getpassword1()));
				commFunc.Explicitywait(driver, login_btn);
				commFunc.Click(driver, login_btn);
		}
	     
	     
	     public void logout(WebDriver driver) {
	    	 this.Explicitywait(driver, By.xpath("//berd-sidebar//mat-toolbar//button//mat-icon[contains(text(),'person')]"));
	    	 this.Click(driver,By.xpath("//berd-sidebar//mat-toolbar//button//mat-icon[contains(text(),'person')]"));
	    	 this.Click_btn(driver,"Logout");
	     }
	     
	     public void approver_module(WebDriver driver) {
	    	 bio.click_module(driver,"bm");
	    	 bio.click_module(driver,"bm/flexibility-mgr");
	    	 this.click_dropdown(driver, "Flexibility Level", "Flexibility Board");
	    	 this.set_approver_update(driver);
	    	 try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 this.set_approver_delete(driver);
	     }
	     
	     public void set_approver_update(WebDriver driver) {
	    	 this.Explicitywait(driver, By.xpath("(//tbody//mat-select)[1]"));
	    	 this.Click(driver, By.xpath("(//tbody//mat-select)[1]"));
	    	 this.Click(driver,By.xpath("//div[@role='listbox']//child::mat-option//span[contains(text(),'Update')]"));
	    	 this.click_dropdown(driver, "false", "true");
	    	 driver.findElement(approver_placeholder).sendKeys(Keys.chord(Keys.CONTROL, "a"));
	    	 driver.findElement(approver_placeholder).sendKeys(Keys.chord(Keys.CONTROL, "x"));
	    	 this.put_field_data(driver,"nbrOfApprovesToApplyAction","1");
	    	 this.Click_btn(driver,"Save");
	    	 this.Click_btn(driver,"Yes");
	    	 this.Click_btn(driver,"OK");
	     }
	     
	     public void set_approver_delete(WebDriver driver) {
	    	 this.Explicitywait(driver, By.xpath("(//tbody//mat-select)[6]"));
	    	 this.Click(driver, By.xpath("(//tbody//mat-select)[6]"));
	    	 this.Click(driver,By.xpath("//div[@role='listbox']//child::mat-option//span[contains(text(),'Update')]"));
	    	 this.click_dropdown(driver, "false", "true");
	    	 driver.findElement(approver_placeholder).sendKeys(Keys.chord(Keys.CONTROL, "a"));
	    	 driver.findElement(approver_placeholder).sendKeys(Keys.chord(Keys.CONTROL, "x"));
	    	 this.put_field_data(driver,"nbrOfApprovesToApplyAction","1");
	    	 this.Click_btn(driver,"Save");
	    	 this.Click_btn(driver,"Yes");
	    	 this.Click_btn(driver,"OK");
	    	 
	     }
	     
	     public void ticket_manager(WebDriver driver) {
	    	 bio.click_module(driver,"bm");
	    	 bio.click_module(driver,"bm/ticket-mgr");
	    	 this.click_dropdown(driver, "Ticket Status", "Open");
	     }
	     
	     public void update_ticket_module(WebDriver driver,String name) {
	    	 this.ticket_manager(driver);
	    	 this.Click(driver, By.xpath("//tbody//tr//td[contains(text(),'"+name+"')]//following-sibling::td[1]"));
	    	 this.Click(driver, By.xpath("//table//thead//tr//th//div[contains(text(),'Ticket ID')]"));
	    	 this.Click(driver, By.xpath("//table//thead//tr//th//div[contains(text(),'Ticket ID')]"));
	    	 this.Click(driver, By.xpath("(//tbody[@role='rowgroup']//tr)[1]"));
	    	 this.Click_btn(driver,"Approve");
	    	 commFunc.Click_btn(driver,"Select Checkboxes");
	 		commFunc.Click_btn(driver,"Approve Ticket");
	 		 this.Click_btn(driver,"Yes");
	 		 this.Explicitywait(driver, By.xpath("//berd-confirmation//div//p[contains(text(),'Action is being approved and ticket is closed now')]"));
	 		 if(driver.findElements(By.xpath("//berd-confirmation//div//p[contains(text(),'Action is being approved and ticket is closed now')]")).size()!=0) {
	 			this.Click_btn(driver,"OK");
	 		 }
	     }
	
	
	//-------------------------------Api work-------------------------------------------------------------------
	
	
	//public static void login_api() {
		
//		baseURI = "http://ec2-13-39-150-197.eu-west-3.compute.amazonaws.com:1338/api/";
//		 
//		RequestSpecification request = RestAssured.given();
//		String payload =  "{\r\n"
//				+ "    \"identifier\": \"rahul.agarwal@mail.vinove.com\",\r\n"
//				+ "    \"password\": \"s47ooSZ6$Rmk#@Dr\"\r\n"
//				+ "}";
//		request.header("Content-Type","application/json");
//		Response res = request.body(payload).post("auth/backend/login");
//		//res.prettyPrint();
//		
//		String JsonString = res.getBody().asString();
//		String jwt = JsonPath.from(JsonString).get("jwt");
//	         
//		            System.out.println(jwt);
//	}
//	
//	public static void main(String args[]) {
//		login_api();
//	}
	
	public long start_time() {
		long start = System.currentTimeMillis()/1000;
		//System.out.println("start_time: "+ start);
		return start;
	}
	
	public long end_time() {
		long end = System.currentTimeMillis()/1000;
		//System.out.println("end_time: " + end);
		return end;
	}
	
	public long time_dif(long start_time,long end_time) {
		
		long time_dif = end_time-start_time;
		//System.out.println(time_dif);
		return time_dif;
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void Data_Pass(String f_name,String fun,String status,String E_description,long time,int sev_value,int mod_name) {
		
		
		JSONObject request =  new JSONObject();
		
		 request.put("featuresName",""+f_name+"");
		 request.put("functionality",""+fun+"");
		request.put("status",""+status+"");
		request.put("errorDescription", ""+E_description+"");
		request.put("duration",time);
		request.put("severityCodeID",sev_value);
		request.put("moduleId",mod_name);
		
		//String s1 = request.toJSONString();
		
		list.add(request);
		//System.out.println(list);
		
	    }
	
	public void api_hit() {
		
		baseURI = "http://ec2-13-39-150-197.eu-west-3.compute.amazonaws.com:1338/api/";
		
		
		 
		RequestSpecification request = RestAssured.given();
		String payload =  "{\r\n"
				+ "    \"identifier\": \"rahul.agarwal@mail.vinove.com\",\r\n"
				+ "    \"password\": \"s47ooSZ6$Rmk#@Dr\"\r\n"
				+ "}";
		request.header("Content-Type","application/json");
		Response res = request.body(payload).post("auth/backend/login");
		String JsonString = res.getBody().asString();
		String jwt = JsonPath.from(JsonString).get("jwt");
		
		request.header("Authorization","Bearer "+jwt)
		       .header("Flexibility",1)
		       .header("Content-Type","application/json")
		       .body(list)
		       .post("ate-test-detail/create-ate-test-detail")
			   .then()
			   .statusCode(200).log().all();
		
		//System.out.println(jwt);
		//System.out.println(list);

}
}