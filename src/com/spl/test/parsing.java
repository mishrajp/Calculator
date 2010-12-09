package com.spl.test;

import junit.framework.*;

import com.spl.math.LogicalManager;
import com.spl.math.Parser;


public class parsing extends TestCase {
	
	Parser parser = new Parser();
	
	public void setUp() {
		parser.load_valid_operators();
	}
	
	public void testConverter() {
		parser.converter("3+3^2");
		parser.converter("3+55-14*2^2");
		parser.converter("163+5.5-14/2*2");
		parser.converter("e163+5.5-14^2*2");
		parser.converter("~-6");
		parser.converter("~+86");
		parser.converter("~9");
		parser.converter("~-126+189^2");
		parser.converter("123+2-(12-(12^2))/2");
		parser.converter("(3+55)/2");
		//this is bug
		parser.converter("18*(3*55)/12*~-9");
		parser.converter("((3/55+2)/2)");
		parser.converter("5+s16-3");
		
		//there is a problem in division when inside parenthesis 
	}
	
	public void testIsNumber(){
		assertTrue(parser.isNumber("123"));
		assertTrue(parser.isNumber("223"));
		assertTrue(parser.isNumber("123.67"));
		assertFalse(parser.isNumber("1a23.67"));
	}
	
	public void testTruncateNumber() {
		LogicalManager logical = new LogicalManager();
		assertEquals(logical.truncate_number("120.355"), "120");
		assertEquals(logical.truncate_number("12"), "12");
		assertEquals(logical.truncate_number("12035.5"), "12035");
	}
}
