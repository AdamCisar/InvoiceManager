package invoice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import invoice.GUI.TaxButtons;

public class LineReader {
	
	private String path;
	private String oldPdf;
	
	static PriceCalculator priceCalc;
	static PriceArray arr;
	static double result;
	static LinkedList<LinkedList<String>> searchTextArr = new LinkedList<>();
	static int numberOfPages = -1;

	MainPdf pdf;
	TaxButtons taxButtons;
	
	public LineReader(String path, String oldPdf) throws IOException {
		this.path = path;
		this.oldPdf = oldPdf;
		priceCalc = new PriceCalculator();
		arr = new PriceArray();
		pdf = new MainPdf();
		taxButtons= new TaxButtons();
	}

	public LineReader() {
	}

	public void processLine() {
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(path));
			String line;
			
			while ((line = reader.readLine()) != null) {
				
				if(line.contains("Strana")) {
					numberOfPages++;
					PriceArray.calculatedPrice.add(new LinkedList<Double>());
					searchTextArr.add(new LinkedList<String>());
				}
				compileLine(line); 
				
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void compileLine(String line) throws IOException {
		
		Pattern pattern = Pattern.compile("(m|ks|bal) (\\d{0,4},\\d{0,4}) (\\d{0,4},\\d{0,4}) (\\d{0,4}) (\\d{0,4},\\d{0,4}) (\\d{0,4},\\d{0,4})");
	    Matcher matcher = pattern.matcher(line);
	    
	    if(matcher.find()) {
	    	System.out.println(TaxButtons.isSelectedWithoutTaxButton());
				if(TaxButtons.isSelectedWithoutTaxButton()) {
					String price = matcher.group(3);
					
					searchTextArr.get(numberOfPages).add(price);
					result = priceCalc.calculate(price);
					PriceArray.calculatedPrice.get(numberOfPages).add(result);
				} else {
					String price = matcher.group(3);
					String sum = matcher.group(6);
					String piece = matcher.group(2);
					
					searchTextArr.get(numberOfPages).add(price);
					
					result = priceCalc.calculate(sum,piece);
					PriceArray.calculatedPrice.get(numberOfPages).add(result);
			}
	    }
	}
	
	public static int getNumberOfPages() {
		return numberOfPages;
	}

	public static void setNumberOfPages(int numberOfPages) {
		LineReader.numberOfPages = numberOfPages;
	}

}