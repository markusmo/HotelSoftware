/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.FailedToSaveToDatabaseException;
import hotelsoftware.model.database.room.DBRoomCategory;
import hotelsoftware.model.database.room.DBRoomOption;
import hotelsoftware.model.database.room.DBRoomStatus;
import hotelsoftware.model.domain.room.Category.RoomCategory;
import hotelsoftware.util.HibernateUtil;
import java.util.Collection;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
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
    
    public void saveOrUpdate(Collection<Category.RoomCategory> categories, Collection<RoomOption> options, Collection<RoomStatus> status) throws FailedToSaveToDatabaseException {
        Session session = null;
        Transaction ts = null;

        try
        {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            ts = session.beginTransaction();
            ts.begin();

            saveOrUpdate(session, categories, options, status);

            ts.commit();
        }  catch (HibernateException ex)
        {
            if (ts != null)
            {
                ts.rollback();
            }

            throw new FailedToSaveToDatabaseException();
        }
        finally
        {
            if (session != null)
            {
                session.close();
            }
        }
        
    }
    
    public void saveOrUpdate(Session session, Collection<Category.RoomCategory> categories, Collection<RoomOption> options, Collection<RoomStatus> status) throws FailedToSaveToDatabaseException {
            
        for (RoomCategory category : categories)
        {
            DBRoomCategory dbCat = (DBRoomCategory) DynamicMapper.map(category);

            session.saveOrUpdate(dbCat);
            category.setId(dbCat.getId());
        }
        
        for (RoomOption option : options)
        {
            DBRoomOption dbOpt = (DBRoomOption) DynamicMapper.map(option);

            session.saveOrUpdate(dbOpt);
            option.setId(dbOpt.getId());
        }
        
        for (RoomStatus stat : status)
        {
            DBRoomStatus dbStat = (DBRoomStatus) DynamicMapper.map(stat);

            session.saveOrUpdate(dbStat);
            stat.setId(dbStat.getId());
        }
            
    }
    
    //TODO implement Rollback
    
    
}
