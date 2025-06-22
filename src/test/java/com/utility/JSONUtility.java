package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.constants.Env;
import com.google.gson.Gson;
import com.ui.pojo.Config;
import com.ui.pojo.Environments;

public class JSONUtility {

	public static Environments readJSON(Env env) {
		
		Gson gson = new Gson();
		File jsonFile = new File(System.getProperty("user.dir")+"//config//config.json");
		FileReader reader = null;
		try {
			reader = new FileReader(jsonFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Config config = gson.fromJson(reader, Config.class);
		Environments environment = config.getEnvironments().get(env.toString());
		return environment;
	}
}
