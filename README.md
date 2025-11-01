# EcommerceAutomation

## Project Description
Automated testing framework for an e-commerce application using Selenium, TestNG, Cucumber, and Maven.

## Tools & Technologies
- Java 21
- Selenium WebDriver
- TestNG
- Cucumber
- Maven
- Eclipse IDE
- Sauce Labs (optional for cross-browser testing)

## Test Reports
- Extent Reports are generated in the `test-output/extent-report/` folder after test execution.
- Open `index.html` in a browser to view detailed test results.

## Project Structure
```
EcommerceAutomation/
│
├── src/
│   ├── test/
│   │   ├── java/
│   │   │   ├── com/ssk/qa/base/
│   │   │   │   └── BaseTest.java
│   │   │   │
│   │   │   ├── com/ssk/qa/pages/
│   │   │   │   ├── CartPage.java
│   │   │   │   ├── CheckoutPage.java
│   │   │   │   ├── LoginPage.java
│   │   │   │   └── ProductsPage.java
│   │   │   │
│   │   │   ├── com/ssk/qa/runners/
│   │   │   │   └── TestRunner.java
│   │   │   │
│   │   │   ├── com/ssk/qa/stepdefinitions/
│   │   │   │   ├── EcommerceSteps.java
│   │   │   │   ├── Hooks.java
│   │   │   │   └── LoginSteps.java
│   │   │   │
│   │   │   ├── com/ssk/qa/tests/
│   │   │   │   └── LoginTest.java
│   │   │   │
│   │   │   └── com/ssk/qa/utils/
│   │   │       └── ExtentReportManager.java
│   │   │
│   │   └── resources/
│   │       └── features/
│   │           └── Ecommerce.feature
│   │
│   └── main/   (optional if backend logic exists)
│
├── pom.xml
├── testng.xml
├── target/
├── test-output/
├── JRE System Library [JavaSE-17]
└── Maven Dependencies
```


## How to Run
- Run all tests using `testng.xml` for full regression.
- Run specific scenarios via Cucumber Runner for BDD-style workflows.

## Notes
- Page Objects contain reusable actions.
- Step definitions call Page Objects to implement business workflows (BDD-based).
- TestNG tests validate core logic and assertions.

## Project Status
- Work in progress: Core automation framework is implemented.
- Additional test scenarios, reporting enhancements, and cross-browser support are being added.
