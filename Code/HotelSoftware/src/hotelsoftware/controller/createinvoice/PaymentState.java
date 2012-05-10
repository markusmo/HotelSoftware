/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.createinvoice;

/**
 *
 * @author Dunst
 */
public class PaymentState extends CreateInvoiceState
{
    public PaymentState(CreateInvoiceController context)
    {
        super(context);
    }
    
    @Override
    void pay()
    {
        if (context.getOpenItems().size() > 0)
        {
            context.setSelectedItems(context.getOpenItems());
            context.setState(new SplitInvoiceState(context));
        }
        else
        {
            context.setState(new SearchState(context));
        }
    }
    
    @Override
    void back()
    {
        context.setState(new SelectCustomerState(context));
    }
}
