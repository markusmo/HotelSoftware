/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.persistence.manager.HabitationManager;
import at.fhv.roomanizer.persistence.manager.IHabitationManager;
import hotelsoftware.util.HelperFunctions;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Dunst
 */
public class HabitationManagerAdapter implements IHabitationManager
{
    private HabitationManagerAdapter()
    {
    }
    
    
    private static HabitationManagerAdapter INSTANCE;

    /**
     * Returns the singleton-instance of the HabitationManagerAdapter
     *
     * @return
     */
    public static HabitationManagerAdapter getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new HabitationManagerAdapter();
        }

        return INSTANCE;
    }

    @Override
    public List<Habitation> getAllHabitations() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Habitation getHabitationById(int habitationID) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Habitation> getHabitationsByDate(Date date) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        return new LinkedList<Habitation>(HelperFunctions.castCollectionUp(
                HelperFunctions.getAdaptedList(hotelsoftware.model.domain.service.Habitation.getHabitationsByDate(date), HabitationAdapter.class),
                Habitation.class, HabitationAdapter.class));
    }

    @Override
    public void saveHabitation(Habitation habitation) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
