package operations;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Divide extends Operation{
	private Operation first;
	private Operation second;
	
	public Divide(Operation first, Operation second){
		this.first = first;
		this.second = second;
	}
	@Override
	public BigDecimal evaluate(BigDecimal x) {
		//use 8 decimal places for calculations
		return first.evaluate(x).divide(second.evaluate(x), 8, RoundingMode.HALF_UP);
	}

	@Override
	public String toString() {
		return "(" + first.toString() + " / " + second.toString() + ")";
	}
	@Override
	public Operation derivative() {// (low*highDer-high*lowDer)/low^2		
		return new Divide(new Subtract(new Multiply(second, first.derivative()), new Multiply(first, second.derivative())), new Power(second, new Constant(new BigDecimal(2))));
	}
	@Override
	public boolean isConstant() {
		return (first.isConstant() && second.isConstant());
	}
	@Override
	public Operation simplifyConstants() {
		if(this.isConstant()){
			return new Constant(first.evaluate(new BigDecimal(0)).divide(second.evaluate(new BigDecimal(0))));
		}
		return new Divide(first.simplifyConstants(), second.simplifyConstants());
	}
	@Override
	public boolean isZero() {
		return (first.isZero() && !second.isZero());
	}
	@Override
	public Operation simplifyZeros() {
		if(this.isZero()){
			return new Constant(new BigDecimal(0));
		}
		return new Divide(first.simplifyZeros(), second.simplifyZeros());
	}
	@Override
	public Operation simplifyOnes() {
		if(second.isConstant() && second.evaluate(new BigDecimal(0)).equals(new BigDecimal(1))){
			return first.simplifyOnes();
		}
		return new Divide(first.simplifyOnes(), second.simplifyOnes());
	}
	@Override
	public String toFile() {
		return Operation.fileRep(Operation.type.DIVIDE) + "\n" + first.toFile() + "\n" + second.toFile();
	}
}
