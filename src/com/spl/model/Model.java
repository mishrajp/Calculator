package com.spl.model;

import java.util.ArrayList;

public class Model implements IModel{
	
	private static IModel instance;
	ArrayList<ModelObserver> observers=new ArrayList<ModelObserver>();
	String result=new String("");
	String statement=new String("");
	
	public void addSymbolToStatement(String symbol) {
		statement=statement.concat(symbol);
		notifyAllObservers();
	}

	
	public void deleteLastSymbol() {
		statement=statement.substring(0, statement.length()-1);
		notifyAllObservers();
	}

	
	public void deleteStatement() {
		statement="";
		notifyAllObservers();
	}

	
	public String getStatement() {
		return statement;
	}

	
	public void setResult(String result) {
		this.result=result;
		notifyAllObservers();
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
	
	private void notifyAllObservers(){
		for (ModelObserver modelObserver : observers) {
			modelObserver.notify();
		}
	}

}
