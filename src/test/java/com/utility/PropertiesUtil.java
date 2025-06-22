package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.constants.Env;

public class PropertiesUtil {

	public static String readProperty(Env env , String propertyName ) {
		
		File propFile = new File(System.getProperty("user.dir")+"/config/"+env+".properties");
		FileInputStream fis;
		Properties property = new Properties();

		try {
			fis = new FileInputStream(propFile);
			property.load(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return property.getProperty(propertyName.toUpperCase());
	}
}
