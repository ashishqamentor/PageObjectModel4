package TestPackage;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class loginsause extends baseclass
{
	@Test(dataProvider = "excel")
	public void login(String user , String pass, String expected)
	{
		openURL("https://www.saucedemo.com/");
		l.logintest(user,pass,expected);
	}
	
	
 
	/*
	@Test
	public void login1() throws Exception
	{
		openURL("https://vinothqaacademy.com/ecommerce-demo/home.html");
		String list =  exceldataread();
		String product[]= list.split(",");
		int size =product.length;
		for(String temp:product)
		{
			w.findElement(By.xpath("//h3[contains(text(),'"+temp+"')]")).click();	
			w.findElement(By.xpath("//button[contains(text(),'Add to Cart')]")).click();
			int itemcount = w.findElements(By.xpath("//ul[@id='cart-list']/li")).size();
			
			if(size==itemcount)
			{
				break;
			}
			w.findElement(By.xpath("//a[contains(text(),'Continue Shopping')]")).click();
					
		}
		
		
	}
	
	*/
	
	
}
