package hotelsoftware.model.domain.invoice;

import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.controller.data.service.ServiceData;
import hotelsoftware.controller.data.users.UserData;
import hotelsoftware.controller.login.LoginController;
import hotelsoftware.model.domain.service.*;
import hotelsoftware.model.domain.users.IUser;
import hotelsoftware.model.domain.users.User;
import java.util.Date;

/**
 * Diese Klasse stellt eine Rechungsposition dar, mit der das System intern
 * arbeitet.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class InvoiceItem implements IInvoiceItem
{
    private Integer id;
    private Integer amount;
    private Date created;
    private IService service;
    private IUser user;
    private IHabitation habitation;
    private IInvoice invoice;

    public InvoiceItem()
    {
    }

    private InvoiceItem(IService service, int amount, IUser user,
            IHabitation habitation)
    {
        this.amount = amount;
        this.service = service;
        this.user = user;
        this.habitation = habitation;
    }

    /**
     * Erstellt eine neue Instanz einer Rechungsposition
     *
     * @param service Der Service, der verrechnet wird
     * @param amount Die Menge der Services, die konsumiert wurden
     * @param habitation Der Aufenthalt, zu dem diese Position gehoert
     * @return Eine Rechungsposition, mit einer Anzahl von Services, zugehoerig
     * zu einem Aufenthalt mit dem User, der sie erstellt hat.
     */
    public static InvoiceItem createInvoiceItem(IService service, int amount, IHabitation habitation)
    {
        return new InvoiceItem(service, amount, LoginController.getInstance().getCurrentUser(), habitation);
    }

    @Override
    public Integer getId()
    {
        return id;
    }

    @Override
    public void setId(Integer id)
    {
        this.id = id;
    }

    @Override
    public Integer getAmount()
    {
        return amount;
    }

    @Override
    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    @Override
    public Date getCreated()
    {
        return created;
    }

    @Override
    public void setCreated(Date created)
    {
        this.created = created;
    }

    @Override
    public IService getService()
    {
        return service;
    }

    @Override
    public void setService(IService service)
    {
        this.service = service;
    }

    @Override
    public IInvoice getInvoice()
    {
        return invoice;
    }

    @Override
    public void setInvoice(IInvoice invoices)
    {
        this.invoice = invoices;
    }

    @Override
    public IUser getUser()
    {
        return user;
    }

    @Override
    public void setUser(IUser user)
    {
        this.user = user;
    }

    @Override
    public IHabitation getHabitation()
    {
        return habitation;
    }

    @Override
    public void fullfill()
    {
        invoice.setFulfilled(Boolean.TRUE);
    }

    /**
     * Diese Methode reduziert den RechnungsBetrag um den eingegebenen Betrag
     *
     * @param amount
     */
    @Override
    public void remove(Integer amount)
    {
        if (amount > this.amount)
        {
            this.amount = 0;
        }
        else
        {
            if (amount > 0)
            {
                this.amount -= amount;
            }
        }
    }

    @Override
    public void setHabitation(IHabitation habitation)
    {
        this.habitation = habitation;
    }

    /**
     * Gibt den Preis für eine Rechungsposion aus, mit Steuern
     *
     * @return Preis des Services * Anzahl der Konsumation + Steuern
     */
    @Override
    public double getTotalPriceWithTax()
    {
        double price = getPriceWithTax() * this.amount;
        return price;
    }

    /**
     * Gibt den Einzelpreis mit Steuern aus
     *
     * @return Einzelpreis mit Steuer
     */
    @Override
    public double getPriceWithTax()
    {
        double price = 0;
        double temp = this.getService().getPrice().doubleValue();
        double tax = (this.getService().getServiceType().getTaxRate().doubleValue() / 100) + 1;
        price = temp + (temp * tax);
        return price;
    }

    /**
     * Gibt den Einzelpreis ohne Steuern aus
     *
     * @return Einzelpreis ohne Steuer
     */
    @Override
    public double getPriceWithoutTax()
    {
        return this.getService().getPrice().doubleValue();
    }

    /**
     * Gibt den Preis für eine Rechungsposition aus, ohne Steuern.
     *
     * @return Preis des Services * Anzahl der Konsumation, ohne Steuern
     */
    @Override
    public double getTotalPriceWithoutTax()
    {
        return this.amount * getPriceWithoutTax();
    }

    @Override
    public HabitationData getHabitationData()
    {
        return (HabitationData) getHabitation();
    }

    @Override
    public ServiceData getServiceData()
    {
        return (ServiceData) getService();
    }

    @Override
    public UserData getUserData()
    {
        return (UserData) getUser();
    }
}
