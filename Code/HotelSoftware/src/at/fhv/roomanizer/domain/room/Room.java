package at.fhv.roomanizer.domain.room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.reservation.Reservation;

/**
 * Represents a room in the hotel.
 * 
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 */
public class Room implements IRoom {
	/**
	 * The id of the room
	 */
	private int _id;
	/**
	 * The number of the room
	 */
	private String _number;
	/**
	 * The status-list of the room
	 */
	private List<RoomStatus> _statusList = new ArrayList<RoomStatus>();
	/**
	 * A list containing the habitations of this room (Habitations are created when the Guest checks in!)
	 */
	private List<Habitation> _habitations = new ArrayList<Habitation>();
	/**
	 * A list containing the reservations of this room (Reservations are made some time before the guest arrives)
	 */
	private List<Reservation> _reservations = new ArrayList<Reservation>();
	/**
	 * The category of this room
	 */
	private Category _category;
	
	/**
	 * Sets the number of the room
	 * @param number The number of the room
	 */
	public void setNumber(String number){
		_number = number;
	}
	
	/**
	 * @see IRoom#getNumber()
	 */
	public String getNumber(){
		return _number;
	}
	
	/**
	 * Adds a habitation to the room
	 * @param habitation The habitation, which will be added to the room
	 */
	public void addHabitation(Habitation habitation){
		_habitations.add(habitation);
	}
	
	/**
	 * Sets the list of habitations, which the room belongs to
	 * @param habitationList List of Habitations, which the room belongs to
	 */
	public void setHabitations(List<Habitation> habitationList) {
		_habitations = habitationList;
	}
	
	/**
	 * @see IRoom#getHabitations()
	 */
	public List<Habitation> getHabitations(){
		return _habitations;
	}
	
	/**
	 * Adds a reservation to the room
	 * @param reservation The reservation, which will be added to the room
	 */
	public void addReservation(Reservation reservation){
		_reservations.add(reservation);
	}
	
	/**
	 * Returns the reservations of this room
	 * @return List<Reservation> The list of reservations of this room
	 */
	public List<Reservation> getReservations(){
		return _reservations;
	}
	/**
	 * Sets the category of the room
	 * @param category The category of the room
	 */
	public void setCategory(Category category){
		_category = category;
	}
	
	/**
	 * Returns the category of the room
	 * @return The category of the room
	 */
	public Category getCategory(){
		return _category;
	}
	
	/**
	 * Checks if the room is free for the given dates.
	 * Iterates through all the habitations, to check if one of them is occupying the room between the given dates.
	 * @param start The start of the duration
	 * @param end The end of the duration
	 * @return True if the room is free for the given duration, otherwise false
	 */
	public boolean checkAvailability(Date start, Date end){
		boolean roomsIntersect = false;
		boolean habitationsIntersect = false;
		
		for(Habitation h : getHabitations()){
			if(h.isBetween(start, end)){
				habitationsIntersect = true;
			}
		}
		
		for(Reservation r : getReservations()){
			if(r.isBetween(start, end)){
				roomsIntersect = true;
			}
		}
		/*In case a reservation OR a habitation is placed in the given
		 * timespan the Room is NOT available, so we return FALSE
		 */
		return !(roomsIntersect || habitationsIntersect);
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	/**
	 * @see IRoom#getICategory()
	 */
	@Override
	public ICategory getICategory() {
		return (ICategory) _category;
	}

	public List<RoomStatus> getStatus() {
		return _statusList;
	}

	public void setStatus(List<RoomStatus> statusList) {
		this._statusList = statusList;
	}

	@Override
	public List<IRoomStatus> getIStatus() {
		List<IRoomStatus> tmpList = new ArrayList<IRoomStatus>();
		for(RoomStatus tmp : _statusList) {
			tmpList.add(tmp);
		}
		
		return tmpList;
	}

	/**
	 * Adds a status to the room
	 * @param status The status to set
	 * @param start The start value of this status
	 * @param end The end value of this status
	 */
	public void addStatus(Status status, Date start, Date end) {
		RoomStatus rs = new RoomStatus();
		rs.setStatus(status);
		rs.setStart(start);
		rs.setRoom(this);
		rs.setEnd(end);
		_statusList.add(rs);
		
	}
}
