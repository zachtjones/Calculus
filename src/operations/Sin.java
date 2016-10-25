package operations;

import java.math.BigDecimal;

public class Sin extends Operation{
	private Operation angle;
	
	public Sin(Operation angle){
		this.angle = angle;
	}
	@Override
	public BigDecimal evaluate(BigDecimal x) {
		return new BigDecimal("" + Math.sin(angle.evaluate(x).doubleValue()));
	}

	@Override
	public String toString() {
		return "sin(" + angle.toString() + ")";
	}
	@Override
	public Operation derivative() {
		return new Multiply(angle.derivative(), new Cos(angle));
	}
	@Override
	public boolean isConstant() {
		return angle.isConstant();
	}
	@Override
	public Operation simplifyConstants() {
		if(this.isConstant()){
			return new Constant(new BigDecimal("" + Math.sin(angle.evaluate(new BigDecimal(0)).doubleValue())));
		}
		return new Sin(angle.simplifyConstants());
	}
	@Override
	public boolean isZero() {
		return false;
	}
	@Override
	public Operation simplifyZeros() {
		return new Sin(angle.simplifyZeros());
	}
	@Override
	public Operation simplifyOnes() {
		return new Sin(angle.simplifyOnes());
	}
	@Override
	public String toFile() {
		return Operation.fileRep(Operation.type.SIN) + "\n" + angle.toFile();
	}
}
