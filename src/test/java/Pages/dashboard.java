package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class dashboard extends helperclass
{

	public WebDriver w; // null
	
	@FindBy(xpath = "//input[@type='search']")WebElement search;
	//@FindBy(xpath = "//button[contains(text(),'ADD TO CART')]") WebElement add;
	By add = By.xpath("//button[contains(text(),'ADD TO CART')]");
	
	public dashboard(WebDriver webfrombaseclass) 
	{
		super(webfrombaseclass);
		this.w= webfrombaseclass;
		PageFactory.initElements(w, this);
	}

	public void addtokart(String veg) throws Exception
	{
		search.sendKeys(veg);
		Thread.sleep(1000);
		waitforEleBy(add).click();
		search.clear();
	}
	
	
}
