package com.spl.math;

import java.util.StringTokenizer;

import com.spl.model.IModel;
import com.spl.model.Model;
import com.spl.properties.ConfigReader;

import bsh.EvalError;
import bsh.Interpreter;

public class LogicalManager implements ILogicalManager{
	IModel model;
	String data_type;
	public void performOperation() {
		this.model=Model.getInstance();
		//if the user presses = and there is nothing in the statement - ignore the process
		if(model.getStatement().equals("")) {
			return;
		}
		
		Interpreter i = new Interpreter();  // Construct an interpreter
		load_data_type();
		//Parser parser=new Parser(model.getStatement());
		Parser parser=new Parser();
		parser.load_valid_operators();
		String converted = parser.converter(model.getStatement());
		
		// Eval a statement and get the result
		try {
			i.eval("result = " + converted);
			String final_result = i.get("result").toString();
			//check the data_type
			if(data_type.equals("integer")) {
				model.setResult(truncate_number(final_result));
			} else {
				model.setResult(final_result);
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.setResult(e.getMessage());
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}             
//System.out.println(converted);
	}
	
	public void load_data_type() {
		ConfigReader confReader = new ConfigReader("data_type");
		data_type = confReader.getProperty("data_type");
	}
	
	public String truncate_number(String number) {
		StringTokenizer st = new StringTokenizer(number, ".");
		return st.nextToken();
	}
	
}
