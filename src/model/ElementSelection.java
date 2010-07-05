package model;

public class ElementSelection {
	private String selector;
	private boolean anonym;
	public final long timestamp;
	public final long elementId;
	public final long drawingId;

	public ElementSelection(final long elementId, final long drawingId, final String selector, final long timestamp) {
		this.setSelector(selector);
		this.elementId = elementId;
		this.drawingId = drawingId;
		this.timestamp = timestamp;
	}

	/**
	 * @param selector the selector to set
	 */
	public void setSelector(String selector) {
		this.anonym = selector.equalsIgnoreCase(PPUser.ANONYMOUS_USER_NAME);
		this.selector = selector;
	}

	/**
	 * <b style="color:#FF0000;">USE WITH CARE:</b>
	 * @return A string containing a unique String for the selecting user.<br/>
	 * ATTENTION: This differs from the selector that was set, if selector is anonym.<br/>
	 * DON'T RETURN this value if a selection/unselection was sucessfull, use the current username instead.<br/>
	 * To check if the current user is the selector use {@link ElementSelection#isSelector(String)}.
	 */
	public String getSelector() {
		return anonym ? PPUser.ANONYMOUS_AUTHOR(drawingId) : selector;
	}

	public boolean isSelector(String username){
		return this.selector.equalsIgnoreCase(username);
	}
}
