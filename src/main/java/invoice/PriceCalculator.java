package invoice;

public class PriceCalculator {
	
	private static PriceCalculator instance;
	String insertedNum;
	double result;
	
	private PriceCalculator() {}

    public static PriceCalculator getInstance() {
        if (instance == null) {
            instance = new PriceCalculator();
        }
        return instance;
    }
    
	public double calculate(String price)  {
		
		result = parseStringToDouble(price) * parseStringToDouble(insertedNum);
		
		return Math.round(result*100.0)/100.0;
	}
	
	public double calculate(String sum, String piece)  {
		
		result = (parseStringToDouble(sum)/parseStringToDouble(piece)) * parseStringToDouble(insertedNum);
		
		return Math.round(result*100.0)/100.0;
	}

	private Double parseStringToDouble(String num) {
		return Double.valueOf(num.replaceAll(",", "."));
	}
	
	public void setInsertedNum(String insertedNum) {
		this.insertedNum = insertedNum;
	}
	
	public String getInsertedNum() {
		return insertedNum;
	}
}
