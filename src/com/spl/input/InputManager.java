package com.spl.input;

public class InputManager {
	
	public static char symbols[] = {' ', '1', '2', '3', '+', '-'};
	//public static String statement;
	
	public InputManager() {
	//	statement = "";
	}

	//read the config file and load the parameters into an array
	public void load_valid_operators() {
		
	}
	
	public String filter_input(String input) {
		char [] in_chars = input.toCharArray();
		String statement = "";
		for (int i = 0; i < in_chars.length && in_chars[i] != '='; i++) {
			//always delete the space
			if(in_chars[i] != ' ' && search_symbols(in_chars[i])){
				statement += in_chars[i];
//				System.out.print(in_chars[i]);
			}
		}
		return statement; 
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
