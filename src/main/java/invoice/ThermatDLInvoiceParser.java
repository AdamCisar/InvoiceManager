package invoice;

import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import invoice.GUI.TaxButtons;

public class ThermatDLInvoiceParser {
	
	static double result;
	static PriceCalculator priceCalc;
	static PriceArray arr;
	
	ThermatDLInvoiceParser(){
		priceCalc = new PriceCalculator();
		arr = new PriceArray();
	}
	
	protected void parse(String line, LinkedList<LinkedList<String>> searchTextArr, int numberOfPages) throws IOException {
		
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
}
