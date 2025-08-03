package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/features/login.feature", "src/test/resources/features/TicketBooking.feature","src/test/resources/features/FlightSearch.feature","src/test/resources/features/Enquiry.feature"},
		glue = {"stepDefinitions", "hooks"},
		plugin = {"pretty", "html:target/cucumber-reports.html","summary","json:target/cucumber-reports/cucumber.json"},
		//tags = "@UserLogin or @FlightBooking or @EnquiryPage or @FlightSearch",

		monochrome = true

		)
public class TestRunner {
}

