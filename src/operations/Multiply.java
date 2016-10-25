package operations;

import java.math.BigDecimal;

public class Multiply extends Operation {
	private Operation first;
	private Operation second;
	
	public Multiply(Operation first, Operation second){
		this.first = first;
		this.second = second;
	}
	@Override
	public BigDecimal evaluate(BigDecimal x) {
		return first.evaluate(x).multiply(second.evaluate(x));
	}

	@Override
	public String toString() {
		return "(" + first.toString() + " * " + second.toString() + ")";
	}
	@Override
	public Operation derivative() {
		//make it simpler with constants
		if(first.isConstant()){
			return new Multiply(first, second.derivative());
		}
		if(second.isConstant()){
			return new Multiply(second, first.derivative());
		}
		//product rule
		return new Add(new Multiply(first, second.derivative()), new Multiply(second, first.derivative()));
	}
	@Override
	public boolean isConstant() {
		return(first.isConstant() && second.isConstant());
	}
	@Override
	public Operation simplifyConstants(){
		if(this.isConstant()){
			return new Constant(first.evaluate(new BigDecimal(0)).multiply(second.evaluate(new BigDecimal(0))));
		}
		return new Multiply(first.simplifyConstants(), second.simplifyConstants());
	}
	@Override
	public boolean isZero() {
		return (first.isZero() || second.isZero());
	}
	@Override
	public Operation simplifyZeros() {
		if(this.isZero()){
			return new Constant(new BigDecimal(0));
		}
		return new Multiply(first.simplifyZeros(), second.simplifyZeros());
	}
	@Override
	public Operation simplifyOnes() {
		if(first.isConstant() && first.evaluate(new BigDecimal(0)).intValue() == 1){
			return second.simplifyOnes();
		}
		if(second.isConstant() && second.evaluate(new BigDecimal(0)).intValue() == 1){
			return first.simplifyOnes();
		}
		return new Multiply(first.simplifyOnes(), second.simplifyOnes());
		
	}
	@Override
	public String toFile() {
		return Operation.fileRep(Operation.type.MULTIPLY) + "\n" + first.toFile() + "\n" + second.toFile();
	}
}
