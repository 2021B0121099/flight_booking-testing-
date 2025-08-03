package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.After;
import pages.LoginPage;
import utils.WebDriverFactory;
import utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;

/**
 * Step Definitions for the User Login Functionality feature.
 * This class maps the Gherkin steps to Java methods.
 */
public class LoginSteps {

    private final WebDriver driver;
    private final LoginPage loginPage;
    private final String baseUrl;

    public LoginSteps() throws IOException {
        // Initialize WebDriver and Page Object classes.
        this.driver = WebDriverFactory.getDriver();
        this.loginPage = new LoginPage(driver);
        // Load properties from config.properties.
        ConfigReader.loadProperties();
        this.baseUrl = ConfigReader.getProperty("baseUrl");
    }

    @Given("User is on the Flight Booking login page")
    public void user_is_on_the_flight_booking_login_page() {
        // Navigate to the login page using the URL from the config file.
        loginPage.navigateToLoginPage(baseUrl);
    }

    @When("User enters username {string} and password {string}")
    public void user_enters_username_and_password(String username, String password) {
        // Delegate actions to the POM class.
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    /**
     * This step definition handles scenarios where the username and password
     * fields are passed with concatenated strings, such as `""` for an empty value.
     * It correctly joins the strings and passes them to the page object.
     */
    @When("User enters username {string}{string} and password {string}{string}")
    public void user_enters_username_and_password_with_empty_strings(String user1, String user2, String pass1, String pass2) {
        // Concatenate the two parts of the username and password, then enter them into the fields.
        loginPage.enterUsername(user1 + user2);
        loginPage.enterPassword(pass1 + pass2);
    }

    /**
     * This step definition handles scenarios where the username is
     * passed with concatenated strings, such as `""` for an empty value.
     * It correctly joins the strings and passes them to the page object.
     */
    @When("User enters username {string}{string} and password {string}")
    public void user_enters_username_with_empty_string(String user1, String user2, String password) {
        // Concatenate the two parts of the username and enter the username and password into the fields.
        loginPage.enterUsername(user1 + user2);
        loginPage.enterPassword(password);
    }

    /**
     * This step definition handles scenarios where the password is
     * passed with concatenated strings, such as `""` for an empty value.
     * It correctly joins the strings and passes them to the page object.
     */
    @When("User enters username {string} and password {string}{string}")
    public void user_enters_password_with_empty_string(String username, String pass1, String pass2) {
        // Concatenate the two parts of the password, then enter the username and password into the fields.
        loginPage.enterUsername(username);
        loginPage.enterPassword(pass1 + pass2);
    }

    @When("User enters the displayed security code")
    public void user_enters_the_displayed_security_code() {
        // Get the dynamically generated security code from the page and then enter it.
        String securityCode = loginPage.getSecurityCodeText();
        // Print the captured security code to the console for debugging purposes.
        System.out.println("Captured security code: " + securityCode);
        loginPage.enterSecurityCode(securityCode);
    }

    @When("User clicks on the Validate button")
    public void user_clicks_on_the_validate_button() {
        // Delegate action to the POM class.
        loginPage.clickValidateButton();
    }

    @When("User accepts the first alert")
    public void user_accepts_the_first_alert() {
        try {
            // Wait for the alert to be present before trying to switch to it.
            // NOTE: Using Duration.ofSeconds() for modern Selenium.
            WebDriverWait wait = new WebDriverWait(driver, WebDriverFactory.getExplicitWaitTime());
            wait.until(ExpectedConditions.alertIsPresent());
            // Switch to the alert and accept it (clicks OK).
            driver.switchTo().alert().accept();
        } catch (TimeoutException e) {
            System.err.println("No alert was present after clicking the validate button within the timeout period.");
            throw e; // Re-throw the exception to fail the test.
        }
    }

    @When("User clicks on the Login button")
    public void user_clicks_on_the_login_button() {
        // Delegate action to the POM class.
        loginPage.clickLoginButton();
    }

    @When("User accepts the second alert")
    public void user_accepts_the_second_alert() {
        try {
            // Wait for the alert to be present before trying to switch to it.
            // NOTE: Using Duration.ofSeconds() for modern Selenium.
            WebDriverWait wait = new WebDriverWait(driver, WebDriverFactory.getExplicitWaitTime());
            wait.until(ExpectedConditions.alertIsPresent());
            // Switch to the alert and accept it (clicks OK).
            driver.switchTo().alert().accept();
        } catch (TimeoutException e) {
            System.err.println("No 'Login Successful' alert was present after clicking the login button within the timeout period.");
            throw e; // Re-throw the exception to fail the test.
        }
    }

    @Then("User should be redirected to the Flight Booking dashboard")
    public void user_should_be_redirected_to_the_flight_booking_dashboard() {
        // Assert that the user is on the dashboard.
        Assert.assertTrue(loginPage.isOnDashboard(), "User was not redirected to the dashboard.");
    }

    @Then("User should see an error message {string}")
    public void user_should_see_an_error_message(String expectedMessage) {
        // Get the actual error message displayed on the page.
        String actualMessage = loginPage.getErrorMessageText();

        // Assert that an error message was displayed.
        Assert.assertNotNull(actualMessage, "Error message was not displayed.");

        // Assert that the text of the actual error message matches the expected message.
        // We use trim() to remove any leading or trailing whitespace for a more robust comparison.
        Assert.assertEquals(actualMessage.trim(), expectedMessage, "The error message text did not match the expected message.");
    }
}
