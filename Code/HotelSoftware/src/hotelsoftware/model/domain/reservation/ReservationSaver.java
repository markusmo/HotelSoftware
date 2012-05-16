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
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Diese Klasse Speichert Reservierungen in der Datenbank ab
 *
 * @author Johannes
 */
public class ReservationSaver {

    private ReservationSaver() {
    }

    public static ReservationSaver getInstance() {
        return ReservationSaverrHolder.INSTANCE;
    }

    private static class ReservationSaverrHolder {

        private static final ReservationSaver INSTANCE = new ReservationSaver();
    }

    /**
     * Diese Methode prüft ob sich etwas geändert hat und schickt die änderungen
     * in die nächste unterschicht
     *
     * @param reservations
     * @param options
     * @param reservationItems
     * @throws FailedToSaveToDatabaseException
     */
    public void saveOrUpdate(Collection<Reservation> reservations, Collection<ReservationOption> options,
            Collection<ReservationItem> reservationItems) throws FailedToSaveToDatabaseException {

        Session session = null;
        Transaction ts = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            ts = session.beginTransaction();
            ts.begin();

            saveOrUpdate(session, reservations, options, reservationItems);

            ts.commit();
        } catch (HibernateException ex) {
            if (ts != null) {
                ts.rollback();
            }

            throw new FailedToSaveToDatabaseException();
        } finally {
            if (session != null) {
            }
        }
    }

    public void saveOrUpdate(Session session, Collection<Reservation> reservations, Collection<ReservationOption> options,
            Collection<ReservationItem> reservationItems) throws FailedToSaveToDatabaseException {

        if (reservations != null) {
            for (Reservation reservation : reservations) {
                DBReservation dbpp = (DBReservation)DynamicMapper.map(ReservationFacade.getInstance().getReservationById(reservation.getId()));
                DBReservation dbp = (DBReservation) DynamicMapper.map(reservation);

                dbp = DynamicMapper.mapTwoObjects(dbpp, dbp);

                session.saveOrUpdate(dbp);
                reservation.setId(dbp.getId());
            }
        }

        if (options != null) {
            for (ReservationOption option : options) {
                DBReservationOption dbo = (DBReservationOption) DynamicMapper.map(option);

                session.saveOrUpdate(dbo);
                option.setId(dbo.getId());
            }
        }

        if (options != null) {
            for (ReservationItem item : reservationItems) {
                DBReservationItem dbi = (DBReservationItem) DynamicMapper.map(item);

                session.saveOrUpdate(dbi);

                ReservationItemPK pk = (ReservationItemPK) DynamicMapper.map(dbi.getReservationitemsPK());
                item.setReservationitemsPK(pk);
            }
        }
    }
}
