package generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
public class ExcelData {
	private static HSSFSheet ExcelWSheet;
	private static HSSFWorkbook ExcelWBook;
	private static HSSFCell Cell;
	public static Object[][] getTableArray(String FilePath, String SheetName, int maxcolumn) throws Exception {   
		String[][] tabArray = null;
		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			ExcelWBook = new HSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int startRow = 1;
			int startCol = 1;
			int ci,cj;
			int totalRows = ExcelWSheet.getLastRowNum();
			int totalCols = maxcolumn;
			tabArray=new String[totalRows][totalCols];
			ci=0;
			for (int i=startRow;i<=totalRows;i++, ci++) {           	   
				cj=0;
				for (int j=startCol-1;j<=totalCols-1;j++, cj++){
					tabArray[ci][cj]=getCellData(i,j);
				}
			}
		}catch (FileNotFoundException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}catch (IOException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return(tabArray);
	}
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try{
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			int dataType = Cell.getCellType();
			if  (dataType == 3) {
				return "";
			}else{
				String CellData = Cell.getStringCellValue();
				return CellData;
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
			throw (e);
		}
	}
}