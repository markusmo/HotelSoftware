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
import hotelsoftware.model.domain.service.Habitation;
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
public class Invoice implements InvoiceData
{
    private Integer id;
    private String invoiceNumber;
    private BigDecimal discount;
    private Date expiration;
    private Boolean fulfilled;
    private Date created;
    private PaymentMethod paymentMethod;
    private Customer customer;
    private User user;
    private Collection<InvoiceItem> invoiceItems;

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
    public static Invoice create(String invoiceNr, BigDecimal discount, Date expiration, Boolean fulfilled, PaymentMethod paymentmethod, Customer customer)
    {
        return new Invoice(invoiceNr, discount, expiration, fulfilled, paymentmethod, customer, LoginController.getInstance().getCurrentUser());
    }

    private Invoice(String invoiceNr, BigDecimal discount, Date expiration,
            Boolean fulfilled, PaymentMethod paymentMethod,
            Customer customer, User user)
    {
        this.invoiceNumber = invoiceNr;
        this.discount = discount;
        this.expiration = expiration;
        this.fulfilled = fulfilled;
        this.paymentMethod = paymentMethod;
        this.customer = customer;
        this.user = user;
        this.invoiceItems = new LinkedHashSet<InvoiceItem>();
    }

    @Override
    public Date getCreated()
    {
        return created;
    }

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

    public Integer getId()
    {
        return id;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public User getUser()
    {
        return user;
    }

    public PaymentMethod getPaymentMethod()
    {
        return paymentMethod;
    }

    @Override
    public String getInvoiceNumber()
    {
        return invoiceNumber;
    }

    public Collection<InvoiceItem> getInvoiceItems()
    {
        return invoiceItems;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public void setDiscount(BigDecimal discount)
    {
        this.discount = discount;
    }

    public void setExpiration(Date expiration)
    {
        this.expiration = expiration;
    }
    
    public void setFulfilled(Boolean fulfilled)
    {
        this.fulfilled = fulfilled;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }

    public void setInvoiceNumber(String invoiceNumber)
    {
        this.invoiceNumber = invoiceNumber;
    }

    public void setInvoiceItems(Collection<InvoiceItem> invoiceitemsCollection)
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
    public Invoice getInvoiceByHabitation(Habitation habitation)
    {
        Invoice invoice = Invoice.create(invoiceNumber, discount, expiration, fulfilled, paymentMethod, customer);
        LinkedHashSet<InvoiceItem> items = new LinkedHashSet<InvoiceItem>();

        for (InvoiceItem item : this.invoiceItems)
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
    public static Invoice getInvoiceByInvoiceNumber(String invoicenumber)
    {
        return InvoiceFacade.getInstance().getInvoiceByInvoiceNumber(invoicenumber);
    }

    /**
     * Gibt den Totalbetrag der Rechung aus, ohne Steuern
     * @return 
     * Totalbetrag der Rechung ohne Steuern
     */
    public double getTotalwithoutTax()
    {
        double total = 0;
        
        for(InvoiceItem i : invoiceItems)
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
    public double getTotalwithTax()
    {
        double total = 0;
        
        for(InvoiceItem i : invoiceItems)
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
        return new HelperFunctions<InvoiceItemData, InvoiceItem>().castCollectionUp(getInvoiceItems());
    }
}
