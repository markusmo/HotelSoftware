package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.parties.data.AddressData;
import hotelsoftware.model.domain.parties.data.GuestData;
import hotelsoftware.model.domain.reservation.Reservation;
import hotelsoftware.model.domain.reservation.data.ReservationData;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.data.HabitationData;
import hotelsoftware.util.HelperFunctions;
import java.util.*;

/**
 * Klasse für Personen die Gäste im Hotel sind. Sie haben ein Geschlecht, einen vor- und einen nachnamen und implementieren das GuestData interface, welches extra dafür geschrieben wrude. 
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Guest extends Party implements GuestData
{   
    private Integer id;
    private Date birthday;
    private String fname;
    private String lname;
    private Character gender;
    private Collection<Habitation> habitations;

    public Guest()
    {
    }
    
    @Override
    public Integer getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        if (this.id == null)
        {
            this.id = id;
        }
    }

    @Override
    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    @Override
    public String getLname()
    {
        return lname;
    }

    public void setLname(String lname)
    {
        this.lname = lname;
    }

    @Override
    public Character getGender()
    {
        return gender;
    }

    public void setGender(Character gender)
    {
        this.gender = gender;
    }

    public Collection<Habitation> getHabitations()
    {
        return habitations;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public void setCurrentHabitations(Collection<Habitation> habitations)
    {
        this.habitations = habitations;
    }

    @Override
    public Date getBirthday()
    {
        return birthday;
    }

    public Collection<Habitation> getCurrentHabitations()
    {
        return habitations;
    }

    private Guest(String fname, String lname, Character gender, Date birthday,
            Address address)
    {
        this(fname, lname, gender, birthday, address,
                new LinkedHashSet<Habitation>());
    }

    private Guest(String fname, String lname, Character gender, Date birthday,
            Address address, Collection<Habitation> habitations)
    {
        super(address);
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.birthday = birthday;
        this.habitations = habitations;

    }

    /**
     * Instanziert einen neuen Gast
     * @param fname
     * Der Vorname des Gastes
     * @param lname
     * Der Nachname des Gastes
     * @param gender
     * Das Geschlecht des Gastes
     * @param birthday
     * Der Geburtstag des Gastes
     * @param address
     * Die Adresse des Gastes
     * @return
     * Eine neue Instanz eines Gastes
     */
    public static Guest create(String fname, String lname, Character gender,
            Date birthday, Address address)
    {
        return new Guest(fname, lname, gender, birthday, address);
    }

    public void addHabitation(Habitation h)
    {
        habitations.add(h);
    }

    public void removeHabitation(Habitation h)
    {
        habitations.remove(h);
    }

    /**
     * Sucht einen Gast nach Namen
     * @param fname
     * Der Vorname des Gastes
     * @param lname
     * Der Nachname des Gastes
     * @return
     * Den gesuchten Gast
     * @throws CompanyNotFoundException
     * @throws GuestNotFoundException 
     * Wirft ein diesen Fehler, wenn der Gast nicht gefunden wurde.
     */
    public static Collection<Guest> getGuestByName(String fname, String lname)
            throws CompanyNotFoundException, GuestNotFoundException
    {
        return PartyFacade.getInstance().getGuestByName(fname, lname);
    }

    public Collection<HabitationData> getCurrentHabitationsData()
    {
        return new HelperFunctions<HabitationData, Habitation>().castCollectionUp(getCurrentHabitations());
    }

    public Collection<HabitationData> getHabitationsData()
    {
        return new HelperFunctions<HabitationData, Habitation>().castCollectionUp(getHabitations());
    }

    @Override
    public AddressData getAddressData()
    {
        return (AddressData) address;
    }

    /**
     * Sucht einen Gast nach der hinterlegten eindeutigen Reservierungsnummer
     * @param reservationNumber
     * Die eindeutige Reservierungsnummer
     * @return
     * Der Gast, der zu dieser Reservierung gehoert
     */
    public static Guest getGuestFromReservationNumber(String reservationNumber)
    {
        return PartyFacade.getInstance().getGuestFromReservationNumber(reservationNumber);
    }
}
