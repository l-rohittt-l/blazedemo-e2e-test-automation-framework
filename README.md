# BlazeDemo E2E Test Automation Framework

![Java](https://img.shields.io/badge/Java-11-orange?logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-4.18.1-43B02A?logo=selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-7.9.0-red)
![Maven](https://img.shields.io/badge/Maven-3.6+-C71A36?logo=apachemaven&logoColor=white)
![Jenkins](https://img.shields.io/badge/CI-Jenkins-D24939?logo=jenkins&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-blue)

Selenium WebDriver + TestNG end-to-end automation framework for the [BlazeDemo](https://blazedemo.com) flight booking application, built as a capstone project.

---

## Table of Contents

- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Project Structure](#project-structure)
- [Test Cases](#test-cases)
- [Configuration](#configuration)
- [Test Data](#test-data)
- [Reports & Artifacts](#reports--artifacts)
- [CI / Jenkins](#ci--jenkins)
- [Framework Features](#framework-features)

---

## Tech Stack

| Layer | Technology | Version |
|---|---|---|
| Language | Java | 11 |
| Browser Automation | Selenium WebDriver | 4.18.1 |
| Test Framework | TestNG | 7.9.0 |
| Driver Management | WebDriverManager | 5.8.0 |
| Test Data (Excel) | Apache POI | 5.2.5 |
| Test Data (CSV) | OpenCSV | 5.9 |
| Test Data (DB) | MySQL Connector/J | 8.0.33 |
| Logging | Log4j2 + SLF4J | 2.23.1 / 2.0.12 |
| Build | Maven + Surefire Plugin | 3.2.5 |
| CI | Jenkins (declarative pipeline) | — |

---

## Prerequisites

- Java 11+
- Maven 3.6+
- Google Chrome (latest stable — ChromeDriver is managed automatically)
- MySQL 8.0+ (only required when `data.source=database`)

---

## Quick Start

```bash
# Clone the repository
git clone https://github.com/l-rohittt-l/blazedemo-e2e-test-automation-framework.git
cd blazedemo-e2e-test-automation-framework

# Run all tests (headed, Excel data source)
mvn clean test

# Run headless (CI-friendly)
mvn clean test -Dbrowser.headless=true

# Switch data source at runtime
mvn clean test -Ddata.source=csv
mvn clean test -Ddata.source=database
```

---

## Project Structure

```
src/
├── main/java/
│   ├── data/
│   │   ├── ExcelDataReader.java        # Reads .xlsx test data via Apache POI
│   │   ├── CsvDataReader.java          # Reads .csv test data via OpenCSV
│   │   └── DatabaseDataReader.java     # Reads test data from MySQL
│   ├── driver/
│   │   └── DriverManager.java          # ThreadLocal ChromeDriver lifecycle
│   ├── pages/                          # Page Object Model classes
│   │   ├── HomePage.java               # City dropdowns + Find Flights button
│   │   ├── FlightsPage.java            # Results table + Choose This Flight
│   │   ├── PurchasePage.java           # Passenger/payment form + Purchase Flight
│   │   └── ConfirmationPage.java       # "Thank you" heading verification
│   └── utils/
│       ├── ConfigReader.java           # Loads config.properties
│       └── WaitUtils.java              # Explicit wait helpers (no Thread.sleep)
└── test/java/
    ├── base/
    │   └── BaseTest.java               # @BeforeMethod / @AfterMethod setup & teardown
    ├── listeners/
    │   └── ScreenshotListener.java     # Auto-screenshot on test failure + retry logic
    └── tests/
        ├── FlightBookingTest.java      # Positive E2E booking flow (data-driven)
        └── FlightBookingNegativeTest.java  # Negative / boundary tests

src/test/resources/
├── config.properties                   # All tuneable settings
├── log4j2.xml                          # Logging configuration
└── testdata/
    ├── bookings.xlsx                   # Excel test data
    ├── bookings.csv                    # CSV test data
    └── create_table.sql                # MySQL schema + seed data

screenshots/    # Failure screenshots (git-ignored)
logs/           # Rolling log files (git-ignored)
Jenkinsfile     # CI pipeline definition
testng.xml      # TestNG suite configuration
```

---

## Test Cases

### Positive Tests — `FlightBookingTest`

Data-driven: one test execution per row in the active data source.

| Step | Verification |
|---|---|
| Select departure and destination cities | — |
| Click Find Flights | Flights results table is visible |
| Select first available flight | Purchase page heading contains reservation text |
| Verify total cost | Cost value is displayed on purchase page |
| Fill passenger and payment details | — |
| Click Purchase Flight | Confirmation page shows "Thank you for your purchase today!" |

### Negative Tests — `FlightBookingNegativeTest`

| Test | Scenario | What it documents |
|---|---|---|
| `testConfirmationHeadingAbsentOnFlightsPage` | Navigate to flights results page | "Thank you" heading is NOT shown on the wrong page |
| `testEmptyFormSubmission` | Submit purchase form with no data | BlazeDemo has no server-side form validation — will fail if validation is ever added |
| `testSameCitySearch` | Search Dublin→Dublin / Cairo→Cairo | BlazeDemo does not block same-city bookings (known AUT gap) |

---

## Configuration

All settings live in [`src/test/resources/config.properties`](src/test/resources/config.properties):

```properties
base.url=https://blazedemo.com
browser.timeout=30          # Explicit wait timeout in seconds
browser.headless=false      # Set true for CI / headless runs
retry.count=2               # Max retry attempts on test failure
data.source=excel           # Options: excel | csv | database

# MySQL — only needed when data.source=database
db.url=jdbc:mysql://localhost:3306/blazedemo_testdata
db.username=root
db.password=root
db.table=flight_bookings
```

---

## Test Data

### Excel / CSV

Place files at `src/test/resources/testdata/`. Expected columns:

`fromCity` · `toCity` · `name` · `address` · `city` · `state` · `zipCode` · `cardType` · `cardNumber` · `cardMonth` · `cardYear` · `nameOnCard`

### MySQL Database

```bash
mysql -u root -p < src/test/resources/testdata/create_table.sql
```

Then set `data.source=database` in `config.properties`.

---

## Reports & Artifacts

| Artifact | Location |
|---|---|
| TestNG HTML report | `target/surefire-reports/index.html` |
| Surefire XML results | `target/surefire-reports/*.xml` |
| Failure screenshots | `screenshots/{method}[city-city]_{timestamp}.png` |
| Application log | `logs/automation.log` (rolling, last 10 archives kept) |

---

## CI / Jenkins

The [`Jenkinsfile`](Jenkinsfile) defines a three-stage declarative pipeline:

1. **Checkout** — pulls source from SCM
2. **Build & Test** — runs `mvn clean test`, publishes TestNG HTML report
3. **Archive Artifacts** — archives failure screenshots and Surefire XML

Requires Jenkins tools configured as `Maven` and `JDK11`. Set `browser.headless=true` for agents without a display.

---

## Framework Features

- **Page Object Model** — tests contain no raw locators; all selectors live in page classes
- **ThreadLocal DriverManager** — thread-safe driver management, ready for parallel execution
- **Data-driven testing** — switch between Excel, CSV, or MySQL via a single config property
- **Automatic retry** — failed tests retry up to `retry.count` times before being marked failed
- **Auto-screenshot on failure** — `ScreenshotListener` captures the browser state at the point of failure
- **Explicit waits only** — no `Thread.sleep()` calls anywhere; all waits go through `WaitUtils`
- **Structured logging** — INFO/DEBUG log lines for every major action via SLF4J + Log4j2
"# blazedemo-e2e-test-automation-framework" 
"# blazedemo-e2e-test-automation-framework" 
