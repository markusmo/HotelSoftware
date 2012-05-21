package hotelsoftware.util;

import hotelsoftware.adapter.Adapter;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Behelftklasse, die Dynamisch Collections mapt
 *
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
     *
     * @param col
     * Die Collection die gemappt werden soll
     * @return
     * eine neue Collection
     */
    @Deprecated
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

    /**
     * castCollectionUp v2. Mapt eine Collection auf eine andere. Wird genutzt um die Data Interfaces and die
     * GUI weiter zu geben. Statisch aufrufbar
     *
     * @param <T> diese Klasse erweitert U
     * @param <U> diese Klasse ist die super-Klasse von T
     * @param col die Collection, die gemappt werden sollte
     * @param cls1 die Klasse auf die gemapt wird
     * @param cls2 die innere Klasse der Collection, die übergeben wird
     * @return eine neue Collection mit der inneren Klasse T
     */
    public static <T, U extends T> Collection<T> castCollectionUp(Collection<U> col, Class<T> cls1, Class<U> cls2)
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

    /**
     * Mapt eine Collection auf eine andere. Wird genutzt um die Data Interfaces and die
     * Controller weiter zu geben.
     *
     * @param col
     * Die Collection die gemappt werden soll
     * @return
     * eine neue Collection
     */
    @Deprecated
    public Collection<U> castCollectionDown(Collection<T> col)
    {
        Collection<U> newCol = new LinkedHashSet<U>();
        if (col != null)
        {
            for (T t : col)
            {
                newCol.add((U) t);
            }
        }

        return newCol;
    }

   /**
     * castCollectionDown v2. Mapt eine Collection auf eine andere. Wird genutzt um die Data Interfaces and die
     * GUI weiter zu geben. Statisch aufrufbar
     *
     * @param <T> diese Klasse erweitert U
     * @param <U> diese Klasse ist die super-Klasse von T
     * @param col die Collection, die gemappt werden sollte
     * @param cls1 die Klasse auf die gemapt wird
     * @param cls2 die innere Klasse der Collection, die übergeben wird
     * @return eine neue Collection mit der inneren Klasse U
     */
    public static <T, U extends T> Collection<U> castCollectionDown(Collection<T> col, Class<T> cls1, Class<U> cls2)
    {
        Collection<U> newCol = new LinkedHashSet<U>();
        if (col != null)
        {
            for (T t : col)
            {
                newCol.add((U) t);
            }
        }

        return newCol;
    }

    /**
     * Liefert eine fortlaufende Nummer als String zurück, je nach Klasse
     * @param cls die Klasse, für die eine Nummer benötigt wird
     * @return eine fortlaufende Nummer
     */
    public static String getNewContinousNumber(Class cls)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
        String date = sdf.format(new Date());
        Integer id = 0;

        char prefix = cls.getName().toLowerCase().charAt(0);
        try
        {
            Method m = cls.getDeclaredMethod("getHighestId");
            id = (Integer) m.invoke(null);
        }
        catch (Exception ex)
        {
            throw new UnsupportedOperationException("Static Method getHighestId():int not implemented");
        }

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumIntegerDigits(8);
        nf.setGroupingUsed(false);

        return prefix + date + nf.format(id + 1);
    }
    
    /**
     * 
     * @param <T> Die zu adaptierende Klasse
     * @param <U> Die Adapterklasse
     * @param col Eien Collection aus zu adaptierenden Objekten
     * @param clazz Die Adapterklassen
     * @return Die adaptierte Liste
     */
    public static <T, U extends Adapter<T>>  List<U> getAdaptedList(Collection<T> col, Class<U> clazz)
    {
        LinkedList<U> list = new LinkedList<U>();
        
        for (T t : col)
        {
            try
            {
                U u = clazz.newInstance();
                u.setOurType(t);
                
                list.add(u);
            }
            catch (InstantiationException ex)
            {
                Logger.getLogger(HelperFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (IllegalAccessException ex)
            {
                Logger.getLogger(HelperFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return list;
    }
    
    public static <T, U extends Adapter<T>>  List<T> getOurList(Collection<U> col)
    {
        LinkedList<T> list = new LinkedList<T>();
        
        for (U u : col)
        {
            list.add(u.getOurType());
        }
        
        return list;
    }
    
    public static <V, T extends V, U extends Adapter<T>>  List<V> getOurList(Collection<U> col, Class<V> returnClass)
    {
        LinkedList<V> list = new LinkedList<V>();
        
        for (U u : col)
        {
            list.add(u.getOurType());
        }
        
        return list;
    }
}
