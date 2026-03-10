package data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelDataReader {

    private static final Logger log = LoggerFactory.getLogger(ExcelDataReader.class);

    private ExcelDataReader() {
        // Utility class — no instantiation
    }

    /**
     * Reads all data rows from the first sheet of an Excel file (.xlsx or .xls)
     * located on the classpath under testdata/.
     *
     * Row 0 (header) is skipped. Each subsequent row is read as 12 String values
     * matching the booking data schema defined in DESIGN.md Section 8.
     *
     * @param fileName file name only (e.g. "bookings.xlsx") — resolved from testdata/
     * @return Object[][] where each row is a String[12] of booking data
     */
    public static Object[][] getData(String fileName) {
        log.info("Reading Excel test data from testdata/{}", fileName);

        String resourcePath = "testdata/" + fileName;

        try (InputStream inputStream = ExcelDataReader.class
                .getClassLoader()
                .getResourceAsStream(resourcePath)) {

            if (inputStream == null) {
                throw new RuntimeException("Excel file not found on classpath: " + resourcePath);
            }

            try (Workbook workbook = WorkbookFactory.create(inputStream)) {
                Sheet sheet = workbook.getSheetAt(0);
                DataFormatter formatter = new DataFormatter();
                List<Object[]> dataRows = new ArrayList<>();

                // Row 0 is the header — start from row index 1
                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    if (row == null) {
                        continue;
                    }
                    String[] rowData = new String[12];
                    for (int colIndex = 0; colIndex < 12; colIndex++) {
                        rowData[colIndex] = getCellValueAsString(row.getCell(colIndex), formatter);
                    }
                    dataRows.add(rowData);
                }

                log.info("Excel data read complete — {} data row(s) loaded", dataRows.size());
                return dataRows.toArray(new Object[0][]);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to read Excel file: " + resourcePath, e);
        }
    }

    /**
     * Returns the cell value as a trimmed String regardless of cell type.
     * DataFormatter renders the value exactly as Excel displays it, preserving
     * leading zeros and avoiding the "12345.0" issue with numeric cells.
     */
    private static String getCellValueAsString(Cell cell, DataFormatter formatter) {
        if (cell == null) {
            return "";
        }
        return formatter.formatCellValue(cell).trim();
    }
}
