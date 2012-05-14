package hotelsoftware.model.domain.invoice;

import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.controller.data.service.ServiceData;
import hotelsoftware.controller.data.users.UserData;
import hotelsoftware.controller.login.LoginController;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.Service;
import hotelsoftware.model.domain.service.ServiceType;
import hotelsoftware.model.domain.users.User;
import java.util.Date;

/**
 * Diese Klasse stellt eine Rechungsposition dar, mit der das System intern
 * arbeitet.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class InvoiceItem implements InvoiceItemData, IInvoiceItem
{
    private Integer id;
    private Integer amount;
    private Date created;
    private Service service;
    private User user;
    private Habitation habitation;
    private Invoice invoice;

    public InvoiceItem()
    {
    }

    private InvoiceItem(Service service, int amount, User user,
            Habitation habitation)
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
    public static InvoiceItem createInvoiceItem(Service service, int amount, Habitation habitation)
    {
        return new InvoiceItem(service, amount, LoginController.getInstance().getCurrentUser(), habitation);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    @Override
    public Integer getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    @Override
    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public Service getService()
    {
        return service;
    }

    public void setService(Service service)
    {
        this.service = service;
    }

    public Invoice getInvoice()
    {
        return invoice;
    }

    public void setInvoice(Invoice invoices)
    {
        this.invoice = invoices;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Habitation getHabitation()
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

    public void setHabitation(Habitation habitation)
    {
        this.habitation = habitation;
    }

    /**
     * Gibt den Preis für eine Rechungsposion aus, mit Steuern
     *
     * @return Preis des Services * Anzahl der Konsumation + Steuern
     */
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

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final InvoiceItem other = (InvoiceItem) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
        {
            return false;
        }
        if (this.created != other.created && (this.created == null || !this.created.equals(other.created)))
        {
            return false;
        }
        if (this.service != other.service && (this.service == null || !this.service.equals(other.service)))
        {
            return false;
        }
        if (this.habitation != other.habitation && (this.habitation == null || !this.habitation.equals(other.habitation)))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 53 * hash + (this.created != null ? this.created.hashCode() : 0);
        hash = 53 * hash + (this.service != null ? this.service.hashCode() : 0);
        hash = 53 * hash + (this.habitation != null ? this.habitation.hashCode() : 0);
        return hash;
    }
    
    
}
