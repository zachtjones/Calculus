package operations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
/**
 * Instances of this class represent a mathematical function. 
 * The subclasses of this class are the various types of functions using an operation, like addition, subtraction, etc.
 * @author Zach Jones
 *
 */
public abstract class Operation implements Cloneable{
	public enum type {
		ADD, CONSTANT, COS, COT, CSC, DIVIDE, E, FACTORIAL, LN, LOG, MULTIPLY, NEGATIVE, PI, POWER, SEC, SIN, SUBTRACT, TAN, VARIABLE;
	}
	/**
	 * This value represents Long.Max_Value as a BigDecimal. 
	 * Use this value instead of infinites as BigDecimal does not support infinities
	 */
	public static BigDecimal largestValue = new BigDecimal(Long.MAX_VALUE);
	
	/**
	 * call this method to evaluate this operation's value when x is a certain value
	 * @param x The value of x
	 * @return The value of the function when x = the number you feed it
	 */
	public abstract BigDecimal evaluate(BigDecimal x);
	/**
	 * Returns the derivative of this operation
	 * @throws IllegalDerivativeException you can't take the derivative of that function
	 * @return
	 */
	public abstract Operation derivative();
	/**
	 * returns a String that represents this operation. <br>
	 * Different parts have different ways to be written, but this should resemble the function as it would appear in text.
	 * <br>The implementation of this method in each subclass of Operation is different.
	 */
	@Override
	public abstract String toString();
	/**
	 * Copies the object
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
	/**
	 * Tells if the operation is a constant. 
	 * This method is used in the simplifyConstants method for most Operation subclasses
	 * @return true if the operation is a constant, otherwise false.
	 */
	public abstract boolean isConstant();
	/**
	 * Attempts to simplify an operation by simplifing constants.
	 * @return A simplified version of the Operation
	 */
	public abstract Operation simplifyConstants();
	/**
	 * Tells if the operation is 0
	 * @return true if the operation is 0, otherwise false
	 */
	public abstract boolean isZero();
	/**
	 * Attempts to simplify an operation by removing instances of operations with 0.
	 * Trig functions do not get simplified, however, they do simplify the angle.
	 * @return A simplified version of the Operation
	 */
	public abstract Operation simplifyZeros();
	/**
	 * Attempts to simplify an operation by removing instances of operations with unnecessary 1's
	 * @return A simplified version of the Operation
	 */
	public abstract Operation simplifyOnes();
	/**
	 * Simplifies an Operation (function) by simplifying ones, zeros and constants.
	 * This is just a shortcut for doing all three actions
	 * @param input The Operation (function) to simplify
	 * @return A simplified result
	 */
	public static Operation simplify(Operation input){
		return input.simplifyConstants().simplifyZeros().simplifyOnes();
	}
	/**
	 * This will return a string, possibly composed of several lines separated by \n that
	 * is the operation and its parts.
	 * @return How the operation should be stored to file
	 */
	public abstract String toFile();
	/**
	 * Gets an operation from the file.
	 * @param fileName The name of the file as a string
	 * @return An operation from that file
	 * @throws IOException If the file can't be read
	 */
	public static Operation fromFile(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		Operation temp = fromFile(br);
		br.close();
		return temp;
	}
	/**
	 * Internal method to retrieve an operation. 
	 * This is recursive to read the same way as the file is made using the types of operations
	 * @param br
	 * @return
	 * @throws IOException
	 */
	private static Operation fromFile(BufferedReader br) throws IOException {
		String indicator = br.readLine();
		type ty = null; //the type
		for(type t: type.values()){
			if (fileRep(t).equals(indicator)){
				ty = t;
			}
		}
		//the type is specified, make the operation from the bufferedReader-unless it is Constant, E, Pi, or Variable
		switch(ty){ //go through and make the type from the file's contents
		case ADD:return new Add(fromFile(br), fromFile(br));
		case CONSTANT:return new Constant(new BigDecimal(br.readLine()));
		case COS:return new Cos(fromFile(br));
		case COT:return new Cot(fromFile(br));
		case CSC:return new Csc(fromFile(br));
		case DIVIDE:return new Divide(fromFile(br), fromFile(br));
		case E:return new E();
		case FACTORIAL:return new Factorial(fromFile(br));
		case LN:return new Ln(fromFile(br));
		case LOG:return new Log(fromFile(br));
		case MULTIPLY:return new Multiply(fromFile(br), fromFile(br));
		case NEGATIVE:return new Negative(fromFile(br));
		case PI:return new Pi();
		case POWER:return new Power(fromFile(br), fromFile(br));
		case SEC:return new Sec(fromFile(br));
		case SIN:return new Sin(fromFile(br));
		case SUBTRACT:return new Subtract(fromFile(br), fromFile(br));
		case TAN:return new Tan(fromFile(br));
		case VARIABLE:return new Variable();
		default:throw new IOException("A non-operation type was found in the file");
		}
	}
	/**
	 * Gets the string that represents one line of the file to specify what type of operation comes next
	 * @param t The Operation.type enum value that specifies the type of operation
	 * @return The string that specifies the type of operation in file
	 */
	static String fileRep(type t){
		switch(t){
		case ADD:return "a";
		case CONSTANT:return "con";
		case COS:return "cos";
		case COT:return "cot";
		case CSC:return "csc";
		case DIVIDE:return "d";
		case E:return "e";
		case FACTORIAL:return "fact";
		case LN:return "ln";
		case LOG:return "log";
		case MULTIPLY:return "mul";
		case NEGATIVE:return "neg";
		case PI:return "pi";
		case POWER:return "pow";
		case SEC:return "sec";
		case SIN:return "sin";
		case SUBTRACT:return "sub";
		case TAN:return "tan";
		case VARIABLE:return "var";
		default: return "";//should not happen
		}
	}
}
