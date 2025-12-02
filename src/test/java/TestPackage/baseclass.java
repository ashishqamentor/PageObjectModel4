package TestPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import Pages.checkoutjourney;
import Pages.dashboard;

public class baseclass 
{
	public WebDriver w;  //having knoledge
	dashboard d;
	checkoutjourney c;
	
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
		
	@AfterTest
	public void terminate()
	{
		w.quit();
	}
	
	
}
