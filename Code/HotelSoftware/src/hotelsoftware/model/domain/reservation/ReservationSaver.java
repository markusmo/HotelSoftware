/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.FailedToSaveToDatabaseException;
import hotelsoftware.model.database.reservation.DBReservationItem;
import hotelsoftware.model.database.reservation.DBReservationOption;
import hotelsoftware.model.database.reservation.DBReservation;
import hotelsoftware.util.HibernateUtil;
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

    public void saveOrUpdate(Collection<Reservation> reservations, Collection<ReservationOption> options, Collection<ReservationItem> reservationItems) throws FailedToSaveToDatabaseException
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

    public void saveOrUpdate(Session session, Collection<Reservation> reservations, Collection<ReservationOption> options, Collection<ReservationItem> reservationItems) throws FailedToSaveToDatabaseException
    {
        for (Reservation reservation : reservations)
        {
            DBReservation dbp = (DBReservation) DynamicMapper.map(reservation);

            session.saveOrUpdate(dbp);
            reservation.setId(dbp.getId());
        }

        for (ReservationOption option : options)
        {
            DBReservationOption dbo = (DBReservationOption) DynamicMapper.map(option);

            session.saveOrUpdate(dbo);
            option.setId(dbo.getId());
        }
        for (ReservationItem item : reservationItems)
        {
            DBReservationItem dbi = (DBReservationItem) DynamicMapper.map(item);

            session.saveOrUpdate(dbi);
            
            ReservationItemPK pk = (ReservationItemPK)DynamicMapper.map(dbi.getReservationitemsPK());
            item.setReservationitemsPK(pk);
        }

    }

    public void rollback(Collection<Reservation> reservations, Collection<ReservationOption> options, Collection<ReservationItem> reservationItems)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        for (Reservation reservation : reservations)
        {
            DBReservation dbr;

            if (reservation.getId() != null)
            {
                dbr = (DBReservation) session.createCriteria(DBReservation.class).add(Restrictions.eq("id",
                        reservation.getId())).uniqueResult();

                Reservation temp = (Reservation) DynamicMapper.map(dbr);
                reservation.setEndDate(temp.getEndDate());
                reservation.setComment(temp.getComment());
                reservation.setStartDate(temp.getStartDate());
                reservation.setOptionCollection(temp.getOptionCollection());
                reservation.setParty(temp.getParty());
                reservation.setReservationitems(temp.getReservationitems());
                reservation.setCreated(temp.getCreated());
            }
        }

        for (ReservationOption option : options)
        {
            DBReservationOption dbo;
            if (option.getId() != null)
            {
                dbo = (DBReservationOption) session.createCriteria(DBReservationOption.class).add(Restrictions.eq("id",
                        option.getId())).uniqueResult();
                ReservationOption temp = (ReservationOption) DynamicMapper.map(dbo);
                option.setExpiration(temp.getExpiration());
                option.setFulfilled(temp.isFulfilled());
                option.setPrepayment(temp.getPrepayment());
            }
        }
        for (ReservationItem item : reservationItems)
        {
           //TODO Implement
        }
    }
}
