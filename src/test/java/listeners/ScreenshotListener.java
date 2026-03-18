package listeners;

import driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotListener implements ITestListener {

    private static final Logger log = LoggerFactory.getLogger(ScreenshotListener.class);
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    private static final String SCREENSHOTS_DIR = "screenshots";

    @Override
    public void onTestFailure(ITestResult result) {
        // Build a name that includes data provider parameters so rows are
        // distinguishable.
        // e.g. testFlightBooking[Philadelphia-BuenosAires]_2026-03-09_10-00-00.png
        String methodName = result.getMethod().getMethodName();
        Object[] params = result.getParameters();
        if (params != null && params.length > 0) {
            String paramSuffix = (params[0] + "-" + (params.length > 1 ? params[1] : ""))
                    .replaceAll("[^a-zA-Z0-9_-]", "");
            methodName = methodName + "[" + paramSuffix + "]";
        }
        captureScreenshot(methodName);
    }

    private void captureScreenshot(String testMethodName) {
        WebDriver driver = DriverManager.getDriver();
        if (driver == null) {
            log.warn("Cannot capture screenshot - driver is null for test: {}", testMethodName);
            return;
        }

        try {
            String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
            String fileName = testMethodName + "_" + timestamp + ".png";
            Path screenshotsDir = Paths.get(SCREENSHOTS_DIR);
            Files.createDirectories(screenshotsDir);

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path destination = screenshotsDir.resolve(fileName);
            Files.copy(srcFile.toPath(), destination);

            log.info("Screenshot saved: {}", destination);
        } catch (IOException e) {
            log.error("Failed to save screenshot for test: {}", testMethodName, e);
        }
    }
}
