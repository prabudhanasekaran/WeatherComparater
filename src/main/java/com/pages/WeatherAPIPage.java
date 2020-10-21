package com.pages;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;

import com.pages.WeatherAPIPage;
import com.reports.LogStatus;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class WeatherAPIPage {

	public String getCityTemp(String baseUrl, String resource, String city, String tempFormat, String apiKey) {
		
		//Get city Weather from API
		baseURI = baseUrl;
				Response respActual;	
				String cityTempFromAPI;
				

				respActual = given().log().all().
						contentType(ContentType.JSON).
						accept(ContentType.JSON).
						header("Content-Type","application/json").
						queryParam("q", city).
						queryParam("units",tempFormat).
						queryParam("appid",apiKey).
						when().
						get(resource);

					//Print Response
					System.out.println("Response of Weather API: "+respActual.asString());
					LogStatus.info("Response of Weather API: "+respActual.asString());
					
					
					//Verify Status Code
					LogStatus.info("Status Code of Weather API: "+respActual.getStatusCode());
					assertEquals(200,respActual.getStatusCode());
					
					//System.out.println(getJsonPath(respActual,"main.temp"));	
					cityTempFromAPI = getJsonPath(respActual,"main.temp");
					
				return cityTempFromAPI;
			}
		

//Parsing the json response
public String getJsonPath(Response response, String key) {
	String resp = response.asString();
	JsonPath js = new JsonPath(resp);
	return js.get(key).toString();
}

public Integer convertStringToInt(String temp) {
	Double d = Double.parseDouble(temp);
	int tempInInt = Integer.parseInt(new DecimalFormat("#").format(d));	
	//System.out.println(tempInInt);	
	return tempInInt;
}

public void compareTemperatureValues(int tempUI, int tempAPI, int magnitude) {
	int variance;
	if(tempUI>tempAPI) {
		variance = tempUI - tempAPI;
		System.out.println("Temp From UI is greater and Variance is: "+variance);
		
	}
		else {
			variance = tempAPI - tempUI;
			System.out.println("Temp From API is greater and Variance is: "+variance);
	}
	
LogStatus.info("Comparing the variance in temperature using given magnitude");
System.out.println("Comparing the variance in temperature using given magnitude");

LogStatus.info("Temperature from UI: "+tempUI);
System.out.println("Temperature from UI: "+tempUI);

LogStatus.info("Temperature from API: "+tempAPI);
System.out.println("Temperature from API: "+tempAPI);

LogStatus.info("Temperature variance: "+variance);
System.out.println("Temperature variance: "+variance);

LogStatus.info("Allowed Magnitude for variance: "+magnitude);
System.out.println("Allowed Magnitude for variance: "+magnitude);


if(variance<=magnitude) {

	System.out.println("Temperature Comparator Test Passed");
	LogStatus.pass("Temperature Comparator Test Passed");
	
}else
{
	System.out.println("Temperature Comparator Test Failed");
	LogStatus.fail("Temperature Comparator Test Failed");
	
}
}
	
}
