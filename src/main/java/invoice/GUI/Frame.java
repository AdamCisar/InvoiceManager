package invoice.GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Popup;

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
	Popup p;
	TaxButtons taxButton;
	ComboBox cb;
	JOptionPane pane;
	JDialog d;
	
	public Frame(){
		
		pdf = new MainPdf();
		calc = PriceCalculator.getInstance();
		dropPanel = new DropPanel();
		fields = new Fields();
		button = new Button();
		taxButton = new TaxButtons();
		cb = new ComboBox();
		button.addActionListener(this);
		pane = new JOptionPane();
		d = pane.createDialog((JFrame)null, "Chyba");
		
		Image icon = new ImageIcon(getClass().getResource("/images/icon.png")).getImage();
        this.setIconImage(icon);
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
		this.add(DropPanel.getlPanel());
		this.add(dropPanel);
		this.add(button);
		this.add(TaxButtons.getWithTaxButton());
		this.add(TaxButtons.getWithoutTaxButton());
		this.add(ComboBox.getComboBox());
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
				
				showJOptionPane("PDF súbor bol úspešne vytvorený!", "");
			}
			
			ComboBox.getComboBox().setSelectedIndex(0);
			TaxButtons.bg.clearSelection();
			dropPanel.changeToDefault();
			pdf.setOldPdf("");
			fields.setText("");
		}
	}

	private boolean validateInput() {
		
		if(String.valueOf(fields.getText()).isEmpty()) {
			showJOptionPane("Nevložili ste číslo!");
			return false;
		}
		
		else if(pdf.getOldPdf() == "") {
			showJOptionPane("Nevložili ste súbor pdf!");
			return false;
		}
		
		else if(!String.valueOf(fields.getText()).matches("[0-9,.]+")){
			showJOptionPane("Povolené sú iba kladné čísla!");
			return false;
		}
		
		else if(!TaxButtons.getWithTaxButton().isSelected() && !TaxButtons.getWithoutTaxButton().isSelected()) {
			showJOptionPane("Vyberte jednú z možností \"s DPH/bez DPH\"!");
			return false;
		}
		
		else if(ComboBox.getComboBox().getSelectedItem() == "-Výber-") {
			showJOptionPane("Nevybrali ste žiadnu z možností faktúr!");
			return false;
		}
		
		return true;
	}
	
	private void showJOptionPane(String message) {
		
		int x = this.getX()+((this.getSize().width-d.getSize().width)/2);
		int y = this.getY()+((this.getSize().height-d.getSize().height)/2);
		Point p = new Point(x, y);
		d.setLocation(p);
		pane.setMessage(message);
		d.setVisible(true);
	}
	
	private void showJOptionPane(String message, String title) {
			
			int x = this.getX()+((this.getSize().width-d.getSize().width)/2);
			int y = this.getY()+((this.getSize().height-d.getSize().height)/2);
			Point p = new Point(x, y);
			d.setTitle(title);
			d.setLocation(p);
			pane.setMessage(message);
			d.setVisible(true);
		}
}