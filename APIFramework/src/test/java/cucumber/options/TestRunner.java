package cucumber.options;



import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features ="src/test/java/features/placeValidation.feature",plugin = "json:target/jsonReports/cucumber-report.json", glue = {"stepDefination"})

//tags = {"@DeletePlace"} plugin = "json:target/jsonReports/cucumber-report.json",
// cd /Users/nandakishor.ban/eclipse-workspace/APIFramework
//mvn test verify -Dcucumber.Options="--tags @AddPlace"

public class TestRunner {
	

}

