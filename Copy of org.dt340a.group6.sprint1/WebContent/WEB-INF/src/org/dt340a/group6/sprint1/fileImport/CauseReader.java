package org.dt340a.group6.sprint1.fileImport;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import org.dt340a.group6.sprint1.persistence.PersistenceUtil;
import org.dt340a.group6.sprint1.entity.Cause;

public class CauseReader {

	private FileReader fileReader;

	public CauseReader() {
		fileReader = new FileReader();
	}

	// possibly this should be a set. not sure.
	public List<Object> getAllEventCauseRows() {
		int length = fileReader.getSheetColumnLength(1);
		ArrayList<Object> eventCauses = new ArrayList<>(length);
		for (int i = 1; i < length + 1; i++) {
			eventCauses.add(getOneEventCauseRow(i));
		}
		return eventCauses;
	}

	public Cause getOneEventCauseRow(int rowNumber) {
		return new Cause.Builder().eventId(getEventId(rowNumber))
				.causeCode(getCauseCode(rowNumber))
				.description(getDescription(rowNumber)).build();
	}

	public int getEventId(int rowNumber) {
		Cell cell = fileReader.getCell(1, rowNumber, 1);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return (int)cell.getNumericCellValue();
	}

	public int getCauseCode(int rowNumber) {
		Cell cell = fileReader.getCell(1, rowNumber, 0);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return (int)cell.getNumericCellValue();
	}

	public String getDescription(int rowNumber) {
		Cell cell = fileReader.getCell(1, rowNumber, 2);
		return cell.getStringCellValue();
	}

}
