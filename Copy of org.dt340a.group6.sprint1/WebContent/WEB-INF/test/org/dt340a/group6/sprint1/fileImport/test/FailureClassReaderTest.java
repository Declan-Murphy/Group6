package org.dt340a.group6.sprint1.fileImport.test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.dt340a.group6.sprint1.persistence.PersistenceUtil;
import org.dt340a.group6.sprint1.entity.FailureClass;
import org.dt340a.group6.sprint1.fileImport.FailureClassReader;

public class FailureClassReaderTest {
	private FailureClassReader failureClassReader;
	private FailureClass failureClass;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceUtil.filePath = "test.xls";
	}

	@Before
	public void setUp() throws Exception {
		failureClassReader = new FailureClassReader();
		failureClass = failureClassReader.getOneFailureClassRow(3);
	}

	@Test
	public void testFailureClassTrue() {
		assertEquals("should be equal", "MT ACCESS",
				failureClass.getDescription());
		assertEquals("should be equal", 2,
				failureClass.getFailureClass());
	}

}
