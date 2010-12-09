package com.spl.math;

import java.util.StringTokenizer;

import com.spl.model.IModel;
import com.spl.model.Model;
import com.spl.properties.ConfigReader;

import bsh.EvalError;
import bsh.Interpreter;

public class LogicalManager implements ILogicalManager{
	IModel model;
	
	public void performOperation() {
		this.model=Model.getInstance();
		Interpreter i = new Interpreter();  // Construct an interpreter
		
		//Parser parser=new Parser(model.getStatement());
		Parser parser=new Parser();
		parser.load_valid_operators();
		String converted = parser.converter(model.getStatement());
		
		// Eval a statement and get the result
		try {
			i.eval("result = " + converted);
			String final_result = i.get("result").toString();
			model.setResult(final_result);
			System.out.println(final_result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.setResult(e.getMessage());
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}             

//System.out.println(converted);
	}
	
}
