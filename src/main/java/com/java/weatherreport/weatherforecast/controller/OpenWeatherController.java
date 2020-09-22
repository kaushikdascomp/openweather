package com.java.weatherreport.weatherforecast.controller;

import com.java.weatherreport.weatherforecast.model.WeatherInfoHolder;
import com.java.weatherreport.weatherforecast.response.ForecastedDailyWeatherResponse;
import com.java.weatherreport.weatherforecast.response.WeatherResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/weather/forecast")
public class OpenWeatherController {

    @Value("${weather.forecast.base.url}")
    private String url;

    @Value("${weather.forecast.appid}")
    private String appid;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(path = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<ForecastedDailyWeatherResponse> getWeatherInformation(@PathVariable String city){
        StringBuilder prepareUrl = new StringBuilder();
        String finalUrl = prepareUrl.append(url).append(city).append("&appid=").append(appid).toString();
        ResponseEntity<WeatherResponseObject> response
                = restTemplate.getForEntity(finalUrl, WeatherResponseObject.class);

        WeatherResponseObject weatherResponseObject = response.getBody();
        List<WeatherInfoHolder> weatherInfoList = weatherResponseObject.getList();
        int min_temp_per3hour = 0;
        int min_temp_perDay = Integer.MAX_VALUE;
        int max_temp_per3hour = 0;
        int max_temp_perDay = Integer.MIN_VALUE;
        boolean rainPredictionPer3Hour = false;
        boolean rainPredictionDaily = false;
        List<ForecastedDailyWeatherResponse> forecastedDailyWeatherResponses = new ArrayList<>();
        for(int i=0;i<24;i+=8){
            rainPredictionPer3Hour = false;
            min_temp_per3hour = weatherInfoList.get(i).getMain().getTemp_min();
            min_temp_perDay = Math.min(min_temp_perDay,min_temp_per3hour);
            max_temp_per3hour = weatherInfoList.get(i).getMain().getTemp_max();
            max_temp_perDay = Math.max(max_temp_per3hour,max_temp_perDay);
            rainPredictionPer3Hour = weatherInfoList.get(i).getWeather().get(0).getMain().contains("Rain");
            if(rainPredictionPer3Hour)
                rainPredictionDaily = true;
            if(i%8 == 0){
                ForecastedDailyWeatherResponse weatherResponse = new ForecastedDailyWeatherResponse();
                weatherResponse.setMaxTempDayWise(max_temp_perDay);
                weatherResponse.setMinTempDayWise(min_temp_perDay);
                weatherResponse.setRainPrediction(rainPredictionDaily);
                if(max_temp_perDay>313){
                    weatherResponse.setTempAbove40(true);
                    weatherResponse.setText("Use Sunscreen Lotion");
                }
                else {
                    weatherResponse.setTempAbove40(false);
                }

                if(weatherResponse.isRainPrediction()){
                    weatherResponse.setText("Please carry Umbrella");
                }
                forecastedDailyWeatherResponses.add(weatherResponse);
                rainPredictionDaily = false;
            }
        }


        return forecastedDailyWeatherResponses;
    }
}
