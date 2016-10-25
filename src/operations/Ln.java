package operations;

import java.math.BigDecimal;

public class Ln extends Operation{
	//log base e
	private Operation inside;
	public Ln(Operation inside){
		this.inside = inside;
	}
	@Override
	public BigDecimal evaluate(BigDecimal x) {
		return new BigDecimal("" + Math.log(inside.evaluate(x).doubleValue()));
	}
	@Override
	public String toString() {
		return "ln(" + inside.toString() + ")";
	}
	@Override
	public Operation derivative() {
		return new Divide(inside.derivative(), inside);
	}
	@Override
	public boolean isConstant() {
		return inside.isConstant();
	}
	@Override
	public Operation simplifyConstants() {
		if(this.isConstant()){
			return new Constant(new BigDecimal("" +Math.log(inside.evaluate(new BigDecimal(0)).doubleValue())));
		}
		return new Ln(inside.simplifyConstants());
	}
	@Override
	public boolean isZero() { //ln1 is 0
		return (inside.isConstant() && inside.evaluate(new BigDecimal(0)).intValue() == 1);
	}
	@Override
	public Operation simplifyZeros() {
		if(this.isZero()){
			return new Constant(new BigDecimal(0));
		}
		return new Ln(inside.simplifyZeros());
	}
	@Override
	public Operation simplifyOnes() {
		return new Ln(inside);
	}
	@Override
	public String toFile() {
		return Operation.fileRep(Operation.type.LN) + "\n" + inside.toFile();
	}
	
}
