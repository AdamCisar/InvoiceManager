package invoice.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import invoice.MainPdf;
import invoice.PriceCalculator;

public class Frame extends JFrame implements ActionListener {

	DropPanel dropPanel; 
	Button button;
	Fields fields;
	PriceCalculator calc;
	MainPdf pdf;
	PopupFactory pf;
	Popup p;
	
	public Frame(){
		
		pdf = new MainPdf();
		calc = new PriceCalculator();
		dropPanel = new DropPanel();
		fields = new Fields();
		button = new Button();
		pf = new PopupFactory();
		
		button.addActionListener(this);
		
		this.setTitle("Invoice Manager");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.black);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.darkGray);
		this.setVisible(true);
		this.add(fields);
		this.add(dropPanel);
		this.add(button);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(button)) {
			
			calc.setInsertedNum(fields.getText());
			
			if(validateInput()) {
				try {
					pdf.convertToPdf();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (TikaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "PDF súbor bol úspešne vytvorený!","", JOptionPane.INFORMATION_MESSAGE);
			}
			dropPanel.changeToDefault();
			pdf.setOldPdf("");
			fields.setText("");
		}
	}

	private boolean validateInput() {
		
		if(String.valueOf(fields.getText()).isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nevložili ste číslo!", "Chyba", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		else if(pdf.getOldPdf() == "") {
			JOptionPane.showMessageDialog(null, "Nevložili ste súbor pdf!", "Chyba", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		else if(!String.valueOf(fields.getText()).matches("[0-9,.]+")){
			JOptionPane.showMessageDialog(null, "Povolené sú iba kladné čísla!", "Chyba", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		return true;
	}
}
