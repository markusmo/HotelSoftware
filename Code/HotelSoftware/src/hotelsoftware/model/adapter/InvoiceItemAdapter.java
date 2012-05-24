/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.adapter;

import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.invoice.InvoiceItem;
import at.fhv.roomanizer.domain.person.User;
import at.fhv.roomanizer.domain.service.Service;
import hotelsoftware.model.domain.invoice.IInvoiceItem;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Johannes
 */
public class InvoiceItemAdapter extends InvoiceItem implements Adapter<hotelsoftware.model.domain.invoice.IInvoiceItem>
{
    private hotelsoftware.model.domain.invoice.InvoiceItem invoiceItem;
    
    public InvoiceItemAdapter()
    {
        this.invoiceItem = new hotelsoftware.model.domain.invoice.InvoiceItem();
    }

    @Override
    public int getAmount()
    {
        return invoiceItem.getAmount();
    }

    @Override
    public Date getCreated()
    {
        return invoiceItem.getCreated();
    }

    @Override
    public Habitation getHabitation()
    {
        return new HabitationAdapter(invoiceItem.getHabitation());
    }

    @Override
    public int getId()
    {
        return invoiceItem.getId();
    }

    @Override
    public double getPrice()
    {
        return invoiceItem.getPrice().doubleValue();
    }

    @Override
    public Service getService()
    {
        return new ServiceAdapter(invoiceItem.getService());
    }

    @Override
    public User getUser()
    {
        return new UserAdapter(invoiceItem.getUser());
    }

    @Override
    public void setAmount(int amount)
    {
        invoiceItem.setAmount(amount);
    }

    @Override
    public void setCreated(Date created)
    {
        invoiceItem.setCreated(created);
    }

    @Override
    public void setHabitation(Habitation habitation)
    {
        invoiceItem.setHabitation((new HabitationAdapter(habitation)).getOurType());
    }

    @Override
    public void setId(int id)
    {
        invoiceItem.setId(id);
    }

    @Override
    public void setPrice(double price)
    {
        invoiceItem.setPrice(BigDecimal.valueOf(price));
    }

    @Override
    public void setService(Service service)
    {
       invoiceItem.setService(((ServiceAdapter)service).getOurType());
    }

    @Override
    public void setUser(User user)
    {
        this.invoiceItem.setUser(((UserAdapter)user).getOurType());
    }

    @Override
    public IInvoiceItem getOurType()
    {
        return invoiceItem;
    }

    @Override
    public void setOurType(IInvoiceItem type)
    {
        invoiceItem = (hotelsoftware.model.domain.invoice.InvoiceItem) type;
    }
}
