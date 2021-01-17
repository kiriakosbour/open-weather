package com.amd.openweather.presentation;

import org.json.JSONObject;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.amd.openweather.helper.RestClient;
import com.amd.openweather.helper.SmsClient;
@Component
public class OpenWeatherPresentation {
	public int counter;
@Autowired
RestClient  client;
@Autowired
SmsClient sms;
@Scheduled(fixedRate = 600000)
public void callToOpenWeather() {
	
	if(counter >=10) {	
		 System.exit(0);
	}
	ResponseEntity<String> response = client.restCallToOpenWeather();
	String body = response.getBody();
	JSONObject obj = new JSONObject(body);
	JSONObject mainBody = obj.getJSONObject("main");
	float temp = mainBody.getFloat("temp");
	String answer = sms.restCallToSms(temp);
	System.out.println(answer);
	counter +=1;
	System.out.println(counter);
	
}
}
