package hotelsoftware.model.domain.invoice;

import hotelsoftware.controller.data.invoice.InvoiceData;
import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.data.invoice.PaymentMethodData;
import hotelsoftware.controller.data.parties.CustomerData;
import hotelsoftware.controller.data.users.UserData;
import hotelsoftware.controller.login.LoginController;
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.invoice.DBInvoice;
import hotelsoftware.model.domain.parties.Customer;
import hotelsoftware.model.domain.parties.ICustomer;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.IHabitation;
import hotelsoftware.model.domain.users.IUser;
import hotelsoftware.model.domain.users.User;
import hotelsoftware.util.HelperFunctions;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

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

    /**
     * Zum Instanzieren einer neuen Rechung.
     * @param invoiceNr
     * Die Rechnunsnummer, muss eindeutig sein
     * @param discount
     * Der Rabatt auf die Rechung
     * @param expiration
     * Das Auslaufsdatum
     * @param fulfilled
     * Ob die Rechnung schon gezahlt wurde, oder nicht
     * @param paymentmethod
     * Die Zahlungsmethode
     * @param customer
     * Der Kunde, der die Rechung zahlt
     * @return 
     * Eine Neue Rechung.
     */
    public static Invoice create(String invoiceNr, BigDecimal discount, Date expiration, Boolean fulfilled, IPaymentMethod paymentmethod, ICustomer customer)
    {
        return new Invoice(invoiceNr, discount, expiration, fulfilled, paymentmethod, customer, LoginController.getInstance().getCurrentUser());
    }

    private Invoice(String invoiceNr, BigDecimal discount, Date expiration,
            Boolean fulfilled, IPaymentMethod paymentMethod,
            ICustomer customer, IUser user)
    {
        this.invoiceNumber = invoiceNr;
        this.discount = discount;
        this.expiration = expiration;
        this.fulfilled = fulfilled;
        this.paymentMethod = paymentMethod;
        this.customer = customer;
        this.user = user;
        this.invoiceItems = new LinkedHashSet<IInvoiceItem>();
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
        return InvoiceFacade.getHighestInvoiceId();
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
     * Gibt eine neue Rechnung aus, die die Rechnungspositionen zu einer jeweiligen Belegung ausgibt.
     * @param habitation
     * die Belegung auf die gebucht wurde
     * @return
     * eine neue Rechnung auf eine Belegung
     */
    @Override
    public IInvoice getInvoiceByHabitation(IHabitation habitation)
    {
        IInvoice invoice = Invoice.create(invoiceNumber, discount, expiration, fulfilled, paymentMethod, customer);
        LinkedHashSet<IInvoiceItem> items = new LinkedHashSet<IInvoiceItem>();

        for (IInvoiceItem item : this.invoiceItems)
        {
            if (item.getHabitation().equals(habitation))
            {
                items.add(item);
            }
        }
        this.invoiceItems.removeAll(items);
        invoice.setInvoiceItems(items);

        return invoice;
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
        return InvoiceFacade.getInstance().getInvoiceByInvoiceNumber(invoicenumber);
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
