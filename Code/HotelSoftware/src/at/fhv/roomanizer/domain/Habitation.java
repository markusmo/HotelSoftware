package at.fhv.roomanizer.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.fhv.roomanizer.domain.person.Guest;
import at.fhv.roomanizer.domain.person.User;
import at.fhv.roomanizer.domain.room.Room;
import at.fhv.roomanizer.domain.service.Service;

/**
 * Represents a habitation in the hotel. In our system a habitation only contains one room,
 * which means for bigger groups of people, living in different rooms, the system manages
 * multiple habitations.
 * 
 * Additionally to the standard information the habitation contains informationabout the room,
 * the guest who lives in the room during the habitation, the employee which did the checkin,
 * and the invoiceItems, which have to be payed at the checkout. 
 * 
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 */
public class Habitation extends Service implements IHabitation {
	/**
	 * The date on which the habitation has started
	 */
	private Date _start;
	/**
	 * The date on which the habitation will end
	 */
	private Date _end;
	/**
	 * The price, for which the habitation has been sold
	 */
	private double _price;
	/**
	 * A remark, which describes the deposit of the guest
	 */
	private String _deposit;
	/**
	 * The date on which the habitation has been created
	 */
	private Date _created;
	/**
	 * The room which belongs to this habitation
	 */
	private Room _room;
	
	/**
	 * The user which has created the habitation
	 */
	private User _user;
	
	/**

	 * @see IHabitation#getId()

	 */
	private List<Guest> _guests = new ArrayList<Guest>();
	
	/**
	 * Sets the start of the habitation
	 * @param start The start of the habitation
	 */
	public void setStart(Date start){
		_start = start;
	}
	
	/**
	 * @see IHabitation#getStart()
	 */
	public Date getStart(){
		return _start;
	}
	
	/**
	 * Sets the end of the habitation
	 * @param end The end of the habitation
	 */
	public void setEnd(Date end){
		_end = end;
	}
	
	/**
	 * @see IHabitation#getEnd()
	 */
	public Date getEnd(){
		return _end;
	}
	
	/**
	 * Sets the price of the habitation
	 * @param price The price of the habitation
	 */
	public void setPrice(double price){
		_price = price;
	}
	
	/**
	 * @see IHabitation#getPrice()
	 */
	public double getPrice(){
		return _price;
	}
	
	/**
	 * Sets the deposit of the habitation
	 * @param deposit The deposit of the habitation
	 */
	public void setDeposit(String deposit){
		_deposit = deposit;
	}
	
	/**
	 * @see IHabitation#getDeposit()
	 */
	public String getDeposit(){
		return _deposit;
	}
	
	/**
	 * Sets the created date of the habitation (when an employee has created it)
	 * @param created The created date of the habitation
	 */
	public void setCreated(Date created){
		_created = created;
	}
	
	/**
	 * @see IHabitation#getCreated()
	 */
	public Date getCreated(){
		return _created;
	}
	
	/**
	 * Sets the user of the habitation
	 * @param user
	 */
	public void setUser(User user){
		_user = user;
	}
	
	/**
	 * Returns the User which has created the habitation
	 * @return User which created the habitation
	 */
	public User getUser(){
		return _user;
	}

	/**
	 * Returns if the habitation intersects with duration spaned by the given dates
	 * @param start The start date to check
	 * @param end The end date to check
	 * @return True if the dates intersect, otherwise false
	 */
	public boolean isBetween(Date start, Date end) {
		return (_start.compareTo(end) < 0 && _end.compareTo(end) >= 0) || (_start.compareTo(start) <= 0 && _end.compareTo(start) > 0);
	}

	/**
	 * Sets the room for this habitation
	 * @param room The room for this habitation
	 */
	public void setRoom(Room room) {
		_room = room;
	}
	
	/**
	 * @see IHabitation#getCreated()
	 */
	public Room getRoom(){
		return _room;
	}

	/**
	 * Adds a guest to this habitation
	 * @param guest The guest to add to this habitation
	 */
	public void addGuest(Guest guest) {
		_guests.add(guest);
	}
	
	/**
	 * Adds a list of guests
	 * @param guests
	 */
	public void setGuests(List<Guest> guests){
		this._guests = guests;
	}
	/**
	 * @see IHabitation#getGuests()
	 */
	public List<Guest> getGuests(){
		return _guests;
	}
}
