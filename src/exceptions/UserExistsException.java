package exceptions;

public class UserExistsException extends Exception {
	
	private static final long serialVersionUID = -1273607527350037455L;

	public UserExistsException () {
		super("User allready exists!");
	} 
} // class
