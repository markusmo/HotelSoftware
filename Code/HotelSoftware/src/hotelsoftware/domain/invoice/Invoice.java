/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.invoice;

import hotelsoftware.domain.users.User;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Invoice
{

    private static class Customer
    {
        public Customer()
        {
        }
    }
    private String invoiceNr;
    private BigDecimal discount;
    private Date expiration;
    private boolean fulfilled;
    private Date created;
    private Collection<InvoiceItem> items;
    private PaymentMethod paymentMethod;
    private Customer Customer;
}
