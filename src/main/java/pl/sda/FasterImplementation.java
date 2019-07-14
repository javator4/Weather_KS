package pl.sda;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sda.model.Weather;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FasterImplementation implements WeatherForecast {
    private WeatherService weatherService;
    private String city;

    @Override
    public Weather getWeather() {
        ObjectMapper objectMapper = new ObjectMapper();
        Weather weather = null;
        try {
            weather = objectMapper.readValue(weatherService.getJSONString(city), Weather.class);
            objectMapper.writeValue(new File("data.json"), weather);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return weather;
    }

    public FasterImplementation(WeatherService weatherService, String city) {
        this.weatherService = weatherService;
        this.city = city;
    }
}
