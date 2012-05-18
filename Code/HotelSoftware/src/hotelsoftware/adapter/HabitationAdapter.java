/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.person.Guest;
import at.fhv.roomanizer.domain.person.User;
import at.fhv.roomanizer.domain.room.Room;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Johannes
 */
public class HabitationAdapter extends Habitation
{
    private hotelsoftware.model.domain.service.Habitation habitation;

    @Override
    public void addGuest(Guest guest)
    {
        //FIXME 
        habitation.addGuests(null);
    }

    @Override
    public Date getCreated()
    {
        return habitation.getCreated();
    }

    @Override
    public String getDeposit()
    {
        return "Fixme";
    }

    @Override
    public Date getEnd()
    {
        return habitation.getEnd();
    }

    @Override
    public List<Guest> getGuests()
    {
        return new LinkedList(habitation.getGuests());
    }

    @Override
    public double getPrice()
    {
        return habitation.getPrice().doubleValue();
    }

    @Override
    public Room getRoom()
    {
        return new RoomAdapter(habitation.getRooms());
    }

    @Override
    public Date getStart()
    {
        return super.getStart();
    }

    @Override
    public User getUser()
    {
        return super.getUser();
    }

    @Override
    public boolean isBetween(Date start, Date end)
    {
        return super.isBetween(start, end);
    }

    @Override
    public void setCreated(Date created)
    {
        super.setCreated(created);
    }

    @Override
    public void setDeposit(String deposit)
    {
        super.setDeposit(deposit);
    }

    @Override
    public void setEnd(Date end)
    {
        super.setEnd(end);
    }

    @Override
    public void setGuests(List<Guest> guests)
    {
        super.setGuests(guests);
    }

    @Override
    public void setPrice(double price)
    {
        super.setPrice(price);
    }

    @Override
    public void setRoom(Room room)
    {
        super.setRoom(room);
    }

    @Override
    public void setStart(Date start)
    {
        super.setStart(start);
    }

    @Override
    public void setUser(User user)
    {
        super.setUser(user);
    }
}
