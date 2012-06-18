/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.createinvoice;

import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.login.LoginController;
import hotelsoftware.model.domain.invoice.*;
import hotelsoftware.model.domain.parties.ICustomer;
import hotelsoftware.util.HelperFunctions;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;

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
        IInvoice invoice = context.getCurrentInvoice();
        invoice.setCreated(new Date());
        invoice.setCustomer((ICustomer) context.getCustomerData());
        invoice.setDiscount(BigDecimal.ZERO);
        invoice.setExpiration(new Date()); //Barzahlung --> sofort
        invoice.setFulfilled(Boolean.TRUE);
        invoice.setInvoiceItems(HelperFunctions.castCollectionDown(context.getChosenItems(), InvoiceItemData.class, IInvoiceItem.class));
        invoice.setPaymentMethod(PaymentMethod.getPaymentMethodByName("Cash"));
        invoice.setUser(LoginController.getInstance().getCurrentUser());
        invoice.setInvoiceNumber(HelperFunctions.getNewContinousNumber(Invoice.class));
        
        for (IInvoiceItem ii : invoice.getInvoiceItems())
        {
            ii.setInvoice(invoice);
        }
        
        LinkedList<IInvoice> invoices = new LinkedList<IInvoice>();
        invoices.add(invoice);
        
        context.saveInvoice(invoice);
        
        context.setSplittedItems(new LinkedList<IInvoiceItem>());
        
        if (context.getOpenItems().size() > 0)
        {
            context.setCurrentInvoice(new Invoice());
            context.getCurrentInvoice().setInvoiceItems(context.getOpenItems());
            context.setState(new InterimBillState(context));
        }
        else
        {
            context.clear();
            context.setState(new SearchState(context));
        }
    }
    
    @Override
    void back()
    {
        context.setState(new SelectCustomerState(context));
    }
}
