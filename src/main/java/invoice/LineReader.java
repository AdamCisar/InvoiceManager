package invoice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import invoice.GUI.ComboBox;
import invoice.GUI.TaxButtons;

public class LineReader {
	
	private String path;
	
	static PriceCalculator priceCalc;
	static PriceArray arr;
	static double result;
	static LinkedList<LinkedList<String>> searchTextArr = new LinkedList<>();
	static int numberOfPages = -1;

	MainPdf pdf;
	TaxButtons taxButtons;
	ComboBox cb;
	
	public LineReader(String string) throws IOException {
		this.path = string;
		priceCalc = new PriceCalculator();
		arr = new PriceArray();
		pdf = new MainPdf();
		taxButtons = new TaxButtons();
		cb = new ComboBox();
	}

	public LineReader() {
	}

	public void processLine() {
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new StringReader(path));
			String line;
			
			while ((line = reader.readLine()) != null) {
				
				if(line.contains("Strana") || line.contains("Popis")) {
					numberOfPages++;
					PriceArray.calculatedPrice.add(new LinkedList<Double>());
					searchTextArr.add(new LinkedList<String>());
				}
				//mplast
				if(ComboBox.getComboBox().getSelectedItem() == ComboBox.getBusinesses()[1]) {
					compileMplast(line); 
				}
				//Thermat dodaci list
				if(ComboBox.getComboBox().getSelectedItem() == ComboBox.getBusinesses()[2]) {
					compileThermatDL(line);
				}
				
				
				
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void compileMplast(String line) throws IOException {
		
		Pattern pattern = Pattern.compile("(m|ks|bal) (\\d{0,4},\\d{0,4}) (\\d{0,4},\\d{0,4}) (\\d{0,4}) (\\d{0,4},\\d{0,4}) (\\d{0,4},\\d{0,4})");
	    Matcher matcher = pattern.matcher(line);
	    
	    if(matcher.find()) {
	    	
				if(TaxButtons.getWithoutTaxButton().isSelected()) {
					String price = matcher.group(3);
					
					searchTextArr.get(numberOfPages).add(price);
					result = priceCalc.calculate(price);
					PriceArray.calculatedPrice.get(numberOfPages).add(result);
				} 
				
				else {
					String price = matcher.group(3);
					String sum = matcher.group(6);
					String piece = matcher.group(2);
					
					searchTextArr.get(numberOfPages).add(price);
					
					result = priceCalc.calculate(sum,piece);
					PriceArray.calculatedPrice.get(numberOfPages).add(result);
			}
	    }
	}
	
	private static void compileThermatDL(String line) throws IOException {
			
			Pattern pattern = Pattern.compile("(\\d{0,4}) (\\d{0,4}) (\\d{0,4},\\d{0,4}) (m|ks|bal) (\\d{0,4},\\d{0,4}) (\\d{0,4},\\d{0,4}) (\\d{0,4},\\d{0,4}) (\\d{0,4},\\d{0,4}) (\\d{0,4},\\d{0,2})");
		    Matcher matcher = pattern.matcher(line);
		    
		    if(matcher.find()) {
		    	
					if(TaxButtons.getWithoutTaxButton().isSelected()) {
						String price = matcher.group(5);
						
						searchTextArr.get(numberOfPages).add(price);
						result = priceCalc.calculate(price);
						PriceArray.calculatedPrice.get(numberOfPages).add(result);
					} 
					
					else {
						String price = matcher.group(5);
						String sum = matcher.group(9);
						String piece = matcher.group(3);
						
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