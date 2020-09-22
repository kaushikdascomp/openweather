package com.java.weatherreport.weatherforecast.response;

import com.java.weatherreport.weatherforecast.model.WeatherInfoHolder;
import lombok.Data;

import java.util.List;

@Data
public class WeatherResponseObject {

    public List<WeatherInfoHolder> list;
}
