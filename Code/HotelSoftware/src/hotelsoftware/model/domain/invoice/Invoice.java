package hotelsoftware.model.domain.invoice;

import hotelsoftware.login.LoginController;
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.invoice.DBInvoice;
import hotelsoftware.model.domain.parties.Customer;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.users.User;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 * @author mohi
 */
public class Invoice implements InvoiceData
{
    private Integer id;
    private String invoiceNumber;
    private BigDecimal discount;
    private Date expiration;
    private boolean fulfilled;
    private Date created;
    private PaymentMethod idpaymentMethods;
    private Customer idCustomers;
    private User idUsers;
    private Collection<InvoiceItem> invoiceitemsCollection;

    public Invoice()
    {
    }

    public static Invoice create(String invoiceNr, BigDecimal discount, Date expiration, boolean fulfilled, PaymentMethod paymentmethod, Customer customer)
    {
        return new Invoice(invoiceNr, discount, expiration, fulfilled, paymentmethod, customer, LoginController.getInstance().getCurrentUser());
    }

    private Invoice(String invoiceNr, BigDecimal discount, Date expiration,
            boolean fulfilled, PaymentMethod paymentMethod,
            Customer customer, User user)
    {
        this.invoiceNumber = invoiceNr;
        this.discount = discount;
        this.expiration = expiration;
        this.fulfilled = fulfilled;
        this.idpaymentMethods = paymentMethod;
        this.idCustomers = customer;
        this.idUsers = user;
        this.invoiceitemsCollection = new LinkedList<InvoiceItem>();
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
    public boolean isFulfilled()
    {
        return fulfilled;
    }

    public Integer getId()
    {
        return id;
    }

    @Override
    public Customer getIdCustomers()
    {
        return idCustomers;
    }

    @Override
    public User getIdUsers()
    {
        return idUsers;
    }

    @Override
    public PaymentMethod getIdpaymentMethods()
    {
        return idpaymentMethods;
    }

    @Override
    public String getInvoiceNumber()
    {
        return invoiceNumber;
    }

    @Override
    public Collection<InvoiceItem> getInvoiceitems()
    {
        return invoiceitemsCollection;
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

    public void setFulfilled(boolean fulfilled)
    {
        this.fulfilled = fulfilled;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setIdCustomers(Customer idCustomers)
    {
        this.idCustomers = idCustomers;
    }

    public void setIdUsers(User idUsers)
    {
        this.idUsers = idUsers;
    }

    public void setIdpaymentMethods(PaymentMethod idpaymentMethods)
    {
        this.idpaymentMethods = idpaymentMethods;
    }

    public void setInvoiceNumber(String invoiceNumber)
    {
        this.invoiceNumber = invoiceNumber;
    }

    public void setInvoiceitems(Collection<InvoiceItem> invoiceitemsCollection)
    {
        this.invoiceitemsCollection = invoiceitemsCollection;
    }

    @Override
    public String getPaymentMethodName()
    {
        return idpaymentMethods.getMethod();
    }

    /**
     * Gibt eine neue Rechnung aus, die die Rechnungspositionen zu einer jeweiligen Belegung ausgibt.
     * @param habitation
     * die Belegung auf die gebucht wurde
     * @return
     * eine neue Rechnung auf eine Belegung
     */
    @Override
    public Invoice getInvoiceByHabitation(Habitation habitation)
    {
        Invoice invoice = Invoice.create(invoiceNumber, discount, expiration, fulfilled, idpaymentMethods, idCustomers);
        LinkedList<InvoiceItem> items = new LinkedList<InvoiceItem>();

        for (InvoiceItem item : this.invoiceitemsCollection)
        {
            if (item.getHabitation().equals(habitation))
            {
                items.add(item);
            }
        }
        this.invoiceitemsCollection.removeAll(items);
        invoice.setInvoiceitems(items);

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
}
