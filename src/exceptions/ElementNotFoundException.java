package exceptions;

public class ElementNotFoundException extends Exception {

	private static final long serialVersionUID = -6509042509524524415L;

	public ElementNotFoundException(String message) {
		super("No Element with given id found! >>> DETAIL: " + message);
	}
} // class
