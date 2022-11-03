Feature: Client registration

  Background:
  As a system admin
  I want to register an new employee

  Scenario: should register a pharmaceutical employee successfully
    Given I want to register a new employee
    And the employee name is "Teobaldo"
    And the employee gender is "male"
    And the employee city is "São Carlos"
    And the employee street is "Carlos Botelho"
    And the employee zipCode is "13561003"
    And the employee birthdate is "23/03/2000"
    And the employee hire date is "23/03/2020"
    And the employee function is "farmaceutico"
    And the employee CRF is 1234567
    When the request is made to Employee Service
    Then the employee must be registered successfully
