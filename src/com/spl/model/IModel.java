package com.spl.model;

public interface IModel{
	public static String RESULT="result";
	public static String STATEMENT="statment";
	
	public void addSymbolToStatement(String Symbol);
	
	public void deleteLastSymbol();
	
	public void deleteStatement();
	
	public String getStatement();

	public void setResult(String result);
	
	public void deleteResult();
	
	public String getResult();
	
	public void subscribe(ModelObserver modelObserver);
	
}
