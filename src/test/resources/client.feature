Feature: Client registration

  Background:
  As a employee
  I want to register a client

  Scenario: should register client successfully
    Given I want to register a new client
    And the client name is "Olga"
    And the client gender is "female"
    And the client city is "São Carlos"
    And the client street is "Carlos Botelho"
    And the client zipCode is 13561003
    And the client birthdate is "23/03/1994"
    When the request is made to Client Service
    Then the status code must be 201

  Scenario Outline: should not register client successfully
    Given I want to register a new client
    And the client name is "<name>"
    And the client gender is "<gender>"
    And the client city is "<city>"
    And the client street is "<street>"
    And the client zipCode is <zipCode>
    And the client birthdate is "<birthDate>"
    When the request is made to Client Service
    Then the status code must be <statusCode>
    And the error message must be "<errorMessage>"
    Examples:
      | name | gender | city       | street         | zipCode  | birthDate  | statusCode | errorMessage |
      |      | female | Sao Carlos | Carlos Botelho | 13561003 | 23/03/1994 | 422        | Nome não pode ser nulo ou vazio. |
