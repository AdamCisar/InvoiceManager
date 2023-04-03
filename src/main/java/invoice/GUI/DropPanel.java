package invoice.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.border.Border;

import invoice.MainPdf;
import invoice.PdfWriter;

public class DropPanel extends JPanel {
	
	JLabel label;
	MainPdf pdf;
	static JPanel lPanel;
	
	DropPanel(){
		
		pdf = new MainPdf();
		
		
		this.setBackground(Color.gray);
		this.setBorder(new RoundedBorder(50));
		this.setBounds(50,20,290,100);
		this.setOpaque(false);
		
		lPanel = new JPanel(new GridBagLayout());
		lPanel.setBounds(50,20,290,100);
		lPanel.setOpaque(false);
		lPanel.setBackground(new Color(0, 0, 0, 0));
	
		label = new JLabel("Vložte sem PDF súbor");
		label.setFont(new Font("SansSerif", Font.BOLD, 11));
		label.setForeground(Color.WHITE);
		lPanel.add(label, new GridBagConstraints());
		
		this.setTransferHandler(new TransferHandler() {
		    public boolean canImport(TransferSupport support) {
		        return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
		    }
	
		    public boolean importData(TransferSupport support) {
		        if (!canImport(support)) {
		            return false;
		        }
		        Transferable transferable = support.getTransferable();
		        try {
		            List<File> files = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
		            for (File file : files) {
		                if (file.getName().toLowerCase().endsWith(".pdf")) {
		                	
		                	PdfWriter.setFileName(file.getName().toLowerCase().replace(".pdf", ""));
		                	changeToImage(file.getName());
		                	
		                	pdf.setOldPdf(file.getAbsolutePath().replaceAll("\\\\", "\\\\\\\\"));
		                	
		                }
		            }
		        } catch (UnsupportedFlavorException | IOException e) {
		            e.printStackTrace();
		            return false;
		        }
		        return true;
		    }
		});
	}
	
	private void changeToImage(String fileName) throws IOException{
		
		ImageIcon im = new ImageIcon(getClass().getResource("/images/image.png"));
		ImageIcon scaledIm = new ImageIcon(im.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		
		label.setIcon(scaledIm);
		label.setText(fileName);
		revalidate();
	}
	
	protected void changeToDefault() {
		label.setIcon(null);
		label.setText("Vložte sem PDF súbor");
		label.setFont(new Font("SansSerif", Font.BOLD, 11));
		label.setForeground(Color.WHITE);
		revalidate();
	}
	
	private static class RoundedBorder implements Border {
        
        private int radius;
        
        RoundedBorder(int radius) {
            this.radius = radius;
        }
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x,y,width-1,height-1,radius,radius);
        }
    }
	
	public static JPanel getlPanel() {
		return lPanel;
	}
}
