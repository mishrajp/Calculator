package com.spl.test;

import com.spl.input.*;
import com.spl.model.Model;

import junit.framework.*;

public class Filter extends TestCase {

	InputManager inputManager = new InputManager();

	public void setUp() {
		inputManager.load_valid_operators();
     }

	public void testOperatorsLoading() {
		//inputManger.load_valid_operators();
		assertTrue(inputManager.search_symbols('1'));
		assertTrue(inputManager.search_symbols('2'));	
	}
	public void testAddSymbol(){
		//good example
		inputManager.addSymbol('1');
		inputManager.addSymbol('6');
		inputManager.addSymbol('+');
		inputManager.addSymbol('2');
		assertEquals(Model.getInstance().getStatement(), "16+2");
		inputManager.clear();
		assertEquals(Model.getInstance().getStatement(), "16+");
		inputManager.addSymbol('4');
		inputManager.addSymbol('=');
		assertEquals(Model.getInstance().getStatement(), "16+4");
		inputManager.clearall();
		
		//example with wrong characters
		inputManager.addSymbol('1');
		inputManager.addSymbol('+');
		inputManager.addSymbol('w');
		inputManager.addSymbol('5');
		inputManager.addSymbol('*');
		inputManager.addSymbol('2');
		inputManager.addSymbol('^');
		inputManager.addSymbol('5');
		inputManager.addSymbol('=');
		inputManager.clearall();	
		
		inputManager.addSymbol('8');
		inputManager.addSymbol('/');
		inputManager.addSymbol('0');
		inputManager.addSymbol('=');
		inputManager.clearall();	
	}
	
}
