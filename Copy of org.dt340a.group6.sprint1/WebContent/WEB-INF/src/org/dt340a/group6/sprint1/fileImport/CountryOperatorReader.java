package org.dt340a.group6.sprint1.fileImport;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import org.dt340a.group6.sprint1.entity.CountryOperator;

public class CountryOperatorReader {

	private FileReader fileReader;

	public CountryOperatorReader() {
		fileReader = new FileReader();
	}

	// possibly this should be a set. not sure.
	public List<Object> getAllCountryOperatorRows() {
		int length = fileReader.getSheetColumnLength(4);
		ArrayList<Object> countryOperators = new ArrayList<>(length);
		for (int i = 1; i < length + 1; i++) {
			countryOperators.add(getOneCountryOperatorRow(i));
		}
		return countryOperators;
	}

	public CountryOperator getOneCountryOperatorRow(int rowNumber) {
		return new CountryOperator.Builder().mCC(getMCC(rowNumber))
				.mNC(getMNC(rowNumber))
				.country(getCountry(rowNumber))
				.operator(getOperator(rowNumber)).build();
	}

	public double getMCC(int rowNumber) {
		Cell cell = fileReader.getCell(4, rowNumber, 0);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return cell.getNumericCellValue();
	}

	public double getMNC(int rowNumber) {
		Cell cell = fileReader.getCell(4, rowNumber, 1);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return cell.getNumericCellValue();
	}

	public String getCountry(int rowNumber) {
		Cell cell = fileReader.getCell(4, rowNumber, 2);
		return cell.getStringCellValue();
	}

	public String getOperator(int rowNumber) {
		Cell cell = fileReader.getCell(4, rowNumber, 3);
		return cell.getStringCellValue();
	}

}
