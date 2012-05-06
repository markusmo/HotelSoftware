/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.createinvoice;

import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.model.domain.service.Habitation;
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
        return new HelperFunctions<HabitationData, Habitation>().castCollectionUp(Habitation.search(firstName, lastName, roomNr));
    }
    
    @Override
    public void selectHabitations(Collection<HabitationData> habitations)
    {
        context.setHabitations(new HelperFunctions<HabitationData, Habitation>().castCollectionDown(habitations));
    }
    
    @Override
    public void next()
    {
        context.setState(new InterimBillState(context));
    }
}
