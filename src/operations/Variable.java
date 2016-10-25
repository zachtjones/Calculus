package operations;

import java.math.BigDecimal;

import ui.CalculusMain;

public class Variable extends Operation{
	public Variable(){}

	@Override
	public BigDecimal evaluate(BigDecimal x) {
		return x;
	}

	@Override
	public String toString() {
		return CalculusMain.variableLetter;
	}

	@Override
	public Operation derivative() { //der of x is always 1
		return new Constant(new BigDecimal(1));
	}

	@Override
	public boolean isConstant() {
		return false;
	}

	@Override
	public Operation simplifyConstants() {
		return this;
	}

	@Override
	public boolean isZero() {
		return false;
	}

	@Override
	public Operation simplifyZeros() {
		return new Variable();
	}

	@Override
	public Operation simplifyOnes() {
		return new Variable();
	}

	@Override
	public String toFile() {
		return Operation.fileRep(Operation.type.VARIABLE);
	}
}
