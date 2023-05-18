package invoice.parser.impl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import invoice.PriceArray;
import invoice.PriceCalculator;
import invoice.GUI.TaxButtons;
import invoice.parser.Parser;

public class MplastInvoiceParser implements Parser {

	static double result;
	static PriceCalculator priceCalc;
	static PriceArray arr;
	
	public MplastInvoiceParser() {
		priceCalc = PriceCalculator.getInstance();
		arr = PriceArray.getInstance();
	}
	
	@Override
	public void parse(String line, LinkedList<LinkedList<String>> searchTextArr, int numberOfPages) throws IOException {
			
			Pattern pattern = Pattern.compile("(m|ks|bal) (\\d{0,4},\\d{0,4}) (\\d{0,4},\\d{0,4}) (\\d{0,4}) (\\d{0,4},\\d{0,4}) (\\d{0,4},\\d{0,4})");
		    Matcher matcher = pattern.matcher(line);
		    
		    if(matcher.find()) {
		    	
					if(TaxButtons.getWithoutTaxButton().isSelected()) {
						String price = matcher.group(3);
						
						searchTextArr.get(numberOfPages).add(price);
						result = priceCalc.calculate(price);
						arr.calculatedPrice.get(numberOfPages).add(result);
					} 
					
					else {
						String price = matcher.group(3);
						String sum = matcher.group(6);
						String piece = matcher.group(2);
						
						searchTextArr.get(numberOfPages).add(price);
						
						result = priceCalc.calculate(sum,piece);
						arr.calculatedPrice.get(numberOfPages).add(result);
				}
		    }
		}
	}