package unitTests;

import com.spl.input.*;

import junit.framework.*;

public class Filter extends TestCase {

	InputManager inputManager = new InputManager();
	public void testOperatorsLoading() {
		//inputManger.load_valid_operators();
		assertTrue(inputManager.search_symbols('1'));
		assertTrue(inputManager.search_symbols('2'));
		
	}
	public void testFilterInput() {
		//bad example
		assertEquals(inputManager.filter_input("3 + 5 = "), "3+");
		assertEquals(inputManager.filter_input("1 + w"), "1+");
		assertEquals(inputManager.filter_input("w ) ?"), "");
		assertEquals(inputManager.filter_input(" = "), "");
		
		assertEquals(inputManager.filter_input(" 1 + 2 = "), "1+2");
		assertEquals(inputManager.filter_input("22 - 12 = "), "22-12");
		
		
	}
	
}
