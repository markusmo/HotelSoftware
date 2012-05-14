/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.createinvoice;

import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.login.LoginController;
import hotelsoftware.model.domain.invoice.Invoice;
import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.invoice.InvoiceSaver;
import hotelsoftware.model.domain.invoice.PaymentMethod;
import hotelsoftware.model.domain.parties.Customer;
import hotelsoftware.util.HelperFunctions;
import hotelsoftware.util.HibernateUtil;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Dunst
 */
public class PaymentState extends CreateInvoiceState
{
    public PaymentState(CreateInvoiceController context)
    {
        super(context);
    }
    
    @Override
    void pay()
    {
        Invoice invoice = new Invoice();
        invoice.setCreated(new Date());
        invoice.setCustomer((Customer) context.getCustomerData());
        invoice.setDiscount(BigDecimal.ZERO);
        invoice.setExpiration(new Date()); //Barzahlung --> sofort
        invoice.setFulfilled(Boolean.TRUE);
        invoice.setInvoiceItems(HelperFunctions.castCollectionDown(context.getChosenItems(), InvoiceItemData.class, InvoiceItem.class));
        invoice.setPaymentMethod(PaymentMethod.getPaymentMethodByName("Cash"));
        invoice.setUser(LoginController.getInstance().getCurrentUser());
        invoice.setInvoiceNumber(HelperFunctions.getNewContinousNumber(Invoice.class));
        
        for (InvoiceItem ii : invoice.getInvoiceItems())
        {
            ii.setInvoice(invoice);
            System.out.println(ii.getId());
        }
        
        LinkedList<Invoice> invoices = new LinkedList<Invoice>();
        invoices.add(invoice);
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        
        InvoiceSaver.getInstance().saveOrUpdate(session, null, invoices, invoice.getInvoiceItems());
        
        ts.commit();
        
        context.setSplittedItems(new LinkedList<InvoiceItem>());
        
        if (context.getOpenItems().size() > 0)
        {
            context.setSelectedItems(context.getOpenItems());
            context.setState(new SplitInvoiceState(context));
        }
        else
        {
            context.clear();
            context.setState(new SearchState(context));
        }
    }
    
    @Override
    void back()
    {
        context.setState(new SelectCustomerState(context));
    }
}
