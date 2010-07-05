package exceptions;

/**
 * In our simple versioning implementation for Drawings exists 
 * following restriction: </br></br>
 * A new version of a given Drawing can only be created if the
 * given Drawing itself is <i>not</i> allready a version </br>
 * of another Drawing entity.
 * </br>
 * Every Drawing entity which has <i>not</i> a value of {@code 0L} for the property {@code versionOf}
 * is per definition allready </br>
 * a version of another Drawing entity.
 */

public class IsAllreadyAVersionOfADrawingException extends Exception {

	private static final long serialVersionUID = -4170537567988256690L;

	public IsAllreadyAVersionOfADrawingException() {
		super("This Drawing is allready a version of another Drawing!");
	}	
} // class
