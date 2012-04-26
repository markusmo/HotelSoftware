package hotelsoftware.model.domain.parties;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.parties.*;
import hotelsoftware.model.domain.reservation.Reservation;
import hotelsoftware.model.domain.reservation.data.ReservationData;
import java.util.Collection;
import java.util.Set;

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

    public Collection<Country> getAllCountries()
    {
        return DynamicMapper.mapCollection(DBCountry.getAllCountries());
    }

    private static class PartyFacadeHolder
    {

        private static final PartyFacade INSTANCE = new PartyFacade();
    }

    /**
     * gibt alle Firmentypen zurück
     *
     * @return
     * alle Firmentypen, die vorhanden sind
     */
    public Set<CompanyType> getAllTypes()
    {
        return (Set<CompanyType>) DynamicMapper.mapCollection(
                DBCompanyType.getAllTypes());
    }

    /**
     * sucht nach einer Firma mit entsprechendem nammen
     *
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
     *
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
        DBPrivateCustomer c = DBPrivateCustomer.getPrivateCustomerByName(
                firstName, lastName);

        if (c == null)
        {
            throw new GuestNotFoundException();
        }

        return (PrivateCustomer) DynamicMapper.map(c);
    }

    Guest getGuestFromReservationNumber(String reservationNumber)
    {
        return (Guest) DynamicMapper.map(DBGuest.getGuestFromReservationNumber(
                reservationNumber));
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
    @SuppressWarnings("unchecked")
    public Set<Guest> getGuestByName(String firstName, String lastName)
            throws CompanyNotFoundException, GuestNotFoundException
    {
        Set<DBGuest> c = DBGuest.getGuestsByName(firstName, lastName);

        if (c == null)
        {
            throw new GuestNotFoundException();
        }

        return (Set<Guest>) DynamicMapper.map(c);
    }
}
