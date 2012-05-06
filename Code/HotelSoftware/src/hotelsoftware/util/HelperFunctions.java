package hotelsoftware.util;

import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.model.domain.service.Habitation;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Behelftklasse, die Dynamisch Collections mapt
 * @author Dunst
 */
public class HelperFunctions<T, U extends T>
{
    public HelperFunctions()
    {
    }

    /**
     * Mapt eine Collection auf eine andere. Wird genutzt um die Data Interfaces and die 
     * GUI weiter zu geben.
     * Verwendung:
     * <code>Set<UserData> test = new HelperFunctions<UserData, User>().castCollectionUp(new LinkedList<User>());</code>
     * @param col
     * Die Collection die gemappt werden soll
     * @return 
     * eine neue Collection
     */
    public Collection<T> castCollectionUp(Collection<U> col)
    {
        Collection<T> newCol = new LinkedHashSet<T>();
        if (col != null)
        {
            for (U u : col)
            {
                newCol.add(u);
            }
        }

        return newCol;
    }

    public Collection<U> castCollectionDown(Collection<T> col)
    {
        Collection<U> newCol = new LinkedHashSet<U>();
        if (col != null)
        {
            for (T t : col)
            {
                newCol.add((U)t);
            }
        }

        return newCol;
    }

   
}
