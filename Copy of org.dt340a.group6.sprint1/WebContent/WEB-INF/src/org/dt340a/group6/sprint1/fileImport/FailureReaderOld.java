//package org.dt340a.group6.sprint1.fileImport;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.apache.poi.ss.usermodel.Cell;
//
//import org.dt340a.group6.sprint1.entity.*;
//
//
//public class FailureReaderOld {
//
//	private FileReader fileReader;
//
//	public FailureReaderOld() {
//		fileReader = new FileReader();
//		// System.out.println("length: " + fileReader.getSheetColumnLength(0));
//	}
//
//	// possibly this should be a set. not sure.
//	public List<Object> getAllFailureRows() {
//		int length = fileReader.getSheetColumnLength(0);
//		ArrayList<Object> failure = new ArrayList<>(length);
//		for (int i = 1; i < length + 1; i++) {
//			failure.add(getOneFailureRow(i));
//		}
//		return failure;
//	}
//	
//	private Date dateTime;
//	private Cause cause;
//	private FailureClass failureClass;
//	private Equipment equipment;
//	private CountryOperator countryOperator;
//	private int cellId;
//	private int duration;
//	private String nEVersion;
//	private String iMSI;
//	private String hier3;
//	private String hier32;
//	private String hier321;
//	getCause() {
//		getCountryOperator() {
//		getEquipment() {
//		getFailureClass() {
//		getHier3() {
//		getHier32() {
//		getHier321() {
//		getiMSI() {
//		getnEVersion() {
//		getCellId() {
//		getDuration() {
//
//	public Failure getOneFailureRow(int rowNumber) {
//		return new Failure.Builder().dateTime(getDateTime(rowNumber))
//				.marketingName(getMarketingName(rowNumber))
//				.manufacturer(getManufacturer(rowNumber))
//				.accessCapability(getAccessCapability(rowNumber))
//				.model(getModel(rowNumber))
//				.vendorName(getVendorName(rowNumber))
//				.equipmentType(getEquipmentType(rowNumber))
//				.operatingSystem(getOperatingSystem(rowNumber))
//				.inputMode(getInputMode(rowNumber)).build();
//	}
//
//	public String getBDHIER321_ID(int rowNumber) {
//		Cell cell = fileReader.getCell(0, rowNumber, 13);
//		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
//			return "NOT VALID!!";
//		// Set the cell type to string to avoid number rounding errors
//		cell.setCellType(1);
//		return cell.getStringCellValue();
//	}
//
//	public String getBDHIER32_ID(int rowNumber) {
//		Cell cell = fileReader.getCell(0, rowNumber, 12);
//		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
//			return "NOT VALID!!";
//		cell.setCellType(1);
//		return cell.getStringCellValue();
//	}
//
//	public String getBDHIER3_ID(int rowNumber) {
//		Cell cell = fileReader.getCell(0, rowNumber, 11);
//		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
//			return "NOT VALID!!";
//		cell.setCellType(1);
//		return cell.getStringCellValue();
//	}
//
//	public String getBDIMSI(int rowNumber) {
//		Cell cell = fileReader.getCell(0, rowNumber, 10);
//		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
//			return "NOT VALID!!";
//		cell.setCellType(1);
//		return cell.getStringCellValue();
//	}
//
//	public String getBDNEVersion(int rowNumber) {
//		Cell cell = fileReader.getCell(0, rowNumber, 9);
//		// TODO Validation
//		return cell.getStringCellValue();
//	}
//
//	public int getBDCauseCode(int rowNumber) {
//		Cell cell = fileReader.getCell(0, rowNumber, 8);
//		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
//			return -1;
//		return (int) cell.getNumericCellValue();
//	}
//
//	public int getBDDuration(int rowNumber) {
//		Cell cell = fileReader.getCell(0, rowNumber, 7);
//		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
//			return -1;
//		return (int) cell.getNumericCellValue();
//	}
//
//	public int getBDCellId(int rowNumber) {
//		Cell cell = fileReader.getCell(0, rowNumber, 6);
//		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
//			return -1;
//		return (int) cell.getNumericCellValue();
//	}
//
//	public int getBDOperator(int rowNumber) {
//		Cell cell = fileReader.getCell(0, rowNumber, 5);
//		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
//			return -1;
//		return (int) cell.getNumericCellValue();
//	}
//
//	public int getBDMarket(int rowNumber) {
//		Cell cell = fileReader.getCell(0, rowNumber, 4);
//		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
//			return -1;
//		return (int) cell.getNumericCellValue();
//	}
//
//	public int getBDUEType(int rowNumber) {
//		Cell cell = fileReader.getCell(0, rowNumber, 3);
//		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
//			return -1;
//		return (int) cell.getNumericCellValue();
//	}
//
//	public int getFailureClass(int rowNumber) {
//		Cell cell = fileReader.getCell(0, rowNumber, 2);
//		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
//			return -1;
//		return (int) cell.getNumericCellValue();
//	}
//
//	public int getEventId(int rowNumber) {
//		// EventId appears in two table, this is only for the one in base Data
//		// It will always be on sheet zero in column one
//		Cell cell = fileReader.getCell(0, rowNumber, 1);
//		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
//			return -1;
//		return (int) cell.getNumericCellValue();
//	}
//
//	public java.util.Date getDateTime(int rowNumber) {
//		// Date will always be on sheet zero in column zero
//		Cell cell = fileReader.getCell(0, rowNumber, 0);
//		// TODO VALIDATION
//		return cell.getDateCellValue();
//	}
//
//}
