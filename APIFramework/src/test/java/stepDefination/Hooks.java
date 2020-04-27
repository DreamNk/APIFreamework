package stepDefination;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		StepDefination st= new StepDefination();
		if(StepDefination.place_id==null)
		{
	    st.add_Place_Payload("Kishor", "Frence", "address");	
	    st.user_calls_API_with_http_request("AddPlaceAPI", "Post");
	    st.verify_place_Id_created_maps_to_using("Kishor", "GetPlaceAPI");
		}
	}

}
