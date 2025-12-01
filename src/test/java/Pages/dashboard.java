package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class dashboard
{

	public WebDriver w; // null
	
	@FindBy(xpath = "//input[@type='search']")WebElement search;
	@FindBy(xpath = "//button[contains(text(),'ADD TO CART')]") WebElement add;
	
	public dashboard(WebDriver webfrombaseclass) 
	{
		this.w= webfrombaseclass;
		PageFactory.initElements(w, this);
	}

	public void addtokart(String veg)
	{
		search.sendKeys(veg);
		add.click();
		search.clear();
	}
	
	
}
