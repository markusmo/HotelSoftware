package hotelsoftware.model.domain.service;

import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.parties.Guest;

/**
 *Dieses Interface enthällt die Methoden der Klasse Habitation, welche dort benötigt werden.
 * @author Kno
 */
public interface IHabitation {

    void addGuests(Guest guest);

    void addInvoiceItems(InvoiceItem newInvoiceItem);

    String toString();
    
}
