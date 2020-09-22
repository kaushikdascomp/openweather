package com.java.weatherreport.weatherforecast.model;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class WeatherInfoHolder {

    public WeatherReportMain main;
    public List<Weather> weather;
    public Clouds clouds;
    public Wind wind;
    public Sys sys;
   // private int dt;
    public String dt_text;

}
