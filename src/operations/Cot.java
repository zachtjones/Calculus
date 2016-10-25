package operations;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Cot extends Operation{
	private Operation angle;
	
	public Cot(Operation angle){
		this.angle = angle;
	}
	@Override
	public BigDecimal evaluate(BigDecimal x) {
		return new BigDecimal(1).divide(new BigDecimal("" + Math.tan(angle.evaluate(x).doubleValue())), 8, RoundingMode.HALF_UP);
	}

	@Override
	public String toString() {
		return "cot(" + angle.toString() + ")";
	}
	@Override
	public Operation derivative() {//der of angle * csc(angle)^2
		return new Multiply(angle.derivative(), new Negative(new Power(new Csc(angle), new Constant(new BigDecimal(2)))));
	}
	@Override
	public boolean isConstant() {
		return angle.isConstant();
	}
	@Override
	public Operation simplifyConstants() {
		if(angle.isConstant()){
			return new Constant(new BigDecimal(1).divide(new BigDecimal("" + Math.tan(angle.evaluate(new BigDecimal(0)).doubleValue()))));
		}
		return new Cot(angle.simplifyConstants());
	}
	@Override
	public boolean isZero() {
		return false;
	}
	@Override
	public Operation simplifyZeros() {
		return new Cot(angle.simplifyZeros());
	}
	@Override
	public Operation simplifyOnes() {
		return new Cot(angle.simplifyOnes());
	}
	@Override
	public String toFile() {
		return Operation.fileRep(Operation.type.COT) + "\n" + angle.toFile();
	}
}
