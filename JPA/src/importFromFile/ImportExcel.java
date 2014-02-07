package importFromFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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
	 * market and operator make up the first 6 digits of IMSI
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
			System.out.println("IMSI is :" + getIMSI(i));
			System.out.println("HIER3_ID is :" + getHIER3_ID(i));
			System.out.println("HIER32_IDSI is :" + getHIER32_ID(i));
			System.out.println("HIER321_IDSI is :" + getHIER321_ID(i));
			System.out.println("EvenCause Description is :" + getEventCauseDescription( getCauseCode(i),getBaseEventId(i) ));
			System.out.println("Failure Class Description is :" + getFailureClassDescription( getFailureClass(i) ));
			System.out.println("marketing name is :" + getMarketingName( getUEType(i) ));
			System.out.println("Manufacturer name is :" + getManufacturer( getUEType(i) ));
			System.out.println("ACCESS CAPABILITY name is :" + getAccessCapability( getUEType(i) ));
			System.out.println("Model name is :" + getModel( getUEType(i) ));
			System.out.println("Vendor name is :" + getVendorName( getUEType(i) ));
			System.out.println("UETABLE UETYPE name is :" + getUETableUEtype( getUEType(i) ));
			System.out.println("OS name is :" + getOS( getUEType(i) ));
			System.out.println("Input Mode name is :" + getInputMode( getUEType(i) ));
			System.out.println("COUNTRY name is :" + getCountry( getMarket(i) ));
			System.out.println("OPERATOR name is :" + getOperator( getMarket(i),getOperator(i) ));


		}
	}

	//TODO For the UE table sheet maybe do them all at once so you don't have to search for the tac each time
	
	//TODO THIS DOES NOT WORK PROPERLY LOOK BECAUSE THERE ARE MULTIPLE MCC DON@T USE
	public String getOperator(double market, double operator) {
		Sheet MCCMNCSheet = wb.getSheetAt(4);
		//i starts at one to avoid headings
		for(int i=1; i<=MCCMNCSheet.getLastRowNum(); i++){
			Cell mccCell = getCell(4, i, 0);
			Cell mncCell = getCell(4, i, 1);
			//TODO Validation necessary on previous two cells
			if(mccCell.getNumericCellValue() == market && mncCell.getNumericCellValue() == operator ){
				Cell operatorCell = getCell(4, i, 3);
				return operatorCell.getStringCellValue();
			}		
		}
		return "NOT FOUND!!";
	}
	public String getCountry(double market) {
		Sheet MCCMNCSheet = wb.getSheetAt(4);
		//i starts at one to avoid headings
		for(int i=1; i<=MCCMNCSheet.getLastRowNum(); i++){
			Cell tacCell = getCell(4, i, 0);
			//TODO Validation necessary on previous two cells
			if(tacCell.getNumericCellValue() == market){
				Cell mccCell = getCell(4, i, 2);
				return mccCell.getStringCellValue();
			}		
		}
		return "NOT FOUND!!";
	}
	public double getMCC(double market) {
		Sheet MCCMNCSheet = wb.getSheetAt(4);
		//i starts at one to avoid headings
		for(int i=1; i<=MCCMNCSheet.getLastRowNum(); i++){
			Cell tacCell = getCell(4, i, 0);
			//TODO Validation necessary on previous two cells
			if(tacCell.getNumericCellValue() == market){
				Cell mccCell = getCell(4, i, 0);
				return mccCell.getNumericCellValue();
			}		
		}
		return -1;
	}
	public String getInputMode(double ueType) {
		Sheet ueTableSheet = wb.getSheetAt(3);
		//i starts at one to avoid headings
		for(int i=1; i<=ueTableSheet.getLastRowNum(); i++){
			Cell tacCell = getCell(3, i, 0);
			//TODO Validation necessary on previous two cells
			if(tacCell.getNumericCellValue() == ueType){
				Cell inputModeCell = getCell(3, i, 8);
				return inputModeCell.getStringCellValue();
			}
			
		}
		return "NOT FOUND!";
	}
	public String getOS(double ueType) {
		Sheet ueTableSheet = wb.getSheetAt(3);
		//i starts at one to avoid headings
		for(int i=1; i<=ueTableSheet.getLastRowNum(); i++){
			Cell tacCell = getCell(3, i, 0);
			//TODO Validation necessary on previous two cells
			if(tacCell.getNumericCellValue() == ueType){
				Cell osCell = getCell(3, i, 7);
				return osCell.getStringCellValue();
			}
			
		}
		return "NOT FOUND!";
	}
	public String getUETableUEtype(double ueType) {
		Sheet ueTableSheet = wb.getSheetAt(3);
		//i starts at one to avoid headings
		for(int i=1; i<=ueTableSheet.getLastRowNum(); i++){
			Cell tacCell = getCell(3, i, 0);
			//TODO Validation necessary on previous two cells
			if(tacCell.getNumericCellValue() == ueType){
				Cell ueTypeCell = getCell(3, i, 6);
				return ueTypeCell.getStringCellValue();
			}
			
		}
		return "NOT FOUND!";
	}
	public String getVendorName(double ueType) {
		Sheet ueTableSheet = wb.getSheetAt(3);
		//i starts at one to avoid headings
		for(int i=1; i<=ueTableSheet.getLastRowNum(); i++){
			Cell tacCell = getCell(3, i, 0);
			//TODO Validation necessary on previous two cells
			if(tacCell.getNumericCellValue() == ueType){
				Cell vendorCell = getCell(3, i, 5);
				return vendorCell.getStringCellValue();
			}
			
		}
		return "NOT FOUND!";
	}
	public String getModel(double ueType) {
		Sheet ueTableSheet = wb.getSheetAt(3);
		//i starts at one to avoid headings
		for(int i=1; i<=ueTableSheet.getLastRowNum(); i++){
			Cell tacCell = getCell(3, i, 0);
			//TODO Validation necessary on previous two cells
			if(tacCell.getNumericCellValue() == ueType){
				Cell modelCell = getCell(3, i, 4);
				return modelCell.getStringCellValue();
			}
			
		}
		return "NOT FOUND!";
	}	
	//ACCESS CAPABILITY MAYBE needs to split into a list. 
	public String getAccessCapability(double ueType) {
		Sheet ueTableSheet = wb.getSheetAt(3);
		//i starts at one to avoid headings
		for(int i=1; i<=ueTableSheet.getLastRowNum(); i++){
			Cell tacCell = getCell(3, i, 0);
			//TODO Validation necessary on previous two cells
			if(tacCell.getNumericCellValue() == ueType){
				Cell accessCapabilityCell = getCell(3, i, 3);
				return accessCapabilityCell.getStringCellValue();
			}
			
		}
		return "NOT FOUND!";
	}
	public String getManufacturer(double ueType) {
		Sheet ueTableSheet = wb.getSheetAt(3);
		//i starts at one to avoid headings
		for(int i=1; i<=ueTableSheet.getLastRowNum(); i++){
			Cell tacCell = getCell(3, i, 0);
			//TODO Validation necessary on previous two cells
			if(tacCell.getNumericCellValue() == ueType){
				Cell manufacturerCell = getCell(3, i, 2);
				return manufacturerCell.getStringCellValue();
			}
			
		}
		return "NOT FOUND!";
	}
	public String getMarketingName(double ueType) {
		Sheet ueTableSheet = wb.getSheetAt(3);
		//i starts at one to avoid headings
		for(int i=1; i<=ueTableSheet.getLastRowNum(); i++){
			Cell tacCell = getCell(3, i, 0);
			//TODO Validation necessary on previous two cells
			if(tacCell.getNumericCellValue() == ueType){
				Cell marketingCell = getCell(3, i, 1);
				return marketingCell.getStringCellValue();
			}
			
		}
		return "NOT FOUND!";
	}
	public String getFailureClassDescription(double failureClass) {
		Sheet failureClassSheet = wb.getSheetAt(2);
		//i starts at one to avoid headings
		for(int i=1; i<=failureClassSheet.getLastRowNum(); i++){
			Cell failureClassCell = getCell(2, i, 0);
			//TODO Validation necessary on previous two cells
			if(failureClassCell.getNumericCellValue() == failureClass){
				Cell descriptionCell = getCell(2, i, 1);
				return descriptionCell.getStringCellValue();
			}
			
		}
		return "NOT FOUND!";
	}
	public String getEventCauseDescription(double CauseCode, double EventId) {
		Sheet eventCauseSheet = wb.getSheetAt(1);
		//i starts at one to avoid headings
		for(int i=1; i<=eventCauseSheet.getLastRowNum(); i++){
			Cell causeCodeCell = getCell(1, i, 0);
			Cell eventIdCell = getCell(1, i, 1);
			//TODO Validation necessary on previous two cells
			if(causeCodeCell.getNumericCellValue() == CauseCode && eventIdCell.getNumericCellValue() == EventId){
				Cell descriptionCell = getCell(1, i, 2);
				return descriptionCell.getStringCellValue();
			}
			
		}
		return "NOT FOUND!";
	}
	public String getHIER321_ID(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 13);
		if(cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  "NOT VALID!!";
		//Set the cell type to string to avoid number rounding errors
		cell.setCellType(1);
		return cell.getStringCellValue();
	}
	public String getHIER32_ID(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 12);
		if(cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  "NOT VALID!!";
		cell.setCellType(1);
		return cell.getStringCellValue();
	}
	public String getHIER3_ID(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 11);
		if(cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  "NOT VALID!!";
		cell.setCellType(1);
		return cell.getStringCellValue();
	}
	public String getIMSI(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 10);
		if(cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  "NOT VALID!!";
		cell.setCellType(1);
		return cell.getStringCellValue();
	}
	public String getNEVersion(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 9);
		//TODO Validation
		return  cell.getStringCellValue();	
	}
	public double getCauseCode(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 8);
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
		//TODO VALIDATION
		return  cell.getDateCellValue();	
	}
	
	public Cell getCell(int sheetNumber, int rowNumber, int columnNumber){		
		Sheet sheet = wb.getSheetAt(sheetNumber);
		Row row = sheet.getRow(rowNumber);
		Cell cell = row.getCell(columnNumber);	
		return cell;		
	}

}
