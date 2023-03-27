package invoice.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import invoice.MainPdf;
import invoice.PdfWriter;

public class DropPanel extends JPanel {
	
	JLabel label;
	MainPdf pdf;
	
	DropPanel(){
		
		pdf = new MainPdf();
		
		this.setBounds(50,20,290,100);
		this.setBackground(Color.gray);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		label = new JLabel("Vložte sem PDF súbor");
		label.setFont(new Font("SansSerif", Font.BOLD, 11));
		label.setForeground(Color.WHITE);
		
		this.add(label);
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
		ImageIcon im = new ImageIcon(getClass().getResource("\\imageIcon\\image.png"));
		
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
}
