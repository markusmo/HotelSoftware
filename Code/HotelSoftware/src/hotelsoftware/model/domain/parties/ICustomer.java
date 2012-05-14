/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.controller.data.invoice.InvoiceData;
import hotelsoftware.controller.data.parties.AddressData;
import hotelsoftware.controller.data.parties.CustomerData;
import hotelsoftware.model.domain.invoice.IInvoice;
import hotelsoftware.model.domain.invoice.Invoice;
import java.util.Collection;

/**
 *
 * @author Kno
 */
public interface ICustomer extends IParty, CustomerData{

    void addInvoice(Invoice i);

    IAddress getInvoiceAddress();

    Collection<IInvoice> getInvoices();

    void addInvoice(IInvoice i);
    
    void removeInvoice(IInvoice i);

    void setInvoiceAddress(IAddress invoiceAddress);

    void setInvoices(Collection<IInvoice> invoices);
    
}
