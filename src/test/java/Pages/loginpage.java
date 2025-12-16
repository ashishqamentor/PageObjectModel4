package Pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginpage extends helperclass
{
	public WebDriver w;
	
	@FindBy(css = "#user-name")WebElement userEle;
	@FindBy(css = "#password")WebElement passEle;
	@FindBy(css = "#login-button")WebElement loginEle;

	public loginpage(WebDriver driverfrombaseclas) 
	{
		super(driverfrombaseclas);
		this.w=driverfrombaseclas;
		PageFactory.initElements(w, this);
	}

	public void logintest(String user, String pass,String expected) throws Exception
	{
		waitforEle(userEle).sendKeys(user);
		//userEle.sendKeys(user);
		waitforEle(passEle).sendKeys(pass);
		Thread.sleep(1000);
		waitforEle(loginEle).click();
		
		String actualURl = w.getCurrentUrl();
		assertEquals(actualURl, expected);
			
	}
	
}
