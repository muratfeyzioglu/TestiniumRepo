package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    public static String readCell(int rowNumber, int cellNumber) {
        String cellData = null;
        try {
            String projectPath = System.getProperty("user.dir");
            FileInputStream file = new FileInputStream(new File(projectPath + "/PropertiesForProduct.xlsx"));

            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            Row row = sheet.getRow(rowNumber);
            Cell cell = row.getCell(cellNumber);

            if (cell != null) {
                cellData = cell.getStringCellValue();

            } else {
                System.out.println("Cell is nan");
            }

            workbook.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cellData;
    }
}