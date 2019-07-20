package pl.sda;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import pl.sda.model.Current;
import pl.sda.model.Location;
import pl.sda.model.Weather;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class App 
{
    private static Logger logger = Logger.getLogger(App.class);

    public static void main( String[] args )
    {
        logger.info("APP RUNNING");
        logger.warn("WARNING");
        logger.debug("DEBUG");
        logger.error("ERROR");

        String url = "https://api.apixu.com/v1/current.json?" +
                "key=2cb8d8e890ff4b6c82581410191307&q=Paris";

        WeatherService weatherService = new WeatherService(
                "https://api.apixu.com/v1/current.json",
                "2cb8d8e890ff4b6c82581410191307"
        );
        String city = "Santa_Monica";

        Location location = weatherService.getJSONData(city).getLocationObject();

        System.out.println(location.getName());
        System.out.println("Country     : " + location.getCountry());
        System.out.println("Latitude    : " + location.getLat());
        System.out.println("Longitude   : " + location.getLon());
        System.out.println();

        Current current = weatherService.getJSONData(city).getCityWeather();
        System.out.println("Temperature : " + current.getTemp_c());
        System.out.println("Pressure    : " + current.getPressure_mb());
        System.out.println("Wind kph    : " + current.getWind_kph());
        System.out.println("Humidity    : " + current.getHumidity());

        ObjectMapper objectMapper = new ObjectMapper();

        //OBJECT MAPPER (Jackson)
//        try {
//            Weather weather = objectMapper.readValue(new URL(url), Weather.class);
//            System.out.println(weather.getLocation().getCountry());
//            objectMapper.writeValue(new File("data.json"), weather);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Weather weather = weatherService.getWeather();
//        System.out.println(weather.getLocation().getCountry());
//
        System.out.println();
        System.out.println();

        //FOR JACKSON AND IO.COMMONS

        WeatherForecast faster = new FasterImplementation(weatherService,city);
        WeatherForecast org = new OrgImplementation(weatherService,city);

        System.out.println(faster.getWeather().getLocation().getCountry());
        System.out.println(faster.getWeather().getCurrent().getWind_kph());
        System.out.println(org.getWeather().getCurrent().getWind_kph());

        JsonDataFaster jsonDataFaster = new JsonDataFaster();
        jsonDataFaster.setUrl("https://api.apixu.com/v1/current.json");
        jsonDataFaster.setKey("2cb8d8e890ff4b6c82581410191307");
        jsonDataFaster.setCity("Warszawa");
        jsonDataFaster.build();
        Weather weatherJSON = jsonDataFaster.getWeather();

        System.out.println(weatherJSON);
    }
}
