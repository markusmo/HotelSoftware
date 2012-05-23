package hotelsoftware.model.domain.invoice;

import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.data.invoice.PaymentMethodData;
import hotelsoftware.controller.data.parties.CustomerData;
import hotelsoftware.controller.data.users.UserData;
import hotelsoftware.model.domain.parties.ICustomer;
import hotelsoftware.model.domain.users.IUser;
import hotelsoftware.util.HelperFunctions;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * Diese Klasse stellt eine Rechung dar, mit der das System arbeitet.
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Invoice implements IInvoice
{
    private Integer id;
    private String invoiceNumber;
    private BigDecimal discount;
    private Date expiration;
    private Boolean fulfilled;
    private Date created;
    private IPaymentMethod paymentMethod;
    private ICustomer customer;
    private IUser user;
    private Collection<IInvoiceItem> invoiceItems;

    public Invoice()
    {
    }

    @Override
    public Date getCreated()
    {
        return created;
    }

    @Override
    public BigDecimal getDiscount()
    {
        return discount;
    }

        public static int getHighestId()
    {
        InvoiceManager facade = InvoiceManager.getInstance();
        return facade.getHighestInvoiceId();
    }
    
    @Override
    public Date getExpiration()
    {
        return expiration;
    }

    @Override
    public Boolean isFulfilled()
    {
        return fulfilled;
    }

    @Override
    public Integer getId()
    {
        return id;
    }

    @Override
    public ICustomer getCustomer()
    {
        return customer;
    }

    @Override
    public IUser getUser()
    {
        return user;
    }

    @Override
    public IPaymentMethod getPaymentMethod()
    {
        return paymentMethod;
    }

    @Override
    public String getInvoiceNumber()
    {
        return invoiceNumber;
    }

    @Override
    public Collection<IInvoiceItem> getInvoiceItems()
    {
        return invoiceItems;
    }

    @Override
    public void setCreated(Date created)
    {
        this.created = created;
    }

    @Override
    public void setDiscount(BigDecimal discount)
    {
        this.discount = discount;
    }

    @Override
    public void setExpiration(Date expiration)
    {
        this.expiration = expiration;
    }
    
    @Override
    public void setFulfilled(Boolean fulfilled)
    {
        this.fulfilled = fulfilled;
    }

    @Override
    public void setId(Integer id)
    {
        this.id = id;
    }

    @Override
    public void setCustomer(ICustomer customer)
    {
        this.customer = customer;
    }

    @Override
    public void setUser(IUser user)
    {
        this.user = user;
    }

    @Override
    public void setPaymentMethod(IPaymentMethod paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public void setInvoiceNumber(String invoiceNumber)
    {
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public void setInvoiceItems(Collection<IInvoiceItem> invoiceitemsCollection)
    {
        this.invoiceItems = invoiceitemsCollection;
    }

    @Override
    public String getPaymentMethodName()
    {
        return paymentMethod.getMethod();
    }

    /**
     * Sucht eine Rechung nach der Rechungsnummer und gibt diese zurück
     * @param invoicenumber
     * eine eindeutige Rechungsnummer
     * @return 
     * die Rechung mit der Rechungsnummer
     */
    public static IInvoice getInvoiceByInvoiceNumber(String invoicenumber)
    {
        return InvoiceManager.getInstance().getInvoiceByInvoiceNumber(invoicenumber);
    }

    /**
     * Gibt den Totalbetrag der Rechung aus, ohne Steuern
     * @return 
     * Totalbetrag der Rechung ohne Steuern
     */
    @Override
    public double getTotalwithoutTax()
    {
        double total = 0;
        
        for(IInvoiceItem i : invoiceItems)
        {
            total = total + i.getTotalPriceWithoutTax();
        }
        
        return total;
    }
    
    /**
     * gibt den Totalbetrag der Rechung aus, mit Steuern
     * @return 
     * Totalbetrag der Rechung mit Steuern
     */
    @Override
    public double getTotalwithTax()
    {
        double total = 0;
        
        for(IInvoiceItem i : invoiceItems)
        {
            total = total + i.getTotalPriceWithTax();
        }
        
        return total;
    }
    
    @Override
    public CustomerData getCustomerData()
    {
        return (CustomerData) getCustomer();
    }

    @Override
    public UserData getUserData()
    {
        return (UserData) getUser();
    }

    @Override
    public PaymentMethodData getPaymentMethodData()
    {
        return (PaymentMethodData) getPaymentMethod();
    }

    @Override
    public Collection<InvoiceItemData> getInvoiceItemsData()
    {
        return new HelperFunctions<InvoiceItemData, IInvoiceItem>().castCollectionUp(getInvoiceItems());
    }
}
