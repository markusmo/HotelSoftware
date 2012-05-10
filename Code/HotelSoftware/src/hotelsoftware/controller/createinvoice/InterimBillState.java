/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.createinvoice;

import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.service.Habitation;
import java.util.LinkedList;

/**
 *
 * @author Dunst
 */
public class InterimBillState extends CreateInvoiceState
{
    public InterimBillState(CreateInvoiceController context)
    {
        super(context);
        
        if (context.getSelectedItems() == null)
        {
            context.setSelectedItems(context.getOpenItems());
        }
    }
    
    @Override
    void next()
    {
        context.setState(new PaymentState(context));
    }
    
    @Override
    void splitInvoice()
    {
        context.setState(new SplitInvoiceState(context));
    }
    
    @Override
    void back()
    {
        context.setState(new SearchState(context));
    }
}
