package pl.sda;

import lombok.Data;
import pl.sda.model.Weather;

@Data

public class JsonDataOrg extends AbstractJsonData {
    private String city;
    private WeatherService weatherService;

    @Override
    Weather getWeather() {
        Weather weather = new Weather();
        weather.setCurrent(weatherService.getCityWeather());
        weather.setLocation(weatherService.getLocationObject());
        return weather;
    }

    public OrgImplementation(WeatherService weatherService, String city) {
        this.weatherService = weatherService;
        this.city = city;
    }
}
