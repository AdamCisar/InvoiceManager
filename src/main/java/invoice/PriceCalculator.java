package invoice;

public class PriceCalculator {
	
	
	public double calculate(String price)  {
		
		double parsedDouble = Double.valueOf(price.replaceAll(",", "."));
		double result = parsedDouble * 1.12D;
		
		return Math.round(result*100.0)/100.0;
	}
}
