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
    }
    
    @Override
    public void next()
    {
        context.setState(new PaymentState(context));
    }
    
    //@Override
    public void splitInvoice()
    {
        context.setState(new SplitInvoiceState(context));
    }
    
    @Override
    public void back()
    {
        
    }
}
