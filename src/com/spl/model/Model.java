package com.spl.model;

import java.util.ArrayList;

public class Model implements IModel, ModelObserver{
	
	private static IModel instance;
	ArrayList<ModelObserver> observers;
	String result=new String("");
	String statement=new String("");
	
	public void addSymbolToStatement(String symbol) {
		statement=statement.concat(symbol);
	}

	
	public void deleteLastSymbol() {
		statement=statement.substring(0, statement.length()-1);
	}

	
	public void deleteStatement() {
		statement="";		
	}

	
	public String getStatement() {
		return statement;
	}

	
	public void setResult(String result) {
		this.result=result;
	}

	
	public void subscribe(ModelObserver modelObserver) {
		observers.add(modelObserver);
	}

	
	public static IModel getInstance() {
		if(instance==null){
			instance = new Model();
		}
		return instance;
	}

}
