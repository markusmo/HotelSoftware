/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.IHabitation;
import at.fhv.roomanizer.persistence.manager.IHabitationManager;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dunst
 */
public class HabitationManagerAdapter implements IHabitationManager
{

    @Override
    public List<IHabitation> getAllHabitations() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IHabitation getHabitationById(int habitationID) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<IHabitation> getHabitationsByDate(Date date) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveHabitation(IHabitation habitation) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
