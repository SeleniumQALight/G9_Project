package libs;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/*
 *  Class that provides methods for extracting data from Excel tables.
 */
public class ExcelDriver {

	/*
	 * Static method that retrieves testing data from multiple data Excel table and returns it
	 * as Map collection of key-value pairs. Column Number indicates number of data set. Note, please, that returned values
	 * are String. We should take care of value's type by himself when will use
	 * data values in the test.
	 */

	public static Map getMultipleData(String dataFileName, String sheetName, int columnNumber) throws IOException {
		System.out.println("dataFile = " + dataFileName);
		System.out.println("sheetName = " + sheetName);
		System.out.println("columnNumber = " + columnNumber);
		Map<String, String> testData = new HashMap<>();
		// Create stream for reading from file. встановляюємо коннекшн до файлу
		InputStream input = new FileInputStream(dataFileName);
		// Get Excel WorkBook from input stream. встановлюємо коннекшн до внутрішньої структури ексель
		HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(input));//розібрати як ми бачимо в ексель
		// Get Excel sheet from WorkBook. Тільки той аркуш, що нас цікавить
		HSSFSheet sheet = wb.getSheet(sheetName);

		// Get number of data values
        int dataSize = sheet.getPhysicalNumberOfRows() - 1;//метод з бібліотеки пои, який повертає кількість рядків в таблиці
		// Look over the table and put key-value pairs into the Map collection
		for (int k = 1; k < (dataSize + 1); k++) {
			HSSFCell keyCell = sheet.getRow(k).getCell(0);
			HSSFCell valueCell = sheet.getRow(k).getCell(columnNumber);
			valueCell.setCellType(HSSFCell.CELL_TYPE_STRING);//встановлюємо зчитаний тип даних як стрінг з ексель. не залежно від того, що в ексель вказано
			testData.put(keyCell.getStringCellValue(), valueCell.getStringCellValue());
		}

		input.close();//обовязково треба закрити коннекшен до файлу. бо може бути помилка і ніхто не зможе зайти в файл
		return testData;
	}


    /*
      * Static method that retrieves testing data from Excel table and returns it
      * as Map collection of key-value pairs. Note, please, that returned values
      * are String. We should take care of value's type by himself when will use
      * data values in the test.
      */
    public static Map<String, String> getData(String dataFileName, String sheetName) throws IOException {
        return getMultipleData(dataFileName, sheetName, 1);
    }

	

	/*
	 * Static method that retrieves testing data from Excel table and returns it
	 * as Map collection of key-value pairs. Note, please, that returned values
	 * are String. We should take care of value's type by himself when will use
	 * data values in the test.
	 */
	public static Map getDataRow(String dataFileName, String sheetName) throws IOException {
		Map<String, String> testData = new HashMap<>();
		// Create stream for reading from file
		InputStream input = new FileInputStream(dataFileName);
		// Get Excel WorkBook from input stream
		HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(input));
		// Get Excel sheet from WorkBook
		HSSFSheet sheet = wb.getSheet(sheetName);

		// Get number of data values
		int dataSize = sheet.getRow(2).getPhysicalNumberOfCells();
		// Look over the table and put key-value pairs into the Map collection
		for (int k = 0; k < (dataSize); k++) {
			HSSFCell keyCell = sheet.getRow(2).getCell(k);
			HSSFCell valueCell = sheet.getRow(3).getCell(k);
			valueCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			testData.put(keyCell.getStringCellValue(), valueCell.getStringCellValue());
		}

		input.close();
		return testData;
	}
}
