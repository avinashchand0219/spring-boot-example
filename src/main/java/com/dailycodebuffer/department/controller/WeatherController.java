package com.dailycodebuffer.department.controller;

import com.dailycodebuffer.department.entity.Weather;
import com.dailycodebuffer.department.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/weather")
@Slf4j
@CrossOrigin(origins = "*")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/zip/{zipCode}")
    public Mono<ResponseEntity<Weather>> getWeatherByZipCode(@PathVariable("zipCode") String zipCode) {
        log.info("Received request for weather data for zip code: {}", zipCode);
        
        return weatherService.getWeatherByZipCode(zipCode)
                .map(weather -> {
                    weather.setZipCode(zipCode);
                    return ResponseEntity.ok(weather);
                })
                .onErrorReturn(ResponseEntity.notFound().build());
    }

    @GetMapping("/zip/{zipCode}/country/{countryCode}")
    public Mono<ResponseEntity<Weather>> getWeatherByZipCodeAndCountry(
            @PathVariable("zipCode") String zipCode,
            @PathVariable("countryCode") String countryCode) {
        
        log.info("Received request for weather data for zip code: {} in country: {}", zipCode, countryCode);
        
        return weatherService.getWeatherByZipCodeAndCountry(zipCode, countryCode)
                .map(weather -> {
                    weather.setZipCode(zipCode);
                    return ResponseEntity.ok(weather);
                })
                .onErrorReturn(ResponseEntity.notFound().build());
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Weather API is running!");
    }
}