package com.openmrs.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataProvider {
	public static FileInputStream fis;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	
	public static String[][] getData(String sheetName) {
		String resutlData[][] = null;
		
		try {
			fis = new FileInputStream(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\com\\openmrs\\testdata\\TestData.xlsx"));
			wb = new XSSFWorkbook(fis);
			ws = wb.getSheet(sheetName);
			
			int rowCount = ws.getLastRowNum();
			int colCount = ws.getRow(0).getLastCellNum();

			resutlData = new String[rowCount] [colCount];
			
			for (int i=1; i<=rowCount; i++) {
				XSSFRow row = ws.getRow(i);
				for (int j=0; j<colCount; j++) {
					resutlData[i-1][j] = row.getCell(j).getStringCellValue();
				}
			}
			wb.close();
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage().toString());
		} catch (IOException e) {
			System.out.println(e.getMessage().toString());
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
		
		return resutlData;
	}
}
