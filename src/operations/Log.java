package operations;

import java.math.BigDecimal;

public class Log extends Operation{
	//log base 10
	private Operation inside;
	public Log(Operation inside){
		this.inside = inside;
	}
	@Override
	public BigDecimal evaluate(BigDecimal x) {
		return new BigDecimal("" + Math.log10(inside.evaluate(x).doubleValue()));
	}
	@Override
	public String toString() {
		return "log(" + inside.toString() + ")";
	}
	@Override
	public Operation derivative() {//ln(10)*the derivative of it would it be a ln
		return new Multiply(new Ln(new Constant(new BigDecimal(10))), new Ln(inside).derivative());
	}
	@Override
	public boolean isConstant() {
		return inside.isConstant();
	}
	@Override
	public Operation simplifyConstants() {
		if(this.isConstant()){
			return new Constant(new BigDecimal("" + Math.log10(inside.evaluate(new BigDecimal(0)).doubleValue())));
		}
		return new Log(inside.simplifyConstants());
	}
	@Override
	public boolean isZero() {
		return (inside.isConstant() && inside.evaluate(new BigDecimal(0)).intValue() == 1);
	}
	@Override
	public Operation simplifyZeros() {
		if(this.isZero()){
			return new Constant(new BigDecimal(0));
		}
		return new Log(inside.simplifyZeros());
	}
	@Override
	public Operation simplifyOnes() {
		return new Log(inside.simplifyOnes());
	}
	@Override
	public String toFile() {
		return Operation.fileRep(Operation.type.LOG) + "\n" + inside.toFile();
	}
}
