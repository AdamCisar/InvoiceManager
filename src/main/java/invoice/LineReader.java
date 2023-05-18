package invoice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;

import invoice.GUI.ComboBox;
import invoice.GUI.TaxButtons;
import invoice.parser.Parser;
import invoice.parser.impl.ParserFactory;

public class LineReader {
	
	
	static PriceCalculator priceCalc;
	static PriceArray arr;
	static LinkedList<LinkedList<String>> searchTextArr = new LinkedList<>();
	static int numberOfPages = -1;

	MainPdf pdf;
	TaxButtons taxButtons;
	ComboBox cb;
	ParserFactory parserFactory;
	Parser parser;
	
	public LineReader() {
		priceCalc = PriceCalculator.getInstance();
		arr = PriceArray.getInstance();
		pdf = new MainPdf();
		taxButtons = new TaxButtons();
		cb = new ComboBox();
		parserFactory = new ParserFactory();
		parser = parserFactory.createInvoiceParser();
	}


	public void processLine(String string) {
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new StringReader(string));
			String line;
			
			while ((line = reader.readLine()) != null) {
				
				if(line.contains("Strana") || line.contains("Popis")) {
					numberOfPages++;
					arr.calculatedPrice.add(new LinkedList<Double>());
					searchTextArr.add(new LinkedList<String>());
				}
				
				parser.parse(line, searchTextArr, numberOfPages); 
				
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int getNumberOfPages() {
		return numberOfPages;
	}

	public static void setNumberOfPages(int numberOfPages) {
		LineReader.numberOfPages = numberOfPages;
	}

}