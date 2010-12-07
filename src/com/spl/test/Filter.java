package com.spl.test;

import com.spl.input.*;

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
	
	public void testFilterInput() {
		//bad example
		assertEquals(inputManager.filter("3 + 5 = "), "3+");
		assertEquals(inputManager.filter("1 + w"), "1+");
		assertEquals(inputManager.filter("w ) ?"), "");
		assertEquals(inputManager.filter(" = "), "");
		assertEquals(inputManager.filter(" = "), "");
		assertEquals(inputManager.filter(", _ "), "");
		
		//good examples
		assertEquals(inputManager.filter(" 1 + 2 = "), "1+2");
		assertEquals(inputManager.filter("22 - 12 = "), "22-12");
		assertEquals(inputManager.filter("242 - 1x2,"), "242-12");
		
	}
	
}
