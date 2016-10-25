package operations;

import java.math.BigDecimal;

public class Subtract extends Operation {
	private Operation first;
	private Operation second;
	
	public Subtract(Operation first, Operation second){
		this.first = first;
		this.second = second;
	}
	@Override
	public BigDecimal evaluate(BigDecimal x) {
		return first.evaluate(x).subtract(second.evaluate(x));
	}

	@Override
	public String toString() {
		return "(" + first.toString() + " - " + second.toString() + ")";
	}
	@Override
	public Operation derivative() {
		return new Subtract(first.derivative(), second.derivative());
	}
	@Override
	public boolean isConstant() {
		return (first.isConstant() && second.isConstant());
	}
	@Override
	public Operation simplifyConstants() {
		if(this.isConstant()){
			return new Constant(first.evaluate(new BigDecimal(0)).subtract(second.evaluate(new BigDecimal(0))));
		}
		return new Subtract(first.simplifyConstants(), second.simplifyConstants());
	}
	@Override
	public boolean isZero() {
		//could later implement an if the first.equals(second) also
		return (first.isZero() && second.isZero());
	}
	@Override
	public Operation simplifyZeros() {
		if(first.isZero() && second.isZero()){
			return new Constant(new BigDecimal(0));
		}
		if(first.isZero()){
			return new Negative(second.simplifyZeros());
		}
		if(second.isZero()){
			return first;
		}
		return new Subtract(first.simplifyZeros(), second.simplifyZeros());
	}
	@Override
	public Operation simplifyOnes() {
		return new Subtract(first.simplifyOnes(), second.simplifyOnes());
	}
	@Override
	public String toFile() {
		return Operation.fileRep(Operation.type.SUBTRACT) + "\n" + first.toFile() + "\n" + second.toFile();
	}
}
