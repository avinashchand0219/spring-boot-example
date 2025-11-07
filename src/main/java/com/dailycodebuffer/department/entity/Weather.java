package com.dailycodebuffer.department.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    private String zipCode;
    private String city;
    private String state;
    private String country;
    private double temperature;
    private double feelsLike;
    private String description;
    private String main;
    private int humidity;
    private double pressure;
    private double windSpeed;
    private int windDirection;
    private int visibility;
    private long timestamp;
}