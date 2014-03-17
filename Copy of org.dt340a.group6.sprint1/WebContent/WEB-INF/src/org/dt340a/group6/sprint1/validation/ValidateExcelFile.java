package org.dt340a.group6.sprint1.validation;

import org.dt340a.group6.sprint1.fileImport.FileReader;

public class ValidateExcelFile {
    
	private FileReader fileReader;
	
	public ValidateExcelFile(FileReader fileReader) {
	    this.fileReader = fileReader;
    }
	
	public boolean isXLSValid(){
		if(isBaseDataValid() && isEventCauseValid() && isFailureClassValid()
				&& isUETableValid() && isMCCMNCValid()){
			return true;
		}
		return false;
	}
	public boolean isBaseDataValid(){
		//Cell cell = fileReader.getCell(0, 0, 0);
		if( !fileReader.getCell(0, 0, 0).getStringCellValue().equals("Date / Time") ){
			return false;	
		}	
		if( !fileReader.getCell(0, 0, 1).getStringCellValue().equals("Event Id") ){
			return false;	
		}	
		if( !fileReader.getCell(0, 0, 2).getStringCellValue().equals("Failure Class") ){
			return false;	
		}	
		if( !fileReader.getCell(0, 0, 3).getStringCellValue().equals("UE Type") ){
			return false;	
		}	
		if( !fileReader.getCell(0, 0, 4).getStringCellValue().equals("Market") ){
			return false;	
		}	
		if( !fileReader.getCell(0, 0, 5).getStringCellValue().equals("Operator") ){
			return false;	
		}	
		if( !fileReader.getCell(0, 0, 6).getStringCellValue().equals("Cell Id") ){
			return false;	
		}	
		if( !fileReader.getCell(0, 0, 7).getStringCellValue().equals("Duration") ){
			return false;	
		}
		if( !fileReader.getCell(0, 0, 8).getStringCellValue().equals("Cause Code") ){
			return false;	
		}	

		if( !fileReader.getCell(0, 0, 9).getStringCellValue().equals("NE Version") ){
			return false;	
		}	

		if( !fileReader.getCell(0, 0, 10).getStringCellValue().equals("IMSI") ){
			return false;	
		}	

		if( !fileReader.getCell(0, 0, 11).getStringCellValue().equals("HIER3_ID") ){
			return false;	
		}	

		if( !fileReader.getCell(0, 0, 12).getStringCellValue().equals("HIER32_ID") ){
			return false;	
		}	

		if( !fileReader.getCell(0, 0, 13).getStringCellValue().equals("HIER321_ID") ){
			return false;	
		}	
		return true;
	}
	
	public boolean isEventCauseValid(){
		if( !fileReader.getCell(1, 0, 0).getStringCellValue().equals("Cause Code") ){
			return false;
		}
		if( !fileReader.getCell(1, 0, 1).getStringCellValue().equals("Event Id") ){
			return false;
		}
		if( !fileReader.getCell(1, 0, 2).getStringCellValue().equals("Description") ){
			return false;
		}
		
		return true;
	}
	
	public boolean isFailureClassValid() {
		if( !fileReader.getCell(2, 0, 0).getStringCellValue().equals("Failure Class") ){
			return false;
		}
		if( !fileReader.getCell(2, 0, 1).getStringCellValue().equals("Description") ){
			return false;
		}
		return true;
	}
	
	public boolean isUETableValid(){
		if( !fileReader.getCell(3, 0, 0).getStringCellValue().equals("TAC") ){
			return false;
		}
		if( !fileReader.getCell(3, 0, 1).getStringCellValue().equals("MARKETING NAME") ){
			return false;
		}
		if( !fileReader.getCell(3, 0, 2).getStringCellValue().equals("MANUFACTURER") ){
			return false;
		}
		if( !fileReader.getCell(3, 0, 3).getStringCellValue().equals("ACCESS CAPABILITY") ){
			return false;
		}
		if( !fileReader.getCell(3, 0, 4).getStringCellValue().equals("MODEL") ){
			return false;
		}
		if( !fileReader.getCell(3, 0, 5).getStringCellValue().equals("VENDOR NAME") ){
			return false;
		}
		if( !fileReader.getCell(3, 0, 6).getStringCellValue().equals("UE TYPE") ){
			return false;
		}
		if( !fileReader.getCell(3, 0, 7).getStringCellValue().equals("OS") ){
			return false;
		}
		if( !fileReader.getCell(3, 0, 8).getStringCellValue().equals("INPUT_MODE") ){
			return false;
		}
		
		return true;
	}
	
	public boolean isMCCMNCValid(){
		if( !fileReader.getCell(4, 0, 0).getStringCellValue().equals("MCC") ){
			return false;
		}
		if( !fileReader.getCell(4, 0, 1).getStringCellValue().equals("MNC") ){
			return false;
		}
		if( !fileReader.getCell(4, 0, 2).getStringCellValue().equals("COUNTRY") ){
			return false;
		}
		if( !fileReader.getCell(4, 0, 3).getStringCellValue().equals("OPERATOR") ){
			return false;
		}
		return true;
	}


}
