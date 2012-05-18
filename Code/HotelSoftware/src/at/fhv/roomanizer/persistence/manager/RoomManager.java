package at.fhv.roomanizer.persistence.manager;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.room.Category;
import at.fhv.roomanizer.domain.room.Price;
import at.fhv.roomanizer.domain.room.Room;
import at.fhv.roomanizer.domain.room.RoomStatus;
import at.fhv.roomanizer.domain.room.Season;
import at.fhv.roomanizer.domain.room.Status;
import at.fhv.roomanizer.persistence.ObjectConverter;
import at.fhv.roomanizer.persistence.entity.room.CategoryEntity;
import at.fhv.roomanizer.persistence.entity.room.PriceEntity;
import at.fhv.roomanizer.persistence.entity.room.RoomEntity;
import at.fhv.roomanizer.persistence.entity.room.RoomStatusEntity;
import at.fhv.roomanizer.persistence.entity.room.StatusEntity;

/**
 * RoomManager is responsible for the whole Domain-Objects in the room package
 *
 * @author Andreas Sinz <andreas.sinz@students.fhv.at>
 *
 */
public class RoomManager extends Manager implements IRoomManager
{
    protected RoomManager(Session session)
    {
        super(session);
    }

    /**
     * Returns a list of all rooms from the database
     *
     * @return List of all rooms, which are stored in the database
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Override
    public List<Room> getAllRooms() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Query roomQuery = _session.createQuery("from RoomEntity");

        @SuppressWarnings("unchecked")
        List<RoomEntity> tmpList = roomQuery.list();

        List<Room> roomList = new ArrayList<Room>();
        for (RoomEntity entity : tmpList)
        {
            roomList.add((Room) ObjectConverter.ConvertHibernateToDomain(entity, new HashMap<Object, Object>()));
        }

        return roomList;
    }

    /**
     * Returns all rooms, which belongs to a specific category
     *
     * @param category, which the rooms should belong to
     * @return
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Override
    public List<Room> getRoomsByCategory(Category category) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Query roomQuery = _session.createQuery("from RoomEntity where idRoomCategories = :catId");
        roomQuery.setInteger("catId", category.getId());

        @SuppressWarnings("unchecked")
        List<RoomEntity> tmpList = roomQuery.list();

        List<Room> roomList = new ArrayList<Room>();
        for (RoomEntity entity : tmpList)
        {
            roomList.add((Room) ObjectConverter.ConvertHibernateToDomain(entity, new HashMap<Object, Object>()));
        }

        return roomList;
    }

    /**
     * Returns a list of all categories from the database
     *
     * @return List of all categories
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Override
    public List<Category> getAllCategories() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Query roomQuery = _session.createQuery("from CategoryEntity");

        @SuppressWarnings("unchecked")
        List<CategoryEntity> tmpList = roomQuery.list();

        List<Category> categoryList = new ArrayList<Category>();
        for (CategoryEntity entity : tmpList)
        {
            categoryList.add((Category) ObjectConverter.ConvertHibernateToDomain(entity, new HashMap<Object, Object>()));
        }

        return categoryList;
    }

    /**
     * Returns a Category specified by its name
     *
     * @param name Name of the Category, which should be retrieved
     * @return
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Override
    public Category getCategoryByName(String name) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Query roomQuery = _session.createQuery("from CategoryEntity where name LIKE :categoryName");
        roomQuery.setString("categoryName", name);

        //We want only 1 category
        roomQuery.setMaxResults(1);

        @SuppressWarnings("unchecked")
        List<CategoryEntity> tmpList = roomQuery.list();

        if (tmpList.size() != 1)
        {
            return null;
        }

        return (Category) ObjectConverter.ConvertHibernateToDomain(tmpList.get(0), new HashMap<Object, Object>());
    }

    /**
     * Returns the price of the given category at the given time
     *
     * @param category Category of the room
     * @param date Date, at which we want to know the price
     * @return Returns a Price object with the given category at the given time
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Override
    public Price getCategoryPriceByDate(Category category, Date date) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Query priceQuery = _session.createQuery("from PriceEntity where idRoomCategories = :categoryId and _season._start < :date and _season._end > :date");
        priceQuery.setMaxResults(1);
        priceQuery.setDate("date", date);
        priceQuery.setInteger("categoryId", category.getId());
        @SuppressWarnings("unchecked")
        List<PriceEntity> tmpList = priceQuery.list();

        if (tmpList.size() == 1)
        {
            return (Price) ObjectConverter.ConvertHibernateToDomain(tmpList.get(0), new HashMap<Object, Object>());
        }

        return null;
    }

    /**
     * Returns the season, which belongs to the given date
     *
     * @param date Date, which we want to know the Season for
     * @return
     * Saves a new status of a room
     * @param status Status, which will be stored in the database
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Override
    public void saveRoomStatus(RoomStatus status) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Transaction tx = _session.beginTransaction();

        Object tmp = ObjectConverter.ConvertDomainToHibernate(status, new HashMap<Object, Object>());
        _session.merge(tmp);
        _session.flush();
        tx.commit();
    }

    @Override
    public Season getSeasonByDate(Date date) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Query seasonQuery = _session.createQuery("from SeasonEntity where startDate < :date and endDate > :date");
        seasonQuery.setMaxResults(1);
        seasonQuery.setDate("date", date);
        @SuppressWarnings("unchecked")
        List<PriceEntity> tmpList = seasonQuery.list();

        if (tmpList.size() == 1)
        {
            return (Season) ObjectConverter.ConvertHibernateToDomain(tmpList.get(0), new HashMap<Object, Object>());
        }

        return null;
    }

    /**
     * Get the room status of the given room at the given date
     *
     * @param room Room, which we want to know the status from
     * @param date Date, at which we want to know the status from
     * @return Returns the status, which the room has at the given date
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Override
    public RoomStatus getRoomStatusByDate(Room room, Date date) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Query statusQuery = _session.createQuery("from RoomStatusEntity where idRooms = :roomID and startDate < :date and endDate > :date");
        statusQuery.setInteger("roomID", room.getId());
        statusQuery.setDate("date", date);

        statusQuery.setMaxResults(1);

        @SuppressWarnings("unchecked")
        List<RoomStatusEntity> tmpList = statusQuery.list();

        if (tmpList.size() == 1)
        {
            return (RoomStatus) ObjectConverter.ConvertHibernateToDomain(tmpList.get(0), new HashMap<Object, Object>());
        }

        return null;

    }

    /**
     * Returns the singleton-instance of the HabitationManager
     *
     * @param session Session, which should be used to retrieve/store data in the database
     * @return
     */
    @Override
    public List<Status> getAllStatus() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Query statusQuery = _session.createQuery("from StatusEntity");
        @SuppressWarnings("unchecked")
        List<StatusEntity> tmpList = statusQuery.list();

        List<Status> statusList = new ArrayList<Status>();
        for (StatusEntity entity : tmpList)
        {
            statusList.add((Status) ObjectConverter.ConvertHibernateToDomain(entity, new HashMap<Object, Object>()));
        }

        return statusList;
    }

    @Override
    public Status getStatusByName(String name) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Query statusQuery = _session.createQuery("from StatusEntity where name like :name");
        statusQuery.setString("name", name);
        statusQuery.setMaxResults(1);

        @SuppressWarnings("unchecked")
        List<StatusEntity> tmpList = statusQuery.list();
        if (tmpList.size() == 1)
        {
            return (Status) ObjectConverter.ConvertHibernateToDomain(tmpList.get(0), new HashMap<Object, Object>());
        }
        return null;
    }
    private static RoomManager _instance;

    public static RoomManager getInstance(Session session)
    {
        if (_instance == null)
        {
            _instance = new RoomManager(session);
        }

        return _instance;
    }
}
