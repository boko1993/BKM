Feature: Delete a customer from the CRM system

  Scenario Outline: Delete existing customer
    Given url baseUrl + '/customers/{id}'
    And path id = <id>
    When method delete
    Then status 204

  Examples:
    | id |
    | 1  |
    | 2  |
    | 3  |