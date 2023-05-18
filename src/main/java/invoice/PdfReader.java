package invoice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class PdfReader {
	
	File f;
	FileInputStream fstream;
	Metadata data;
	ParseContext context;
	PDFParser pdfparser;
	BodyContentHandler contenthandler;
	
	 PdfReader(String path) throws IOException, SAXException, TikaException{
		f = new File(path);
		contenthandler = new BodyContentHandler();
        fstream = null;
        data = new Metadata();
        context = new ParseContext();
        pdfparser = new PDFParser();
        
	}
        
        
	protected String parseText() throws IOException, SAXException, TikaException {
		fstream = new FileInputStream(f);
		pdfparser.parse(fstream, contenthandler, data, context);
        return contenthandler.toString();
	}
	
}
