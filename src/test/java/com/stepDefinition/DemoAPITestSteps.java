package com.stepDefinition;

import fixtures.api.CheckVariousAPICalls;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;
import static org.testng.Assert.*;

public class DemoAPITestSteps {

    @Given("consider {string} as base URI")
    public void consider_as_base_uri(String baseURI) {
        assertNotNull(CheckVariousAPICalls.setupBaseURI(baseURI));
    }

    @When("I make a GET call with {string} as endpoint")
    public void i_make_a_get_call_with_as_endpoint(String endpoint) {
        assertNotNull(CheckVariousAPICalls.performGETCallOnSpecifiedEndPoint(endpoint));
    }

    @Then("I should get the below details as response")
    public void i_should_get_the_below_details_as_response(io.cucumber.datatable.DataTable dataTable) {
       List<Map<String,String>> mapOfDataTable = dataTable.asMaps(String.class,String.class);
       assertTrue(CheckVariousAPICalls.checkResponseContainsExpectedUserDetails(mapOfDataTable));
    }
}
