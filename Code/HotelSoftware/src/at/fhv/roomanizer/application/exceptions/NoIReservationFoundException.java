/**
 * 
 */
package at.fhv.roomanizer.application.exceptions;

/**
 * A Exception thrown is no reservation was found
 * 
 * @author phils
 */
public class NoIReservationFoundException extends Exception {
	public NoIReservationFoundException(){
	}
	/**
	 * gives the exception a specific message
	 * @param s
	 */
	public NoIReservationFoundException(String s) {
		super(s);
	}
}
