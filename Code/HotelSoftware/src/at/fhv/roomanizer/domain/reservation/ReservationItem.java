package at.fhv.roomanizer.domain.reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.fhv.roomanizer.domain.room.Category;
import at.fhv.roomanizer.domain.room.ICategory;
import at.fhv.roomanizer.domain.room.Room;

/**
 * 
 * @author Shady
 *
 */
public class ReservationItem implements IReservationItem{
	
	/**
	 * Amount of needed rooms
	 */
	private int _amount;
	
	/**
	 * Wanted category of the ReservationItem
	 */
	private Category _category;
	
	private Reservation _reservation;
	
	/**
	 * Check the availability of rooms in the given category.
	 * @param start
	 * @param end
	 * @return A list with the available rooms for the given category
	 */
	public List<Room> checkAvailability(Date start, Date end) {
		List<Room> rooms = new ArrayList<Room>();
		rooms = getCategory().checkAvailability(start, end, getAmount());
		return rooms;
	}
	
	
	
	
	/*------------ GETTER AND SETTER ---------------*/
	/**
	 * @see IReservationItem#getAmount()
	 */
	public int getAmount() {
		return _amount;
	}
	/**
	 * Sets the amount of rooms of the reservation item
	 * @param amount int amount of rooms
	 */
	public void setAmount(int amount) {
		_amount = amount;
	}
	
	/**
	 * Returns the category of the reservationItem
	 * @return Category the category
	 */
	public Category getCategory() {
		return _category;
	}
	
	/**
	 * Sets the category of the ReservationItem
	 * @param category Category the category
	 */
	public void setCategory(Category category) {
		_category = category;
	}

	/**
	 * @see IReservationItem#getICategory()
	 */
	@Override
	public ICategory getICategory() {
		return (ICategory) _category;
	}

	/**
	 * Returns the reservation to which this ReservationItem is attached
	 * @return Reservation
	 */
	public Reservation getReservation() {
		return _reservation;
	}

	/**
	 * Sets the reservation to which this ReservationItem is attached
	 * @param reservation Reservation
	 */
	public void setReservation(Reservation reservation) {
		this._reservation = reservation;
	}
	
}
