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
	static LinkedList<LinkedList<String>> searchTextArr = new LinkedList<>();
	static int numberOfPages = -1;

	MainPdf pdf;
	TaxButtons taxButtons;
	ComboBox cb;
	MplastInvoiceParser mplast;
	ThermatDLInvoiceParser thermatDL;
	
	public LineReader(String string) throws IOException {
		this.path = string;
		priceCalc = new PriceCalculator();
		arr = new PriceArray();
		pdf = new MainPdf();
		taxButtons = new TaxButtons();
		cb = new ComboBox();
		mplast = new MplastInvoiceParser();
		thermatDL = new ThermatDLInvoiceParser();
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
					mplast.parse(line, searchTextArr, numberOfPages); 
				}
				//Thermat dodaci list
				if(ComboBox.getComboBox().getSelectedItem() == ComboBox.getBusinesses()[2]) {
					thermatDL.parse(line, searchTextArr, numberOfPages);
				}
				
				
				
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