/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import java.awt.List;
import java.util.Collection;
import java.util.LinkedList;

import hotelsoftware.model.database.parties.DBCustomer;
import hotelsoftware.model.domain.invoice.Invoice;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public abstract class Customer extends Party {

	protected Address invoiceAddress;
	protected Collection<Invoice> invoices;

	/*
	 * 
	 * // WTF ist das ?!?
	 * 
	 * protected DBCustomer costumerModel;
	 * 
	 * public Customer(DBCustomer costumers) { this.costumerModel = costumers; }
	 * 
	 * public DBCustomer getCostumerModel() { return costumerModel; }
	 */

	Customer(){}
	
	public abstract String getName();

	public Address getInvoiceAddress() {
		return invoiceAddress;
	}

	public void setInvoiceAddress(Address invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}

	protected Customer(Address address, Address invoiceAddress) {
		this(address, invoiceAddress, new LinkedList<Invoice>());
	}

	protected Customer(Address address, Address invoiceAddress, Collection<Invoice> invoices)
	{super(address);

	this.invoiceAddress = invoiceAddress;
	this.invoices = invoices;	
	}
	
	public Collection<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(Collection<Invoice> invoices) {
		this.invoices = invoices;
	}

	public void removeInvoice(Invoice i) {
		invoices.remove(i);
	}

	public void addInvoice(Invoice i) {
		invoices.add(i);
	}
}
