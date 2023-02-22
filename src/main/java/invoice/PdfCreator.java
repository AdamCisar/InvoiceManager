package invoice;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class PdfCreator {

	String newPath;

	public PdfCreator(String newPath) {
		this.newPath = newPath;
	}
	
	void createPDF() throws Exception{
		 Document document = new Document();
         PdfWriter.getInstance(document, new FileOutputStream("C:\\\\Users\\\\adamc\\\\Desktop\\\\newPdf.pdf"));
		 BufferedReader br = new BufferedReader(new FileReader(newPath));
         String line;
         document.open();
         while ((line = br.readLine()) != null) {

             document.add(new Paragraph(line));
         }
         br.close();
         document.close();
	}
}
