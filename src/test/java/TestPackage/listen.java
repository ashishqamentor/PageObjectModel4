package TestPackage;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class listen extends baseclass implements ITestListener
{

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		System.out.println(" test started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		System.out.println(" test successful");
		try {
			
			String testname= result.getMethod().getMethodName();
			w = (WebDriver)result.getTestClass().getRealClass().getField("w").get(result.getInstance());
			screenshot(w,testname);
			
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		System.out.println(" test fail");
		
		try {
			String testname= result.getMethod().getMethodName();
			w = (WebDriver)result.getTestClass().getRealClass().getField("w").get(result.getInstance());
			screenshot(w,testname);
			
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		System.out.println(" test skip");
	}

	
	
	
}
