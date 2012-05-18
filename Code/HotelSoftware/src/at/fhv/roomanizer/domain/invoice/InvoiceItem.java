package at.fhv.roomanizer.domain.invoice;

import java.util.Date;

import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.person.User;
import at.fhv.roomanizer.domain.service.Service;

/**
 * Represent an Item of the Invoice
 * 
 * @author phils, Rafael Neumann
 */
public class InvoiceItem implements IInvoiceItem {
	/**
	 * the id of the InvoiceItem
	 */
	private int _id;
	/**
	 * the price of the invoiceitem
	 */
	private double _price;
	/**
	 * the amount which was consumed
	 */
	private int _amount;
	/**
	 * the date the item was created
	 */
	private Date _created;
	/**
	 * the service of the item
	 */
	private Service _service;
	
	/**
	 * the user who created the invoiceitem
	 */
	private User _user;
	
	/**
	 * the habitation which has the created the invoiceitem
	 */
	private Habitation _habitation;
	
	
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
	public Service getService() {
		return _service;
	}
	/**
	 * Returns the User who created the InvoiceItem
	 * @return the User
	 */
	public User getUser(){
		return _user;
	}
	/**
	 * Returns the habitation
	 * @return the habitation
	 */
	public Habitation getHabitation(){
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
	public void setService(Service service) {
		_service = service;
	}
	/**
	 * Sets the User which created the InvoiceItem
	 * @param user
	 */
	public void setUser(User user){
		_user = user;
	}

	/**
	 * sets the habitation
	 * @param habitation
	 */
	public void setHabitation(Habitation habitation){
		_habitation = habitation;
	}
}
