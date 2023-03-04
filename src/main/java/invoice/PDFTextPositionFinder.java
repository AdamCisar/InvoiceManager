package invoice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class PDFTextPositionFinder {
	
    private static String filePath;
    private static String searchText;
    static float pageHeight;
    static LinkedHashSet<Float> arrY = new LinkedHashSet<Float>();
    
	public PDFTextPositionFinder(String filePath) {
        PDFTextPositionFinder.filePath = filePath;
    }
    
    public void findTextPosition(int numberOfPage) throws IOException {
    	
        // Load the PDF document
        PDDocument document = PDDocument.load(new File(filePath));
        
        // Create a PDFTextStripper object to extract text and position information
        PDFTextStripper stripper = new PDFTextStripper() {
            @Override
            protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
                if (text.contains(searchText)) {
                    for (TextPosition position : textPositions) {
                    	arrY.add((float) Math.abs(position.getYDirAdj()-position.getPageHeight()));
                    }
                }
            }
        };
        // pages of document
//        stripper.setStartPage(numberOfPage);
//        stripper.setEndPage(numberOfPage);
        String text = stripper.getText(document);
        
        // Close the PDF document
        document.close();
    }

	public void setSearchText(String searchText) {
		PDFTextPositionFinder.searchText = searchText;
	}
}