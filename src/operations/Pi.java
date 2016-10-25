package operations;

import java.math.BigDecimal;

public class Pi extends Operation{
	public Pi(){}

	@Override
	public BigDecimal evaluate(BigDecimal x) {
		return new BigDecimal(Math.PI);
	}

	@Override
	public String toString() {
		return "\u03C0"; //pi char
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
		return new Constant(new BigDecimal(Math.PI));
	}

	@Override
	public boolean isZero() {
		return false;
	}

	@Override
	public Operation simplifyZeros() {
		return new Pi();
	}

	@Override
	public Operation simplifyOnes() {
		return new Pi();
	}

	@Override
	public String toFile() {
		return Operation.fileRep(Operation.type.PI);
	}
}
