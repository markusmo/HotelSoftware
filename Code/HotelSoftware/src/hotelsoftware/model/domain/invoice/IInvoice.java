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
    
    /**
     * Storniert eine gegebene Anzahl von Rechnungspositionen einer Rechnung
     * @param item Die zu stornierende Rechnungsposition
     * @param amount Die anzahl an zu stornierenden Positionen
     */
    void cancelInvoiceItem(IInvoiceItem item, int amount);
    
    /**
     * Fügt der Rechnung eine weitere Rechnungsposition hinzu
     * @param item Die Position die hinzugefügt werden soll
     */
    void addInvoiceItem(IInvoiceItem item);
    
    /**
     * Fügt der Rechnung weitere Rechnungspositionen hinzu
     * @param items Die Positionen die hinzugefügt werden sollen
     */
    void addInvoiceItems(Collection<IInvoiceItem> items);
    
    /**
     * Ändert die Anzahl einer Rechnungsposition und gibt die dadurch neu entstandene Position zurück
     * @param item Das zu splittende InvoiceItem
     * @param amount Die Anzahl die beibehalten werden soll
     */
    IInvoiceItem splitItem(IInvoiceItem item, Integer amount);

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

    /**
     * Überprüft ob die Rechnung leer ist
     * @return TRUE wenn die Rechnung keine Positionen enthält
     */
    boolean isEmpty();

    /**
     * Entfernt alle Positionen von der Rechnung
     */
    void clear();

}
