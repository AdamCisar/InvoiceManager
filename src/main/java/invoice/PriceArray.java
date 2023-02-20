package invoice;

import java.util.LinkedList;

public class PriceArray {

	static LinkedList<Double> calculatedPrice = new LinkedList<Double>();

	
	protected LinkedList<Double> getCalculatedPrice() {
		return calculatedPrice;
	}

	protected void setCalculatedPrice(double result) {
		calculatedPrice.add(result);
	} 
	
	
}
