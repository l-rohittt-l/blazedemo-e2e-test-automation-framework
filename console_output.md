[RemoteTestNG] detected TestNG version 7.9.0
2026-03-09 15:00:40 [main] INFO  tests.FlightBookingTest - Loading test data from source: excel
2026-03-09 15:00:40 [main] INFO  data.ExcelDataReader - Reading Excel test data from testdata/bookings.xlsx
2026-03-09 15:00:40 [main] INFO  data.ExcelDataReader - Excel data read complete — 2 data row(s) loaded
2026-03-09 15:00:41 [main] INFO  base.BaseTest - === Test setup started ===
2026-03-09 15:00:41 [main] INFO  driver.DriverManager - Setting up ChromeDriver for thread: main
Mar 09, 2026 3:00:43 PM org.openqa.selenium.devtools.CdpVersionFinder findNearestMatch
WARNING: Unable to find CDP implementation matching 145
Mar 09, 2026 3:00:43 PM org.openqa.selenium.chromium.ChromiumDriver lambda$new$5
WARNING: Unable to find version of CDP to use for 145.0.7632.160. You may need to include a dependency on a specific version of the CDP using something similar to `org.seleniumhq.selenium:selenium-devtools-v86:4.18.1` where the version ("v86") matches the version of the chromium-based browser you're using and the version number of the artifact is the same as Selenium's.
2026-03-09 15:00:43 [main] INFO  driver.DriverManager - ChromeDriver initialised successfully.
2026-03-09 15:00:44 [main] INFO  base.BaseTest - Navigated to: https://blazedemo.com
2026-03-09 15:00:44 [main] INFO  tests.FlightBookingTest - === Starting flight booking test: Philadelphia -> Buenos Aires for John Smith ===
2026-03-09 15:00:44 [main] INFO  pages.HomePage - Selecting departure city: Philadelphia
2026-03-09 15:00:44 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.name: fromPort
2026-03-09 15:00:44 [main] INFO  pages.HomePage - Departure city selected: Philadelphia
2026-03-09 15:00:44 [main] INFO  pages.HomePage - Selecting destination city: Buenos Aires
2026-03-09 15:00:44 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.name: toPort
2026-03-09 15:00:44 [main] INFO  pages.HomePage - Destination city selected: Buenos Aires
2026-03-09 15:00:44 [main] INFO  pages.HomePage - Clicking Find Flights button
2026-03-09 15:00:44 [main] DEBUG utils.WaitUtils - Waiting for clickability of element: By.cssSelector: input[value='Find Flights']
2026-03-09 15:00:45 [main] INFO  pages.HomePage - Find Flights button clicked
2026-03-09 15:00:45 [main] INFO  pages.FlightsPage - Verifying flights results table is visible
2026-03-09 15:00:45 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.cssSelector: table.table
2026-03-09 15:00:45 [main] INFO  pages.FlightsPage - Flights table visible with 5 result row(s)
2026-03-09 15:00:45 [main] INFO  tests.FlightBookingTest - Flights table verified — selecting first available flight
2026-03-09 15:00:45 [main] INFO  pages.FlightsPage - Selecting first available flight
2026-03-09 15:00:45 [main] DEBUG utils.WaitUtils - Waiting for clickability of element: By.cssSelector: input[value='Choose This Flight']
2026-03-09 15:00:46 [main] INFO  pages.FlightsPage - First flight selected — navigating to purchase page
2026-03-09 15:00:46 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.cssSelector: h2
2026-03-09 15:00:46 [main] INFO  pages.PurchasePage - Purchase page heading: Your flight from TLV to SFO has been reserved.
2026-03-09 15:00:46 [main] INFO  tests.FlightBookingTest - Purchase page heading verified: Your flight from TLV to SFO has been reserved.
2026-03-09 15:00:46 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.xpath: //p[contains(text(),'Total Cost:')]/em
2026-03-09 15:00:46 [main] INFO  pages.PurchasePage - Total cost displayed: 914.76
2026-03-09 15:00:46 [main] INFO  tests.FlightBookingTest - Total cost verified: 914.76
2026-03-09 15:00:46 [main] INFO  pages.PurchasePage - Filling passenger details for: John Smith
2026-03-09 15:00:46 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.id: inputName
2026-03-09 15:00:46 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.id: address
2026-03-09 15:00:46 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.id: city
2026-03-09 15:00:46 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.id: state
2026-03-09 15:00:47 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.id: zipCode
2026-03-09 15:00:47 [main] INFO  pages.PurchasePage - Selecting card type: Visa
2026-03-09 15:00:47 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.id: cardType
2026-03-09 15:00:47 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.id: creditCardNumber
2026-03-09 15:00:47 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.id: creditCardMonth
2026-03-09 15:00:47 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.id: creditCardYear
2026-03-09 15:00:48 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.id: nameOnCard
2026-03-09 15:00:48 [main] INFO  pages.PurchasePage - Passenger details filled successfully
2026-03-09 15:00:48 [main] INFO  pages.PurchasePage - Clicking Purchase Flight button
2026-03-09 15:00:48 [main] DEBUG utils.WaitUtils - Waiting for clickability of element: By.cssSelector: input[value='Purchase Flight']
2026-03-09 15:00:49 [main] INFO  pages.PurchasePage - Purchase Flight button clicked
2026-03-09 15:00:49 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.xpath: //h1[contains(text(),'Thank you')]
2026-03-09 15:00:49 [main] INFO  pages.ConfirmationPage - Confirmation page heading: Thank you for your purchase today!
2026-03-09 15:00:49 [main] INFO  tests.FlightBookingTest - === Flight booking test passed for John Smith ===
2026-03-09 15:00:49 [main] INFO  base.BaseTest - === Test teardown started ===
2026-03-09 15:00:49 [main] INFO  driver.DriverManager - Quitting ChromeDriver for thread: main
2026-03-09 15:00:49 [main] INFO  driver.DriverManager - ChromeDriver quit and ThreadLocal cleared.
2026-03-09 15:00:49 [main] INFO  base.BaseTest - === Test teardown complete ===
2026-03-09 15:00:49 [main] INFO  base.BaseTest - === Test setup started ===
2026-03-09 15:00:49 [main] INFO  driver.DriverManager - Setting up ChromeDriver for thread: main
Mar 09, 2026 3:00:50 PM org.openqa.selenium.devtools.CdpVersionFinder findNearestMatch
WARNING: Unable to find CDP implementation matching 145
Mar 09, 2026 3:00:50 PM org.openqa.selenium.chromium.ChromiumDriver lambda$new$5
WARNING: Unable to find version of CDP to use for 145.0.7632.160. You may need to include a dependency on a specific version of the CDP using something similar to `org.seleniumhq.selenium:selenium-devtools-v86:4.18.1` where the version ("v86") matches the version of the chromium-based browser you're using and the version number of the artifact is the same as Selenium's.
2026-03-09 15:00:50 [main] INFO  driver.DriverManager - ChromeDriver initialised successfully.
2026-03-09 15:00:51 [main] INFO  base.BaseTest - Navigated to: https://blazedemo.com
2026-03-09 15:00:51 [main] INFO  tests.FlightBookingTest - === Starting flight booking test: Boston -> Rome for Jane Doe ===
2026-03-09 15:00:51 [main] INFO  pages.HomePage - Selecting departure city: Boston
2026-03-09 15:00:51 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.name: fromPort
2026-03-09 15:00:51 [main] INFO  pages.HomePage - Departure city selected: Boston
2026-03-09 15:00:51 [main] INFO  pages.HomePage - Selecting destination city: Rome
2026-03-09 15:00:51 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.name: toPort
2026-03-09 15:00:51 [main] INFO  pages.HomePage - Destination city selected: Rome
2026-03-09 15:00:51 [main] INFO  pages.HomePage - Clicking Find Flights button
2026-03-09 15:00:51 [main] DEBUG utils.WaitUtils - Waiting for clickability of element: By.cssSelector: input[value='Find Flights']
2026-03-09 15:00:52 [main] INFO  pages.HomePage - Find Flights button clicked
2026-03-09 15:00:52 [main] INFO  pages.FlightsPage - Verifying flights results table is visible
2026-03-09 15:00:52 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.cssSelector: table.table
2026-03-09 15:00:52 [main] INFO  pages.FlightsPage - Flights table visible with 5 result row(s)
2026-03-09 15:00:52 [main] INFO  tests.FlightBookingTest - Flights table verified — selecting first available flight
2026-03-09 15:00:52 [main] INFO  pages.FlightsPage - Selecting first available flight
2026-03-09 15:00:52 [main] DEBUG utils.WaitUtils - Waiting for clickability of element: By.cssSelector: input[value='Choose This Flight']
2026-03-09 15:00:53 [main] INFO  pages.FlightsPage - First flight selected — navigating to purchase page
2026-03-09 15:00:53 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.cssSelector: h2
2026-03-09 15:00:53 [main] INFO  pages.PurchasePage - Purchase page heading: Your flight from TLV to SFO has been reserved.
2026-03-09 15:00:53 [main] INFO  tests.FlightBookingTest - Purchase page heading verified: Your flight from TLV to SFO has been reserved.
2026-03-09 15:00:53 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.xpath: //p[contains(text(),'Total Cost:')]/em
2026-03-09 15:00:53 [main] INFO  pages.PurchasePage - Total cost displayed: 914.76
2026-03-09 15:00:53 [main] INFO  tests.FlightBookingTest - Total cost verified: 914.76
2026-03-09 15:00:53 [main] INFO  pages.PurchasePage - Filling passenger details for: Jane Doe
2026-03-09 15:00:53 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.id: inputName
2026-03-09 15:00:53 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.id: address
2026-03-09 15:00:53 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.id: city
2026-03-09 15:00:53 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.id: state
2026-03-09 15:00:54 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.id: zipCode
2026-03-09 15:00:54 [main] INFO  pages.PurchasePage - Selecting card type: American Express
2026-03-09 15:00:54 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.id: cardType
2026-03-09 15:00:54 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.id: creditCardNumber
2026-03-09 15:00:54 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.id: creditCardMonth
2026-03-09 15:00:54 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.id: creditCardYear
2026-03-09 15:00:54 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.id: nameOnCard
2026-03-09 15:00:55 [main] INFO  pages.PurchasePage - Passenger details filled successfully
2026-03-09 15:00:55 [main] INFO  pages.PurchasePage - Clicking Purchase Flight button
2026-03-09 15:00:55 [main] DEBUG utils.WaitUtils - Waiting for clickability of element: By.cssSelector: input[value='Purchase Flight']
2026-03-09 15:00:55 [main] INFO  pages.PurchasePage - Purchase Flight button clicked
2026-03-09 15:00:55 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.xpath: //h1[contains(text(),'Thank you')]
2026-03-09 15:00:55 [main] INFO  pages.ConfirmationPage - Confirmation page heading: Thank you for your purchase today!
2026-03-09 15:00:55 [main] INFO  tests.FlightBookingTest - === Flight booking test passed for Jane Doe ===
2026-03-09 15:00:55 [main] INFO  base.BaseTest - === Test teardown started ===
2026-03-09 15:00:55 [main] INFO  driver.DriverManager - Quitting ChromeDriver for thread: main
2026-03-09 15:00:56 [main] INFO  driver.DriverManager - ChromeDriver quit and ThreadLocal cleared.
2026-03-09 15:00:56 [main] INFO  base.BaseTest - === Test teardown complete ===
2026-03-09 15:00:56 [main] INFO  base.BaseTest - === Test setup started ===
2026-03-09 15:00:56 [main] INFO  driver.DriverManager - Setting up ChromeDriver for thread: main
Mar 09, 2026 3:00:56 PM org.openqa.selenium.devtools.CdpVersionFinder findNearestMatch
WARNING: Unable to find CDP implementation matching 145
Mar 09, 2026 3:00:56 PM org.openqa.selenium.chromium.ChromiumDriver lambda$new$5
WARNING: Unable to find version of CDP to use for 145.0.7632.160. You may need to include a dependency on a specific version of the CDP using something similar to `org.seleniumhq.selenium:selenium-devtools-v86:4.18.1` where the version ("v86") matches the version of the chromium-based browser you're using and the version number of the artifact is the same as Selenium's.
2026-03-09 15:00:56 [main] INFO  driver.DriverManager - ChromeDriver initialised successfully.
2026-03-09 15:00:58 [main] INFO  base.BaseTest - Navigated to: https://blazedemo.com
2026-03-09 15:00:58 [main] INFO  tests.FlightBookingNegativeTest - === Negative test: confirmation heading must not appear on flights page ===
2026-03-09 15:00:58 [main] INFO  pages.HomePage - Selecting departure city: Philadelphia
2026-03-09 15:00:58 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.name: fromPort
2026-03-09 15:00:58 [main] INFO  pages.HomePage - Departure city selected: Philadelphia
2026-03-09 15:00:58 [main] INFO  pages.HomePage - Selecting destination city: Buenos Aires
2026-03-09 15:00:58 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.name: toPort
2026-03-09 15:00:58 [main] INFO  pages.HomePage - Destination city selected: Buenos Aires
2026-03-09 15:00:58 [main] INFO  pages.HomePage - Clicking Find Flights button
2026-03-09 15:00:58 [main] DEBUG utils.WaitUtils - Waiting for clickability of element: By.cssSelector: input[value='Find Flights']
2026-03-09 15:00:58 [main] INFO  pages.HomePage - Find Flights button clicked
2026-03-09 15:00:59 [main] INFO  tests.FlightBookingNegativeTest - === Confirmation heading correctly absent on flights results page ===
2026-03-09 15:00:59 [main] INFO  base.BaseTest - === Test teardown started ===
2026-03-09 15:00:59 [main] INFO  driver.DriverManager - Quitting ChromeDriver for thread: main
2026-03-09 15:00:59 [main] INFO  driver.DriverManager - ChromeDriver quit and ThreadLocal cleared.
2026-03-09 15:00:59 [main] INFO  base.BaseTest - === Test teardown complete ===
2026-03-09 15:00:59 [main] INFO  base.BaseTest - === Test setup started ===
2026-03-09 15:00:59 [main] INFO  driver.DriverManager - Setting up ChromeDriver for thread: main
Mar 09, 2026 3:01:00 PM org.openqa.selenium.devtools.CdpVersionFinder findNearestMatch
WARNING: Unable to find CDP implementation matching 145
Mar 09, 2026 3:01:00 PM org.openqa.selenium.chromium.ChromiumDriver lambda$new$5
WARNING: Unable to find version of CDP to use for 145.0.7632.160. You may need to include a dependency on a specific version of the CDP using something similar to `org.seleniumhq.selenium:selenium-devtools-v86:4.18.1` where the version ("v86") matches the version of the chromium-based browser you're using and the version number of the artifact is the same as Selenium's.
2026-03-09 15:01:00 [main] INFO  driver.DriverManager - ChromeDriver initialised successfully.
2026-03-09 15:01:01 [main] INFO  base.BaseTest - Navigated to: https://blazedemo.com
2026-03-09 15:01:01 [main] INFO  tests.FlightBookingNegativeTest - === Negative test: empty payment form submission ===
2026-03-09 15:01:01 [main] INFO  pages.HomePage - Selecting departure city: Philadelphia
2026-03-09 15:01:01 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.name: fromPort
2026-03-09 15:01:01 [main] INFO  pages.HomePage - Departure city selected: Philadelphia
2026-03-09 15:01:01 [main] INFO  pages.HomePage - Selecting destination city: Buenos Aires
2026-03-09 15:01:01 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.name: toPort
2026-03-09 15:01:01 [main] INFO  pages.HomePage - Destination city selected: Buenos Aires
2026-03-09 15:01:01 [main] INFO  pages.HomePage - Clicking Find Flights button
2026-03-09 15:01:01 [main] DEBUG utils.WaitUtils - Waiting for clickability of element: By.cssSelector: input[value='Find Flights']
2026-03-09 15:01:02 [main] INFO  pages.HomePage - Find Flights button clicked
2026-03-09 15:01:02 [main] INFO  pages.FlightsPage - Selecting first available flight
2026-03-09 15:01:02 [main] DEBUG utils.WaitUtils - Waiting for clickability of element: By.cssSelector: input[value='Choose This Flight']
2026-03-09 15:01:04 [main] INFO  pages.FlightsPage - First flight selected — navigating to purchase page
2026-03-09 15:01:04 [main] INFO  pages.PurchasePage - Clicking Purchase Flight button
2026-03-09 15:01:04 [main] DEBUG utils.WaitUtils - Waiting for clickability of element: By.cssSelector: input[value='Purchase Flight']
2026-03-09 15:01:04 [main] INFO  pages.PurchasePage - Purchase Flight button clicked
2026-03-09 15:01:04 [main] INFO  tests.FlightBookingNegativeTest - === Empty-form submission test passed — page navigated away from purchase.php ===
2026-03-09 15:01:04 [main] INFO  base.BaseTest - === Test teardown started ===
2026-03-09 15:01:04 [main] INFO  driver.DriverManager - Quitting ChromeDriver for thread: main
2026-03-09 15:01:05 [main] INFO  driver.DriverManager - ChromeDriver quit and ThreadLocal cleared.
2026-03-09 15:01:05 [main] INFO  base.BaseTest - === Test teardown complete ===
2026-03-09 15:01:05 [main] INFO  base.BaseTest - === Test setup started ===
2026-03-09 15:01:05 [main] INFO  driver.DriverManager - Setting up ChromeDriver for thread: main
Mar 09, 2026 3:01:06 PM org.openqa.selenium.devtools.CdpVersionFinder findNearestMatch
WARNING: Unable to find CDP implementation matching 145
Mar 09, 2026 3:01:06 PM org.openqa.selenium.chromium.ChromiumDriver lambda$new$5
WARNING: Unable to find version of CDP to use for 145.0.7632.160. You may need to include a dependency on a specific version of the CDP using something similar to `org.seleniumhq.selenium:selenium-devtools-v86:4.18.1` where the version ("v86") matches the version of the chromium-based browser you're using and the version number of the artifact is the same as Selenium's.
2026-03-09 15:01:06 [main] INFO  driver.DriverManager - ChromeDriver initialised successfully.
2026-03-09 15:01:07 [main] INFO  base.BaseTest - Navigated to: https://blazedemo.com
2026-03-09 15:01:07 [main] INFO  tests.FlightBookingNegativeTest - === Negative test: same city search Dublin -> Dublin ===
2026-03-09 15:01:07 [main] INFO  pages.HomePage - Selecting departure city: Dublin
2026-03-09 15:01:07 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.name: fromPort
2026-03-09 15:01:07 [main] WARN  listeners.RetryAnalyzer - Retrying test 'testSameCitySearch' — attempt 1/2
2026-03-09 15:01:07 [main] INFO  base.BaseTest - === Test teardown started ===
2026-03-09 15:01:07 [main] INFO  driver.DriverManager - Quitting ChromeDriver for thread: main
2026-03-09 15:01:07 [main] INFO  driver.DriverManager - ChromeDriver quit and ThreadLocal cleared.
2026-03-09 15:01:07 [main] INFO  base.BaseTest - === Test teardown complete ===
2026-03-09 15:01:07 [main] INFO  base.BaseTest - === Test setup started ===
2026-03-09 15:01:07 [main] INFO  driver.DriverManager - Setting up ChromeDriver for thread: main
Mar 09, 2026 3:01:08 PM org.openqa.selenium.devtools.CdpVersionFinder findNearestMatch
WARNING: Unable to find CDP implementation matching 145
Mar 09, 2026 3:01:08 PM org.openqa.selenium.chromium.ChromiumDriver lambda$new$5
WARNING: Unable to find version of CDP to use for 145.0.7632.160. You may need to include a dependency on a specific version of the CDP using something similar to `org.seleniumhq.selenium:selenium-devtools-v86:4.18.1` where the version ("v86") matches the version of the chromium-based browser you're using and the version number of the artifact is the same as Selenium's.
2026-03-09 15:01:08 [main] INFO  driver.DriverManager - ChromeDriver initialised successfully.
2026-03-09 15:01:09 [main] INFO  base.BaseTest - Navigated to: https://blazedemo.com
2026-03-09 15:01:09 [main] INFO  tests.FlightBookingNegativeTest - === Negative test: same city search Dublin -> Dublin ===
2026-03-09 15:01:09 [main] INFO  pages.HomePage - Selecting departure city: Dublin
2026-03-09 15:01:09 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.name: fromPort
2026-03-09 15:01:09 [main] WARN  listeners.RetryAnalyzer - Retrying test 'testSameCitySearch' — attempt 2/2
2026-03-09 15:01:09 [main] INFO  base.BaseTest - === Test teardown started ===
2026-03-09 15:01:09 [main] INFO  driver.DriverManager - Quitting ChromeDriver for thread: main
2026-03-09 15:01:10 [main] INFO  driver.DriverManager - ChromeDriver quit and ThreadLocal cleared.
2026-03-09 15:01:10 [main] INFO  base.BaseTest - === Test teardown complete ===
2026-03-09 15:01:10 [main] INFO  base.BaseTest - === Test setup started ===
2026-03-09 15:01:10 [main] INFO  driver.DriverManager - Setting up ChromeDriver for thread: main
Mar 09, 2026 3:01:10 PM org.openqa.selenium.devtools.CdpVersionFinder findNearestMatch
WARNING: Unable to find CDP implementation matching 145
Mar 09, 2026 3:01:10 PM org.openqa.selenium.chromium.ChromiumDriver lambda$new$5
WARNING: Unable to find version of CDP to use for 145.0.7632.160. You may need to include a dependency on a specific version of the CDP using something similar to `org.seleniumhq.selenium:selenium-devtools-v86:4.18.1` where the version ("v86") matches the version of the chromium-based browser you're using and the version number of the artifact is the same as Selenium's.
2026-03-09 15:01:10 [main] INFO  driver.DriverManager - ChromeDriver initialised successfully.
2026-03-09 15:01:12 [main] INFO  base.BaseTest - Navigated to: https://blazedemo.com
2026-03-09 15:01:12 [main] INFO  tests.FlightBookingNegativeTest - === Negative test: same city search Dublin -> Dublin ===
2026-03-09 15:01:12 [main] INFO  pages.HomePage - Selecting departure city: Dublin
2026-03-09 15:01:12 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.name: fromPort
2026-03-09 15:01:12 [main] INFO  listeners.RetryAnalyzer - Test 'testSameCitySearch' exhausted all 2 retry attempts
2026-03-09 15:01:12 [main] INFO  listeners.ScreenshotListener - Screenshot saved: screenshots\testSameCitySearch[Dublin-Dublin]_2026-03-09_15-01-12.png
2026-03-09 15:01:12 [main] INFO  base.BaseTest - === Test teardown started ===
2026-03-09 15:01:12 [main] INFO  driver.DriverManager - Quitting ChromeDriver for thread: main
2026-03-09 15:01:12 [main] INFO  driver.DriverManager - ChromeDriver quit and ThreadLocal cleared.
2026-03-09 15:01:12 [main] INFO  base.BaseTest - === Test teardown complete ===
2026-03-09 15:01:12 [main] INFO  base.BaseTest - === Test setup started ===
2026-03-09 15:01:12 [main] INFO  driver.DriverManager - Setting up ChromeDriver for thread: main
Mar 09, 2026 3:01:13 PM org.openqa.selenium.devtools.CdpVersionFinder findNearestMatch
WARNING: Unable to find CDP implementation matching 145
Mar 09, 2026 3:01:13 PM org.openqa.selenium.chromium.ChromiumDriver lambda$new$5
WARNING: Unable to find version of CDP to use for 145.0.7632.160. You may need to include a dependency on a specific version of the CDP using something similar to `org.seleniumhq.selenium:selenium-devtools-v86:4.18.1` where the version ("v86") matches the version of the chromium-based browser you're using and the version number of the artifact is the same as Selenium's.
2026-03-09 15:01:13 [main] INFO  driver.DriverManager - ChromeDriver initialised successfully.
2026-03-09 15:01:14 [main] INFO  base.BaseTest - Navigated to: https://blazedemo.com
2026-03-09 15:01:14 [main] INFO  tests.FlightBookingNegativeTest - === Negative test: same city search Cairo -> Cairo ===
2026-03-09 15:01:14 [main] INFO  pages.HomePage - Selecting departure city: Cairo
2026-03-09 15:01:14 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.name: fromPort
2026-03-09 15:01:14 [main] WARN  listeners.RetryAnalyzer - Retrying test 'testSameCitySearch' — attempt 1/2
2026-03-09 15:01:14 [main] INFO  base.BaseTest - === Test teardown started ===
2026-03-09 15:01:14 [main] INFO  driver.DriverManager - Quitting ChromeDriver for thread: main
2026-03-09 15:01:15 [main] INFO  driver.DriverManager - ChromeDriver quit and ThreadLocal cleared.
2026-03-09 15:01:15 [main] INFO  base.BaseTest - === Test teardown complete ===
2026-03-09 15:01:15 [main] INFO  base.BaseTest - === Test setup started ===
2026-03-09 15:01:15 [main] INFO  driver.DriverManager - Setting up ChromeDriver for thread: main
Mar 09, 2026 3:01:15 PM org.openqa.selenium.devtools.CdpVersionFinder findNearestMatch
WARNING: Unable to find CDP implementation matching 145
Mar 09, 2026 3:01:15 PM org.openqa.selenium.chromium.ChromiumDriver lambda$new$5
WARNING: Unable to find version of CDP to use for 145.0.7632.160. You may need to include a dependency on a specific version of the CDP using something similar to `org.seleniumhq.selenium:selenium-devtools-v86:4.18.1` where the version ("v86") matches the version of the chromium-based browser you're using and the version number of the artifact is the same as Selenium's.
2026-03-09 15:01:15 [main] INFO  driver.DriverManager - ChromeDriver initialised successfully.
2026-03-09 15:01:16 [main] INFO  base.BaseTest - Navigated to: https://blazedemo.com
2026-03-09 15:01:16 [main] INFO  tests.FlightBookingNegativeTest - === Negative test: same city search Cairo -> Cairo ===
2026-03-09 15:01:16 [main] INFO  pages.HomePage - Selecting departure city: Cairo
2026-03-09 15:01:16 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.name: fromPort
2026-03-09 15:01:17 [main] WARN  listeners.RetryAnalyzer - Retrying test 'testSameCitySearch' — attempt 2/2
2026-03-09 15:01:17 [main] INFO  base.BaseTest - === Test teardown started ===
2026-03-09 15:01:17 [main] INFO  driver.DriverManager - Quitting ChromeDriver for thread: main
2026-03-09 15:01:17 [main] INFO  driver.DriverManager - ChromeDriver quit and ThreadLocal cleared.
2026-03-09 15:01:17 [main] INFO  base.BaseTest - === Test teardown complete ===
2026-03-09 15:01:17 [main] INFO  base.BaseTest - === Test setup started ===
2026-03-09 15:01:17 [main] INFO  driver.DriverManager - Setting up ChromeDriver for thread: main
Mar 09, 2026 3:01:18 PM org.openqa.selenium.devtools.CdpVersionFinder findNearestMatch
WARNING: Unable to find CDP implementation matching 145
Mar 09, 2026 3:01:18 PM org.openqa.selenium.chromium.ChromiumDriver lambda$new$5
WARNING: Unable to find version of CDP to use for 145.0.7632.160. You may need to include a dependency on a specific version of the CDP using something similar to `org.seleniumhq.selenium:selenium-devtools-v86:4.18.1` where the version ("v86") matches the version of the chromium-based browser you're using and the version number of the artifact is the same as Selenium's.
2026-03-09 15:01:18 [main] INFO  driver.DriverManager - ChromeDriver initialised successfully.
2026-03-09 15:01:19 [main] INFO  base.BaseTest - Navigated to: https://blazedemo.com
2026-03-09 15:01:19 [main] INFO  tests.FlightBookingNegativeTest - === Negative test: same city search Cairo -> Cairo ===
2026-03-09 15:01:19 [main] INFO  pages.HomePage - Selecting departure city: Cairo
2026-03-09 15:01:19 [main] DEBUG utils.WaitUtils - Waiting for visibility of element: By.name: fromPort
2026-03-09 15:01:19 [main] INFO  listeners.RetryAnalyzer - Test 'testSameCitySearch' exhausted all 2 retry attempts
2026-03-09 15:01:19 [main] INFO  listeners.ScreenshotListener - Screenshot saved: screenshots\testSameCitySearch[Cairo-Cairo]_2026-03-09_15-01-19.png
2026-03-09 15:01:19 [main] INFO  base.BaseTest - === Test teardown started ===
2026-03-09 15:01:19 [main] INFO  driver.DriverManager - Quitting ChromeDriver for thread: main
2026-03-09 15:01:20 [main] INFO  driver.DriverManager - ChromeDriver quit and ThreadLocal cleared.
2026-03-09 15:01:20 [main] INFO  base.BaseTest - === Test teardown complete ===

===============================================
BlazeDemo E2E Suite
Total tests run: 10, Passes: 4, Failures: 2, Skips: 0, Retries: 4
===============================================

