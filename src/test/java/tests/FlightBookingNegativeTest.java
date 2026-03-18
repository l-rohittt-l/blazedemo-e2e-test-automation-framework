package tests;

import base.BaseTest;
import driver.DriverManager;
import listeners.RetryAnalyzer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FlightsPage;
import pages.HomePage;
import pages.PurchasePage;

public class FlightBookingNegativeTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(FlightBookingNegativeTest.class);

    /**
     * Negative scenario 1 - clicking "Find Flights" navigates away from the home
     * page.
     *
     * BlazeDemo's fromPort and toPort dropdowns have entirely disjoint city sets,
     * so a same-city search is not possible on this AUT. Instead this test guards
     * against a regression where the Find Flights button stops navigating to the
     * flights results page (reserve.php). Using Philadelphia → Buenos Aires, which
     * are confirmed valid entries in the respective dropdowns.
     */
    @Test(retryAnalyzer = RetryAnalyzer.class, description = "Find Flights navigates to the flights results page (reserve.php)")
    public void testFindFlightsNavigatesToResultsPage() {
        log.info("=== Negative test: Find Flights button navigation ===");

        HomePage homePage = new HomePage(DriverManager.getDriver());
        homePage.selectDepartureCity("Philadelphia");
        homePage.selectDestinationCity("Buenos Aires");
        homePage.clickFindFlights();

        WebDriver driver = DriverManager.getDriver();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("reserve.php"),
                "URL should contain 'reserve.php' after clicking Find Flights, but was: " + currentUrl);
        log.info("=== Find Flights navigation test passed - URL is: {} ===", currentUrl);
    }

    /**
     * Negative scenario 2 - purchase form submitted with all payment fields blank.
     *
     * After selecting a flight, all passenger/payment fields are left empty and the
     * Purchase Flight button is clicked. BlazeDemo does not perform server-side
     * validation and navigates to the confirmation page regardless, so the
     * assertion
     * documents this known gap: the confirmation heading IS shown even with no
     * data.
     * Any future addition of form validation that blocks the submission will
     * surface
     * here.
     */
    @Test(retryAnalyzer = RetryAnalyzer.class, description = "Empty payment form submitted - confirmation page reached (no AUT validation)")
    public void testEmptyFormSubmission() {
        log.info("=== Negative test: empty payment form submission ===");

        HomePage homePage = new HomePage(DriverManager.getDriver());
        homePage.selectDepartureCity("Philadelphia");
        homePage.selectDestinationCity("Buenos Aires");
        homePage.clickFindFlights();

        FlightsPage flightsPage = new FlightsPage(DriverManager.getDriver());
        flightsPage.selectFirstFlight();

        // Submit the purchase form with all fields left at their default (empty) values
        PurchasePage purchasePage = new PurchasePage(DriverManager.getDriver());
        purchasePage.clickPurchase();

        // BlazeDemo does not validate required fields - it proceeds to confirmation.
        // Assert the URL changed away from purchase.php to confirm a page transition
        // occurred.
        WebDriver driver = DriverManager.getDriver();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("purchase.php"),
                "Page should have navigated away from purchase.php after clicking Purchase Flight");
        log.info("=== Empty-form submission test passed - page navigated away from purchase.php ===");
    }

    /**
     * Negative scenario 3 - verify the confirmation heading is NOT present on the
     * flights results page (wrong-page assertion guard).
     *
     * Ensures the confirmation heading element does not appear on the FlightsPage,
     * guarding against a locator that accidentally matches across pages.
     */
    @Test(retryAnalyzer = RetryAnalyzer.class, description = "Confirmation heading must not appear on the flights results page")
    public void testConfirmationHeadingAbsentOnFlightsPage() {
        log.info("=== Negative test: confirmation heading must not appear on flights page ===");

        HomePage homePage = new HomePage(DriverManager.getDriver());
        homePage.selectDepartureCity("Philadelphia");
        homePage.selectDestinationCity("Buenos Aires");
        homePage.clickFindFlights();

        WebDriver driver = DriverManager.getDriver();
        boolean headingPresent = !driver.findElements(By.cssSelector("h1")).isEmpty()
                && driver.findElement(By.cssSelector("h1"))
                        .getText().contains("Thank you");

        Assert.assertFalse(headingPresent,
                "Confirmation heading must not be present on the flights results page");
        log.info("=== Confirmation heading correctly absent on flights results page ===");
    }
}
