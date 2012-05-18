package at.fhv.roomanizer.domain.invoice;

import java.util.Date;

import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.person.User;
import at.fhv.roomanizer.domain.service.Service;

public interface IInvoiceItem {
	/**
	 * Returns the id of the InvoiceItem
	 * @return the of the InvoiceItem
	 */
	public int getId();
	/**
	 * returns the price of the invoiceitem
	 * @return the price of the invoiceitem
	 */
	public double getPrice();
	/**
	 * Returns the amount of the invoiceitem
	 * @return the amount of the invoiceitem
	 */
	public int getAmount();
	/**
	 * Returns the date the invoiceitem was created
	 * @return the date the invoiceitem was created
	 */
	public Date getCreated();
	/**
	 * Returns the service of the invoiceitem
	 * @return the service of the invoiceitem
	 */
	public Service getService();
	/**
	 * Returns the User who created the InvoiceItem
	 * @return the User
	 */
	public User getUser();
	/**
	 * Returns the habitation
	 * @return the habitation
	 */
	public Habitation getHabitation();
}
