/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.checkin;

import hotelsoftware.model.domain.service.Habitation;
import java.util.Collection;

/**
 *
 * @author Dunst
 */
public class FinalState extends CheckInState
{
    public FinalState(CheckInController context)
    {
        super(context);
    }
    
    @Override
    Collection<Habitation> getHabitationsOverview()
    {
        return context.getHabitations();
    }
}
