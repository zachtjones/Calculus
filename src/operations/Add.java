package operations;

import java.math.BigDecimal;

public class Add extends Operation {
	private Operation first;
	private Operation second;
	
	public Add(Operation first, Operation second){
		this.first = first;
		this.second = second;
	}
	@Override
	public BigDecimal evaluate(BigDecimal x) {
		return first.evaluate(x).add(second.evaluate(x));
	}

	@Override
	public String toString() {
		return "(" + first.toString() + " + " + second.toString() + ")";
	}
	@Override
	public Operation derivative() {
		return new Add(first.derivative(), second.derivative());
	}
	@Override
	public boolean isConstant() {
		return(first.isConstant() && second.isConstant());
	}
	@Override
	public Operation simplifyConstants() {
		if(this.isConstant()){
			return new Constant(first.evaluate(new BigDecimal(0)).add(second.evaluate(new BigDecimal(0))));
		}
		return new Add(first.simplifyConstants(), second.simplifyConstants());
	}
	@Override
	public boolean isZero() {
		return(first.isZero() && second.isZero());
	}
	@Override
	public Operation simplifyZeros() {
		if(this.isZero()){
			return new Constant(new BigDecimal(0));
		}
		if(first.isZero()){
			return second.simplifyZeros();
		}
		if(second.isZero()){
			return first.simplifyZeros();
		}
		return new Add(first.simplifyZeros(), second.simplifyZeros());
	}
	@Override
	public Operation simplifyOnes() {
		return new Add(first.simplifyOnes(), second.simplifyOnes());
	}
	@Override
	public String toFile() {
		return Operation.fileRep(Operation.type.ADD) + "\n" + first.toFile() + "\n" + second.toFile();
	}
	

}
