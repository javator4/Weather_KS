package pl.sda.model;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class WeatherService {
    private String url;
    private String key;
    private String finalURL;

    public WeatherService(String site, String key) {
        this.url = site;
        this.key = key;
        this.finalURL = site + "?key=" + key;
    }

    public Current getCityWeather(String city) {
        this.finalURL = finalURL + "&q=" + city;
        Current current = new Current();

        try {
            String data = IOUtils.toString(new URL(this.finalURL),
                    Charset.forName("UTF-8"));
            JSONObject jsonObject = new JSONObject(data);

            String tempc = jsonObject.getJSONObject("current").get("temp_c").toString();
            String tempf = jsonObject.getJSONObject("current").get("temp_f").toString();
            String wind_mph = jsonObject.getJSONObject("current").get("wind_mph").toString();
            String wind_kph = jsonObject.getJSONObject("current").get("wind_kph").toString();
            String pressure_mb = jsonObject.getJSONObject("current").get("pressure_mb").toString();
            String pressure_in = jsonObject.getJSONObject("current").get("pressure_in").toString();
            String humidity = jsonObject.getJSONObject("current").get("humidity").toString();



            current.setTemp_c(Double.parseDouble(tempc));
            current.setTemp_f(Double.parseDouble(tempf));
            current.setWind_mph(Double.parseDouble(wind_mph));
            current.setWind_kph(Double.parseDouble(wind_kph));
            current.setPressure_mb(Double.parseDouble(pressure_mb));
            current.setPressure_in(Double.parseDouble(pressure_in));
            current.setHumidity(Double.parseDouble(humidity));

            //to samo builderem (lombak @Builder w Current)
            Current cr = Current.builder()
                    .temp_c(Double.parseDouble(tempc))
                    .temp_f(Double.parseDouble(tempf))
                    .wind_mph(Double.parseDouble(wind_mph))
                    .wind_kph(Double.parseDouble(wind_kph))
                    .pressure_mb(Double.parseDouble(pressure_mb))
                    .pressure_in(Double.parseDouble(pressure_in))
                    .humidity(Double.parseDouble(humidity))
                    .build();

            current = cr; //tymczasowo

        } catch (IOException e) {
            e.printStackTrace();
        }

        return current;
    }
}
