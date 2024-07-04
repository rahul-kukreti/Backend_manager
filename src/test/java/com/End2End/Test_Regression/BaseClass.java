package com.End2End.Test_Regression;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.End2End.PagesObjects.Backend_manager_ObjectPage;
import com.End2End.PagesObjects.CoordinateManager_ObjectPage;
import com.End2End.PagesObjects.Upalparameter_ObjectPage;
import com.End2End.PagesObjects.User_manager_OjectPage;
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
	public static Backend_manager_ObjectPage bio = new Backend_manager_ObjectPage();
	public static CoordinateManager_ObjectPage coordinate = new CoordinateManager_ObjectPage();
	public static Upalparameter_ObjectPage upal = new Upalparameter_ObjectPage();
	public static User_manager_OjectPage user = new User_manager_OjectPage();

	@Parameters({ "enviroment" })
	@BeforeSuite(description = "initializing Driver", alwaysRun = true)
	public void initializeDriver(String enviroment) throws InterruptedException {
		driver = browserSelect.openChromeIncongnito();
		driver.manage().window().maximize();
		driver.get("http://berd-internal-tool-dev2-env.s3-website.eu-west-3.amazonaws.com/auth");

	}

	@Parameters({ "testCaseID" })
	@BeforeSuite(description = "initializing DataInput", alwaysRun = true)
	public void initializeInpuData(String testCaseID) throws FilloException {

		// Get data from sheet

		record = input.datafromsheet(sheetName, testCaseID);
	}

}
