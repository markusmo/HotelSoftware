package at.fhv.roomanizer.persistence.entity.reservation;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import at.fhv.roomanizer.persistence.entity.room.CategoryEntity;

/**
 * ReservationItem defines, which room category and how many rooms of this category are marked for a reservation
 * @author Shady
 * @author Andreas Sinz <andreas.sinz@students.fhv.at>
 *
 */

@Entity
@Table(name="reservationItems")
public class ReservationItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Amount of needed rooms
	 */
	
	@Column(name="amount")
	private int _amount;
	
	/**
	 * Wanted category of the ReservationItem
	 */
	@Id
	@ManyToOne
	@JoinColumn(name="idRoomCategories", insertable=false, updatable=false)
	private CategoryEntity _category;
	
	@Id
	@ManyToOne
	@JoinColumn(name="idReservations", insertable=false, updatable=false)
	private ReservationEntity _reservation;
	
	
	/*------------ GETTER AND SETTER ---------------*/
	/**
	 * Returns the amount of rooms of the reservation item
	 * @return int amount of rooms
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
	 * Returns the room category, the reservation item belongs to
	 * @return Room category, which the reservation item belongs to
	 */
	public CategoryEntity getCategory() {
		return _category;
	}
	
	/**
	 * Sets the room category of the reservation item
	 * @param category of the reservation item 
	 */
	public void setCategory(CategoryEntity category) {
		_category = category;
	}
	
	/**
	 * Returns the reservation the reservation item belongs to
	 * @return Returns the reservation, which the reservation item belongs to
	 */
	public ReservationEntity getReservation() {
		return _reservation;
	}
	
	/**
	 * Set the reservation, the reservation item belongs to
	 * @param reservation, which the reservation item belongs to
	 */
	public void setReservation(ReservationEntity reservation) {
		this._reservation = reservation;
	}
}
