# EcommerceAutomation – Test Automation Framework
A complete end-to-end automation framework designed for modern QA engineering.
Built with a powerful blend of Selenium, TestNG, Cucumber BDD, Page Object Model, Maven, Log4j2, Excel data-driven execution, and Jenkins CI/CD.

## Project Overview

### This framework automates workflows on https://www.saucedemo.com, validating:
- User login
- Product selection
- Adding items to cart
- Checkout process
- Full order workflow
- Logout functionality
- Data-driven user tests
- Parallel cross-browser execution
- BDD scenario execution
- CI/CD-ready TestNG suite
All backed by extensive reporting, logging, retries, and screenshots.

### Key Features
- TestNG Framework (Primary Automation Suite)
    - Page Object Model (POM)
    - Parallel execution
    - Retry Analyzer (Auto re-run flaky tests)
    - Screenshot capture on failure
    - Log4j2 structured logging
    - Extent Reports (beautiful HTML report)
    - Excel-driven test inputs
    - Cross-browser: Chrome / Edge
- Cucumber BDD (Behavior-Driven Layer)
    - Gherkin scenarios
    - Hooks for setup/cleanup
    - Integrated with Selenium POM pages
    - Separate report under target/
    - CI/CD Ready (Jenkins + GitHub)
    - Maven-driven automated execution
    - HTML reports published after every build
    - Fully local Jenkins integration

### Tech Stack
```
----------------------------------------------------------------
| Component                         | Version / Tool           |
|-----------------------------------|--------------------------|
| Java                              | 17                       |
| Selenium WebDriver                | 4.14.0                   |
| TestNG                            | 7.8.0                    |
| Cucumber (TestNG integration)     | 7.30.0                   |
| Maven                             | Build/Dependency Manager |
| Log4j2                            | Logging                  |
| ExtentReports                     | 5.0.9                    |
| Apache POI                        | Excel test data          |
| WebDriverManager                  | Driver setup             |
| Jenkins                           | CI/CD                    |
----------------------------------------------------------------
```

## Project Structure
```
EcommerceAutomation/
│
├── src/test/java/
│   ├── com.ssk.qa.base/
│   │     ├── BaseTest.java
│   │     └── SetupTest.java
│   │
│   ├── com.ssk.qa.pages/
│   │     ├── LoginPage.java
│   │     ├── ProductsPage.java
│   │     ├── CartPage.java
│   │     ├── CheckoutPage.java
│   │     └── LogoutPage.java
│   │
│   ├── com.ssk.qa.tests/
│   │     └── EndToEndTest.java
│   │
│   ├── com.ssk.qa.stepdefinitions/
│   │     ├── EcommerceSteps.java
│   │     └── Hooks.java
│   │
│   ├── com.ssk.qa.runners/
│   │     └── TestRunner.java
│   │
│   └── com.ssk.qa.utils/
│         ├── ConfigReader.java
│         ├── ExcelUtils.java
│         ├── ExtentReportManager.java
│         ├── ScreenshotManager.java
│         ├── RetryAnalyzer.java
│         ├── LoggerManager.java
│         └── TestListener.java
│
├── src/test/resources/
│   ├── config/
│   │     └── config.properties
│   ├── features/
│   │     └── Ecommerce.feature
│   ├── testdata/
│   │     └── LoginData.xlsx
│   ├── logs/
│   │     └── test-log.log
│   └── reports/
│         ├── screenshots/
│         └── ExtentReports (timestamped)
│
├── testng.xml
├── pom.xml
└── README.md
```

## Running the Tests

### Run the TestNG Framework (Full Regression Suite)
> [!INFO]
> To run all TestNG test cases, use:
>
> ```
> mvn clean test -DsuiteXmlFile=testng.xml
> ```


- This runs:
`All TestNG tests`, `Retry Analyzer`, `Screenshots on failure` 
`Log4j2 logging`, `Extent HTML report generation`

- Report Location: `/reports/Ecommerce_Automation_Report_<timestamp>.html`

### Run Cucumber Tests (BDD)
``` 
mvn test -Dcucumber.options="--plugin pretty" 
```
or execute: `TestRunner.java`

- Cucumber HTML Report Location: `/target/cucumber-html-report/`

## What’s Tested
`Multi-user login test (Excel-driven)`, `Add to cart`, `Checkout workflow`, `Logout`
`Full end-to-end scenarios with parallel execution`

`
## CI/CD – Jenkins Pipeline
This project supports:
✔ Manual trigger ✔ GitHub automatic trigger ✔ Publishing HTML reports
✔ Running TestNG suite via Jenkins ✔ Running Cucumber (optional separate job)
`

## Future Enhancements
Docker-based Selenium Grid, Allure Reporting, Distributed parallel execution, API + UI hybrid testing, Additional workflows & data sets
