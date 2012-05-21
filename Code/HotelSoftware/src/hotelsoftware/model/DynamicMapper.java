package hotelsoftware.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Diese Klasse mapt Objekte zwischen den Schichten
 *
 * @author Johannes
 */
public class DynamicMapper
{
    /**
     * Mapt Objekte zwischen zwei Schichten (Hibernate und Domain) hin und her
     *
     * @param urObject Das Objekt der Schicht von der gemappt werden soll
     *
     *
     * Falls die Objekte nicht zueinander passen werden leere Objekte
     * zurückgegeben
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
        return map(urObject, new HashMap());
    }

    
    /**
     * private map Methode mit der HashMap die verwendet wird um zu schauen ob Objekte schon gemappt wurden.
     * @param urObject
     * @param mappedObjects
     * @return 
     */
    private static Object map(Object urObject, HashMap mappedObjects)
    {
        if (mappedObjects != null)
        {
            try
            {
                //ein neues Objekt der Klasse von der anderen Schicht erzeugen.
                Class newClass = Class.forName(convertClassName(urObject.getClass().getName()));
                Object returnvalue = newClass.newInstance();

                
                //das neue und alte Objekt in die Hashmap packen
                mappedObjects.put(urObject, returnvalue);

                //über alle Methoden der neuen Klasse durchiterieren. Die getMethods Methode hat den Vorteil im vergleich zu getDeclaredMethods das sie auch superklassen berücksichtigt.
                for (Method targetSetterMethod : returnvalue.getClass().getMethods())
                {
                    //nur Methoden die mit "set" beginnen berücksichtigen, also nur Setter
                    if (targetSetterMethod.getName().startsWith("set"))
                    {
                        //Getter der aktuellen Schicht holen der zum Setter der neuen Schicht passt.
                        Method actuallGetterMethod = getMethod(targetSetterMethod, urObject);

                        if (actuallGetterMethod != null)
                        {
                            //in hashmap schauen ob Objekt vom Getter vom Urobjekt als key vorhanden ist und setter invoken
                            Object val = actuallGetterMethod.invoke(urObject);
                            if (mappedObjects.containsKey(val))
                            {
                                targetSetterMethod.invoke(returnvalue, mappedObjects.get(val));
                            }
                            //falls noch nicht gemappt wurde.
                            else
                            {
                                //Falls die getter methode eine Collection zurückgibt
                                if (actuallGetterMethod.getReturnType().equals(Collection.class))
                                {
                                    //mapCollection aufrufen mit der Collection die man aus der aktuellen Getter Methode bekommt.
                                    targetSetterMethod.invoke(returnvalue, mapCollection((Collection) actuallGetterMethod.invoke(urObject), mappedObjects));
                                }
                                //Falls keine Collection
                                else
                                {
                                    //Hole aktuelles zu mappendes Objekt von der Gettermethode
                                    Object o = actuallGetterMethod.invoke(urObject);
                                    if (o != null)
                                    {
                                        //Falls das Objekt eines von uns ist
                                        if (o.getClass().getName().startsWith("hotelsoftware"))
                                        {
                                            //das Objekt muss gemappt werden.
                                            o = map(o, mappedObjects);
                                        }
                                        //Via SetterMethode übergeben
                                        targetSetterMethod.invoke(returnvalue, o);
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
                //e.printStackTrace();
                return null;
            }
        }
        else
        {
            //System.out.println(counter);
        }
        return null;
    }

    /**
     * Konvertiert den vollwertigen (mit Package) Klassennamen nach Namenskonvention um
     *
     * @param name
     * Vollwertiger Name der Klasse
     * @return
     * Vollwertiger Name der Klasse in einem anderen Package
     */
    private static String convertClassName(String name)
    {
        final String PRE = "DB";

        int index = name.lastIndexOf('.') + 1;
        String pkg = name.substring(0, index);
        String cls = name.substring(index);

        if (cls.startsWith(PRE))
        {
            return pkg.replaceAll(".database.", ".domain.") + cls.substring(
                    PRE.length());
        }
        else
        {
            return pkg.replaceAll(".domain.", ".database.") + PRE + cls;
        }
    }

    /**
     * Mappt Collections dynamisch
     *
     * @param urCollection
     * die Collection die auf eine andere gemappt werden muss
     * @return
     * eine neue Collection
     */
    public static Collection mapCollection(Collection urCollection)
    {
        return mapCollection(urCollection, new HashMap());
    }

    /**
     * Mappt Collections dynamisch um, atm noch mit Rekursiostiefe, da indirekte
     * Rekursion auftreten kann
     *
     * @param urCollection
     * Die Collection die gemappt werden muss
     * @param counter
     * Die Rekursionstiefe
     * @return
     * eine neue Collection
     */
    private static Collection mapCollection(Collection urCollection, HashMap mappedObjects)
    {
        if (mappedObjects != null)
        {
            try
            {
                Collection returnValue = new LinkedHashSet();

                for (Object obj : urCollection)
                {
                    if (mappedObjects.containsKey(obj))
                    {
                        returnValue.add(mappedObjects.get(obj));
                    }
                    else
                    {
                        Object i = map(obj, mappedObjects);
                        returnValue.add(i);
                    }
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

    /**
     * Diese Methode mapt alle Attribute des Objekts A auf das Objekt B, wobei nur Werte überschrieben
     * werden, die nicht NULL sind.
     *
     * @param <T> Klasse der zu mappenden Objekte
     * @param mappingObject Objekt mit den gesetzten Attributen
     * @param mapToObject Objekt mit den nicht gesetzten Attributen
     * @return ein Objekt, bei dem alle Attribute gesetzt sind, die verfügbar waren
     */
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
     * Überprüft ob die GetterMethode in einer Klasse vorhanden ist. Falls nicht
     * return diese Methode null
     *
     * @param m die Settermethode der ausgehendern Klasse
     * @param o das Objekt in deren Klasse die zu überprüfende Methode sein
     * sollte.
     * @return
     * Gibt den Methodennamen aus
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
     * public static void main(String args[]) {
     *
     * Users users1 = new Users(); users1.setPassword("Hallo Test");
     * users1.setUsername("Chris"); User s = User.createNewUser("", ""); s =
     * (User) map(users1, s.getClass()); System.out.println(s.getPassword());
     * System.out.println(s.getUsername());
     *
     * }
     */
}
