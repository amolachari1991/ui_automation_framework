package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

public class CSVReaderUtility {
	@Test
	public static Iterator<User> csvReader(String fileName) {
//		fileName = "loginData";
		File csvFile = new File(System.getProperty("user.dir") + "//testData//" + fileName + ".csv");
		FileReader fileReader = null;
		CSVReader csvReader;
		String[] line;
		User userData;
		List<User> userList = null;

		try {
			fileReader = new FileReader(csvFile);
			csvReader = new CSVReader(fileReader);
			csvReader.readNext();// to escape column name line
			userList = new ArrayList<User>();
			while ((line = csvReader.readNext()) != null) {
				userData = new User(line[0], line[1]);
				userList.add(userData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userList.iterator();

	}

}
