package stepDefination;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import static io.restassured.RestAssured.given;
import resources.Utils;
 
public class StepDefination extends Utils{
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data =new TestDataBuild();
	static String place_id;
	
	@Given("Add Place Payload {string} {string} {string}")
	public void add_Place_Payload(String name, String language, String address)  throws IOException {
	    // Write code here that turns the phrase above into concrete actions	
		
	     res = given().spec(requestSpecification()).body(data.addPlacePayLoad(name, language, address));	  	    
	}

	@When("User calls {string} API with {string} http request")
	public void user_calls_API_with_http_request(String resource, String httpMethod) {
	    // Write code here that turns the phrase above into concrete actions
		APIResources resourceAPI = APIResources.valueOf(resource);
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200)
	    		 .expectContentType(ContentType.JSON).build();
		
		if (httpMethod.equalsIgnoreCase("POST"))
		response =res.when().post(resourceAPI.GetResource());
					
		else if (httpMethod.equalsIgnoreCase("GET"))
		{			
			response =res.when().get(resourceAPI.GetResource());	
			
		}		
		else if (httpMethod.equalsIgnoreCase("PUT"))
		{			
			response =res.when().put(resourceAPI.GetResource());
			
		}
	}
	
	
	@Then("the  API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(),200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
		
		assertEquals((getJsonPath(response,keyValue)),Expectedvalue);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    
		place_id = getJsonPath(response,"place_id");
		res = given().spec(requestSpecification()).queryParam("place_id",place_id);
		user_calls_API_with_http_request(resource, "GET");
		String actual_name = getJsonPath(response,"name");
		assertEquals(actual_name,expectedName );

	}
	

@Given("Delete Place payload")
public void delete_Place_payload() throws IOException 
{
    // Write code here that turns the phrase above into concrete actions
	res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    
}

@Given("Update Place payload")
public void update_Place_payload() throws IOException {
    // Write code here that turns the phrase above into concrete actions
	res = given().spec(requestSpecification()).body(data.updatePlacePayload(place_id));
    
}




}
