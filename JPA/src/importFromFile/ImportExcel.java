package importFromFile;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ImportExcel {
	
	public static void excel() throws Exception{
		//TODO THE EXCEPTION HANDLING OF THIS METHOD NEEDS TO BE REWRITTEN!!
		InputStream inp = new FileInputStream("test.xls");
		//InputStream inp = new FileInputStream("workbook.xlsx");
		Workbook wb = WorkbookFactory.create(inp);
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(2);
		Cell cell = row.getCell(3);
		
//		if (cell == null)
//		    cell = row.createCell(3);
		//cell.setCellType(Cell.CELL_TYPE_STRING);
		//cell.setCellValue("a test");
		System.out.print("FINISHED!" + cell.getNumericCellValue());
		//dateTime(inp);
		
	}
	
	public static void dateTime(InputStream inp) throws Exception{
		
		Workbook wb = WorkbookFactory.create(inp);
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(2);
		Cell cell = row.getCell(3);
		
//		if (cell == null)
//		    cell = row.createCell(3);
		//cell.setCellType(Cell.CELL_TYPE_STRING);
		//cell.setCellValue("a test");
		System.out.print("FINISHED!" + cell.getNumericCellValue());
		
	}

}
