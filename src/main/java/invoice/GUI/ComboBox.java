package invoice.GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class ComboBox {

	final static String[] businesses = {"-Výber-", "M-Plast", "Thermat - DL, TZB - DL/Faktúra"};
	static JComboBox<String> comboBox = new JComboBox<String>(businesses); 
	
	public ComboBox(){
		
		comboBox.setFont(new Font("SansSerif", Font.BOLD, 12));
		comboBox.setBounds(30,190,180,25);
		comboBox.setForeground(new Color(154, 206, 235));
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