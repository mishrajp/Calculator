package com.spl.math;

import java.util.Arrays;
import java.util.HashSet;
import com.spl.properties.ConfigReader;

public class Parser {
	HashSet<String> numbers=new HashSet<String>();
	HashSet<String> operators=new HashSet<String>();
	String convertedStatement="";
	String statement;
	
	public Parser(String statement){
		this.statement=statement;
		
		//numbers.addAll(Arrays.asList(new ConfigReader("symbols").getProperty("numbers").split(",")));
		operators.addAll(Arrays.asList(new ConfigReader("symbols").getProperty("operators").split(",")));
		
		for (int i = 0; i < statement.length(); i++) {
			if(operators.contains(statement.charAt(i))){
				new ConfigReader("operations").getProperty(String.valueOf(statement.charAt(i)));
			}
			
			
			
			
			else{
				convertedStatement=convertedStatement.concat(String.valueOf(statement.charAt(i)));
			}
		}
	}
	

}
