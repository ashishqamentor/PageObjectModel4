package TestPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import Pages.dashboard;

public class baseclass 
{

	public WebDriver w;  //having knoledge
	dashboard d;
	
	@BeforeTest
	public void launch()
	{
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--incognito");
		w= new ChromeDriver(op);
		w.manage().window().maximize();
		w.manage().deleteAllCookies();
		w.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
	
		//creating object
		d = new dashboard(w);
		
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
		
	//@AfterTest
	public void terminate()
	{
		w.quit();
	}
	
	
}
