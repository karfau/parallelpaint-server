package exceptions;

public class OperationNotAllowedException extends Exception {
	
	private static final long serialVersionUID = 7389142952112293477L;

	public OperationNotAllowedException(String reason) {
		super(reason);
	}
} // class
