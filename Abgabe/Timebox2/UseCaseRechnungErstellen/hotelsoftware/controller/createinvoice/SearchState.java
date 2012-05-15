/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.createinvoice;

import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.IHabitation;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;

/**
 *
 * @author Dunst
 */
public class SearchState extends CreateInvoiceState
{
    public SearchState(CreateInvoiceController context)
    {
        super(context);
    }
    
    @Override
    public Collection<HabitationData> search(String firstName, String lastName, String roomNr)
    {
        return HelperFunctions.castCollectionUp(Habitation.searchHabitations(firstName, lastName, roomNr), HabitationData.class, Habitation.class);
    }
    
    @Override
    public void selectHabitations(Collection<HabitationData> habitations)
    {
        context.setHabitations(HelperFunctions.castCollectionDown(habitations, HabitationData.class, IHabitation.class));
    }
    
    @Override
    public void next()
    {
        context.setState(new InterimBillState(context));
    }
}
