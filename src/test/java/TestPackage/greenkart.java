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
		
		// given
		openURL(site);	
			
		// when
		String veglist = exceldataread();
		String veg[] = veglist.split(",");
		
		for(String temp:veg) //Brocolli - 1 Kg, Brinjal - 1 Kg ,Mushroom - 1 Kg
		{
			d.addtokart(temp);
		}
		
		//and
		c.checkout(promocode,Mycountry);
	}
	
}
