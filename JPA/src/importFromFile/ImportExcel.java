package importFromFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ImportExcel {
	public Workbook wb;
	
	public ImportExcel(){
		try {
			wb = WorkbookFactory.create(new FileInputStream("test.xls"));
		} catch (Exception e) {
			e.printStackTrace();
		}	
		processFile();
	}
	/*
	 * There should be a method for each sheet i.e getBaseData then each sheet has its own set of methods
	 */
	public void processFile() {
		Sheet testSheet = wb.getSheetAt(0);
		System.out.println("Last row num(start at 0): " + testSheet.getLastRowNum() );
		System.out.println("Physical num of row: " + testSheet.getPhysicalNumberOfRows() );
		for(int i = 1; i<=  testSheet.getLastRowNum(); i++ ){
			System.out.println("Date is :" + getDateTime(i));
			System.out.println("Base EventID is :" + getBaseEventId(i));
			System.out.println("Failure Class is :" + getFailureClass(i));
			System.out.println("UEType is :" + getUEType(i));
			System.out.println("Market is :" + getMarket(i));
			System.out.println("Operator is :" + getOperator(i));
			System.out.println("CellId is :" + getCellId(i));
			System.out.println("Duration is :" + getDuration(i));
			System.out.println("Cause Code is :" + getCauseCode(i));
			System.out.println("NE Version is :" + getNEVersion(i));
		}
	}
	//IMSI	HIER3_ID	HIER32_ID	HIER321_ID
	public String getNEVersion(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 9);
		return  cell.getStringCellValue();	
	}
	public double getCauseCode(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 2);
		if(cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return cell.getNumericCellValue();
	}
	public double getDuration(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 7);
		if(cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return cell.getNumericCellValue();
	}
	public double getCellId(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 6);
		if(cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return cell.getNumericCellValue();
	}
	public double getOperator(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 5);
		if(cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return cell.getNumericCellValue();
	}
	public double getMarket(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 4);
		if(cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return cell.getNumericCellValue();
	}
	public double getUEType(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 3);
		if(cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return cell.getNumericCellValue();
	}
	public double getFailureClass(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 2);
		if(cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return cell.getNumericCellValue();
	}
	public double getBaseEventId(int rowNumber) {
		//EventId appears in two table, this is only for the one in base Data
		//It will always be on sheet zero in column one
		Cell cell = getCell(0, rowNumber, 1);
		if(cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return cell.getNumericCellValue();
	}
	
	public java.util.Date getDateTime(int rowNumber)  {
		//Date will always be on sheet zero in column zero
		Cell cell = getCell(0, rowNumber, 0);
		return  cell.getDateCellValue();	
	}
	
	public Cell getCell(int sheetNumber, int rowNumber, int columnNumber){		
		Sheet sheet = wb.getSheetAt(sheetNumber);
		Row row = sheet.getRow(rowNumber);
		Cell cell = row.getCell(columnNumber);	
		return cell;		
	}

}
