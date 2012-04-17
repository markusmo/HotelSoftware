package hotelsoftware.model.domain.invoice;

import hotelsoftware.model.database.invoice.DBInvoiceitems;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.Service;
import hotelsoftware.model.domain.users.User;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 * @author mohi
 */
public class InvoiceItem
{

    private int amount;
    private Date created;
    private Service service;
    private User user;
    private Habitation habitation;

    public InvoiceItem(Service service, int amount, User user,
            Habitation habitation)
    {
        this.amount = amount;
        this.service = service;
        this.user = user;
        this.habitation = habitation;
    }

    public int getAmount()
    {
        return amount;
    }

    public Date getCreated()
    {
        return created;
    }

    public Habitation getHabitation()
    {
        return habitation;
    }

    public Service getService()
    {
        return service;
    }

    public User getUser()
    {
        return user;
    }
    
    /**
     * not yet implemented
     * @return 
     */
    public double getTotalPrice()
    {
        throw new NotImplementedException();
    }

    /**
     * communicates with the model and retrieves all invoiceitems for a invoice
     * @param invoice
     * the invoice, which owns the invoiceitems
     * @return
     * a linked list of invoiceitems
     */
    public static LinkedList<InvoiceItem> getInvoiceItemByInvoice(
            Invoice invoice)
    {
        return null;
    }

    /**
     * communicates with the model and retrieves all invoiceitems for a habitation
     * @param habitation
     * the habitation, which owns the invoices
     * @return 
     * a linked list of invoiceitems
     */
    public static LinkedList<InvoiceItem> getInvoiceItemsByHabitation(
            Habitation habitation)
    {
       return null;
    }
}
