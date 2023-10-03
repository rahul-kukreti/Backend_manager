package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.End2End.Test_Regression.BaseClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentManager extends BaseClass{

	
private static ExtentReports extent;
public static String dirPath = "";

public static String screenshotPath;
public static String screenshotName;
	
	
	public static ExtentReports getInstance() {
		
		if(extent==null){
			dirPath =System.getProperty("user.dir") + "/ExtentReports/";
			new File(dirPath).mkdir();
			extent = new ExtentReports(dirPath + File.separator + "TestAutomationReport.html", true);
			extent.loadConfig(new File(System.getProperty("user.dir")+"\\src\\com\\Test\\resources\\ReportsConfig.xml"));
		}
		return extent;
		//+ new SimpleDateFormat("ddMMyyyy_hh.mm.ss").format(new Date());
		}
	
	public static void captureScreenshot(String screenshotNameDescription) throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String imgPath = dirPath + "/" + screenshotNameDescription + ".png";
		Date d = new Date();
		//screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		FileUtils.copyFile(scrFile,new File(imgPath));

	}
	
	 public static void takeScreenShot(WebDriver temp, String description) throws
	  IOException {
	  
	  File imgFile = ((TakesScreenshot) temp).getScreenshotAs(OutputType.FILE);
	  String imgPath = dirPath + "/" + description + ".png";
	  FileUtils.copyFile(imgFile, new File(imgPath)); String encodedBase64 = null;
	  FileInputStream fileInputStreamReader = null; 
	  try { fileInputStreamReader =
	  new FileInputStream(imgFile); 
	  byte[] bytes = new byte[(int)
	  imgFile.length()]; 
	  fileInputStreamReader.read(bytes); 
	  encodedBase64 = new
	  String(Base64.encodeBase64(bytes));
	  
	  } catch (IOException e) 
	  { e.printStackTrace(); 
	  } logger.log(LogStatus.PASS,
	  description + logger.addScreenCapture("data:image/png;base64," +encodedBase64));
	  
	  }
}
