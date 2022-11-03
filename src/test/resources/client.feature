Feature: Client registration

  Background:
  As a employee
  I want to register a new client

  Scenario: should register a client successfully
    Given I want to register a new client
    And the client name is "Olga"
    And the client gender is "female"
    And the client city is "São Carlos"
    And the client street is "Carlos Botelho"
    And the client zipCode is "13561003"
    And the client birthdate is "23/03/1994"
    When the request is made to Client Service
    Then the client must be registered successfully

  Scenario Outline: should not register a client successfully
    Given I want to register a new client
    And the client name is "<name>"
    And the client gender is "<gender>"
    And the client city is "<city>"
    And the client street is "<street>"
    And the client zipCode is "<zipCode>"
    And the client birthdate is "<birthDate>"
    When the request is made to Client Service
    Then the client must not be registered
    And the error message must be "<errorMessage>"
    Examples:
      | name | gender    | city       | street         | zipCode  | birthDate  | errorMessage                                        |
      |      | F         | Sao Carlos | Carlos Botelho | 13561003 | 23/03/1994 | Nome não pode ser nulo ou vazio.                    |
      | Olga |           | Sao Carlos | Carlos Botelho | 13561003 | 23/03/1994 | Gênero (sexo biológico) não pode ser nulo ou vazio. |
      | Olga | undefined | Sao Carlos | Carlos Botelho | 13561003 | 23/03/1994 | Gênero (sexo biológico) inválido.                   |
      | Olga | F         |            | Carlos Botelho | 13561003 | 23/03/1994 | Cidade não pode ser nulo ou vazio.                  |
      | Olga | F         | Sao Carlos |                | 13561003 | 23/03/1994 | Rua não pode ser nulo ou vazio.                     |
      | Olga | F         | Sao Carlos | Carlos Botelho |          | 23/03/1994 | CEP não pode ser nulo ou vazio.                     |
      | Olga | F         | Sao Carlos | Carlos Botelho | 13561003 |            | Data de Nascimento não pode ser nulo ou vazio.      |
