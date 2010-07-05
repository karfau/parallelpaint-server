package exceptions;

public class DrawingNotFoundException extends Exception {
	
	private static final long serialVersionUID = 6746925744831262812L;

	public DrawingNotFoundException () {
		super("No Drawing with requested id found!");
	} 	
} // class
