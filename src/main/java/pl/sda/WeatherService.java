package pl.sda;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import pl.sda.model.Current;
import pl.sda.model.Location;
import pl.sda.model.Weather;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class WeatherService {
    private String url;
    private String key;
    private String finalURL;
    private String data = "";

    public WeatherService(String site, String key) {
        this.url = site;
        this.key = key;
        this.finalURL = site + "?key=" + key;
    }

    public WeatherService getJSONData(String city) {
        if(this.data.isEmpty()) {
            this.finalURL = finalURL + "&q=" + city;
            try {
                this.data = IOUtils.toString(new URL(this.finalURL),
                        Charset.forName("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return this;
    }

    public String getJSONString(String city) {
        if(this.data.isEmpty()) {
            this.finalURL = finalURL + "&q=" + city;
            try {
                this.data = IOUtils.toString(new URL(this.finalURL),
                        Charset.forName("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return data;
    }
    public Weather getWeather(){
        Weather weather = new Weather();

        weather.setCurrent(getCityWeather());
        weather.setLocation(getLocationObject());
        return weather;
    }

    public Location getLocationObject(){
        String stringKey = "location";
        JSONObject jsonObject = new JSONObject(data).getJSONObject(stringKey);

        String lat = jsonObject.get("lat").toString();
        String lon = jsonObject.get("lon").toString();
        String country = jsonObject.get("country").toString();
        String name = jsonObject.get("name").toString();


        return Location.builder()
                .lat(Double.parseDouble(lat))
                .lon(Double.parseDouble(lon))
                .country(country)
                .name(name).build();
    }

    public Current getCityWeather() {


        String stringKey = "current";
        JSONObject jsonObject = new JSONObject(data).getJSONObject(stringKey);

        String tempc = jsonObject.get("temp_c").toString();
        String tempf = jsonObject.get("temp_f").toString();
        String wind_mph = jsonObject.get("wind_mph").toString();
        String wind_kph = jsonObject.get("wind_kph").toString();
        String pressure_mb = jsonObject.get("pressure_mb").toString();
        String pressure_in = jsonObject.get("pressure_in").toString();
        String humidity = jsonObject.get("humidity").toString();

        //builder (lombak @Builder w Current)
        Current current = Current.builder()
                .temp_c(Double.parseDouble(tempc))
                .temp_f(Double.parseDouble(tempf))
                .wind_mph(Double.parseDouble(wind_mph))
                .wind_kph(Double.parseDouble(wind_kph))
                .pressure_mb(Double.parseDouble(pressure_mb))
                .pressure_in(Double.parseDouble(pressure_in))
                .humidity(Double.parseDouble(humidity))
                .build();


        return current;
    }
}
