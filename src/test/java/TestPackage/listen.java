package TestPackage;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class listen extends baseclass implements ITestListener
{
	
	ExtentReports extent =  extentreportObj();
	ExtentTest test;
	ThreadLocal<ExtentTest> thread = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) 
	{
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		String testname= result.getMethod().getMethodName();
		System.out.println(" test started");
		test = extent.createTest(testname);
		thread.set(test);
		thread.get().info("this is my page object model test");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		System.out.println(" test successful");
		thread.get().pass("test is passing");
		try {
			
			String testname= result.getMethod().getMethodName();
			Thread.sleep(1000);
			w = (WebDriver)result.getTestClass().getRealClass().getField("w").get(result.getInstance());
			String path=screenshot(w,testname).getAbsolutePath();
			thread.get().addScreenCaptureFromPath(path);
			
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		extent.flush();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		System.out.println(" test fail");
		thread.get().fail("test is failing");
		try {
			String testname= result.getMethod().getMethodName();
			w = (WebDriver)result.getTestClass().getRealClass().getField("w").get(result.getInstance());
			Thread.sleep(1000);
			String path=screenshot(w,testname).getAbsolutePath();
			thread.get().addScreenCaptureFromPath(path);
			
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extent.flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		System.out.println(" test skip");
	}

	
	
	
}
