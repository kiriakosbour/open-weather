package com.amd.openweather.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class SmsClient {
	@Autowired
	RestTemplate restTemplate;
	final String ROOT_URI = String
			.format("https://connect.routee.net/sms");
	public String restCallToSms(Float temp) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json; charset=utf8");
		headers.add("authorization", "Bearer 8c5ab961-a2a6-4e91-a872-5d16d2b520e2");  
		String requestJson = "";
		if(temp>20) {
		 requestJson = "{ \"body\": \"Kyriakos Bourmpouchakis temperature more than 20C" +temp.toString()+"\",\"to\" : \"+306978745957\",\"from\": \"amdTelecom\"}";
		System.out.println(requestJson);
		}else {
			requestJson = "{ \"body\": \"Kyriakos Bourmpouchakis temperature less than 20C" +temp.toString()+"\",\"to\" : \"+306978745957\",\"from\": \"amdTelecom\"}";
			System.out.println(requestJson);
		}
		
		HttpEntity<?> entity = new HttpEntity<>(requestJson,headers);
		
		try {
			String answer = restTemplate.postForObject(ROOT_URI, entity, String.class);
		return answer;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
}
