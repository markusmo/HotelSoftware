package hotelsoftware.model.domain.invoice;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.invoice.DBInvoice;
import hotelsoftware.model.database.invoice.DBPaymentMethod;
import hotelsoftware.model.domain.service.IHabitation;
import hotelsoftware.util.HibernateUtil;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Fassade die das Package Invoice managed, alle relevanten Methoden sind auf dieser abgebildet.
 *
 * @author mohi
 */
public class InvoiceFacade
{
    private InvoiceFacade()
    {
    }

    public static InvoiceFacade getInstance()
    {
        return InvoiceFacadeHolder.INSTANCE;
    }

    private static class InvoiceFacadeHolder
    {
        private static final InvoiceFacade INSTANCE = new InvoiceFacade();
    }
    
    static int getHighestInvoiceId()
    {
        return DBInvoice.getHighestId();
    }

    /**
     * Sucht eine Rechnung nach der Rechungsnummer und gibt diese aus, wenn vorhanden.
     *
     * @param invoicenumber
     * die Rechnungsnummer, nach der man sucht.
     * @return
     * die Rechung.
     */
    public Invoice getInvoiceByInvoiceNumber(String invoicenumber)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        DBInvoice retInvoices = (DBInvoice) session.createCriteria(DBInvoice.class)
                .add(Restrictions.eq("invoiceNumber",invoicenumber))
                .uniqueResult();
        
        return (Invoice) DynamicMapper.map(retInvoices);
    }

    /**
     * Gibt alle Zahlungsmethoden aus.
     *
     * @return
     * Eine Collection aus allen Zahlungsmethoden
     */
    public Set<PaymentMethod> getAllPaymentMethods()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        List<DBPaymentMethod> retList = session.createCriteria(DBPaymentMethod.class).list();
        
        return new LinkedHashSet<PaymentMethod>(DynamicMapper.mapCollection(retList));
    }

    /**
     * Gibt eine spezifische Zahlungsmethode nach Namen aus.
     *
     * @param name
     * Der Name der Zahlungsmethode.
     * @return
     * Die spezifische Zahlungsmethode.
     */
    public PaymentMethod getPaymentMethodByName(String name)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        DBPaymentMethod ret = (DBPaymentMethod) session.createCriteria(DBPaymentMethod.class)
                .add(Restrictions.eq("method", name))
                .uniqueResult();
        
        return (PaymentMethod)DynamicMapper.map(ret);
    }
}
