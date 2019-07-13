package pl.sda.model;

public class WeatherService {
    private String site;
    private String key;
    private String finalURL;

    public WeatherService(String site, String key) {
        this.site = site;
        this.key = key;
        this.finalURL = site + "?key=" + key;
    }

    public Current getCityWeather(String city) {
        this.finalURL = finalURL + "&q=" + city;

        return new Current();
    }
}
