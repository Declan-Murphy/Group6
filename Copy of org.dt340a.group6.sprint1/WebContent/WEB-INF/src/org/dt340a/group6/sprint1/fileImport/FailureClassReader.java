package org.dt340a.group6.sprint1.fileImport;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import org.dt340a.group6.sprint1.persistence.PersistenceUtil;
import org.dt340a.group6.sprint1.entity.FailureClass;

public class FailureClassReader {

	private FileReader fileReader;

	public FailureClassReader() {
		fileReader = new FileReader();
	}

	public static void main(String[] args) {
		FailureClassReader failureClassTable = new FailureClassReader();
		failureClassTable.persistAllFailureClasses();
		System.out.println("all persisted");
	}

	public void persistAllFailureClasses() {
		PersistenceUtil.persistAll(getAllFailureClassRows());
	}

	// possibly this should be a set. not sure. i've far bigger problems at the
	// moment though.
	public List<Object> getAllFailureClassRows() {
		int length = fileReader.getSheetColumnLength(2);
		ArrayList<Object> failureClasses = new ArrayList<>(length);
		for (int i = 1; i < length + 1; i++) {
			failureClasses.add(getOneFailureClassRow(i));
		}
		return failureClasses;
	}

	public FailureClass getOneFailureClassRow(int rowNumber) {
		return new FailureClass.Builder()
				.failureClass(getFailureClassCell(rowNumber))
				.description(getDescription(rowNumber)).build();
	}

	public int getFailureClassCell(int rowNumber) {
		Cell cell = fileReader.getCell(2, rowNumber, 0);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return (int) cell.getNumericCellValue();
	}

	public String getDescription(int rowNumber) {
		Cell cell = fileReader.getCell(2, rowNumber, 1);
		return cell.getStringCellValue();
	}

}
