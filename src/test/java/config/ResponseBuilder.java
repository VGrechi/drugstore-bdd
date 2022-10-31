package config;

import org.springframework.http.HttpStatus;

public class ResponseBuilder {

    private Response response;

    public ResponseBuilder init(){
        this.response = new Response();
        return this;
    }

    public ResponseBuilder status(HttpStatus status){
        this.response.setStatus(status);
        return this;
    }

    public ResponseBuilder body(Object body){
        this.response.setBody(body);
        return this;
    }

    public Response build(){
        return this.response;
    }

}
