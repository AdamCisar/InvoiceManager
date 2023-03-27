package invoice;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

public class MainPdf {
	
	 File oldTxt = new File("txt");
	 static String oldPdf = "";
	 
	 PdfReader readerPDF;
	 TxtWriter txt;
	 
	 public void convertToPdf() throws IOException, SAXException, TikaException{
		
		readerPDF = new PdfReader(oldPdf);
		String parsedText = readerPDF.parseText();
    	
    	txt = new TxtWriter(parsedText, oldTxt.getAbsolutePath());
    	
    	LineReader lr = new LineReader(oldTxt.getAbsolutePath(), oldPdf);
    	lr.processLine();
    	
    	PdfWriter pdf = new PdfWriter(oldPdf);
    	pdf.createPdf();
    	
	 }

	public void deleteTxtFiles() {
		
		oldTxt.delete();
	}
	
	 public String getOldPdf() {
		return oldPdf;
	}

	 public void setOldPdf(String oldPdf) {
		MainPdf.oldPdf = oldPdf;
	}
	 
}
