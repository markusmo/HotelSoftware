/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.reservation.Reservation;
import at.fhv.roomanizer.domain.room.Room;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Johannes
 */
public class RoomAdapter extends Room implements Adapter<hotelsoftware.model.domain.room.IRoom>
{
    private hotelsoftware.model.domain.room.Room room;

    RoomAdapter(hotelsoftware.model.domain.room.IRoom room)
    {
        this.room = (hotelsoftware.model.domain.room.Room) room;
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
