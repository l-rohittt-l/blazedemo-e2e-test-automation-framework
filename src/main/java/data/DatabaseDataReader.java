package data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDataReader {

    private static final Logger log = LoggerFactory.getLogger(DatabaseDataReader.class);

    /**
     * Column names in the flight_bookings table, in schema order (DESIGN.md Section
     * 8).
     * The SELECT query uses explicit column ordering to guarantee Object[][]
     * alignment
     * regardless of the physical column order in the database.
     */
    private static final String[] COLUMNS = {
            "departure_city",
            "destination_city",
            "name",
            "address",
            "city",
            "state",
            "zip",
            "card_type",
            "card_number",
            "card_month",
            "card_year",
            "name_on_card"
    };

    private DatabaseDataReader() {
        // Utility class - no instantiation
    }

    /**
     * Reads all rows from the configured MySQL table and returns them as
     * Object[][].
     *
     * Connection details are read exclusively from ConfigReader
     * (config.properties):
     * db.url, db.username, db.password, db.table
     *
     * NOTE: Update config.properties with real credentials before using this
     * reader.
     * The database and table must exist and be populated (see create_table.sql).
     *
     * @return Object[][] where each row is a String[12] matching the 12-column
     *         booking schema
     */
    public static Object[][] getData() {
        String dbUrl = ConfigReader.getDbUrl();
        String dbUsername = ConfigReader.getDbUsername();
        String dbPassword = ConfigReader.getDbPassword();
        String dbTable = ConfigReader.getDbTable();

        log.info("Connecting to database: {}", dbUrl);

        String query = buildSelectQuery(dbTable);

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            log.info("Database connection established. Executing query: {}", query);

            List<Object[]> dataRows = new ArrayList<>();

            while (resultSet.next()) {
                String[] rowData = new String[12];
                for (int i = 0; i < COLUMNS.length; i++) {
                    String value = resultSet.getString(COLUMNS[i]);
                    rowData[i] = (value != null) ? value.trim() : "";
                }
                dataRows.add(rowData);
            }

            log.info("Database read complete - {} data row(s) loaded", dataRows.size());
            return dataRows.toArray(new Object[0][]);

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to read test data from database. " +
                            "Ensure db.* values in config.properties are correct and the database is running. " +
                            "Error: " + e.getMessage(),
                    e);
        }
    }

    /**
     * Builds a SELECT query with explicit column ordering matching the schema.
     */
    private static String buildSelectQuery(String tableName) {
        return "SELECT " + String.join(", ", COLUMNS) + " FROM " + tableName;
    }
}
