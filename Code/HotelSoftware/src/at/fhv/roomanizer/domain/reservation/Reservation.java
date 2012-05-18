package at.fhv.roomanizer.domain.reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.fhv.roomanizer.domain.person.Guest;
import at.fhv.roomanizer.domain.person.IGuest;
import at.fhv.roomanizer.domain.person.Party;
import at.fhv.roomanizer.domain.room.Category;
import at.fhv.roomanizer.domain.room.Room;

/**
 * Reservations are made by an authorized user. They are bound
 * to one person, which is responsible for confirming the
 * reservation, as well as for billing.
 * @author Shady
 *
 */
public class Reservation implements IReservation {

	/**
	 * The ID of the reservation
	 */
	private int _id;
	/**
	 * The number of the reservation (information for the customer)
	 */
	private String _number;
	/**
	 * Start date of the reservation (respectively of the habitation)
	 */
	private Date _start;
	/**
	 * End date of the reservation (respectively of the habitation)
	 */
	private Date _end;
	/**
	 * Comments regarding the reservation
	 */
	private String _comment;
	/**
	 * When the reservation was created
	 */
	private Date _created;
	/**
	 * The person to which this reservation belongs
	 */
	private Guest _person;
	/**
	 * The list of all the options
	 */
	private List<Option> _options = new ArrayList<Option>();
	
	/**
	 * List of reservation Items of this reservation
	 */
	private List<ReservationItem> _reservationItems= new ArrayList<ReservationItem>();
	/**
	 * List of all the guests of this reservation
	 */
	private List<Guest> _guests = new ArrayList<Guest>();
	
	/**
	 * Creating one or more reservation items, with the category.
	 * Adding the items to the _reservationItems list
	 * @param c Category of the reservation item
	 * @param amount Amount of identical reservation items to be created
	 */
	public void createItem(Category c, int amount){
			ReservationItem item = new ReservationItem();
			item.setCategory(c);
			item.setAmount(amount);
			_reservationItems.add(item);
	}
	
	/**
	 * Adds an option to the reservation
	 * @param option The option to add
	 */
	public void addOption(Option option) {
		_options.add(option);
	}
	
	/**
	 * The Reservation checks all its reservation items,
	 * and bundles all the available rooms in the returned list.
	 * @return One list with all the available rooms for this reservation
	 */
	public List<Room> checkAvailability(){
		List<Room> allAvailableRooms = new ArrayList<Room>();
		for(ReservationItem item : getReservationItems()){
			List<Room> availableRooms = item.checkAvailability(getStart(), getEnd());
			for(Room room : availableRooms){
				allAvailableRooms.add(room);
			}
		}
		return allAvailableRooms;
	}
	
	
	/**
	 * Returns if the reservation intersects with duration spaned by the given dates
	 * @param start The start date to check
	 * @param end The end date to check
	 * @return True if the dates intersect, otherwise false
	 */
	public boolean isBetween(Date start, Date end) {
		return (_start.compareTo(end) < 0 && _end.compareTo(end) >= 0) || (_start.compareTo(start) <= 0 && _end.compareTo(start) > 0);
	}
	
	/*----------- GETTER AND SETTER -----------*/
	
	/**
	 * @see IReservation#getId()
	 */
	public int getId() {
		return _id;
	}
	
	/**
	 * Sets the ID of the reservation
	 * @param id int The id of the reservation
	 */
	public void setId(int id) {
		_id = id;
	}
	
	/**
	 * @see IReservation#getNumber()
	 */
	public String getNumber() {
		return _number;
	}
	
	/**
	 * Sets the number of the Reservation
	 * @param number String Number of the reservation
	 */
	public void setNumber(String number) {
		_number = number;
	}
	
	/**
	 * @see IReservation#getStart()
	 */
	public Date getStart() {
		return _start;
	}
	
	/**
	 * Sets the start date of the reservation
	 * @param start Date the start date 
	 */
	public void setStart(Date start) {
		_start = start;
	}
	
	/**
	 * @see IReservation#getEnd()
	 */
	public Date getEnd() {
		return _end;
	}
	
	/**
	 * Sets the end date of the reservation
	 * @param end Date the end date of the reservation
	 */
	public void setEnd(Date end) {
		_end = end;
	}
	
	/**
	 * @see IReservation#getComment()
	 */
	public String getComment() {
		return _comment;
	}
	
	/**
	 * Sets the comment of the reservation
	 * @param comment String comment of the reservation
	 */
	public void setComment(String comment) {
		_comment = comment;
	}
	
	/**
	 * @see IReservation#getCreated()
	 */
	public Date getCreated() {
		return _created;
	}
	
	/**
	 * Sets the creation date of the reservation 
	 * @param created Date created date of the reservation
	 */
	public void setCreated(Date created) {
		_created = created;
	}
	
	/**
	 * Sets the person 
	 * @param person Person of the reservation
	 */
	public void setGuest(Guest person){
		_person = person;
	}
	
	/**
	 * Returns the person of the reservation
	 * @return Person of the reservation
	 */
	public Guest getGuest(){
		return _person;
	}

	/**
	 * Returns the reservation item of the reservation
	 * @return ReservationItem 
	 */
	public List<ReservationItem> getReservationItems() {
		return _reservationItems;
	}

	/**
	 * Sets the reservation item of this reservation
	 * @param reservationItems
	 */
	public void setReservationItems(List<ReservationItem> reservationItems) {
		_reservationItems = reservationItems;
	}
	
	/**
	 * Returns a list of options which belong to this reservation
	 * @return List<Option> List of option
	 */
	public List<Option> getOptions(){
		return _options;
	}

	/**
	 * @see IReservation#getIOptions()
	 */
	@Override
	public List<IOption> getIOptions() {
		List<IOption> ioptions = new ArrayList<IOption>();
		for(Option o : getOptions()){
			ioptions.add((IOption)o);
		}
		return ioptions;
	}
	
	/**
	 * Adds a guest to this reservation
	 * @param g The guest to add
	 */
	public void addGuest(Guest g) {
		_guests.add(g);
	}

	/**
	 * @see IReservation#getGuests()
	 */
	@Override
	public List<Guest> getGuests() {
		return _guests;
	}
	
	/**
	 * @see IReservation#getIGuest()
	 */
	@Override
	public IGuest getIGuest() {
		return (IGuest) getGuest();
	}

	/**
	 * @see IReservation#getIReservationItems()
	 */
	@Override
	public List<IReservationItem> getIReservationItems() {
		List<IReservationItem> items = new ArrayList<IReservationItem>();
		for(ReservationItem ri : getReservationItems()){
			items.add((IReservationItem) ri);
		}
		return items;
	}
}
