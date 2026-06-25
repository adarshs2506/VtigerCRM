package generic_utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * FileUtility — Reusable utility for reading test data from:
 *   1. JSON file  (commondata.json)
 *   2. Excel file (testScriptData.xlsx)
 * @author Adarsh Singh
 */
public class FileUtility {

    private static final String JSON_PATH  = "./src/test/resources/commondata.json";
    private static final String EXCEL_PATH = "./src/test/resources/testScriptData.xlsx";

    /**
     * Reads a value from commondata.json by key.
     *
     * @param key JSON key (e.g. "bro", "url", "un", "pwd")
     * @return String value for the key; empty string if key not found
     */
    public String getDataFromJsonFile(String key)
            throws FileNotFoundException, IOException, ParseException {

        // FIX: try-with-resources auto-closes FileReader
        try (FileReader fr = new FileReader(JSON_PATH)) {
            JSONParser parser = new JSONParser();
            JSONObject jObj   = (JSONObject) parser.parse(fr);

            Object val = jObj.get(key);
            if (val == null) {
                System.out.println("[WARN] Key '" + key + "' not found in JSON. Returning empty string.");
                return "";
            }
            return val.toString();
        }
    }

    /**
     * Reads a cell value from testScriptData.xlsx.
     *
     * @param sheetName  Name of the sheet (e.g. "contacts", "org", "lead")
     * @param rowIndex   Row number (0-based)
     * @param cellIndex  Column number (0-based)
     * @return Cell value as String
     */
    public String getDataFromExcelFile(String sheetName, int rowIndex, int cellIndex)
            throws EncryptedDocumentException, FileNotFoundException, IOException {

        FileInputStream fis = new FileInputStream(EXCEL_PATH);

        // FIX: Workbook closed in finally block to release file handle
        Workbook wb = null;
        try {
            wb   = WorkbookFactory.create(fis);
            Sheet sh   = wb.getSheet(sheetName);

            if (sh == null) {
                throw new IllegalArgumentException("[ERROR] Sheet '" + sheetName + "' not found in Excel.");
            }

            Row  row  = sh.getRow(rowIndex);
            Cell cell = row.getCell(cellIndex);
            return cell.getStringCellValue();

        } finally {
            if (wb != null) wb.close();
            fis.close();
        }
    }
}
