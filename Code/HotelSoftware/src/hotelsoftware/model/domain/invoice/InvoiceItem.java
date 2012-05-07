package hotelsoftware.model.domain.invoice;

import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.controller.data.service.ServiceData;
import hotelsoftware.controller.data.users.UserData;
import hotelsoftware.controller.login.LoginController;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.Service;
import hotelsoftware.model.domain.users.User;
import java.util.Date;

/**
 * Diese Klasse stellt eine Rechungsposition dar, mit der das System intern
 * arbeitet.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class InvoiceItem implements InvoiceItemData {

    private Integer amount;
    private Date created;
    private Service service;
    private User user;
    private Habitation habitation;
    private InvoiceItemPK pk;
    private Invoice invoice;

    public InvoiceItem() {
    }

    private InvoiceItem(Service service, int amount, User user,
            Habitation habitation) {
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
    public static InvoiceItem createInvoiceItem(Service service, int amount, Habitation habitation) {
        return new InvoiceItem(service, amount, LoginController.getInstance().getCurrentUser(), habitation);
    }

       public InvoiceItemPK getInvoiceitemsPK()
    {
        return pk;
    }

    public void setInvoiceitemsPK(InvoiceItemPK invoiceitemsPK)
    {
        this.pk = invoiceitemsPK;
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

    public void setHabitation(Habitation habitation)
    {
        this.habitation = habitation;
    }
    /**
     * Gibt den Preis für eine Rechungsposion aus, mit Steuern
     *
     * @return Preis des Services * Anzahl der Konsumation + Steuern
     */
    public double getTotalPriceWithTax() {
        double price = 0;
        double temp = this.amount * this.getService().getPrice().doubleValue();
        price = temp + (temp * this.getService().getServiceType().getTaxRate().doubleValue());
        return price;
    }

    /**
     * Gibt den Einzelpreis mit Steuern aus
     *
     * @return Einzelpreis mit Steuer
     */
    public double getPriceWithTax() {
        double price = 0;
        double temp = this.getService().getPrice().doubleValue();
        price = temp + (temp * this.getService().getServiceType().getTaxRate().doubleValue());
        return price;
    }

    /**
     * Gibt den Preis für eine Rechungsposition aus, ohne Steuern.
     *
     * @return Preis des Services * Anzahl der Konsumation, ohne Steuern
     */
    @Override
    public double getTotalPriceWithoutTax() {
        return this.getAmount() * this.getService().getPrice().doubleValue();
    }

    @Override
    public HabitationData getHabitationData() {
        return (HabitationData) getHabitation();
    }

    @Override
    public ServiceData getServiceData() {
        return (ServiceData) getService();
    }

    @Override
    public UserData getUserData() {
        return (UserData) getUser();
    }
}
