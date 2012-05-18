package at.fhv.roomanizer.persistence.entity.reservation;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name="reservationOptions")
public class OptionEntity {
	/**
	 * The id of the option
	 */
	@Id
	@Generated(GenerationTime.INSERT)
	@Column(name="id")
	private int _id;
	/**
	 * The date until which the option has to be fulfilled
	 */
	@Column(name="expiration")
	private Date _expiration;
	/**
	 * Wether the option has been fulfilled or not
	 */
	@Column(name="fulfilled")
	private boolean _fulfilled;
	/**
	 * The prepayment which has to be done until the expiration date
	 */
	@Column(name="prepayment")
	private double _prePayment;
	
	/**
	 * Reservation, which this option belongs to
	 */
	@ManyToOne
	@JoinColumn(name="idReservations")
	private ReservationEntity _reservation;
	
	/**
	 * Returns the reservation, which this option belongs to
	 * @return reservation, which this option belongs to
	 */
	public ReservationEntity getReservation() {
		return _reservation;
	}

	/**
	 * Sets the reservation, which this option belongs to
	 * @param reservation which this option belongs to
	 */
	public void setReservation(ReservationEntity reservation) {
		this._reservation = reservation;
	}

	/**
	 * Sets the date until which the option has to be fulfilled
	 * @param expiration The date until which the option has to be fulfilled
	 */
	public void setExpiration(Date expiration){
		_expiration = expiration;
	}
	
	/**
	 * Returns the date until which the opation has to be fulfilled
	 * @return The date until which the opation has to be fulfilled
	 */
	public Date getExpiration(){
		return _expiration;
	}
	
	/**
	 * Sets the date until which the option has to be fulfilled
	 * @param fulfilled The date until which the option has to be fulfilled
	 */
	public void setFulfilled(boolean fulfilled){
		_fulfilled = fulfilled;
	}
	
	/**
	 * Returns if the option has been fulfilled
	 * @return If the option has been fulfilled
	 */
	public boolean getFulfilled(){
		return _fulfilled;
	}
	
	/**
	 * Sets the prepayment needed until the expiration date
	 * @param prePayment The prepayment needed until the expiration date
	 */
	public void setPrePayment(double prePayment){
		_prePayment = prePayment;
	}
	
	/**
	 * Returns the prepayment needed until the expiration date
	 * @return The prepayment needed until the expiration date
	 */
	public double getPrePayment(){
		return _prePayment;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getId() {
		return this._id;
	}
	
	public void setId(int id) {
		this._id = id;
	}
	
	// Conversion Domain-Objects <-> Hibernate-Objects
	/*public static OptionEntity getEntity(Option option) {
		OptionEntity entity = new OptionEntity();
		entity.setExpiration(option.getExpiration());
		entity.setFulfilled(option.getFulfilled());
		entity.setPrePayment(option.getPrePayment());
		entity.setId(option.getId());
		
		return entity;
	}
	
	public Option getDomainObject() {
		Option option = new Option();
		option.setExpiration(_expiration);
		option.setFulfilled(_fulfilled);
		option.setPrePayment(_prePayment);
		option.setId(_id);
		
		return option;
	}*/
}
