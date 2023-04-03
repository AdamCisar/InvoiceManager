package invoice.GUI;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class ComboBox {

	final static String[] businesses = {"-Výber-", "M-Plast", "Thermat - dodací list"};
	static JComboBox<String> comboBox = new JComboBox<String>(businesses); 
	
	public ComboBox(){
		
		comboBox.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboBox.setBounds(50,190,130,25);
		comboBox.setUI(new BasicComboBoxUI() {
		    protected JButton createArrowButton() {
		        return new JButton() {
		            public int getWidth() {
		                return 0;
		            }
		        };
		    }
		});
		
		
	}
	
	
	public static JComboBox<String> getComboBox() {
		return comboBox;
	}
	
	public static String[] getBusinesses() {
		return businesses;
	}

}