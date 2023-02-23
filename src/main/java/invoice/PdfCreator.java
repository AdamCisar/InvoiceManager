package invoice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class PdfCreator {

	String newTxt;
	String newPdf = "C:\\\\Users\\\\adamc\\\\Desktop\\\\newPdf.pdf";
	int i = 0;
	public PdfCreator(String newTxt) {
		this.newTxt = newTxt;
	}
	
	void createPDF() throws Exception{
		 Document document = new Document();
		 
		 if(!fileExists()) {
			 PdfWriter.getInstance(document, new FileOutputStream(newPdf));
		 }
		 BufferedReader br = new BufferedReader(new FileReader(newTxt));
         String line;
         document.open();
         while ((line = br.readLine()) != null) {

             document.add(new Paragraph(line));
         }
         br.close();
         document.close();
	}

	private boolean fileExists() {
		
		File f = new File(newPdf);
		while (f.exists()) {
		    i++;
		    newPdf = "C:\\\\Users\\\\adamc\\\\Desktop\\\\newPdf"+Integer.toString(i)+".pdf";
		    f = new File(newPdf);
		}
		return false;
	}
}
