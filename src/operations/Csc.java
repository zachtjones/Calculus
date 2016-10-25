package operations;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Csc extends Operation{
private Operation angle;
	
	public Csc(Operation angle){
		this.angle = angle;
	}
	@Override
	public BigDecimal evaluate(BigDecimal x) {
		return new BigDecimal(1).divide(new BigDecimal("" + Math.sin(angle.evaluate(x).doubleValue())), 8, RoundingMode.HALF_UP);
	}

	@Override
	public String toString() {
		return "csc(" + angle.toString() + ")";
	}
	@Override
	public Operation derivative() {
		return new Multiply(angle.derivative(), new Negative(new Multiply(new Csc(this.angle),new Cot(angle))));
	}
	@Override
	public boolean isConstant() {
		return angle.isConstant();
	}
	@Override
	public Operation simplifyConstants() {
		if(this.isConstant()){
			return new Constant(new BigDecimal(1).divide(new BigDecimal("" + Math.sin(angle.evaluate(new BigDecimal(0)).doubleValue()))));
		}
		return new Csc(angle.simplifyConstants());
	}
	@Override
	public boolean isZero() {
		return false;
	}
	@Override
	public Operation simplifyZeros() {
		return new Csc(angle.simplifyZeros());
	}
	@Override
	public Operation simplifyOnes() {
		return new Csc(angle.simplifyOnes());
	}
	@Override
	public String toFile() {
		return Operation.fileRep(Operation.type.CSC) + "\n" + angle.toFile();
	}
}
