/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.invoice;

import hotelsoftware.controller.data.invoice.InvoiceData;
import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.data.invoice.PaymentMethodData;
import hotelsoftware.controller.data.parties.CustomerData;
import hotelsoftware.controller.data.users.UserData;
import hotelsoftware.model.domain.parties.Customer;
import hotelsoftware.model.domain.parties.ICustomer;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.IHabitation;
import hotelsoftware.model.domain.users.IUser;
import hotelsoftware.model.domain.users.User;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Kno
 */
public interface IInvoice extends InvoiceData{

    Date getCreated();

    ICustomer getCustomer();

    BigDecimal getDiscount();

    Date getExpiration();

    Integer getId();

    Collection<IInvoiceItem> getInvoiceItems();

    String getInvoiceNumber();

    IPaymentMethod getPaymentMethod();

    String getPaymentMethodName();

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

    Boolean isFulfilled();

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
