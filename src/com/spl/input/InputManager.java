package com.spl.input;

import java.util.StringTokenizer;

import com.spl.math.LogicalManager;
import com.spl.model.Model;
import com.spl.properties.ConfigReader;

public class InputManager {
	
	public char symbols[];
	public char nums[];
	public String ops;
	
	//read the config file and load the parameters into an array
	public void load_valid_operators() {
		//read the operators from the file
		ConfigReader confReader = new ConfigReader("symbols");
		char [] numbers = confReader.getProperty("numbers").toCharArray();
		char [] operators = confReader.getProperty("operators").toCharArray();
		//delete ',' from the operators
		int without_commas = ((numbers.length + operators.length)/ 2) + 1;
		symbols = new char [without_commas];
		nums = new char[(numbers.length/2)+1];
		
		int i;
		int j = 0;
		int n = 0;
		int o = 0;
		for(i = 0; i < numbers.length; i++) {
			if(numbers[i] != ','){
				symbols[j++] = numbers[i];
				nums[n++] = numbers[i];
			}
		}
		
		for(i = 0; i < operators.length; i++) {
			if(operators[i] != ',') {
				symbols[j++] = operators[i];
				ops += operators[i];
			}
		}
	}
/*	
	public String filter(String input) {
		char [] in_chars = input.toCharArray();
		String statement = "";
		for (int i = 0; i < in_chars.length && in_chars[i] != '='; i++) {
			//always skip the space
			if(in_chars[i] != ' ' && search_symbols(in_chars[i])){
				addSymbol(in_chars[i]);
				statement += in_chars[i];
			}
		}
		
		//call performOperations
		LogicalManager logicalManager = new LogicalManager();
		logicalManager.performOperation();
		
		return statement; 
	}
*/
	public void addSymbol(char in_char) {
//System.out.print(in_char);
		if(in_char == '=') {
			//call performOperations
			LogicalManager logicalManager = new LogicalManager();
			logicalManager.performOperation();
		} else if(search_symbols(in_char)) {
			Model.getInstance().addSymbolToStatement(Character.toString(in_char));			
		}else {}
	}
	
	public void clearall() {
		Model.getInstance().deleteStatement();
		Model.getInstance().deleteResult();
	}
	
	public void clear() {
		Model.getInstance().deleteLastSymbol();
	}
	
	public boolean search_symbols(char symbol) {
		boolean exists = false;
		for(int i = 0; i < symbols.length; i++){
			if(symbol == symbols[i]){
				exists = true;
				break;
			}
		}
		return exists;
	}
}
