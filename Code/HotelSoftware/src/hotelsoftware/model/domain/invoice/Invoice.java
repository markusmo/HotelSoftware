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
    private boolean fulfilled;
    private Date created;
    private PaymentMethod idpaymentMethod;
    private Customer idCustomer;
    private User idUser;
    private Collection<InvoiceItem> invoiceitems;

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
        this.idpaymentMethod = paymentMethod;
        this.idCustomer = customer;
        this.idUser = user;
        this.invoiceitems = new LinkedHashSet<InvoiceItem>();
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

    public Customer getIdCustomer()
    {
        return idCustomer;
    }

    public User getIdUser()
    {
        return idUser;
    }

    public PaymentMethod getIdpaymentMethod()
    {
        return idpaymentMethod;
    }

    @Override
    public String getInvoiceNumber()
    {
        return invoiceNumber;
    }

    public Collection<InvoiceItem> getInvoiceitems()
    {
        return invoiceitems;
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

    public void setIdCustomer(Customer idCustomers)
    {
        this.idCustomer = idCustomers;
    }

    public void setIdUser(User idUsers)
    {
        this.idUser = idUsers;
    }

    public void setIdpaymentMethod(PaymentMethod idpaymentMethods)
    {
        this.idpaymentMethod = idpaymentMethods;
    }

    public void setInvoiceNumber(String invoiceNumber)
    {
        this.invoiceNumber = invoiceNumber;
    }

    public void setInvoiceitems(Collection<InvoiceItem> invoiceitemsCollection)
    {
        this.invoiceitems = invoiceitemsCollection;
    }

    @Override
    public String getPaymentMethodName()
    {
        return idpaymentMethod.getMethod();
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
        Invoice invoice = Invoice.create(invoiceNumber, discount, expiration, fulfilled, idpaymentMethod, idCustomer);
        LinkedHashSet<InvoiceItem> items = new LinkedHashSet<InvoiceItem>();

        for (InvoiceItem item : this.invoiceitems)
        {
            if (item.getHabitation().equals(habitation))
            {
                items.add(item);
            }
        }
        this.invoiceitems.removeAll(items);
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

    public CustomerData getIdCustomersData()
    {
        return (CustomerData) getIdCustomer();
    }

    public UserData getIdUsersData()
    {
        return (UserData) getIdUser();
    }

    public PaymentMethodData getIdpaymentMethodsData()
    {
        return (PaymentMethodData) getIdpaymentMethod();
    }

    public Collection<InvoiceItemData> getInvoiceitemsData()
    {
        return new HelperFunctions<InvoiceItemData, InvoiceItem>().castCollectionUp(getInvoiceitems());
    }
}
