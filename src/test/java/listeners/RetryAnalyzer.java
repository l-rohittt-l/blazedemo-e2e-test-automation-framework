package listeners;

import utils.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final Logger log = LoggerFactory.getLogger(RetryAnalyzer.class);
    private static final int MAX_RETRY_COUNT = ConfigReader.getRetryCount();

    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            log.warn("Retrying test '{}' — attempt {}/{}", result.getMethod().getMethodName(),
                    retryCount, MAX_RETRY_COUNT);
            return true;
        }
        log.info("Test '{}' exhausted all {} retry attempts", result.getMethod().getMethodName(),
                MAX_RETRY_COUNT);
        return false;
    }
}
