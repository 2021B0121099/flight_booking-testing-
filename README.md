# FLIGHT_BOOKING
#Table of Contents
## About the Project

Framework Features

Technologies Used

Project Structure

Setup and Installation

Running Tests

Contributing

Contact

# About the Project
This is a comprehensive and reusable automation framework designed for BDD. The core idea is to define test scenarios in a human-readable format using Gherkin syntax (Given, When, Then) and then implement the corresponding test logic in step definition files. This approach enhances collaboration between technical and non-technical stakeholders and improves the clarity of test cases.

The framework is built around the following key principles from the guideline document:

->Modular and Reusable Code: Implementing the Page Object Model (POM) to keep locators and actions separate from test logic.

->Robust Synchronization: Using explicit waits to handle dynamic web elements effectively.

->Comprehensive Reporting: Integrating logging and reporting tools to provide clear and detailed test results with screenshots on failure.

# Framework Features
BDD with Gherkin: Test scenarios are written in plain language, making them easy to understand.

Page Object Model (POM): A design pattern that improves code readability and maintainability by centralizing page-specific interactions.

Configuration Management: Externalized configuration for browser type, URLs, and other environment-specific settings.

Robust Wait Strategies: Implements explicit waits to avoid unreliable tests caused by hardcoded sleeps.

Extensible Reporting: Configured to generate detailed reports with screenshots for failed scenarios.

# Technologies Used
Java: The primary programming language.

Maven: For project management and dependency handling.

Selenium WebDriver: For browser automation.

Cucumber: The BDD tool that links Gherkin feature files to Java step definitions.

JUnit: The underlying test framework.

log4j: For logging and tracking test execution.

Reporting Libraries: (e.g., Cucumber HTML Reports, ExtentReports) To generate visual test reports.

# Setup and Installation
## Prerequisites:

Java Development Kit (JDK) 8 or higher installed.

Maven installed.

Git installed.

Eclipse.

Clone the Repository:

git clone [repository_url_here]
cd [repository_folder_name]

Import into your IDE:

Open your IDE and import the project as a Maven or Gradle project. The IDE should automatically handle dependencies.

# Running Tests
Tests are executed via a Cucumber Runner class or through Maven commands.

From your IDE:
Locate the test runner class in the runners package (e.g., TestRunner.java).

Right-click on the runner class and select Run As -> JUnit Test (or the equivalent for your IDE and configuration).

From the Command Line (using Maven):
Open your terminal and navigate to the project's root directory.

Execute the following command:

mvn clean test

This command will run all feature files configured in your pom.xml or test runner.

# Contributing
Contributions are welcome! Please follow the guidelines in the provided documentation.

# Contact
For any questions or inquiries about this project, please open an issue in the GitHub repository.
