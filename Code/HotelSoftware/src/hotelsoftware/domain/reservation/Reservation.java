/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.reservation;

import hotelsoftware.domain.parties.Party;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Reservation
{
    private Date start;
    private Date end;
    private String comment;
    private Date created;
    private Collection<Option> optionCollection;
    private Party party;
    private Collection<ReservationItem> reservationItemCollection;

    private Reservation()
    {
    }
    
    public static Reservation newReservation()
    {
        return new Reservation();
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public Date getEnd()
    {
        return end;
    }

    public void setEnd(Date end)
    {
        this.end = end;
    }

    public Collection<Option> getOptionCollection()
    {
        return optionCollection;
    }

    public void setOptionCollection(Collection<Option> optionCollection)
    {
        this.optionCollection = optionCollection;
    }

    public Party getParty()
    {
        return party;
    }

    public void setParty(Party party)
    {
        this.party = party;
    }

    public Collection<ReservationItem> getReservationItemCollection()
    {
        return reservationItemCollection;
    }

    public void setReservationItemCollection(Collection<ReservationItem> reservationItemCollection)
    {
        this.reservationItemCollection = reservationItemCollection;
    }

    public Date getStart()
    {
        return start;
    }

    public void setStart(Date start)
    {
        this.start = start;
    }
}
