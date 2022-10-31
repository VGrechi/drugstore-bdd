package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Data
public class Context {

    private static Context instance;

    //private RequestBuilder request = new RequestBuilder();
    //private ResponseBuilder response = new ResponseBuilder();

    public static Context getInstance(){
        if(instance == null){
            instance = new Context();
        }
        return instance;
    }

    public void reset(){
        this.instance = null;
    }



}
