/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.parties.Party;
import hotelsoftware.model.domain.parties.PartyData;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Reservation implements ReservationData
{
    private Date startDate;
    private Date endDate;
    private String comment;
    private Date created;
    private Set<ReservationOption> optionCollection;
    private Party party;
    private Set<ReservationItem> reservationItems;
    private Integer id;
    private String reservationNumber;

    public Reservation()
    {
    }

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

    public Set<ReservationOption> getOptionCollection()
    {
        return optionCollection;
    }

    public Party getParty()
    {
        return party;
    }

    public void setParty(Party party)
    {
        this.party = party;
    }

    public Set<ReservationItem> getReservationItems()
    {
        return reservationItems;
    }

    public void setReservationItems(Set<ReservationItem> reservationItems)
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

    public void setOptionCollection(Set<ReservationOption> optionCollection)
    {
        this.optionCollection = optionCollection;
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

    public Set<ReservationOptionData> getOptionCollectionData()
    {
        return new HelperFunctions<ReservationOptionData, ReservationOption>().castCollectionUp(getOptionCollection());
    }

    public PartyData getPartyData()
    {
        return (PartyData) getParty();
    }

    public Set<ReservationItemData> getReservationItemCollectionData()
    {
        return new HelperFunctions<ReservationItemData, ReservationItem>().castCollectionUp(reservationItems);
    }

    public Integer getGuestAmount()
    {
        return ReservationFacade.getInstance().getGuestAmount(this);
    }

    public static Reservation getReservationByNumber(int reservationNr)
    {
        return ReservationFacade.getInstance().getReservationByNumber(reservationNr);
    }

    public static Set<Reservation> getReservationsByName(String firstName, String lastName)
    {
        return ReservationFacade.getInstance().getReservationsByName(firstName, lastName);
    }

    public static Set<Reservation> getReservationsByNameApprox(String firstName, String lastName)
    {
        return ReservationFacade.getInstance().getReservationsByNameApprox(firstName, lastName);
    }

    public static Set<Reservation> getAllReservations()
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
}
