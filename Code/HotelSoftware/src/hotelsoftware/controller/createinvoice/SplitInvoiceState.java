/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.createinvoice;

import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.controller.login.LoginController;
import hotelsoftware.model.domain.invoice.IInvoiceItem;
import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.IHabitation;
import hotelsoftware.model.domain.service.Service;
import hotelsoftware.model.domain.users.Permission;
import hotelsoftware.support.PermissionNotFoundException;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dunst
 */
public class SplitInvoiceState extends CreateInvoiceState
{
    @Override
    Collection<HabitationData> getHabitations()
    {
        return HelperFunctions.castCollectionUp(context.getHabitations(), HabitationData.class, IHabitation.class);
    }

    public SplitInvoiceState(CreateInvoiceController context)
    {
        super(context);
    }

    @Override
    void selectItems(Map<InvoiceItemData, Integer> items)
    {
        Collection<IInvoiceItem> col = new LinkedList<IInvoiceItem>();
        
        for (Entry<InvoiceItemData, Integer> entry : items.entrySet())
        {
            if (entry.getValue().equals(entry.getKey().getAmount()))
            {
                col.add((IInvoiceItem) entry.getKey());
            }
            else
            {
                InvoiceItem newItem = new InvoiceItem();
                newItem.setAmount(entry.getValue());
                newItem.setCreated(entry.getKey().getCreated());
                newItem.setService((Service)entry.getKey().getServiceData());
                newItem.setUser(LoginController.getInstance().getCurrentUser());
                
                col.add(newItem);
                
                InvoiceItem oldItem = (InvoiceItem) entry.getKey();
                //TODO testen ob das gespeichert wird
                oldItem.setAmount(oldItem.getAmount() - entry.getValue());
                
                oldItem.getHabitation().addInvoiceItems(newItem);
            }
        }
        
        context.setSelectedItems(col);
    }

    @Override
    boolean cancelItems(InvoiceItemData item, int amount)
    {
        try
        {
            if (LoginController.getInstance().getCurrentUser().hasPermission(Permission.getPermissionByName("CancelItems")))
            {
                InvoiceItem ii = (InvoiceItem) item;
                ii.remove(amount);
                
                return true;
            }
        }
        catch (PermissionNotFoundException ex)
        {
            ex.printStackTrace();
            Logger.getLogger(SplitInvoiceState.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public void next()
    {
        context.setState(new InterimBillState(context));
    }
}
