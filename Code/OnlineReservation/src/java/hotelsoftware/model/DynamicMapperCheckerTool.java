package hotelsoftware.model;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Testing Tool fuer den DynamicMapper
 * @author Johannes
 */
public class DynamicMapperCheckerTool
{
    public static void main(String args[])
    {
        check();
    }

    /**
     * Prueft den DynamicMapper
     */
    private static void check()
    {

        List<Class> databaseClasses = null;
        List<Class> domainClasses = null;
        HashMap<Class, Class> mapping = new HashMap<Class, Class>();
        try
        {
            databaseClasses = getClasses("hotelsoftware.model.database");
            domainClasses = getClasses("hotelsoftware.model.domain");
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(DynamicMapperCheckerTool.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(DynamicMapperCheckerTool.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean in = false;
        for (Class c : databaseClasses)
        {
            in = false;
            for (Class c2 : domainClasses)
            {
                String domain = c2.getName().split("\\.")[c2.getName().split("\\.").length - 1];
                String database = c.getName().split("\\.")[c.getName().split("\\.").length - 1];
                if (database.replace("DB", "").equals(domain))
                {
                    mapping.put(c, c2);
                    System.out.println(domain + "    " + database);

                    in = true;
                }

            }
            if (!in)
            {
                System.out.println("");
                System.out.println(c.getName());
                System.out.println("");
            }
        }

        for (Class c : mapping.keySet())
        {
            System.out.println(c.getName());
            for (Field f : c.getDeclaredFields())
            {
                if (GetterExists(c, f.getName()))
                {
                    try
                    {
                        Method getter = getGetter(c, f.getName());
                        Method getter2 = mapping.get(c).getMethod(getter.getName(), getter.getParameterTypes());
                    }
                    catch (NoSuchMethodException ex)
                    {
                        System.out.println("\t" + f.getName() + " Getter in domainebene fehlt");
                    }
                    catch (SecurityException ex)
                    {
                        Logger.getLogger(DynamicMapperCheckerTool.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                {
                    System.out.println("\t" + f.getName() + " Getter fehlt");
                }
            }
        }

        //checkGetterUndSetter(databaseClasses, domainClasses);
    }

    /**
     * Prueft den DynamicMapper, ob die getter und setter der Klassen gemappt werden koennen
     * @param databaseClasses
     * Die Klassen die mit laut Namenskonvention mit einem Praefix beginnen
     * @param domainClasses 
     * die Klassen die mit laut Namenskonvention ohne einem Praefix sind
     */
    private static void checkGetterUndSetter(List<Class> databaseClasses, List<Class> domainClasses)
    {

        for (Class c : databaseClasses)
        {
            System.out.println(c.getName().split("\\.")[c.getName().split("\\.").length - 1] + ":");
            for (Field f : c.getDeclaredFields())
            {
                System.out.println("\tGetter fehlt bei: ");
                if (!GetterExists(c, f.getName()))
                {
                    System.out.println("\t\t" + f.getName());
                }
                System.out.println("\tSetter fehlt bei: ");
                if (!SetterExists(c, f.getName()))
                {
                    System.out.println("\t\t" + f.getName());
                }
            }
            System.out.println("");
        }
        for (Class c : domainClasses)
        {
            System.out.println(c.getName().split("\\.")[c.getName().split("\\.").length - 1] + ":");
            for (Field f : c.getDeclaredFields())
            {
                System.out.println("\tGetter fehlt bei: ");
                if (!GetterExists(c, f.getName()))
                {
                    System.out.println("\t\t" + f.getName());
                }
                System.out.println("\tSetter fehlt bei: ");
                if (!SetterExists(c, f.getName()))
                {
                    System.out.println("\t\t" + f.getName());
                }
            }
            System.out.println("");
        }
    }

    /**
     * Gibt die Getter-Methode fuer ein Attribut aus
     * @param clazz
     * Die Klasse auf die geprueft werden muss
     * @param fieldName
     * das Attribut fuer die der Getter gesucht wird
     * @return 
     * Die Gettermethode
     */
    private static Method getGetter(Class clazz, String fieldName)
    {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods)
        {

            if (m.getName().startsWith("g") && m.getName().endsWith(fieldName.substring(1)))
            {
                return m;
            }
        }
        return null;
    }

    /**
     * Gibt die Setter-Methode fuer ein Attribut aus
     * @param clazz
     * Die Klasse auf die geprueft werden muss
     * @param fieldName
     * das Attribut fuer die der Setter gesucht wird
     * @return 
     * Die Settermethode
     */
    private static Method getSetter(Class clazz, String fieldName)
    {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods)
        {

            if (m.getName().startsWith("s") && m.getName().endsWith(fieldName.substring(1)))
            {
                return m;
            }
        }
        return null;
    }

    /**
     * Prueft der Getter fuer ein Attribut existiert
     * @param clazz
     * Die Klasse auf die geprueft werden muss
     * @param fieldName
     * das Attribut fuer die der Getter gesucht wird
     * @return
     * True, wenn existiert.
     */
    private static boolean GetterExists(Class clazz, String fieldName)
    {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods)
        {

            if (m.getName().startsWith("g") && m.getName().endsWith(fieldName.substring(1)))
            {
                return true;
            }
        }
        return false;
    }
    /**
     * Prueft der Setter fuer ein Attribut existiert
     * @param clazz
     * Die Klasse auf die geprueft werden muss
     * @param fieldName
     * das Attribut fuer die der Setter gesucht wird
     * @return
     * True, wenn existiert.
     */
    private static boolean SetterExists(Class clazz, String fieldName)
    {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods)
        {

            if (m.getName().startsWith("s") && m.getName().endsWith(fieldName.substring(1)))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Scannt alle Klassen aus dem Context-Class-Loader, die zu einem gegebenen Package gehoeren und dessen Subpackages.
     * @param packageName Das Basispaket
     * @return Die Klassen in diesem Packet
     * @throws ClassNotFoundException Wirft diesen fehler, wenn die Klassen nicht verfuegbar sind.
     * @throws IOException Wirft diesen Fehler, wenn die Klassen nicht vorhanden sind.
     */
    @SuppressWarnings("unchecked")
    private static List<Class> getClasses(String packageName)
            throws ClassNotFoundException, IOException
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements())
        {
            URL resource = resources.nextElement();
            String fileName = resource.getFile();
            String fileNameDecoded = URLDecoder.decode(fileName, "UTF-8");
            dirs.add(new File(fileNameDecoded));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs)
        {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    /**
     * Rekursive Methode, die alle Klassen in einem Verzeichnis und dessen Subverzeichnissen sind ausgibt
     * @param directory Das Basisverzeichnis
     * @param packageName Das Package, in dem die Klassen zu finden sind
     * @return Die gefundenen Klassen
     * @throws ClassNotFoundException Wirft diesen Fehler, wenn eine Klasse nicht gefunden wird.
     */
    @SuppressWarnings("unchecked")
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException
    {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists())
        {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files)
        {
            String fileName = file.getName();
            if (file.isDirectory())
            {
                assert !fileName.contains(".");
                classes.addAll(findClasses(file, packageName + "." + fileName));
            }
            else
            {
                if (fileName.endsWith(".class") && !fileName.contains("$"))
                {
                    Class _class;
                    try
                    {
                        _class = Class.forName(packageName + '.' + fileName.substring(0, fileName.length() - 6));
                    }
                    catch (ExceptionInInitializerError e)
                    {
                        // happen, for example, in classes, which depend on 
                        // Spring to inject some beans, and which fail, 
                        // if dependency is not fulfilled
                        _class = Class.forName(packageName + '.' + fileName.substring(0, fileName.length() - 6),
                                false, Thread.currentThread().getContextClassLoader());
                    }
                    classes.add(_class);
                }
            }
        }
        return classes;
    }
}
