package hotelsoftware.util;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;

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
     * @param newObjectClass Die Klasse des Objects in die zu mappen ist.
     * 
     * Falls die Objekte nicht zueinander passen werden leere Objekte zurückgegeben
     * 
     * Falls irgendein Fehler auftritt wird null zurückgegeben.
     * 
     * Muss auf Zieltyp gecastet werden.
     * 
     * Vom Zieltyp muss ein Leerer Constructor existieren
     * 
     * Die Getter und Setter müssen getXYZ bzw setXYZ heißen und public sein
     */
    public static Object map(Object urObject)
    {
        try
        {
            Class newClass = Class.forName(convertClassName(urObject.getClass().getName()));
            Object returnvalue = newClass.newInstance();
            
            for (Method m : returnvalue.getClass().getMethods())
            {
                if (m.getName().startsWith("set"))
                {
                    Method m2 = getMethod(m, urObject);
                    
                    if (m2 != null)
                    {
                        if (m2.getReturnType().equals(Collection.class))
                        {
                            m.invoke(returnvalue, mapCollection((Collection)m2.invoke(urObject)));
                        }
                        else
                        {
                            m.invoke(returnvalue, m2.invoke(urObject));
                        }
                    }
                }
            }
            return returnvalue;
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    private static String convertClassName(String name)
    {
        int index = name.lastIndexOf('.') + 1;
        String pkg = name.substring(0, index);
        String cls = name.substring(index);
        
        if (cls.startsWith("DB"))
        {
            return "hotelsoftware.domain.users." + cls.substring("DB".length());
        }
        else
        {
            return "hotelsoftware.database.model." + "DB" + cls;
        }
    }
    
    public static Collection mapCollection(Collection urCollection)
    {
        try
        {
            Collection returnValue = new LinkedList();
            
            for (Object obj : urCollection)
            {
                //Class newClass = Class.forName(convertClassName(obj.getClass().getName()));
                returnValue.add(map(obj));
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
            // ignore
        }
        return m2;
    }
/*
    public static void main(String args[])
    {

        Users users1 = new Users();
        users1.setPassword("Hallo Test");
        users1.setUsername("Chris");
        User s = User.createNewUser("", "");
        s = (User) map(users1, s.getClass());
        System.out.println(s.getPassword());
        System.out.println(s.getUsername());

    }*/
}
