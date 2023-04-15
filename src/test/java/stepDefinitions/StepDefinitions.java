package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
    @Given("I open New Customer page")
    public void iOpenNewCustomerPage() {
        System.out.println("Hello Minh");
    }

    @When("Input to New Customer form with data")
    public void inputToNewCustomerFormWithData() {
        System.out.println("Hello Minh");
    }

    @When("I click to Submit button at New Customer page")
    public void iClickToSubmitButtonAtNewCustomerPage() {
        System.out.println("Hello Minh");
    }

    @Then("Verify message displayed with data")
    public void verifyMessageDisplayedWithData() {
        System.out.println("Hello Minh");
    }

    @Then("I verify all above infomation created success")
    public void iVerifyAllAboveInfomationCreatedSuccess() {
        System.out.println("Hello Minh");
    }

}
