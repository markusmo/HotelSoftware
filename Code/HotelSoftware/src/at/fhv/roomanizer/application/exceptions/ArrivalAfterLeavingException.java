/**
 * 
 */
package at.fhv.roomanizer.application.exceptions;

/**
 * A Exception thrown if the start date is after the end date
 * 
 * @author phils
 */
public class ArrivalAfterLeavingException extends Exception {
	public ArrivalAfterLeavingException() {
	}

	/**
	 * with this you can send your own errormessage
	 * 
	 * @param s
	 */
	public ArrivalAfterLeavingException(String s) {
		super(s);
	}
}
