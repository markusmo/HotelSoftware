package hotelsoftware.model.domain.reservation;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.reservation.DBReservation;
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
public class ReservationFacade
{
    private ReservationFacade()
    {
    }

    public static ReservationFacade getInstance()
    {
        return ReservationFacadeHolder.INSTANCE;
    }

    static int getHighestReservationId()
    {
        return DBReservation.getHighestId();
    }

    private static class ReservationFacadeHolder
    {
        private static final ReservationFacade INSTANCE = new ReservationFacade();
    }

    /**
     * Gibt eine Reservierung, nach der eindeutigen Reservierungsnummer aus
     *
     * @param reservationNr Die eindeutige Reservierungsnummer
     * @return Die Reservierung, die gesucht wurde
     */
    public Reservation getReservationByNumber(String reservationNr)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "SELECT * FROM Reservations r WHERE reservationNumber = :resNr";
        SQLQuery sqlquery = session.createSQLQuery(query);
        sqlquery.setString("resNr", reservationNr);
        sqlquery.addEntity(DBReservation.class);

        DBReservation reservation = (DBReservation) sqlquery.uniqueResult();

        return (Reservation) DynamicMapper.map(reservation);
    }

    /**
     * Gibt eine Reservierung nach dem Gast/Kunden aus, der reserviert hat
     *
     * @param fname Der Vorname des Gastes/Kunden
     * @param lname Der Nachname des Gastes/Kunden
     * @return Die gesuchte Reservierung
     */
    public Collection<Reservation> getReservationsByName(String fname, String lname)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "SELECT * FROM Reservations r WHERE r.idParties = ( SELECT idParties FROM guests g WHERE g.fname = '" + fname + "' AND g.lname = '" + lname + "') ";
        SQLQuery sqlquery = session.createSQLQuery(query);

        sqlquery.addEntity(DBReservation.class);
        List<DBReservation> retList = sqlquery.list();

        return (Collection<Reservation>) DynamicMapper.mapCollection(retList);
    }

    public Collection<Reservation> getReservationsByNameApprox(String fname,
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

        return (Collection<Reservation>) DynamicMapper.mapCollection(retList);
    }

    public Collection<Reservation> getReservationsByCompanyNameApprox(String companyName)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "SELECT * FROM Reservations r WHERE r.idParties IN ( SELECT idParties FROM companies WHERE name like '" + companyName + "%') ";
        SQLQuery sqlquery = session.createSQLQuery(query);

        sqlquery.addEntity(DBReservation.class);
        List<DBReservation> retList = sqlquery.list();

        return (Collection<Reservation>) DynamicMapper.mapCollection(retList);
    }

    /**
     * Gibt eine Reservierung, nach ihrer eindeutigen ID aus
     *
     * @param id Die eindeutige ID
     * @return Die gesuchte Reservierung
     */
    public Reservation getReservationById(int id)
    {
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        Criteria criteria = session.createCriteria(DBReservation.class);
        criteria.add(Restrictions.eq("id", id));
        DBReservation retValue = (DBReservation) criteria.uniqueResult();
        
        return (Reservation) DynamicMapper.map(retValue);
    }

    /**
     * Gibt alle Reservierungen aus
     *
     * @return Alle vorhandenen Reservierungen
     */
    public Collection<Reservation> getAllReservations()
    {
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "SELECT * FROM Reservations r";
        SQLQuery sqlquery = session.createSQLQuery(query);
        sqlquery.addEntity(DBReservation.class);
        
        Collection<DBReservation> retList = sqlquery.list();
        
        return (Collection<Reservation>) DynamicMapper.mapCollection(retList);
    }

    /**
     * Gibt die Anzahl der reservierten Gaeste aus
     *
     * @param reservation Die Reservierung
     * @return Die Anzahl, der Gaeste in einer Reservierung.
     */
    public Integer getGuestAmount(Reservation reservation)
    {
        Collection<IReservationItem> reservationItems = reservation.getReservationItems();
        int retValue = 0;
        for(IReservationItem res : reservationItems)
        {
            retValue += res.getRoomCategory().getBedCount();
        }
        return retValue;
    }
}
