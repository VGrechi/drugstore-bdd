package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import config.Request;
import models.ResponseError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import utils.JsonUtils;

public class HttpClient {

    public ResponseEntity execute(Request request) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = null;
        try{
            switch (request.getMethod()){
                case POST: responseEntity = this.post(request); break;
                default:
                    throw new Exception("Http request for this method is not implemented");
            }
        }catch (HttpClientErrorException e){
            ResponseError responseError = (ResponseError) JsonUtils.convertToObject(e.getResponseBodyAsString(), ResponseError.class);
            responseEntity = new ResponseEntity(responseError, e.getStatusCode());
        }
        return responseEntity;
    }

    private ResponseEntity post(Request request) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(request.getUrl(), request.getBody(), request.getResponseType());
    }
}
