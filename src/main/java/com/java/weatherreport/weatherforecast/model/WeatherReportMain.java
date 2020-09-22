package com.java.weatherreport.weatherforecast.model;

import lombok.Data;

@Data
public class WeatherReportMain {

    public int temp;
    public int temp_min;
    public int temp_max;
    public int pressure;
    public int sea_level;
    public int grnd_level;
    public int humidity;
    public int temp_kf;
}
