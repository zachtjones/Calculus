package operations;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Sec extends Operation{
	private Operation angle;
	
	public Sec(Operation angle){
		this.angle = angle;
	}
	@Override
	public BigDecimal evaluate(BigDecimal x) {
		return new BigDecimal(1).divide(new BigDecimal("" + Math.cos(angle.evaluate(x).doubleValue())), 8, RoundingMode.HALF_UP);
	}

	@Override
	public String toString() {
		return "sec(" + angle.toString() + ")";
	}
	@Override
	public Operation derivative() { //der of angle * sec(angle)*tan(angle)
		return new Multiply(angle.derivative(), new Multiply(new Sec(angle), new Tan(angle)));
	}
	@Override
	public boolean isConstant() {
		return angle.isConstant();
	}
	@Override
	public Operation simplifyConstants() {
		if(this.isConstant()){
			return new Constant(new BigDecimal(1).divide(new BigDecimal("" + Math.cos(angle.evaluate(new BigDecimal(0)).doubleValue()))));
		}
		return new Sec(angle.simplifyConstants());
	}
	@Override
	public boolean isZero() {
		return false;
	}
	@Override
	public Operation simplifyZeros() {
		return new Sec(angle.simplifyZeros());
	}
	@Override
	public Operation simplifyOnes() {
		return new Sec(angle.simplifyOnes());
	}
	@Override
	public String toFile() {
		return Operation.fileRep(Operation.type.SEC) + "\n" + angle.toFile();
	}
}
