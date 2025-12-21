package stepdefinationGreenkart;

import TestPackage.baseclass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class geenkartstep extends baseclass
{

	@Given("user is on greenkart site")
	public void user_is_on_greenkart_site() throws Exception
	{
		launch();
		openURL("https://rahulshettyacademy.com/seleniumPractise/#/");	
		
	}
	
	@When("user add vegitable into basket")
	public void user_add_vegitable_into_basket() throws Exception
	{
		String veglist = exceldataread();
		String veg[] = veglist.split(",");
		
		for(String temp:veg) //Brocolli - 1 Kg, Brinjal - 1 Kg ,Mushroom - 1 Kg
		{
			d.addtokart(temp);
		}
	}
	
	@And("user perform checkout")
	public void user_perform_checkout() throws Exception
	{
		c.checkout("ashish", "India");
	}

	@Then("successfull message displayed")
	public void successfull_message_displayed() throws Exception
	{
		screenshot(w, "greenkart_cucumber");
		terminate();
	}
	
}
