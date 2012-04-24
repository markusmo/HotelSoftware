/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.invoice;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.invoice.DBInvoice;
import hotelsoftware.model.database.invoice.DBInvoiceItem;
import hotelsoftware.model.database.invoice.DBPaymentMethod;
import java.util.Collection;
import java.util.Set;
import org.hibernate.Session;

/**
 *
 * @author mohi
 */
public class InvoiceSaver
{
    private InvoiceSaver()
    {
    }

    public static InvoiceSaver getInstance()
    {
        return InvoiceSaverHolder.INSTANCE;
    }

    private static class InvoiceSaverHolder
    {
        private static final InvoiceSaver INSTANCE = new InvoiceSaver();
    }

    public void saveOrUpdate(Session session, Set<PaymentMethod> paymentmethods, Set<Invoice> invoices ,Set<InvoiceItem> invoiceitems)
    {
        for(PaymentMethod method : paymentmethods)
        {
            DBPaymentMethod dbm = (DBPaymentMethod)DynamicMapper.map(method);
            session.saveOrUpdate(dbm);
            method.setId(dbm.getId());
        }
        
        for(Invoice invoice : invoices)
        {
            DBInvoice dbi = (DBInvoice)DynamicMapper.map(invoice);
            session.saveOrUpdate(dbi);
            invoice.setId(dbi.getId());
        }
        
        for(InvoiceItem item : invoiceitems)
        {
            DBInvoiceItem dbii = (DBInvoiceItem)DynamicMapper.map(item);
            session.saveOrUpdate(dbii);
            InvoiceItemPK pk = (InvoiceItemPK)DynamicMapper.map(dbii.getInvoiceitemsPK());
            item.setInvoiceitemsPK(pk);
        }
    }
}
