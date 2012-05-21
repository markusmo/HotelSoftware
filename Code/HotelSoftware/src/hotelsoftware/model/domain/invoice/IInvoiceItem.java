/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.invoice;

import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.model.domain.service.IHabitation;
import hotelsoftware.model.domain.service.IService;
import hotelsoftware.model.domain.users.IUser;
import java.util.Date;

/**
 *
 * @author Kno
 */
public interface IInvoiceItem extends InvoiceItemData{

    void fullfill();

    Integer getPrice();

    void setPrice(Integer price);

    IHabitation getHabitation();

    Integer getId();

    public IInvoice getInvoice();

    /**
     * Gibt den Einzelpreis mit Steuern aus
     *
     * @return Einzelpreis mit Steuer
     */
    double getPriceWithTax();

    

    IService getService();


    /**
     * Gibt den Preis für eine Rechungsposion aus, mit Steuern
     *
     * @return Preis des Services * Anzahl der Konsumation + Steuern
     */
    double getTotalPriceWithTax();

    /**
     * Gibt den Preis für eine Rechungsposition aus, ohne Steuern.
     *
     * @return Preis des Services * Anzahl der Konsumation, ohne Steuern
     */
    double getTotalPriceWithoutTax();

    IUser getUser();

    /**
     * Diese Methode reduziert den RechnungsBetrag um den eingegebenen Betrag
     *
     * @param amount
     */
    void remove(Integer amount);

    void setAmount(int amount);

    void setCreated(Date created);

    void setHabitation(IHabitation habitation);

    void setId(Integer id);

    void setInvoice(IInvoice invoices);

    void setService(IService service);

    void setUser(IUser user);
    
}
