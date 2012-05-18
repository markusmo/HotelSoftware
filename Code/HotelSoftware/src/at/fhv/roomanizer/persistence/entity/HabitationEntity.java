package at.fhv.roomanizer.persistence.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import at.fhv.roomanizer.persistence.entity.person.GuestEntity;
import at.fhv.roomanizer.persistence.entity.person.UserEntity;
import at.fhv.roomanizer.persistence.entity.room.RoomEntity;
import at.fhv.roomanizer.persistence.entity.service.ServiceEntity;

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
@Entity
@Table(name="habitations")
@PrimaryKeyJoinColumn(name="idServices")
public class HabitationEntity extends ServiceEntity {
	/**
	 * The date on which the habitation has started
	 */
	
	@Column(name="startDate")
	private Date _start;
	/**
	 * The date on which the habitation will end
	 */
	@Column(name="endDate")
	private Date _end;
	/**
	 * The price, for which the habitation has been sold
	 */
	@Column(name="price")
	private double _price;
	/**
	 * A remark, which describes the deposit of the guest
	 */
	@Column(name="deposit")
	private String _deposit;
	/**
	 * The date on which the habitation has been created
	 */
	@Column(name="created")
	private Date _created;
	/**
	 * The room which belongs to this habitation
	 */
	@OneToOne
	@JoinColumn(name="idRooms")
	private RoomEntity _room;
	
	@OneToOne
	@JoinColumn(name="idUsers")
	private UserEntity _user;
	
	/**
	 * All the guests which are included in this habitation
	 */
	@ManyToMany
	@JoinTable(name="allocations",
			joinColumns = { @JoinColumn(name="idHabitations", referencedColumnName="idServices") },
			inverseJoinColumns = { @JoinColumn(name="idGuests", referencedColumnName="idParties") })
	private List<GuestEntity> _guests = new ArrayList<GuestEntity>();
	
	/**
	 * Sets the start of the habitation
	 * @param start The start of the habitation
	 */
	public void setStart(Date start){
		_start = start;
	}
	
	/**
	 * Returns the start of the habitation
	 * @return The start of the habitation
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
	 * Returns the end of the habitation
	 * @return The end of the habitation
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
	 * Returns the price of the habitation
	 * @return The price of the habitation
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
	 * Returns the deposit of the habitation
	 * @return The deposit of the habitation
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
	 * Returns the created date of the habitation (when an employee has created it)
	 * @return The created date of the habitation
	 */
	public Date getCreated(){
		return _created;
	}

	/**
	 * Sets the room for this habitation
	 * @param room The room for this habitation
	 */
	public void setRoom(RoomEntity room) {
		_room = room;
	}
	
	/**
	 * Returns the room for this habitation
	 * @return The room for this habitation
	 */
	public RoomEntity getRoom(){
		return _room;
	}

	/**
	 * Adds a guest to this habitation
	 * @param guests The guest to add to this habitation
	 */
	public void setGuests(List<GuestEntity> guests) {
		_guests = guests;
	}
	
	/**
	 * Returns the list with the guests for this habitation
	 * @return The list with the guests for this habitation
	 */
	public List<GuestEntity> getGuests(){
		return _guests;
	}

	/**
	 * Returns the user, which has created the habitation
	 * @return User, which has created the habitation
	 */
	public UserEntity getUser() {
		return _user;
	}

	/**
	 * Sets the user, which has created the habitation
	 * @param user The user, which has created the habitation
	 */
	public void setUser(UserEntity user) {
		this._user = user;
	}
}
