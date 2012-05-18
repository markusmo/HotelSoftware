package at.fhv.roomanizer.persistence;

import org.hibernate.Session;

import at.fhv.roomanizer.persistence.manager.HabitationManager;
import at.fhv.roomanizer.persistence.manager.InvoiceItemManager;
import at.fhv.roomanizer.persistence.manager.PersonManager;
import at.fhv.roomanizer.persistence.manager.ReservationManager;
import at.fhv.roomanizer.persistence.manager.RoomManager;
import at.fhv.roomanizer.persistence.manager.ServiceManager;

/**
 * ManagerFactory is responsible for the creation of the different Hibernate-Managers
 *
 * @author Andreas Sinz <andreas.sinz@students.fhv.at>
 *
 */
public class ManagerFactory
{
    private static Session _hibernateSession;

    /**
     * Returns the single instance of the ReservationManager
     *
     * @return A ReservationManager
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public static ReservationManager getReserveationManager() throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        return ReservationManagerAdapter.getInstance();
    }

    /**
     * Returns the single instance of the PersonManager
     *
     * @return A PersonManager
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public static PersonManager getPersonmanager() throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        return PersonManagerAdapter.getInstance();
    }

    /**
     * Returns the single instance of the RoomManager
     *
     * @return A RoomManager
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public static RoomManager getRoomManager() throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        return RoomManagerAdapter.getInstance();
    }

    /**
     * Returns the single instance of the HabitationManager
     *
     * @return A HabitationManager
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public static HabitationManager getHabitationManager() throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        return HabitationManagerAdapter.getInstance();
    }

    /**
     * Returns the current active Hibernate-Session
     *
     * @return Current Hibernate-Session
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public static ServiceManager getServiceManager() throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        return ServiceManagerAdapter.getInstance();
    }

    public static InvoiceItemManager getInvoiceItemManager() throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        return InvoiceItemManagerAdapter.getInstance();
    }
}
