package hotelsoftware.model.domain.invoice;

import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.controller.data.service.ServiceData;
import hotelsoftware.controller.data.users.UserData;
import hotelsoftware.controller.login.LoginController;
import hotelsoftware.model.domain.service.IHabitation;
import hotelsoftware.model.domain.service.IService;
import hotelsoftware.model.domain.users.IUser;
import java.math.BigDecimal;
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
    private BigDecimal price;

    public InvoiceItem()
    {
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

    
    @Override
    public double getOnlyTax()
    {
        double tax = (this.getService().getServiceType().getTaxRate().doubleValue() / 100);
        return this.price.doubleValue() * tax;
    }
        
    @Override
    public double getTotalPriceWithTax()
    {
        return getPriceWithTax() * this.amount;
    }

    @Override
    public double getPriceWithTax()
    {
        return this.price.doubleValue() +  getOnlyTax();
    }

    @Override
    public double getPriceWithoutTax()
    {
        return this.price.doubleValue();
    }

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
    public BigDecimal getPrice()
    {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price)
    {
        this.price = price;
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
