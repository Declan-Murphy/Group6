package org.dt340a.group6.sprint1.fileImport;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.dt340a.group6.sprint1.persistence.PersistenceUtil;
import org.dt340a.group6.sprint1.entity.Cause;

public class CauseReaderTest {
	private CauseReader causeReader;
	private Cause cause;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceUtil.filePath = "test.xls";
	}
	
	@Before
	public void setUp() throws Exception {
		causeReader = new CauseReader();
		cause = causeReader.getOneEventCauseRow(4);
	}

	@Test
	public void testDescriptionTrue() {
		assertEquals("should be equal", 3, cause.getCauseCode(),0);
		assertEquals("should be equal", 4097, cause.getEventId(),0);
		assertEquals("should be equal",
				"RRC CONN SETUP-EUTRAN GENERATED REASON",
				cause.getDescription());
	}
}
