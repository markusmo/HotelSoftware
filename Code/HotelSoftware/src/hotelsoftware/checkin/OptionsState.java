/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.checkin;

import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.service.ExtraService;
import hotelsoftware.model.domain.service.ExtraServiceData;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;

/**
 *
 * @author Dunst
 */
public class OptionsState extends CheckInState
{
    public OptionsState(CheckInController context)
    {
        super(context);
    }
    
    @Override
    public void initKeys()
    {
        throw new IllegalStateException();
    }
    
    @Override
    public Collection<ExtraServiceData> getServices()
    {
        Collection<ExtraService> services = ExtraService.getAllExtraServices();
        
        return new HelperFunctions<ExtraServiceData, ExtraService>().castCollectionUp(services);
    }
    
    @Override
    public void selectServices(Collection<ExtraServiceData> services)
    {
        for (ExtraServiceData entry : services)
        {
            InvoiceItem item = InvoiceItem.createInvoiceItem((ExtraService)entry, 1, habitation);
            habitation.addInvoiceItem(item);
        }
    }
}
