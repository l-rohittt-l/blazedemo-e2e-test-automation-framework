package data;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvDataReader {

    private static final Logger log = LoggerFactory.getLogger(CsvDataReader.class);

    private CsvDataReader() {
        // Utility class — no instantiation
    }

    /**
     * Reads all data rows from a CSV file located on the classpath under testdata/.
     *
     * Row 0 (header) is skipped. Each subsequent row is read as 12 String values
     * matching the booking data schema defined in DESIGN.md Section 8.
     *
     * @param fileName file name only (e.g. "bookings.csv") — resolved from testdata/
     * @return Object[][] where each row is a String[12] of booking data
     */
    public static Object[][] getData(String fileName) {
        log.info("Reading CSV test data from testdata/{}", fileName);

        String resourcePath = "testdata/" + fileName;

        try (InputStream inputStream = CsvDataReader.class
                .getClassLoader()
                .getResourceAsStream(resourcePath)) {

            if (inputStream == null) {
                throw new RuntimeException("CSV file not found on classpath: " + resourcePath);
            }

            try (CSVReader csvReader = new CSVReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                List<Object[]> dataRows = new ArrayList<>();
                String[] row;
                boolean headerSkipped = false;

                while ((row = csvReader.readNext()) != null) {
                    if (!headerSkipped) {
                        headerSkipped = true;
                        continue; // skip header row
                    }
                    if (row.length < 12) {
                        log.warn("Skipping CSV row with fewer than 12 columns: {}", (Object) row);
                        continue;
                    }
                    String[] rowData = new String[12];
                    for (int i = 0; i < 12; i++) {
                        rowData[i] = row[i].trim();
                    }
                    dataRows.add(rowData);
                }

                log.info("CSV data read complete — {} data row(s) loaded", dataRows.size());
                return dataRows.toArray(new Object[0][]);
            }

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("Failed to read CSV file: " + resourcePath, e);
        }
    }
}
