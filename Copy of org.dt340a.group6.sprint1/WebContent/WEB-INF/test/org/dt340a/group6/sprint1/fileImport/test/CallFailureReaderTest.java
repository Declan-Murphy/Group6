package org.dt340a.group6.sprint1.fileImport.test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.dt340a.group6.sprint1.persistence.PersistenceUtil;
import org.dt340a.group6.sprint1.entity.AllMasterTableRows;
import org.dt340a.group6.sprint1.entity.CallFailure;
import org.dt340a.group6.sprint1.fileImport.CallFailureReader;
import org.dt340a.group6.sprint1.fileImport.CauseReader;
import org.dt340a.group6.sprint1.fileImport.CountryOperatorReader;
import org.dt340a.group6.sprint1.fileImport.EquipmentReader;
import org.dt340a.group6.sprint1.fileImport.FailureClassReader;

public class CallFailureReaderTest {
	private FailureClassReader failureClassReader;
	private CauseReader causeReader;
	private CountryOperatorReader countryOperatorReader;
	private EquipmentReader equipmentReader;
	private CallFailureReader callFailureReader;
	private CallFailure callFailure;
	private AllMasterTableRows allMasterTableRows;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceUtil.filePath = "test.xls";
	}
	
	@Before
	public void setUp() throws Exception {
		failureClassReader = new FailureClassReader();
		causeReader = new CauseReader();
		countryOperatorReader = new CountryOperatorReader();
		equipmentReader = new EquipmentReader();
		callFailureReader = new CallFailureReader();
		allMasterTableRows = new AllMasterTableRows();
		allMasterTableRows.setFailureClasses(failureClassReader
				.getAllFailureClassRows());
		allMasterTableRows.setCauses(causeReader.getAllEventCauseRows());
		allMasterTableRows.setCountryOperators(countryOperatorReader
				.getAllCountryOperatorRows());
		allMasterTableRows.setEquipment(equipmentReader.getAllEquipmentRows());
		callFailureReader.setLocalTableLists(allMasterTableRows);
		callFailure = callFailureReader.getOneCallFailureRow(5);
		System.out.println("here");
		System.out.println(callFailure);
	}

	@Test
	public void testHier321True() {
		System.out.println(callFailure);
		assertEquals("should be equal", "1150444940909480000", callFailure.getHier321());
	}

}
