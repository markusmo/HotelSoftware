/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.checkin;

import hotelsoftware.model.domain.service.IHabitation;
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
    Collection<IHabitation> getHabitationsOverview()
    {
        return context.getHabitations();
    }
}
