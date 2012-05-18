/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhv.roomanizer.persistence.manager;

import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.IHabitation;
import at.fhv.roomanizer.domain.invoice.IInvoiceItem;
import at.fhv.roomanizer.domain.invoice.InvoiceItem;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Dunst
 */
public interface IInvoiceItemManager
{

    InvoiceItem getHabitationInvoiceItem(Habitation habitation) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException;

    /**
     * Stores a InvoiceItem in the database
     * @param habitation, which should be stored in the database
     */
    void saveInvoiceItem(InvoiceItem invoiceItem) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException;
    
}
