package hotelsoftware.model.domain.room;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.FailedToSaveToDatabaseException;
import hotelsoftware.model.database.room.DBRoomCategory;
import hotelsoftware.model.database.room.DBRoomOption;
import hotelsoftware.model.database.room.DBRoomStatus;
import hotelsoftware.model.database.room.DBRoomsRoomStatus;
import hotelsoftware.util.HibernateUtil;
import java.util.Collection;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Die Sicherungsklasse, mit der alle Objekte des Room Package in die Datenbank
 * gespielt werden koennen
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class RoomSaver
{
    private RoomSaver()
    {
    }

    public static RoomSaver getInstance()
    {
        return RoomSaverHolder.INSTANCE;
    }

    private static class RoomSaverHolder
    {
        private static final RoomSaver INSTANCE = new RoomSaver();
    }

    /**
     * Diese Methode speichert alle änderungen in der Datenbank ab
     *
     * @param categories
     * @param options
     * @param status
     * @throws FailedToSaveToDatabaseException
     */
    public void saveOrUpdate(Set<RoomCategory> categories, Set<RoomOption> options, Set<RoomStatus> status, Set<RoomsRoomStatus> roomStatus) throws FailedToSaveToDatabaseException
    {

        Session session = null;
        Transaction ts = null;

        try
        {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            ts = session.beginTransaction();
            ts.begin();

            saveOrUpdate(session, categories, options, status, roomStatus);

            ts.commit();
        }
        catch (HibernateException ex)
        {
            if (ts != null)
            {
                ts.rollback();
            }

            throw new FailedToSaveToDatabaseException();
        }
    }

    /**
     * Diese Methode sichert und updated mittels einer uebergebenen
     * Hibernate-Session alle Objekte, des Package Room in die Datenbank
     *
     * @param session Eine Liste von veraenderten/neuen Saisonen
     * @param categories Eine Liste von veraenderten/neuen Zimmerkategorieen
     * @param options Eine Liste von veraenderten/neuen Zimmeroptionen
     * @param status Eine Liste von veraenderten/neuen Zimmerstatus
     * @throws FailedToSaveToDatabaseException Wirft einen Fehler, wenn das
     * sichern in die Datenbank fehllschlaegt
     */
    public void saveOrUpdate(Session session, Set<RoomCategory> categories, Set<RoomOption> options, Set<RoomStatus> status, Set<RoomsRoomStatus> roomStatus) 
            throws FailedToSaveToDatabaseException
    {

        if (categories != null)
        {
            for (RoomCategory category : categories)
            {
                DBRoomCategory dbCat = (DBRoomCategory) DynamicMapper.map(category);

                session.saveOrUpdate(dbCat);
                category.setId(dbCat.getId());
            }
        }

        if (options != null)
        {
            for (RoomOption option : options)
            {
                DBRoomOption dbOpt = (DBRoomOption) DynamicMapper.map(option);

                session.saveOrUpdate(dbOpt);
                option.setId(dbOpt.getId());
            }
        }

        if (status != null)
        {
            for (RoomStatus stat : status)
            {
                DBRoomStatus dbStat = (DBRoomStatus) DynamicMapper.map(stat);

                session.saveOrUpdate(dbStat);
                stat.setId(dbStat.getId());
            }
        }
        
        if (roomStatus != null)
        {
            for (RoomsRoomStatus stat : roomStatus)
            {
                DBRoomsRoomStatus dbStat = (DBRoomsRoomStatus) DynamicMapper.map(stat);

                session.saveOrUpdate(dbStat);
                stat.setRoomsroomstatusPK(dbStat.getId());
            }
        }
    }
}
