package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * This class is created for Excel Data provider. It will read the data from give excel sheet returns the content in two dimentional string array
 * @author Sai Kotha
 *
 */
public class ExcelReader implements Reader{

	String fileName, sheetName;
	
	public ExcelReader(String fileName,String sheetName){
		this.fileName = fileName;
		this.sheetName= sheetName;
	}
	
	/**
	 * This method read the entire data from Excel and returns in two dimentional string array
	 */
	public String[][] getData() {		
		
		String[][] arrayExcelData = null;
		try {
			FileInputStream fs = new FileInputStream(fileName);
			Workbook wb = Workbook.getWorkbook(fs);			
			Sheet sh = wb.getSheet(sheetName);

			int totalNoOfCols = sh.getColumns();
			int totalNoOfRows = sh.getRows();
			
			arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
			
			for (int i= 1 ; i < totalNoOfRows; i++) {

				for (int j=0; j < totalNoOfCols; j++) {
					arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();			
		} catch (BiffException e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}
}

