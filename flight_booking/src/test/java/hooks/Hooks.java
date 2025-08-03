package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.WebDriverFactory;

/**
 * This class contains Cucumber hooks for setting up and tearing down the test environment.
 */
public class Hooks {

    @Before
    public void setup() {
        // Initializes the WebDriver instance before each scenario.
        WebDriverFactory.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        // Captures a screenshot if the scenario fails and attaches it to the report.
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) WebDriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed Screenshot");
        }
        // Quits the WebDriver instance after each scenario.
        WebDriverFactory.quitDriver();
    }
}
