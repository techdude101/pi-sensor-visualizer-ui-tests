package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", plugin = { "pretty", "html:bin/cucumber-junit/htmloutput",
		"html:target/cucumber-reports.html" }, glue = "seleniumgluecode", monochrome = true)
public class Testrunner {

}
