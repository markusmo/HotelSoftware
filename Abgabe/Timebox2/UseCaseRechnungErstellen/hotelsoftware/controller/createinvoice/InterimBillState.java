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
        //FIXME Verursacht Probleme, wenn zurück geganben wird und eine neue Zwischenrechnung generiert wird
        if (context.getSelectedItems() == null)
        {
            context.setSelectedItems(context.getOpenItems());
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
        context.setState(new SearchState(context));
    }
}
