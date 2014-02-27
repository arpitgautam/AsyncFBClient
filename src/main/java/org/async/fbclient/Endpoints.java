package org.async.fbclient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Endpoints {

	
	static Properties properties;
	static  boolean fileLoaded;
	static{
		fileLoaded = false;
		properties = new Properties();
		FileInputStream file;
		try {
			file = new FileInputStream("properties/endpoints.properties");
			properties.load(file);
			file.close();
			fileLoaded = true;
		} catch (FileNotFoundException e) {
			//log here
		}catch(IOException  e){
			//log here
		}
	}
	
	public static String get(String key){
		return properties.getProperty(key);
	}
}
