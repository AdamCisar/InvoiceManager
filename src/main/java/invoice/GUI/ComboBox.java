package invoice.GUI;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class ComboBox extends JComboBox<String>{

	final static String[] businesses = {"M-Plast","Thermat - dodací list"};
	
	ComboBox(){
		super(businesses);
		this.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.setBounds(40,190,130,25);
		this.setUI(new BasicComboBoxUI() {
		    protected JButton createArrowButton() {
		        return new JButton() {
		            public int getWidth() {
		                return 0;
		            }
		        };
		    }
		});
	}
}