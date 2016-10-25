package operations;

import java.math.BigDecimal;

public class Power extends Operation{
	private Operation base;
	private Operation exponent;
	
	public Power(Operation base, Operation exponent){
		this.base = base;
		this.exponent = exponent;
	}
	@Override
	public BigDecimal evaluate(BigDecimal x) {
		return new BigDecimal(Math.pow(base.evaluate(x).doubleValue(),exponent.evaluate(x).doubleValue()));
	}

	@Override
	public String toString() {
		return "(" + base.toString() + " ^ " + exponent.toString() + ")";
	}
	@Override
	public Operation derivative() {
		//e^x
		if(base instanceof E || (base.isConstant()) && base.evaluate(new BigDecimal(0)).doubleValue() == Math.E){
			return new Multiply(exponent.derivative(), this.simplifyConstants()); //use the simplify as it creates a new instance
		}
		if(exponent instanceof Constant || exponent instanceof E || exponent instanceof Pi){
			//power rule - x^n = n*x^n-1 * der of x
			return new Multiply(new Constant(exponent.evaluate(new BigDecimal(0))), new Multiply(new Power(base, new Constant(exponent.evaluate(new BigDecimal(0)).subtract(new BigDecimal(1)))), base.derivative()));
		}
		System.out.println("Logaritmic differentiation not defined yet in this program");
		throw new IllegalDerivativeException();
	}
	@Override
	public boolean isConstant() {
		return (base.isConstant() && exponent.isConstant());
	}
	@Override
	public Operation simplifyConstants() {
		if(this.isConstant()){
			return new Constant(new BigDecimal("" + Math.pow(base.evaluate(new BigDecimal(0)).doubleValue(), exponent.evaluate(new BigDecimal(0)).doubleValue())));
		}
		return new Power(base.simplifyConstants(), exponent.simplifyConstants());
	}
	@Override
	public boolean isZero() { //0^0 is an indeterminate
		return(base.isZero() && !exponent.isZero());
	}
	@Override
	public Operation simplifyZeros() {
		if(this.isZero()){
			return new Constant(new BigDecimal(0));
		}
		return new Power(base.simplifyZeros(), exponent.simplifyZeros());
	}
	@Override
	public Operation simplifyOnes() {
		if(exponent.isZero()){
			return new Constant(new BigDecimal(1));
		}
		if(exponent.isConstant() && exponent.evaluate(new BigDecimal(0)).doubleValue() == 1){ //anything to the first is itself
			return base.simplifyOnes();
		}
		return new Power(base.simplifyOnes(), exponent.simplifyOnes());
	}
	/**
	 * Makes the exponent the negative of the existing exponent
	 * @return new Power(base, new Negative(exponent))
	 */
	public Power makeExpNegative(){
		return new Power(base, new Negative(exponent));
	}
	@Override
	public String toFile() {
		return Operation.fileRep(Operation.type.POWER) + "\n" + base.toFile() + "\n" + exponent.toFile();
	}
}
