package com.testcases;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.pages.NDTVHomePage;
import com.pages.NDTVWeatherPage;
import com.reports.LogStatus;

import io.restassured.http.ContentType;

public class WeatherNDTV extends BaseTest{

	static String cityTempInCelcius_FromUI;
	
	@Test
	public void getWeatherFromWebTest(Hashtable<String,String> data) {
			System.out.println("In UI Test");
			
			String city, cityFullName;
			city = data.get("city");
			cityFullName = data.get("cityFullName");
			NDTVHomePage ndtvHmPage = new NDTVHomePage();
			

			//Navigating to Weather page		
			ndtvHmPage.navigateToWeatherMenu();
			
			//Select City in Pin your City
			NDTVWeatherPage ndtvWeatherPg = new NDTVWeatherPage();
			ndtvWeatherPg.searchAndSelectCity(city);
		
			//Click and Validate City
			ndtvWeatherPg.clickAndValidateCityDetails(data.get("city"),data.get("cityFullName"));
		
			//Get and Store the Value of Temperature in Degrees
			cityTempInCelcius_FromUI = ndtvWeatherPg.getAndStoreCityTempDegree();
			  
			System.out.println("Temparature of City - "+city+" from UI : "+cityTempInCelcius_FromUI);
			LogStatus.info("Temparature of City - "+city+" from UI : "+cityTempInCelcius_FromUI);
	}

	
	
}


