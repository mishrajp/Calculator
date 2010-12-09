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
		
		//assertEquals(Model.getInstance().getStatement(), "1+5");
		inputManager.clearall();		
	}
/*	
	public void testFilterInput() {
		//bad example
		//assertEquals(inputManager.filter("3 + 5 = "), "3+");
		assertEquals(inputManager.filter("1 + w"), "1+");
		assertEquals(inputManager.filter("w & ?"), "");
		assertEquals(inputManager.filter(" = "), "");
		assertEquals(inputManager.filter(" = "), "");
		assertEquals(inputManager.filter(", _ "), "");
		
		//good examples
		assertEquals(inputManager.filter(" 1 + 2 = "), "1+2");
		assertEquals(inputManager.filter("22 - 12 = "), "22-12");
		assertEquals(inputManager.filter("242 - 1x2,"), "242-12");
		//assertEquals(inputManager.filter("sr(4) + 3"), "sr(4)+3");
		
	}
*/
	
}
