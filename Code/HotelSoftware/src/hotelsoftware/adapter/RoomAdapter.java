/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.reservation.Reservation;
import at.fhv.roomanizer.domain.room.*;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Johannes
 */
public class RoomAdapter extends Room
{
    private hotelsoftware.model.domain.room.Room room;

    RoomAdapter(hotelsoftware.model.domain.room.IRoom room)
    {
        this.room = (hotelsoftware.model.domain.room.Room) room;
    }

    @Override
    public void addHabitation(Habitation habitation)
    {
        super.addHabitation(habitation);
    }

    @Override
    public void addReservation(Reservation reservation)
    {
        super.addReservation(reservation);
    }

    @Override
    public void addStatus(Status status, Date start, Date end)
    {
        super.addStatus(status, start, end);
    }

    @Override
    public boolean checkAvailability(Date start, Date end)
    {
        return super.checkAvailability(start, end);
    }

    @Override
    public Category getCategory()
    {
        return super.getCategory();
    }

    @Override
    public List<Habitation> getHabitations()
    {
        return super.getHabitations();
    }

    @Override
    public ICategory getICategory()
    {
        return super.getICategory();
    }

    @Override
    public List<IRoomStatus> getIStatus()
    {
        return super.getIStatus();
    }

    @Override
    public int getId()
    {
        return super.getId();
    }

    @Override
    public String getNumber()
    {
        return super.getNumber();
    }

    @Override
    public List<Reservation> getReservations()
    {
        return super.getReservations();
    }

    @Override
    public List<RoomStatus> getStatus()
    {
        return super.getStatus();
    }

    @Override
    public void setCategory(Category category)
    {
        super.setCategory(category);
    }

    @Override
    public void setHabitations(List<Habitation> habitationList)
    {
        super.setHabitations(habitationList);
    }

    @Override
    public void setId(int id)
    {
        super.setId(id);
    }

    @Override
    public void setNumber(String number)
    {
        super.setNumber(number);
    }

    @Override
    public void setStatus(List<RoomStatus> statusList)
    {
        super.setStatus(statusList);
    }
    
    
}
