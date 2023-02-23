package invoice;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

public class MainPdf {
	 
	 String newTxt = "C:\\Users\\adamc\\eclipse-workspace\\invoice\\output\\newPdf.txt";
	 String oldTxt = "C:\\Users\\adamc\\eclipse-workspace\\invoice\\pdf\\pdf.txt";
	 
	 PdfReader readerPDF;
	 TxtWriter txt;
	 
	 public void processToPdf(String oldPdf) throws IOException, SAXException, TikaException{
		
		readerPDF = new PdfReader(oldPdf);
		String parsedText = readerPDF.parseText();
    	
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
    	deleteTxtFiles(oldTxt, newTxt);
	 }

	private void deleteTxtFiles(String oldTxt, String newTxt) {
		
		File f1 = new File(oldTxt);
		File f2 = new File(newTxt);
		
		f1.delete();
		f2.delete();
	}
}
