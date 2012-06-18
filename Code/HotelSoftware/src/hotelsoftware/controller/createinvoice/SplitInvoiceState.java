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
import hotelsoftware.model.domain.service.IHabitation;
import hotelsoftware.model.domain.users.Permission;
import hotelsoftware.support.PermissionNotFoundException;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
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
        context.getCurrentInvoice().clear();
        
        for (Entry<InvoiceItemData, Integer> entry : items.entrySet())
        {
            IInvoiceItem oldItem = (InvoiceItem) entry.getKey();
            context.getCurrentInvoice().addInvoiceItem(oldItem);
            
            if (!entry.getValue().equals(entry.getKey().getAmount()))
            {
                IInvoiceItem newItem = context.getCurrentInvoice().splitItem(oldItem, entry.getValue());
                context.addSplittedItems(newItem);
            }
        }
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
            //ex.printStackTrace();
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
