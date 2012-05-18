package at.fhv.roomanizer.persistence.entity.reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import at.fhv.roomanizer.domain.person.Guest;
import at.fhv.roomanizer.domain.person.IGuest;
import at.fhv.roomanizer.domain.reservation.IOption;
import at.fhv.roomanizer.domain.reservation.IReservationItem;
import at.fhv.roomanizer.domain.reservation.Option;
import at.fhv.roomanizer.domain.reservation.ReservationItem;
import at.fhv.roomanizer.persistence.entity.person.GuestEntity;
import at.fhv.roomanizer.persistence.entity.person.UserEntity;

/**
 * Reservations are made by an authorized user. They are bound
 * to one person, which is responsible for confirming the
 * reservation, as well as for billing.
 * @author Shady
 * @author Andreas Sinz <andreas.sinz@students.fhv.at>
 *
 */
@Entity
@Table(name="reservations")
public class ReservationEntity {

	/**
	 * The ID of the reservation
	 */
	@Id
	@Generated(GenerationTime.INSERT)
	@Column(name="id")
	private int _id;
	/**
	 * The number of the reservation (information for the customer)
	 */
	@Column(name="reservationNumber")
	private String _number;
	/**
	 * Start date of the reservation (respectively of the habitation)
	 */
	@Column(name="startDate")
	private Date _start;
	/**
	 * End date of the reservation (respectively of the habitation)
	 */
	@Column(name="endDate")
	private Date _end;
	/**
	 * Comments regarding the reservation
	 */
	@Column(name="comment")
	private String _comment;
	/**
	 * When the reservation was created
	 */
	@Column(name="created")
	private Date _created;
	/**
	 * The person to which this reservation belongs
	 */
	
	@OneToOne
	@JoinColumn(name="idParties")
	private GuestEntity _guest = new GuestEntity();
	/**
	 * The list of all the options
	 */
	@OneToMany(mappedBy="_reservation")
	private List<OptionEntity> _options = new ArrayList<OptionEntity>();
	
	@ManyToOne
	@JoinColumn(name="idUsers")
	private UserEntity _user;
	
	/**
	 * List of reservation Items of this reservation
	 */
	@OneToMany(mappedBy="_reservation")
	private List<ReservationItemEntity> _reservationItems= new ArrayList<ReservationItemEntity>();
	
	/*----------- GETTER AND SETTER -----------*/
	/**
	 * Returns the ID of the reservation
	 * @return int The id of the reservation
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
	 * Returns the reservation number
	 * @return String the number of the reservation
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
	 * Returns the start date
	 * @return Date the start date of the reservation
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
	 * Returns the end date of the reservation
	 * @return Date the end date of the reservation
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
	 * Returns the comment of the reservation
	 * @return String the comment of the reservation
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
	 * Returns the creation date of the reservation
	 * @return Date creation date
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
	public void setGuest(GuestEntity person){
		_guest = person;
	}
	
	/**
	 * Returns the person of the reservation
	 * @return Person of the reservation
	 */
	public GuestEntity getGuest(){
		return _guest;
	}

	/**
	 * Returns the reservation item of the reservation
	 * @return ReservationItem 
	 */
	public List<ReservationItemEntity> getReservationItems() {
		return _reservationItems;
	}

	/**
	 * Sets the reservation item of this reservation
	 * @param reservationItems
	 */
	public void setReservationItems(List<ReservationItemEntity> reservationItems) {
		_reservationItems = reservationItems;
	}
	
	/**
	 * Returns a list of options which belong to this reservation
	 * @return List<Option> List of option
	 */
	public List<OptionEntity> getOptions(){
		return _options;
	}
	
	/**
	 * Sets the options of the reservation
	 * @param options of the reservation
	 */
	public void setOptions(List<OptionEntity> options) {
		this._options = options;
	}
	
}
