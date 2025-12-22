package stepdefinationLogin;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import TestPackage.baseclass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class loginstep extends baseclass
{

	@Given("user is on login page")
	public void user_is_on_login_page() throws Exception
	{
		launch();
		openURL("https://www.saucedemo.com/");
	}
	
	
	@When("^user enters (.+) and (.+)$")
	public void user_enters_username_and_password(String username, String password)
	{
		w.findElement(By.cssSelector("#user-name")).sendKeys(username);
		w.findElement(By.cssSelector("#password")).sendKeys(password);
	}
	
	@And("click on login button")
	public void click_on_login_button()
	{
		w.findElement(By.cssSelector("#login-button")).click();
	}
	
	@Then("Url is expected to display {string}")
	public void urlexpected(String expUrl) throws Exception
	{
		SoftAssert sa = new SoftAssert();
		String actualURl = w.getCurrentUrl();
		sa.assertEquals(actualURl, expUrl);
		screenshot(w, "login");
		terminate();
		sa.assertAll();

				
	}
	
}
