package operations;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DefiniteIntegral {

	/**
	 * Uses the trapeziodal rule to approximate the definite integral.
	 * @param subIntervals The number of subIntervals to use. 
	 * Remember that while a larger number of subIntervals gives a larger degree of accuracy, but will take longer to compute.
	 * @param start The start of the interval
	 * @param end The end of the interval
	 * @param function The Operation that is the function to take the definite integral of.
	 * @return The approximation to the definite integral of function from start to end.
	 * @throws IllegalIntegralException If you can't use this rule for the function.
	 */
	public static BigDecimal trapeziodalRule(int subIntervals, BigDecimal start, BigDecimal end, Operation function) throws IllegalIntegralException{
		if(start.doubleValue() > end.doubleValue()) {//if the start is after the end, return the negative of end to start
			return trapeziodalRule(subIntervals, end, start, function).negate();
		}
		
		BigDecimal accumulator = new BigDecimal(0);
		BigDecimal deltaX = end.subtract(start).divide(new BigDecimal(subIntervals), 8, RoundingMode.HALF_UP);
		accumulator = accumulator.add(function.evaluate(start));
		for(BigDecimal i = start.divide(new BigDecimal(1)).add(deltaX); i.doubleValue() <= end.doubleValue() - deltaX.doubleValue(); i = i.add(deltaX)){
			accumulator = accumulator.add(function.evaluate(i).multiply(new BigDecimal(2)));
		}
		accumulator = accumulator.add(function.evaluate(end));
		//round to 6 decimal places
		accumulator = accumulator.multiply(deltaX.divide(new BigDecimal(2), 6, RoundingMode.HALF_UP));
		
		return accumulator;
	}

	/**
	 * Uses Simpson's rule to approximate the definite integral.
	 * @param subIntervals The number of subIntervals to use. 
	 * Remember that while a larger number of subIntervals gives a larger degree of accuracy, but will take longer to compute.
	 * @param start The start of the interval
	 * @param end The end of the interval
	 * @param function The Operation that is the function to take the definite integral of.
	 * @return The approximation to the definite integral of function from start to end.
	 * @throws IllegalIntegralException If you can't use this rule for the function.
	 */
	public static BigDecimal simpsonsRule(int subIntervals, BigDecimal start, BigDecimal end, Operation function) throws IllegalIntegralException{
		if(start.doubleValue() > end.doubleValue()) {//if the start is after the end, return the negative of end to start
			return simpsonsRule(subIntervals, end, start, function).negate();
		}
		
		//you need an even number of subintervals
		if(subIntervals % 2 != 0){
			throw new IllegalIntegralException("You need an even number of subintervals for Simpson's rule");
		}
		
		BigDecimal accumulator = new BigDecimal(0);
		BigDecimal deltaX = end.subtract(start).divide(new BigDecimal(subIntervals), 8, RoundingMode.HALF_UP);
		
		accumulator = accumulator.add(function.evaluate(start));
		for(BigDecimal i = start.add(deltaX); i.doubleValue() <= end.doubleValue() - deltaX.doubleValue(); i = i.add(deltaX.multiply(new BigDecimal(2)))){
			//4 times the function
			accumulator = accumulator.add(function.evaluate(i).multiply(new BigDecimal(4)));
		}
		for(BigDecimal i = start.add(deltaX).add(deltaX); i.doubleValue() <= end.doubleValue() - 2*deltaX.doubleValue(); i = i.add(deltaX.multiply(new BigDecimal(2)))){
			//2 times the function
			accumulator = accumulator.add(function.evaluate(i).multiply(new BigDecimal(2)));
		}
		accumulator = accumulator.add(function.evaluate(end));
		//round to 6 decimal places
		accumulator = accumulator.multiply(deltaX).divide(new BigDecimal(3), 6, RoundingMode.HALF_UP);
		
		return accumulator;
	}
}
