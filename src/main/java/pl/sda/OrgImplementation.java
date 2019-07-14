package pl.sda;

import pl.sda.model.Weather;

public class OrgImplementation implements WeatherForecast {
    private WeatherService weatherService;
    private String city;
    @Override
    public Weather getWeather() {
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
