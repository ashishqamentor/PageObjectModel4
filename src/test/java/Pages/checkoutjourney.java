package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class checkoutjourney extends helperclass
{

	public WebDriver w; // null
	
	@FindBy(css = ".cart-icon")WebElement carticon;
	@FindBy(xpath = "//button[contains(text(),'PROCEED TO CHECKOUT')]") WebElement procced;
	@FindBy(css = ".promoCode")WebElement promocode;
	@FindBy(xpath = "//button[contains(text(),'Apply')]") WebElement apply;
	@FindBy(xpath = "//button[contains(text(),'Place Order')]")WebElement placeorder;
	@FindBy(xpath = "//select")WebElement countrylist;
	@FindBy(css = ".chkAgree")WebElement chkbx;
	@FindBy(xpath = "//button[contains(text(),'Proceed')]")WebElement finalprocced;
	
	By finalprocess = By.xpath("//button[contains(text(),'Proceed')]");
	
	
	public checkoutjourney(WebDriver driverfrombase) 
	{
		super(driverfrombase);
		this.w =driverfrombase;
		PageFactory.initElements(w, this);
		
	}

	public void checkout(String promo, String country) throws Exception
	{
		carticon.click();
		procced.click();
		promocode.sendKeys(promo);
		apply.click();
				
		//wait.until(ExpectedConditions.visibilityOf(placeorder)).click();
		waitforEle(placeorder).click();
				
	//	Thread.sleep(3000);
		//placeorder.click();
		
		Select s = new Select(countrylist);
		s.selectByVisibleText(country);
		
		waitforEle(chkbx).click();
	//	chkbx.click();
		
		//finalprocced.click();
		waitforEleBy(finalprocess).click();
		
		
		
	}
	
	
}
