/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import hotelsoftware.model.database.reservation.DBReservations;
import hotelsoftware.model.domain.parties.Party;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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

    public static List<Reservation> getReservationsByName(String fname)
    {
        List<Reservation> list = new LinkedList<Reservation>();
        DBReservations.getReservationsByFName(fname);
        return list;
    }

    public String getComment()
    {
        return comment;
    }

    public Date getCreated()
    {
        return created;
    }

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

    public Date getStart()
    {
        return start;
    }

    private void setComment(String comment)
    {
        this.comment = comment;
    }

    private void setCreated(Date created)
    {
        this.created = created;
    }

    private void setEnd(Date end)
    {
        this.end = end;
    }

    private void setOptionCollection(Collection<Option> optionCollection)
    {
        this.optionCollection = optionCollection;
    }

    private void setParty(Party party)
    {
        this.party = party;
    }

    private void setReservationItemCollection(Collection<ReservationItem> reservationItemCollection)
    {
        this.reservationItemCollection = reservationItemCollection;
    }

    private void setStart(Date start)
    {
        this.start = start;
    }
}
