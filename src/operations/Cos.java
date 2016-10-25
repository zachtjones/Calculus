package operations;

import java.math.BigDecimal;

public class Cos extends Operation{
	private Operation angle;
	
	public Cos(Operation angle){
		this.angle = angle;
	}
	@Override
	public BigDecimal evaluate(BigDecimal x) {
		return new BigDecimal("" + Math.cos(x.doubleValue())); //use the String contructor
	}

	@Override
	public String toString() {
		return "cos(" + angle.toString() + ")";
	}
	@Override
	public Operation derivative() {
		return new Multiply(angle.derivative(), new Negative(new Sin(angle)));
	}
	@Override
	public boolean isConstant() {
		return angle.isConstant();
	}
	@Override
	public Operation simplifyConstants() {
		if(this.isConstant()){
			return new Constant(new BigDecimal("" + Math.cos(angle.evaluate(new BigDecimal(0)).doubleValue())));
		}
		return new Cos(angle.simplifyConstants());
	}
	@Override
	public boolean isZero() { //cos answers that are 0 will be reduced with the constant thing
		return false;
	}
	@Override
	public Operation simplifyZeros() {
		return new Cos(angle.simplifyZeros());
	}
	@Override
	public Operation simplifyOnes() {
		return new Cos(angle.simplifyOnes());
	}
	@Override
	public String toFile() {
		return Operation.fileRep(Operation.type.COS) + "\n" + angle.toFile();
	}
}
