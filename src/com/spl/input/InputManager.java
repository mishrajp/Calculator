package com.spl.input;

import com.spl.math.LogicalManager;
import com.spl.model.Model;
import com.spl.properties.ConfigReader;

public class InputManager {
	
	public char symbols[];
	
	//read the config file and load the parameters into an array
	public void load_valid_operators() {
		//read the operators from the file
		ConfigReader confReader = new ConfigReader("symbols");
		char [] numbers = confReader.getProperty("numbers").toCharArray();
		char [] operators = confReader.getProperty("operators").toCharArray();
		//delete ',' from the operators
		int without_commas = ((numbers.length + operators.length)/ 2) + 1;
		symbols = new char [without_commas];
		
		int i;
		int j = 0;
		for(i = 0; i < numbers.length; i++) {
			if(numbers[i] != ',')
				symbols[j++] = numbers[i];
		}
		
		for(i = 0; i < operators.length; i++) {
			if(operators[i] != ',')
				symbols[j++] = operators[i];
		}
		//for(i = 0; i < symbols.length; i++)
		//	System.out.print(symbols[i] + " ");
		//System.arraycopy(numbers, 0, symbols, 0, numbers.length);
		//System.arraycopy(operators, 0, symbols, numbers.length, operators.length);
	}
	
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
	
	public void addSymbol(char in_chars) {
		Model.getInstance().addSymbolToStatement(Character.toString(in_chars));
	}
	
	public void clearall() {
		Model.getInstance().deleteStatement();
	}
	
	public void clear() {
		Model.getInstance().deleteLastSymbol();
	}
	
	
	public boolean search_symbols(char symbol) {
		boolean exists = false;
		//System.out.println(symbols.length);
		for(int i = 0; i < symbols.length; i++){
			if(symbol == symbols[i]){
				exists = true;
				break;
			}
		}
		return exists;
	}
}
