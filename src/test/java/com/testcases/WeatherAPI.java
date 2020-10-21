package com.testcases;



import java.util.Hashtable;


import org.testng.annotations.Test;

import com.pages.WeatherAPIPage;
import com.reports.LogStatus;


public class WeatherAPI {
	
	static String cityTempFromAPI;

	@Test
	public void getWeatherFromAPITest(Hashtable<String,String> data) {
		System.out.println("In API Test");
		WeatherAPIPage wapipg = new WeatherAPIPage();
		
		String baseUrl = data.get("baseURI");
		String resource = data.get("resource");
		String city = data.get("city");
		String tempFormat = data.get("tempUnit");
		String apiKey = data.get("apiKey");	
		
		
		cityTempFromAPI = wapipg.getCityTemp(baseUrl, resource, city, tempFormat, apiKey);
				
		System.out.println("Temparature of City - "+city+" from API : "+cityTempFromAPI);
		LogStatus.info("Temparature of City - "+city+" from API : "+cityTempFromAPI);

	}
	
}
