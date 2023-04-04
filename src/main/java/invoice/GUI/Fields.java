package invoice.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;

public class Fields extends JTextField {
	
	TextPrompt tp;
	
	Fields(){
		
		TextPrompt tp = new TextPrompt("Násobok", this);
		tp.changeAlpha(0.5f);
		tp.changeStyle(Font.BOLD + Font.PLAIN);
		
		this.setFont(new Font("SansSerif", Font.BOLD, 13));
		this.setForeground(new Color(154, 206, 235));
		this.setBounds(230, 190, 100, 25);
		this.setHorizontalAlignment(JTextField.CENTER);
	
		
	}
	
	 @Override protected void paintComponent(Graphics g) {
		    if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
		      Graphics2D g2 = (Graphics2D) g.create();
		      g2.setPaint(getBackground());
		      g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
		          0, 0, getWidth() - 1, getHeight() - 1));
		      g2.dispose();
		    }
		    super.paintComponent(g);
		  }
		  @Override public void updateUI() {
		    super.updateUI();
		    setOpaque(false);
		    setBorder(new RoundedCornerBorder());
		  }
		  
	class RoundedCornerBorder extends AbstractBorder {
		  private final Color ALPHA_ZERO = new Color(0x0, true);
		  @Override public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		    Graphics2D g2 = (Graphics2D) g.create();
		    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    Shape border = getBorderShape(x, y, width - 1, height - 1);
		    g2.setPaint(ALPHA_ZERO);
		    Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
		    corner.subtract(new Area(border));
		    g2.fill(corner);
		    g2.setPaint(Color.DARK_GRAY);
		    g2.draw(border);
		    g2.dispose();
		  }
		  public Shape getBorderShape(int x, int y, int w, int h) {
		    int r = h; //h / 2;
		    return new RoundRectangle2D.Double(x, y, w, h, r, r);
		  }
		  @Override public Insets getBorderInsets(Component c) {
		    return new Insets(4, 8, 4, 8);
		  }
		  @Override public Insets getBorderInsets(Component c, Insets insets) {
		    insets.set(4, 8, 4, 8);
		    return insets;
		  }
	}
}
