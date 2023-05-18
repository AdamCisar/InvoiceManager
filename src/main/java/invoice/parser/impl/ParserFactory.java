package invoice.parser.impl;

import invoice.GUI.ComboBox;
import invoice.parser.Parser;

public class ParserFactory {

	public Parser createInvoiceParser() {
		
		//MPLAST
		if(ComboBox.getComboBox().getSelectedItem() == ComboBox.getBusinesses()[1]) {
			return new MplastInvoiceParser();
		}
		//THERMAT - dodaci list
		if(ComboBox.getComboBox().getSelectedItem() == ComboBox.getBusinesses()[2]) {
			return new ThermatDLInvoiceParser();
		}
		return null;
	}
}
