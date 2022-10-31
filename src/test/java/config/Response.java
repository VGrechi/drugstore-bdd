package config;


import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@Data
public class Response {

    private HttpStatus status;
    private Object body;

}
