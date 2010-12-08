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
	
	public void testConverter() {
		inputManager.converter("3+3^2");
		inputManager.converter("3+55-14*2^2");
		inputManager.converter("163+5.5-14/2*2");
		inputManager.converter("e163+5.5-14^2*2");
		inputManager.converter("~-6");
		inputManager.converter("~+86");
		inputManager.converter("~9");
		inputManager.converter("~-126+189^2");
		inputManager.converter("123+2-(12-(12^2))/2");
		inputManager.converter("(3+55)/2");
		//this is bug
		inputManager.converter("18*(3*55)/12*~-9");
		inputManager.converter("((3/55+2)/2)");
		//there is a problem in division when inside parenethsis 
	}
	public void testIsNumber(){
		assertTrue(inputManager.isNumber("123"));
		assertTrue(inputManager.isNumber("223"));
		assertTrue(inputManager.isNumber("123.67"));
		assertFalse(inputManager.isNumber("1a23.67"));
	}
	
}
