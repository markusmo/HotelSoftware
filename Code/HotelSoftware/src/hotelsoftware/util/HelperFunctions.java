/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.util;

import java.util.Collection;
import java.util.LinkedList;

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
    //Collection<UserData> test = new HelperFunctions<UserData, User>().castCollectionUp(new LinkedList<User>());
    public Collection<T> castCollectionUp(Collection<U> col)
    {
        Collection<T> newCol = new LinkedList<T>();
        
        for (U u : col)
        {
            newCol.add(u);
        }
        
        return newCol;
    }
}