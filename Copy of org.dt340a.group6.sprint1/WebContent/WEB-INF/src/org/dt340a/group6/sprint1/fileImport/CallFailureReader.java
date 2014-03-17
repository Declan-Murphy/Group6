package org.dt340a.group6.sprint1.fileImport;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import org.dt340a.group6.sprint1.entity.*;

public class CallFailureReader {

	private FileReader fileReader;
	private List<Object> failureClasses;
	private List<Object> causes;
	private List<Object> countryOperators;
	private List<Object> equipmentList;
	private static int numOfInvalidRows;
	private static int numOfValidRows;
	
	public CallFailureReader() {
		fileReader = new FileReader();
	}

	// possibly this should be a set. not sure.
	public List<Object> getAllCallFailureRows(
			AllMasterTableRows allMasterTableRows) {
		setLocalTableLists(allMasterTableRows);
		int length = fileReader.getSheetColumnLength(0);
		numOfInvalidRows=0;
		numOfValidRows=0;
		ArrayList<Object> callFailures = new ArrayList<>(length);
		for (int i = 1; i < length + 1; i++) {
			try {
				callFailures.add(getOneCallFailureRow(i));
				numOfValidRows++;
			} catch (ForeignTableException exception) {
				//System.out.print("i = " + i + " ");
				//System.out.println(exception.getMessage());
				numOfInvalidRows++;
			}
		}
		System.out.println("Invalid rows = " + numOfInvalidRows + " ");
		System.out.println("Valid rows = " + numOfValidRows + " ");
		return callFailures;
	}

	public void setLocalTableLists(AllMasterTableRows allMasterTableRows) {
		failureClasses = allMasterTableRows.getFailureClasses();
		causes = allMasterTableRows.getCauses();
		countryOperators = allMasterTableRows.getCountryOperators();
		equipmentList = allMasterTableRows.getEquipment();
	}

	public CallFailure getOneCallFailureRow(int rowNumber)
			throws ForeignTableException {
		return new CallFailure.Builder().dateTime(getDateTime(rowNumber))
				.cause(getCause(rowNumber))
				.failureClass(getFailureClass(rowNumber))
				.equipment(getEquipment(rowNumber))
				.countryOperator(getCountryOperator(rowNumber))
				.cellId(getCellId(rowNumber)).duration(getDuration(rowNumber))
				.nEVersion(getNEVersion(rowNumber)).iMSI(getIMSI(rowNumber))
				.hier3(getHier3(rowNumber)).hier32(getHier32(rowNumber))
				.hier321(getHier321(rowNumber)).build();
	}

	public java.util.Date getDateTime(int rowNumber)  {
		//Date will always be on sheet zero in column zero
		Cell cell = fileReader.getCell(0, rowNumber, 0);
		try{
			return  cell.getDateCellValue();		
		}catch(Exception e){
			return null;
		}		
	}

	public Cause getCause(int rowNumber) throws ForeignTableException {
		double eventId = getEventId(rowNumber);
		double causeCode = getCauseCode(rowNumber);
		for (Object object : causes) {
			Cause cause = (Cause) object;
			if (cause.getEventId() == eventId
					&& cause.getCauseCode() == causeCode)
				return cause;
		}
		// return new Cause();
		throw new ForeignTableException("Cause not in table");
	}

	public double getEventId(int rowNumber) {
		// EventId appears in two table, this is only for the one in base Data
		// It will always be on sheet zero in column one
		Cell cell = fileReader.getCell(0, rowNumber, 1);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return cell.getNumericCellValue();
	}

	public double getCauseCode(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 8);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return cell.getNumericCellValue();
	}

	public FailureClass getFailureClass(int rowNumber)
			throws ForeignTableException {
		int failureClassCell = getFailureClassCell(rowNumber);
		for (Object object : failureClasses) {
			FailureClass failureClass = (FailureClass) object;
			if (failureClass.isFailureClassEqual(failureClassCell))
				return failureClass;
		}
		// return new FailureClass();
		throw new ForeignTableException("Failure Class not in table");
	}

	public int getFailureClassCell(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 2);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return (int) cell.getNumericCellValue();
	}

	public Equipment getEquipment(int rowNumber) throws ForeignTableException {
		int tAC = getTAC(rowNumber);
		for (Object object : equipmentList) {
			Equipment equipment = (Equipment) object;
			if (equipment.isTACEqual(tAC))
				return equipment;
		}
		throw new ForeignTableException("Equipment not in table");
	}

	public int getTAC(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 3);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return (int) cell.getNumericCellValue();
	}

	public CountryOperator getCountryOperator(int rowNumber)
			throws ForeignTableException {
		int mCC = getMCC(rowNumber);
		int mNC = getMNC(rowNumber);
		for (Object object : countryOperators) {
			CountryOperator countryOperator = (CountryOperator) object;
			if (countryOperator.isMCCEqual(mCC)
					&& countryOperator.isMNCEqual(mNC))
				return countryOperator;
		}
		throw new ForeignTableException("Country or Operator not in table");
	}

	public int getMNC(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 5);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return (int) cell.getNumericCellValue();
	}

	public int getMCC(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 4);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return (int) cell.getNumericCellValue();
	}

	public int getCellId(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 6);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return (int) cell.getNumericCellValue();
	}

	public int getDuration(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 7);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return (int) cell.getNumericCellValue();
	}

	public String getNEVersion(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 9);
		// TODO Validation
		return cell.getStringCellValue();
	}

	public String getIMSI(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 10);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return "NOT VALID!!";
		cell.setCellType(1);
		return cell.getStringCellValue();
	}

	public String getHier3(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 11);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return "NOT VALID!!";
		cell.setCellType(1);
		return cell.getStringCellValue();
	}

	public String getHier32(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 12);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return "NOT VALID!!";
		cell.setCellType(1);
		return cell.getStringCellValue();
	}

	public String getHier321(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 13);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return "NOT VALID!!";
		// Set the cell type to string to avoid number rounding errors
		cell.setCellType(1);
		return cell.getStringCellValue();
	}

	public static int getNumOfInvalidRows() {
		return numOfInvalidRows;
	}
	public static int getNumOfValidRows() {
		return numOfValidRows;
	}

}
