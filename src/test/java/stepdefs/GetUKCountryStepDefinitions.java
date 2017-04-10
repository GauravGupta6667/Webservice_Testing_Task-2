package stepdefs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import groovy.json.JsonSlurper;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class GetUKCountryStepDefinitions {

	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;

	private String ENDPOINT_UK_COUNTRIES = "http://services.groupkt.com/country/get/iso2code/";


	@Given("I load the country service for (.*)$")
	public void countryServiceForUK(String country){
		response = given().get(ENDPOINT_UK_COUNTRIES+country);
	}

	@When("a user retrieves the country by alpha2_code")
	public void a_user_retrieves_the_response(){
		//response = request.when().get(ENDPOINT_UK_COUNTRIES);
		
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("the status code is (\\d+)")
	public void verify_status_code_for_country1(int statusCode){
		System.out.println("response code------------"+response.statusCode());
		json = response.then().statusCode(statusCode);
	}

	@And("response includes the following (.*) and (.*) and (.*)$")
	public void response_equals(String name, String code1, String code2) throws JSONException{
		/*for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if(StringUtils.isNumeric(field.getValue())){
				json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
			}
			else{
				json.body(field.getKey(), equalTo(field.getValue()));
			}
		}*/
		
		
		String json=response.prettyPrint();
		
		System.out.println("rsponseeeeeeeeee  "+json);
		
		JSONObject jsonObject=new JSONObject(json);
		
		JSONObject restResponseObj=jsonObject.getJSONObject("RestResponse");
		
		//System.out.println("desired cap value:"+desiredcap.toString());
		
		JSONObject resultObj=restResponseObj.getJSONObject("result");
		
		
		
		Assert.assertEquals(name,resultObj.getString("name"));
		
		
		Assert.assertEquals(code1,resultObj.getString("alpha2_code"));
		
		Assert.assertEquals(code2,resultObj.getString("alpha3_code"));
		
		System.out.println("result----"+name+ "alpha2_code"+" ");
		
		
		
	}

	/*@And("response includes the following in any order")
	public void response_contains_in_any_order_toverify(Map<String,String> responseFields){
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if(StringUtils.isNumeric(field.getValue())){
				json.body(field.getKey(), containsInAnyOrder(Integer.parseInt(field.getValue())));
			}
			else{
				json.body(field.getKey(), containsInAnyOrder(field.getValue()));
			}
		}
	}*/
}


