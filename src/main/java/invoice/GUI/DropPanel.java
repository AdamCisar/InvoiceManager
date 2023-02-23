package invoice.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import invoice.MainPdf;

public class DropPanel extends JPanel {
	
	JLabel label;
	MainPdf pdf;
	
	DropPanel(){
		
		pdf = new MainPdf();
		
		this.setBounds(50,20,290,100);
		this.setBackground(Color.gray);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		label = new JLabel("Vložte sem PDF súbor");
		label.setFont(new Font("SansSerif", Font.BOLD, 10));
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
		                	
		                	try {
								pdf.processToPdf(file.getAbsolutePath().replaceAll("\\\\", "\\\\\\\\"));
							} catch (SAXException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (TikaException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
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
}
