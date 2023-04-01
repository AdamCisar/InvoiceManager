package invoice;

import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

public class MainPdf {
	
	 static String oldPdf = "";
	 
	 PdfReader readerPDF;
	 
	 public void convertToPdf() throws IOException, SAXException, TikaException{
		
		readerPDF = new PdfReader(oldPdf);
		String parsedText = readerPDF.parseText();
		
    	LineReader lr = new LineReader(parsedText);
    	lr.processLine();
    	
    	PdfWriter pdf = new PdfWriter(oldPdf);
    	pdf.createPdf();
    	
	 }

	 public String getOldPdf() {
		return oldPdf;
	}

	 public void setOldPdf(String oldPdf) {
		MainPdf.oldPdf = oldPdf;
	}
	 
}
