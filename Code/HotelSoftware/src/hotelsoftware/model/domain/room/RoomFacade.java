package hotelsoftware.model.domain.room;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.room.DBRoom;
import hotelsoftware.model.database.room.DBRoomCategory;
import hotelsoftware.model.database.room.DBRoomOption;
import hotelsoftware.model.database.room.DBRoomStatus;
import hotelsoftware.util.HibernateUtil;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Die Fassadenklasse, die das Package Room verwaltet
 *
 * @author mohi
 */
public class RoomFacade
{

    private RoomFacade()
    {
    }

    public static RoomFacade getInstance()
    {
        return RoomFacadeHolder.INSTANCE;
    }

    private static class RoomFacadeHolder
    {
        private static final RoomFacade INSTANCE = new RoomFacade();
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
     * Gibt alle Optionen aus
     * @return 
     * Gibt ein Set mit den Zimmeroptionen aus, die verfuegbar sind
     */
    public Set<IRoomOption> getRoomOptions()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        
        List<DBRoomOption> options = session.createCriteria(DBRoomOption.class).list();
        
        return new LinkedHashSet<IRoomOption>(DynamicMapper.mapCollection(options));
    }

    /**
     * Gibt alle Kategorien aus.
     * @return eine Liste von allen Kategorien
     */
    public Collection<IRoomCategory> getAllCategorys()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        
        List<DBRoomCategory> cats = session.createCriteria(DBRoomCategory.class).list();
        return (Collection<IRoomCategory>) DynamicMapper.mapCollection(cats);
    }
    
    /**
     * Gibt eine Kategorie nach einen Namen aus
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
}
