/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.parties.DBCompany;
import hotelsoftware.model.database.parties.DBCompanyType;
import hotelsoftware.model.database.parties.DBGuest;
import hotelsoftware.model.database.parties.DBPrivateCustomer;
import hotelsoftware.model.domain.reservation.Reservation;
import hotelsoftware.model.domain.reservation.ReservationData;
import java.util.Collection;

/**
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
/**
 * 
 * @author Hubert
 *
 */
    private static class PartyFacadeHolder
    {
        private static final PartyFacade INSTANCE = new PartyFacade();
    }
/**
 * gibt alle CompanyTypen zurück
 * @return
 */
    public Collection<CompanyType> getAllTypes()
    {
        return DynamicMapper.mapCollection(DBCompanyType.getAllTypes());
    }
/**
 * sucht nach einer Firma mit entsprechendem nammen
 * @param name
 * @return
 * @throws CompanyNotFoundException
 */
    public Company getCompanyByName(String name)
            throws CompanyNotFoundException
    {
        DBCompany c = DBCompany.getCompanyByName(name);

        if (c == null)
        {
            throw new CompanyNotFoundException();
        }

        return (Company) DynamicMapper.map(c);
    }

    /**
     * sucht einen PrivateCustomer mit entsprechendem namen
     * @param firstName
     * @param lastName
     * @return
     * @throws PrivateCustomerNotFoundException
     * @throws GuestNotFoundException
     */
    public PrivateCustomer getPrivateCustomerByName(String firstName,
            String lastName) throws PrivateCustomerNotFoundException,
            GuestNotFoundException
    {
        DBPrivateCustomer c = DBPrivateCustomer.getPrivateCustomerByName(firstName, lastName);

        if (c == null)
        {
            throw new GuestNotFoundException();
        }

        return (PrivateCustomer) DynamicMapper.map(c);
    }

    Guest getGuestFromReservationNumber(String  reservationNumber)
    {
        return (Guest) DynamicMapper.map(DBGuest.getGuestFromReservationNumber(reservationNumber));
    }
/**
 * sucht nach einem Gast anhand eines Namens
 * @param firstName
 * @param lastName
 * @return
 * @throws CompanyNotFoundException
 * @throws GuestNotFoundException
 */
    @SuppressWarnings("unchecked")
    public Collection<Guest> getGuestByName(String firstName, String lastName)
            throws CompanyNotFoundException, GuestNotFoundException
    {
        Collection<DBGuest> c = DBGuest.getGuestsByName(firstName, lastName);

        if (c == null)
        {
            throw new GuestNotFoundException();
        }

        return (Collection<Guest>) DynamicMapper.map(c);
    }
}
