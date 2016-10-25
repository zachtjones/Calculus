package operations;

public class IllegalIntegralException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new IlligalIntegralException given the message
	 * @param message The string message that is retrievable by the getMessage() method
	 */
	public IllegalIntegralException(String message){
		super(message);
	}
}
