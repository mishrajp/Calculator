package com.spl.test;

import junit.framework.TestCase;

import com.spl.properties.ConfigReader;


public class ConfigReaderTest extends TestCase{
	ConfigReader configReader;
	
	public void testOperations(){
		configReader=new ConfigReader("operations");
		assertEquals("+", configReader.getProperty("+"));
		assertEquals("-", configReader.getProperty("-"));
		assertEquals("Math.sqrt(x)", configReader.getProperty("~"));
	}
	
	public void testSymbols(){
		configReader=new ConfigReader("symbols");
		assertEquals("1,2,3,4", configReader.getProperty("numbers"));
		assertEquals("+,-", configReader.getProperty("operators"));
	}
	
	public void testTheme(){
		configReader=new ConfigReader("theme");
		assertEquals("#445564", configReader.getProperty("background"));
	}
}
