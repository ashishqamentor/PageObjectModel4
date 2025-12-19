package TestPackage;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class greenkart extends baseclass
{

	@Parameters({"url","promo","country"})
	@Test
	public void checkoutjourny(String site,String promocode,String Mycountry) throws Exception
	{
		openURL(site);
		
		System.out.println("user a commited");
		System.out.println("user a is not aware of user b changes ");
		
		String veglist = exceldataread();
		String veg[] = veglist.split(",");
		
		for(String temp:veg) //Brocolli - 1 Kg, Brinjal - 1 Kg ,Mushroom - 1 Kg
		{
			d.addtokart(temp);
		}
		c.checkout(promocode,Mycountry);
	}
	
}
