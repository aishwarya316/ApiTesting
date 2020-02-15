package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class test{
	
	public int RESPONSE_CODE_200=200;
	public int RESPONSE_CODE_201=201;
	
	 public Properties prop;
	
	public test(){
	
	try {
		prop= new Properties();
		FileInputStream ip = new FileInputStream("F:\\API_testing\\restapi\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e1) {
		e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
