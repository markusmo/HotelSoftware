/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.util;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author Dunst
 */
public class HelperFunctions<T, U extends T>
{
    public HelperFunctions()
    {
    }

    //Verwendung:
    //Set<UserData> test = new HelperFunctions<UserData, User>().castCollectionUp(new LinkedList<User>());
    public Set<T> castCollectionUp(Set<U> col)
    {
        Set<T> newCol = new LinkedHashSet<T>();
        if (col != null)
        {
            for (U u : col)
            {
                newCol.add(u);
            }
        }

        return newCol;
    }
}
