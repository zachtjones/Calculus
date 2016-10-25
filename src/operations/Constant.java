package operations;

import java.math.BigDecimal;

public class Constant extends Operation{
	public BigDecimal value;
	public Constant(BigDecimal value){
		this.value = value;
	}
	@Override
	public BigDecimal evaluate(BigDecimal x) {
		return value;
	}
	@Override
	public String toString() {
		return "" + value;
	}
	@Override
	public Operation derivative() {
		return new Constant(new BigDecimal(0)); //derivative of constant is 0
	}
	@Override
	public boolean isConstant() {
		return true;
	}
	@Override
	public Operation simplifyConstants() { //already simplified
		return new Constant(value);
	}
	@Override
	public boolean isZero() {
		return (value.equals(new BigDecimal(0)));
	}
	@Override
	public Operation simplifyZeros() { //already simplified
		return new Constant(value);
	}
	@Override
	public Operation simplifyOnes() {
		return new Constant(value);
	}
	@Override
	public String toFile() {
		return Operation.fileRep(Operation.type.CONSTANT) + "\n" + value.toString();
	}
	
}
