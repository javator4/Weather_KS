package pl.sda;
import org.apache.commons.io.*;
import pl.sda.model.Current;
import pl.sda.model.Location;
import pl.sda.model.WeatherService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Location location = new Location();
        //https://api.apixu.com/v1/current.json?key=2cb8d8e890ff4b6c82581410191307&q=Paris

        WeatherService weatherService = new WeatherService(
                "https://api.apixu.com/v1/current.json",
                "2cb8d8e890ff4b6c82581410191307"
        );

        Current current = weatherService.getCityWeather("Torun");
        current.getTemp_c();
    }
}
