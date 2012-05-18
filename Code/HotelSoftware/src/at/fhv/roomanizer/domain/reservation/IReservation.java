package at.fhv.roomanizer.domain.reservation;

import java.util.Date;
import java.util.List;

import at.fhv.roomanizer.domain.person.Guest;
import at.fhv.roomanizer.domain.person.IGuest;

public interface IReservation {
	
	/**
	 * Returns the ID of the reservation
	 * @return int The id of the reservation
	 */
	public int getId();
	
	/**
	 * Returns the Number of the reservation
	 * @return String number of the reservation
	 */
	public String getNumber();
	
	/**
	 * Returns the start date
	 * @return Date the start date of the reservation
	 */
	public Date getStart();
	
	/**
	 * Returns the end date of the reservation
	 * @return Date the end date of the reservation
	 */
	public Date getEnd();
	
	/**
	 * Returns the comment of the reservation
	 * @return String the comment of the reservation
	 */
	public String getComment();
	
	/**
	 * Returns the creation date of the reservation
	 * @return Date creation date
	 */
	public Date getCreated();
	
	/**
	 * Returns all the options of the reservation
	 * @return List<IOption>
	 */
	public List<IOption> getIOptions();
	
	/**
	 * Returns all the guests of the reservation, which are saved
	 * @return IGuest
	 */
	public IGuest getIGuest();
	
	/**
	 * Returns a list of all the guests in the reservation
	 * @return List<Guest>
	 */
	public List<Guest> getGuests();
	
	/**
	 * Returns a list of all the ReservationItems
	 * @return List<IReservationItems>
	 */
	public List<IReservationItem> getIReservationItems();
}
