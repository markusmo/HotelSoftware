/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.invoice.Invoice;

/**
 *
 * @author Kno
 */
public interface ICustomer {

    void removeInvoice(Invoice i);
    
}
