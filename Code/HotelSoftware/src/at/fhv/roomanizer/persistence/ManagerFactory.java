package at.fhv.roomanizer.persistence;

import at.fhv.roomanizer.persistence.manager.*;
import hotelsoftware.model.adapter.HabitationManagerAdapter;
import hotelsoftware.model.adapter.InvoiceItemManagerAdapter;
import org.hibernate.Session;

import hotelsoftware.model.adapter.RoomManagerAdapter;
import hotelsoftware.model.adapter.ServiceManagerAdapter;

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
        throw new UnsupportedOperationException("Not implemented yet");
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
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Returns the single instance of the RoomManager
     *
     * @return A RoomManager
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public static IRoomManager getRoomManager() throws InstantiationException, IllegalAccessException, ClassNotFoundException
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
    public static IHabitationManager getHabitationManager() throws InstantiationException, IllegalAccessException, ClassNotFoundException
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
    public static IServiceManager getServiceManager() throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        return ServiceManagerAdapter.getInstance();
    }

    public static IInvoiceItemManager getInvoiceItemManager() throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        return InvoiceItemManagerAdapter.getInstance();
    }
}
