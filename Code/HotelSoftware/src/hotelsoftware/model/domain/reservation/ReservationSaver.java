/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.FailedToSaveToDatabaseException;
import hotelsoftware.model.database.reservation.DBReservationitems;
import hotelsoftware.model.database.reservation.DBReservationoptions;
import hotelsoftware.model.database.reservation.DBReservations;
import hotelsoftware.model.database.users.DBPermission;
import hotelsoftware.model.database.users.DBRole;
import hotelsoftware.model.database.users.DBUser;
import hotelsoftware.model.domain.users.Permission;
import hotelsoftware.model.domain.users.Role;
import hotelsoftware.model.domain.users.User;
import hotelsoftware.util.HibernateUtil;
import java.math.BigDecimal;
import java.util.Collection;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Johannes
 */
public class ReservationSaver
{
    private ReservationSaver()
    {
    }

    public static ReservationSaver getInstance()
    {
        return ReservationSaverrHolder.INSTANCE;
    }

    private static class ReservationSaverrHolder
    {
        private static final ReservationSaver INSTANCE = new ReservationSaver();
    }

    public void saveOrUpdate(Collection<Reservation> reservations, Collection<Option> options, Collection<ReservationItem> reservationItems) throws FailedToSaveToDatabaseException
    {
        Session session = null;
        Transaction ts = null;

        try
        {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            ts = session.beginTransaction();
            ts.begin();

            saveOrUpdate(session, reservations, options, reservationItems);

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
        finally
        {
            if (session != null)
            {
                session.close();
            }
        }

    }

    public void saveOrUpdate(Session session, Collection<Reservation> reservations, Collection<Option> options, Collection<ReservationItem> reservationItems) throws FailedToSaveToDatabaseException
    {
        for (Reservation reservation : reservations)
        {
            DBReservations dbp = (DBReservations) DynamicMapper.map(reservation);

            session.saveOrUpdate(dbp);
            reservation.setId(dbp.getId());
        }

        for (Option option : options)
        {
            DBReservationoptions dbo = (DBReservationoptions) DynamicMapper.map(option);

            session.saveOrUpdate(dbo);
            option.setId(dbo.getId());
        }
        for (ReservationItem item : reservationItems)
        {
            DBReservationitems dbi = (DBReservationitems) DynamicMapper.map(item);

            session.saveOrUpdate(dbi);
            // reservationItems.setId(dbi.getId());
        }

    }

    public void rollback(Collection<Reservation> reservations, Collection<Option> options, Collection<ReservationItem> reservationItems)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        for (Reservation reservation : reservations)
        {
            DBReservations dbr;

            if (reservation.getId() != null)
            {
                dbr = (DBReservations) session.createCriteria(DBReservations.class).add(Restrictions.eq("id",
                        reservation.getId())).uniqueResult();

                Reservation temp = (Reservation) DynamicMapper.map(dbr);
                reservation.setEnd(temp.getEnd());
                reservation.setComment(temp.getComment());
                reservation.setStart(temp.getStart());
                reservation.setOptionCollection(temp.getOptionCollection());
                reservation.setParty(temp.getParty());
                reservation.setReservationItemCollection(temp.getReservationItemCollection());
                reservation.setCreated(temp.getCreated());
            }
        }

        for (Option option : options)
        {
            DBReservationoptions dbo;
            if (option.getId() != null)
            {
                dbo = (DBReservationoptions) session.createCriteria(DBReservationoptions.class).add(Restrictions.eq("id",
                        option.getId())).uniqueResult();
                Option temp = (Option) DynamicMapper.map(dbo);
                option.setExpiration(temp.getExpiration());
                option.setFulfilled(temp.getFulfilled());
                option.setPrepayment(temp.getPrepayment());
            }
        }
        for (ReservationItem item : reservationItems)
        {
            
        }
    }
}
