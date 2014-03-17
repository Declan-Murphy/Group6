package org.dt340a.group6.sprint1.fileImport.test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.dt340a.group6.sprint1.persistence.PersistenceUtil;
import org.dt340a.group6.sprint1.entity.CountryOperator;
import org.dt340a.group6.sprint1.fileImport.CountryOperatorReader;

public class CountryOperatorReaderTest {
	private CountryOperatorReader countryOperatorReader;
	private CountryOperator countryOperator;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceUtil.filePath = "test.xls";
	}
	
	@Before
	public void setUp() throws Exception {
		countryOperatorReader = new CountryOperatorReader();
		countryOperator = countryOperatorReader.getOneCountryOperatorRow(4);
	}

	@Test
	public void testOperatorTrue() {
		assertEquals("should be equal", 240, countryOperator.getmCC(),0);
		assertEquals("should be equal", 1, countryOperator.getmNC(),0);
		assertEquals("should be equal", "Sweden", countryOperator.getCountry());
		assertEquals("should be equal", "Telia Sonera-SE", countryOperator.getOperator());

	}

}
