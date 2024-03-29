package invoice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import invoice.GUI.ComboBox;

public class PdfWriter {
	
	static String fileName;
	static String newPdf;
	static String oldPdf;
	static int i = 0;
	static Excel excel = new Excel();
	
	PdfStamper pdfStamper;
	PriceArray arr = PriceArray.getInstance();
	LineReader lineReader = new LineReader();
	PDFTextPositionFinder finder;
	ComboBox cb = new ComboBox();
	
	static int x = 568;
	static LinkedList<LinkedList<Float>> yPositionArr = new LinkedList<>();
	
	public PdfWriter(String oldPdf) {
		PdfWriter.oldPdf = oldPdf;
		newPdf = System.getProperty("user.home") + "\\Desktop\\" +fileName+" (prepočítaný)"+".pdf";
		finder = new PDFTextPositionFinder(oldPdf);
		setX((String) ComboBox.getComboBox().getSelectedItem(), ComboBox.getBusinesses());
	}

	void createPdf() {
		try {
		    PdfReader pdfReader = new PdfReader(oldPdf);	
 
		    //Create PdfStamper instance.
		    if(!fileExists()) {
		    	pdfStamper = new PdfStamper(pdfReader,new FileOutputStream(newPdf));
		    }
 
		    //Create BaseFont instance.
		    BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
 
		    //Get the number of pages in pdf.
		    int pages = pdfReader.getNumberOfPages(); 
		    
		    findYpositions(pages);
		    //Iterate the pdf through pages.
		    for(int i=1; i<=pages; i++) { 
			//Contain the pdf data.
				PdfContentByte pageContentByte = pdfStamper.getOverContent(i);
				
				pageContentByte.beginText();
				//Set text font and size.
				pageContentByte.setFontAndSize(baseFont, 8);
				
				for(int j=0; j<LineReader.searchTextArr.get(i-1).size(); j++) {
					
					pageContentByte.setTextMatrix(x, yPositionArr.get(i-1).get(j));
					pageContentByte.showText(String.valueOf(arr.calculatedPrice.get(i-1).get(j)));
				}
				
				pageContentByte.endText();
		    }
		    
		    excel.writeToExcel();
		    excel.clearData();
		    
		    pdfStamper.close();	
		    arr.calculatedPrice.clear();
		    LineReader.setNumberOfPages(-1);
		    LineReader.searchTextArr.clear();
		    yPositionArr.clear();
 
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	private void findYpositions(int pages) throws IOException {
		 
		for(int i=1; i<=pages; i++) { 
			
			for(int j=0; j<LineReader.searchTextArr.get(i-1).size(); j++) {
				finder.setSearchText(LineReader.searchTextArr.get(i-1).get(j));
				finder.findTextPosition(i);
			}
			LinkedList<Float> arrY = new LinkedList<Float>(PDFTextPositionFinder.arrY);
		    Collections.sort(arrY);
		    Collections.reverse(arrY);
		    yPositionArr.add(arrY);
		}
		
		PDFTextPositionFinder.arrY.clear();
	}
	private boolean fileExists() {
		
		File f = new File(newPdf);
		while (f.exists()) {
		    i++;
		    newPdf = System.getProperty("user.home") + "\\Desktop\\" +fileName+" (prepočítaný)"+Integer.toString(i)+".pdf";
		    f = new File(newPdf);
		}
		return false;
	}
	
	public static void setX(String selected, String[] business) {
		
		if(selected == business[1]) {
			x = 568;
		}
		
		if(selected == business[2]) {
			x = 540;
		}
		
	}
	
	public static void setFileName(String fileName) {
		PdfWriter.fileName = fileName;
	}

}
