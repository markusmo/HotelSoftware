package hotelsoftware.model.domain.reservation;

import hotelsoftware.model.database.manager.ReservationManager;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.controller.data.parties.PartyData;
import hotelsoftware.controller.data.reservation.ReservationItemData;
import hotelsoftware.controller.data.reservation.ReservationOptionData;
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.parties.IGuest;
import hotelsoftware.model.domain.parties.IParty;
import hotelsoftware.model.domain.users.IUser;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.Set;

/**
 * Diese Klasse bildet eine Reservierung ab, mit der das System arbeitet.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Reservation implements IReservation
{
    private Date startDate;
    private Date endDate;
    private String comment;
    private Date created;
    private Collection<IReservationOption> reservationOptions;
    private IParty party;
    private Collection<IReservationItem> reservationItems;
    private Collection<IGuest> guests;
    private Integer id;
    private String reservationNumber;
    private IUser user;

    public Reservation()
    {
    }

    /**
     * Liefert eine neue Instanz einer Reservierung zurueck, jedes Feld muss
     * mittels Getter gesetzt werden.
     *
     * @return
     * eine neue leere Instanz.
     */
    public static IReservation newInstance()
    {
        return new Reservation();
    }

    public static int getHighestId()
    {
        return ReservationManager.getInstance().getHighestReservationId();
    }

    @Override
    public String getComment()
    {
        return comment;
    }

    @Override
    public Date getCreated()
    {
        return created;
    }

    @Override
    public Date getEndDate()
    {
        return endDate;
    }

    @Override
    public Collection<IReservationOption> getReservationOptions()
    {
        return reservationOptions;
    }

    @Override
    public IParty getParty()
    {
        return party;
    }

    @Override
    public void setParty(IParty party)
    {
        this.party = party;
    }

    @Override
    public Collection<IReservationItem> getReservationItems()
    {
        return reservationItems;
    }

    @Override
    public void setReservationItems(Collection<IReservationItem> reservationItems)
    {
        this.reservationItems = reservationItems;
    }

    @Override
    public Collection<IGuest> getGuests()
    {
        return guests;
    }

    @Override
    public void setGuests(Collection<IGuest> guests)
    {
        this.guests = guests;
    }

    @Override
    public Date getStartDate()
    {
        return startDate;
    }

    @Override
    public void setComment(String comment)
    {
        this.comment = comment;
    }

    @Override
    public void setCreated(Date created)
    {
        this.created = created;
    }

    @Override
    public void setEndDate(Date end)
    {
        this.endDate = end;
    }

    @Override
    public void setReservationOptions(Set<IReservationOption> reservationOptions)
    {
        this.reservationOptions = reservationOptions;
    }

    @Override
    public void setStartDate(Date start)
    {
        this.startDate = start;
    }

    @Override
    public Integer getId()
    {
        return id;
    }

    @Override
    public void setId(Integer id)
    {
        if (this.id == null)
        {
            this.id = id;
        }
    }

    @Override
    public Collection<ReservationOptionData> getOptionCollectionData()
    {
        return new HelperFunctions<ReservationOptionData, IReservationOption>().castCollectionUp(reservationOptions);
    }

    @Override
    public PartyData getPartyData()
    {
        return (PartyData) getParty();
    }

    @Override
    public Collection<ReservationItemData> getReservationItemCollectionData()
    {
        return new HelperFunctions<ReservationItemData, IReservationItem>().castCollectionUp(
                reservationItems);
    }

    @Override
    public Integer getGuestAmount()
    {
        int retValue = 0;
        for(IReservationItem res : this.getReservationItems())
        {
            retValue += res.getRoomCategory().getBedCount();
        }
        return retValue;
    }

    public static Collection<IReservation> search(String firstName, String lastName, String companyName, String reservationNumber)
    {
        Collection<IReservation> results = new LinkedList<IReservation>();

        if (reservationNumber.length() > 0)
        {
            IReservation r = getReservationByNumber(reservationNumber);
            if (r != null)
            {
                results.add(r);
            }
        }
        if (firstName.length() > 0 || lastName.length() > 0)
        {
            results.addAll(getReservationsByNameApprox(firstName, lastName));
        }
        if (companyName.length() > 0)
        {
            results.addAll(getReservationsByCompanyNameApprox(companyName));
        }

        return results;
    }

    public static IReservation getReservationByNumber(String reservationNr)
    {
        return ReservationManager.getInstance().getReservationByNumber(reservationNr);
    }

    private static Collection<IReservation> getReservationsByCompanyNameApprox(String companyName)
    {
        return ReservationManager.getInstance().getReservationsByCompanyNameApprox(companyName);
    }

    public static Collection<IReservation> getReservationsByNameApprox(String firstName, String lastName)
    {
        return ReservationManager.getInstance().getReservationsByNameApprox(
                firstName, lastName);
    }

    public static Collection<IReservation> getAllReservations()
    {
        return ReservationManager.getInstance().getAllReservations();
    }

    @Override
    public void setReservationNumber(String reservationNumber)
    {
        this.reservationNumber = reservationNumber;
    }

    @Override
    public void setReserationNumber(String reservationNumber)
    {
        this.reservationNumber = reservationNumber;
    }

    @Override
    public String getReservationNumber()
    {
        return this.reservationNumber;
    }

    @Override
    public String getReserationNumber()
    {
        return this.reservationNumber;
    }

    @Override
    public IUser getUser()
    {
        return user;
    }

    @Override
    public void setUser(IUser user)
    {
        this.user = user;
    }

    @Override
    public Collection<GuestData> getGuestsData()
    {
        return new HelperFunctions<GuestData, IGuest>().castCollectionUp(getGuests());
    }
}
