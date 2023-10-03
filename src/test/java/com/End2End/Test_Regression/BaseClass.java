package com.End2End.Test_Regression;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.utility.BrowserFactory;
import com.utility.CommonFunctions;
import com.utility.DataInput;
import com.utility.ExtentManager;

public class BaseClass {
	

	public static WebDriver driver;
   public ArrayList list = new ArrayList();
// ExtentReport;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest logger;
	public static Recordset record;
	String sheetName = "Backend";
	
	public static CommonFunctions commFunc = new CommonFunctions();
	public static BrowserFactory browserSelect = new BrowserFactory();
	public static DataInput input = new DataInput();
//	public static PredicateConn_ObjectPage predicate = new PredicateConn_ObjectPage();
//	public static Coordinate_ObjectPage coordinate = new Coordinate_ObjectPage();
//	public static ParamterManage_ObjectPage upal = new ParamterManage_ObjectPage();
//	public static AxiomRelation_ObjectPage axiom = new AxiomRelation_ObjectPage();
//	public static SourceOrder_ObjectPage source = new SourceOrder_ObjectPage();
//	public static DefinitionBook_ObjectPage book =  new DefinitionBook_ObjectPage();
//	public static FunctionsLibrary_ObjectPage functions=new FunctionsLibrary_ObjectPage();
//	public static Delete_modules_ObjectPage deleteModule= new Delete_modules_ObjectPage();
//	public static SpecificationManager_ObjectPage specific = new SpecificationManager_ObjectPage();
	
	
	

	@Parameters({ "enviroment" })
	@BeforeSuite(description = "initializing Driver", alwaysRun = true)
	public void initializeDriver(String enviroment) throws InterruptedException {
		driver = browserSelect.openChromeIncongnito();
		driver.manage().window().maximize();
		driver.get("http://berd-internal-tools-dev-env.s3-website.eu-west-3.amazonaws.com/auth");

	}
	
	@Parameters({ "testCaseID" })
	@BeforeSuite(description = "initializing DataInput", alwaysRun = true)
	public void initializeInpuData(String testCaseID) throws FilloException {

		// Get data from sheet
		
			record = input.datafromsheet(sheetName, testCaseID);
	}

}
