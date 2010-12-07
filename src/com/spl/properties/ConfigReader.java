package com.spl.properties;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigReader {
	ResourceBundle properties;
	
	/**
	 * Creates a new config file reader
	 * @param fileName The name of the properties file without the extension. For example "operations"
	 */
	public ConfigReader(String fileName){
		properties = ResourceBundle.getBundle("com.spl.properties."+fileName);
	}
	
	/**
	 * Returns the value of a key
	 * @param key The key for the value you are looking for
	 * @return The value of the given key
	 */
	public String getProperty(String key){
		return properties.getString(key);
	}
	
	
}
