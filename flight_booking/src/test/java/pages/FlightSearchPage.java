package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;

/**
 * This class represents the Page Object Model (POM) for the Flight Search page.
 * It has been updated to support the generic step definitions provided by the user.
 */
public class FlightSearchPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators for web elements on the flight search page
    private final By searchByFlightNumberInput = By.xpath("//*[@id='myInputnumber']");
    private final By searchByFlightNumberButton = By.xpath("//*[@id='mySearchnumber']");

    private final By searchByFlightNameInput = By.xpath("//*[@id='myInputname']");
    private final By searchByFlightNameButton = By.xpath("//*[@id='mySearchname']");

    private final By searchByFlightTypeInput = By.xpath("//*[@id='myInputtype']");
    private final By searchByFlightTypeButton = By.xpath("//*[@id='mySearchtype']");
    
    private final By searchResultsContainer = By.id("//*[@id='tableHeaderRow']");

    public FlightSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
    }

    /**
     * Navigates to the specified URL.
     * @param url The URL of the flight search page.
     */
    public void navigateTo(String url) {
        driver.get(url);
    }

    /**
     * Enters the flight number into the input field.
     * @param flightNumber The flight number.
     */
    public void enterFlightNumber(String flightNumber) {
        WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchByFlightNumberInput));
        inputElement.clear();
        inputElement.sendKeys(flightNumber);
    }
    
    /**
     * Enters the flight name into the input field.
     * @param flightName The flight name.
     */
    public void enterFlightName(String flightName) {
        WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchByFlightNameInput));
        inputElement.clear();
        inputElement.sendKeys(flightName);
    }
    
    /**
     * Enters the flight type into the input field.
     * @param flightType The flight type.
     */
    public void enterFlightType(String flightType) {
        WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchByFlightTypeInput));
        inputElement.clear();
        inputElement.sendKeys(flightType);
    }

    /**
     * Clicks the "Search By Flight Number" button.
     */
    public void clickSearchByFlightNumberButton() {
        WebElement buttonElement = wait.until(ExpectedConditions.elementToBeClickable(searchByFlightNumberButton));
        buttonElement.click();
    }
    
    /**
     * Clicks the "Search By Flight Name" button.
     */
    public void clickSearchByFlightNameButton() {
        WebElement buttonElement = wait.until(ExpectedConditions.elementToBeClickable(searchByFlightNameButton));
        buttonElement.click();
    }
    
    /**
     * Clicks the "Search By Flight Type" button.
     */
    public void clickSearchByFlightTypeButton() {
        WebElement buttonElement = wait.until(ExpectedConditions.elementToBeClickable(searchByFlightTypeButton));
        buttonElement.click();
    }

    /**
     * Checks if the search results container is displayed and has content.
     * @return true if search results are visible, false otherwise.
     */
    public boolean areSearchResultsDisplayed() {
        try {
            WebElement resultsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsContainer));
            // Check if the results container has any rows.
            return resultsElement.isDisplayed() && !resultsElement.findElements(By.tagName("tr")).isEmpty();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
