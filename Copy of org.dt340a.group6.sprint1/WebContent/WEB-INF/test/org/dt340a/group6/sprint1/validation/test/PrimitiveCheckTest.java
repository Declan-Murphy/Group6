package org.dt340a.group6.sprint1.validation.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.dt340a.group6.sprint1.validation.PrimitiveCheck;


public class PrimitiveCheckTest {

	@ Test
	public void testIntegerIsInteger() {
		assertEquals("The string '5' must be an integer.", true, PrimitiveCheck.isInteger("5"));
	}

	@ Test
	public void testDoubleIsNotInteger() {
		assertEquals("The string '5.12' must not be an integer.", false, PrimitiveCheck.isInteger("5.12"));
	}
	
	@ Test
	public void testLetterIsNotInteger() {
		assertEquals("The string 'ab5c' must not be an integer.", false, PrimitiveCheck.isInteger("ab5c"));
	}
	
	@ Test
	public void testSymbolIsNotInteger() {
		assertEquals("The string '@!' must not be an integer.", false, PrimitiveCheck.isInteger("@!"));
	}
	
	@ Test
	public void testLongIsLong() {
		assertEquals("The string '240210000000003' must be an integer.", true, PrimitiveCheck.isLong("240210000000003"));
	}

	@ Test
	public void testDoubleIsNotLong() {
		assertEquals("The string '5.12' must not be an integer.", false, PrimitiveCheck.isLong("5.12"));
	}
	
	@ Test
	public void testLetterIsNotLong() {
		assertEquals("The string 'ab5c' must not be an integer.", false, PrimitiveCheck.isLong("ab5c"));
	}
	
	@ Test
	public void testSymbolIsNotLong() {
		assertEquals("The string '@!' must not be an integer.", false, PrimitiveCheck.isLong("@!"));
	}
}
