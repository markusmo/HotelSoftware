/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.adapter;

import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.invoice.InvoiceItem;
import at.fhv.roomanizer.persistence.manager.IInvoiceItemManager;
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.manager.InvoiceManager;
import hotelsoftware.util.HibernateUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Dunst
 */
public class InvoiceItemManagerAdapter implements IInvoiceItemManager
{
    private InvoiceItemManagerAdapter()
    {
    }
    
    private static InvoiceItemManagerAdapter INSTANCE;

    public static InvoiceItemManagerAdapter getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new InvoiceItemManagerAdapter();
        }

        return INSTANCE;
    }

    @Override
    public InvoiceItem getHabitationInvoiceItem(Habitation habitation) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Query invoiceQuery = session.createQuery("from DBInvoiceItem where idServices = :habitationID and idHabitations = :habitationID");
        invoiceQuery.setMaxResults(1);
        invoiceQuery.setInteger("habitationID", habitation.getId());

        InvoiceItemAdapter adapter = new InvoiceItemAdapter();
        adapter.setOurType((hotelsoftware.model.domain.invoice.InvoiceItem)DynamicMapper.map(invoiceQuery.uniqueResult()));
        return adapter;
    }

    @Override
    public void saveInvoiceItem(InvoiceItem invoiceItem) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        List<hotelsoftware.model.domain.invoice.IInvoiceItem> items = new LinkedList<hotelsoftware.model.domain.invoice.IInvoiceItem>();
        items.add((hotelsoftware.model.domain.invoice.IInvoiceItem)((Adapter)invoiceItem).getOurType());
        InvoiceManager.getInstance().saveInvoiceItems(items, session);
        t.commit();
    }
    
}
