package at.fhv.roomanizer.domain.reservation;

import at.fhv.roomanizer.domain.room.ICategory;

public interface IReservationItem {
	/**
	 * Returns the amount of rooms of the reservation item
	 * @return int amount of rooms
	 */
	public int getAmount();
	
	/**
	 * Returns the Category of the ReservationItem
	 * @return ICategory
	 */
	public ICategory getICategory();
}
