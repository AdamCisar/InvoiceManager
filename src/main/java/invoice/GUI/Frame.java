package invoice.GUI;

import java.awt.Color;

import javax.swing.JFrame;

public class Frame extends JFrame {

	DropPanel dropPanel; 
	
	public Frame(){
		
		dropPanel = new DropPanel();
		
		this.setTitle("Invoice Manager");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.black);
		this.setLayout(null);
		this.add(dropPanel);
	}


}
