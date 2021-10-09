package com.stepDefinition;



import exception.CustomException;
import fixtures.web.CreateAdminUserFixture;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import static org.testng.Assert.assertTrue;

public class DemoWebTestSteps  {
    Logger logger = Logger.getLogger(DemoWebTestSteps.class);

    @Given("I am on login page")
    public void i_am_on_login_page() {
        assertTrue(CreateAdminUserFixture.checkLoginPageIsVisible());
    }

    @When("I enter {string} and {string}")
    public void i_enter_and(String username, String password) throws CustomException {
        assertTrue(CreateAdminUserFixture.createAdminUser(username,password));
    }

    @Then("I should be on Home page")
    public void i_should_be_on_home_page() {
        assertTrue(CreateAdminUserFixture.checkHomePageAppeared());
    }

}
