package invoice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import invoice.GUI.Frame;

public class LineReader {
	
	private String path;
	
	static PriceCalculator priceCalc;
	static PriceArray arr;
	static double result;
	MainPdf pdf;
	
	public LineReader(String path) {
		this.path = path;
		priceCalc = new PriceCalculator();
		arr = new PriceArray();
		pdf = new MainPdf();
	}

	public LinkedList<Double> processLine() {
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(path));
			String line;
			
			while ((line = reader.readLine()) != null) {
				
				compileLine(line); 
				
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arr.getCalculatedPrice();
	}

	private static void compileLine(String line) throws IOException {
		
		Pattern pattern = Pattern.compile(" ks (\\d{1,3},\\d{3}) (\\d{1,3},\\d{3})");
	    Matcher matcher = pattern.matcher(line);
	    
	    if(matcher.find()) {
	    	String price = matcher.group(2);
	    	
	    	result = priceCalc.calculate(price);
			arr.setCalculatedPrice(result);
	    }
	}

	
}