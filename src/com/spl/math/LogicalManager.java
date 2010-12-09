package com.spl.math;

import java.util.StringTokenizer;

import com.spl.model.IModel;
import com.spl.model.Model;
import com.spl.properties.ConfigReader;

public class LogicalManager implements ILogicalManager{
	IModel model;
	
	public void performOperation() {
		this.model=Model.getInstance();
		//Parser parser=new Parser(model.getStatement());
		Parser parser=new Parser();
		parser.load_valid_operators();
		String converted = parser.converter(model.getStatement());
System.out.println(converted);
	}
	
}
