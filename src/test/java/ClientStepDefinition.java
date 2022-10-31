import config.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Client;
import models.ResponseError;
import org.junit.Assert;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ClientStepDefinition {

    private Client client;
    private RequestBuilder requestBuilder;
    private ResponseBuilder responseBuilder;

    @Given("ok")
    public void ok(){
        Assert.assertTrue(true);
    }

    @Given("I want to register a new client")
    public void iWantToRegisterANewClient() {
        client = new Client();

        requestBuilder = new RequestBuilder();
        requestBuilder.init();
        requestBuilder.method(HttpMethod.POST);
        requestBuilder.responseType(String.class);
    }

    @And("the client name is {string}")
    public void theClientNameIsOlga(String clientName) {
        client.setNome(clientName);
    }

    @And("the client gender is {string}")
    public void theClientGenderIsFemale(String gender) {
        client.setSexo(gender == "female" ? "F" : "M");
    }

    @And("the client city is {string}")
    public void theClientCityIs(String city) {
        client.setCidade(city);
    }

    @And("the client street is {string}")
    public void theClientStreetIs(String street) {
        client.setRua(street);
    }

    @And("the client zipCode is {int}")
    public void theClientZipCodeIs(Integer zipCode) {
        client.setCep(zipCode);
    }

    @And("the client birthdate is {string}")
    public void theClientBirthdateIs(String birthDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        client.setDataNascimento(sdf.parse(birthDate));
    }

    @When("the request is made to Client Service")
    public void madeRequest() throws Exception {
        requestBuilder.url("http://localhost:8080/clients/");
        requestBuilder.body(client);

        Request request = requestBuilder.build();
        HttpClient httpClient = new HttpClient();
        ResponseEntity execute = httpClient.execute(request);

        responseBuilder = new ResponseBuilder();
        responseBuilder.init()
                .status(execute.getStatusCode())
                .body(execute.getBody());
    }

    @Then("the status code must be {int}")
    public void theStatusCodeMustBe(int statusCode) {
        Response response = responseBuilder.build();
        Assert.assertEquals(statusCode, response.getStatus().value());
    }

    @And("the error message must be {string}")
    public void theErrorMessageMustBe(String errorMessage) {
        Response response = responseBuilder.build();
        ResponseError responseError = (ResponseError) response.getBody();
        Assert.assertEquals(errorMessage, responseError.getMessage());
    }
}
