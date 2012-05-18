package at.fhv.roomanizer.persistence;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ObjectConverter provides static methods, which converts hibernate objects to domain objects and vice versa
 *
 * @author Andreas Sinz <andreas.sinz@students.fhv.at>
 *
 */
public class ObjectConverter
{
    /**
     * Converts a Domain object into it's equivalent Hibernate object
     *
     * @param domainObject
     * @return The converted domain as hibernate object
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     * @throws IllegalArgumentException
     * @throws Exception
     */
    public static Object ConvertDomainToHibernate(Object domainObject, HashMap<Object, Object> objectsInConversion) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        //If the object, which will be converted, is null, don't convert it
        if (domainObject == null)
        {
            return null;
        }

        Class<? extends Object> domainClass = domainObject.getClass();

        return convert(domainObject, getHibernatePackagename(domainClass.getName()), false, objectsInConversion);
    }

    /**
     * Converts a Hibernate Object into it's equivalent Domain object
     *
     * @param hibernateObject
     * @return The converted hibernate as domain object
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     * @throws IllegalArgumentException
     * @throws Exception
     */
    public static Object ConvertHibernateToDomain(Object hibernateObject, HashMap<Object, Object> objectsInConversion) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        //If the object, which will be converted, is null, don't convert it
        if (hibernateObject == null)
        {
            return null;
        }

        Class<? extends Object> hibernateClass = hibernateObject.getClass();
        //Change the package of the targetClass

        return convert(hibernateObject, getDomainPackageName(hibernateClass.getName()), true, objectsInConversion);
    }

    /**
     * Converts a Object into an Object of the other layer
     *
     * @param sourceObject Object, which will be converted
     * @param targetClassName Name of the target class
     * @param hibernateToDomain Defines, whether we should convert the sourceObject into a hibernate or a domain object
     * @param objectsInConversion Objects, which are currently in the process of conversion
     * @return Returns the converted object
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    private static Object convert(Object sourceObject, String targetClassName, boolean hibernateToDomain, HashMap<Object, Object> objectsInConversion) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {

        //Get source and destination Classes
        Class<? extends Object> sourceClass = sourceObject.getClass();

        Class<?> targetClass = Class.forName(targetClassName);

        //Create an Object of the target-class
        Object targetObject = targetClass.newInstance();

        objectsInConversion.put(sourceObject, targetObject);

        //Get all Methods from the sourceClass
        Method[] sourceMethods = sourceClass.getDeclaredMethods();
        Class<?> superClass = sourceClass.getSuperclass();

        List<Method> methodList = new ArrayList<Method>();

        for (Method m : sourceMethods)
        {
            methodList.add(m);
        }

        if (!superClass.equals(Object.class))
        {
            for (Method m : superClass.getDeclaredMethods())
            {
                methodList.add(m);
            }
        }


        //Iterate over the methods of the sourceClass
        for (Method tmp : methodList)
        {

            String methodName = tmp.getName();
            if (methodName.startsWith("get"))
            {

                Type returnType = tmp.getReturnType();
                if (((Class<?>) returnType).isInterface() && !((Class<?>) returnType).equals(List.class))
                {
                    continue;
                }

                Object returnValue = tmp.invoke(sourceObject, new Object[0]);
                if (returnValue == null)
                {
                    continue;
                }

                boolean alreadyConverted = false;

                Object setParameter = null;
                if (objectsInConversion.containsKey(returnValue))
                {
                    setParameter = objectsInConversion.get(returnValue);
                    alreadyConverted = true;
                }
                else
                {
                    setParameter = returnValue;
                }

                //Replace "get" with "set", so we can set the retrieved value on the other side
                methodName = methodName.replace("get", "set");

                Method targetMethod = null;
                try
                {
                    Package returnTypePackage = tmp.getReturnType().getPackage();
                    if (returnTypePackage != null)
                    {
                        if (returnTypePackage.getName().contains("entity") && hibernateToDomain)
                        {
                            returnType = Class.forName(getDomainPackageName(((Class<?>) returnType).getName()));

                            if (!alreadyConverted)
                            {
                                setParameter = ConvertHibernateToDomain(setParameter, objectsInConversion);
                            }
                        }
                        else
                        {
                            if (returnTypePackage.getName().contains("domain") && !hibernateToDomain)
                            {
                                returnType = Class.forName(getHibernatePackagename(((Class<?>) returnType).getName()));

                                if (!alreadyConverted)
                                {
                                    setParameter = ConvertDomainToHibernate(setParameter, objectsInConversion);
                                }
                            }
                        }
                    }

                    targetMethod = targetClass.getMethod(methodName, (Class<?>) returnType);

                    if (returnType.equals(List.class))
                    {
                        Class<?> listClass = Class.forName("java.util.ArrayList");
                        Object listObject = listClass.newInstance();


                        Method listGetMethod = ((Class<?>) returnType).getDeclaredMethod("get", int.class);
                        Method listAddMethod = listClass.getDeclaredMethod("add", Object.class);
                        Method listSizeMethod = ((Class<?>) returnType).getDeclaredMethod("size", new Class[0]);
                        Object tmpObj = listSizeMethod.invoke(returnValue, new Object[0]);

                        int listSize = (Integer) tmpObj;

                        //for-loop
                        for (int j = 0; j < listSize; j++)
                        {
                            Object listItem = listGetMethod.invoke(returnValue, j);
                            Package itemPackage = listItem.getClass().getPackage();


                            if (objectsInConversion.containsKey(listItem))
                            {
                                listAddMethod.invoke(listObject, objectsInConversion.get(listItem));
                            }
                            else
                            {

                                if (hibernateToDomain && itemPackage.getName().contains("entity"))
                                {
                                    listAddMethod.invoke(listObject, ConvertHibernateToDomain(listItem, objectsInConversion));
                                }
                                else
                                {
                                    if (!hibernateToDomain && itemPackage.getName().contains("domain") && !hibernateToDomain)
                                    {
                                        listAddMethod.invoke(listObject, ConvertDomainToHibernate(listItem, objectsInConversion));
                                    }
                                    else
                                    {
                                        listAddMethod.invoke(listObject, listItem);
                                    }
                                }
                            }
                        }

                        //Set the newly created List with the
                        setParameter = listObject;
                    }

                    targetMethod.invoke(targetObject, setParameter);
                }
                catch (NoSuchMethodException e)
                {
                    System.out.println("NoSuchMethod!!!! " + e.getLocalizedMessage() + "\n");
                }
            }
        }

        return targetObject;
    }

    /**
     * Converts a hibernatePackage into a domainPackage
     *
     * @param hibernatePackage, which will be converted
     * @return The domainPackage of the given hibernatePackage
     */
    private static String getDomainPackageName(String hibernatePackage)
    {
        String targetClassName = hibernatePackage.replace("persistence.entity", "domain");

        //Remove the Entity Suffix from the class
        targetClassName = targetClassName.replace("Entity", "");

        return targetClassName;
    }

    /**
     * Converts a domainPackage into a hibernatePackage
     *
     * @param domainPackage, which will be converted
     * @return The hibernatePackage of the given domainPackage
     */
    private static String getHibernatePackagename(String domainPackage)
    {
        return domainPackage.replace("domain", "persistence.entity") + "Entity";
    }
}
