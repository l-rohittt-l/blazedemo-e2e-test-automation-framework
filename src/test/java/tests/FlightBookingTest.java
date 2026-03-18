package tests;

import base.BaseTest;
import data.CsvDataReader;
import data.DatabaseDataReader;
import data.ExcelDataReader;
import driver.DriverManager;
import listeners.RetryAnalyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ConfirmationPage;
import pages.FlightsPage;
import pages.HomePage;
import pages.PurchasePage;
import utils.ConfigReader;

public class FlightBookingTest extends BaseTest {

        private static final Logger log = LoggerFactory.getLogger(FlightBookingTest.class);

        /**
         * Supplies test data from the source configured by the data.source key in
         * config.properties.
         * Valid values: excel (default), csv, database.
         *
         * Each row is a String[12] matching the booking data schema:
         * [0] departure_city
         * [1] destination_city
         * [2] name
         * [3] address
         * [4] city
         * [5] state
         * [6] zip
         * [7] card_type
         * [8] card_number
         * [9] card_month
         * [10] card_year
         * [11] name_on_card
         */
        @DataProvider(name = "bookingData")
        public Object[][] bookingData() {
                String source = ConfigReader.getDataSource();
                log.info("Loading test data from source: {}", source);
                switch (source) {
                        case "csv":
                                return CsvDataReader.getData("bookings.csv");
                        case "database":
                                return DatabaseDataReader.getData();
                        default:
                                return ExcelDataReader.getData("bookings.xlsx");
                }
        }

        /**
         * End-to-end flight booking test.
         *
         * Acceptance criteria:
         * 1. Flights table is visible and has at least one row.
         * 2. Purchase page heading contains "Your flight from".
         * 3. Total cost is displayed on the purchase page (non-empty).
         * 4. Confirmation heading equals "Thank you for your purchase today!".
         */
        @Test(dataProvider = "bookingData", retryAnalyzer = RetryAnalyzer.class)
        public void testFlightBooking(String departureCity, String destinationCity,
                        String name, String address, String city, String state,
                        String zip, String cardType, String cardNumber,
                        String cardMonth, String cardYear, String nameOnCard) {

                log.info("=== Starting flight booking test: {} -> {} for {} ===",
                                departureCity, destinationCity, name);

                // Step 1: Select cities and find flights
                HomePage homePage = new HomePage(DriverManager.getDriver());
                homePage.selectDepartureCity(departureCity);
                homePage.selectDestinationCity(destinationCity);
                homePage.clickFindFlights();

                // Step 2: Verify flights table and select first flight
                FlightsPage flightsPage = new FlightsPage(DriverManager.getDriver());
                Assert.assertTrue(flightsPage.isFlightsTableVisible(),
                                "Flights table should be visible with at least one result");
                log.info("Flights table verified - selecting first available flight");
                flightsPage.selectFirstFlight();

                // Step 3: Verify purchase page heading and total cost are displayed
                PurchasePage purchasePage = new PurchasePage(DriverManager.getDriver());
                String purchaseHeading = purchasePage.getPageHeading();
                Assert.assertTrue(purchaseHeading.contains("Your flight from"),
                                "Purchase page heading should contain 'Your flight from' but was: " + purchaseHeading);
                log.info("Purchase page heading verified: {}", purchaseHeading);

                String totalCost = purchasePage.getTotalCost();
                Assert.assertFalse(totalCost.isEmpty(),
                                "Total cost should be displayed on the purchase page");
                log.info("Total cost verified: {}", totalCost);

                // Step 4: Fill passenger and payment details
                purchasePage.fillPassengerDetails(name, address, city, state, zip,
                                cardType, cardNumber, cardMonth, cardYear, nameOnCard);
                purchasePage.clickPurchase();

                // Step 5: Verify confirmation heading
                ConfirmationPage confirmationPage = new ConfirmationPage(DriverManager.getDriver());
                String confirmationHeading = confirmationPage.getConfirmationHeading();
                Assert.assertEquals(confirmationHeading, ConfirmationPage.EXPECTED_HEADING,
                                "Confirmation heading mismatch");
                log.info("=== Flight booking test passed for {} ===", name);
        }
}
