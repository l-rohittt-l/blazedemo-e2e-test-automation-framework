package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

public class PurchasePage {

    private static final Logger log = LoggerFactory.getLogger(PurchasePage.class);

    private final WebDriver driver;

    // Locators — mapped to BlazeDemo purchase.php form element IDs
    private static final By PAGE_HEADING        = By.cssSelector("h2");
    private static final By INPUT_NAME          = By.id("inputName");
    private static final By ADDRESS             = By.id("address");
    private static final By CITY                = By.id("city");
    private static final By STATE               = By.id("state");
    private static final By ZIP_CODE            = By.id("zipCode");
    private static final By CARD_TYPE           = By.id("cardType");
    private static final By CREDIT_CARD_NUMBER  = By.id("creditCardNumber");
    private static final By CREDIT_CARD_MONTH   = By.id("creditCardMonth");
    private static final By CREDIT_CARD_YEAR    = By.id("creditCardYear");
    // BlazeDemo uses "nameOnCard" — maps to data schema column 11 (card_cvv)
    private static final By NAME_ON_CARD        = By.id("nameOnCard");
    private static final By PURCHASE_BUTTON     = By.cssSelector("input[value='Purchase Flight']");
    // Total cost on purchase page — inside <em> within the <p> that contains "Total Cost:"
    private static final By TOTAL_PRICE         = By.xpath("//p[contains(text(),'Total Cost:')]/em");

    public PurchasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Returns the text of the page heading (h2).
     * Used to verify the acceptance criteria: heading must contain "Your flight from".
     *
     * @return the heading text
     */
    public String getPageHeading() {
        String heading = WaitUtils.waitForVisibility(PAGE_HEADING).getText();
        log.info("Purchase page heading: {}", heading);
        return heading;
    }

    /**
     * Returns the total cost value displayed in the flight details table.
     * Used to verify that flight details including total cost are shown on the purchase page.
     *
     * @return total cost text (e.g. "$300.00")
     */
    public String getTotalCost() {
        String totalCost = WaitUtils.waitForVisibility(TOTAL_PRICE).getText();
        log.info("Total cost displayed: {}", totalCost);
        return totalCost;
    }

    /**
     * Fills all passenger and payment fields on the purchase form.
     *
     * Data schema column mapping:
     *   [2]  name            → inputName
     *   [3]  address         → address
     *   [4]  city            → city
     *   [5]  state           → state
     *   [6]  zip             → zipCode
     *   [7]  card_type       → cardType (dropdown)
     *   [8]  card_number     → creditCardNumber
     *   [9]  card_month      → creditCardMonth
     *   [10] card_year       → creditCardYear
     *   [11] card_cvv        → nameOnCard (BlazeDemo has no CVV field)
     *
     * @param name           passenger full name
     * @param address        street address
     * @param city           city
     * @param state          state
     * @param zip            ZIP code
     * @param cardType       credit card type visible text (e.g. "Visa")
     * @param cardNumber     credit card number
     * @param cardMonth      card expiry month
     * @param cardYear       card expiry year
     * @param nameOnCard     name on card (schema column card_cvv — BlazeDemo uses nameOnCard)
     */
    public void fillPassengerDetails(String name, String address, String city, String state,
                                     String zip, String cardType, String cardNumber,
                                     String cardMonth, String cardYear, String nameOnCard) {

        log.info("Filling passenger details for: {}", name);

        enterText(INPUT_NAME, name);
        enterText(ADDRESS, address);
        enterText(CITY, city);
        enterText(STATE, state);
        enterText(ZIP_CODE, zip);
        selectCardType(cardType);
        enterText(CREDIT_CARD_NUMBER, cardNumber);
        enterText(CREDIT_CARD_MONTH, cardMonth);
        enterText(CREDIT_CARD_YEAR, cardYear);
        enterText(NAME_ON_CARD, nameOnCard);

        log.info("Passenger details filled successfully");
    }

    /**
     * Clicks the Purchase Flight button to submit the booking.
     */
    public void clickPurchase() {
        log.info("Clicking Purchase Flight button");
        WaitUtils.waitForClickability(PURCHASE_BUTTON).click();
        log.info("Purchase Flight button clicked");
    }

    // -------------------------------------------------------------------------
    // Private helpers
    // -------------------------------------------------------------------------

    private void enterText(By locator, String text) {
        WaitUtils.waitForVisibility(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    private void selectCardType(String cardType) {
        log.info("Selecting card type: {}", cardType);
        WaitUtils.waitForVisibility(CARD_TYPE);
        Select dropdown = new Select(driver.findElement(CARD_TYPE));
        dropdown.selectByVisibleText(cardType);
    }
}
