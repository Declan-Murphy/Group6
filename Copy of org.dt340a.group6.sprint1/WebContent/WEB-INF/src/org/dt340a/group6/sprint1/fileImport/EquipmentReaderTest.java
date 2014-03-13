package org.dt340a.group6.sprint1.fileImport;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.dt340a.group6.sprint1.persistence.PersistenceUtil;
import org.dt340a.group6.sprint1.entity.Equipment;

public class EquipmentReaderTest {
	private EquipmentReader equipmentReader;
	private Equipment equipment;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceUtil.filePath = "test.xls";
	}
	
	@Before
	public void setUp() throws Exception {
		equipmentReader = new EquipmentReader();
		equipment = equipmentReader.getOneEquipmentRow(5);
	}

	@Test
	public void testMarketingNameTrue() {
		assertEquals("should be equal", "M930 NA DB", equipment.getMarketingName());
	}

}
