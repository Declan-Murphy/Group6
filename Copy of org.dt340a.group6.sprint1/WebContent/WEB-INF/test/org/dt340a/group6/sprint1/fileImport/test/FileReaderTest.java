package org.dt340a.group6.sprint1.fileImport.test;

import static org.junit.Assert.assertEquals;
import org.apache.poi.ss.usermodel.Cell;
import org.dt340a.group6.sprint1.fileImport.FileReader;
import org.junit.Test;

public class FileReaderTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testGetCellNumeric() {
		FileReader fileReader = new FileReader("test.xls");
		Cell cell = fileReader.getCell(0,1,1);
		double num = 4098.0;
		System.out.println("cell value " + cell.getNumericCellValue());
		assertEquals("Cell value should have been 4098", num, cell.getNumericCellValue(),00);
		//fail("Not yet implemented");
	}
	@Test
	public void testGetCellString() {
		FileReader fileReader = new FileReader("test.xls");
		Cell cell = fileReader.getCell(0,1,11);
		String testString = "4809532081614990000";
		cell.setCellType(1);
		System.out.println("cell value " + cell.getStringCellValue());
		assertEquals("Cell value should have been 4809532081614990000", testString, cell.getStringCellValue());
		//fail("Not yet implemented");
	}
	@Test
	public void testGetSheetColumnLengthTrue() {
		FileReader fileReader = new FileReader("test.xls");
		System.out.println("sheet length " + fileReader.getSheetColumnLength(0));
		assertEquals("Row length is 1000", 1000, fileReader.getSheetColumnLength(0));
		//fail("Not yet implemented");
	}

}
