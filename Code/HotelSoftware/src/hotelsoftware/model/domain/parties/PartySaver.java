package hotelsoftware.model.domain.parties;

import hotelsoftware.model.database.parties.DBAddress;
import hotelsoftware.model.database.parties.DBCompany;
import hotelsoftware.model.database.parties.DBCompanyType;
import hotelsoftware.model.database.parties.DBGuest;
import hotelsoftware.model.database.parties.DBPrivateCustomer;
import hotelsoftware.model.DynamicMapper;
import java.util.Collection;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import hotelsoftware.util.HibernateUtil;
import hotelsoftware.model.database.FailedToSaveToDatabaseException;
import java.util.Set;

/**
 * Klasse die für die Speicherung in die Datenbank verantwortlich ist.
 *
 * @author Hubert
 *
 */
public class PartySaver
{

    private PartySaver()
    {
    }

    public static PartySaver getInstance()
    {
        return PartySaverHolder.INSTANCE;
    }

    /**
     * Hilfklasse für Singleton
     *
     * @author Hubert
     *
     */
    private static class PartySaverHolder
    {

        private static final PartySaver INSTANCE = new PartySaver();
    }

    /**
     * Speichert oder akualisiert die Informationen in der Datebank
     *
     * @param addresses addressen
     * @param companytypes fimentypen
     * @param companys firmen
     * @param privateCustomers privatkunden
     * @param guests gänste
     * @throws FailedToSaveToDatabaseException konnte nicht in der datenbank
     * eingetragen werden
     */
    public void saveOrUpdate(Set<Address> addresses,
            Set<CompanyType> companytypes, Set<Company> companys,
            Set<PrivateCustomer> privateCustomers,
            Set<Guest> guests) throws FailedToSaveToDatabaseException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        try
        {
            for (Address addresse : addresses)
            {
                DBAddress dbadr = (DBAddress) DynamicMapper.map(addresse);

                session.saveOrUpdate(dbadr);
                addresse.setId(dbadr.getId());
            }

            for (CompanyType type : companytypes)
            {
                DBCompanyType dbct = (DBCompanyType) DynamicMapper.map(type);

                session.saveOrUpdate(dbct);
                type.setId(dbct.getId());
            }

            for (Company company : companys)
            {
                DBCompany dbc = (DBCompany) DynamicMapper.map(company);

                session.saveOrUpdate(dbc);
                company.setId(dbc.getIdParties());
            }

            for (PrivateCustomer privatecustomer : privateCustomers)
            {
                DBPrivateCustomer dbpc = (DBPrivateCustomer) DynamicMapper.map(
                        privatecustomer);

                session.saveOrUpdate(dbpc);
                privatecustomer.setId(dbpc.getIdParties());
            }

            for (Guest guest : guests)
            {
                DBGuest dbg = (DBGuest) DynamicMapper.map(guest);

                session.saveOrUpdate(dbg);
                guest.setId(dbg.getIdParties());
            }

            ts.commit();
        } catch (HibernateException ex)
        {
            ts.rollback();
            throw new FailedToSaveToDatabaseException();
        } finally
        {
            session.close();
        }
    }

    /**
     * macht änderungen rückgängig
     *
     * @param addresses adressen
     * @param companytypes firmentypen
     * @param companys firmen
     * @param privateCustomers privatkunden
     * @param guests gäste
     */
    /*public void rollback(Set<Address> addresses,
            Set<CompanyType> companytypes, Set<Company> companys,
            Set<PrivateCustomer> privateCustomers,
            Set<Guest> guests)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        for (Address addresse : addresses)
        {
            DBAddress dbadr;

            if (addresse.getId() != null)
            {
                dbadr = (DBAddress) session.createCriteria(DBAddress.class).add(Restrictions.eq(
                        "id", addresse.getId())).uniqueResult();

                Address temp = (Address) DynamicMapper.map(dbadr);

                addresse.setStreet(temp.getStreet());
                addresse.setCity(temp.getCity());
                addresse.setZip(temp.getZip());
                addresse.setEmail(temp.getEmail());
                addresse.setPhone(temp.getPhone());
                addresse.setFax(temp.getFax());
                addresse.setIdCountry(temp.getIdCountry());
            }
        }

        for (CompanyType companyType : companytypes)
        {
            DBCompanyType dbct;

            if (companyType.getId() != null)
            {
                dbct = (DBCompanyType) session.createCriteria(
                        DBCompanyType.class).add(Restrictions.eq("id",
                        companyType.getId())).uniqueResult();

                CompanyType temp = (CompanyType) DynamicMapper.map(dbct);
                companyType.setName(temp.getName());
            }
        }

        for (Company company : companys)
        {
            DBCompany dbc;

            if (company.getId() != null)
            {
                dbc = (DBCompany) session.createCriteria(DBCompany.class).add(Restrictions.eq(
                        "id", company.getId())).uniqueResult();

                Company temp = (Company) DynamicMapper.map(dbc);
                company.setAddress(temp.getAddress());
                company.setCompanyname(temp.getCompanyname());
                company.setContactPersons(temp.getContactPersons());
                company.setInvoiceAddress(temp.getInvoiceAddress());
                company.setInvoices(temp.getInvoices());
                company.setType(temp.getType());
            }
        }

        for (PrivateCustomer privateCustomer : privateCustomers)
        {
            DBPrivateCustomer dbpc;

            if (privateCustomer.getId() != null)
            {
                dbpc = (DBPrivateCustomer) session.createCriteria(
                        DBPrivateCustomer.class).add(Restrictions.eq("id",
                        privateCustomer.getId())).uniqueResult();

                PrivateCustomer temp = (PrivateCustomer) DynamicMapper.map(dbpc);
                privateCustomer.setAddress(temp.getAddress());
                privateCustomer.setFname(temp.getFname());
                privateCustomer.setGender(temp.getGender());
                privateCustomer.setInvoiceAddress(temp.getInvoiceAddress());
                privateCustomer.setInvoices(temp.getInvoices());
                privateCustomer.setLname(temp.getLname());
            }
        }

        for (Guest guest : guests)
        {
            DBGuest dbg;

            if (guest.getId() != null)
            {
                dbg = (DBGuest) session.createCriteria(DBGuest.class).add(Restrictions.eq(
                        "id", guest.getId())).uniqueResult();

                Guest temp = (Guest) DynamicMapper.map(dbg);
                guest.setAddress(temp.getAddress());
                guest.setBirthday(temp.getBirthday());
                guest.setCurrentHabitations(temp.getCurrentHabitations());
                guest.setFname(temp.getFname());
                guest.setGender(temp.getGender());
                guest.setLname(temp.getLname());
            }
        }

    }*/
}
