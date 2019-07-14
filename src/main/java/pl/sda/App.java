package pl.sda;
import org.apache.commons.io.*;
import pl.sda.model.Current;
import pl.sda.model.Location;
import pl.sda.model.WeatherService;

public class App 
{
    public static void main( String[] args )
    {
        WeatherService weatherService = new WeatherService(
                "https://api.apixu.com/v1/current.json",
                "2cb8d8e890ff4b6c82581410191307"
        );
        String city = "Great_Brittain";

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




    }
}
