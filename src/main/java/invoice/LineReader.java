package invoice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineReader {

	private String path;
	BufferedReader reader;
	PriceCalculator priceCalc = new PriceCalculator();
	PriceArray arr = new PriceArray();
	boolean start = false;
	
	public LineReader(String path) {
		this.path = path;
	}


	public LinkedList<Double> processLine() {
		
			try {
				reader = new BufferedReader(new FileReader(path));
				String line;
				
				while ((line = reader.readLine()) != null) {
					
					
					if(line.isBlank()) {
						continue;
					}
						
					if(line.contains("Názov") && line.contains("Počet")){
						start = true;
						continue;
					}
					
					if(line.contains("Company")) {
						break;
					}
					
					if(start) {
						Pattern pattern = Pattern.compile(" ks (\\d{1,3},\\d{3}) (\\d{1,3},\\d{3})");
					    Matcher matcher = pattern.matcher(line);
					    
					    if(matcher.find()) {
//					    	String piece = matcher.group(1);
					    	String price = matcher.group(2);
					    	
					    	double result = priceCalc.calculate(price);
						
							arr.setCalculatedPrice(result);
//					       	System.out.println(result);
					       	
							}
//					    System.out.print(piece + prize);
					}
				}
				
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return(arr.getCalculatedPrice());
		}
}
