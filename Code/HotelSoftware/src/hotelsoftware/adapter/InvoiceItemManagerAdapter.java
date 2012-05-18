/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.IHabitation;
import at.fhv.roomanizer.domain.invoice.IInvoiceItem;
import at.fhv.roomanizer.domain.invoice.InvoiceItem;
import at.fhv.roomanizer.persistence.manager.IInvoiceItemManager;
import hotelsoftware.model.domain.invoice.InvoiceSaver;
import hotelsoftware.util.HibernateUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Dunst
 */
public class InvoiceItemManagerAdapter implements IInvoiceItemManager
{

    @Override
    public InvoiceItem getHabitationInvoiceItem(Habitation habitation) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveInvoiceItem(InvoiceItem invoiceItem) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        List<hotelsoftware.model.domain.invoice.IInvoiceItem> items = new LinkedList<hotelsoftware.model.domain.invoice.IInvoiceItem>();
        items.add((hotelsoftware.model.domain.invoice.IInvoiceItem)((Adapter)invoiceItem).getOurType());
        InvoiceSaver.getInstance().saveOrUpdate(session, null, null, items);
        t.commit();
    }
    
}
