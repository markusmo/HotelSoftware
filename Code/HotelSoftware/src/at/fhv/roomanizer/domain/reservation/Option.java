package at.fhv.roomanizer.domain.reservation;

import java.util.Date;

/**
 * An option is a condition which has to be fulfilled until a given date,
 * otherwise the reservation will be invalid.
 * 
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 */
public class Option implements IOption {
	/**
	 * The id of the option
	 */
	private int _id;
	/**
	 * The date until which the option has to be fulfilled
	 */
	private Date _expiration;
	/**
	 * Wether the option has been fulfilled or not
	 */
	private boolean _fulfilled;
	/**
	 * The prepayment which has to be done until the expiration date
	 */
	private double _prePayment;
	
	/**
	 * Sets the date until which the option has to be fulfilled
	 * @param expiration The date until which the option has to be fulfilled
	 */
	public void setExpiration(Date expiration){
		_expiration = expiration;
	}
	
	/**
	 * @see IOption#getExpiration()
	 */
	public Date getExpiration(){
		return _expiration;
	}
	
	/**
	 * Sets the date until which the option has to be fulfilled
	 * @param fulfilled The date until which the option has to be fulfilled
	 */
	public void setFulfilled(boolean fulfilled){
		_fulfilled = fulfilled;
	}
	
	/**
	 * @see IOption#getFulfilled()
	 */
	public boolean getFulfilled(){
		return _fulfilled;
	}
	
	/**
	 * Sets the prepayment needed until the expiration date
	 * @param prePayment The prepayment needed until the expiration date
	 */
	public void setPrePayment(double prePayment){
		_prePayment = prePayment;
	}
	
	/**
	 * @see IOption#getPrePayment()
	 */
	public double getPrePayment(){
		return _prePayment;
	}
	
	/**
	 * Sets the id of the Option
	 * @param id
	 */
	public void setId(int id) {
		this._id = id;
	}
	
	/**
	 * Returns the id of the Option
	 * @return The id of the option
	 */
	public int getId() {
		return this._id;
	}
}
