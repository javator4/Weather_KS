package pl.sda;

import lombok.Data;
import org.apache.commons.io.IOUtils;
import pl.sda.model.Weather;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

@Data
public abstract class AbstractJsonData {
    private String url;//"https://api.apixu.com/v1/current.json?";
    private String key; //"key=2cb8d8e890ff4b6c82581410191307&q=Paris";
    private String finalURL;
    private String data = "";
    public void build(){
        this.finalURL = this.url + "?key=" + key;
    }
//    public AbstractJsonData(){
//        this.finalURL = this.url + "?key=" + key + "&q=";
//    }
    public String getJSONData(String city) {
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
    abstract Weather getWeather();

}
