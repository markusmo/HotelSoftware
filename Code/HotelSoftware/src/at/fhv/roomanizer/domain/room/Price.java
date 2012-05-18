package at.fhv.roomanizer.domain.room;

/**
 * Represents the price of a specific category in a specific season
 * 
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 */
public class Price implements IPrice{
	/**
	 * The standard price for the given season
	 */
	private double _price;
	/**
	 * The minimun price for the given season
	 */
	private double _priceMin;
	/**
	 * The season, in which this price is valid
	 */
	private Season _season;
	
	/**
	 * Sets the price
	 * @param price The price
	 */
	public void setPrice(double price){
		_price = price;
	}
	
	/**
	 * Returns the price
	 * @return The price
	 */
	public double getPrice(){
		return _price;
	}
	
	/**
	 * Sets the minimum price
	 * @param priceMin The minimum price
	 */
	public void setPriceMin(double priceMin){
		_priceMin = priceMin;
	}
	
	/**
	 * Returns the minimum price
	 * @return The minimum price
	 */
	public double getPriceMin(){
		return _priceMin;
	}
	
	/**
	 * Sets the season of the price
	 * @param season The season of the price
	 */
	public void setSeason(Season season){
		_season = season;
	}
	
	/**
	 * Returns the season of the price
	 * @return The season of the price
	 */
	public Season getSeason(){
		return _season;
	}

	@Override
	public ISeason getISeason() {
		return (ISeason) _season;
	}
}
