package operations;

import java.math.BigDecimal;

public class Negative extends Operation{
	private Operation inside;
	public Negative(Operation inside){
		this.inside = inside;
	}
	@Override
	public BigDecimal evaluate(BigDecimal x) {
		return inside.evaluate(x).negate();
	}

	@Override
	public Operation derivative() {
		return new Negative(inside.derivative());
	}

	@Override
	public String toString() {
		return "(-" + inside.toString() + ")";
	}
	@Override
	public boolean isConstant() {
		return inside.isConstant();
	}
	@Override
	public Operation simplifyConstants() {
		if(this.isConstant()){
			return new Constant(inside.evaluate(new BigDecimal(0)).negate());
		}
		return new Negative(inside.simplifyConstants());
	}
	@Override
	public boolean isZero() {
		return inside.isZero();
	}
	@Override
	public Operation simplifyZeros() {
		if(this.isZero()){
			return new Constant(new BigDecimal(0));
		}
		return new Negative(inside.simplifyZeros());
	}
	@Override
	public Operation simplifyOnes() {
		//test for -(-(quantity))
		if(inside instanceof Negative){
			return ((Negative) inside).getInside();
		}
		
		return new Negative(inside.simplifyOnes());
	}

	public Operation getInside(){
		return inside;
	}
	@Override
	public String toFile() {
		return Operation.fileRep(Operation.type.NEGATIVE) + "\n" + inside.toFile();
	}

}
