package com.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.pages.WeatherAPIPage;
import com.reports.LogStatus;

public class WeatherComparator {

@Test
public void weatherComparatorTest(Hashtable<String,String> data) {
	
	System.out.println("In Weather Comparison Test");
	
	String tempFromUI,tempFromAPI, magnitudeTemp = data.get("magnitude");
	int tempUI,tempAPI,magnitude; 
	
	//Getting temperature from UI
	WeatherNDTV wNDTV = new WeatherNDTV();
	tempFromUI = wNDTV.cityTempInCelcius_FromUI;
	
	
	//Getting temperature from API
	WeatherAPI wAPI = new WeatherAPI();
	tempFromAPI = wAPI.cityTempFromAPI;
	
	//Converting Temperature in String to Integer
	WeatherAPIPage wAPIPg = new WeatherAPIPage();
	
	tempUI = wAPIPg.convertStringToInt(tempFromUI);
	tempAPI = wAPIPg.convertStringToInt(tempFromAPI);
	magnitude = wAPIPg.convertStringToInt(magnitudeTemp);
	
	//Compare the variance in temperature using given magnitude
	wAPIPg.compareTemperatureValues(tempUI, tempAPI,magnitude);
	
	


}
	
}
