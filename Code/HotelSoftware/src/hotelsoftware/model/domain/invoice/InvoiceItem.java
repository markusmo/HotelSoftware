package hotelsoftware.model.domain.invoice;

import hotelsoftware.model.domain.invoice.data.InvoiceItemData;
import hotelsoftware.login.LoginController;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.data.HabitationData;
import hotelsoftware.model.domain.service.Service;
import hotelsoftware.model.domain.service.data.ServiceData;
import hotelsoftware.model.domain.users.User;
import hotelsoftware.model.domain.users.data.UserData;
import java.util.Date;

/**
 * Diese Klasse stellt eine Rechungsposition dar, mit der das System intern arbeitet.
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class InvoiceItem implements InvoiceItemData
{
    private Integer amount;
    private Date created;
    private Service service;
    private User idUser;
    private Habitation idHabitation;
    private InvoiceItemPK pk;

    public InvoiceItem()
    {
    }

    private InvoiceItem(Service service, int amount, User user,
            Habitation habitation)
    {
        this.amount = amount;
        this.service = service;
        this.idUser = user;
        this.idHabitation = habitation;
    }

    /**
     * Erstellt eine neue Instanz einer Rechungsposition
     * @param service
     * Der Service, der verrechnet wird
     * @param amount
     * Die Menge der Services, die konsumiert wurden
     * @param habitation
     * Der Aufenthalt, zu dem diese Position gehoert
     * @return
     * Eine Rechungsposition, mit einer Anzahl von Services, zugehoerig zu einem Aufenthalt
     * mit dem User, der sie erstellt hat.
     */
    public static InvoiceItem createInvoiceItem(Service service, int amount, Habitation habitation)
    {
        return new InvoiceItem(service, amount, LoginController.getInstance().getCurrentUser(), habitation);
    }

    @Override
    public Integer getAmount()
    {
        return amount;
    }

    @Override
    public Date getCreated()
    {
        return created;
    }

    public Habitation getHabitation()
    {
        return idHabitation;
    }

    public Service getService()
    {
        return service;
    }

    public User getUser()
    {
        return idUser;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public void setIdHabitation(Habitation habitation)
    {
        this.idHabitation = habitation;
    }

    public void setService(Service service)
    {
        this.service = service;
    }

    public void setIdUser(User user)
    {
        this.idUser = user;
    }

    public InvoiceItemPK getInvoiceitemsPK()
    {
        return this.pk;
    }

    public void setInvoiceitemsPK(InvoiceItemPK invoiceitemsPK)
    {
        if (this.pk == null)
        {
            this.pk = invoiceitemsPK;
        }
    }
    
    /**
     * Gibt den Preis für eine Rechungsposition aus.
     * @return 
     * Preis es Services * Anzahl der Konsumation
     */
    @Override
    public double getTotalPrice()
    {
        return this.getAmount() * this.getTotalPrice();
    }

    public HabitationData getHabitationData()
    {
        return (HabitationData) getHabitation();
    }

    public ServiceData getServiceData()
    {
        return (ServiceData) getService();
    }

    public UserData getUserData()
    {
        return (UserData) getUser();
    }
}
