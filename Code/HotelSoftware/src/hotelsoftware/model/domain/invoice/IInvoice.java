/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.invoice;

import hotelsoftware.controller.data.invoice.InvoiceData;
import hotelsoftware.model.domain.parties.ICustomer;
import hotelsoftware.model.domain.users.IUser;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Kno
 */
public interface IInvoice extends InvoiceData{

    ICustomer getCustomer();

    BigDecimal getDiscount();

    Integer getId();

    Collection<IInvoiceItem> getInvoiceItems();

    IPaymentMethod getPaymentMethod();

    /**
     * gibt den Totalbetrag der Rechung aus, mit Steuern
     * @return 
     * Totalbetrag der Rechung mit Steuern
     */
    double getTotalwithTax();

    /**
     * Gibt den Totalbetrag der Rechung aus, ohne Steuern
     * @return
     * Totalbetrag der Rechung ohne Steuern
     */
    double getTotalwithoutTax();

    IUser getUser();

    void setCreated(Date created);

    void setCustomer(ICustomer customer);

    void setDiscount(BigDecimal discount);

    void setExpiration(Date expiration);

    void setFulfilled(Boolean fulfilled);

    void setId(Integer id);

    void setInvoiceItems(Collection<IInvoiceItem> invoiceitemsCollection);

    void setInvoiceNumber(String invoiceNumber);

    void setPaymentMethod(IPaymentMethod paymentMethod);

    void setUser(IUser user);
    
}
