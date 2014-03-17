package org.dt340a.group6.sprint1.fileImport;

import org.dt340a.group6.sprint1.entity.*;
import org.dt340a.group6.sprint1.persistence.PersistenceUtil;
import org.dt340a.group6.sprint1.persistence.test.*;
import org.dt340a.group6.sprint1.validation.ValidateExcelFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileReader {
	public Workbook wb;

	public FileReader(String fileName) {
		try {
			// wb = WorkbookFactory.create(new FileInputStream("C:\\upload\\"+
			// fileName));
			wb = WorkbookFactory.create(new FileInputStream(fileName));

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		// processFile();
	}

	public FileReader() {
		this(PersistenceUtil.filePath);
	}


	public Cell getCell(int sheetNumber, int rowNumber, int columnNumber) {
		Sheet sheet = wb.getSheetAt(sheetNumber);
		Row row = sheet.getRow(rowNumber);
		Cell cell = row.getCell(columnNumber);
		return cell;
	}

	public int getSheetColumnLength(int sheetNumber) {
		return wb.getSheetAt(sheetNumber).getLastRowNum();
	}

}

