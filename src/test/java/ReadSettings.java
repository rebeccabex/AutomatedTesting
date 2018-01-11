import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ReadSettings {

    public static void main(String[] args) {
        ReadSettings readSettings = new ReadSettings();

        ArrayList<String> stringArray = readSettings.readExcelDoc("C:\\Users\\Admin\\Documents\\5AutomatedTesting\\settings.xlsx");
        for (String s: stringArray) {
            System.out.println(s);
        }
    }


    public ReadSettings() {

    }

    public ArrayList<String> readExcelDoc(String filename) {

        ArrayList<String> stringArray = new ArrayList<String>();

        try {
            FileInputStream excelFile = new FileInputStream(new File(filename));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                String newString = "";
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();

                    if (!newString.equalsIgnoreCase("")) {
                        newString += ": ";
                    }

                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        newString += currentCell.getStringCellValue();
                   }
                }
                stringArray.add(newString);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringArray;
    }
}
