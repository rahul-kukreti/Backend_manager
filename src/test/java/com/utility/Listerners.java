package com.utility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.End2End.Test_Regression.BaseClass;
import com.relevantcodes.extentreports.LogStatus;



public class Listerners extends BaseClass implements ITestListener {
	
	
	
	BaseClass base=new BaseClass();

	public void onTestStart(ITestResult result) {
		
	}

	public void onTestSuccess(ITestResult result) {
		logger.log(LogStatus.PASS, result.getName().toUpperCase()+" PASS");
		rep.endTest(logger);
		rep.flush();
		
	}

	public void onTestFailure(ITestResult result) {
		
		try {
			ExtentManager.captureScreenshot(result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.log(LogStatus.FAIL, result.getName().toUpperCase()+" Failed with exception : "+result.getThrowable());
		//logger.log(LogStatus.FAIL, ExtentManager.captureScreenshot(result.getName()));
		

		BaseClass.logger.log(LogStatus.INFO, "Check Error message");
		try {
			ExtentManager.takeScreenShot(driver, "Error ScreenShoot");
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		rep.endTest(logger);
		rep.flush();
		
	}
		


	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
}
