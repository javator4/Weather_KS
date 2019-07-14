package pl.sda;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.*;
import pl.sda.model.Current;
import pl.sda.model.Location;
import pl.sda.model.Weather;
import pl.sda.model.WeatherService;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class App 
{
    public static void main( String[] args )
    {
        String url = "https://api.apixu.com/v1/current.json?" +
                "key=2cb8d8e890ff4b6c82581410191307&q=Paris";

        WeatherService weatherService = new WeatherService(
                "https://api.apixu.com/v1/current.json",
                "2cb8d8e890ff4b6c82581410191307"
        );
        String city = "England";

        Location location = weatherService.getJSONData(city).getLocationObject();

        System.out.println(location.getName());
        System.out.println("Country  : " + location.getCountry());
        System.out.println("Latitude : " + location.getLat());
        System.out.println("Longitude: " + location.getLon());
        System.out.println();

        Current current = weatherService.getJSONData(city).getCityWeather();
        System.out.println("Temperature: " + current.getTemp_c());
        System.out.println("Pressure:    " + current.getPressure_mb());
        System.out.println("Wind kph:    " + current.getWind_kph());
        System.out.println("Humidity:    " + current.getHumidity());

        ObjectMapper objectMapper = new ObjectMapper();

        //OBJECT MAPPER
        try {
            Weather weather = objectMapper.readValue(new URL(url), Weather.class);
            System.out.println(weather.getLocation().getCountry());
            objectMapper.writeValue(new File("data.json"), weather);

        } catch (IOException e) {
            e.printStackTrace();
        }





    }
}
