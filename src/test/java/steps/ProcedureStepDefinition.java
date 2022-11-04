package steps;

import config.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Employee;
import models.Procedure;
import models.Product;
import org.junit.Assert;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import utils.HttpClient;

import java.util.List;
import java.util.Objects;

public class ProcedureStepDefinition {

    private Procedure procedure;
    private RequestBuilder requestBuilder;
    private ResponseBuilder responseBuilder;

    @Given("I want to register a new procedure")
    public void iWantToRegisterANewProcedure() {
        procedure = new Procedure();

        Context context = Context.getInstance();
        if(Objects.nonNull(context.getClientId())) procedure.setIdCliente(context.getClientId());
        if(Objects.nonNull(context.getEmployeeInss())) procedure.setInss(context.getEmployeeInss());

        requestBuilder = new RequestBuilder();
        requestBuilder.init();
        requestBuilder.method(HttpMethod.POST);
        requestBuilder.responseType(String.class);
    }

    @And("the procedure type is {string}")
    public void theProcedureTypeIs(String type) {
        procedure.setTipo(type);

    }

    @And("the products used in the procedure are:")
    public void theProductsUsedInTheProcedureAre(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);

        rows = rows.subList(1, rows.size());

        for (List<String> columns : rows) {
            int productId = Integer.parseInt(columns.get(0));
            Product product = new Product(productId);
            procedure.getProdutos().add(product);
        }
    }

    @And("the responsible pharmaceutical inss number is {int}")
    public void theResponsiblePharmaceuticalHasID(int inss) {
        procedure.setInss(inss);
    }

    @And("the client who requested has ID {int}")
    public void theClientWhoRequestedHasID(int clientId) {
        procedure.setIdCliente(clientId);
    }

    @When("the request is made to Procedure Service")
    public void theRequestIsMadeToProcesureService() throws Exception {
        requestBuilder.url(Constants.PROCEDURE_SERVICE);
        requestBuilder.body(procedure);

        Request request = requestBuilder.build();
        HttpClient httpClient = new HttpClient();
        ResponseEntity execute = httpClient.execute(request);

        responseBuilder = new ResponseBuilder();
        responseBuilder.init()
                .status(execute.getStatusCode())
                .body(execute.getBody());
    }

    @Then("the procedure must be registered successfully")
    public void theProcedureMustBeRegisteredSuccessfully() {
        Response response = responseBuilder.build();
        Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus().value());
    }
}
