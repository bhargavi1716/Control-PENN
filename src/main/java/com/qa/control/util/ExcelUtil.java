package com.qa.control.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.qa.control.commons.Constants;

public class ExcelUtil {

	public static Workbook book;
	public static Sheet sheet;

	public static String TESTDATA_SHEET_PATH =Constants.DATA_SHEET_PATH;  
	
	    
    public static Object[][] getTestData(String sheetName) {
		// fetch the data from excel sheet:

		FileInputStream file = null;
		
//		System.out.println(TESTDATA_SHEET_PATH);
//		System.out.println(sheetName);

		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);

		Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
//		System.out.println(sheet.getLastRowNum());
//		System.out.println(sheet.getRow(0).getLastCellNum());

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				//System.out.println( sheet.getRow(i + 1).getCell(k).toString());
				try {
				   data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				} catch(NullPointerException e) {
					e.printStackTrace();
					e.toString();
				}
			}
		} 
		return data;
	}

}

