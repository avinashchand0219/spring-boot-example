package com.dailycodebuffer.department.service;

import com.dailycodebuffer.department.entity.Weather;
import com.dailycodebuffer.department.entity.WeatherApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class WeatherService {

    private final WebClient webClient;
    
    @Value("${weather.api.key:demo_key}")
    private String apiKey;
    
    @Value("${weather.api.base-url:https://api.openweathermap.org/data/2.5}")
    private String baseUrl;

    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<Weather> getWeatherByZipCode(String zipCode) {
        // For demonstration purposes, return mock data when API key is not configured
        if (apiKey == null || apiKey.equals("your_openweathermap_api_key_here") || apiKey.equals("demo_key")) {
            log.info("Using mock weather data for zip code: {}", zipCode);
            return Mono.just(createMockWeather(zipCode, "US"));
        }
        
        log.info("Fetching weather data for zip code: {}", zipCode);
        
        String url = String.format("%s/weather?zip=%s,US&appid=%s&units=metric", 
                                 baseUrl, zipCode, apiKey);
        
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(WeatherApiResponse.class)
                .map(this::convertToWeather)
                .doOnSuccess(weather -> log.info("Successfully fetched weather data for zip: {}", zipCode))
                .doOnError(error -> log.error("Error fetching weather data for zip: {}", zipCode, error));
    }

    public Mono<Weather> getWeatherByZipCodeAndCountry(String zipCode, String countryCode) {
        // For demonstration purposes, return mock data when API key is not configured
        if (apiKey == null || apiKey.equals("your_openweathermap_api_key_here") || apiKey.equals("demo_key")) {
            log.info("Using mock weather data for zip code: {} in country: {}", zipCode, countryCode);
            return Mono.just(createMockWeather(zipCode, countryCode));
        }
        
        log.info("Fetching weather data for zip code: {} in country: {}", zipCode, countryCode);
        
        String url = String.format("%s/weather?zip=%s,%s&appid=%s&units=metric", 
                                 baseUrl, zipCode, countryCode, apiKey);
        
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(WeatherApiResponse.class)
                .map(this::convertToWeather)
                .doOnSuccess(weather -> log.info("Successfully fetched weather data for zip: {} in country: {}", zipCode, countryCode))
                .doOnError(error -> log.error("Error fetching weather data for zip: {} in country: {}", zipCode, countryCode, error));
    }

    private Weather convertToWeather(WeatherApiResponse response) {
        Weather weather = new Weather();
        weather.setCity(response.getName());
        weather.setCountry(response.getSys().getCountry());
        weather.setTemperature(response.getMain().getTemp());
        weather.setFeelsLike(response.getMain().getFeelsLike());
        weather.setHumidity(response.getMain().getHumidity());
        weather.setPressure(response.getMain().getPressure());
        weather.setVisibility(response.getVisibility());
        weather.setTimestamp(response.getDt());
        
        if (response.getWeather() != null && !response.getWeather().isEmpty()) {
            WeatherApiResponse.WeatherInfo weatherInfo = response.getWeather().get(0);
            weather.setMain(weatherInfo.getMain());
            weather.setDescription(weatherInfo.getDescription());
        }
        
        if (response.getWind() != null) {
            weather.setWindSpeed(response.getWind().getSpeed());
            weather.setWindDirection(response.getWind().getDeg());
        }
        
        return weather;
    }
    
    private Weather createMockWeather(String zipCode, String countryCode) {
        Weather weather = new Weather();
        
        // Set basic location info
        weather.setZipCode(zipCode);
        weather.setCountry(countryCode);
        
        // Set mock city names based on zip code
        switch (zipCode) {
            case "10001":
                weather.setCity("New York");
                weather.setState("NY");
                break;
            case "90210":
                weather.setCity("Beverly Hills");
                weather.setState("CA");
                break;
            case "60601":
                weather.setCity("Chicago");
                weather.setState("IL");
                break;
            case "33101":
                weather.setCity("Miami");
                weather.setState("FL");
                break;
            default:
                weather.setCity("Sample City");
                weather.setState("XX");
        }
        
        // Set mock weather data
        weather.setTemperature(22.5);
        weather.setFeelsLike(24.0);
        weather.setHumidity(65);
        weather.setPressure(1013.25);
        weather.setMain("Clear");
        weather.setDescription("clear sky");
        weather.setWindSpeed(3.5);
        weather.setWindDirection(180);
        weather.setVisibility(10000);
        weather.setTimestamp(System.currentTimeMillis() / 1000);
        
        return weather;
    }
}