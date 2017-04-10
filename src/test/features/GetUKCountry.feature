Feature: Get country by code
  Scenario Outline: User calls web service to get a country by its code
	Given I load the country service for <country> 
	When a user retrieves the country by alpha2_code
	Then the status code is 200
	And response includes the following <name> and <alpha2_code> and <alpha3_code>
	Examples:
	|country| name	|alpha2_code|alpha3_code|   
	|GB| United Kingdom of Great Britain and Northern Ireland | GB		|GBR|
	|US| United States of America | US		|USA|
  |DE| Germany | DE		|DEU|
