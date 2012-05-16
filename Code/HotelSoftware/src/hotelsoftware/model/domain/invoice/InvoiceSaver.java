package hotelsoftware.model.domain.invoice;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.invoice.DBInvoice;
import hotelsoftware.model.database.invoice.DBInvoiceItem;
import hotelsoftware.model.database.invoice.DBPaymentMethod;
import java.util.Collection;
import java.util.Set;
import org.hibernate.Session;

/**
 * Zum Speichern von Services in die Datenbank.
 *
 * @author mohi
 */
public class InvoiceSaver {

    private InvoiceSaver() {
    }

    public static InvoiceSaver getInstance() {
        return InvoiceSaverHolder.INSTANCE;
    }

    private static class InvoiceSaverHolder {

        private static final InvoiceSaver INSTANCE = new InvoiceSaver();
    }

    /**
     * Diese Methode speichert oder erneuert alle Zahlungsmethoden, Rechungen
     * und Rechnungspositionen, die neu erstellt oder bearbeitet wurden.
     *
     * @param session Die Hibernate Session, auf der das saveOrUpdate()
     * ausgefuehrt werden soll
     * @param paymentmethods Alle neuen/bearbeiteten/vorhandenen
     * Zahlungsmethoden
     * @param invoices Alle neuen/bearbeiteten/vorhandenen Rechungen
     * @param invoiceitems Alle neuen/bearbeiteten/vorhandenen
     * Rechungspositionen
     */
    public void saveOrUpdate(Session session, Collection<IPaymentMethod> paymentmethods, Collection<IInvoice> invoices , Collection<IInvoiceItem> invoiceitems)
    {
        if (paymentmethods != null)
        {
            for(IPaymentMethod method : paymentmethods)
            {
                DBPaymentMethod dbm = (DBPaymentMethod)DynamicMapper.map(method);
                session.merge(dbm);
                method.setId(dbm.getId());
            }
        }
        
        if (invoices != null)
        {
            for(IInvoice invoice : invoices)
            {
                DBInvoice dbi = (DBInvoice)DynamicMapper.map(invoice);
                session.saveOrUpdate(session.merge(dbi));
                invoice.setId(dbi.getId());
                
            }
        }
        
        if (invoiceitems != null)
        {
            for(IInvoiceItem item : invoiceitems)
            {
                DBInvoiceItem dbii = (DBInvoiceItem)DynamicMapper.map(item);
                if (dbii.getId() == null)
                {
                    session.saveOrUpdate(dbii);
                    item.setId(dbii.getId());
                }
                else
                {
                    DBInvoiceItem newii = (DBInvoiceItem)session.merge(dbii);
                    session.update(newii);
                }
            }
        }
    }
}
