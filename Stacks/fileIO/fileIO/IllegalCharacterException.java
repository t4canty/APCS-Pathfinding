package fileIO;

public class IllegalCharacterException extends Exception {
	/**
	 * Error for what happens when an illegal char is found. 
	 */
	private static final long serialVersionUID = 2437295439366279347L;
	public IllegalCharacterException(String err, Throwable t) {
		super(err, t);
	}
	public IllegalCharacterException(String err) {
		super(err);
	}
}
