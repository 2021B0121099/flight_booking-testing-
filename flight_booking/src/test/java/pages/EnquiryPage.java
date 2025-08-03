package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

/**
 * Page Object Model (POM) for the Enquiry page.
 * This class contains all the locators and methods to interact with the enquiry form.
 * This version of the code has removed the JavaScript scrolling functionality as it was not required.
 */
public class EnquiryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators for the input fields and button
    private final By fullNameInput = By.xpath("//*[@id='name']");
    private final By emailAddressInput = By.xpath("//*[@id='email']");
    private final By phoneNumberInput = By.xpath("//*[@id='phone']");
    private final By subjectInput = By.xpath("//*[@id='subject']");
    private final By messageInput = By.xpath("//*[@id='message']");
    private final By sendButton = By.xpath("//*[@id='submit']");

    // Locator for the confirmation message that appears after clicking send.
    private final By confirmationMessage = By.xpath("//*[@id='success-msg']");

    public EnquiryPage(WebDriver driver) {
        this.driver = driver;
        // Using Duration.ofSeconds() for compatibility with Selenium 3.
        this.wait = new WebDriverWait(driver, 5);
    }

    /**
     * Navigates to the specified URL of the enquiry page.
     *
     * @param url The URL of the enquiry page.
     */
    public void navigateTo(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(fullNameInput));
    }
    
    /**
     * Finds and returns a web element for a text input field by its associated label.
     * @param fieldLabel The label of the field (e.g., "FULL NAME").
     * @return The WebElement of the input field.
     */
    private By getInputElementByLabel(String fieldLabel) {
        String field = fieldLabel.toUpperCase();
        if (field.equals("FULL NAME")) {
            return fullNameInput;
        } else if (field.equals("EMAIL ADDRESS")) {
            return emailAddressInput;
        } else if (field.equals("PHONE NUMBER")) {
            return phoneNumberInput;
        } else if (field.equals("SUBJECT")) {
            return subjectInput;
        } else if (field.equals("MESSAGE")) {
            return messageInput;
        } else {
            throw new IllegalArgumentException("Invalid field label: " + fieldLabel);
        }
    }

    /**
     * Enters a value into an input field identified by its label.
     * Includes a retry mechanism to ensure the text is correctly entered.
     *
     * @param fieldLabel The label of the field (e.g., "FULL NAME").
     * @param value      The value to be entered.
     */
    public void enterTextByLabel(String fieldLabel, String value) {
        By locator = getInputElementByLabel(fieldLabel);
        WebElement element = null;

        // Use a loop to retry entering text if the value is not set correctly
        for (int i = 0; i < 3; i++) {
            try {
                element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                element.clear();
                element.sendKeys(value);
                // Verify if the value was successfully entered
                if (value.equals(element.getAttribute("value"))) {
                    System.out.println("Successfully entered '" + value + "' into the field: " + fieldLabel);
                    return; // Exit the method on success
                }
            } catch (TimeoutException e) {
                System.err.println("Timeout while waiting for element " + fieldLabel + ". Retrying...");
            }
        }
        // If the loop completes without success, throw an exception
        throw new RuntimeException("Failed to enter text into element after multiple retries: " + fieldLabel);
    }

    /**
     * Clicks the "SEND" button.
     */
    public void clickSendButton() {
        WebElement sendBtn = wait.until(ExpectedConditions.elementToBeClickable(sendButton));
        sendBtn.click();
    }

    /**
     * Gets the confirmation message text from the page.
     *
     * @return The text of the confirmation message.
     */
    public String getConfirmationMessage() {
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage));
        return messageElement.getText();
    }
}