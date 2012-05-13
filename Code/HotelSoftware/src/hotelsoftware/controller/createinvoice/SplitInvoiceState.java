/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.createinvoice;

import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;

/**
 *
 * @author Dunst
 */
public class SplitInvoiceState extends CreateInvoiceState
{

    @Override
    Collection<HabitationData> getHabitations()
    {
        // TODO implement
        return super.getHabitations();
    }
    
    
    public SplitInvoiceState(CreateInvoiceController context)
    {
        super(context);
    }
    
    @Override
    void selectItems(Collection<InvoiceItemData> items)
    {
        context.setSelectedItems(HelperFunctions.castCollectionDown(items, InvoiceItemData.class, InvoiceItem.class));
    }
    
    @Override
    void cancelItems(InvoiceItemData item, int amount)
    {
        InvoiceItem ii = (InvoiceItem)item;
        ii.remove(amount);
    }
}
