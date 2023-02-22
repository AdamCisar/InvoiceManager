package invoice;

import java.io.IOException;
import java.util.LinkedList;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

public class MainPdf {
	 
	 String newTxt = "C:\\Users\\adamc\\eclipse-workspace\\invoice\\output\\newPdf.txt";
	 PdfReader readerPDF;
	 TxtWriter txt;
	 Paths path = new Paths();
	 
	 public void processToPdf(String oldPdf) throws IOException, SAXException, TikaException{
		
		readerPDF = new PdfReader(oldPdf);
		String parsedText = readerPDF.parseText();
		
    	String oldTxt = "C:\\Users\\adamc\\eclipse-workspace\\invoice\\pdf\\pdf.txt";
    	txt = new TxtWriter(parsedText, oldTxt);
    	
    	LineReader lr = new LineReader(oldTxt, newTxt);
    	LinkedList<Double> result = lr.processLine();
    	
    	PdfCreator pdfCreator = new PdfCreator(newTxt);
    	try {
			pdfCreator.createPDF();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
