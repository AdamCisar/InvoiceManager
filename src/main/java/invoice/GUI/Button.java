package invoice.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Button extends JButton {
	
	Button(){
		
		this.setFocusable(false);
		this.setBounds(200, 280, 100, 25);
		this.setText("Výpočítaj");
		this.setFont(new Font("SansSerif", Font.BOLD, 11));
		this.setForeground(Color.white);
		this.setBackground(Color.gray);
		this.setBorder(BorderFactory.createEtchedBorder());
		
		this.setOpaque(false);
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				setBackground(Color.black);
				setBorder(BorderFactory.createLoweredBevelBorder());
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				setBackground(Color.gray);
				setBorder(BorderFactory.createEtchedBorder());
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(Color.black);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(Color.gray);
			}
		});
	}
	
	 @Override
	    protected void paintComponent(Graphics g) {
	        Graphics2D g2 = (Graphics2D) g.create();
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2.setColor(getBackground());
	        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
	        g2.setColor(getForeground());
	        g2.setFont(getFont());
	        FontMetrics fm = g2.getFontMetrics();
	        Rectangle2D r = fm.getStringBounds(getText(), g2);
	        int x = (getWidth() - (int) r.getWidth()) / 2;
	        int y = (getHeight() - (int) r.getHeight()) / 2 + fm.getAscent();
	        g2.drawString(getText(), x, y);
	        g2.dispose();
	    }
	 
}
