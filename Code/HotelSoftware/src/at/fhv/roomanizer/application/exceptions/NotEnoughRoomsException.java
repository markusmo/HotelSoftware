package at.fhv.roomanizer.application.exceptions;

/**
 * if not enough rooms are available
 * 
 * @author phils
 * 
 */
public class NotEnoughRoomsException extends Exception {
	/**
	 * Throws a exception with a specific text
	 * @param s
	 */
	public NotEnoughRoomsException(String s) {
		super(s);
	}
}
