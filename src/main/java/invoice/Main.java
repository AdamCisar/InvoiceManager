package invoice;

import java.io.IOException;
import java.util.LinkedList;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;


public class Main {
	
	static PdfReader readerPDF;
	static String pdfPath;
	static TxtWriter txt;
	
	public static void main(String[] args) throws IOException, SAXException, TikaException {
	
		pdfPath = "C:\\Users\\adamc\\eclipse-workspace\\invoice\\pdf\\XXX.pdf";
		readerPDF = new PdfReader(pdfPath);
		String parsedText = readerPDF.parseText();
		
    	String txtPath = "C:\\Users\\adamc\\eclipse-workspace\\invoice\\output\\pdf.txt";
    	txt = new TxtWriter(parsedText, txtPath);
    	
    	LineReader lr = new LineReader(txtPath);
    	LinkedList<Double> result = lr.processLine();
    	
    	System.out.println(result);
	}

}
