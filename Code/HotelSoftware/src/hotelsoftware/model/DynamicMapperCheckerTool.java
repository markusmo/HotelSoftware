/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Johannes
 */
public class DynamicMapperCheckerTool
{
    public static void main(String args[])
    {
        check();
    }

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
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
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
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
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
