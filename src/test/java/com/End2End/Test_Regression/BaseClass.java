package com.End2End.Test_Regression;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.End2End.PagesObjects.BioLexicon_ObjectPage;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.utility.BrowserFactory;
import com.utility.CommonFunctions;
import com.utility.ConfigReader;
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

	//Classes name
	public static CommonFunctions commFunc = new CommonFunctions();
	public static BrowserFactory browserSelect = new BrowserFactory();
	public static DataInput input = new DataInput();
	public static ConfigReader conf = new ConfigReader();
	public static BioLexicon_ObjectPage bio = new BioLexicon_ObjectPage();
	
	
	
	

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
