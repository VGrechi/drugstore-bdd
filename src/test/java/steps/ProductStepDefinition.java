package steps;

import config.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Employee;
import models.Product;
import org.junit.Assert;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import utils.HttpClient;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductStepDefinition {

    private Product product;
    private RequestBuilder requestBuilder;
    private ResponseBuilder responseBuilder;
    @Given("I want to register a new product")
    public void iWantToRegisterANewProduct() {
        product = new Product();

        requestBuilder = new RequestBuilder();
        requestBuilder.init();
        requestBuilder.method(HttpMethod.POST);
        requestBuilder.responseType(String.class);
    }

    @And("the product description is {string}")
    public void theProductDescriptionIs(String description) {
        product.setDescricao(description);
    }

    @And("the product quantity in the inventory is {int}")
    public void theProductQuantityInTheInventoryIs(int quantity) {
        product.setQtdEstoque(quantity);
    }

    @And("the product price per unit is {double}")
    public void theProductPricePerUnitIs(double price) {
        product.setPrecoUnitario(new BigDecimal(price));
    }

    @And("the product measurement is {string}")
    public void theProductMeasureIs(String measurement) {
        product.setUnidade(measurement);
    }

    @And("the product type is {string}")
    public void theProductTypeIs(String type) {
        product.setTipo(type);
    }

    @When("the request is made to Product Service")
    public void theRequestIsMadeToProductService() throws Exception {
        requestBuilder.url(Constants.PRODUCT_SERVICE);
        requestBuilder.body(product);

        Request request = requestBuilder.build();
        HttpClient httpClient = new HttpClient();
        ResponseEntity execute = httpClient.execute(request);

        responseBuilder = new ResponseBuilder();
        responseBuilder.init()
                .status(execute.getStatusCode())
                .body(execute.getBody());
    }

    @Then("the product must be registered successfully")
    public void theProductMustBeRegisteredSuccessfully() {
        Response response = responseBuilder.build();
        Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus().value());
    }


    @And("I save the product's code in the inventory")
    public void iSaveTheProductSCodeInTheInventory() {
        Response response = responseBuilder.build();
        Product responseBody = (Product) response.getBody();
        Context.getInstance().getInventory().add(Objects.nonNull(responseBody) ? responseBody.getCodProduto() : 1);
    }
}
