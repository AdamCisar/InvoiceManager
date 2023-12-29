package invoice.parser.impl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import invoice.Excel;
import invoice.PriceArray;
import invoice.PriceCalculator;
import invoice.GUI.TaxButtons;
import invoice.parser.Parser;

public class ThermatDLInvoiceParser implements Parser {
	
	static double result;
	static PriceCalculator priceCalc;
	static PriceArray arr;
	static Excel excel;
	
	ThermatDLInvoiceParser(){
		priceCalc = PriceCalculator.getInstance();
		arr = PriceArray.getInstance();
		excel = new Excel();
	}
	
	@Override
	public void parse(String line, LinkedList<LinkedList<String>> searchTextArr, int numberOfPages) throws IOException {
		
		Pattern pattern = Pattern.compile("(\\d{0,4}) (.*) (\\d{0,4},\\d{0,4}) (m|ks|bal) (\\d{0,4},\\d{0,4}) (\\d{0,4},\\d{0,4}) (\\d{0,4},\\d{0,4}) (\\d{0,4},\\d{0,4}) (\\d{0,4},\\d{0,2})(.*)");
	    Matcher matcher = pattern.matcher(line);
	    
	    if(matcher.find()) {
			String unit = matcher.group(4);
			String title = matcher.group(10);
			
	    	String price = matcher.group(5);
	    	String piece = matcher.group(3);
	    	
				if(TaxButtons.getWithoutTaxButton().isSelected()) {
					result = priceCalc.calculate(price);
				} else {
					String sum = matcher.group(9);
					result = priceCalc.calculate(sum,piece);

				}
				searchTextArr.get(numberOfPages).add(price);
				arr.calculatedPrice.get(numberOfPages).add(result);
				
				excel.setData(
						title, unit, String.valueOf(result), piece
						);
	    }
	}
}
