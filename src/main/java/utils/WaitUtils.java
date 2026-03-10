package utils;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class WaitUtils {

    private static final Logger log = LoggerFactory.getLogger(WaitUtils.class);

    private WaitUtils() {
        // Utility class — no instantiation
    }

    /**
     * Returns a WebDriverWait instance using the timeout from config.properties.
     * A new instance is created each time to always use the current thread's driver.
     */
    private static WebDriverWait buildWait() {
        return new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(ConfigReader.getBrowserTimeout())
        );
    }

    /**
     * Waits until the element located by the given locator is visible in the DOM
     * and rendered on the page.
     *
     * @param locator By locator for the target element
     * @return the visible WebElement
     */
    public static WebElement waitForVisibility(By locator) {
        log.debug("Waiting for visibility of element: {}", locator);
        return buildWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until the element located by the given locator is visible and enabled
     * so that it can be clicked.
     *
     * @param locator By locator for the target element
     * @return the clickable WebElement
     */
    public static WebElement waitForClickability(By locator) {
        log.debug("Waiting for clickability of element: {}", locator);
        return buildWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits until the element located by the given locator is present in the DOM.
     * The element does not need to be visible.
     *
     * @param locator By locator for the target element
     * @return the WebElement present in the DOM
     */
    public static WebElement waitForPresence(By locator) {
        log.debug("Waiting for DOM presence of element: {}", locator);
        return buildWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
