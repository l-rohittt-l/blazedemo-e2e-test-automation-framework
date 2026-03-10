package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("config.properties not found on the classpath.");
            }
            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage(), e);
        }
    }

    private ConfigReader() {
        // Utility class — no instantiation
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static int getBrowserTimeout() {
        return Integer.parseInt(properties.getProperty("browser.timeout"));
    }

    public static int getRetryCount() {
        return Integer.parseInt(properties.getProperty("retry.count"));
    }

    public static String getDbUrl() {
        return properties.getProperty("db.url");
    }

    public static String getDbUsername() {
        return properties.getProperty("db.username");
    }

    public static String getDbPassword() {
        return properties.getProperty("db.password");
    }

    public static String getDbTable() {
        return properties.getProperty("db.table");
    }

    public static String getDataSource() {
        return properties.getProperty("data.source", "excel").trim().toLowerCase();
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("browser.headless", "false"));
    }
}
