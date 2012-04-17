package hotelsoftware.domain.invoice;

import hotelsoftware.database.FailedToSaveToDatabaseException;
import hotelsoftware.database.model.DBInvoices;
import hotelsoftware.domain.parties.Customer;
import hotelsoftware.domain.users.User;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 * @author mohi
 */
public class Invoice
{

    private String invoiceNr;
    private BigDecimal discount;
    private Date expiration;
    private boolean fulfilled;
    private Date created;
    private PaymentMethod paymentMethod;
    private Customer Customer;
    private User user;
    private DBInvoices model;
    private Collection<InvoiceItem> items;

    public Invoice(String invoiceNr, BigDecimal discount, Date expiration,
            boolean fulfilled, Date created, PaymentMethod paymentMethod,
            Customer customer, User user)
    {
        this.invoiceNr = invoiceNr;
        this.discount = discount;
        this.expiration = expiration;
        this.fulfilled = fulfilled;
        this.created = created;
        this.paymentMethod = paymentMethod;
        this.Customer = customer;
        this.user = user;
        this.items = new LinkedList<InvoiceItem>();
    }
    
    public Collection<InvoiceItem> getItems()
    {
        return items;
    }

    public DBInvoices getModel()
    {
        return model;
    }

    public Customer getCustomer()
    {
        return Customer;
    }

    public Date getCreated()
    {
        return created;
    }

    public BigDecimal getDiscount()
    {
        return discount;
    }

    public Date getExpiration()
    {
        return expiration;
    }

    public boolean isFulfilled()
    {
        return fulfilled;
    }

    public String getInvoiceNr()
    {
        return invoiceNr;
    }

    public PaymentMethod getPaymentMethod()
    {
        return paymentMethod;
    }

    public User getUser()
    {
        return user;
    }

    public String getPaymentMethodName()
    {
        return paymentMethod.getMethod();
    }

    /**
     * Communicates with the model and returns a invoice to its number
     * @param invoicenumber
     * a unique invoicenumber
     * @return 
     */
    public static Invoice getInvoiceByInvoiceNumber(String invoicenumber)
    {
        return null;
    }
}
