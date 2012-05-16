package hotelsoftware.util;

import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

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
    
        public static <T, U extends T> Collection<U> castCollectionDown(Collection<T> col, Class<T> cls1, Class<U> cls2)
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
}