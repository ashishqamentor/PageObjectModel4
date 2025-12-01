package TestPackage;

import org.testng.annotations.Test;

public class greenkart extends baseclass
{

	@Test
	public void checkoutjourny() throws Exception
	{
		openURL("https://rahulshettyacademy.com/seleniumPractise/#/cart");
		
		String veglist = exceldataread();
		String veg[] = veglist.split(",");
		
		for(String temp:veg) //Brocolli - 1 Kg, Brinjal - 1 Kg ,Mushroom - 1 Kg
		{
			d.addtokart(temp);
		}
			
	}
	
}
