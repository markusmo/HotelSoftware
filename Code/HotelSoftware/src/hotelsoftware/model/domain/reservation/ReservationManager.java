package hotelsoftware.model.domain.reservation;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.reservation.DBReservation;
import hotelsoftware.model.database.reservation.DBReservationItem;
import hotelsoftware.util.HibernateUtil;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Fassadenobjekt, welches das Reservation-Package verwaltet.
 *
 * @author Johannes
 */
public class ReservationManager
{
    private ReservationManager()
    {
    }

    public static ReservationManager getInstance()
    {
        return ReservationFacadeHolder.INSTANCE;
    }

    private static class ReservationFacadeHolder
    {
        private static final ReservationManager INSTANCE = new ReservationManager();
    }

    int getHighestReservationId()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "Select max(id) FROM reservations r";
        SQLQuery sqlquery = session.createSQLQuery(query);


        Integer bd = (Integer) sqlquery.uniqueResult();

        if (bd != null)
        {
            return bd;
        }
        else
        {
            return 0;
        }
    }

    /**
     * Gibt eine Reservierung, nach der eindeutigen Reservierungsnummer aus
     *
     * @param reservationNr Die eindeutige Reservierungsnummer
     * @return Die Reservierung, die gesucht wurde
     */
    public IReservation getReservationByNumber(String reservationNr)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "SELECT * FROM Reservations r WHERE reservationNumber = :resNr";
        SQLQuery sqlquery = session.createSQLQuery(query);
        sqlquery.setString("resNr", reservationNr);
        sqlquery.addEntity(DBReservation.class);

        DBReservation reservation = (DBReservation) sqlquery.uniqueResult();

        return (IReservation) DynamicMapper.map(reservation);
    }

    /**
     * Gibt eine Reservierung nach dem Gast/Kunden aus, der reserviert hat
     *
     * @param fname Der Vorname des Gastes/Kunden
     * @param lname Der Nachname des Gastes/Kunden
     * @return Die gesuchte Reservierung
     */
    public Collection<IReservation> getReservationsByName(String fname, String lname)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "SELECT * FROM Reservations r WHERE r.idParties = ( SELECT idParties FROM guests g WHERE g.fname = '" + fname + "' AND g.lname = '" + lname + "') ";
        SQLQuery sqlquery = session.createSQLQuery(query);

        sqlquery.addEntity(DBReservation.class);
        List<DBReservation> retList = sqlquery.list();

        return (Collection<IReservation>) DynamicMapper.mapCollection(retList);
    }

    public Collection<IReservation> getReservationsByNameApprox(String fname,
            String lname)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "SELECT * FROM Reservations r WHERE r.idParties IN ( SELECT idParties FROM guests g WHERE g.fname like '"
                + fname + "%' AND g.lname like '" + lname + "%') OR r.idParties IN ( SELECT idParties FROM privatepersons p WHERE p.fname like '"
                + fname + "%' AND p.lname like '" + lname + "%')";
        SQLQuery sqlquery = session.createSQLQuery(query);


        //addEntity gibt den rueckgabewert an...
        sqlquery = sqlquery.addEntity(DBReservation.class);
        List<DBReservation> retList = sqlquery.list();

        return (Collection<IReservation>) DynamicMapper.mapCollection(retList);
    }

    public Collection<IReservation> getReservationsByCompanyNameApprox(String companyName)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "SELECT * FROM Reservations r WHERE r.idParties IN ( SELECT idParties FROM companies WHERE name like '" + companyName + "%') ";
        SQLQuery sqlquery = session.createSQLQuery(query);

        sqlquery.addEntity(DBReservation.class);
        List<DBReservation> retList = sqlquery.list();

        return (Collection<IReservation>) DynamicMapper.mapCollection(retList);
    }

    /**
     * Gibt eine Reservierung, nach ihrer eindeutigen ID aus
     *
     * @param id Die eindeutige ID
     * @return Die gesuchte Reservierung
     */
    public IReservation getReservationById(int id)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        Criteria criteria = session.createCriteria(DBReservation.class);
        criteria.add(Restrictions.eq("id", id));
        DBReservation retValue = (DBReservation) criteria.uniqueResult();

        return (IReservation) DynamicMapper.map(retValue);
    }

    /**
     * Gibt alle Reservierungen aus
     *
     * @return Alle vorhandenen Reservierungen
     */
    public Collection<IReservation> getAllReservations()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "SELECT * FROM Reservations r";
        SQLQuery sqlquery = session.createSQLQuery(query);
        sqlquery.addEntity(DBReservation.class);

        Collection<DBReservation> retList = sqlquery.list();

        return (Collection<IReservation>) DynamicMapper.mapCollection(retList);
    }

    public void saveReservation(IReservation address)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        t.begin();

        saveReservation(address, session);

        t.commit();
    }

    public void saveReservation(IReservation reservation, Session session)
    {
        DBReservation dbr = (DBReservation) DynamicMapper.map(reservation);

        if (dbr.getId() == null)
        {
            session.saveOrUpdate(dbr);
            reservation.setId(dbr.getId());
        }
        else
        {
            session.saveOrUpdate(session.merge(dbr));
        }
    }

    public void saveReservationItem(IReservationItem reservationItem)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        t.begin();

        saveReservationItem(reservationItem, session);

        t.commit();
    }

    public void saveReservationItem(IReservationItem reservationItem, Session session)
    {
        DBReservationItem dbri = (DBReservationItem) DynamicMapper.map(reservationItem);

        session.saveOrUpdate(dbri);
    }
}
