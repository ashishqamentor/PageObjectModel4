package Pages;

import java.time.Duration;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class helperclass 
{

	public WebDriver w; // null
	
	
	public helperclass(WebDriver driverfrombase) 
	{
		this.w= driverfrombase;
	}


	public WebElement waitforEle(WebElement temp)
	{
		WebDriverWait wait = new WebDriverWait(w, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOf(temp));
	}
	
	
	public WebElement waitforEleBy(By temp)
	{
		WebDriverWait wait = new WebDriverWait(w, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(temp));
	}
	
	
}
