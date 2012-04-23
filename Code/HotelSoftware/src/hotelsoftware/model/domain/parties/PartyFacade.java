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
 * Kommunikationsklasse zwischen den Schichten
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
 * Hilfsklasse für Singleton
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
 * @param name name 
 * @return Firmenobjekt
 * @throws CompanyNotFoundException firma nicht gefunden
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
     * @param firstName vorname
     * @param lastName nachname
     * @return privatkundenobjekt
     * @throws PrivateCustomerNotFoundException privatkunde nicht gefunden
     * @throws GuestNotFoundException gast nicht gefunden
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
 * @param firstName vorname
 * @param lastName nachname
 * @return Collektion aus Gästen
 * @throws CompanyNotFoundException Firma nicht gefunden
 * @throws GuestNotFoundException Gast nicht gefunden
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
