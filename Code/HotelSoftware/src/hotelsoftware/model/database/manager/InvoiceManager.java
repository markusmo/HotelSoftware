package hotelsoftware.model.database.manager;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.invoice.DBInvoice;
import hotelsoftware.model.database.invoice.DBInvoiceItem;
import hotelsoftware.model.database.invoice.DBPaymentMethod;
import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.model.domain.invoice.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;

/**
 * Fassade die das Package Invoice managed, alle relevanten Methoden sind auf dieser abgebildet.
 *
 * @author mohi
 */
public class InvoiceManager extends Manager
{
    private InvoiceManager()
    {
    }

    public static InvoiceManager getInstance()
    {
        return InvoiceFacadeHolder.INSTANCE;
    }

    private static class InvoiceFacadeHolder
    {
        private static final InvoiceManager INSTANCE = new InvoiceManager();
    }

    /**
     * Gibt die höchste ID aus der Datenbank aus
     *
     * @return die höchste ID aus der Datenbank
     */
    public int getHighestInvoiceId()
    {
        startTransaction();

        String query = "Select max(id) FROM invoices i";
        SQLQuery sqlquery = getSession().createSQLQuery(query);

        Integer bd = (Integer) sqlquery.uniqueResult();

        commit();

        if (bd != null)
        {
            return bd;
        }
        else
        {
            return 0;
        }
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
        startTransaction();
        DBInvoice retInvoices = (DBInvoice) getSession().createCriteria(DBInvoice.class).add(Restrictions.eq("invoiceNumber", invoicenumber)).uniqueResult();
        commit();

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
        startTransaction();
        List<DBPaymentMethod> retList = getSession().createCriteria(DBPaymentMethod.class).list();
        commit();

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
        startTransaction();
        DBPaymentMethod ret = (DBPaymentMethod) getSession().createCriteria(DBPaymentMethod.class).add(Restrictions.eq("method", name)).uniqueResult();
        commit();

        return (PaymentMethod) DynamicMapper.map(ret);
    }

    /**
     * Gibt alle Rechnungspositionen für einen Aufenthalt aus
     *
     * @param habitation der Aufenthalt, nach dem gesucht wird und auf den die Rechungspositionen zeigen
     * @return Rechnungspostitionen für einen Aufenthalt
     * @throws HibernateException Wenn die Transaktion fehlschlägt wird dieser Fehler geworfen.
     */
    public Set<IInvoiceItem> getInvoiceItemsByHabitation(DBHabitation habitation) throws HibernateException
    {
        startTransaction();
        List<DBInvoiceItem> retList = getSession().createCriteria(DBInvoiceItem.class).add(
                Restrictions.eq("idHabitations", habitation)).list();
        commit();

        return new LinkedHashSet<IInvoiceItem>(DynamicMapper.mapCollection(retList));
    }

    /**
     * Speichert eine Rechnung in die Datenbank
     *
     * @param invoice Die Rechnung, die gespeichert werden soll
     */
    public void saveInvoice(IInvoice invoice)
    {
        DBInvoice dbi = (DBInvoice) DynamicMapper.map(invoice);
        Integer id = (Integer) getSession().save(getSession().merge(dbi));
        invoice.setId(id);
        dbi.setId(id);

        for (IInvoiceItem item : invoice.getInvoiceItems())
        {
            DBInvoiceItem dbii = (DBInvoiceItem) DynamicMapper.map(item);
            dbii.setInvoice(dbi);

            if (dbii.getId() == null)
            {
                getSession().saveOrUpdate(dbii);
                item.setId(dbii.getId());
            }
            else
            {
                DBInvoiceItem newii = (DBInvoiceItem) getSession().merge(dbii);
                getSession().update(newii);
            }
        }
    }

    /**
     * Speicher Rechunspositionen
     *
     * @param items Rechnungspositionen, die zu speichern sind
     */
    public void saveInvoiceItems(Collection<IInvoiceItem> items)
    {
        for (IInvoiceItem item : items)
        {
            DBInvoiceItem dbii = (DBInvoiceItem) DynamicMapper.map(item);
            if (dbii.getId() == null)
            {
                Integer id = (Integer) getSession().save(getSession().merge(dbii));
                item.setId(id);
            }
            else
            {
                DBInvoiceItem newii = (DBInvoiceItem) getSession().merge(dbii);
                getSession().update(newii);
            }
        }
    }
}
