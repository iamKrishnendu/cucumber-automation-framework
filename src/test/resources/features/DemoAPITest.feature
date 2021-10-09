Feature: Check API operations

  Background:
    Given consider "https://reqres.in" as base URI

  @demoAPI
  Scenario: GET user details from API
    When I make a GET call with "/api/users/2" as endpoint
    Then I should get the below details as response
    |id|email                 |first_name|last_name|
    |2 |janet.weaver@reqres.in|Janet     |Weaver   |