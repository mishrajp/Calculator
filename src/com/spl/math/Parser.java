package com.spl.math;

import java.util.*;
import com.spl.properties.ConfigReader;

public class Parser {

	public String ops = "";

	public void load_valid_operators() {
		//read the operators from the file
		ConfigReader confReader = new ConfigReader("symbols");
		char [] numbers = confReader.getProperty("numbers").toCharArray();
		char [] operators = confReader.getProperty("operators").toCharArray();
		//delete ',' from the operators
		
		int i;
		for(i = 0; i < operators.length; i++)
			if(operators[i] != ',') 
				ops += operators[i];
	}
	
	public String converter(String expression){
		StringTokenizer st = new StringTokenizer(expression, ops, true);
		String tokens [] = new String[50];
		boolean marker [] = new boolean[50]; //it will mark the numbers and operators that were moved to the final expression
		boolean parenthesis_left = false;
		boolean parenthesis_right = false;
		int number_tokens = 0;
		String final_expression = "";
				
		int i;
		for(i = 0; i < marker.length; i++)
			marker[i] = false;
		
		//put all tokens in array 
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			tokens[number_tokens++] = token;
		}
		//here we read the array and we check the operators, we can retrieve based on the position
		for(i = 0; i < number_tokens; i++) {
			if(isNumber(tokens[i])) {
			} else {
				//if parentheses ... keep reading until you reach the other parenthesis
				if(tokens[i].equals("(") || tokens[i].equals(")") ) {
					if(tokens[i].equals("("))
						parenthesis_left = true;
					if(tokens[i].equals(")"))
						parenthesis_right = true;
					
					final_expression += tokens[i];
					marker[i] = true;
				} else if(isArithmetic(tokens[i])) {
					//now check if the next operator is from the same category
					if(isArithmetic(tokens[i+2]) && marker[i+2] == false) {
						//in this case read both numbers and operator and skip to the next operator						
						if(i > 0 && marker[i-1] == false) {
							final_expression += tokens[i-1];
							marker[i-1] = true;							
						}
						final_expression += tokens[i] + tokens[i+1];
						marker[i] = true;
						marker[i+1] = true;
						++i;
					} else { //if not - add the number and the first operator only
						if(i > 0 && marker[i-1] == false) {
							final_expression += tokens[i-1];
							marker[i-1] = true;							
						}
						final_expression +=  tokens[i];
						marker[i] = true;
						//check if the parentheses are closed or opened 
						if(parenthesis_left == true && parenthesis_right == false){
							final_expression +=  tokens[i+1];
							marker[i+1] = true;
							parenthesis_left = false; 
						}
					}
				} else {				
					if(false){
						//do nothing
					} else if(tokens[i].equals("^")) {
						final_expression += "Math.pow(" + tokens[i-1] + "," + tokens[i+1] + ")";
						marker[i-1] = true;
						marker[i] = true;
						marker[i+1] = true;
					} else if(tokens[i].equals("e")) {
						final_expression += "Math.exp(" + tokens[i+1] + ")";
						marker[i] = true;
						marker[i+1] = true;						
					} else if(tokens[i].equals("s")) {
						final_expression += "Math.sqrt(" + tokens[i+1] + ")";
						marker[i] = true;
						marker[i+1] = true;						
					} else if(tokens[i].equals("~")) {
						final_expression += "Math.abs(";
						if(tokens[i+1].equals("-") ||tokens[i+1].equals("+")) {
							final_expression += tokens[i+1] + tokens[i + 2] + ")";
							marker[i] = true;
							marker[i+1] = true;						
							marker[i+2] = true;
							i++;
						} else {
							final_expression += tokens[i+1] + ")";
							marker[i+1] = true;	
							marker[i] = true;
						}		
					}
				}
			}
		}
		// if the last element is not added
		if(marker[i - 1] == false)
			final_expression += tokens[i - 1];
//System.out.println("final: " + final_expression);
		return final_expression;
	}
	
	public boolean isArithmetic(String ops) {
		String operators []= {"+", "-", "*", "/"};
		boolean exists = false;
		for(int i = 0; i < operators.length; i++) {
			if(operators[i].equals(ops)) {
				exists = true;
				break;
			}
		}
		return exists;
	}
	
	public boolean isNumber(String exp) {
		 if (exp.matches("([0-9]+(\\.[0-9]+)?)+")) 
             return true;
		 else
			 return false;
	}

}
