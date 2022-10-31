package config;


import lombok.Data;
import org.springframework.http.HttpMethod;
@Data
public class Request {

    private HttpMethod method;
    private String url;
    private Object body;
    private Class responseType;

}
