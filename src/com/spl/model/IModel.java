package com.spl.model;

public interface IModel{
	
	
	public void addSymbolToStatement(String Symbol);

	
	public void deleteLastSymbol();

	
	public void deleteStatement();

	
	public String getStatement();

	
	public void setResult(String result);

	
	public void subscribe(ModelObserver modelObserver);
}
