package invoice.GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

public class Fields extends JTextField {
	
	TextPrompt tp;
	
	Fields(){
		
		TextPrompt tp = new TextPrompt("Násobok", this);
		tp.changeAlpha(0.5f);
		tp.changeStyle(Font.BOLD + Font.PLAIN);
		
		this.setFont(new Font("SansSerif", Font.BOLD, 13));
		this.setForeground(Color.black);
		this.setBounds(150, 190, 100, 25);
	}
	
}
