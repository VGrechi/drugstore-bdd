package steps;

import config.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Employee;
import org.junit.Assert;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import utils.HttpClient;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class EmployeeStepDefinition {

    private Employee employee;
    private RequestBuilder requestBuilder;
    private ResponseBuilder responseBuilder;

    @Given("I want to register a new employee")
    public void iWantToRegisterANewEmployee() {
        employee = new Employee();

        requestBuilder = new RequestBuilder();
        requestBuilder.init();
        requestBuilder.method(HttpMethod.POST);
        requestBuilder.responseType(String.class);
    }

    @And("the employee name is {string}")
    public void theEmployeeNameIsOlga(String clientName) {
        employee.setNome(clientName);
    }

    @And("the employee gender is {string}")
    public void theEmployeeGenderIsFemale(String gender) {
        employee.setSexo(gender);
    }

    @And("the employee city is {string}")
    public void theEmployeeCityIs(String city) {
        employee.setCidade(city);
    }

    @And("the employee street is {string}")
    public void theEmployeeStreetIs(String street) {
        employee.setRua(street);
    }

    @And("the employee zipCode is {string}")
    public void theEmployeeZipCodeIs(String zipCode) {
        if(Objects.nonNull(zipCode) && !zipCode.isEmpty()){
            employee.setCep(Integer.parseInt(zipCode));
        }
    }

    @And("the employee birthdate is {string}")
    public void theEmployeeBirthdateIs(String birthDate) throws ParseException {
        if(Objects.nonNull(birthDate) && !birthDate.isEmpty()){
            SimpleDateFormat sdfEn = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdfPt = new SimpleDateFormat("dd/MM/yyyy");
            employee.setDataNascimento(sdfEn.format(sdfPt.parse(birthDate)));
        }
    }

    @And("the employee hire date is {string}")
    public void theEmployeeHireDateIs(String hireDate) throws ParseException {
        if(Objects.nonNull(hireDate) && !hireDate.isEmpty()){
            SimpleDateFormat sdfEn = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdfPt = new SimpleDateFormat("dd/MM/yyyy");
            employee.setDataContratacao(sdfEn.format(sdfPt.parse(hireDate)));
        }
    }

    @And("the employee function is {string}")
    public void theEmployeeFunctionIs(String function) {
        employee.setFuncao(function);
    }

    @And("the employee CRF is {int}")
    public void theEmployeeCRFIs(int crf) {
        employee.setCrf(crf);
    }

    @When("the request is made to Employee Service")
    public void madeRequest() throws Exception {
        requestBuilder.url(Constants.EMPLOYEE_SERVICE);
        requestBuilder.body(employee);

        Request request = requestBuilder.build();
        HttpClient httpClient = new HttpClient();
        ResponseEntity execute = httpClient.execute(request);

        responseBuilder = new ResponseBuilder();
        responseBuilder.init()
                .status(execute.getStatusCode())
                .body(execute.getBody());
    }

    @Then("the employee must be registered successfully")
    public void theEmployeeMustBeRegisteredSuccessfully() {
        Response response = responseBuilder.build();
        Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus().value());
    }

    @Then("the employee must not be registered")
    public void theEmployeeMustNotBeRegistered() {
        Response response = responseBuilder.build();
        Assert.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatus().value());
    }

    @And("I save the employee inss number")
    public void iSaveTheEmployeeInssNumber() {
        Response response = responseBuilder.build();
        Employee responseBody = (Employee) response.getBody();
        Context.getInstance().setEmployeeInss(Objects.nonNull(responseBody) ? responseBody.getInss() : 1);
    }
}
