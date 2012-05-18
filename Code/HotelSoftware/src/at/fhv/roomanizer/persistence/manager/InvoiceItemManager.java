package at.fhv.roomanizer.persistence.manager;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.invoice.InvoiceItem;
import at.fhv.roomanizer.persistence.ObjectConverter;
import at.fhv.roomanizer.persistence.entity.invoice.InvoiceItemEntity;

public class InvoiceItemManager extends Manager implements IInvoiceItemManager
{
    protected InvoiceItemManager(Session session)
    {
        super(session);
    }
    private static InvoiceItemManager _instance;

    /**
     * Returns the singleton-instance of the InvoiceItemManager
     *
     * @param session Session, which should be used to retrieve/store data in the database
     * @return singleton instance of the InvoiceItemManager
     */
    public static InvoiceItemManager getInstance(Session session)
    {
        if (_instance == null)
        {
            _instance = new InvoiceItemManager(session);
        }

        return _instance;
    }

    /**
     * Stores a InvoiceItem in the database
     *
     * @param habitation, which should be stored in the database
     */
    @Override
    public void saveInvoiceItem(InvoiceItem invoiceItem) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Transaction tx = _session.beginTransaction();
        _session.merge(ObjectConverter.ConvertDomainToHibernate(invoiceItem, new HashMap<Object, Object>()));
        _session.flush();
        tx.commit();
    }

    @Override
    public InvoiceItem getHabitationInvoiceItem(Habitation habitation) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Query invoiceQuery = _session.createQuery("from InvoiceItemEntity where idServices = :habitationID and idHabitations = :habitationID");
        invoiceQuery.setMaxResults(1);
        invoiceQuery.setInteger("habitationID", habitation.getId());

        @SuppressWarnings("unchecked")
        List<InvoiceItemEntity> tmpList = invoiceQuery.list();


        if (tmpList.size() != 1)
        {
            return null;
        }

        return (InvoiceItem) ObjectConverter.ConvertHibernateToDomain(tmpList.get(0), new HashMap<Object, Object>());
    }
}
