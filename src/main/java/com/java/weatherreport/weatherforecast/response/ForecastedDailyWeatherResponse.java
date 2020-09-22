package com.java.weatherreport.weatherforecast.response;

import lombok.Data;

@Data
public class ForecastedDailyWeatherResponse {

    int minTempDayWise;
    int maxTempDayWise;
    boolean rainPrediction;
    boolean tempAbove40;
    String text;
}
