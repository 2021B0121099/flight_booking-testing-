package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
//import java.time.Duration;

/**
 * A factory class for managing WebDriver instances.
 * It provides a single point of entry for getting and quitting the driver.
 * This version is configured to only use Firefox and is compatible with Selenium 3.
 */
public class WebDriverFactory {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static long explicitWaitTime;

    static {
        try {
            ConfigReader.loadProperties();
            explicitWaitTime = Long.parseLong(ConfigReader.getProperty("explicitWaitTime"));
        } catch (IOException e) {
            System.err.println("Error loading configuration properties: " + e.getMessage());
            // It's crucial to handle this error or the tests won't run.
            throw new RuntimeException("Could not load config.properties", e);
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
        }
    }

    public static WebDriverWait getWebDriverWait() {
        if (wait == null) {
            wait = new WebDriverWait(driver,explicitWaitTime);
        }
        return wait;
    }

    /**
     * Initializes the WebDriver for Firefox.
     * The path to the geckodriver executable must be specified.
     */
    private static void initializeDriver() {
        // IMPORTANT: Replace "path/to/your/geckodriver.exe" with the actual path
        // to the geckodriver executable on your machine.
        // You can also add the driver to your system's PATH variable instead of setting it here.
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\shwet\\OneDrive\\Desktop\\COGNIZANT NOTES\\Ecllipse\\geckodriver-v0.36.0-win32\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    public static long getExplicitWaitTime() {
        return explicitWaitTime;
    }
}
