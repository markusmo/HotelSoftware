package at.fhv.roomanizer.domain.service;

/**
 * Represents an extra service in the hotel. 
 * 
 * @author Philipp Steiner, Rafael Neuamnn
 */
public class ExtraService extends Service implements IExtraService{
	/**
	 * The price of the extraservice
	 */
	private double _price;
	/**
	 * Returns the price of the extra service
	 * @return the price 
	 */
	public double getPrice() {
		return _price;
	}
	
	/**
	 * Sets the price of the extra service
	 * @param price
	 */
	public void setPrice(double price) {
		_price = price;
	}
	
}
