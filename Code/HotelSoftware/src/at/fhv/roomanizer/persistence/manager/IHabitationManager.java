/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhv.roomanizer.persistence.manager;

import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.IHabitation;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dunst
 */
public interface IHabitationManager
{

    /**
     * Returns all habitations stored in the database
     * @return All habitations stored in the database
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    List<Habitation> getAllHabitations() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException;

    Habitation getHabitationById(int habitationID) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException;

    /**
     * Returns all habitations which are/were active during the given date
     * @return List<Habitation>
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    List<Habitation> getHabitationsByDate(Date date) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException;

    /**
     * Stores a habitation in the database
     * @param habitation, which should be stored in the database
     */
    void saveHabitation(Habitation habitation) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException;
    
}
