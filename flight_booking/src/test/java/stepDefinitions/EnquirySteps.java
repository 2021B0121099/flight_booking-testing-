package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.EnquiryPage;
import utils.ConfigReader;
import utils.WebDriverFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Step Definitions for the Enquiry feature.
 * This class maps the Gherkin steps to Java methods, interacting with the EnquiryPage POM.
 */
public class EnquirySteps {

    private final WebDriver driver;
    private final EnquiryPage enquiryPage;

    public EnquirySteps() {
        // Load properties file once when the step definitions are initialized
        try {
            ConfigReader.loadProperties();
        } catch (IOException e) {
            System.err.println("Failed to load config.properties: " + e.getMessage());
        }
        this.driver = WebDriverFactory.getDriver();
        this.enquiryPage = new EnquiryPage(driver);
    }

    @Given("User is on the Enquiry page")
    public void user_is_on_the_enquiry_page() {
        String enquiryPageUrl = ConfigReader.getProperty("enquiryPageUrl");
        enquiryPage.navigateTo(enquiryPageUrl);
        System.out.println("User is on the Enquiry page.");
    }

    @When("User enters enquiry details")
    public void user_enters_enquiry_details(DataTable dataTable) {
        // Correctly read the data table as a list of maps
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String field = row.get("Field");
            String value = row.get("Value");
            enquiryPage.enterTextByLabel(field, value);
        }
    }

    @And("User clicks the Send button")
    public void user_clicks_the_send_button() {
        enquiryPage.clickSendButton();
        System.out.println("User clicked the 'Send' button.");
    }

    @Then("User should see a confirmation message starting with {string}")
    public void user_should_see_a_confirmation_message_starting_with(String expectedMessage) {
        String actualMessage = enquiryPage.getConfirmationMessage();
        System.out.println("Actual confirmation message: " + actualMessage);
        Assert.assertTrue(actualMessage.startsWith(expectedMessage), "Confirmation message did not start with expected text.");
    }
}
