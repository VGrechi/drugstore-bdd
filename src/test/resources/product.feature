Feature: Product registration

  Background:
  As a employee
  I want to register an new product

  Scenario: should register a product successfully
    Given I want to register a new product
    And the product description is "Bandeide"
    And the product quantity in the inventory is 200
    And the product price per unit is 2.50
    And the product measurement is "unidades"
    And the product type is "nao-medicamento"
    When the request is made to Product Service
    Then the product must be registered successfully
