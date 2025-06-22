package com.ui.dataProviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojo.LoginTestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtility;
import com.utility.ExcelReaderUtility;

public class LoginDataProvider {
	
	@DataProvider(name = "loginJSON_DataProvider")
	public Iterator<Object[]> loginDataProvider(){		
		File loginJSON = new File(System.getProperty("user.dir")+"/testData/logindata.json");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(loginJSON);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		LoginTestData loginTestData = gson.fromJson(fileReader, LoginTestData.class);
		
		List<Object[]> userData = new ArrayList<Object[]>();
		for(User user : loginTestData.getData()) {
			userData.add(new Object[] {user});
		}
		return userData.iterator();

	}
	
	@DataProvider(name = "loginCSV_DataProvider")
	public Iterator<User> loginCSVDataProvider() {
		return CSVReaderUtility.csvReader("loginData");
	}
	
	@DataProvider(name = "loginExelDataProvider")
	public Iterator<User> loginExelDataProvider() {
		return ExcelReaderUtility.excelReaderUtility("loginData");
	}
	

}
