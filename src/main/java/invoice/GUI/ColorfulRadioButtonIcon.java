package invoice.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JRadioButton;

public class ColorfulRadioButtonIcon implements Icon {
    private final Color color;

    public ColorfulRadioButtonIcon(Color color) {
        this.color = color;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ButtonModel model = ((JRadioButton)c).getModel();
        if (model.isSelected()) {
            g2d.setColor(color);
            g2d.fillOval(x+2, y+2, getIconWidth()-4, getIconHeight()-4);
        }
        g2d.setColor(Color.BLACK);
        g2d.drawOval(x+2, y+2, getIconWidth()-4, getIconHeight()-4);
        g2d.dispose();
    }

    @Override
    public int getIconWidth() {
        return 14;
    }

    @Override
    public int getIconHeight() {
        return 14;
    }
}