package stepdefinationLogin;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/featurefiles/login.feature", 
glue = "stepdefinationLogin", 
plugin = { "pretty", "html:Report/cucumberReport/login.html",
				     "json:Report/cucumberReport/login.json" }, 
monochrome = true)

public class testrunnerLogin extends AbstractTestNGCucumberTests {

}
