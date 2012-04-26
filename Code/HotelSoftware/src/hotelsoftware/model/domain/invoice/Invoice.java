package hotelsoftware.model.domain.invoice;

import hotelsoftware.model.domain.invoice.data.InvoiceItemData;
import hotelsoftware.model.domain.invoice.data.PaymentMethodData;
import hotelsoftware.model.domain.invoice.data.InvoiceData;
import hotelsoftware.login.LoginController;
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.invoice.DBInvoice;
import hotelsoftware.model.domain.parties.Customer;
import hotelsoftware.model.domain.parties.data.CustomerData;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.users.User;
import hotelsoftware.model.domain.users.data.UserData;
import hotelsoftware.util.HelperFunctions;
import java.math.BigDecimal;
import java.util.*;

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
        DBInvoice dbi = DBInvoice.getInvoiceByInvoiceNumber(invoicenumber);
        return (Invoice) DynamicMapper.map(dbi);
    }

    public CustomerData getCustomerData()
    {
        return (CustomerData) getCustomer();
    }

    public UserData getUserData()
    {
        return (UserData) getUser();
    }

    public PaymentMethodData getPaymentMethodData()
    {
        return (PaymentMethodData) getPaymentMethod();
    }

    public Collection<InvoiceItemData> getInvoiceItemsData()
    {
        return new HelperFunctions<InvoiceItemData, InvoiceItem>().castCollectionUp(getInvoiceItems());
    }
}
