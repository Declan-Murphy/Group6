package org.dt340a.group6.sprint1.fileImport;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import org.dt340a.group6.sprint1.entity.Equipment;

public class EquipmentReader {

	private FileReader fileReader;

	public EquipmentReader() {
		fileReader = new FileReader();
	}

	// possibly this should be a set. not sure.
	public List<Object> getAllEquipmentRows() {
		int length = fileReader.getSheetColumnLength(3);
		ArrayList<Object> equipment = new ArrayList<>(length);
		for (int i = 1; i < length + 1; i++) {
			equipment.add(getOneEquipmentRow(i));
		}
		return equipment;
	}

	public Equipment getOneEquipmentRow(int rowNumber) {
		return new Equipment.Builder().tAC(getTAC(rowNumber))
				.marketingName(getMarketingName(rowNumber))
				.manufacturer(getManufacturer(rowNumber))
				.accessCapability(getAccessCapability(rowNumber))
				.model(getModel(rowNumber))
				.vendorName(getVendorName(rowNumber))
				.equipmentType(getEquipmentType(rowNumber))
				.operatingSystem(getOperatingSystem(rowNumber))
				.inputMode(getInputMode(rowNumber)).build();
	}

	public String getInputMode(int rowNumber) {
		Cell cell = fileReader.getCell(3, rowNumber, 8);
		return cell.getStringCellValue();
	}

	public String getOperatingSystem(int rowNumber) {
		Cell cell = fileReader.getCell(3, rowNumber, 7);
		return cell.getStringCellValue();
	}

	public String getEquipmentType(int rowNumber) {
		Cell cell = fileReader.getCell(3, rowNumber, 6);
		return cell.getStringCellValue();
	}

	public String getVendorName(int rowNumber) {
		Cell cell = fileReader.getCell(3, rowNumber, 5);
		return cell.getStringCellValue();
	}

	public String getModel(int rowNumber) {
		Cell cell = fileReader.getCell(3, rowNumber, 4);
		if (cell.getCellType() != Cell.CELL_TYPE_STRING)
			return "NOT VALID!!";
		return cell.getStringCellValue();
	}

	public String getAccessCapability(int rowNumber) {
		Cell cell = fileReader.getCell(3, rowNumber, 3);
		return cell.getStringCellValue();
	}

	public String getManufacturer(int rowNumber) {
		Cell cell = fileReader.getCell(3, rowNumber, 2);
		return cell.getStringCellValue();
	}

	public String getMarketingName(int rowNumber) {
		Cell cell = fileReader.getCell(3, rowNumber, 1);
		if (cell.getCellType() != Cell.CELL_TYPE_STRING)
			return "NOT VALID!!";
		return cell.getStringCellValue();
	}

	public int getTAC(int rowNumber) {
		Cell cell = fileReader.getCell(3, rowNumber, 0);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return (int) cell.getNumericCellValue();
	}

}
