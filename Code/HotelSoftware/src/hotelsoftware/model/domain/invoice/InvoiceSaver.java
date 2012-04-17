/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.invoice;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.invoice.DBInvoice;
import hotelsoftware.model.database.invoice.DBInvoiceitem;
import hotelsoftware.model.database.invoice.DBPaymentmethod;
import java.util.Collection;
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

    public void saveOrUpdate(Session session, Collection<PaymentMethod> paymentmethods, Collection<Invoice> invoices ,Collection<InvoiceItem> invoiceitems)
    {
        for(PaymentMethod method : paymentmethods)
        {
            DBPaymentmethod dbm = (DBPaymentmethod)DynamicMapper.map(method);
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
            DBInvoiceitem dbii = (DBInvoiceitem)DynamicMapper.map(item);
            session.saveOrUpdate(dbii);
            InvoiceitemsPK pk = (InvoiceitemsPK)DynamicMapper.map(dbii.getInvoiceitemsPK());
            item.setInvoiceitemsPK(pk);
        }
    }
}
