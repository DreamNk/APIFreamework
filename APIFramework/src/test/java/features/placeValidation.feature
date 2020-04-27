Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI

Given Add Place Payload "<name>" "<language>" "<address>"
When User calls "AddPlaceAPI" API with "Post" http request
Then the  API call is success with status code 200
And "status" in response body is "OK" 
And "scope" in response body is "APP"

And verify place_Id created maps to "<name>" using "GetPlaceAPI"



Examples:
	|name             	|language 	|address					|
	|Frontline house  	|English  	|29, side layout, cohen 09	|
#	|Frontline house1  	|English  	|29, side layout, cohen 010	|



@UpdatePlace
Scenario: Verify if update place functionality is working

Given Update Place payload
When User calls "UpdatePlaceAPI" API with "Put" http request
Then the  API call is success with status code 200
#And "msg" in response body is "Address successfully updated" 



@DeletePlace
Scenario: Verify if Delete place functionality is working

Given Delete Place payload
When User calls "DeletePlaceAPI" API with "Post" http request
Then the  API call is success with status code 200
And "status" in response body is "OK" 