package com.spl.math;

import com.spl.model.IModel;
import com.spl.model.Model;

public class LogicalManager implements ILogicalManager{
	IModel model;
	
	
	public void performOperation() {
		this.model=Model.getInstance();
		Parser parser=new Parser(model.getStatement());
		
	}
	
	
	

}
