package config;

import org.springframework.http.HttpMethod;

public class RequestBuilder {

    private Request request;

    public RequestBuilder init(){
        this.request = new Request();
        return this;
    }

    public RequestBuilder method(HttpMethod method){
        this.request.setMethod(method);
        return this;
    }

    public RequestBuilder url(String url){
        this.request.setUrl(url);
        return this;
    }

    public RequestBuilder body(Object body){
        this.request.setBody(body);
        return this;
    }

    public RequestBuilder responseType(Class responseType){
        this.request.setResponseType(responseType);
        return this;
    }

    public Request build(){
        return this.request;
    }

}
