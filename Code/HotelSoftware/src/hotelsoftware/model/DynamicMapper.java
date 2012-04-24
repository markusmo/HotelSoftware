package hotelsoftware.model;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author Johannes
 */
public class DynamicMapper
{
    /**
     * Mapt Objekte zwischen zwei Schichten hin und her
     *
     * @param urObject Das Objekt der Schicht von der gemappt werden soll
     *
     *
     * Falls die Objekte nicht zueinander passen werden leere Objekte zurückgegeben
     *
     * Falls irgendein Fehler auftritt wird null zurückgegeben.
     *
     * Muss auf Zieltyp gecastet werden.
     *
     * Vom Zieltyp muss ein Leerer Constructor existieren und public sein
     *
     * Die Getter und Setter müssen getXYZ bzw setXYZ heißen und public sein
     */
    private static HashMap<Integer, Object> hashedObjects = new HashMap<Integer, Object>();

    public static Object map(Object urObject)
    {
        try
        {
            Class newClass = Class.forName(convertClassName(urObject.getClass().getName()));
            Object returnvalue = newClass.newInstance();

            for (Method setterMethod : returnvalue.getClass().getMethods())
            {
                if (setterMethod.getName().startsWith("set"))
                {
                    Method getterMethodNewLevel = getMethod(setterMethod, urObject);

                    if (getterMethodNewLevel != null)
                    {
                        Method getterMethodCurrentLevel = getMethod(setterMethod, returnvalue);

                        if (getterMethodCurrentLevel != null && getterMethodCurrentLevel.invoke(returnvalue) == null)
                        {
                            if (getterMethodNewLevel.getReturnType().equals(Set.class))
                            {
                                setterMethod.invoke(returnvalue, mapCollection((Set) getterMethodNewLevel.invoke(urObject)));
                            }
                            else
                            {
                                Object o = getterMethodNewLevel.invoke(urObject);
                                if (o.getClass().getName().contains("hotelsoftware"))
                                {
                                    if (hashedObjects.containsKey(o.hashCode()))
                                    {
                                        o = hashedObjects.get(o.hashCode());
                                    }
                                    else
                                    {
                                        o = map(o);
                                    }
                                }
                                setterMethod.invoke(returnvalue, o);
                            }
                        }
                    }
                }
            }
            if (returnvalue.getClass().getName().contains("hotelsoftware.model.domain"))
            {
                hashedObjects.put(returnvalue.hashCode(), returnvalue);
            }
            return returnvalue;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private static String convertClassName(String name)
    {
        final String PRE = "DB";

        int index = name.lastIndexOf('.') + 1;
        String pkg = name.substring(0, index);
        String cls = name.substring(index);

        if (cls.startsWith(PRE))
        {
            return pkg.replaceAll(".database.", ".domain.") + cls.substring(PRE.length());
        }
        else
        {
            return pkg.replaceAll(".domain.", ".database.") + PRE + cls;
        }
    }

    public static Set mapCollection(Set urCollection)
    {
        try
        {
            Set returnValue = new LinkedHashSet();

            for (Object obj : urCollection)
            {
                //Class newClass = Class.forName(convertClassName(obj.getClass().getName()));
                Object i = map(obj);
                returnValue.add(i);
            }

            return returnValue;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * Überprüft ob die GetterMethode in einer Klasse vorhanden ist. Falls nicht return diese Methode null
     *
     * @param m die Settermethode der ausgehendern Klasse
     * @param o das Objekt in deren Klasse die zu überprüfende Methode sein sollte.
     * @return
     */
    private static <T> Method getMethod(Method m, T o)
    {
        Method m2 = null;
        try
        {
            m2 = o.getClass().getMethod(m.getName().replace("set", "get"));
        }
        catch (NoSuchMethodException e)
        {
            try
            {
                m2 = o.getClass().getMethod(m.getName().replace("set", "is"));
            }
            catch (NoSuchMethodException e2)
            {
                // ignore
            }
        }
        return m2;
    }
    /*
     * public static void main(String args[])
     * {
     *
     * Users users1 = new Users();
     * users1.setPassword("Hallo Test");
     * users1.setUsername("Chris");
     * User s = User.createNewUser("", "");
     * s = (User) map(users1, s.getClass());
     * System.out.println(s.getPassword());
     * System.out.println(s.getUsername());
     *
     * }
     */
}
