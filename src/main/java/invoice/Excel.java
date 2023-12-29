package invoice;

import java.io.File;

import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.xssf.usermodel.XSSFRow; 
import org.apache.poi.xssf.usermodel.XSSFSheet; 
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map; 
import java.util.Set; 
import java.util.TreeMap; 

public class Excel {
	
	// This data needs to be written (Object[]) 
	private static Map<Integer, Object[]> invoiceData = new TreeMap<Integer, Object[]>(); 
	private static int keyIncrement = 0; 

	// any exceptions need to be caught 
	public void writeToExcel() throws IOException 
	{ 
		// workbook object 
		XSSFWorkbook workbook = new XSSFWorkbook(); 

		// spreadsheet object 
		XSSFSheet spreadsheet 
			= workbook.createSheet(" Student Data "); 

		// creating a row object 
		XSSFRow row; 

		Set<Integer> keyid = invoiceData.keySet(); 

		int rowid = 0; 

		// writing the data into the sheets... 

		for (Integer key : keyid) { 

			Object[] objectArr = invoiceData.get(key); 

			for (Object obj : objectArr) { 
				row = spreadsheet.createRow(rowid++); 
				Cell cell = row.createCell(0); //first cell always
				
	            cell.setCellValue((String) obj);
			} 
		} 
		String excelFile = System.getProperty("user.home") + "\\Desktop\\" + "EXCEL_INVOICE_MANAGER" + "(excel)"+".xlsx";

		FileOutputStream out = new FileOutputStream( 
			new File(excelFile)); 

		workbook.write(out); 
		out.close(); 
	} 
	
	public void setData(String title, String unit, String price, String quantity) {
		keyIncrement++;
		
		invoiceData.put( 
			keyIncrement, 
			new Object[] { title, unit, price, quantity}); 
		
	}
	
	public void clearData() {
		invoiceData.clear();
		keyIncrement = 0;
	}
}
