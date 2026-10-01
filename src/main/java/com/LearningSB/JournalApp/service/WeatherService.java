package com.LearningSB.JournalApp.service;

import com.LearningSB.JournalApp.api.response.WeatherResponse;
import com.LearningSB.JournalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//@Service
//public class WeatherService {
//
//    @Value("${weather.api.key}")
//    private String apiKey;
//    private static final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    public WeatherResponse getWeather(String city) {
//        String finalAPI = API.replace("API_KEY", apiKey).replace("CITY", city);
//        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
//        WeatherResponse body = response.getBody();
//        return body;
//    }
//
//}


@Service
public class WeatherService {

    @Autowired
    private AppCache appCache;

//    private static final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    private final RestTemplate restTemplate;
    private final String apiKey;

    public WeatherService(RestTemplate restTemplate, @Value("${weather.api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }

    public WeatherResponse getWeather(String city) {
        String finalApi = appCache.appCacheMap.get(AppCache.keys.WEATHER_API.toString()).replace("<apiKey>", apiKey).replace("<city>", city);

        return restTemplate.getForEntity(finalApi, WeatherResponse.class).getBody();
    }
}
