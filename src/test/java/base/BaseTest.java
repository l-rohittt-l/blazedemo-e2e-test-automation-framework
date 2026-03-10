package base;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    /**
     * Runs before every test method.
     * Initialises ChromeDriver via DriverManager, maximises the window,
     * and navigates to the base URL defined in config.properties.
     */
    @BeforeMethod
    public void setUp() {
        log.info("=== Test setup started ===");
        DriverManager.setDriver();

        WebDriver driver = DriverManager.getDriver();
        driver.manage().window().maximize();

        String baseUrl = ConfigReader.getBaseUrl();
        driver.get(baseUrl);
        log.info("Navigated to: {}", baseUrl);
    }

    /**
     * Runs after every test method, including when @BeforeMethod throws.
     * alwaysRun = true ensures the browser is never left open after a setup failure.
     * Quits the browser and cleans up the ThreadLocal driver reference.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("=== Test teardown started ===");
        DriverManager.quitDriver();
        log.info("=== Test teardown complete ===");
    }
}
