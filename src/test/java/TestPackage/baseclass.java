package TestPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

import Pages.checkoutjourney;
import Pages.dashboard;
import Pages.loginpage;

public class baseclass 
{
	public WebDriver w;  //having knoledge
	dashboard d;
	checkoutjourney c;
	loginpage l;
	
	@BeforeTest
	public void launch() throws Exception
	{
		FileInputStream fis = new FileInputStream("./Data/config.properties");
		Properties p = new Properties();
		p.load(fis);
		String browser = p.getProperty("browser");
		String env = p.getProperty("env");
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			ChromeOptions op = new ChromeOptions();
			op.addArguments("--incognito");
		
			if(env.equalsIgnoreCase("local"))
			{				
				if(browser.contains("headless"))
				{
					op.addArguments("headless");
				}
				w= new ChromeDriver(op);
			}			
			
			if(env.equalsIgnoreCase("remote"))
			{
				URL url = new URL("http://localhost:4444/wd/hub");
				op.setCapability("browserName", browser);
				op.getBrowserVersion();
				w= new RemoteWebDriver(url, op);		
			}		
		}
		
		if(browser.equalsIgnoreCase("MicrosoftEdge"))
		{
			EdgeOptions op = new EdgeOptions();
			op.addArguments("-inprivate");
		
			if(env.equalsIgnoreCase("local"))
			{				
				if(browser.contains("headless"))
				{
					op.addArguments("headless");
				}
				w= new EdgeDriver(op);
			}			
			
			if(env.equalsIgnoreCase("remote"))
			{
				URL url = new URL("http://localhost:4444/wd/hub");
				op.setCapability("browserName", browser);
				op.getBrowserVersion();
				w= new RemoteWebDriver(url, op);
			}				
		}
			
		w.manage().window().maximize();
		w.manage().deleteAllCookies();
		w.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
	
		//creating object
		d = new dashboard(w);
		c= new checkoutjourney(w);	
		l= new loginpage(w);
	}
		
	public void openURL(String url)
	{
		w.get(url);
	}
	
	
	public String exceldataread() throws Exception
	{
		FileInputStream fis = new FileInputStream("./Data/login.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheetAt(0);
		int rowcount = sh.getLastRowNum();
		
		String veglist ="";
		for(int i =0;i<rowcount;i++)
		{
			XSSFRow row = sh.getRow(i+1); //2
			XSSFCell cell = row.getCell(0);
			String veg= cell.getStringCellValue();
			veglist = veglist +veg+ ",";  //  veglist = Brocolli - 1 Kg,
										// veglist =Brocolli - 1 Kg, Brinjal - 1 Kg ,
										//veglist = Brocolli - 1 Kg, Brinjal - 1 Kg ,Mushroom - 1 Kg
		}
		return veglist;
	}
	
	
	@DataProvider(name = "excel")
	public Object[][] exceldata() throws Exception
	{
		FileInputStream fis = new FileInputStream("./Data/login.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheetAt(1);
		int rowcount = sh.getLastRowNum();
		
		XSSFRow row = sh.getRow(0);
		int colcount =row.getLastCellNum();
		
		Object obj [][] = new Object[rowcount][colcount];
		
		for(int i=0;i<rowcount;i++)
		{
			row = sh.getRow(i+1);
			for(int j =0;j<colcount;j++)
			{
				XSSFCell cell = row.getCell(j);
				obj[i][j]= cell.getStringCellValue(); // obj[0][0] == standard_user
			}
		}
		return obj;	
	}
	
	public File screenshot(WebDriver driverinstance, String testname) throws Exception
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_HH_mm_ss");
        String timestamp = LocalDateTime.now().format(dtf);
		
		TakesScreenshot tc = (TakesScreenshot)driverinstance;  // null
		File src = tc.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshot/"+testname+"_"+timestamp+".png");
		Files.copy(src, dest);		
		return dest;
	}
		
	public ExtentReports extentreportObj()
	{
		ExtentSparkReporter reporter = new ExtentSparkReporter("./Report/testreport.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		return extent;
	}
	
	
	@AfterTest
	public void terminate()
	{
		w.quit();
	}
	
	
}
