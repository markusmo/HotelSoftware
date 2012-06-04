package hotelsoftware.model.database.manager;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.room.*;
import hotelsoftware.model.domain.room.IRoom;
import hotelsoftware.model.domain.room.IRoomCategory;
import hotelsoftware.model.domain.room.IRoomOption;
import hotelsoftware.model.domain.room.IRoomRoomStatus;
import hotelsoftware.model.domain.room.IRoomStatus;
import hotelsoftware.model.domain.room.RoomCategory;
import hotelsoftware.util.HibernateUtil;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Die Fassadenklasse, die das Package Room verwaltet
 *
 * @author mohi
 */
public class RoomManager
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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        DBRoom room = (DBRoom) session.createCriteria(DBRoom.class).add(Restrictions.eq("number", number)).uniqueResult();
        return (IRoom) DynamicMapper.map(room);
    }
    
    /**
     * Gibt alle Zimmer einer Kategorie aus
     * @param roomCategory die Kategorie der die Räume angehören sollen
     * @return die Zimmer die der Kategorie entsprechen
     */
    public Collection<IRoom> getRoomsByCategory(RoomCategory roomCategory){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        
        Collection<DBRoom> rooms = (Collection<DBRoom>) session.createCriteria(DBRoom.class).add(Restrictions.eq("idRoomCategories", roomCategory.getId()));
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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        Collection<DBRoomOption> options = session.createCriteria(DBRoomOption.class).list();

        return DynamicMapper.mapCollection(options);
    }

    /**
     * Gibt alle Kategorien aus.
     *
     * @return eine Liste von allen Kategorien
     */
    public Collection<IRoomCategory> getAllCategories()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();

        Query query = session.createQuery("FROM DBRoomCategory ORDER BY name");
        List<DBRoomCategory> cats = query.list();
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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();

        DBRoomCategory cat = (DBRoomCategory) session.createCriteria(DBRoomCategory.class).add(Restrictions.eq("name", name)).uniqueResult();
        return (RoomCategory) DynamicMapper.map(cat);
    }

    public IRoomStatus getRoomStatusByName(String name)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();

        DBRoomStatus status = (DBRoomStatus) session.createCriteria(DBRoomStatus.class).add(Restrictions.eq("statusName", name)).uniqueResult();
        return (IRoomStatus) DynamicMapper.map(status);
    }

    public void saveRoomStatus(IRoomStatus roomStatus)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        t.begin();

        saveRoomStatus(roomStatus, session);

        t.commit();
    }

    public void saveRoomStatus(IRoomStatus roomStatus, Session session)
    {
        DBRoomStatus dbrs = (DBRoomStatus) DynamicMapper.map(roomStatus);

        if (dbrs.getId() == null)
        {
            session.saveOrUpdate(dbrs);
            roomStatus.setId(dbrs.getId());
        }
        else
        {
            session.saveOrUpdate(session.merge(dbrs));
        }
    }

    public void saveRoomsRoomStatus(IRoomRoomStatus roomsRoomStatus)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        t.begin();

        saveRoomsRoomStatus(roomsRoomStatus, session);

        t.commit();
    }

    public void saveRoomsRoomStatus(IRoomRoomStatus roomsRoomStatus, Session session)
    {
        DBRoomsRoomStatus dbrrs = (DBRoomsRoomStatus) DynamicMapper.map(roomsRoomStatus);

        session.saveOrUpdate(dbrrs);
    }
}