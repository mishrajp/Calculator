package com.spl.model;

import java.util.ArrayList;

public class Model implements IModel{
	private static IModel instance;
	ArrayList<ModelObserver> observers=new ArrayList<ModelObserver>();
	String result=new String("");
	String statement=new String("");
	
	
	public void addSymbolToStatement(String symbol) {
		statement=statement.concat(symbol);
		notifyAllObservers(STATEMENT);
	}

	
	public void deleteLastSymbol() {
		statement=statement.substring(0, statement.length()-1);
		notifyAllObservers(STATEMENT);
	}

	
	public void deleteStatement() {
		statement="";
		notifyAllObservers(STATEMENT);
	}

	
	public String getStatement() {
		return statement;
	}
	
	public String getResult() {
		return result;
	}

	
	public void setResult(String result) {
		this.result=result;
		notifyAllObservers(RESULT);
	}
	
	public void deleteResult() {
		result="";
		notifyAllObservers(RESULT);
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
	
	private void notifyAllObservers(String output){
		for (ModelObserver modelObserver : observers) {
			modelObserver.notifyObserver(output);
		}
	}

}
