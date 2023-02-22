package invoice;

import java.io.IOException;
import java.util.LinkedList;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import invoice.GUI.Frame;


public class Main {
	
//	static String newTxt = "C:\\Users\\adamc\\eclipse-workspace\\invoice\\output\\newPdf.txt";
//	static PdfReader readerPDF;
//	static TxtWriter txt;
//	static Paths path = new Paths();
//	static boolean fileExist = false; 
	
	public static void main(String[] args) throws IOException, SAXException, TikaException {
	
		new Frame();
		
//		oldPdf = "C:\\Users\\adamc\\eclipse-workspace\\invoice\\pdf\\test.pdf";
//		
//		while(!fileExist) {
//			if(path.getOldPdf() != null) {
//				readerPDF = new PdfReader(path.getOldPdf());
//				String parsedText = readerPDF.parseText();
//				
//		    	String oldTxt = "C:\\Users\\adamc\\eclipse-workspace\\invoice\\pdf\\pdf.txt";
//		    	txt = new TxtWriter(parsedText, oldTxt);
//		    	
//		    	LineReader lr = new LineReader(oldTxt, newTxt);
//		    	LinkedList<Double> result = lr.processLine();
//		    	
//		    	PdfCreator pdfCreator = new PdfCreator(newTxt);
//		    	try {
//					pdfCreator.createPDF();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		    	fileExist = true;
//			}
//		}
	}

}
