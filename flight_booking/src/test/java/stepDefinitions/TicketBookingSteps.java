package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import pages.TicketBookingPage;
import utils.WebDriverFactory;
import utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Step Definitions for the Flight Ticket Booking feature.
 * This class maps the Gherkin steps to Java methods, interacting with the TicketBookingPage POM.
 */
public class TicketBookingSteps {

    private final WebDriver driver;
    private final TicketBookingPage ticketBookingPage;

    public TicketBookingSteps() {
        // Load properties file once when the step definitions are initialized
        try {
            ConfigReader.loadProperties();
        } catch (IOException e) {
            System.err.println("Failed to load config.properties: " + e.getMessage());
        }
        this.driver = WebDriverFactory.getDriver();
        this.ticketBookingPage = new TicketBookingPage(driver);
    }

    @Given("User is on the Flight Ticket Booking page")
    public void user_is_on_the_flight_ticket_booking_page() {
        String flightBookingUrl = ConfigReader.getProperty("flightBookingUrl");
        driver.get(flightBookingUrl);
        System.out.println("User is on the Flight Ticket Booking page.");
    }

    /**
     * This step definition handles all booking details from a single data table,
     * which is now the correct way to handle your feature file's structure.
     * @param dataTable The data table from the feature file containing all booking details.
     */
    @When("User enters the following booking details:")
    public void user_enters_the_following_booking_details(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String field = row.get("Field");
            // Check for null value and convert to an empty string to avoid IllegalArgumentException
            String value = row.get("Value");
            if (value == null) {
                value = "";
            }

            if ("Number of Passengers".equals(field)) {
                ticketBookingPage.enterNumberOfPassengers(Integer.parseInt(value));
            } else if ("Class".equals(field)) {
                ticketBookingPage.selectClass(value);
            } else {
                ticketBookingPage.enterTextByLabel(field, value);
            }
        }
    }

    @When("User selects class {string}")
    public void user_selects_class(String className) {
        ticketBookingPage.selectClass(className);
    }
    
    @When("User clicks on the {string} button")
    public void user_clicks_on_the_button(String buttonName) {
        if ("Book Now".equalsIgnoreCase(buttonName)) {
            ticketBookingPage.clickBookNowButton();
        }
    }

    @Then("User should see expected message {string}")
    public void user_should_see_expected_message(String expectedMessage) {
        String actualMessage = ticketBookingPage.getConfirmationMessage();
        Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "On-page confirmation message did not match.");
    }
    
    @When("User enters valid booking details for {string}")
    public void user_enters_valid_booking_details_for(String className) {
        ticketBookingPage.enterTextByLabel("Travel From", "Delhi");
        ticketBookingPage.enterTextByLabel("Travel To", "Mumbai");
        ticketBookingPage.enterTextByLabel("Departure Date", "2025-09-15");
        ticketBookingPage.selectClass(className);
        ticketBookingPage.enterTextByLabel("Passenger Name", "John Doe");
        ticketBookingPage.enterTextByLabel("Email", "john.doe@example.com");
        ticketBookingPage.enterTextByLabel("Phone Number", "9876543210");
        ticketBookingPage.enterNumberOfPassengers(2);
    }
    
    @Then("User should see a confirmation message for class {string}")
    public void user_should_see_a_confirmation_message_for_class(String className) {
        String confirmationText = ticketBookingPage.getConfirmationMessage();
        Assert.assertTrue(confirmationText.contains(className), "Confirmation message did not contain the expected class name: " + className);
    }
}
