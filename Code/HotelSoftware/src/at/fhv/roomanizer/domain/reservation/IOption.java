package at.fhv.roomanizer.domain.reservation;

import java.util.Date;

public interface IOption {
	/**
	 * Returns the date until which the opation has to be fulfilled
	 * @return The date until which the opation has to be fulfilled
	 */
	public Date getExpiration();
	
	/**
	 * Returns if the option has been fulfilled
	 * @return If the option has been fulfilled
	 */
	public boolean getFulfilled();
	
	/**
	 * Returns the prepayment needed until the expiration date
	 * @return The prepayment needed until the expiration date
	 */
	public double getPrePayment();
}
