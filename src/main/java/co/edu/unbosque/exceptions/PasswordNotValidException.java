package co.edu.unbosque.exceptions;

public class PasswordNotValidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2775008037238877677L;

	public PasswordNotValidException() {
		super("The password doesn't meet the standard, needs at least 8 characters, one uppercase letter, one lowercase letter and one symbol");
	}

}
