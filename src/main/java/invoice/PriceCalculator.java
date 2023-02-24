package invoice;

public class PriceCalculator {
	
	static String insertedNum;

	double result;
	
	public double calculate(String price)  {
		
		result = parseStringToDouble(price) * parseStringToDouble(insertedNum);
		
		return Math.round(result*100.0)/100.0;
	}

	private Double parseStringToDouble(String num) {
		return Double.valueOf(num.replaceAll(",", "."));
	}
	
	public void setInsertedNum(String insertedNum) {
		PriceCalculator.insertedNum = insertedNum;
	}
	
	public String getInsertedNum() {
		return insertedNum;
	}
}
