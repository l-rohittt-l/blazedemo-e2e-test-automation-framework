package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;

import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    private static final Logger log = LoggerFactory.getLogger(DriverManager.class);

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    private DriverManager() {
        // Utility class - no instantiation
    }

    /**
     * Creates a new ChromeDriver instance for the current thread and stores it
     * in ThreadLocal. WebDriverManager handles the ChromeDriver binary
     * automatically.
     */
    public static void setDriver() {
        log.info("Setting up ChromeDriver for thread: {}", Thread.currentThread().getName());
        WebDriverManager.chromedriver().setup();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.credit_card_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-notifications");

        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            log.info("Headless mode enabled.");
        }

        driverThread.set(new ChromeDriver(options));
        log.info("ChromeDriver initialised successfully.");
    }

    /**
     * Returns the WebDriver instance for the current thread.
     *
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return driverThread.get();
    }

    /**
     * Quits the WebDriver session for the current thread and removes the
     * ThreadLocal entry to prevent memory leaks.
     */
    public static void quitDriver() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            log.info("Quitting ChromeDriver for thread: {}", Thread.currentThread().getName());
            driver.quit();
            driverThread.remove();
            log.info("ChromeDriver quit and ThreadLocal cleared.");
        }
    }
}
