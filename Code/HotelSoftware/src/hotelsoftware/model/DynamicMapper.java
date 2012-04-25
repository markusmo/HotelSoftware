package hotelsoftware.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public static Object map(Object urObject)
    {
        return map(urObject, 10);
    }

    private static Object map(Object urObject, int counter)
    {
        if (counter > 0)
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
                                if (getterMethodNewLevel.getReturnType().equals(Collection.class))
                                {
                                    setterMethod.invoke(returnvalue, mapCollection((Collection) getterMethodNewLevel.invoke(urObject), counter - 1));
                                }
                                else
                                {
                                    Object o = getterMethodNewLevel.invoke(urObject);
                                    if (o != null)
                                    {
                                        if (o.getClass().getName().contains("hotelsoftware"))
                                        {
                                            o = map(o, counter - 1);
                                        }
                                        setterMethod.invoke(returnvalue, o);
                                    }
                                }
                            }
                        }
                    }
                }

                return returnvalue;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }
        return null;
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

    public static Collection mapCollection(Collection urCollection)
    {
        return mapCollection(urCollection, 10);
    }

    private static Collection mapCollection(Collection urCollection, int counter)
    {
        if (counter > 0)
        {
            try
            {
                Collection returnValue = new LinkedHashSet();

                for (Object obj : urCollection)
                {
                    //Class newClass = Class.forName(convertClassName(obj.getClass().getName()));
                    Object i = map(obj, counter - 1);
                    returnValue.add(i);
                }

                return returnValue;
            }
            catch (Exception e)
            {
                return null;
            }
        }

        return null;
    }

    public static <T> T mapTwoObjects(T mappingObject, T mapToObject)
    {
        for (Method setterMethod : mapToObject.getClass().getMethods())
        {
            if (setterMethod.getName().startsWith("set"))
            {
                Method getterMethodNewLevel = getMethod(setterMethod, mapToObject);

                if (getterMethodNewLevel != null)
                {
                    try
                    {
                        Method getterMethodCurrentLevel = getMethod(setterMethod, mapToObject);

                        if (getterMethodCurrentLevel != null && getterMethodCurrentLevel.invoke(mapToObject) == null)
                        {
                            setterMethod.invoke(mapToObject, getterMethodNewLevel.invoke(mappingObject));
                        }

                    }
                    catch (IllegalAccessException ex)
                    {
                        Logger.getLogger(DynamicMapper.class.getName()).log(Level.SEVERE, null, ex);
                        return null;
                    }
                    catch (IllegalArgumentException ex)
                    {
                        Logger.getLogger(DynamicMapper.class.getName()).log(Level.SEVERE, null, ex);
                        return null;
                    }
                    catch (InvocationTargetException ex)
                    {
                        Logger.getLogger(DynamicMapper.class.getName()).log(Level.SEVERE, null, ex);
                        return null;
                    }
                }
            }
        }
        
        return mapToObject;
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
