package com.amd.openweather.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
@Component
public class RestClient {
	@Autowired
	RestTemplate restTemplate;
	final String ROOT_URI = String
			.format("http://api.openweathermap.org/data/2.5/weather?q={city_name}&appid={API_key}&units=metric");
public ResponseEntity<String> restCallToOpenWeather() {
	HttpHeaders headers = new HttpHeaders();
	headers.set("Content-Type", "application/json; charset=utf8");
	UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(ROOT_URI);
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("city_name", "Thessaloniki");
	params.put("API_key", "b385aa7d4e568152288b3c9f5c2458a5");
	builder.uriVariables(params);
	HttpEntity<?> entity = new HttpEntity<>(headers);
	System.out.println(builder.toUriString());
	try {
		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,entity, String.class);
	return response;
	} catch (Exception e) {
		System.err.println(e.getMessage());
	}
	return null;
}

}
