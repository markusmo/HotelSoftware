/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.invoice;

import hotelsoftware.model.domain.parties.Customer;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.users.User;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface InvoiceData
{
    //FIXME change return-types to *Data!!!
    
    Date getCreated();

    Date getExpiration();

    Customer getIdCustomers();

    User getIdUsers();

    PaymentMethod getIdpaymentMethods();

    /**
     * Gibt eine neue Rechnung aus, die die Rechnungspositionen zu einer jeweiligen Belegung ausgibt.
     * @param habitation
     * die Belegung auf die gebucht wurde
     * @return
     * eine neue Rechnung auf eine Belegung
     */
    Invoice getInvoiceByHabitation(Habitation habitation);

    String getInvoiceNumber();

   
    Collection<InvoiceItem> getInvoiceitems();

    String getPaymentMethodName();

    boolean isFulfilled();
    
}
