package invoice.GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;


public class TaxButtons {
	
	static JRadioButton withTaxButton;
	static JRadioButton withoutTaxButton;
	static boolean isSelectedWithoutTaxButton;
	
	public TaxButtons(){   
		
	withTaxButton=new JRadioButton("s DPH");    
	withoutTaxButton=new JRadioButton("bez DPH");   
	ButtonGroup bg = new ButtonGroup();
	bg.add(withTaxButton);
	bg.add(withoutTaxButton);
	withTaxButton.setSelected(true);
	
	withTaxButton.setBounds(40,230,80,15);  
	withTaxButton.setBackground(Color.darkGray);
	withTaxButton.setForeground(Color.white);
	withTaxButton.setFont(new Font("SansSerif", Font.BOLD, 11));
	withTaxButton.setFocusable(false);
	
	withoutTaxButton.setBounds(40,260,80,15);
	withoutTaxButton.setBackground(Color.darkGray);
	withoutTaxButton.setForeground(Color.white);
	withoutTaxButton.setFont(new Font("SansSerif", Font.BOLD, 11));
	withoutTaxButton.setFocusable(false);
	
	
	}

	public static boolean isSelectedWithoutTaxButton() {
		return isSelectedWithoutTaxButton;
	}


	public static void setSelectedWithTaxButton(boolean isSelectedWithoutTaxButton) {
		TaxButtons.isSelectedWithoutTaxButton = isSelectedWithoutTaxButton;
	}

	public static JRadioButton getWithTaxButton() {
		return withTaxButton;
	}


	public static void setWithTaxButton(JRadioButton withTaxButton) {
		TaxButtons.withTaxButton = withTaxButton;
	}


	public static JRadioButton getWithoutTaxButton() {
		return withoutTaxButton;
	}


	public static void setWithoutTaxButton(JRadioButton withoutTaxButton) {
		TaxButtons.withoutTaxButton = withoutTaxButton;
	}
}