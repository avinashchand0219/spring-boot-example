# Weather API Documentation

This Spring Boot application now includes a REST API for retrieving weather information by zip code.

## Endpoints

### Health Check
- **GET** `/api/weather/health`
- Returns: `"Weather API is running!"`

### Get Weather by Zip Code (US)
- **GET** `/api/weather/zip/{zipCode}`
- Example: `/api/weather/zip/10001`
- Returns weather data for the specified US zip code

### Get Weather by Zip Code and Country
- **GET** `/api/weather/zip/{zipCode}/country/{countryCode}`
- Example: `/api/weather/zip/10001/country/US`
- Returns weather data for the specified zip code and country

## Response Format

```json
{
  "zipCode": "10001",
  "city": "New York",
  "state": "NY",
  "country": "US",
  "temperature": 22.5,
  "feelsLike": 24.0,
  "description": "clear sky",
  "main": "Clear",
  "humidity": 65,
  "pressure": 1013.25,
  "windSpeed": 3.5,
  "windDirection": 180,
  "visibility": 10000,
  "timestamp": 1762551961
}
```

## Configuration

The API is configured to use mock data by default. To use real weather data from OpenWeatherMap:

1. Get an API key from [OpenWeatherMap](https://openweathermap.org/api)
2. Update `application.properties`:
   ```properties
   weather.api.key=your_actual_api_key_here
   ```

## Sample Zip Codes (Mock Data)

The following zip codes return specific city data:
- `10001` - New York, NY
- `90210` - Beverly Hills, CA
- `60601` - Chicago, IL
- `33101` - Miami, FL
- Any other zip code returns "Sample City, XX"

## Testing

You can test the API using curl:

```bash
# Health check
curl -X GET "http://localhost:12000/api/weather/health"

# Get weather for New York
curl -X GET "http://localhost:12000/api/weather/zip/10001"

# Get weather for Beverly Hills
curl -X GET "http://localhost:12000/api/weather/zip/90210"

# Get weather with country code
curl -X GET "http://localhost:12000/api/weather/zip/10001/country/US"
```

## Implementation Details

- Built with Spring Boot 3.1.5
- Uses Spring WebFlux for reactive HTTP client
- Includes proper error handling and logging
- Supports both mock and real weather data
- Temperature values are in Celsius
- Wind speed is in meters per second
- Pressure is in hPa (hectopascals)