package hotelsoftware.model.domain.reservation;

import hotelsoftware.model.database.users.DBUser;
import hotelsoftware.model.domain.reservation.data.ReservationItemData;
import hotelsoftware.model.domain.reservation.data.ReservationOptionData;
import hotelsoftware.model.domain.reservation.data.ReservationData;
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.parties.Party;
import hotelsoftware.model.domain.parties.data.PartyData;
import hotelsoftware.model.domain.users.User;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Diese Klasse bildet eine Reservierung ab, mit der das System arbeitet.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Reservation implements ReservationData
{
    private Date startDate;
    private Date endDate;
    private String comment;
    private Date created;
    private Collection<ReservationOption> reservationOptions;
    private Party party;
    private Collection<ReservationItem> reservationItems;
    private Integer id;
    private String reservationNumber;
    private User user;

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
    public static Reservation newInstance()
    {
        return new Reservation();
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

    public Collection<ReservationOption> getReservationOptions()
    {
        return reservationOptions;
    }

    public Party getParty()
    {
        return party;
    }

    public void setParty(Party party)
    {
        this.party = party;
    }

    public Collection<ReservationItem> getReservationItems()
    {
        return reservationItems;
    }

    public void setReservationItems(Collection<ReservationItem> reservationItems)
    {
        this.reservationItems = reservationItems;
    }

    @Override
    public Date getStartDate()
    {
        return startDate;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public void setEndDate(Date end)
    {
        this.endDate = end;
    }

    public void setReservationOptions(Set<ReservationOption> reservationOptions)
    {
        this.reservationOptions = reservationOptions;
    }

    public void setStartDate(Date start)
    {
        this.startDate = start;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        if (this.id == null)
        {
            this.id = id;
        }
    }

    public Collection<ReservationOptionData> getOptionCollectionData()
    {
        return new HelperFunctions<ReservationOptionData, ReservationOption>().castCollectionUp(reservationOptions);
    }

    public PartyData getPartyData()
    {
        return (PartyData) getParty();
    }

    public Collection<ReservationItemData> getReservationItemCollectionData()
    {
        return new HelperFunctions<ReservationItemData, ReservationItem>().castCollectionUp(
                reservationItems);
    }

    public Integer getGuestAmount()
    {
        return ReservationFacade.getInstance().getGuestAmount(this);
    }

    public static Reservation getReservationByNumber(int reservationNr)
    {
        return ReservationFacade.getInstance().getReservationByNumber(
                reservationNr);
    }

    public static Collection<Reservation> getReservationsByName(String firstName, String lastName)
    {
        return ReservationFacade.getInstance().getReservationsByName(firstName,
                lastName);
    }
    public static Collection<Reservation> getReservationsByNameApprox(String firstName, String lastName)
    {
        return ReservationFacade.getInstance().getReservationsByNameApprox(
                firstName, lastName);
    }

    public static Collection<Reservation> getAllReservations()
    {
        return ReservationFacade.getInstance().getAllReservations();
    }

    public void setReservationNumber(String reservationNumber)
    {
        this.reservationNumber = reservationNumber;
    }

    public void setReserationNumber(String reservationNumber)
    {
        this.reservationNumber = reservationNumber;
        setParty(Guest.getGuestFromReservationNumber(reservationNumber));
    }

    public String getReservationNumber()
    {
        return this.reservationNumber;
    }

    public String getReserationNumber()
    {
        return this.reservationNumber;
    }
    
    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
