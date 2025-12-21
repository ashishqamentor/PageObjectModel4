package stepdefinationGreenkart;

import static org.testng.Assert.assertTrue;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
features = "src/test/java/featurefiles/greenkart.feature",
glue = "stepdefinationGreenkart",
plugin = { "pretty" , "html:Report/cucumberReport/greenkart.html" ,
				      "json:Report/cucumberReport/greenkart.json" }  ,
monochrome = true
)

public class testrunnerGreenkart extends AbstractTestNGCucumberTests
{

}
