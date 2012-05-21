package hotelsoftware.model.domain.invoice;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.FaildToDeleteFromDatabaseException;
import hotelsoftware.model.database.FailedToSaveToDatabaseException;
import hotelsoftware.model.database.invoice.DBInvoice;
import hotelsoftware.model.database.invoice.DBInvoiceItem;
import hotelsoftware.model.database.invoice.DBPaymentMethod;
import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.model.domain.service.IHabitation;
import hotelsoftware.util.HibernateUtil;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
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
    
    public int getHighestInvoiceId()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "Select max(id) FROM invoices i";
        SQLQuery sqlquery = session.createSQLQuery(query);


        Integer bd = (Integer) sqlquery.uniqueResult();
        
        if (bd != null)
            return bd;
        else
            return 0;
    }

    /**
     * Sucht eine Rechnung nach der Rechungsnummer und gibt diese aus, wenn vorhanden.
     *
     * @param invoicenumber
     * die Rechnungsnummer, nach der man sucht.
     * @return
     * die Rechung.
     */
    public IInvoice getInvoiceByInvoiceNumber(String invoicenumber)
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
    public Set<IPaymentMethod> getAllPaymentMethods()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        List<DBPaymentMethod> retList = session.createCriteria(DBPaymentMethod.class).list();
        
        return new LinkedHashSet<IPaymentMethod>(DynamicMapper.mapCollection(retList));
    }

    /**
     * Gibt eine spezifische Zahlungsmethode nach Namen aus.
     *
     * @param name
     * Der Name der Zahlungsmethode.
     * @return
     * Die spezifische Zahlungsmethode.
     */
    public IPaymentMethod getPaymentMethodByName(String name)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        DBPaymentMethod ret = (DBPaymentMethod) session.createCriteria(DBPaymentMethod.class)
                .add(Restrictions.eq("method", name))
                .uniqueResult();
        
        return (PaymentMethod)DynamicMapper.map(ret);
    }
    
    public Set<IInvoiceItem> getInvoiceItemsByHabitation(
            DBHabitation habitation) throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        List<DBInvoiceItem> retList = session.createCriteria(DBInvoiceItem.class).add(
                Restrictions.eq("idHabitations", habitation)).list();

        return new LinkedHashSet<IInvoiceItem>(DynamicMapper.mapCollection(retList));
    }
    
    
    /**
     * Speichert die Zahlungsart in der Datenbank ab
     *
     * @param name
     * the new paymentmethod to be created
     * @throws HibernateException
     * @throws FailedToSaveToDatabaseException
     */
    public void savePaymentMethod(String name) throws FailedToSaveToDatabaseException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        try
        {
            session.save(new DBPaymentMethod(name));
            ts.commit();
        }
        catch (HibernateException e)
        {
            ts.rollback();
            throw new FailedToSaveToDatabaseException();
        }
        finally
        {
        }
    }

    /**
     * Löscht Zahlungsmethoden aus der Datenbank
     *
     * @param name
     * the name of the method to delete
     * @throws FaildToDeleteFromDatabaseException
     */
    public void deletePaymentMethod(String name) throws FaildToDeleteFromDatabaseException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        try
        {
            DBPaymentMethod method = (DBPaymentMethod) session.createCriteria(
                    DBPaymentMethod.class).add(Restrictions.like("name", name)).uniqueResult();
            session.delete(method);
        }
        catch (HibernateException ex)
        {
            ts.rollback();
            throw new FaildToDeleteFromDatabaseException();
        }
    }
}
