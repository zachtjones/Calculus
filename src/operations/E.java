package operations;

import java.math.BigDecimal;

public class E extends Operation{
//Euler's number, the base of ln
	@Override
	public BigDecimal evaluate(BigDecimal x) {
		return new BigDecimal("" + Math.E);
	}

	@Override
	public String toString() {
		return "e";
	}

	@Override
	public Operation derivative() {
		return new Constant(new BigDecimal(0));
	}

	@Override
	public boolean isConstant() {
		return true;
	}

	@Override
	public Operation simplifyConstants() {
		return new Constant(new BigDecimal("" + Math.E));
	}

	@Override
	public boolean isZero() {
		return false;
	}

	@Override
	public Operation simplifyZeros() {
		return new E();
	}

	@Override
	public Operation simplifyOnes() {
		return new E();
	}

	@Override
	public String toFile() {
		return Operation.fileRep(Operation.type.E);
	}
}
