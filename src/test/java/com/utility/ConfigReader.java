package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	
	Properties prop;
	
	public ConfigReader() {
		try {

			File src = new File("./src/test/java/com/utility/resources/Config.property");
			FileInputStream fs = new FileInputStream(src);

			prop = new Properties();
			prop.load(fs);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getlogin() {
		return prop.getProperty("username");
	}

	public String getpassword() {
		return prop.getProperty("password");
	}
	
	public String getlogin_wrong() {
		return prop.getProperty("wrong_username");
	}

	public String getpassword_wrong() {
		return prop.getProperty("wrong_password");
	}
}

