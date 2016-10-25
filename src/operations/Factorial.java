package operations;

import java.math.BigDecimal;

public class Factorial extends Operation{
	private Operation first;
	
	public Factorial(Operation first){
		this.first = first;
	}
	@Override
	public BigDecimal evaluate(BigDecimal x) {
		return fact(first.evaluate(x));
	}

	@Override
	public String toString() {
		return "(" + first.toString() + ")!";
	}
	/**
	 * Returns the factorial of a number
	 * @param x The number to take the factorial of
	 * @return x!
	 */
	public static BigDecimal fact(BigDecimal x){
		if(!(new BigDecimal(x.intValue()).equals(x)) || x.signum() == -1){
			throw new IllegalArgumentException("You can not take the factorial of a non-integer number or a negative one!");
		}
		if(x.intValue() > 500){
			return Operation.largestValue;
		}
		BigDecimal retVal = new BigDecimal(1);
		for(int i = 1; i <= x.intValue(); i++){
			retVal = retVal.multiply(new BigDecimal(i));
		}
		return retVal;
	}
	@Override
	public Operation derivative() {
		throw new IllegalArgumentException("You can not take the derivative of a factorial");
	}
	@Override
	public boolean isConstant() {
		return first.isConstant();
	}
	@Override
	public Operation simplifyConstants() {
		if(this.isConstant()){
			return new Constant(fact(first.evaluate(new BigDecimal(0))));
		}
		return new Factorial(first.simplifyConstants());
	}
	@Override
	public boolean isZero() { //anything factorial is not 0
		return false;
	}
	@Override
	public Operation simplifyZeros() {
		return new Factorial(first);
	}
	@Override
	public Operation simplifyOnes() {
		return new Factorial(first);
	}
	@Override
	public String toFile() {
		return Operation.fileRep(Operation.type.FACTORIAL) + "\n" + first.toFile();
	}
	
}
