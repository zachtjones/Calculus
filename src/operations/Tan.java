package operations;

import java.math.BigDecimal;

public class Tan extends Operation{
	private Operation angle;
	
	public Tan(Operation angle){
		this.angle = angle;
	}
	@Override
	public BigDecimal evaluate(BigDecimal x) {
		return new BigDecimal("" + Math.tan(angle.evaluate(x).doubleValue()));
	}

	@Override
	public String toString() {
		return "tan(" + angle.toString() + ")";
	}
	@Override
	public Operation derivative() {//der of angle * sec2(angle)
		return new Multiply(angle.derivative(), new Power(new Sec(angle), new Constant(new BigDecimal(2))));
	}
	@Override
	public boolean isConstant() {
		return angle.isConstant();
	}
	@Override
	public Operation simplifyConstants() {
		if(this.isConstant()){
			return new Constant(new BigDecimal("" + Math.tan(angle.evaluate(new BigDecimal(0)).doubleValue())));
		}
		return new Tan(angle.simplifyConstants());
	}
	@Override
	public boolean isZero() {
		return false;
	}
	@Override
	public Operation simplifyZeros() {
		return new Tan(angle.simplifyZeros());
	}
	@Override
	public Operation simplifyOnes() {
		return new Tan(angle.simplifyOnes());
	}
	@Override
	public String toFile() {
		return Operation.fileRep(Operation.type.TAN) + "\n" + angle.toFile();
	}
}
