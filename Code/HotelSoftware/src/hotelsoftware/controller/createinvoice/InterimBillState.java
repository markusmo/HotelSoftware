/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.createinvoice;

/**
 *
 * @author Dunst
 */
public class InterimBillState extends CreateInvoiceState
{
    public InterimBillState(CreateInvoiceController context)
    {
        super(context);
        if (context.getCurrentInvoice().isEmpty())
        {
            context.getCurrentInvoice().setInvoiceItems(context.getOpenItems());
        }
    }
    
    @Override
    void next()
    {
        context.setState(new SelectCustomerState(context));
    }
    
    @Override
    void splitInvoice()
    {
        context.setState(new SplitInvoiceState(context));
    }
    
    @Override
    void back()
    {
        context.clear();
        context.setState(new SearchState(context));
    }
}
