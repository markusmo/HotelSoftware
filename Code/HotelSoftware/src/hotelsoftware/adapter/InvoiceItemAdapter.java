/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.invoice.InvoiceItem;
import at.fhv.roomanizer.domain.person.User;
import at.fhv.roomanizer.domain.service.Service;
import java.util.Date;

/**
 *
 * @author Johannes
 */
public class InvoiceItemAdapter extends InvoiceItem
{
    private hotelsoftware.model.domain.invoice.InvoiceItem invoiceItem;

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
       return invoiceItem.getPrice();
    }

    @Override
    public Service getService()
    {
        return super.getService();
    }

    @Override
    public User getUser()
    {
        return super.getUser();
    }

    @Override
    public void setAmount(int amount)
    {
        super.setAmount(amount);
    }

    @Override
    public void setCreated(Date created)
    {
        super.setCreated(created);
    }

    @Override
    public void setHabitation(Habitation habitation)
    {
        super.setHabitation(habitation);
    }

    @Override
    public void setId(int id)
    {
        super.setId(id);
    }

    @Override
    public void setPrice(double price)
    {
        super.setPrice(price);
    }

    @Override
    public void setService(Service service)
    {
        super.setService(service);
    }

    @Override
    public void setUser(User user)
    {
        super.setUser(user);
    }
    
    
}
