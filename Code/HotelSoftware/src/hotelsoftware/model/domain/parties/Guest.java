package hotelsoftware.model.domain.parties;

import hotelsoftware.model.database.manager.PartyManager;
import hotelsoftware.support.CompanyNotFoundException;
import hotelsoftware.support.GuestNotFoundException;
import hotelsoftware.model.database.reservation.DBReservation;
import hotelsoftware.controller.data.parties.AddressData;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.model.domain.reservation.Reservation;
import hotelsoftware.controller.data.reservation.ReservationData;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.model.domain.reservation.IReservation;
import hotelsoftware.model.domain.service.IHabitation;
import hotelsoftware.util.HelperFunctions;
import java.util.*;

/**
 * Klasse für Personen die Gäste im Hotel sind. Sie haben ein Geschlecht, einen vor- und einen nachnamen und implementieren das GuestData interface, welches extra dafür geschrieben wrude. 
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Guest extends Party implements IGuest
{

    private Date birthday;
    private String fname;
    private String lname;
    private Character gender;
    private Collection<IHabitation> habitations;
    private Collection<IReservation> reservations;
    

    public Guest()
    {
        habitations = new LinkedList<IHabitation>();
        reservations = new LinkedList<IReservation>();
    }

    @Override
    public String getFname()
    {
        return fname;
    }

    @Override
    public void setFname(String fname)
    {
        this.fname = fname;
    }

    @Override
    public String getLname()
    {
        return lname;
    }

    @Override
    public void setLname(String lname)
    {
        this.lname = lname;
    }

    @Override
    public Character getGender()
    {
        return gender;
    }

    @Override
    public void setGender(Character gender)
    {
        this.gender = gender;
    }

    @Override
    public Collection<IHabitation> getHabitations()
    {
        return habitations;
    }

    @Override
    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    @Override
    public void setCurrentHabitations(Collection<IHabitation> habitations)
    {
        this.habitations = habitations;
    }
    
    @Override
    public Collection<IReservation> getReservations()
    {
        return reservations;
    }

    @Override
    public void setReservations(Collection<IReservation> reservations)
    {
        this.reservations = reservations;
    }

    @Override
    public Date getBirthday()
    {
        return birthday;
    }

    @Override
    public Collection<IHabitation> getCurrentHabitations()
    {
        return habitations;
    }

    @Override
    public void addHabitation(IHabitation h)
    {
        habitations.add(h);
    }

    @Override
    public void removeHabitation(IHabitation h)
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
    public static Collection<IGuest> getGuestByName(String fname, String lname)
            throws CompanyNotFoundException, GuestNotFoundException
    {
        return PartyManager.getInstance().getGuestByName(fname, lname);
    }

    /**
     * Sucht einen Gast nach Vornamen
     * @param fname der Vorname des Gastes
     * @return eine Liste von Gästen mit diesem Vornamen
     */
    public static Collection<IGuest> getGuestByFName(String fname)
    {
        return PartyManager.getInstance().getGuestsByFName(fname);
    }
    
    /**
     * Sucht einen Gast nach seinem Nachnamen
     * @param lname der Nachname des Gastes
     * @return eine Liste von Gästen mit diesem Nachnamen
     */
    public static Collection<IGuest> getGuestByLName(String lname)
    {
        return PartyManager.getInstance().getGuestsByLName(lname);
    }
    
    @Override
    public Collection<HabitationData> getCurrentHabitationsData()
    {
        return new HelperFunctions<HabitationData, IHabitation>().castCollectionUp(getCurrentHabitations());
    }

    @Override
    public Collection<HabitationData> getHabitationsData()
    {
        return new HelperFunctions<HabitationData, IHabitation>().castCollectionUp(getHabitations());
    }

    @Override
    public AddressData getAddressData()
    {
        return (AddressData) address;
    }
    
    @Override
    public String toString()
    {
        return fname + " " + lname;
    }
}
