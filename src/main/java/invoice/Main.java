package invoice;

import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import com.formdev.flatlaf.FlatDarkLaf;

import invoice.GUI.Frame;


public class Main {
	
	
	public static void main(String[] args) throws IOException, SAXException, TikaException {
		FlatDarkLaf.setup();
		new Frame();
		
	}
}
