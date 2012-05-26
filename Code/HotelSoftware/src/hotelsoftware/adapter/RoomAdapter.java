/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.reservation.Reservation;
import at.fhv.roomanizer.domain.room.*;
import hotelsoftware.model.domain.room.IRoomRoomStatus;
import hotelsoftware.model.domain.room.RoomCategory;
import hotelsoftware.model.domain.room.RoomsRoomStatus;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Johannes
 */
public class RoomAdapter extends Room implements Adapter<hotelsoftware.model.domain.room.IRoom>
{
    private hotelsoftware.model.domain.room.Room room;

    public RoomAdapter()
    {
    }

    public RoomAdapter(hotelsoftware.model.domain.room.IRoom room)
    {
        this.room = (hotelsoftware.model.domain.room.Room) room;
    }

    @Override
    public void addHabitation(Habitation habitation)
    {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void addReservation(Reservation reservation)
    {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void addStatus(Status status, Date start, Date end)
    {
        IRoomRoomStatus st = new RoomsRoomStatus();
        st.setRoomstatus(((StatusAdapter) status).getOurType());
        st.setStart(start);
        st.setEnd(end);
        this.room.getStatus().add(st);
    }

    @Override
    public boolean checkAvailability(Date start, Date end)
    {
        return this.room.isFree(start, end);
    }

    @Override
    public Category getCategory()
    {
        return new CategoryAdapter((RoomCategory) this.room.getCategory());
    }

    @Override
    public List<Habitation> getHabitations()
    {
        return new LinkedList<Habitation>(
                HelperFunctions.castCollectionUp(HelperFunctions.getAdaptedList(room.getHabitations(), HabitationAdapter.class),
                Habitation.class, HabitationAdapter.class));
    }

    @Override
    public ICategory getICategory()
    {
        return getCategory();
    }

    @Override
    public List<IRoomStatus> getIStatus()
    {
        return new LinkedList<IRoomStatus>(
                HelperFunctions.castCollectionUp(HelperFunctions.getAdaptedList(room.getStatus(), RoomStatusAdapter.class),
                IRoomStatus.class, RoomStatusAdapter.class));
    }

    @Override
    public int getId()
    {
        return this.room.getId();
    }

    @Override
    public String getNumber()
    {
        return this.room.getNumber();
    }

    @Override
    public List<Reservation> getReservations()
    {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<RoomStatus> getStatus()
    {
        return new LinkedList<RoomStatus>(
                HelperFunctions.castCollectionUp(HelperFunctions.getAdaptedList(room.getStatus(), RoomStatusAdapter.class),
                RoomStatus.class, RoomStatusAdapter.class));
    }

    @Override
    public void setCategory(Category category)
    {
        this.room.setCategory(((CategoryAdapter) category).getOurType());
    }

    @Override
    public void setHabitations(List<Habitation> habitationList)
    {
        this.room.setHabitations(HelperFunctions.getOurList(HelperFunctions.castCollectionDown(habitationList, Habitation.class, HabitationAdapter.class),
                hotelsoftware.model.domain.service.IHabitation.class));
    }

    @Override
    public void setId(int id)
    {
        this.room.setId(id);
    }

    @Override
    public void setNumber(String number)
    {
        this.room.setNumber(number);
    }

    @Override
    public void setStatus(List<RoomStatus> statusList)
    {
        this.room.setStatus(HelperFunctions.getOurList(HelperFunctions.castCollectionDown(statusList, RoomStatus.class, RoomStatusAdapter.class)));
    }

    @Override
    public void setOurType(hotelsoftware.model.domain.room.IRoom type)
    {
        this.room = (hotelsoftware.model.domain.room.Room) type;
    }

    @Override
    public hotelsoftware.model.domain.room.IRoom getOurType()
    {
        return room;
    }
}
