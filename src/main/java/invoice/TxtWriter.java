package invoice;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TxtWriter {
	
	PrintWriter pw = null;
	
	
	TxtWriter(String parsedText, String path) throws FileNotFoundException{
		
		pw = new PrintWriter(path);
		pw.print(parsedText); 
		pw.close();
	}
	
}
