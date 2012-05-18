package at.fhv.roomanizer.persistence.entity.invoice;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import at.fhv.roomanizer.persistence.entity.HabitationEntity;
import at.fhv.roomanizer.persistence.entity.person.UserEntity;
import at.fhv.roomanizer.persistence.entity.service.ServiceEntity;

/**
 * Entitity representing the InvoiceItem
 * @author Shady
 *
 */
@Entity
@Table(name="invoiceItems")
public class InvoiceItemEntity {

	/**
	 * the id of the InvoiceItem
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int _id;
	
	/**
	 * the price of the invoiceitem
	 */
	@Column(name="price")
	private double _price;
	
	/**
	 * the amount which was consumed
	 */
	@Column(name="amount")
	private int _amount;
	
	/**
	 * the date the item was created
	 */
	@Column(name="created")
	private Date _created;
	
	/**
	 * the service of the item
	 */
	@ManyToOne
	@JoinColumn(name="idServices")
	@Cascade(value = { CascadeType.ALL })
	private ServiceEntity _service;

	/**
	 * the user who created the invoiceitem
	 */
	@ManyToOne
	@JoinColumn(name="idUsers")
	private UserEntity _user;

	/**
	 * the habitation which has created the invoiceitem
	 */
	@ManyToOne
	@JoinColumn(name="idHabitations")
	private HabitationEntity _habitation;


	/*-------------getter of the class-------------------------*/
	/**
	 * Returns the id of the InvoiceItem
	 * @return the of the InvoiceItem
	 */
	public int getId() {
		return _id;
	}
	/**
	 * returns the price of the invoiceitem
	 * @return the price of the invoiceitem
	 */
	public double getPrice() {
		return _price;
	}
	/**
	 * Returns the amount of the invoiceitem
	 * @return the amount of the invoiceitem
	 */
	public int getAmount() {
		return _amount;
	}
	/**
	 * Returns the date the invoiceitem was created
	 * @return the date the invoiceitem was created
	 */
	public Date getCreated() {
		return _created;
	}
	/**
	 * Returns the service of the invoiceitem
	 * @return the service of the invoiceitem
	 */
	public ServiceEntity getService() {
		return _service;
	}
	/**
	 * Returns the User who created the InvoiceItem
	 * @return the User
	 */
	public UserEntity getUser(){
		return _user;
	}
	/**
	 * Returns the habitation
	 * @return the habitation
	 */
	public HabitationEntity getHabitation(){
		return _habitation;
	}

	/*-------------setter of the class-------------------------*/
	/**
	 * Sets the ID of the InvoiceItem
	 * @param id
	 */
	public void setId(int id) {
		_id = id;
	}
	/**
	 * sets the price of the invoiceitem
	 * @param price
	 */
	public void setPrice(double price) {
		_price = price;
	}

	/**
	 * set the amount of the invoiceitem
	 * @param amount
	 */
	public void setAmount(int amount) {
		_amount = amount;
	}

	/**
	 * Sets the date the invoiceitem was created
	 * @param created
	 */
	public void setCreated(Date created) {
		_created = created;
	}

	/**
	 * Sets the service of the invoiceitem
	 * @param service
	 */
	public void setService(ServiceEntity service) {
		_service = service;
	}
	/**
	 * Sets the User which created the InvoiceItem
	 * @param user
	 */
	public void setUser(UserEntity user){
		_user = user;
	}

	/**
	 * sets the habitation
	 * @param habitation
	 */
	public void setHabitation(HabitationEntity habitation){
		_habitation = habitation;
	}
}
