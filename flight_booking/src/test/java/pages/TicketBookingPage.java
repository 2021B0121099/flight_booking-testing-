package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;

//import java.time.Duration;

/**
 * Page Object Model (POM) for the Flight Ticket Booking page.
 * This class contains all the web elements and methods for interacting with the page.
 */
public class TicketBookingPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators for web elements on the page using individual IDs
    private final By travelFromInput = By.id("travelFrom");
    private final By travelToInput = By.id("travelTo");
    private final By departureDateInput = By.id("departure");
    private final By passengerNameInput = By.id("name");
    private final By emailInput = By.id("email");
    private final By phoneNumberInput = By.id("phone");
    
    
    private final By classDropdown = By.id("selectclass");
    private final By passengersInput = By.id("ticket-class-count");
    private final By bookNowButton = By.id("book-now");
    private final By confirmationMessage = By.xpath("//*[@id='bookingconfirm']");

    public TicketBookingPage(WebDriver driver) {
        this.driver = driver;
        // The explicit wait time is read from the config.properties file
        long explicitWaitTime = Long.parseLong(ConfigReader.getProperty("explicitWaitTime"));
        this.wait = new WebDriverWait(driver, explicitWaitTime);
    }

    /**
     * Finds and returns a web element for a text input field by its associated label.
     * It uses an explicit wait to ensure the element is visible before returning it.
     * @param labelText The text of the label for the input field.
     * @return The WebElement of the input field.
     */
    private WebElement getInputElementByLabel(String labelText) {
        By locator;
        if ("Travel From".equals(labelText)) {
            locator = travelFromInput;
        } else if ("Travel To".equals(labelText)) {
            locator = travelToInput;
        } else if ("Departure Date".equals(labelText)) {
            locator = departureDateInput;
        } else if ("Passenger Name".equals(labelText)) {
            locator = passengerNameInput;
        } else if ("Email".equals(labelText)) {
            locator = emailInput;
        } else if ("Phone Number".equals(labelText)) {
            locator = phoneNumberInput;
        } else {
            throw new IllegalArgumentException("Invalid field label: " + labelText);
        }
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Enters text into an input field identified by its label.
     * @param labelText The label of the input field.
     * @param textToEnter The text to be entered.
     */
    public void enterTextByLabel(String labelText, String textToEnter) {
        WebElement inputElement = getInputElementByLabel(labelText);
        // Scroll the element into view before interacting with it
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", inputElement);
        inputElement.clear();
        inputElement.sendKeys(textToEnter);
    }

    /**
     * Selects a class from the dropdown menu.
     * @param className The name of the class to select (e.g., "Economic Class", "First Class").
     */
    public void selectClass(String className) {
        WebElement classSelectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(classDropdown));
        Select classSelect = new Select(classSelectElement);
        classSelect.selectByVisibleText(className);
    }
    
    /**
     * Enters the number of passengers.
     * @param numberOfPassengers The number of passengers to enter.
     */
    public void enterNumberOfPassengers(int numberOfPassengers) {
        WebElement passengersInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passengersInput));
        // Scroll the element into view before interacting with it
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", passengersInputElement);
        passengersInputElement.clear();
        passengersInputElement.sendKeys(String.valueOf(numberOfPassengers));
    }

    /**
     * Clicks the "Book Now" button.
     */
    public void clickBookNowButton() {
        WebElement bookNowButtonElement = wait.until(ExpectedConditions.elementToBeClickable(bookNowButton));
        // Scroll the element into view before clicking
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bookNowButtonElement);
        bookNowButtonElement.click();
    }

    /**
     * Gets and accepts an alert message, if one is present.
     * @return The text of the alert.
     */
    public String getAndAcceptAlert() {
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }

    /**
     * Gets the confirmation message from the page.
     * @return The text of the confirmation message.
     */
    public String getConfirmationMessage() {
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage));
        // Scroll the element into view before getting its text
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", messageElement);
        return messageElement.getText();
    }
}
