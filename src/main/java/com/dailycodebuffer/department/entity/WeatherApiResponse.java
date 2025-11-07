package com.dailycodebuffer.department.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherApiResponse {
    
    @JsonProperty("coord")
    private Coord coord;
    
    @JsonProperty("weather")
    private List<WeatherInfo> weather;
    
    @JsonProperty("base")
    private String base;
    
    @JsonProperty("main")
    private Main main;
    
    @JsonProperty("visibility")
    private int visibility;
    
    @JsonProperty("wind")
    private Wind wind;
    
    @JsonProperty("clouds")
    private Clouds clouds;
    
    @JsonProperty("dt")
    private long dt;
    
    @JsonProperty("sys")
    private Sys sys;
    
    @JsonProperty("timezone")
    private int timezone;
    
    @JsonProperty("id")
    private int id;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("cod")
    private int cod;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Coord {
        @JsonProperty("lon")
        private double lon;
        
        @JsonProperty("lat")
        private double lat;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WeatherInfo {
        @JsonProperty("id")
        private int id;
        
        @JsonProperty("main")
        private String main;
        
        @JsonProperty("description")
        private String description;
        
        @JsonProperty("icon")
        private String icon;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main {
        @JsonProperty("temp")
        private double temp;
        
        @JsonProperty("feels_like")
        private double feelsLike;
        
        @JsonProperty("temp_min")
        private double tempMin;
        
        @JsonProperty("temp_max")
        private double tempMax;
        
        @JsonProperty("pressure")
        private double pressure;
        
        @JsonProperty("humidity")
        private int humidity;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Wind {
        @JsonProperty("speed")
        private double speed;
        
        @JsonProperty("deg")
        private int deg;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Clouds {
        @JsonProperty("all")
        private int all;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sys {
        @JsonProperty("type")
        private int type;
        
        @JsonProperty("id")
        private int id;
        
        @JsonProperty("country")
        private String country;
        
        @JsonProperty("sunrise")
        private long sunrise;
        
        @JsonProperty("sunset")
        private long sunset;
    }
}