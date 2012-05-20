package hotelsoftware.model.domain.parties;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.parties.*;
import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.support.CompanyNotFoundException;
import hotelsoftware.support.GuestNotFoundException;
import hotelsoftware.support.PrivateCustomerNotFoundException;
import hotelsoftware.util.HibernateUtil;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

/**
 * Kommunikationsklasse zwischen den Schichten. Sie dient als Übersetzer.
 *
 * @author Hubert
 */
public class PartyFacade
{
    private PartyFacade()
    {
    }

    public static PartyFacade getInstance()
    {
        return PartyFacadeHolder.INSTANCE;
    }

    private static class PartyFacadeHolder
    {
        private static final PartyFacade INSTANCE = new PartyFacade();
    }

    /**
     * gibt alle Länder aus
     *
     * @return
     * Alle Länder in der Datenbank
     */
    public Collection<ICountry> getAllCountries()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        Criteria criteria = session.createCriteria(DBCountry.class);
        Collection<DBCountry> countries = criteria.list();
        return DynamicMapper.mapCollection(countries);
    }

    /**
     * gibt alle Firmentypen zurück
     *
     * @return
     * alle Firmentypen, die vorhanden sind
     */
    public Set<ICompanyType> getAllTypes()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(DBCompanyType.class);
        List<DBCompanyType> retList = criteria.list();

        return (Set<ICompanyType>) DynamicMapper.mapCollection(retList);
    }

    /**
     * sucht nach einer Firma mit entsprechendem Namen
     *
     * @param name name
     * @return Firmenobjekt
     * @throws CompanyNotFoundException firma nicht gefunden
     */
    public ICompany getCompanyByName(String name)
            throws CompanyNotFoundException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(DBCompany.class).add(Restrictions.eq(
                "name", name));
        DBCompany c = (DBCompany) criteria.uniqueResult();

        if (c == null)
        {
            throw new CompanyNotFoundException();
        }

        return (Company) DynamicMapper.map(c);
    }

    /**
     * sucht einen PrivateCustomer mit entsprechendem Namen
     *
     * @param firstName vorname
     * @param lastName nachname
     * @return privatkundenobjekt
     * @throws PrivateCustomerNotFoundException privatkunde nicht gefunden
     * @throws GuestNotFoundException gast nicht gefunden
     */
    public IPrivateCustomer getPrivateCustomerByName(String firstName,
            String lastName) throws PrivateCustomerNotFoundException,
            GuestNotFoundException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        DBPrivateCustomer cust = (DBPrivateCustomer) session.createCriteria(DBPrivateCustomer.class).add(Restrictions.and(Restrictions.eq("fname", firstName), Restrictions.eq("lname", lastName))).uniqueResult();

        if (cust == null)
        {
            throw new GuestNotFoundException();
        }

        return (IPrivateCustomer) DynamicMapper.map(cust);
    }

    /**
     * Sucht einen Privatkunden nach Vornamen
     *
     * @param firstName der Vorname des Kunden
     * @return eine Set von allen Kunden mit diesem Vornamen
     * @throws PrivateCustomerNotFoundException
     * @throws GuestNotFoundException
     */
    public Set<IPrivateCustomer> getPrivateCustomerByFName(String firstName) throws PrivateCustomerNotFoundException,
            GuestNotFoundException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        Criteria criteria = session.createCriteria(DBPrivateCustomer.class);
        criteria = criteria.add(Restrictions.eq(
                "fname", firstName));
        Collection<DBPrivateCustomer> cust = criteria.list();

        if (cust == null)
        {
            throw new GuestNotFoundException();
        }

        return (Set<IPrivateCustomer>) DynamicMapper.mapCollection(cust);
    }

    /**
     * Sucht einen Privatkunden nach einen Nachnamen
     *
     * @param lastName der Nachname des Kunden
     * @return ein Set von Privatkunden mit diesem Nachnamen
     * @throws PrivateCustomerNotFoundException
     * @throws GuestNotFoundException
     */
    public Set<IPrivateCustomer> getPrivateCustomerByLName(String lastName) throws PrivateCustomerNotFoundException,
            GuestNotFoundException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        Criteria criteria = session.createCriteria(DBPrivateCustomer.class);
        criteria = criteria.add(Restrictions.eq(
                "lname", lastName));
        Collection<DBPrivateCustomer> cust = criteria.list();

        if (cust == null)
        {
            throw new GuestNotFoundException();
        }

        return (Set<IPrivateCustomer>) DynamicMapper.mapCollection(cust);
    }

    /**
     * Gibt einen Gast nach der Reservierungsnummer aus
     *
     * @param reservationNumber die Reservierungsnummer nach der gesucht wird
     * @return einen Gast, der zu dieser Reservierung passt
     */
    public IGuest getGuestFromReservationNumber(String reservationNumber)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        String query = "SELECT * FROM guests r WHERE r.idParties = ( SELECT idParties FROM reservations g WHERE g.reserationNumber = '" + reservationNumber + "') ";
        SQLQuery sqlquery = session.createSQLQuery(query);

        sqlquery.addEntity(DBGuest.class);
        DBGuest guest = (DBGuest) sqlquery.uniqueResult();

        return (IGuest) DynamicMapper.map(guest);
    }

    /**
     * sucht nach einem Gast anhand eines Namens
     *
     * @param firstName vorname
     * @param lastName nachname
     * @return Collektion aus Gästen
     * @throws CompanyNotFoundException Firma nicht gefunden
     * @throws GuestNotFoundException Gast nicht gefunden
     */
    public Set<IGuest> getGuestByName(String firstName, String lastName)
            throws CompanyNotFoundException, GuestNotFoundException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(DBGuest.class);

        if (firstName.isEmpty() && lastName.isEmpty())
        {
            return null;
        }
        if (firstName.isEmpty())
        {
            criteria = criteria.add(Restrictions.eq(
                    "lname", lastName));
        }
        else
        {
            if (lastName.isEmpty())
            {
                criteria = criteria.add(Restrictions.eq(
                        "fname", firstName));
            }
            else
            {
                criteria = criteria.add(Restrictions.eq(
                        "lname", lastName)).add(Restrictions.eq(
                        "fname", firstName));
            }
        }

        List<DBGuest> retList = criteria.list();

        if (retList == null)
        {
            throw new GuestNotFoundException();
        }

        return (Set<IGuest>) DynamicMapper.mapCollection(retList);
    }

    /**
     * Diese Methode sucht nach einen Gast mithilfe des Vornamens
     *
     * @param firstName dies ist der Vorname des Gastes.
     * @return gibt eine Kollektion zurück, welche Objekte vom typ DBGuest
     * enthält.
     */
    public Set<IGuest> getGuestsByFName(String firstName)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(DBGuest.class);

        if (firstName.isEmpty())
        {
            return null;
        }
        criteria = criteria.add(Restrictions.eq(
                "fname", firstName));
        Collection<DBGuest> retList = criteria.list();

        return (Set<IGuest>) DynamicMapper.mapCollection(retList);
    }

    /**
     * Diese Methode sucht nach einen Gast mithilfe des Nachnamens
     *
     * @param lastName dies ist der Nachname des Gastes.
     * @return gibt eine Kollektion zurück, welche Objekte vom typ DBGuest
     * enthält.
     */
    public Set<IGuest> getGuestsByLName(String lastName)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(DBGuest.class);

        if (lastName.isEmpty())
        {
            return null;
        }
        criteria = criteria.add(Restrictions.eq(
                "lname", lastName));
        Collection<DBGuest> retList = criteria.list();

        return (Set<IGuest>) DynamicMapper.mapCollection(retList);
    }
    /**
     * Diese Methode sucht nach Firmen anhand eines Namens.
     *
     * @param name Dies ist der Name der Firma
     * @return Eine Liste von Firmen mit ähnlichen Namen
     */
    public Collection<ICompany> getCompaniesByName(String name)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(DBCompany.class)
                .add(Restrictions.like("name", name));
        Collection<DBCompany> retList = criteria.list();
        
        return DynamicMapper.mapCollection(retList);
    }
}
