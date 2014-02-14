package importFromFile;

import main.*;
import entity.*;
import persistence.*;

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

public class ImportExcel {
	public Workbook wb;
	
	public ImportExcel(String fileName){
		try {
			wb = WorkbookFactory.create(new FileInputStream("C:\\upload\\"+ fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}	
		processFile();
	}
	public static void main(String args[]){
		//System.out.println("sdfsd");
		
		try {
			new ImportExcel("test.xls");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	/*
	 * There should be a method for each sheet i.e getBaseData then each sheet has its own set of methods
	 * market and operator make up the first 6 digits of IMSI
	 * Implement a cache on the UE TABLE to store the relationship between base table and UE Table
	 * For example when you fine out that the UE Number is associated with a row in the UR Table
	 * save that relationship somehow.
	 */
	public void processFile() {
		Sheet baseDataSheet = wb.getSheetAt(0);
		Sheet eventCauseSheet = wb.getSheetAt(1);
		Sheet failureClassSheet = wb.getSheetAt(2);
		Sheet ueTableSheet = wb.getSheetAt(3);
		Sheet mccmncSheet = wb.getSheetAt(4);
		//System.out.println("Last row num(start at 0): " + testSheet.getLastRowNum() );
		//System.out.println("Physical num of row: " + testSheet.getPhysicalNumberOfRows() );
		List<Object> callFailureList = new ArrayList<Object>();
		List<Object> failureClassList = new ArrayList<Object>();
	//<Object> userEquipmentList = new ArrayList<Object>();
		
	//	for(int j=0; j<=30;j++){		
			//TODO Do validation on strings
//			for(int i = 1; i<=baseDataSheet.getLastRowNum(); i++ ){
//				callFailureList.add(new CallFailure(getBDDateTime(i), getBDEventId(i), getBDFailureClass(i), getBDUEType(i), getBDMarket(i), 
//						getBDOperator(i), getBDCellId(i), getBDDuration(i), getBDNEVersion(i), getBDIMSI(i),getBDHIER3_ID(i),getBDHIER32_ID(i),getBDHIER321_ID(i)));				
//			}
//			for(int i=1; i<=failureClassSheet.getLastRowNum(); i++){
//				 failureClassList.add(new FailureClass(getFCFailureClass(i),getFCDescription(i)));
//			}


//			for(int i=1; i<=ueTableSheet.getLastRowNum(); i++){
//				//public UserEquipment(int ueTypeTacID, String marketingName, String manufacturerName, String phoneModel, int vendorName, String ueType, String phoneOS, String inputMode, String accessCapability) {
//				userEquipmentList.add(new UserEquipment(getUETAC(i),getUEMarketingName(i),getUEManufacturer(i),getUEModel(i),getUEVendorName(i),getUEUETYPE(i), getUEOS(i), getUEInputMode(i), getUEAccessCapability(i)    ));
//			}
	//	}
		FailureClass test = new FailureClass(1,"first");
		FailureClass test2 = new FailureClass(2,"second");
		FailureClass test3 = new FailureClass(3,"first");
	
		failureClassList.add(test);
		failureClassList.add(test2);
		failureClassList.add(test3);
		
		
		
		CallFailure c1 = new CallFailure(getBDDateTime(1), getBDEventId(1), test, getBDUEType(1), getBDMarket(1), 
		getBDOperator(1), getBDCellId(1), getBDDuration(1), getBDNEVersion(1), getBDIMSI(1),getBDHIER3_ID(1),getBDHIER32_ID(1),getBDHIER321_ID(1));
		
		//c1.setFailureClass(test);
		test.addCallFailure(c1);

		
		callFailureList.add(c1);
		PersistenceUtil.persistAll(failureClassList);
		PersistenceUtil.persistAll(callFailureList);
		

//		PersistenceUtil.persistAll(userEquipmentList);
		//IMSI imsi = new IMSI("1234");
			
		//PersistenceUtil.persist(new IMSI("1234"));
		
		System.out.println("FINISHED!!" );
		
//		for(int i = 1; i<=  testSheet.getLastRowNum(); i++ ){
//			System.out.println("Date is :" + getBDDateTime(i));
//			System.out.println("Base EventID is :" + getBDEventId(i));
//			System.out.println("Failure Class is :" + getBDFailureClass(i));
//			System.out.println("UEType is :" + getBDUEType(i));
//			System.out.println("Market is :" + getBDMarket(i));
//			System.out.println("Operator is :" + getBDOperator(i));
//			System.out.println("CellId is :" + getBDCellId(i));
//			System.out.println("Duration is :" + getBDDuration(i));
//			System.out.println("Cause Code is :" + getBDCauseCode(i));
//			System.out.println("NE Version is :" + getBDNEVersion(i));
//			System.out.println("IMSI is :" + getBDIMSI(i));
//			System.out.println("HIER3_ID is :" + getBDHIER3_ID(i));
//			System.out.println("HIER32_IDSI is :" + getBDHIER32_ID(i));
//			System.out.println("HIER321_IDSI is :" + getBDHIER321_ID(i));
			
//			System.out.println("EvenCause Description is :" + getEventCauseDescription( getCauseCode(i),getBaseEventId(i) ));
//			System.out.println("Failure Class Description is :" + getFailureClassDescription( getFailureClass(i) ));
//			System.out.println("marketing name is :" + getMarketingName( getUEType(i) ));
//			System.out.println("Manufacturer name is :" + getManufacturer( getUEType(i) ));
//			System.out.println("ACCESS CAPABILITY name is :" + getAccessCapability( getUEType(i) ));
//			System.out.println("Model name is :" + getModel( getUEType(i) ));
//			System.out.println("Vendor name is :" + getVendorName( getUEType(i) ));
//			System.out.println("UETABLE UETYPE name is :" + getUETableUEtype( getUEType(i) ));
//			System.out.println("OS name is :" + getOS( getUEType(i) ));
//			System.out.println("Input Mode name is :" + getInputMode( getUEType(i) ));
//			System.out.println("COUNTRY name is :" + getCountry( getMarket(i) ));
//			System.out.println("OPERATOR name is :" + getOperator( getMarket(i),getOperator(i) ));


		//}
	}

	//TODO For the UE table sheet maybe do them all at once so you don't have to search for the tac each time
	
	//TODO THIS DOES NOT WORK PROPERLY LOOK BECAUSE THERE ARE MULTIPLE MCC DON@T USE

	public String getMMOperator(int rowNumber) {
		Cell cell = getCell(4, rowNumber, 3);
		return cell.getStringCellValue();
	}
	public String getMMCountry(int rowNumber) {
		Cell cell = getCell(4, rowNumber, 2);
		return cell.getStringCellValue();
	}
	public double getMMMNC(int rowNumber) {
		Cell cell = getCell(4, rowNumber, 1);
		if(cell == null || cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return cell.getNumericCellValue();
	}
	public double getMMMCC(int rowNumber) {
		Cell cell = getCell(4, rowNumber, 0);
		if(cell == null || cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return cell.getNumericCellValue();
	}
	public String getUEInputMode(int rowNumber) {
		Cell cell = getCell(3, rowNumber, 8);
		return cell.getStringCellValue();
	}
	public String getUEOS(int rowNumber) {
		Cell cell = getCell(3, rowNumber, 7);
		return cell.getStringCellValue();
	}
	public String getUEUETYPE(int rowNumber) {
		Cell cell = getCell(3, rowNumber, 6);
		return cell.getStringCellValue();
	}
	public String getUEVendorName(int rowNumber) {
		Cell cell = getCell(3, rowNumber, 5);
		return cell.getStringCellValue();
	}
	public String getUEModel(int rowNumber) {
		Cell cell = getCell(3, rowNumber, 4);
		if(cell.getCellType() != cell.CELL_TYPE_STRING )return  "NOT VALID!!";
		return cell.getStringCellValue();
	}
	public String getUEAccessCapability(int rowNumber) {
		Cell cell = getCell(3, rowNumber, 3);
		return cell.getStringCellValue();
	}
	public String getUEManufacturer(int rowNumber) {
		Cell cell = getCell(3, rowNumber, 2);
		return cell.getStringCellValue();
	}
	public String getUEMarketingName(int rowNumber) {
		Cell cell = getCell(3, rowNumber, 1);
		if(cell.getCellType() != cell.CELL_TYPE_STRING )return  "NOT VALID!!";
		return cell.getStringCellValue();
	}
	public int getUETAC(int rowNumber) {
		Cell cell = getCell(3, rowNumber, 0);
		if(cell == null || cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return (int) cell.getNumericCellValue();
	}
	public String getFCDescription(int rowNumber) {
		Cell cell = getCell(2, rowNumber, 1);
		return cell.getStringCellValue();
	}
	public int getFCFailureClass(int rowNumber) {
		Cell cell = getCell(2, rowNumber, 0);
		if(cell == null || cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return (int) cell.getNumericCellValue();
	}
	public String getECDescription(int rowNumber) {
		Cell cell = getCell(1, rowNumber, 2);
		return cell.getStringCellValue();
	}
	public double getECEventId(int rowNumber) {
		Cell cell = getCell(1, rowNumber, 1);
		if(cell == null || cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return cell.getNumericCellValue();
	}
	public double getECCauseCode(int rowNumber) {
		Cell cell = getCell(1, rowNumber, 0);
		if(cell == null || cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return cell.getNumericCellValue();
	}
	public String getBDHIER321_ID(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 13);
		if(cell == null || cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  "NOT VALID!!";
		//Set the cell type to string to avoid number rounding errors
		cell.setCellType(1);
		return cell.getStringCellValue();
	}
	public String getBDHIER32_ID(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 12);
		if(cell == null || cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  "NOT VALID!!";
		cell.setCellType(1);
		return cell.getStringCellValue();
	}
	public String getBDHIER3_ID(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 11);
		if(cell == null || cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  "NOT VALID!!";
		cell.setCellType(1);
		return cell.getStringCellValue();
	}
	public String getBDIMSI(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 10);
		if(cell == null || cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  "NOT VALID!!";
		cell.setCellType(1);
		return cell.getStringCellValue();
	}
	public String getBDNEVersion(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 9);
		//TODO Validation
		return  cell.getStringCellValue();	
	}
	public int getBDCauseCode(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 8);
		if(cell == null || cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return (int) cell.getNumericCellValue();
	}
	public int getBDDuration(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 7);
		if(cell == null || cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return (int) cell.getNumericCellValue();
	}
	public int getBDCellId(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 6);
		if(cell == null || cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return (int) cell.getNumericCellValue();
	}
	public int getBDOperator(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 5);
		if(cell == null || cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return (int) cell.getNumericCellValue();
	}
	public int getBDMarket(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 4);
		if(cell == null || cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return (int) cell.getNumericCellValue();
	}
	public int getBDUEType(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 3);
		if(cell == null || cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return (int) cell.getNumericCellValue();
	}
	public int getBDFailureClass(int rowNumber) {
		Cell cell = getCell(0, rowNumber, 2);
		if(cell == null || cell.getCellType() != cell.CELL_TYPE_NUMERIC) return  -1;
		return (int) cell.getNumericCellValue();
	}
	public int getBDEventId(int rowNumber) {
		//EventId appears in two table, this is only for the one in base Data
		//It will always be on sheet zero in column one
		Cell cell = getCell(0, rowNumber, 1);
		if(cell == null || cell.getCellType() != cell.CELL_TYPE_NUMERIC )return  -1;
		return (int) cell.getNumericCellValue();
	}
	
	public java.util.Date getBDDateTime(int rowNumber)  {
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
	public int getSheetLength(int sheetNumber){
		return  wb.getSheetAt(sheetNumber).getLastRowNum();
	}

}
//public String getOperator(double market, double operator) {
//Sheet MCCMNCSheet = wb.getSheetAt(4);
////i starts at one to avoid headings
//for(int i=1; i<=MCCMNCSheet.getLastRowNum(); i++){
//	Cell mccCell = getCell(4, i, 0);
//	Cell mncCell = getCell(4, i, 1);
//	//TODO Validation necessary on previous two cells
//	if(mccCell.getNumericCellValue() == market && mncCell.getNumericCellValue() == operator ){
//		Cell operatorCell = getCell(4, i, 3);
//		return operatorCell.getStringCellValue();
//	}		
//}
//return "NOT FOUND!!";
//}
//public String getCountry(double market) {
//Sheet MCCMNCSheet = wb.getSheetAt(4);
////i starts at one to avoid headings
//for(int i=1; i<=MCCMNCSheet.getLastRowNum(); i++){
//	Cell tacCell = getCell(4, i, 0);
//	//TODO Validation necessary on previous two cells
//	if(tacCell.getNumericCellValue() == market){
//		Cell mccCell = getCell(4, i, 2);
//		return mccCell.getStringCellValue();
//	}		
//}
//return "NOT FOUND!!";
//}
//public double getMCC(double market) {
//Sheet MCCMNCSheet = wb.getSheetAt(4);
////i starts at one to avoid headings
//for(int i=1; i<=MCCMNCSheet.getLastRowNum(); i++){
//	Cell tacCell = getCell(4, i, 0);
//	//TODO Validation necessary on previous two cells
//	if(tacCell.getNumericCellValue() == market){
//		Cell mccCell = getCell(4, i, 0);
//		return mccCell.getNumericCellValue();
//	}		
//}
//return -1;
//}
//public String getInputMode(double ueType) {
//Sheet ueTableSheet = wb.getSheetAt(3);
////i starts at one to avoid headings
//for(int i=1; i<=ueTableSheet.getLastRowNum(); i++){
//	Cell tacCell = getCell(3, i, 0);
//	//TODO Validation necessary on previous two cells
//	if(tacCell.getNumericCellValue() == ueType){
//		Cell inputModeCell = getCell(3, i, 8);
//		return inputModeCell.getStringCellValue();
//	}
//	
//}
//return "NOT FOUND!";
//}
//public String getOS(double ueType) {
//Sheet ueTableSheet = wb.getSheetAt(3);
////i starts at one to avoid headings
//for(int i=1; i<=ueTableSheet.getLastRowNum(); i++){
//	Cell tacCell = getCell(3, i, 0);
//	//TODO Validation necessary on previous two cells
//	if(tacCell.getNumericCellValue() == ueType){
//		Cell osCell = getCell(3, i, 7);
//		return osCell.getStringCellValue();
//	}
//	
//}
//return "NOT FOUND!";
//}
//public String getUETableUEtype(double ueType) {
//Sheet ueTableSheet = wb.getSheetAt(3);
////i starts at one to avoid headings
//for(int i=1; i<=ueTableSheet.getLastRowNum(); i++){
//	Cell tacCell = getCell(3, i, 0);
//	//TODO Validation necessary on previous two cells
//	if(tacCell.getNumericCellValue() == ueType){
//		Cell ueTypeCell = getCell(3, i, 6);
//		return ueTypeCell.getStringCellValue();
//	}
//	
//}
//return "NOT FOUND!";
//}
//public String getVendorName(double ueType) {
//Sheet ueTableSheet = wb.getSheetAt(3);
////i starts at one to avoid headings
//for(int i=1; i<=ueTableSheet.getLastRowNum(); i++){
//	Cell tacCell = getCell(3, i, 0);
//	//TODO Validation necessary on previous two cells
//	if(tacCell.getNumericCellValue() == ueType){
//		Cell vendorCell = getCell(3, i, 5);
//		return vendorCell.getStringCellValue();
//	}
//	
//}
//return "NOT FOUND!";
//}
//public String getModel(double ueType) {
//Sheet ueTableSheet = wb.getSheetAt(3);
////i starts at one to avoid headings
//for(int i=1; i<=ueTableSheet.getLastRowNum(); i++){
//	Cell tacCell = getCell(3, i, 0);
//	//TODO Validation necessary on previous two cells
//	if(tacCell.getNumericCellValue() == ueType){
//		Cell modelCell = getCell(3, i, 4);
//		return modelCell.getStringCellValue();
//	}
//	
//}
//return "NOT FOUND!";
//}	
////ACCESS CAPABILITY MAYBE needs to split into a list. 
//public String getAccessCapability(double ueType) {
//Sheet ueTableSheet = wb.getSheetAt(3);
////i starts at one to avoid headings
//for(int i=1; i<=ueTableSheet.getLastRowNum(); i++){
//	Cell tacCell = getCell(3, i, 0);
//	//TODO Validation necessary on previous two cells
//	if(tacCell.getNumericCellValue() == ueType){
//		Cell accessCapabilityCell = getCell(3, i, 3);
//		return accessCapabilityCell.getStringCellValue();
//	}
//	
//}
//return "NOT FOUND!";
//}
//public String getManufacturer(double ueType) {
//Sheet ueTableSheet = wb.getSheetAt(3);
////i starts at one to avoid headings
//for(int i=1; i<=ueTableSheet.getLastRowNum(); i++){
//	Cell tacCell = getCell(3, i, 0);
//	//TODO Validation necessary on previous two cells
//	if(tacCell.getNumericCellValue() == ueType){
//		Cell manufacturerCell = getCell(3, i, 2);
//		return manufacturerCell.getStringCellValue();
//	}
//	
//}
//return "NOT FOUND!";
//}
//public String getMarketingName(double ueType) {
//Sheet ueTableSheet = wb.getSheetAt(3);
////i starts at one to avoid headings
//for(int i=1; i<=ueTableSheet.getLastRowNum(); i++){
//	Cell tacCell = getCell(3, i, 0);
//	//TODO Validation necessary on previous two cells
//	if(tacCell.getNumericCellValue() == ueType){
//		Cell marketingCell = getCell(3, i, 1);
//		return marketingCell.getStringCellValue();
//	}
//	
//}
//return "NOT FOUND!";
//}
//public String getFailureClassDescription(double failureClass) {
//Sheet failureClassSheet = wb.getSheetAt(2);
////i starts at one to avoid headings
//for(int i=1; i<=failureClassSheet.getLastRowNum(); i++){
//	Cell failureClassCell = getCell(2, i, 0);
//	//TODO Validation necessary on previous two cells
//	if(failureClassCell.getNumericCellValue() == failureClass){
//		Cell descriptionCell = getCell(2, i, 1);
//		return descriptionCell.getStringCellValue();
//	}
//	
//}
//return "NOT FOUND!";
//}
//public String getECDescription(double CauseCode, double EventId) {
//Sheet eventCauseSheet = wb.getSheetAt(1);
////i starts at one to avoid headings
//for(int i=1; i<=eventCauseSheet.getLastRowNum(); i++){
//	Cell causeCodeCell = getCell(1, i, 0);
//	Cell eventIdCell = getCell(1, i, 1);
//	//TODO Validation necessary on previous two cells
//	if(causeCodeCell.getNumericCellValue() == CauseCode && eventIdCell.getNumericCellValue() == EventId){
//		Cell descriptionCell = getCell(1, i, 2);
//		return descriptionCell.getStringCellValue();
//	}
//
//}
//return "NOT FOUND!";
//}
