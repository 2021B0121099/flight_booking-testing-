package pages;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverFactory;

/**
 * Page Object Model (POM) class for the Flight Booking login page.
 * This class encapsulates the web elements and methods for interacting with the login page.
 */
public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators for web elements on the login page.
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By securityCodeField = By.id("captcha");
    private final By securityCodeElement = By.id("code"); // Locator for the displayed security code
    private final By validateButton = By.id("captchaBtn");
    private final By loginButton = By.id("login-submit");
    private final By errorMessageLabel = By.xpath("//*[@id='login-info']/span"); // Locator for the error message
    private final By dashboardHeader = By.id("indexnavigation");
    
    /**
     * Constructor for the LoginPage.
     * @param driver The WebDriver instance.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // Use an explicit wait time from the configuration.
        this.wait = new WebDriverWait(driver, WebDriverFactory.getExplicitWaitTime());
    }

    /**
     * Navigates the browser to the specified URL.
     * @param url The URL of the login page.
     */
    public void navigateToLoginPage(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        // Wait for the username field to be visible to ensure the page is loaded.
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
    }

    /**
     * Enters the provided username into the username field.
     * @param username The username to enter.
     */
    public void enterUsername(String username) {
        WebElement userElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        userElement.clear();
        userElement.sendKeys(username);
    }

    /**
     * Enters the provided password into the password field.
     * @param password The password to enter.
     */
    public void enterPassword(String password) {
        WebElement passElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passElement.clear();
        passElement.sendKeys(password);
    }

    /**
     * Enters the security code into the security code field.
     * @param securityCode The security code to enter.
     */
    public void enterSecurityCode(String securityCode) {
        WebElement securityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(securityCodeField));
        securityElement.clear();
        securityElement.sendKeys(securityCode);
    }

    /**
     * Gets the dynamically generated security code text from the page.
     * NOTE: This assumes the security code is displayed as text.
     * @return The text of the security code. Returns an empty string if the element is not found or has no text.
     */
    public String getSecurityCodeText() {
        try {
            WebElement securityCodeTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(securityCodeElement));
            return securityCodeTextElement.getText();
        } catch (NoSuchElementException e) {
            System.err.println("Could not find the security code element. Check the locator or if the element is present on the page.");
            // Return an empty string instead of null to prevent IllegalArgumentException in sendKeys().
            return "";
        }
    }

    /**
     * Clicks the "Validate" button.
     */
    public void clickValidateButton() {
        WebElement validateBtn = wait.until(ExpectedConditions.elementToBeClickable(validateButton));
        validateBtn.click();
    }

    /**
     * Clicks the "Login" button.
     */
    public void clickLoginButton() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();
    }

    /**
     * Checks if the user is on the dashboard page by waiting for a key element.
     * @return True if the dashboard header is visible, false otherwise.
     */
    public boolean isOnDashboard() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Retrieves the text of the error message displayed on the page.
     * @return The error message text. Returns an empty string if the element is not found.
     */
    public String getErrorMessageText() {
        try {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLabel));
            return errorMessage.getText();
        } catch (Exception e) {
            return ""; // Return an empty string if the error message is not found within the timeout.
        }
    }
}
