package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

public class ConfirmationPage {

    private static final Logger log = LoggerFactory.getLogger(ConfirmationPage.class);

    /**
     * Expected confirmation heading - used by the test assertion.
     * Defined here so the test never hardcodes this string independently.
     */
    public static final String EXPECTED_HEADING = "Thank you for your purchase today!";

    private final WebDriver driver;

    // Locator - XPath used to match the specific heading text, avoiding ambiguity
    // with any other h1
    private static final By CONFIRMATION_HEADING = By.xpath("//h1[contains(text(),'Thank you')]");

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Waits for the confirmation heading to appear and returns its text.
     * The test must assert this equals {@link #EXPECTED_HEADING}.
     *
     * @return the confirmation page h1 heading text
     */
    public String getConfirmationHeading() {
        WaitUtils.waitForVisibility(CONFIRMATION_HEADING);
        String heading = driver.findElement(CONFIRMATION_HEADING).getText();
        log.info("Confirmation page heading: {}", heading);
        return heading;
    }
}
