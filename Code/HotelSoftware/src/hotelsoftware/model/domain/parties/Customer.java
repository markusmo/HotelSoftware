/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.invoice.Invoice;
import java.util.Collection;
import java.util.LinkedList;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 * @author Hubert
 */
public abstract class Customer extends Party implements CustomerData {

	protected Address invoiceAddress;
	protected Collection<Invoice> invoices;

	Customer(){}

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
