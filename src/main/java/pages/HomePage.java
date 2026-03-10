package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

public class HomePage {

    private static final Logger log = LoggerFactory.getLogger(HomePage.class);

    private final WebDriver driver;

    // Locators
    private static final By DEPARTURE_CITY_DROPDOWN  = By.name("fromPort");
    private static final By DESTINATION_CITY_DROPDOWN = By.name("toPort");
    private static final By FIND_FLIGHTS_BUTTON       = By.cssSelector("input[value='Find Flights']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Selects the departure city from the fromPort dropdown.
     *
     * @param city visible text of the city option (e.g. "Paris")
     */
    public void selectDepartureCity(String city) {
        log.info("Selecting departure city: {}", city);
        WaitUtils.waitForVisibility(DEPARTURE_CITY_DROPDOWN);
        Select dropdown = new Select(driver.findElement(DEPARTURE_CITY_DROPDOWN));
        dropdown.selectByVisibleText(city);
        log.info("Departure city selected: {}", city);
    }

    /**
     * Selects the destination city from the toPort dropdown.
     *
     * @param city visible text of the city option (e.g. "Buenos Aires")
     */
    public void selectDestinationCity(String city) {
        log.info("Selecting destination city: {}", city);
        WaitUtils.waitForVisibility(DESTINATION_CITY_DROPDOWN);
        Select dropdown = new Select(driver.findElement(DESTINATION_CITY_DROPDOWN));
        dropdown.selectByVisibleText(city);
        log.info("Destination city selected: {}", city);
    }

    /**
     * Clicks the Find Flights submit button to trigger the flight search.
     */
    public void clickFindFlights() {
        log.info("Clicking Find Flights button");
        WaitUtils.waitForClickability(FIND_FLIGHTS_BUTTON).click();
        log.info("Find Flights button clicked");
    }
}
