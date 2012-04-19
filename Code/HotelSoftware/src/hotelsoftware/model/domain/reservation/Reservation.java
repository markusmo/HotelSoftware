/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.reservation.DBReservation;
import hotelsoftware.model.domain.invoice.Invoice;
import hotelsoftware.model.domain.parties.Party;
import hotelsoftware.model.domain.parties.PartyData;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Reservation implements ReservationData
{
    private Date start;
    private Date end;
    private String comment;
    private Date created;
    private Collection<Option> optionCollection;
    private Party party;
    private Collection<ReservationItem> reservationItemCollection;
    private Integer id;

    Reservation()
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
    public Date getEnd()
    {
        return end;
    }

    public Collection<Option> getOptionCollection()
    {
        return optionCollection;
    }

    public Party getParty()
    {
        return party;
    }

    public Collection<ReservationItem> getReservationItemCollection()
    {
        return reservationItemCollection;
    }

    @Override
    public Date getStart()
    {
        return start;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public void setEnd(Date end)
    {
        this.end = end;
    }

    public void setOptionCollection(Collection<Option> optionCollection)
    {
        this.optionCollection = optionCollection;
    }

    public void setParty(Party party)
    {
        this.party = party;
    }

    public void setReservationItemCollection(Collection<ReservationItem> reservationItemCollection)
    {
        this.reservationItemCollection = reservationItemCollection;
    }

    public void setStart(Date start)
    {
        this.start = start;
    }
    private Invoice getInvoice;

    public Integer getId()
    {
        return id;
    }

    void setId(Integer id)
    {
        if (id == null)
        {
            this.id = id;
        }
    }

    public Collection<OptionData> getOptionCollectionData()
    {
        return new HelperFunctions<OptionData, Option>().castCollectionUp(getOptionCollection());
    }

    public PartyData getPartyData()
    {
        return (PartyData) getParty();
    }

    public Collection<ReservationItemData> getReservationItemCollectionData()
    {
        return new HelperFunctions<ReservationItemData, ReservationItem>().castCollectionUp(getReservationItemCollection());
    }
}