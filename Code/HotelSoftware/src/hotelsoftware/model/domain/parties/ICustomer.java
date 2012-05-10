package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.invoice.Invoice;

/**
 *Dieses Interface enthällt die Methoden der Klasse Customer, welche dort benötigt werden.
 * @author Kno
 */
public interface ICustomer {

    void removeInvoice(Invoice i);
    
}
