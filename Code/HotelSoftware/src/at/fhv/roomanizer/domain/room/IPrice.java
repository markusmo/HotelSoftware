package at.fhv.roomanizer.domain.room;

/**
 * Represents the price of a specific category in a specific season
 * @author phils
 */
public interface IPrice {
	/**
	 * Returns the price
	 * @return The price
	 */
	public double getPrice();
	/**
	 * Returns the minimum price
	 * @return The minimum price
	 */
	public double getPriceMin();
	/**
	 * Returns the season of the price
	 * @return The season of the price
	 */
	public ISeason getISeason();
	
}
