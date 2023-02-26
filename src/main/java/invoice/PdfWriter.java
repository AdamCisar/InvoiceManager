package invoice;

import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PdfWriter {
	
	static String fileName;
	static String newPdf;
	static String oldPdf;
	static int i = 0;
	PdfStamper pdfStamper;
	PriceArray arrPr = new PriceArray();
	int y = 629;
	
	public PdfWriter(String oldPdf) {
		PdfWriter.oldPdf = oldPdf;
		newPdf = System.getProperty("user.home") + "\\Desktop\\" +fileName+".pdf";
	}
	
	public PdfWriter() {
		
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
 
		    //Iterate the pdf through pages.
		    for(int i=1; i<=pages; i++) { 
			//Contain the pdf data.
				PdfContentByte pageContentByte = pdfStamper.getOverContent(i);
				
				pageContentByte.beginText();
				//Set text font and size.
				pageContentByte.setFontAndSize(baseFont, 8);
				
				//y - 11
				
				for(int j=0; j<arrPr.getCalculatedPrice().size(); j++) {
					pageContentByte.setTextMatrix(568,y);
					pageContentByte.showText(String.valueOf(arrPr.getCalculatedPrice().get(j)));
					y -= 11;
				}
				
				pageContentByte.endText();
		    }
 
		    pdfStamper.close();	
		    arrPr.getCalculatedPrice().clear();
 
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	private boolean fileExists() {
		
		File f = new File(newPdf);
		while (f.exists()) {
		    i++;
		    newPdf = System.getProperty("user.home") + "\\Desktop\\" +fileName+Integer.toString(i)+".pdf";
		    f = new File(newPdf);
		}
		return false;
	}
	
	public static void setFileName(String fileName) {
		PdfWriter.fileName = fileName;
	}

}
