Feature: Test the requests and responses of reqres

  Scenario: Verify adding user
    Given set the header and payload
    When select post method to create user
    Then the response status code should be

  Scenario: get user with id
    Given set the header
    When select get method to fetch user details
    Then the response status code should be 200

    Scenario Outline:  update user with id
      Given set the header and update load
      When select put method to update user <username> and <job>
      Then validate the response status code should be 200

      Examples:
        | username | job |
        | morpheus | zion resident |

  Scenario Outline:  register user with new details
    Given set the header for register
    When select post method to register user <email> and <password>
    Then validate the response status code and id

    Examples:
      | email              | password |
      | eve.holt@reqres.in | pistol |






