/**
 * 
 */
package at.fhv.roomanizer.application.exceptions;

/**
 * If not enough rooms are available
 * 
 * @author phils
 * 
 */
public class NoRoomException extends Exception {
	

	/**
	 * with this you can send your own errormessage
	 * 
	 * @param s
	 */
	public NoRoomException(String s) {
		super(s);
	}
}
