package hotelsoftware.model.database.manager;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.room.*;
import hotelsoftware.model.domain.room.*;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 * Die Fassadenklasse, die das Package Room verwaltet
 *
 * @author mohi
 */
public class RoomManager extends Manager
{
    private RoomManager()
    {
    }

    public static RoomManager getInstance()
    {
        return RoomFacadeHolder.INSTANCE;
    }

    private static class RoomFacadeHolder
    {
        private static final RoomManager INSTANCE = new RoomManager();
    }

    /**
     * Gibt ein Zimmer nach einer nummer aus
     *
     * @param number die Nummer des Zimmers
     * @return das Zimmer mit der gesuchten Nummer
     */
    public IRoom getRoomByNumber(String number)
    {
        startTransaction();

        DBRoom room = (DBRoom) getSession().createCriteria(DBRoom.class).add(Restrictions.eq("number", number)).uniqueResult();
        commit();

        return (IRoom) DynamicMapper.map(room);
    }

    /**
     * Gibt alle Zimmer einer Kategorie aus
     *
     * @param roomCategory die Kategorie der die Räume angehören sollen
     * @return die Zimmer die der Kategorie entsprechen
     */
    public Collection<IRoom> getRoomsByCategory(RoomCategory roomCategory)
    {
        startTransaction();

        Collection<DBRoom> rooms = (Collection<DBRoom>) getSession().createCriteria(DBRoom.class).add(Restrictions.eq("idRoomCategories", roomCategory.getId()));
        commit();

        return (Collection<IRoom>) DynamicMapper.mapCollection(rooms);
    }

    /**
     * Gibt alle Optionen aus
     *
     * @return
     * Gibt ein Set mit den Zimmeroptionen aus, die verfuegbar sind
     */
    public Collection<IRoomOption> getAllRoomOptions()
    {
        startTransaction();

        Collection<DBRoomOption> options = getSession().createCriteria(DBRoomOption.class).list();
        commit();

        return DynamicMapper.mapCollection(options);
    }

    /**
     * Gibt alle Kategorien aus.
     *
     * @return eine Liste von allen Kategorien
     */
    public Collection<IRoomCategory> getAllCategories()
    {
        startTransaction();

        Query query = getSession().createQuery("FROM DBRoomCategory ORDER BY name");
        List<DBRoomCategory> cats = query.list();
        commit();

        return (Collection<IRoomCategory>) DynamicMapper.mapCollection(cats);
    }

    /**
     * Gibt eine Kategorie nach einen Namen aus
     *
     * @param name der Name der Kategorie
     * @return Die gefundene Kategorie mit dem angegebenen Namen
     */
    public IRoomCategory getCategoryByName(String name)
    {
        startTransaction();

        DBRoomCategory cat = (DBRoomCategory) getSession().createCriteria(DBRoomCategory.class).add(Restrictions.eq("name", name)).uniqueResult();
        commit();

        return (RoomCategory) DynamicMapper.map(cat);
    }

    public IRoomStatus getRoomStatusByName(String name)
    {
        startTransaction();

        DBRoomStatus status = (DBRoomStatus) getSession().createCriteria(DBRoomStatus.class).add(Restrictions.eq("statusName", name)).uniqueResult();
        commit();

        return (IRoomStatus) DynamicMapper.map(status);
    }

    public void saveRoomStatus(IRoomStatus roomStatus)
    {
        DBRoomStatus dbrs = (DBRoomStatus) DynamicMapper.map(roomStatus);

        if (dbrs.getId() == null)
        {
            getSession().saveOrUpdate(dbrs);
            roomStatus.setId(dbrs.getId());
        }
        else
        {
            getSession().saveOrUpdate(getSession().merge(dbrs));
        }
    }

    public void saveRoomsRoomStatus(IRoomRoomStatus roomsRoomStatus)
    {
        DBRoomsRoomStatus dbrrs = (DBRoomsRoomStatus) DynamicMapper.map(roomsRoomStatus);

        getSession().saveOrUpdate(dbrrs);
    }
}
