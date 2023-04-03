package invoice.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicRadioButtonUI;


public class TaxButtons {
	
	static JRadioButton withTaxButton = new JRadioButton("s DPH"); 
	static JRadioButton withoutTaxButton = new JRadioButton("bez DPH");
	static ButtonGroup bg = new ButtonGroup();

	public TaxButtons(){   
		
	withTaxButton.setBounds(40,260,80,15);  
	withTaxButton.setBackground(Color.darkGray);
	withTaxButton.setForeground(Color.white);
	withTaxButton.setFont(new Font("SansSerif", Font.BOLD, 11));
	withTaxButton.setFocusable(false);
	withTaxButton.setIcon(new ColorfulRadioButtonIcon(new Color(154, 206, 235)));
	
	withoutTaxButton.setBounds(40,290,80,15);
	withoutTaxButton.setBackground(Color.darkGray);
	withoutTaxButton.setForeground(Color.white);
	withoutTaxButton.setFont(new Font("SansSerif", Font.BOLD, 11));
	withoutTaxButton.setFocusable(false);
	withoutTaxButton.setIcon(new ColorfulRadioButtonIcon(new Color(154, 206, 235)));
	
	bg.add(withTaxButton);
	bg.add(withoutTaxButton);
	
	}
	
	public static JRadioButton getWithTaxButton() {
		return withTaxButton;
	}

	public static JRadioButton getWithoutTaxButton() {
		return withoutTaxButton;
	}
	
}