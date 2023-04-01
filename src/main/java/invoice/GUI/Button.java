package invoice.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import invoice.MainPdf;
import invoice.PriceCalculator;

public class Button extends JButton {
	
	Button(){
		
		this.setFocusable(false);
		this.setBounds(200, 280, 100, 25);
		this.setText("Výpočítaj");
		this.setFont(new Font("SansSerif", Font.BOLD, 11));
		this.setForeground(Color.white);
		this.setBackground(Color.gray);
	}
}
