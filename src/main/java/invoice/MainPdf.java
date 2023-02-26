package invoice;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

public class MainPdf {
	
	 static final String oldTxt = "C:\\Users\\adamc\\eclipse-workspace\\invoice\\txt\\pdf.txt";
	 static String oldPdf = "";
	 
	 PdfReader readerPDF;
	 TxtWriter txt;
	 
	 public void convertToPdf() throws IOException, SAXException, TikaException{
		
		readerPDF = new PdfReader(oldPdf);
		String parsedText = readerPDF.parseText();
    	
    	txt = new TxtWriter(parsedText, oldTxt);
    	
    	LineReader lr = new LineReader(oldTxt);
    	lr.processLine();
    	
    	PdfWriter pdf = new PdfWriter(oldPdf);
    	pdf.createPdf();
    	
    	deleteTxtFiles();
    	setOldPdf("");
	 }

	private void deleteTxtFiles() {
		
		File f1 = new File(oldTxt);
		
		f1.delete();
	}
	
	 public String getOldPdf() {
		return oldPdf;
	}

	public void setOldPdf(String oldPdf) {
		MainPdf.oldPdf = oldPdf;
	}
}
