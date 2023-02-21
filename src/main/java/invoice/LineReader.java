package invoice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineReader {

	String newPath = "C:\\Users\\adamc\\eclipse-workspace\\invoice\\output\\newPdf.txt";
	private String path;
	BufferedReader reader;
	PriceCalculator priceCalc = new PriceCalculator();
	PriceArray arr = new PriceArray();
	TxtOutput txtOut = new TxtOutput(newPath);
	double result;
	boolean start = true;
	
	
	
	public LineReader(String path) {
		this.path = path;
	}


	public LinkedList<Double> processLine() {
		
			try {
				reader = new BufferedReader(new FileReader(path));
				String line;
				
				while ((line = reader.readLine()) != null) {
					
					if(line.contains("Názov") && line.contains("Počet")){
						txtOut.write(line += " Naša cena");
						txtOut.write("");
						start = false;
					}
					
					if(start) {
					   txtOut.write(line);
					}
					
					if(line.isBlank()) {
						continue;
					}
						
					
					if(line.contains("Company")) {
						start = true;
						txtOut.write(line);
					}
					compileLine(line); 
					
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return(arr.getCalculatedPrice());
		}
	
	private void compileLine(String line) throws IOException {
		
		Pattern pattern = Pattern.compile(" ks (\\d{1,3},\\d{3}) (\\d{1,3},\\d{3})");
	    Matcher matcher = pattern.matcher(line);
	    
	    if(matcher.find()) {
//	    	String piece = matcher.group(1);
	    	String price = matcher.group(2);
	    	
	    	result = priceCalc.calculate(price);
	    	
	    	txtOut.write(line, result);
	    	
			arr.setCalculatedPrice(result);
	    }
	}
}
