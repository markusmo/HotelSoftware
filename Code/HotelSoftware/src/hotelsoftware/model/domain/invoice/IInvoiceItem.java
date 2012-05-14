/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.invoice;

import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.IHabitation;
import hotelsoftware.model.domain.service.IService;
import hotelsoftware.model.domain.service.Service;
import hotelsoftware.model.domain.users.IUser;
import hotelsoftware.model.domain.users.User;
import java.util.Date;

/**
 *
 * @author Kno
 */
public interface IInvoiceItem extends InvoiceItemData{

    void fullfill();

    Integer getAmount();

    Date getCreated();

    IHabitation getHabitation();

    Integer getId();

    IInvoice getInvoice();

    /**
     * Gibt den Einzelpreis mit Steuern aus
     *
     * @return Einzelpreis mit Steuer
     */
    double getPriceWithTax();

    /**
     * Gibt den Einzelpreis ohne Steuern aus
     *
     * @return Einzelpreis ohne Steuer
     */
    double getPriceWithoutTax();

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
