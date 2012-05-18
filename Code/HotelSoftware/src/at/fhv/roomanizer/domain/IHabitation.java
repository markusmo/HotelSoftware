package at.fhv.roomanizer.domain;

import java.util.Date;
import java.util.List;

import at.fhv.roomanizer.domain.person.Guest;
import at.fhv.roomanizer.domain.room.Room;

public interface IHabitation {
	
	/**
	 * Returns the id of the habitation
	 * @return The id of the habitation
	 */
	public int getId();
	
	/**
	 * Returns the start of the habitation
	 * @return The start of the habitation
	 */
	public Date getStart();
	
	/**
	 * Returns the end of the habitation
	 * @return The end of the habitation
	 */
	public Date getEnd();
	
	/**
	 * Returns the price of the habitation
	 * @return The price of the habitation
	 */
	public double getPrice();
	
	/**
	 * Returns the deposit of the habitation
	 * @return The deposit of the habitation
	 */
	public String getDeposit();
	
	/**
	 * Returns the created date of the habitation (when an employee has created it)
	 * @return The created date of the habitation
	 */
	public Date getCreated();
	
	/**
	 * Returns the room for this habitation
	 * @return The room for this habitation
	 */
	public Room getRoom();
	
	/**
	 * Returns the list with the guests for this habitation
	 * @return The list with the guests for this habitation
	 */
	public List<Guest> getGuests();
}
