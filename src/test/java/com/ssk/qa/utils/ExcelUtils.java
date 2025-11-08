package com.ssk.qa.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    public static Object[][] getExcelData(String path, String sheetName) {
        Object[][] data = null;

        try (FileInputStream file = new FileInputStream(path);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("❌ Sheet '" + sheetName + "' not found in file: " + path);
            }

            int totalRows = sheet.getPhysicalNumberOfRows();
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException("❌ Header row is missing in sheet: " + sheetName);
            }

            int totalCols = headerRow.getLastCellNum();
            data = new Object[totalRows - 1][totalCols];

            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i < totalRows; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                for (int j = 0; j < totalCols; j++) {
                    Cell cell = row.getCell(j);
                    String cellValue = formatter.formatCellValue(cell).trim();
                    cellValue = cellValue.replaceAll("[\\u00A0\\u2007\\u202F\\t\\n\\r]", "").trim();
                    data[i - 1][j] = cellValue;
                }
            }

            // Print loaded data for debugging
            System.out.println("📊 Loaded data from Excel:");
            for (Object[] row : data) {
                System.out.println(" → Username: [" + row[0] + "], Password: [" + row[1] + "]");
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("⚠️ Unable to read Excel file: " + path);
        }

        return data;
    }
}
