Feature: Procedure registration

  Background:
  As a system admin
  I want to register an new employee

  Scenario: should register a procedure successfully [Complete Flow]
    # Pharmaceutical
    Given I want to register a new employee
    And the employee name is "Teobaldo"
    And the employee gender is "M"
    And the employee city is "São Carlos"
    And the employee street is "Carlos Botelho"
    And the employee zipCode is "13561003"
    And the employee birthdate is "23/03/2000"
    And the employee hire date is "23/03/2020"
    And the employee function is "farmaceutico"
    And the employee CRF is 1234567
    When the request is made to Employee Service
    Then the employee must be registered successfully
    And I save the employee inss number

    #Client
    Given I want to register a new client
    And the client name is "Olga"
    And the client gender is "F"
    And the client city is "São Carlos"
    And the client street is "Carlos Botelho"
    And the client zipCode is "13561003"
    And the client birthdate is "23/03/1994"
    When the request is made to Client Service
    Then the client must be registered successfully
    And I save the client's ID number

    #Product
    Given I want to register a new product
    And the product description is "Bandeide"
    And the product quantity in the inventory is 200
    And the product price per unit is 2.50
    And the product measurement is "unidades"
    And the product type is "nao-medicamento"
    When the request is made to Product Service
    Then the product must be registered successfully
    And I save the product's code in the inventory

    #Procedure
    Given I want to register a new procedure
    And the procedure type is "curativo"
    When the request is made to Procedure Service
    Then the procedure must be registered successfully


  Scenario: should register a procedure successfully [Short Flow]
    Given I want to register a new procedure
    And the procedure type is "aplicacao"
    And the products used in the procedure are:
      | productId |
      | 100       |
      | 203       |
      | 458       |
    And the responsible pharmaceutical inss number is 1234567
    And the client who requested has ID 1587
    When the request is made to Procedure Service
    Then the procedure must be registered successfully

