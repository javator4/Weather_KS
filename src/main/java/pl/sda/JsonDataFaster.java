package pl.sda;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import pl.sda.model.Weather;

import java.io.File;
import java.io.IOException;

@Data
public class JsonDataFaster extends AbstractJsonData {
    private WeatherService weatherService;
    private String city;

    @Override
    Weather getWeather() {
        ObjectMapper objectMapper = new ObjectMapper();
        Weather weather = null;
        try {
            weather = objectMapper.readValue(this.getJSONData(city), Weather.class);
            objectMapper.writeValue(new File("data.json"), weather);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return weather;
    }
}
