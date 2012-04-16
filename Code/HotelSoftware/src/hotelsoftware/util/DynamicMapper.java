package hotelsoftware.util;
import java.lang.reflect.Method;

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
    static public Object map(Object urObject, Class newObjectClass)
    {
        try
        {
            Object returnvalue = newObjectClass.newInstance();
            for (Method m : returnvalue.getClass().getMethods())
            {
                if (m.getName().startsWith("set"))
                {
                    Method m2 = getMethod(m, urObject);
                    if (m2 != null)
                    {
                        m.invoke(returnvalue, m2.invoke(urObject, null));
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
    /**
     * Überprüft ob die GetterMethode in einer Klasse vorhanden ist. Falls nicht return diese Methode null
     * @param m die Settermethode der ausgehendern Klasse
     * @param o das Objekt in deren Klasse die zu überprüfende Methode sein sollte.
     * @return 
     */
    private static Method getMethod(Method m, Object o)
    {
        Method m2 = null;
        try
        {
            m2 = o.getClass().getMethod(m.getName().replace("set", "get"), null);
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

    }/*
}
