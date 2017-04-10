package services;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.junit.Test;


public class GetUKCountryTest
{
	private static String ENDPOINT_UK_COUNTRIES = "http://services.groupkt.com/country/get/iso2code/GB";

	@Test
	public void testGetGBCountry(){

		given().get(ENDPOINT_UK_COUNTRIES).then().
		statusCode(HttpStatus.SC_OK).
		body("result.alpha2_code",equalTo("GB"),
			 "result.name",containsInAnyOrder("United Kingdom of Great Britain and Northern Ireland"),
			 "result.alpha3_code",containsInAnyOrder("GBR"));
	}
}
