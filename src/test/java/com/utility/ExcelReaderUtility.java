package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;

public class ExcelReaderUtility {

	public static Iterator<User> excelReaderUtility(String SheetName) {
		
		File xlsxFile = new File(System.getProperty("user.dir") + "//testData//"+SheetName+".xlsx");
		XSSFWorkbook workbook = null;
		XSSFSheet sheet;
		Row row;
		Iterator<Row> rowIterator;
		Cell emailAddressCell;
		Cell passwordCell;
		List<User> userDataList = null;
		User user;
		
		try {
			workbook = new XSSFWorkbook(xlsxFile);
			sheet = workbook.getSheet("loginDataExel");
			rowIterator = sheet.iterator();
			rowIterator.next();
			userDataList = new ArrayList<User>();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				emailAddressCell = row.getCell(0);
				passwordCell = row.getCell(1);
				user = new User(emailAddressCell.toString(), passwordCell.toString());
				userDataList.add(user);
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDataList.iterator();
	}
}
