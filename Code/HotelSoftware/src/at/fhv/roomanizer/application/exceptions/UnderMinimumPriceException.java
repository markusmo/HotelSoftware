/**
 * 
 */
package at.fhv.roomanizer.application.exceptions;

/**
 * A Exception thrown if the price is less than the minimum
 * 
 * @author phils
 */
public class UnderMinimumPriceException extends Exception {

	public UnderMinimumPriceException() {

	}

	/**
	 * with this you can send your own errormessage
	 * 
	 * @param s
	 */
	public UnderMinimumPriceException(String s) {
		super(s);
	}
}
