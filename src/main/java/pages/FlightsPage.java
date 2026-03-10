package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

public class FlightsPage {

    private static final Logger log = LoggerFactory.getLogger(FlightsPage.class);

    private final WebDriver driver;

    // Locators
    private static final By FLIGHTS_TABLE      = By.cssSelector("table.table");
    private static final By FLIGHT_TABLE_ROWS  = By.cssSelector("table.table tbody tr");
    private static final By FIRST_CHOOSE_BUTTON = By.cssSelector("input[value='Choose This Flight']");

    public FlightsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Verifies that the flights results table is visible on the page.
     * Per acceptance criteria: table must be visible and contain at least one row.
     *
     * @return true if the table is visible and has at least one result row
     */
    public boolean isFlightsTableVisible() {
        log.info("Verifying flights results table is visible");
        WaitUtils.waitForVisibility(FLIGHTS_TABLE);
        int rowCount = driver.findElements(FLIGHT_TABLE_ROWS).size();
        log.info("Flights table visible with {} result row(s)", rowCount);
        return rowCount > 0;
    }

    /**
     * Clicks the 'Choose This Flight' button for the first available flight
     * in the search results.
     */
    public void selectFirstFlight() {
        log.info("Selecting first available flight");
        WaitUtils.waitForClickability(FIRST_CHOOSE_BUTTON).click();
        log.info("First flight selected — navigating to purchase page");
    }
}
