package hotelsoftware.domain.invoice;

import hotelsoftware.database.Exceptions.FailedToSaveToDatabaseException;
import hotelsoftware.database.model.Invoices;
import hotelsoftware.domain.parties.Customer;
import hotelsoftware.domain.users.User;
import java.math.BigDecimal;
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
    private Invoices model;
    private LinkedList<InvoiceItem> items;

    private Invoice(String invoiceNr, BigDecimal discount, Date expiration,
            boolean fulfilled, Date created, Invoices invoice,
            PaymentMethod paymentMethod,
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

    private Invoice(String invoiceNr, BigDecimal discount, Date expiration,
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

    public static Invoice createNewInvoice(String invoiceNr, BigDecimal discount,
            Date expiration,
            boolean fulfilled, Date created, PaymentMethod paymentMethod,
            Customer customer, User user)
    {
        return new Invoice(invoiceNr, discount, expiration, fulfilled, created,
                paymentMethod, customer, user);
    }

    public LinkedList<InvoiceItem> getItems()
    {
        return items;
    }

    public Invoices getModel()
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
        //Exceptionhandling
        Invoices retInvoice = Invoices.getInvoiceByInvoiceNumber(invoicenumber);

        return new Invoice(retInvoice.getInvoiceNumber(),
                retInvoice.getDiscount(), retInvoice.getExpiration(),
                retInvoice.getFulfilled(), retInvoice.getCreated(), retInvoice,
                new PaymentMethod(retInvoice.getIdpaymentMethods()),
                new Customer(retInvoice.getIdCustomers()),
                new User(retInvoice.getIdUsers()));
    }

    /**
     * saves a invoice to the database
     * @param invoice 
     * the invoice to be saved
     */
    public static void saveInvoice(Invoice invoice)
    {
        try
        {
            Invoices invoiceToSave = new Invoices(invoice.getInvoiceNr(),
                    invoice.getDiscount(), invoice.getExpiration(),
                    invoice.isFulfilled(),
                    invoice.getCreated(), invoice.getPaymentMethod().getModel(),
                    invoice.getUser().getModel(),
                    invoice.getCustomer().getCostumerModel());
            Invoices.saveInvoice(invoiceToSave);
        } catch (FailedToSaveToDatabaseException ex)
        {
            //failed to save
        }
    }
}
