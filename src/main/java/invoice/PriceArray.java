package invoice;

import java.util.LinkedList;

public class PriceArray {
	
	private static PriceArray instance;

	public LinkedList<LinkedList<Double>> calculatedPrice = new LinkedList<>();
	
	private PriceArray() {}

    public static PriceArray getInstance() {
        if (instance == null) {
            instance = new PriceArray();
        }
        return instance;
    }
	
}
