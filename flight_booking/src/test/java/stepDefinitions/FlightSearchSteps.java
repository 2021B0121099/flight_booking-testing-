package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.FlightSearchPage;
import utils.WebDriverFactory;
import utils.ConfigReader;

/**
 * Step Definitions for the Flight Search feature.
 * This class provides concrete implementations for the undefined steps,
 * and has been updated to handle cases where no message is displayed on no-match.
 */
public class FlightSearchSteps {

    private final WebDriver driver;
    private final FlightSearchPage flightSearchPage;
    private String lastSearchType;

    public FlightSearchSteps() {
        this.driver = WebDriverFactory.getDriver();
        this.flightSearchPage = new FlightSearchPage(driver);
    }

    @Given("User is on the Flight Search page")
    public void user_is_on_the_flight_search_page() {
        // Read the URL from the config.properties file for better maintainability.
        String url = ConfigReader.getProperty("flightSearchUrl");
        if (url == null || url.isEmpty()) {
            throw new IllegalStateException("Flight search URL is not defined in config.properties");
        }
        flightSearchPage.navigateTo(url);
    }

    @When("User searches for {string} with value {string}")
    public void user_searches_for_with_value(String searchType, String value) {
        this.lastSearchType = searchType; // Store the search type for the next step
        if ("flight number".equalsIgnoreCase(searchType)) {
            flightSearchPage.enterFlightNumber(value);
        } else if ("flight name".equalsIgnoreCase(searchType)) {
            flightSearchPage.enterFlightName(value);
        } else if ("flight type".equalsIgnoreCase(searchType)) {
            flightSearchPage.enterFlightType(value);
        } else {
            throw new IllegalArgumentException("Invalid search type: " + searchType);
        }
    }

    @When("User clicks on the corresponding search button")
    public void user_clicks_on_the_corresponding_search_button() {
        if (lastSearchType == null) {
            throw new IllegalStateException("Search type was not specified in the previous step.");
        }

        if ("flight number".equalsIgnoreCase(lastSearchType)) {
            flightSearchPage.clickSearchByFlightNumberButton();
        } else if ("flight name".equalsIgnoreCase(lastSearchType)) {
            flightSearchPage.clickSearchByFlightNameButton();
        } else if ("flight type".equalsIgnoreCase(lastSearchType)) {
            flightSearchPage.clickSearchByFlightTypeButton();
        } else {
            throw new IllegalArgumentException("Invalid search type stored: " + lastSearchType);
        }
    }

    
}
