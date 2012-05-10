/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.parties.Guest;

/**
 *
 * @author Kno
 */
public interface IHabitation {

    void addGuests(Guest guest);

    void addInvoiceItems(InvoiceItem newInvoiceItem);

    String toString();
    
}
